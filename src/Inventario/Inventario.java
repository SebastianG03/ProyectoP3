package Inventario;

import Producto.Producto;
import Producto.ProductoAccesorio;
import Producto.ProductoComida;
import Producto.ProductoInsMedico;

public class Inventario {
    private Categoria comida;
    private Categoria accesorio;
    private Categoria insMedico;
    private String[] categorias = {"Comida", "Accesorio", "Insumo Médico"};
    public Inventario(){
        this.comida = new Comida();
        this.accesorio = new Accesorio();
        this.insMedico = new InsMedico();
    }

    public Comida obtenerCatComida(){
        return (Comida) this.comida;
    }
    public Accesorio obtenerCatAccesorio(){

        return (Accesorio) this.accesorio;
    }
    public InsMedico obtenerCatInsMedico(){
        return (InsMedico) this.insMedico;
    }
    public void agregarProducto(Producto nuevoProducto) {
        if (nuevoProducto instanceof ProductoComida) {
            comida.agregarProducto(nuevoProducto);
            ((Comida) comida).vectorizarProducto(nuevoProducto);
        } else if (nuevoProducto instanceof ProductoAccesorio) {
            accesorio.agregarProducto(nuevoProducto);
            ((Accesorio) accesorio).vectorizarProducto(nuevoProducto);
            } else if (nuevoProducto instanceof ProductoInsMedico) {
                insMedico.agregarProducto(nuevoProducto);
            ((InsMedico) insMedico).vectorizarProducto(nuevoProducto);
        }
    }
    public void solicitudCompra(Producto nuevoProducto, int cantidad) {
        if (nuevoProducto instanceof ProductoComida) {
            comida.restarStock(nuevoProducto.obtenerId(), cantidad);
            comida.aumentarUnVendidas(nuevoProducto.obtenerId(), cantidad);
        } else if (nuevoProducto instanceof ProductoAccesorio) {
            accesorio.restarStock(nuevoProducto.obtenerId(), cantidad);
            accesorio.aumentarUnVendidas(nuevoProducto.obtenerId(), cantidad);
        } else if (nuevoProducto instanceof ProductoInsMedico) {
            insMedico.restarStock(nuevoProducto.obtenerId(), cantidad);
            insMedico.aumentarUnVendidas(nuevoProducto.obtenerId(), cantidad);
        }
    }


    @Override
    public String toString(){
        return comida.toString() + accesorio.toString() + insMedico.toString();
    }




    /*
    //OPERACIONES BÁSICAS
    public boolean agregarProducto(Producto p){
        int indiceCategoria = p.obtenerId().obtenerCategoria();
        int indiceEspMacota = p.obtenerId().obtenerEspecieMascota();

        //Asigna la categoría y especie en función de los dos dígitos de la ID
        p.establecerCategoria(categorias[indiceCategoria]);
        p.establecerEspMacota(especiesMascota[indiceEspMacota]);

        //Desplazamiento necesario para acceder al producto al interior de un segmento
        int desplazamiento = matrizCantidad[indiceCategoria][indiceEspMacota] += 1;

        if(desplazamiento < 1000){ //No se pueden añadir más de 999 productos por segmento
            //Anexa el desplazamiento a la ID del producto - Ejm -> "01" + "001" = 01001
            p.obtenerId().establecerDesplazamiento(desplazamiento);
        }else{
            System.out.println("Máximo número de productos alcanzado");
        }


        //Agrega el prodcuto al inventario - Controla que no se agreguen productos repetidos
        boolean repetido = !conjuntoProductos.add(p);
        if(repetido){
            matrizCantidad[indiceCategoria][indiceEspMacota] -= 1;
        }
        return repetido;
    }
    public boolean eliminarProducto(Id id) throws Exception{
        Object[] arregloProductos = conjuntoProductos.toArray();

        int indiceCategoria = id.obtenerCategoria();
        int indiceEspMacota = id.obtenerEspecieMascota();

        int inicioSegmento = busquedaBinaria(arregloProductos, id);
        conjuntoProductos.remove((Producto)arregloProductos[inicioSegmento]);

        try{
            int indiceP = id.obtenerDesplazamiento();

            for(int i = 0; i < matrizCantidad[indiceCategoria][indiceEspMacota] - indiceP; i++){
                id.establecerDesplazamiento(indiceP + i);
                Producto aCambiar = buscarProducto(id);
                aCambiar.obtenerId().establecerDesplazamiento(aCambiar.obtenerId().obtenerDesplazamiento() - 1);
            }

            matrizCantidad[indiceCategoria][indiceEspMacota] -= 1;
            return true;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return false;
    }
    public boolean modificarNombre(Id id, String nombre){
        Object[] arregloProductos = conjuntoProductos.toArray();
        try{
            int indice = busquedaBinaria(arregloProductos, id);
            Producto aModificar = (Producto) arregloProductos[indice];
            aModificar.establecerNombre(nombre);
            return true;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        return false;
    }
    public boolean modificarPrecio(Id id, double precio){
        Object[] arregloProductos = conjuntoProductos.toArray();
        try{
            int indice = busquedaBinaria(arregloProductos, id);
            Producto aModificar = (Producto) arregloProductos[indice];
            aModificar.establecerPrecio(precio);
            return true;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        return false;
    }
    public boolean modificarStock(Id id, int stock){
        Object[] arregloProductos = conjuntoProductos.toArray();
        try{
            int indice = busquedaBinaria(arregloProductos, id);
            Producto aModificar = (Producto) arregloProductos[indice];
            aModificar.establecerStock(stock);
            return true;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        return false;
    }
    public boolean modificarDescripcion(Id id, String descripcion){
        Object[] arregloProductos = conjuntoProductos.toArray();
        try{
            int indice = busquedaBinaria(arregloProductos, id);
            Producto aModificar = (Producto) arregloProductos[indice];
            aModificar.establecerDescripcion(descripcion);
            return true;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        return false;
    }
    public Producto buscarProducto(Id id) throws Exception{
        Object[] arregloProductos = conjuntoProductos.toArray(); //Arreglo ordenado de productos

        //Indices tabla de segmentos                          primeros dos digitos ID producto -> {'1','0'}
        int indiceCategoria = id.obtenerCategoria();
        int indiceEspMacota = id.obtenerEspecieMascota();

        if(indiceCategoria >= categorias.length || indiceEspMacota >= especiesMascota.length){
            throw new Exception("Formato de ID incorrecto");
        }

        //Indice producto          indice inicio segmento               desplazamiento
        //                         tablaSegmentos[1][0] = 45            desplazamiento = 002 -> 2
        //Indice = 45 + 2 -1 = 46
        int indice = tablaSegmentos[indiceCategoria][indiceEspMacota] + id.obtenerDesplazamiento() -1;

        //Acesso al producto - Ejm: arregloProductos[46] = producto encontrado
        Producto encontrado = (Producto) arregloProductos[indice];
        if(encontrado == null){
            throw new Exception("El producto no se encuentra en el inventario");
        }
        return encontrado;
    }
    public void calificarProducto(Id id, int calificacion){
        Object[] arregloProductos = conjuntoProductos.toArray();
        try{
            int indice = busquedaBinaria(arregloProductos, id);
            Producto aModificar = (Producto) arregloProductos[indice];
            aModificar.establecerCalificacion(calificacion);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }

    //FILTROS
    public Object[] filtrarCategoria(int categoria){
        Object[] arregloProductos = conjuntoProductos.toArray();
        TreeSet<Producto> arregloFiltrado = new TreeSet<>();
        int indice = tablaSegmentos[categoria][0];
        int alcance = 0;
        for(int i = 0; i<matrizCantidad[categoria].length; i++){
            alcance += matrizCantidad[categoria][i];
        }
        for(int j = 0; j < alcance; j++){
            arregloFiltrado.add((Producto) arregloProductos[indice]);
            indice++;
        }
        return arregloFiltrado.toArray();
    }
    public Object[] filtrarEspecieMascota(int especieMacota){
        Object[] arregloProductos = conjuntoProductos.toArray();
        TreeSet<Producto> arregloFiltrado = new TreeSet<>();

        for(int i = 0; i<especiesMascota.length; i++){
            int indice = tablaSegmentos[i][especieMacota];
            int alcance = matrizCantidad[i][especieMacota];
            for(int k = 0; k < alcance; k++){
                arregloFiltrado.add((Producto) arregloProductos[indice]);
                indice++;
            }
        }
        return arregloFiltrado.toArray();
    }

    //ORDEN
    public void ordenarPorPrecio(Object[] arreglo){
        Comparator comparator = new Comparator() {
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
        Arrays.sort(arreglo, comparator);
    }
    public void ordenarPorStock(Object[] arreglo){
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                double precio1 = ((Producto)o1).obtenerStock();
                double precio2 = ((Producto)o2).obtenerStock();
                if(precio1 > precio2){
                    return 1;
                }
                return -1;
            }
        };
        Arrays.sort(arreglo, comparator);
    }
    public void ordenarUnVendidas(Object[] arreglo){
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                double precio1 = ((Producto)o1).obtenerUnVenidas();
                double precio2 = ((Producto)o2).obtenerUnVenidas();
                if(precio1 > precio2){
                    return 1;
                }
                return -1;
            }
        };
        Arrays.sort(arreglo, comparator);
    }
    public void ordenarCalificacion(Object[] arreglo){
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                double precio1 = ((Producto)o1).obtenerCalificacion();
                double precio2 = ((Producto)o2).obtenerCalificacion();
                if(precio1 > precio2){
                    return 1;
                }
                return -1;
            }
        };
        Arrays.sort(arreglo, comparator);
    }

    //METODOS AUXILIARES
    private void mapearInicioSegmentos(){
        Object[] arregloProductos = conjuntoProductos.toArray();

        for(int i = 0; i < tablaSegmentos.length; i++){
            for(int j = 0; j < tablaSegmentos[i].length; j++){

                //Genera la id de los productos al inicio de cada segmento del arreglo
                Id id = new Id(i + String.valueOf(j) + "001");

                try{
                    //Se asigna la posición del producto a su respectiva posición en la matriz de índices
                    tablaSegmentos[i][j] = busquedaBinaria(arregloProductos, id);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex);
                    System.out.println("No hay productos para la categoría: " + categorias[i] +
                            " y la especie: " + especiesMascota[j] + "\n");
                }
            }
        }
    }
    private int busquedaBinaria(Object[] arregloProductos, Id id) throws Exception{
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                int id1 = ((Producto)o1).obtenerId().obtenerDigitos();
                int id2 = ((Id)o2).obtenerDigitos();

                if(id1 == id2){
                    return 0;
                }else if(id1 > id2){
                    return 1;
                }else{
                    return -1;
                }
            }
        };
        //Se busca el producto en función de su ID por medio de búsqueda binaria
        int indice = Arrays.binarySearch(arregloProductos, id, comparator);
        //Si el índice es diferente de 0 significa que el producto no se encuentra en el inventario
        if(indice < 0) {
            throw new Exception("El producto no se enceuntra en el inventario");
        }
        return indice;
    }
    public void restarStock(Id id){
        Object[] arregloProductos = conjuntoProductos.toArray();
        try{
            int indice = busquedaBinaria(arregloProductos, id);
            Producto aModificar = (Producto) arregloProductos[indice];
            aModificar.restarStock();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public void aumentarUnVendidas(Id id){
        Object[] arregloProductos = conjuntoProductos.toArray();
        try{
            int indice = busquedaBinaria(arregloProductos, id);
            Producto aModificar = (Producto) arregloProductos[indice];
            aModificar.aumentarUnVendidas();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }

    //METODOS MOSTRAR CONSOLA
    private String generarTablaSegmentos(){
        String resultado = "TABLA DE SEGMENTOS: \n";
        for(int i = 0; i < tablaSegmentos.length; i++){
            for(int j = 0; j < tablaSegmentos.length; j++){
                resultado += tablaSegmentos[i][j].toString() + "\t";
            }
            resultado += "\n";
        }
        return resultado;
    }
    private String generarMatrizCantidad(){
        String resultado = "MATRIZ DE CANTIDAD: \n";
        for(int i = 0; i < matrizCantidad.length; i++){
            for(int j = 0; j < matrizCantidad[i].length; j++){
                resultado += matrizCantidad[i][j].toString() + "\t";
            }
            resultado += "\n";
        }
        return resultado;
    }
    private void mostrarTablaProductos(Object[] arreglo){
        String separador = generarSeparador(92);
        mapearInicioSegmentos();

        //Encabezado de la tabla
        System.out.println(separador);
        System.out.printf("|%-7s|%-6s|%-7s|%-17s|%-8s|%-8s|%-6s|%-9s|%-3s|%-13s\n",
                "INDICE","ID","NOMBRE","CATEGORÍA","ESPECIE","PRECIO","STOCK","VENDIDOS","CA","DESCRIPCIÓN");
        System.out.println(separador);

        //Contenido de la tabla
        for(int i = 0; i <= arreglo.length - 1; i++){
            System.out.printf("%-8s", i + ". ");
            ((Producto)arreglo[i]).mostrarProducto();
        }
        System.out.println(separador);
    }

    //METODOS ESTÉTICOS
    private String generarSeparador(int tamanio){
        String separador = "";
        for(int j = 0; j <= tamanio; j++){
            separador += "—";
        }
        return separador;
    }

    @Override
    public String toString() {
        mostrarTablaProductos(conjuntoProductos.toArray());
        return "\n" + generarTablaSegmentos() + "\n" + generarMatrizCantidad();
    }
     */
}


