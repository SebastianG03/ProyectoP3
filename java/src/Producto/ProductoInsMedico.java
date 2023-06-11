package Producto;

public class ProductoInsMedico extends Producto{
    String marca;
    String fabricante;
    String tipo;

    //CONSTRUCTOR
    public  ProductoInsMedico(String categoria, String especie, String nombre, double precio, double descuento, int stock,
                              String descripcion, String marca, String fabricante, String tipo){
        super(categoria,especie,nombre,precio,descuento,stock,descripcion);
        this.marca = marca;
        this.fabricante = fabricante;
        this.tipo = tipo;
    }
    //METODOS ESTABLECER
    public void establecer_marca(String marca) {
        this.marca = marca;
    }
    public void establecer_fabricante(String fabricante) {
        this.fabricante = fabricante;
    }
    public void establecer_tipo(String tipo) {
        this.tipo = tipo;
    }

    //METODOS OBTENER
    public String obtener_marca() {
        return marca;
    }
    public String obtener_fabricante() {
        return fabricante;
    }
    public String obtener_tipo() {
        return tipo;
    }
    public Object[] obtener_atributos_especificos(){
        return new Object[] {};
    }
}
