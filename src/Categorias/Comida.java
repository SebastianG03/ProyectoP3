package Categorias;
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
    private final String[] ETAPADEVIDA = {"Bebé", "Adulto"};
    private final String[] TIPO = {"Balanceado", "Comida húmeda", "Comida cruda", "Comida especializada"};
    public Comida(){
        insertarProductosDefecto();
    }

    //METODOS OBTENER
    public String[] obtenerMARCA() {
        return MARCA;
    }
    public String[] obtenerFABRICANTE() {
        return FABRICANTE;
    }
    public String[] obtenerRAZA() {
        return RAZA;
    }
    public String[] obtenerSABOR() {
        return SABOR;
    }
    public String[] obtenerCONTENEDOR() {
        return CONTENEDOR;
    }
    public String[] obtenerETAPADEVIDA() {
        return ETAPADEVIDA;
    }
    public String[] obtenerTIPO() {
        return TIPO;
    }

    public void modificarProductoComida(Id identificador, String marca, String fabricante, String raza, String sabor,
                                        String contenedor, String etapaDeVida, String tipo){
        Object[] arregloProductos = productos.toArray();
        try{
            ProductoComida aModificar = (ProductoComida) arregloProductos[busquedaBinaria(arregloProductos,identificador)];
            aModificar.establecerMarca(marca);
            aModificar.establecerFabricante(fabricante);
            aModificar.establecerRaza(raza);
            aModificar.establecerSabor(sabor);
            aModificar.establecerContenedor(contenedor);
            aModificar.establecerEtapaDeVida(etapaDeVida);
            aModificar.establecerTipo(tipo);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    //VECTORIZAR PRODUCTO
    public void vectorizarProducto(Producto aVectorizar){
        vectorizarMarca((ProductoComida) aVectorizar);
        vectorizarFabricante((ProductoComida) aVectorizar);
        vectorizarRaza((ProductoComida) aVectorizar);
        vectorizarSabor((ProductoComida) aVectorizar);
        vectorizarContenedor((ProductoComida) aVectorizar);
        vectorizarEtapaVida((ProductoComida) aVectorizar);
        vectorizarTipo((ProductoComida) aVectorizar);
    }

    //METODOS AUXILIARES
    private void vectorizarMarca(ProductoComida aVectorizar){
        List<Integer> vector = aVectorizar.obtenerVector();
        for(String m: MARCA){
            if(aVectorizar.obtenerMarca().equals(m)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizarFabricante(ProductoComida aVectorizar){
        List<Integer> vector = aVectorizar.obtenerVector();
        for(String f: FABRICANTE){
            if(aVectorizar.obtenerFabricante().equals(f)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizarRaza(ProductoComida aVectorizar){
        List<Integer> vector = aVectorizar.obtenerVector();
        for(String r: RAZA){
            if(aVectorizar.obtenerRaza().equals(r)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizarSabor(ProductoComida aVectorizar){
        List<Integer> vector = aVectorizar.obtenerVector();
        for(String s: SABOR){
            if(aVectorizar.obtenerSabor().equals(s)){
                vector.add(1);
            }else {
                vector.add(0);
            }
        }
    }
    private void vectorizarContenedor(ProductoComida aVectorizar){
        List<Integer> vector = aVectorizar.obtenerVector();
        for(String c: CONTENEDOR){
            if(aVectorizar.obtenerContenedor().equals(c)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizarEtapaVida(ProductoComida aVectorizar){
        List<Integer> vector = aVectorizar.obtenerVector();
        for(String e: ETAPADEVIDA){
            if(aVectorizar.obtenerEtapaDeVida().equals(e)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizarTipo(ProductoComida aVectorizar){
        List<Integer> vector = aVectorizar.obtenerVector();
        for(String t: TIPO){
            if(aVectorizar.obtenerTipo().equals(t)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }

    public void insertarProductosDefecto(){
        Random generador = new Random();
        for(int i=0; i<30; i++){
            String especie = ESPECIES[generador.nextInt(0,ESPECIES.length)];
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
            String descripcion = String.format("%s para %s %s,\nmarca %s,\nfabricante %s,\npara %s,\ncon sabor a %s " +
                            "y\ncontenedor tipo %s.",
                    tipo,especie,etapaVida,marca,fabricante,raza,sabor,contenedor);

            ProductoComida productoComida= new ProductoComida("Comida",especie,nombre,precio,descuento,
                    stock,descripcion,marca,fabricante,raza,sabor,contenedor,etapaVida,tipo);
            agregarProducto(productoComida);
            vectorizarProducto(productoComida);
        }
    }
}
