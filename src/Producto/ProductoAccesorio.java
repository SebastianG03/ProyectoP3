package Producto;

public class ProductoAccesorio extends Producto{
    private String marca;
    private String fabricante;
    private String tipo;
    private String etapaDeVida;

    //CONSTRUCTOR
    public ProductoAccesorio(String categoria, String especie, String nombre, double precio, double descuento, int stock,
                             String descripcion, String marca, String fabricante, String tipo, String etapaDeVida){
        super(categoria,especie,nombre,precio,descuento,stock,descripcion);
        this.marca = marca;
        this.fabricante = fabricante;
        this.tipo = tipo;
        this.etapaDeVida = etapaDeVida;
    }

    //METODOS ETABLECER
    public void establecer_marca(String marca){
        this.marca = marca;
    }
    public void establecer_fabricante(String fabricante){
        this.fabricante = fabricante;
    }
    public void establecer_tipo(String tipo){
        this.tipo = tipo;
    }
    public void establecer_etapa_de_vida(String etapaDeVida){
        this.etapaDeVida = etapaDeVida;
    }

    //METODOS OBTENER
    public String obtener_marca(){
        return this.marca;
    }
    public String obtener_fabricante(){
        return  this.fabricante;
    }
    public String obtener_tipo(){
        return this.tipo;
    }
    public String obtener_etapa_de_vida(){
        return this.etapaDeVida;
    }
    public Object[] obtener_atributos_especificos(){
        return new Object[] {obtener_id(),this.marca,this.fabricante,this.etapaDeVida,this.tipo};
    }
}
