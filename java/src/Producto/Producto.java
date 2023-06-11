package Producto;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;

public abstract class Producto implements Comparable{
    private Id identificador; // Atributo con el que se va a trackear el producto en el sistema
    private String nombre;
    private String categoria; // Puede ser: comida, accesorio e insumo médico
    private String mascota; // Puede ser: perro, gato y conejo
    private double precio;
    private double descuento;
    private int stock; // Cuando sea igual a cero se deshabilita de la tienda
    private int unidadesVendidas;
    private int calificacion; // Del 1 al 5, siendo 5 la mejor valoracion
    private String descripcion; // Descripción detallada del producto

    private List<Integer> vector;
    private DecimalFormat decimalFormat = new DecimalFormat("#." + "0".repeat(2));

    //CONSTRUCTOR
    public Producto (String categoria, String mascota, String nombre, double precio, double descuento,
                     int stock, String descripcion){
        String formattedPrecio = decimalFormat.format(precio);
        String formattedDescuento = decimalFormat.format(descuento);

        this.categoria = categoria;
        this.mascota = mascota;
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
    public void identificador(int categoria, int mascota, int indice){
        identificador = new Id(categoria,mascota,indice);
    }
    public void establecer_desplazamiento(int desplazamiento){
        this.identificador.establecer_desplazamiento(desplazamiento);
    }
    public void establecer_nombre(String nombre){
        this.nombre = nombre;
    }
    public void establecer_precio(double precio){
        this.precio = precio;
    }
    public void establecer_descuento(double descuento){
        this.descuento = descuento;
    }
    public void establecer_stock(int stock){
        this.stock = stock;
    }
    public void establecer_descripcion(String descripcion){
        this.descripcion = descripcion;
    }

    //METODOS OBTENER
    public String obtenerCategoria(){
        return this.categoria;
    }
    public String obtener_nombre(){
        return this.nombre;
    }
    public double obtener_descuento(){
        return this.descuento;
    }
    public String obtener_mascota(){
        return this.mascota;
    }
    public int obtener_identificador(){
        return identificador.obtener_identificador();
    }
    public Id obtener_id(){
        return this.identificador;
    }
    public double obtener_precio(){
        return this.precio;
    }
    public int obtener_stock(){
        return this.stock;
    }
    public int obtener_unidades_vendidas(){
        return this.unidadesVendidas;
    }
    public int obtener_calificacion(){
        return this.calificacion;
    }
    public String obtener_descripcion(){
        return this.descripcion;
    }
    public List<Integer> obtener_vector(){
        return this.vector;
    }
    public Object[] obtener_atributos_generales(){
        return new Object[] {this.identificador, this.nombre, this.mascota, this.precio, this.descuento,
        this.stock, this.unidadesVendidas, this.calificacion, this.descripcion};
    }
    abstract Object[] obtener_atributos_especificos();

    //METODOS AUXILIARES
    public void restar_stock(int cantidad){
        this.stock -= cantidad;
    }
    public void aumentar_unidades_vendidas(int cantidad){
        this.unidadesVendidas += cantidad;
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
        int id1 = this.identificador.obtener_identificador();
        int id2 = ((Producto) o).obtener_identificador();

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
