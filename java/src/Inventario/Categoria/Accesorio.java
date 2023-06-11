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

    //CONSTRUCTOR
    public Accesorio(){
        productos_por_defecto();
    }

    //OPERACIONES BÁSICAS
    public void modificarProducto(Id identificador, String marca, String fabricante, String tipo, String etapaDeVida){
        try{
            ProductoAccesorio modificado = (ProductoAccesorio) buscar_producto(identificador);
            modificado.establecer_marca(marca);
            modificado.establecer_fabricante(fabricante);
            modificado.establecer_tipo(tipo);
            modificado.establecer_etapa_de_vida(etapaDeVida);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void vectorizar_producto(Producto aVectorizar){
        vectorizar_marca((ProductoAccesorio) aVectorizar);
        vectorizar_fabricante((ProductoAccesorio) aVectorizar);
        vectorizar_tipo((ProductoAccesorio) aVectorizar);
        vectorizar_etapa_de_vida((ProductoAccesorio) aVectorizar);
    }

    //METODOS AUXILIARES
    private void vectorizar_marca(ProductoAccesorio aVectorizar){
        List<Integer> vector = aVectorizar.obtener_vector();
        for(String m: MARCA){
            if(aVectorizar.obtener_marca().equals(m)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizar_fabricante(ProductoAccesorio aVectorizar){
        List<Integer> vector = aVectorizar.obtener_vector();
        for(String f: FABRICANTE){
            if(aVectorizar.obtener_fabricante().equals(f)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizar_tipo(ProductoAccesorio aVectorizar){
        List<Integer> vector = aVectorizar.obtener_vector();
        for(String t: TIPO){
            if(aVectorizar.obtener_tipo().equals(t)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizar_etapa_de_vida(ProductoAccesorio aVectorizar){
        List<Integer> vector = aVectorizar.obtener_vector();
        for(String e: ETAPADEVIDA){
            if(aVectorizar.obtener_etapa_de_vida().equals(e)){
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
            String etapaVida = ETAPADEVIDA[generador.nextInt(0,ETAPADEVIDA.length)];
            String tipo = TIPO[generador.nextInt(0,TIPO.length)];
            double precio = generador.nextDouble(1,9.99);
            double descuento = generador.nextDouble(0,0.40);
            int stock = generador.nextInt(1,999);

            String nombre = String.format("%s para %s %s",
                    tipo,especie,etapaVida);
            String descripcion = String.format("""
                            %s para %s %s,
                            marca %s,
                            fabricante %s.""",
                    tipo,especie,etapaVida,marca,fabricante);

            ProductoAccesorio productoAccesorio= new ProductoAccesorio("Accesorio",especie,nombre,precio,descuento,
                    stock,descripcion,marca,fabricante,tipo,etapaVida);
            agregar_producto(productoAccesorio);
            vectorizar_producto(productoAccesorio);
        }
    }
}
