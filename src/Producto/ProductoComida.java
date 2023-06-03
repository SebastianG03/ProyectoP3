package Producto;

public class ProductoComida extends Producto{
    String marca;
    String fabricante;
    String raza;
    String sabor;
    String contenedor;
    String etapaVida;
    String tipo;
    public ProductoComida(String categoria, String especie, String nombre, double precio, double descuento, int stock,
                          String descripcion, String marca, String fabricante, String raza, String sabor,
                          String contenedor, String etapaVida, String tipo){
        super(categoria,especie,nombre,precio,descuento,stock,descripcion);
        this.marca = marca;
        this.fabricante = fabricante;
        this.raza = raza;
        this.sabor = sabor;
        this.contenedor = contenedor;
        this.etapaVida = etapaVida;
        this.tipo = tipo;
    }

    //METODOS ESTABLECER
    public void establecerMarca(String marca){
        this.marca = marca;
    }
    public void establecerFabricante(String fabricante){
        this.fabricante = fabricante;
    }
    public void establecerRaza(String raza){
        this.raza = raza;
    }
    public void establecerSabor(String sabor){
        this.sabor = sabor;
    }
    public void establecerContenedor(String contenedor){
        this.contenedor = contenedor;
    }
    public void establecerEtapaDeVida(String etapaVida){
        this.etapaVida = etapaVida;
    }
    public void establecerTipo(String tipo){
        this.tipo = tipo;
    }

    //METODOS OBTENER
    public String obtenerMarca(){
        return this.marca;
    }
    public String obtenerFabricante(){
        return this.fabricante;
    }
    public String obtenerRaza(){
        return this.raza;
    }
    public String obtenerSabor(){
        return this.sabor;
    }
    public String obtenerContenedor(){
        return this.contenedor;
    }
    public String obtenerEtapaDeVida(){
        return this.etapaVida;
    }
    public String obtenerTipo(){
        return this.tipo;
    }
    public Object[] obtenerAtributosGenerales(){
        return new Object[] {obtenerId().toString(), obtenerNombre(), obtenerEspecie(),obtenerPrecio(),
                obtenerDescuento(), obtenerStock(), obtenerUnVenidas(), obtenerCalificacion(), obtenerDescripcion()};
    }
    public Object[] obtenerAtributosEspecificos(){
        return new Object[] {obtenerId().toString(), this.marca, this.fabricante, this.raza, this.sabor, this.contenedor
                , this.etapaVida, this.tipo};
    }
}
