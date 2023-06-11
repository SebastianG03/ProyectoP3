package Inventario;

import Inventario.Categoria.*;
import Producto.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventario {
    private List<Categoria> categorias;
    private Map<String, Integer> diccionario;
    private String[] mascotas = new String[] {"perro","gato","conejo"};
    Comida comida;
    Accesorio accesorio;
    InsMedico insMedico;

    //CONSTRUCTOR
    public Inventario(){
        this.comida = new Comida(mascotas);
        this.accesorio = new Accesorio(mascotas);
        this.insMedico = new InsMedico(mascotas);
        //Inicialización lista de categorías
        categorias = new ArrayList<>();
        categorias.add(comida);
        categorias.add(accesorio);
        categorias.add(insMedico);
        //Inicialización Diccionario
        diccionario = new HashMap<>();
        diccionario.put("comida",categorias.indexOf(comida));
        diccionario.put("accesorio",categorias.indexOf(accesorio));
        diccionario.put("insumo médico",categorias.indexOf(insMedico));
    }

    //METODOS OBTENER
    public String[] obtenerMascotas(){
        return this.mascotas;
    }
    public Categoria obtenerCategoria(String categoria){
        int identificador = diccionario.get(categoria.toLowerCase());
        return this.categorias.get(identificador);
    }
    public Comida obtenerCategoriaComida(){
        return (Comida) categorias.get(0);
    }
    public Accesorio obtenerCategoriaAccesorio(){
        return (Accesorio) categorias.get(1);
    }
    public InsMedico obtenerCategoriaInsMedico(){
        return (InsMedico) categorias.get(2);
    }

    //OPERACIONES BÁSICAS
        //AGREGAR
    public boolean agregarProducto(String categoria, Producto nuevoProducto){
        int identificador = diccionario.get(categoria.toLowerCase());
        categorias.get(identificador).vectorizar_producto(nuevoProducto);
        return categorias.get(identificador).agregar_producto(identificador, nuevoProducto);
    }
        //ELIMINAR
    public boolean eliminarProducto(String categoria, Id identificadorProducto){
        int identificadorCategoria = diccionario.get(categoria);
        return categorias.get(identificadorCategoria).eliminar_producto(identificadorProducto);
    }
        //BUSCAR
    public Producto buscarProducto(String categoria, Id identificadorProducto){
        int identificadorCategoria = diccionario.get(categoria);
        try{
            Producto encontrado = categorias.get(identificadorCategoria).buscar_producto(identificadorProducto);
            return encontrado;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public void solicitudCompra(Producto nuevoProducto, int cantidad) {
        if (nuevoProducto instanceof ProductoComida) {
            comida.restar_stock(nuevoProducto.obtener_id(), cantidad);
            comida.aumentar_unidades_vendidas(nuevoProducto.obtener_id(), cantidad);
        } else if (nuevoProducto instanceof ProductoAccesorio) {
            accesorio.restar_stock(nuevoProducto.obtener_id(), cantidad);
            accesorio.aumentar_unidades_vendidas(nuevoProducto.obtener_id(), cantidad);
        } else if (nuevoProducto instanceof ProductoInsMedico) {
            insMedico.restar_stock(nuevoProducto.obtener_id(), cantidad);
            insMedico.aumentar_unidades_vendidas(nuevoProducto.obtener_id(), cantidad);
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<categorias.size(); i++){
            sb.append(categorias.get(i).toString());
        }
        return sb.toString();
    }
}


