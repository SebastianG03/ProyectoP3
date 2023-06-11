package Producto;

public class ProductoComida extends Producto{
    private String marca;
    private String fabricante;
    private String raza;
    private String sabor;
    private String contenedor;
    private String etapaVida;
    private String tipo;

    //CONSTRUCTOR
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
    public void establecer_marca(String marca){
        this.marca = marca;
    }
    public void establecer_fabricante(String fabricante){
        this.fabricante = fabricante;
    }
    public void establecer_raza(String raza){
        this.raza = raza;
    }
    public void establecer_sabor(String sabor){
        this.sabor = sabor;
    }
    public void establecer_contenedor(String contenedor){
        this.contenedor = contenedor;
    }
    public void establecer_etapa_de_vida(String etapaVida){
        this.etapaVida = etapaVida;
    }
    public void establecer_tipo(String tipo){
        this.tipo = tipo;
    }

    //METODOS OBTENER
    public String obtener_marca(){
        return this.marca;
    }
    public String obtener_fabricante(){
        return this.fabricante;
    }
    public String obtener_raza(){
        return this.raza;
    }
    public String obtener_sabor(){
        return this.sabor;
    }
    public String obtener_contenedor(){
        return this.contenedor;
    }
    public String obtener_etapa_de_vida(){
        return this.etapaVida;
    }
    public String obtener_tipo(){
        return this.tipo;
    }

    public Object[] obtener_atributos_especificos(){
        return new Object[] {obtener_id().toString(), this.marca, this.fabricante, this.raza, this.sabor, this.contenedor
                , this.etapaVida, this.tipo};
    }
}
