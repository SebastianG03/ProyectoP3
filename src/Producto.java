public class Producto implements Comparable{
    private Id id; // atributo con el que se va a trackear el producto en el sistema
    private String nombre;// atributo que se presentará al producto a traves de la interfaz
    private String categoria; // puede ser: comida, accesorio e insumo médico
    private String espMascota; // puede ser: perro, gato y conejo
    private double precio; // precio del producto
    private int stock; // debe tener un limite?
    private int unidadesVendidas; // cuando sea igual a cero se evita que se compre mas
    private int calificacion; // del 1 al 5, siendo 5 la mejor valoracion
    private String descripcion; // descripción detallada del producto
    public Producto (String id, String nombre, double precio, int stock, String descripcion){
        this.id = new Id(id);
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.unidadesVendidas = 0;
        this.calificacion = 0;
        this.descripcion = descripcion;
    }
    //METODOS ESTABLCER
    public void establecerNombre(String nombre){
        this.nombre = nombre;
    }
    public void establecerCategoria(String categoria){
        this.categoria = categoria;
    }
    public void establecerEspMacota(String espMascota){
        this.espMascota = espMascota;
    }
    public void establecerPrecio(double precio){
        this.precio = precio;
    }
    public void establecerStock(int stock){
        this.stock = stock;
    }
    public void establecerCalificacion(int calificacion){
        this.calificacion = calificacion;
    }
    public void establecerDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    //METODOS OBTENER
    public Id obtenerId() {
        return this.id;
    }
    public double obtenerPrecio(){
        return this.precio;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public int obtenerStock(){
        return this.stock;
    }
    public int obtenerUnVenidas(){
        return this.unidadesVendidas;
    }
    public int obtenerCalificacion(){
        return this.calificacion;
    }
    //METODOS AUXILIARES
    public void restarStock(){
        this.stock -= 1;
    }
    public void aumentarUnVendidas(){
        this.unidadesVendidas += 1;
    }
    //METODOS MOSTRAR CONSOLA
    public void mostrarProducto() {
        System.out.printf("|%-6s|%-7s|%-17s|%-8s|$%.2f   |%-6s|%-9s|%-3s|%-13s\n",
                id, nombre, categoria, espMascota, precio, stock, unidadesVendidas, calificacion, descripcion);
    }

    @Override
    public int compareTo(Object o) {
        Producto p = (Producto)o;
        Id id1 = p.obtenerId();
        Id id2 = this.id;

        //Evita que se introduzcan productos con el mismo nombre o desccripción
        if(p.nombre.equals(this.nombre) || p.descripcion.equals(this.descripcion)){
            return 0;
            //Ordena los productos según su ID de forma ascendente
        }else if(id1.obtenerDigitos() > id2.obtenerDigitos()){
            return -1;
        }else{
            return 1;
        }
    }

    @Override
    public String toString() {
        return "[ID: " + id + "]";
    }
}
