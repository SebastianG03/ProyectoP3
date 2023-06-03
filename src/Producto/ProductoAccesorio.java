package Producto;

public class ProductoAccesorio extends Producto{
    private String marca;
    private String fabricante;
    private String tipo;
    private String etapaDeVida;
    public ProductoAccesorio(String categoria, String especie, String nombre, double precio, double descuento, int stock,
                             String descripcion, String marca, String fabricante, String tipo, String etapaDeVida){
        super(categoria,especie,nombre,precio,descuento,stock,descripcion);
        this.marca = marca;
        this.fabricante = fabricante;
        this.tipo = tipo;
        this.etapaDeVida = etapaDeVida;
    }

    //METODOS ETABLECER
    public void establecerMarca(String marca){
        this.marca = marca;
    }
    public void establecerFabricante(String fabricante){
        this.fabricante = fabricante;
    }
    public void establecerTipo(String tipo){
        this.tipo = tipo;
    }
    public void establecerEtapaDeVida(String etapaDeVida){
        this.etapaDeVida = etapaDeVida;
    }

    //METODOS OBTENER
    public String obtenerMarca(){
        return this.marca;
    }
    public String obtenerFabricante(){
        return  this.fabricante;
    }
    public String obtenerTipo(){
        return this.tipo;
    }
    public String obtenerEtapaDeVida(){
        return this.etapaDeVida;
    }
    public Object[] obtenerAtributosGenerales(){
        return new Object[] {obtenerId().toString(), obtenerNombre(), obtenerEspecie(),obtenerPrecio(),
                obtenerDescuento(), obtenerStock(), obtenerUnVenidas(), obtenerCalificacion(), obtenerDescripcion()};
    }
    public Object[] obtenerAtributosEspecificos(){
        return new Object[] {};
    }

}
