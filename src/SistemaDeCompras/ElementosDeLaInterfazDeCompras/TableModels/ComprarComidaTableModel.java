package SistemaDeCompras.ElementosDeLaInterfazDeCompras.TableModels;

import Inventario.Inventario;
import Producto.ProductoComida;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;


public class ComprarComidaTableModel extends DefaultTableModel {
    private static final String[] titles = {
            "Id", "Nombre",  "Precio", "Marca"
            , "Calificación", "Cantidad", "Más información" ,"Añadir a carrito"
    };//La última fila es la fila del botón.

    private Object[] productos;
    private Inventario inventario = new Inventario();;
    private String filtro;

    public ComprarComidaTableModel() {
        super(0, titles.length);
        this.productos = inventario.obtenerCatComida().obtenerProductos();
        addColumns();
        this.filtro = "Ninguno";

    }

    private void addColumns() {
        setColumnCount(0);
        for (String str : titles) {
            super.addColumn(str);
        }
    }

    public void generateRows() {
        setRowCount(0);
        List<Object> objects = new ArrayList<>();
        for (Object obj : productos) {
            objects.add(obtenerAtributosProducto((ProductoComida) obj));
            objects.add(new JButton());
            objects.add(new JButton());
            super.addRow(objects.toArray());
        }
    }

    public void filtrarProductos(String filtro) {
        setRowCount(0);
        this.filtro = filtro;

        if(!filtro.equals("Ninguno")) {
            productos = inventario.obtenerCatComida().filtrarEspecieMascota(filtro);
        } else {
            productos = inventario.obtenerCatComida().obtenerProductos();
        }

        generateRows();

    }

    public void ordenarProductos(String orden) {

        if(orden.equals("Precio")) {
            inventario.obtenerCatComida().ordenarPorPrecio(productos);
        } else if (orden.equals("Stock")) {
            inventario.obtenerCatComida().ordenarPorStock(productos);
        } else if (orden.equals("Vendidas")) {
            inventario.obtenerCatComida().ordenarUnVendidas(productos);
        } else if (orden.equals("Calificación")) {
            inventario.obtenerCatComida().ordenarCalificacion(productos);
        }

        generateRows();
    }

    private Object[] obtenerAtributosProducto(ProductoComida producto) {
        /*
        *Los atributos se obtendrán en el siguiente orden:  "Id", "Nombre",
        *  "Precio", "Marca"
        * , "Calificación"
        * */
        return new Object[]{ producto.obtenerId(), producto.obtenerNombre(), producto.obtenerPrecio(),
                producto.obtenerMarca(),producto.obtenerCalificacion()};
    }

    @Override
    public int getRowCount() {
        if(this.productos != null) {
            return productos.length;
        } else {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
