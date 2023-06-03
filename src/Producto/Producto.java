package Producto;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;

public abstract class Producto implements Comparable{
    private Id identificador; // atributo con el que se va a trackear el producto en el sistema
    private String nombre;// atributo que se presentará al producto a traves de la interfaz
    private String categoria; // puede ser: comida, accesorio e insumo médico
    private String especie; // puede ser: perro, gato y conejo
    private double precio; // precio del producto
    private double descuento;
    private int stock; // debe tener un limite?
    private int unidadesVendidas; // cuando sea igual a cero se evita que se compre mas
    private int calificacion; // del 1 al 5, siendo 5 la mejor valoracion
    private String descripcion; // descripción detallada del producto
    private List<Integer> vector;
    private DecimalFormat decimalFormat = new DecimalFormat("#." + "0".repeat(2));

    public Producto (String categoria, String especie, String nombre, double precio, double descuento,
                     int stock, String descripcion){
        String formattedPrecio = decimalFormat.format(precio);
        String formattedDescuento = decimalFormat.format(descuento);

        this.categoria = categoria;
        this.especie = especie;
        this.nombre = nombre;
        this.precio = Double.parseDouble((formattedPrecio.contains(","))? formattedPrecio.replace(",", ".") : formattedPrecio);
        this.descuento = Double.parseDouble((formattedDescuento.contains(","))? formattedDescuento.replace(",", ".") : formattedDescuento);
        this.stock = stock;
        this.unidadesVendidas = 0;
        this.calificacion = 0;
        this.descripcion = descripcion;
        this.vector = new Vector<>();
    }

    //METODOS ESTABLECER
    public void establecerIdentificador(int identificadorEspecie, int desplazamiento){
        identificador = new Id(identificadorEspecie,desplazamiento);
    }
    public void establecerDesplazamiento(int desplazamiento){
        this.identificador.establecerDesplazamiento(desplazamiento);
    }
    public void establecerNombre(String nombre){
        this.nombre = nombre;
    }
    public void establecerPrecio(double precio){
        this.precio = precio;
    }
    public void establecerDescuento(double descuento){
        this.descuento = descuento;
    }
    public void establecerStock(int stock){
        this.stock = stock;
    }
    public void establecerDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    //METODOS OBTENER
    public String obtenerNombre(){
        return this.nombre;
    }
    public double obtenerDescuento(){
        return this.descuento;
    }
    public String obtenerEspecie(){
        return this.especie;
    }
    public int obtenerIdentificador(){
        return identificador.obtenerIdentificador();
    }
    public Id obtenerId(){
        return this.identificador;
    }
    public double obtenerPrecio(){
        return this.precio;
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
    public String obtenerDescripcion(){
        return this.descripcion;
    }
    public List<Integer> obtenerVector(){
        return this.vector;
    }
    abstract Object[] obtenerAtributosGenerales();
    abstract Object[] obtenerAtributosEspecificos();

    //METODOS AUXILIARES
    public void restarStock(){
        this.stock -= 1;
    }
    public void aumentarUnVendidas(){
        this.unidadesVendidas += 1;
    }
    public boolean obtenerAccesibilidad(){
        if(this.stock > 0){
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {
        Producto p = (Producto)o;
        int id1 = this.identificador.obtenerIdentificador();
        int id2 = ((Producto) o).obtenerIdentificador();

        //Evita que se introduzcan productos con el mismo nombre o desccripción
        if(p.nombre.equals(this.nombre) || p.descripcion.equals(this.descripcion)){
            return 0;
            //Ordena los productos según su identificador de manera ascendente
        }else if(id1 > id2){
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public String toString(){
        return String.format("""
                Nombre: %s\n
                Precio: $ %s\n
                Descuento: %s\n
                Stock: %s\n
                Unidades vendidas %s\n
                Calificacion: %s""",nombre,precio,descuento*100 + "%",stock,unidadesVendidas,calificacion);
    }
}
