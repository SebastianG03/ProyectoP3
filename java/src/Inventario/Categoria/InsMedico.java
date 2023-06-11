package Inventario.Categoria;

import Producto.*;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class InsMedico extends Categoria{
    private final String[] MARCA = {"Healthy Paw Life", "Huggibles"};
    private final String[] FABRICANTE = {"Classic Agrimed LLC", "Gensavis Pharmaceuticals"};
    private final String[] TIPO = {"Antiparasitario", "Vitamina", "Probiotico"};

    //CONSTRUCTOR
    public InsMedico(String[] mascotas){
        super(mascotas);
        productos_por_defecto();
    }

    //METODOS OBTENER
    public String[] obtenerMarca(){
        return this.MARCA;
    }
    public String[] obtenerFabricante(){
        return this.FABRICANTE;
    }
    public String[] obtenerTipo(){
        return this.TIPO;
    }

    //OPERACIONES BÁSICAS
    public void modificarProductoInsMedico(Id identificador, String marca, String fabricante, String tipo) {
        try{
            ProductoInsMedico modificado = (ProductoInsMedico) buscar_producto(identificador);
            modificado.establecer_marca(marca);
            modificado.establecer_fabricante(fabricante);
            modificado.establecer_tipo(tipo);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    //VECTORIZAR PRODUCTO
    public void vectorizar_producto(Producto aVectorizar){
        vectorizar_marca((ProductoInsMedico) aVectorizar);
        vectorizar_fabricante((ProductoInsMedico) aVectorizar);
        vectorizar_tipo((ProductoInsMedico) aVectorizar);
    }

    //METODOS AUXILIARES
    private void vectorizar_marca(ProductoInsMedico aVectorizar){
        List<Integer> vector = aVectorizar.obtener_vector();
        for(String m: MARCA){
            if(aVectorizar.obtener_marca().equals(m)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizar_fabricante(ProductoInsMedico aVectorizar){
        List<Integer> vector = aVectorizar.obtener_vector();
        for(String f: FABRICANTE){
            if(aVectorizar.obtener_fabricante().equals(f)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizar_tipo(ProductoInsMedico aVectorizar){
        List<Integer> vector = aVectorizar.obtener_vector();
        for(String t: TIPO){
            if(aVectorizar.obtener_tipo().equals(t)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }

    public void productos_por_defecto() {
        Random generador = new Random();
        for (int i = 0; i < 5; i++) {
            String especie = (String) MASCOTAS.keySet().toArray()[generador.nextInt(0,MASCOTAS.size())];
            String marca = MARCA[generador.nextInt(0, MARCA.length)];
            String fabricante = FABRICANTE[generador.nextInt(0, FABRICANTE.length)];
            String tipo = TIPO[generador.nextInt(0, TIPO.length)];
            double precio = generador.nextDouble(1, 9.99);
            double descuento = generador.nextDouble(0, 0.40);
            int stock = generador.nextInt(1, 999);

            String nombre = String.format("%s para %s",
                    tipo,especie);
            String descripcion = String.format("""
                            %s para %s,
                            marca %s,
                            fabricante %s.""",
                    tipo,especie,marca,fabricante);

            ProductoInsMedico productoInsMedico = new ProductoInsMedico("Insumo médico",especie,nombre,
                    precio,descuento,stock,descripcion,marca,fabricante,tipo);
            agregar_producto(2,productoInsMedico);
            vectorizar_producto(productoInsMedico);
        }
    }
}
