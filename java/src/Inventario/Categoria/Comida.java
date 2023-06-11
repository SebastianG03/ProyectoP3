package Inventario.Categoria;
import Producto.*;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class Comida extends Categoria{
    private final String[] MARCA = {"DogChow", "HappyCat"};
    private final String[] FABRICANTE = {"RoyalCanin", "Purina"};
    private final String[] RAZA = {"Todas las razas", "Razas grandes", "Razas medianas", "Razas Pequeñas"};
    private final String[] SABOR = {"Tocino", "Carne", "Pollo", "Pescado"};
    private final String[] CONTENEDOR = {"Bolsa", "Lata"};
    private final String[] ETAPADEVIDA = {"Cachorro", "Adulto"};
    private final String[] TIPO = {"Balanceado", "Comida húmeda", "Comida cruda", "Comida especializada"};

    //CONSTRUCTOR
    public Comida(){
        productos_por_defecto();
    }

    //METODOS OBTENER
    public String[] obtener_marca() {
        return MARCA;
    }
    public String[] obtener_fabricante() {
        return FABRICANTE;
    }
    public String[] obtener_raza() {
        return RAZA;
    }
    public String[] obtener_sabor() {
        return SABOR;
    }
    public String[] obtener_contenedor() {
        return CONTENEDOR;
    }
    public String[] obtener_etapa_de_vida() {
        return ETAPADEVIDA;
    }
    public String[] obtener_tipo() {
        return TIPO;
    }

    //OPERACIONES BÁSICAS
    public boolean modificar_producto_comida(Id identificador, String marca, String fabricante, String raza,
                                             String sabor, String contenedor, String etapaDeVida, String tipo){
        try{
            ProductoComida modificado = (ProductoComida) buscar_producto(identificador);
            modificado.establecer_marca(marca);
            modificado.establecer_fabricante(fabricante);
            modificado.establecer_raza(raza);
            modificado.establecer_sabor(sabor);
            modificado.establecer_contenedor(contenedor);
            modificado.establecer_etapa_de_vida(etapaDeVida);
            modificado.establecer_tipo(tipo);
            return true;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public void vectorizar_producto(Producto vectorizado){
        vectorizar_marca((ProductoComida) vectorizado);
        vectorizar_fabricante((ProductoComida) vectorizado);
        vectorizar_raza((ProductoComida) vectorizado);
        vectorizar_sabor((ProductoComida) vectorizado);
        vectorizar_contenedor((ProductoComida) vectorizado);
        vectorizar_etapa_de_vida((ProductoComida) vectorizado);
        vectorizar_tipo((ProductoComida) vectorizado);
    }

    //METODOS AUXILIARES
    private void vectorizar_marca(ProductoComida aVectorizar){
        List<Integer> vector = aVectorizar.obtener_vector();
        for(String m: MARCA){
            if(aVectorizar.obtener_marca().equals(m)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizar_fabricante(ProductoComida aVectorizar){
        List<Integer> vector = aVectorizar.obtener_vector();
        for(String f: FABRICANTE){
            if(aVectorizar.obtener_fabricante().equals(f)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizar_raza(ProductoComida aVectorizar){
        List<Integer> vector = aVectorizar.obtener_vector();
        for(String r: RAZA){
            if(aVectorizar.obtener_raza().equals(r)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizar_sabor(ProductoComida aVectorizar){
        List<Integer> vector = aVectorizar.obtener_vector();
        for(String s: SABOR){
            if(aVectorizar.obtener_sabor().equals(s)){
                vector.add(1);
            }else {
                vector.add(0);
            }
        }
    }
    private void vectorizar_contenedor(ProductoComida aVectorizar){
        List<Integer> vector = aVectorizar.obtener_vector();
        for(String c: CONTENEDOR){
            if(aVectorizar.obtener_contenedor().equals(c)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizar_etapa_de_vida(ProductoComida aVectorizar){
        List<Integer> vector = aVectorizar.obtener_vector();
        for(String e: ETAPADEVIDA){
            if(aVectorizar.obtener_etapa_de_vida().equals(e)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizar_tipo(ProductoComida aVectorizar){
        List<Integer> vector = aVectorizar.obtener_vector();
        for(String t: TIPO){
            if(aVectorizar.obtener_tipo().equals(t)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }

    public void productos_por_defecto(){
        Random generador = new Random();
        for(int i=0; i<30; i++){
            String especie = (String) MASCOTAS.keySet().toArray()[generador.nextInt(0,MASCOTAS.size())];
            String marca = MARCA[generador.nextInt(0,MARCA.length)];
            String fabricante = FABRICANTE[generador.nextInt(0,FABRICANTE.length)];
            String raza = RAZA[generador.nextInt(0,RAZA.length)];
            String sabor = SABOR[generador.nextInt(0,SABOR.length)];
            String contenedor = CONTENEDOR[generador.nextInt(0,CONTENEDOR.length)];
            String etapaVida = ETAPADEVIDA[generador.nextInt(0,ETAPADEVIDA.length)];
            String tipo = TIPO[generador.nextInt(0,TIPO.length)];
            double precio = generador.nextDouble(1,9.99);
            double descuento = generador.nextDouble(0,0.40);
            int stock = generador.nextInt(1,999);

            String nombre = String.format("%s para %s %s con sabor a %s",
                    tipo,especie,etapaVida,sabor);
            String descripcion = String.format("""
                            %s para %s %s,
                            marca %s,
                            fabricante %s,
                            para %s,
                            con sabor a %s y
                            contenedor tipo %s.""",
                    tipo,especie,etapaVida,marca,fabricante,raza,sabor,contenedor);

            ProductoComida productoComida= new ProductoComida("Comida",especie,nombre,precio,descuento,
                    stock,descripcion,marca,fabricante,raza,sabor,contenedor,etapaVida,tipo);
            agregar_producto(productoComida);
            vectorizar_producto(productoComida);
        }
    }
}
