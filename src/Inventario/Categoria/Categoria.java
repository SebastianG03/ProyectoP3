package Inventario.Categoria;

import Producto.Id;
import Producto.Producto;

import javax.swing.*;
import java.util.*;

public abstract class Categoria {
    String[] ESPECIES = {"Perro", "Gato", "Conejo"};
    private List<Integer> cantidadProductos;
    private List<Integer> indiceOrigen;
    Set<Object> productos;

    //CONSTRUCTOR
    public Categoria(){
        productos = new TreeSet<>();
        cantidadProductos = new LinkedList<>();
        indiceOrigen = new LinkedList<>();
        inicializarArreglos();
    }

    //METODOS OBTENER
    public String[] obtenerEspecies(){
        return this.ESPECIES;
    }
    public Object[] obtenerProductos(){
        return productos.toArray();
    }


    //OPERACIONES BÁSICAS
    public void agregarProducto(Producto nuevoProducto){
        try{
            int indiceEspecie = obtenerIndiceEspecie(nuevoProducto.obtenerEspecie());
            int desplazamiento = cantidadProductos.get(indiceEspecie) + 1;

            nuevoProducto.establecerIdentificador(indiceEspecie,desplazamiento);
            if(productos.add(nuevoProducto)){
                aumentarCantidadProducto(indiceEspecie);
            }

        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public Producto buscarProducto(Id identificador) throws Exception{
        Object[] arregloProductos = productos.toArray();
        int indice = busquedaBinaria(arregloProductos,identificador);
        if(indice >= 0){
            return (Producto) arregloProductos[indice];
        }
        throw new Exception("Producto no encontrado");
    }
    public void eliminarProducto(Id identificador){
        Object[] arregloProductos = productos.toArray();

        try{
            Producto aEliminar = buscarProducto(identificador);
            productos.remove(aEliminar); //Elimina el producto del TreeSet

            int indiceEspecie = aEliminar.obtenerId().obtenerIdentificadorEspecie();
            int indiceEliminado = busquedaBinaria(arregloProductos, identificador);
            int desplazamiento = aEliminar.obtenerId().obtenerDesplazamiento();

            while(desplazamiento < cantidadProductos.get(indiceEspecie)){
                //Corrige el desplazamiento de los productos que se encntraban despúes del producto eliminado
                ((Producto)arregloProductos[indiceEliminado]).establecerDesplazamiento(desplazamiento);
                indiceEliminado ++;
                desplazamiento ++;
            }

            restarCantidadProducto(indiceEspecie);

        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void modificarProducto(Id identificador, String nombre, double precio, double descuento,
                                  int stock, String descripcion){
        Object[] arregloProductos = productos.toArray();
        try{
            Producto aModificar = (Producto) arregloProductos[busquedaBinaria(arregloProductos,identificador)];
            aModificar.establecerNombre(nombre);
            aModificar.establecerPrecio(precio);
            aModificar.establecerDescuento(descuento);
            aModificar.establecerStock(stock);
            aModificar.establecerDescripcion(descripcion);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    abstract void vectorizarProducto(Producto nuevoProducto);

    //FILTROS
    public Object[] filtrarEspecieMascota(String especie){
        Object[] arregloProductos = productos.toArray();
        List<Object> arregloFiltrado = new ArrayList<>();
        inicializarIndicesOrigen();

        try{
            int indiceEspecie = obtenerIndiceEspecie(especie);
            int indiceOrigen = this.indiceOrigen.get(indiceEspecie);

            for(int i=0; i<cantidadProductos.get(indiceEspecie); i++){
                arregloFiltrado.add(arregloProductos[indiceOrigen]);
                indiceOrigen++;
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return arregloFiltrado.toArray();
    }

    //ORDEN
    public void ordenarPorPrecio(Object[] arreglo){
        Comparator comparador = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                double precio1 = ((Producto)o1).obtenerPrecio();
                double precio2 = ((Producto)o2).obtenerPrecio();
                if(precio1 > precio2){
                    return 1;
                }
                return -1;
            }
        };
        Arrays.sort(arreglo, comparador);
    }
    public void ordenarPorStock(Object[] arreglo){
        Comparator comparador = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                double stock1 = ((Producto)o1).obtenerStock();
                double stock2 = ((Producto)o2).obtenerStock();
                if(stock1 > stock2){
                    return 1;
                }
                return -1;
            }
        };
        Arrays.sort(arreglo, comparador);
    }
    public void ordenarUnVendidas(Object[] arreglo){
        Comparator comparador = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                double unidadesVendidas1 = ((Producto)o1).obtenerUnVenidas();
                double unidadesVendidas2 = ((Producto)o2).obtenerUnVenidas();
                if(unidadesVendidas1 > unidadesVendidas2){
                    return 1;
                }
                return -1;
            }
        };
        Arrays.sort(arreglo, comparador);
    }
    public void ordenarCalificacion(Object[] arreglo){
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                double calificacion1 = ((Producto)o1).obtenerCalificacion();
                double calificacion2 = ((Producto)o2).obtenerCalificacion();
                if(calificacion1 > calificacion2){
                    return 1;
                }
                return -1;
            }
        };
        Arrays.sort(arreglo, comparator);
    }

    //METODOS AUXILIARES
    private void inicializarArreglos(){
        for(int i=0; i< ESPECIES.length; i++){
            cantidadProductos.add(0);
            indiceOrigen.add(0);
        }
    }
    protected int busquedaBinaria(Object[] productos, Id identificador) throws Exception{
        Comparator comparador = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                int id1 = ((Producto)o1).obtenerIdentificador();
                int id2 = Integer.parseInt(o2.toString());
                if(id1 == id2){
                    return 0;
                }else if(id1 > id2){
                    return 1;
                }else{
                    return -1;
                }
            }
        };
        //Busqueda binaria en función del identificador del producto
        int indice = Arrays.binarySearch(productos,identificador,comparador);
        if(indice < 0){
            throw new Exception("No hay producto");
        }
        return indice;
    }
    private int obtenerIndiceEspecie(String especie) throws Exception{
        int indice = -1;
        for(int i = 0; i<this.ESPECIES.length; i++) {
            if (this.ESPECIES[i].compareToIgnoreCase(especie)==0) {
                indice = i;
                break;
            }
        }
        if(indice < 0){
            throw new Exception("No hay la especie");
        }
        return indice;
    }
    public void restarCantidadProducto(int indiceEspecie){
        int cantidad = cantidadProductos.get(indiceEspecie);
        cantidadProductos.set(indiceEspecie, cantidad - 1);
    }
    public void aumentarCantidadProducto(int indiceEspecie){
        int cantidad = cantidadProductos.get(indiceEspecie);
        cantidadProductos.set(indiceEspecie, cantidad + 1);
    }
    public void inicializarIndicesOrigen(){
        Object[] arregloProductos = productos.toArray();
        try{
            Id identificador;
            for(int i = 0; i < ESPECIES.length; i++){
                identificador = new Id(i,1);
                indiceOrigen.set(i,busquedaBinaria(arregloProductos, identificador));
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void restarStock(Id id, int cantidad){
        Object[] arregloProductos = productos.toArray();
        try{
            int indice = busquedaBinaria(arregloProductos, id);
            Producto aModificar = (Producto) arregloProductos[indice];
            aModificar.restarStock(cantidad);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void aumentarUnVendidas(Id id){
        Object[] arregloProductos = productos.toArray();
        try{
            int indice = busquedaBinaria(arregloProductos, id);
            Producto aModificar = (Producto) arregloProductos[indice];
            aModificar.aumentarUnVendidas();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    abstract void insertarProductosDefecto();

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Object o: productos){
            sb.append(o.toString() + "\n");
        }
        return sb.toString();
    }
}
