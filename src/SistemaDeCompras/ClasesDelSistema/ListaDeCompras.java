package SistemaDeCompras.ClasesDelSistema;

import Inventario.Categoria.*;
import Inventario.Inventario;
import Producto.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaDeCompras {

    private List<Categoria> listaCompras;
    private Inventario inventario;
    public ListaDeCompras() {
        this.listaCompras = new ArrayList<>();
        inventario = new Inventario();
        inicializarProductos();
    }

    private void inicializarProductos() {
        listaCompras.add(inventario.obtenerCatComida());
        listaCompras.add(inventario.obtenerCatAccesorio());
        listaCompras.add(inventario.obtenerCatInsMedico());
    }

    public List<Object> filtrarComidasPorRaza(String raza) {
        return Arrays.asList((inventario.obtenerCatComida().filtrarEspecieMascota(raza)));
    }

    //Se puede crear un switch en otra funcion para ordenar por producto, si el producto es instanceof Accesorio, Comida, InsMedico.
    public void ordenarTodosLosProductos(List<Producto> productos, String filtro) {
        Object[] obj = new Object[] {productos};

        switch (filtro) {
            case "Precio" -> {
                inventario.obtenerCatAccesorio().ordenarPorPrecio(obj);
                inventario.obtenerCatComida().ordenarPorPrecio(obj);
                inventario.obtenerCatInsMedico().ordenarPorPrecio(obj);
            } case "Stock" -> {
                inventario.obtenerCatAccesorio().ordenarPorStock(obj);
                inventario.obtenerCatComida().ordenarPorStock(obj);
                inventario.obtenerCatInsMedico().ordenarPorStock(obj);
            } case "Unidades Vendidas" -> {
                inventario.obtenerCatAccesorio().ordenarUnVendidas(obj);
                inventario.obtenerCatComida().ordenarUnVendidas(obj);
                inventario.obtenerCatInsMedico().ordenarUnVendidas(obj);
            } case "Calificación" -> {
                inventario.obtenerCatAccesorio().ordenarCalificacion(obj);
                inventario.obtenerCatComida().ordenarCalificacion(obj);
                inventario.obtenerCatInsMedico().ordenarCalificacion(obj);
            }
        }
    }

    public void ordenarPrecioPorCategoria(Object[] obj) {

    }

    public void ordenarStockPorCategoria(Object[] obj) {

    }

    public void ordenarUnidadesVendidasPorCategoria(Object[] obj) {

    }

    public void ordenarCalificacionPorCategoria(Object[] obj) {

    }

    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> list = new ArrayList<>();
        obtenerComidas().forEach(x -> list.add((Producto) x));
        obtenerAccesorios().forEach(x -> list.add((Producto) x));
        obtenerInsMedicos().forEach(x -> list.add((Producto) x));
        return list;
    }

    public List<Categoria> obtenerListaCompras() {
        return listaCompras;
    }

    public int obtenerIndiceCatComida() {
        return listaCompras.indexOf(inventario.obtenerCatComida());
    }

    public int obtenerIndiceCatAccesorio() {
        return listaCompras.indexOf(inventario.obtenerCatAccesorio());
    }

    public int obtenerIndiceCatInsMedico() {
        return listaCompras.indexOf(inventario.obtenerCatInsMedico());
    }

    public List<ProductoComida> obtenerComidas() {
        List<Object> obj = Arrays.asList(listaCompras.get(obtenerIndiceCatComida()).obtenerProductos());
        List<ProductoComida> productos = new ArrayList<>();
        obj.forEach(x -> productos.add((ProductoComida) x));
        return productos;
    }
    public List<ProductoAccesorio> obtenerAccesorios() {
        List<Object> obj = Arrays.asList(listaCompras.get(obtenerIndiceCatComida()).obtenerProductos());
        List<ProductoAccesorio> productos = new ArrayList<>();
        obj.forEach(x -> productos.add((ProductoAccesorio) x));
        return productos;    }

    public List<ProductoInsMedico> obtenerInsMedicos() {
        List<Object> obj = Arrays.asList(listaCompras.get(obtenerIndiceCatComida()).obtenerProductos());
        List<ProductoInsMedico> productos = new ArrayList<>();
        obj.forEach(x -> productos.add((ProductoInsMedico) x));
        return productos;
    }

}
