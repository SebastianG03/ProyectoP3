package Inventario.Categoria;
import Producto.*;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class Accesorio extends Categoria{
    private final String[] MARCA = {"PetSafe", "BEDSURE"};
    private final String[] FABRICANTE = {"Bunfly", "Dimaka"};
    private final String[] TIPO = {"Juguete", "Ropa", "Adiestramiento", "Higiene", "Mobiliario", "Estética"};
    private final String[] ETAPADEVIDA = {"Cachorro", "Adulto"};
    public Accesorio(){
        insertarProductosDefecto();
    }
    public void modificarProducto(Id identificador, String marca, String fabricante, String tipo, String etapaDeVida){
        Object[] arregloProductos = productos.toArray();
        try{
            ProductoAccesorio aModificar = (ProductoAccesorio) arregloProductos[busquedaBinaria(arregloProductos,identificador)];
            aModificar.establecerMarca(marca);
            aModificar.establecerFabricante(fabricante);
            aModificar.establecerTipo(tipo);
            aModificar.establecerEtapaDeVida(etapaDeVida);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    //VECTORIZAR PRODUCTO
    public void vectorizarProducto(Producto aVectorizar){
        vectorizarMarca((ProductoAccesorio) aVectorizar);
        vectorizarFabricante((ProductoAccesorio) aVectorizar);
        vectorizarTipo((ProductoAccesorio) aVectorizar);
        vectorizarEtapaVida((ProductoAccesorio) aVectorizar);
    }

    //METODOS AUXILIARES
    private void vectorizarMarca(ProductoAccesorio aVectorizar){
        List<Integer> vector = aVectorizar.obtenerVector();
        for(String m: MARCA){
            if(aVectorizar.obtenerMarca().equals(m)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizarFabricante(ProductoAccesorio aVectorizar){
        List<Integer> vector = aVectorizar.obtenerVector();
        for(String f: FABRICANTE){
            if(aVectorizar.obtenerFabricante().equals(f)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizarTipo(ProductoAccesorio aVectorizar){
        List<Integer> vector = aVectorizar.obtenerVector();
        for(String t: TIPO){
            if(aVectorizar.obtenerTipo().equals(t)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizarEtapaVida(ProductoAccesorio aVectorizar){
        List<Integer> vector = aVectorizar.obtenerVector();
        for(String e: ETAPADEVIDA){
            if(aVectorizar.obtenerEtapaDeVida().equals(e)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    public void insertarProductosDefecto(){
        Random generador = new Random();
        for(int i=0; i<10; i++){
            String texto = "";
            String especie = ESPECIES[generador.nextInt(0,ESPECIES.length)];
            String marca = MARCA[generador.nextInt(0,MARCA.length)];
            String fabricante = FABRICANTE[generador.nextInt(0,FABRICANTE.length)];
            String etapaVida = ETAPADEVIDA[generador.nextInt(0,ETAPADEVIDA.length)];
            String tipo = TIPO[generador.nextInt(0,TIPO.length)];
            double precio = generador.nextDouble(1,9.99);
            double descuento = generador.nextDouble(0,0.40);
            int stock = generador.nextInt(1,999);
            for(int j=0; j<5 ; j++){
                char c = (char)(generador.nextInt(5) + 'a');
                texto += c;
            }
            ProductoAccesorio productoAccesorio= new ProductoAccesorio("Accesorio",especie,texto,precio,descuento,
                    stock,texto,marca,fabricante,tipo,etapaVida);
            agregarProducto(productoAccesorio);
            vectorizarProducto(productoAccesorio);
        }
    }
}
