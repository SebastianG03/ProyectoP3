package SistemaDeCompras.ClasesDelSistema;

import Producto.Id;
import Producto.Producto;
import Producto.ProductoAccesorio;
import Producto.ProductoComida;

import java.util.ArrayList;
import java.util.List;

public class Filtros {

    private Inventario inventario = new Inventario();

    public Filtros() {

    }


    public Object[] filtrar(String categoria, String raza, String filtro) {
        Object[] list;
        if(raza.compareToIgnoreCase("Ninguno") == 0) {
            list = (categoria.compareToIgnoreCase("Comida") == 0)? obtenerComidas() :
                    (categoria.compareToIgnoreCase("Accesorios") == 0)? obtenerAccesorios() : obtenerInsMedicos();
        } else {
            list = (categoria.compareToIgnoreCase("Comida") == 0)? inventario.obtenerCatComida().filtrarEspecieMascota(raza) :
                    (categoria.compareToIgnoreCase("Accesorios") == 0)? inventario.obtenerCatAccesorio().filtrarEspecieMascota(raza) :
                            inventario.obtenerCatInsMedico().filtrarEspecieMascota(raza);
        }

        ordenarTodosLosProductos(list, filtro);

        return list;
    }

    private void ordenarTodosLosProductos(Object[] productos, String filtro) { //Se puede añadir la categoria que desea buscar
        Object[] obj = new Object[] {productos};

        switch (filtro) {
            case "Precio" -> {
                if (productos instanceof ProductoAccesorio[]) {
                    inventario.obtenerCatAccesorio().ordenarPorPrecio(obj);
                } else if (productos instanceof  ProductoComida[]) {
                    inventario.obtenerCatComida().ordenarPorPrecio(obj);
                } else {
                    inventario.obtenerCatInsMedico().ordenarPorPrecio(obj);
                }
            } case "Stock" -> {
                if(productos instanceof  ProductoAccesorio[]) {
                    inventario.obtenerCatAccesorio().ordenarPorStock(obj);
                } else if (productos instanceof  ProductoComida[]) {
                    inventario.obtenerCatComida().ordenarPorStock(obj);
                } else {
                    inventario.obtenerCatInsMedico().ordenarPorStock(obj);
                }
            } case "Unidades Vendidas" -> {
                if(productos instanceof  ProductoAccesorio[]) {
                    inventario.obtenerCatAccesorio().ordenarUnVendidas(obj);
                } else if(productos instanceof  ProductoComida[]) {
                    inventario.obtenerCatComida().ordenarUnVendidas(obj);
                } else {
                    inventario.obtenerCatInsMedico().ordenarUnVendidas(obj);
                }
            } case "Calificación" -> {
                if(productos instanceof ProductoAccesorio[]) {
                    inventario.obtenerCatAccesorio().ordenarCalificacion(obj);
                } else if(productos instanceof  ProductoComida[]) {
                    inventario.obtenerCatComida().ordenarCalificacion(obj);
                } else {
                    inventario.obtenerCatInsMedico().ordenarCalificacion(obj);
                }
            }
        }
    }


    public Object[] obtenerTodosLosProductos() {
        List<Object> list = new ArrayList<>();
        list.addAll(List.of(obtenerComidas()));
        list.addAll(List.of(obtenerAccesorios()));
        list.addAll(List.of(obtenerInsMedicos()));
        return list.toArray();
    }

    public Producto buscarProductoPorId(Id id) throws Exception {

        return (inventario.obtenerCatComida().buscarProducto(id) != null)? inventario.obtenerCatComida().buscarProducto(id) :
                        (inventario.obtenerCatAccesorio().buscarProducto(id) != null)? inventario.obtenerCatAccesorio().buscarProducto(id) :
                                inventario.obtenerCatInsMedico().buscarProducto(id);
    }

    public Object[] obtenerComidas() {
        return inventario.obtenerCatComida().obtenerProductos();
    }
    public Object[] obtenerAccesorios() {
        return inventario.obtenerCatAccesorio().obtenerProductos();
    }

    public Object[] obtenerInsMedicos() {
        return inventario.obtenerCatInsMedico().obtenerProductos();
    }

}
