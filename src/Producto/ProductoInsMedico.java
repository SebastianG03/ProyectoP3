package Producto;

public class ProductoInsMedico extends Producto{
    String marca;
    String fabricante;
    String tipo;
    public  ProductoInsMedico(String categoria, String especie, String nombre, double precio, double descuento, int stock,
                              String descripcion, String marca, String fabricante, String tipo){
        super(categoria,especie,nombre,precio,descuento,stock,descripcion);
        this.marca = marca;
        this.fabricante = fabricante;
        this.tipo = tipo;
    }
    //METODOS ESTABLECER
    public void establecerMarca(String marca) {
        this.marca = marca;
    }
    public void establecerFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
    public void establecerTipo(String tipo) {
        this.tipo = tipo;
    }

    //METODOS OBTENER
    public String obtenerMarca() {
        return marca;
    }
    public String obtenerFabricante() {
        return fabricante;
    }
    public String obtenerTipo() {
        return tipo;
    }
    public Object[] obtenerAtributosGenerales(){
        return new Object[] {obtenerId().toString(), obtenerNombre(), obtenerEspecie(),obtenerPrecio(),
                obtenerDescuento(), obtenerStock(), obtenerUnVenidas(), obtenerCalificacion(), obtenerDescripcion()};
    }
    public Object[] obtenerAtributosEspecificos(){
        return new Object[] {};
    }
}
