package Inventario.Categoria;

import Producto.Id;
import Producto.Producto;

import javax.swing.*;
import java.util.*;
import java.util.TreeSet;

public abstract class Categoria {
    public final Hashtable<String,Integer> MASCOTAS;
    private final List<Integer> tabla_de_cantidades;
    private final List<Integer> tabla_de_indices;
    private final Set<Object> conjunto_de_productos;

    //CONSTRUCTOR
    public Categoria(){
        MASCOTAS = new Hashtable<>(); //Una tabla de mascotas <String> con su respectivo identificador <Integer>
        MASCOTAS.put("perro",0); MASCOTAS.put("gato",1); MASCOTAS.put("conejo",2); //Se inicializan las mascotas por defecto
        conjunto_de_productos = new TreeSet<>(); //Un conjunto ordenado de todos los productos (No acepta duplicados)
        tabla_de_cantidades = new LinkedList<>(); //Tabla que almacena la cantidad de productos que tiene cada mascota
        tabla_de_indices = new LinkedList<>(); //Tabla que almacena los indices del primer producto de cada mascota
        inicializar_tablas(); //Inicializa en cero las tablas de cantidades e indices
    }

    //METODOS OBTENER
    public Object[] obtener_mascotas(){
        return this.MASCOTAS.keySet().toArray();
    }
    public Object[] obtenerProductos(){
        return conjunto_de_productos.toArray();
    }

    //OPERACIONES BÁSICAS
    public boolean agregar_producto(Producto nuevo){
        //Guarda el valor numerico de la mascota en funcion de su llave <String>, Ejm. "perro" -> 0
        int mascota = MASCOTAS.get(nuevo.obtener_mascota().toLowerCase());
        int desplazamiento = tabla_de_cantidades.get(mascota) + 1;
        nuevo.identificador(mascota,desplazamiento); //Asigna al producto un identificador
        if(conjunto_de_productos.add(nuevo)){ //Añade el producto en el TreeSet
            //Si el producto no es duplicado, entonces la cantidad de productos aumenta en uno
            aumentar_cantidad(mascota);//La tabla de cantidades se actualiza en función del indice de la mascota
            return true; //El producto fue agregado con exito
        }
        return false; //El producto no fue agregado (producto duplicado)
    }
    public Producto buscar_producto(Id identificador) throws Exception{
        Exception error = new Exception("No hay un producto con ese identificador, vuelva a intentarlo");
        actualizar_tabla_de_indices();
        if(MASCOTAS.containsValue(identificador.obtener_mascota())){ //Verifica que la mascota se encuentre en el inventario
            int mascota = tabla_de_indices.get(identificador.obtener_mascota()); //Apunta al indice del primer producto de la mascota
            //Verifica que el desplazamiento este dentro de los limites de cada mascota
            if(identificador.obtener_desplazamiento() <= tabla_de_cantidades.get(identificador.obtener_mascota())){
                int desplazamiento = identificador.obtener_desplazamiento() - 1;
                Object[] arreglo_de_productos = conjunto_de_productos.toArray(); //Convierte el conjunto en un arreglo ordenado
                Object encontrado = arreglo_de_productos[mascota + desplazamiento]; //Busca el producto
                return (Producto) encontrado; //Devuelve el producto encontrado
            }else{
                throw error;
            }
        }else{
            throw error;
        }
    }
    public boolean eliminar_producto(Id identificador){
        try{
            Producto eliminado = buscar_producto(identificador); //Busca el producto según el identificador
            conjunto_de_productos.remove(eliminado); //Elimina el producto del TreeSet
            Object[] arreglo_de_productos = conjunto_de_productos.toArray();
            int mascota = identificador.obtener_mascota();
            int desplazamiento = identificador.obtener_desplazamiento() - 1;
            int indice = tabla_de_indices.get(mascota) + desplazamiento; //Indice del producto
            while(desplazamiento < tabla_de_cantidades.get(mascota)){
                //Corrige el desplazamiento de los productos que se encontraban despúes del producto eliminado
                ((Producto)arreglo_de_productos[indice]).establecer_desplazamiento(desplazamiento + 1);
                indice ++;
                desplazamiento ++;
            }
            //La cantidad de productos decrece en uno
            restar_cantidad(mascota); //La tabla de cantidades se actulaiza en función del indice de la mascota
            return true; //El producto fue eliminado con éxito
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            return false; //El producto no fue eliminado (identificador erroneo)
        }
    }
    public void restar_stock(Id identificador, int cantidad){
        try{
            Producto modificado = buscar_producto(identificador);
            modificado.restar_stock(cantidad);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void aumentar_unidades_vendidas(Id identificador, int cantidad){
        try{
            Producto modificado = buscar_producto(identificador);
            modificado.aumentar_unidades_vendidas(cantidad);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public abstract void vectorizar_producto(Producto nuevoProducto);
    public boolean modificar_producto(Id identificador, String nombre, double precio,
                                      double descuento, int stock, String descripcion){
        try{
            Producto modificado = buscar_producto(identificador); //Busca el producto a ser modificado
            modificado.establecer_nombre(nombre); //Modifica el nombre
            modificado.establecer_precio(precio); //Modifica el precio
            modificado.establecer_descuento(descuento); //Modifica el descuento
            modificado.establecer_stock(stock); //Modifica el stock
            modificado.establecer_descripcion(descripcion); //Modifica la descripcion
            return true; //El producto fue modificado con éxito
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            return false; //El producto no fue modificado (identificador erroneo)
        }
    }

    //FILTROS
    public Object[] filtrar_por_mascota(String mascota){
        Object[] arreglo_de_productos = conjunto_de_productos.toArray();
        List<Object> arreglo_filtrado = new ArrayList<>();
        actualizar_tabla_de_indices();
        int indice_mascota = obtener_indice_mascota(mascota);
        int indice_producto = tabla_de_indices.get(indice_mascota);
        if(indice_mascota != -1){
            for(int i = 0; i<tabla_de_cantidades.get(indice_mascota); i++){
                arreglo_filtrado.add(arreglo_de_productos[indice_producto]);
                indice_producto++;
            }
            return arreglo_filtrado.toArray();
        }
        return null;
    }

    //ORDENAMIENTO
    public void ordenar_por_precio(Object[] arreglo){
        Comparator comparador = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                double precio1 = ((Producto)o1).obtener_precio();
                double precio2 = ((Producto)o2).obtener_precio();
                if(precio1 > precio2){
                    return 1;
                }
                return -1;
            }
        };
        Arrays.sort(arreglo, comparador);
    }
    public void ordenar_por_stock(Object[] arreglo){
        Comparator comparador = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                double stock1 = ((Producto)o1).obtener_stock();
                double stock2 = ((Producto)o2).obtener_stock();
                if(stock1 > stock2){
                    return 1;
                }
                return -1;
            }
        };
        Arrays.sort(arreglo, comparador);
    }
    public void ordenar_por_unidades_vendidas(Object[] arreglo){
        Comparator comparador = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                double unidadesVendidas1 = ((Producto)o1).obtener_unidades_vendidas();
                double unidadesVendidas2 = ((Producto)o2).obtener_unidades_vendidas();
                if(unidadesVendidas1 > unidadesVendidas2){
                    return 1;
                }
                return -1;
            }
        };
        Arrays.sort(arreglo, comparador);
    }
    public void ordenar_por_calificacion(Object[] arreglo){
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                double calificacion1 = ((Producto)o1).obtener_calificacion();
                double calificacion2 = ((Producto)o2).obtener_calificacion();
                if(calificacion1 > calificacion2){
                    return 1;
                }
                return -1;
            }
        };
        Arrays.sort(arreglo, comparator);
    }

    //METODOS AUXILIARES
    private void inicializar_tablas(){
        for(int i = 0; i < MASCOTAS.size(); i++){
            tabla_de_cantidades.add(0);
            tabla_de_indices.add(0);
        }
    }
    private void actualizar_tabla_de_indices(){
        int acumulador = 0;
        for(int i = 0; i < MASCOTAS.size(); i++){
            if((i - 1) >= 0){
                acumulador += tabla_de_cantidades.get(i -1);
                tabla_de_indices.set(i, acumulador);
            }
        }
    }
    private void restar_cantidad(int mascota){
        int cantidad = tabla_de_cantidades.get(mascota);
        tabla_de_cantidades.set(mascota, cantidad - 1);
    }
    private void aumentar_cantidad(int mascota){
        int cantidad = tabla_de_cantidades.get(mascota);
        tabla_de_cantidades.set(mascota, cantidad + 1);
    }
    private int obtener_indice_mascota(String mascota){
        if(MASCOTAS.containsKey(mascota.toLowerCase())){
            return MASCOTAS.get(mascota);
        }
        return -1;
    }
    abstract void productos_por_defecto();

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Object o: conjunto_de_productos){
            sb.append(o.toString() + "\n");
        }
        return sb.toString();
    }
}
