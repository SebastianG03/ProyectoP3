package Categorias;
import Producto.*;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class InsMedico extends Categoria{
    private final String[] MARCA = {"Healthy Paw Life", "Huggibles"};
    private final String[] FABRICANTE = {"Classic Agrimed LLC", "Gensavis Pharmaceuticals"};
    private final String[] TIPO = {"Antiparasitario", "Vitamina", "Probiotico"};
    public InsMedico(){
        insertarProductosDefecto();
    }
    public void modificarProducto(Id identificador, String marca, String fabricante, String tipo) {
        Object[] arregloProductos = productos.toArray();
        try{
            ProductoInsMedico aModificar = (ProductoInsMedico) arregloProductos[busquedaBinaria(arregloProductos,identificador)];
            aModificar.establecerMarca(marca);
            aModificar.establecerFabricante(fabricante);
            aModificar.establecerTipo(tipo);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    //VECTORIZAR PRODUCTO
    public void vectorizarProducto(Producto aVectorizar){
        vectorizarMarca((ProductoInsMedico) aVectorizar);
        vectorizarFabricante((ProductoInsMedico) aVectorizar);
        vectorizarTipo((ProductoInsMedico) aVectorizar);
    }

    //METODOS AUXILIARES
    private void vectorizarMarca(ProductoInsMedico aVectorizar){
        List<Integer> vector = aVectorizar.obtenerVector();
        for(String m: MARCA){
            if(aVectorizar.obtenerMarca().equals(m)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizarFabricante(ProductoInsMedico aVectorizar){
        List<Integer> vector = aVectorizar.obtenerVector();
        for(String f: FABRICANTE){
            if(aVectorizar.obtenerFabricante().equals(f)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }
    private void vectorizarTipo(ProductoInsMedico aVectorizar){
        List<Integer> vector = aVectorizar.obtenerVector();
        for(String t: TIPO){
            if(aVectorizar.obtenerTipo().equals(t)){
                vector.add(1);
            }else{
                vector.add(0);
            }
        }
    }

    public void insertarProductosDefecto() {
        Random generador = new Random();
        for (int i = 0; i < 10; i++) {
            String texto = "";
            String especie = ESPECIES[generador.nextInt(0, ESPECIES.length)];
            String marca = MARCA[generador.nextInt(0, MARCA.length)];
            String fabricante = FABRICANTE[generador.nextInt(0, FABRICANTE.length)];
            String tipo = TIPO[generador.nextInt(0, TIPO.length)];
            double precio = generador.nextDouble(1, 9.99);
            double descuento = generador.nextDouble(0, 0.40);
            int stock = generador.nextInt(1, 999);
            for (int j = 0; j < 5; j++) {
                char c = (char) (generador.nextInt(5) + 'a');
                texto += c;
            }
            ProductoInsMedico productoInsMedico = new ProductoInsMedico("Insumo Médico", especie, texto,
                    precio, descuento, stock, texto, marca, fabricante, tipo);
            agregarProducto(productoInsMedico);
            vectorizarProducto(productoInsMedico);
        }
    }
}
