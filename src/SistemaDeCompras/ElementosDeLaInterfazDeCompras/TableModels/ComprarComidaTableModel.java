package SistemaDeCompras.ElementosDeLaInterfazDeCompras.TableModels;

import Inventario.Inventario;
import Producto.ProductoComida;

import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;


public class ComprarComidaTableModel extends DefaultTableModel {
    private static final String[] titles = {
            "Id", "Nombre",  "Precio", "Marca"
            , "Calificación", "Opciones"
    };//La última fila es la fila del botón, la cantidad, y obtener más información del producto.

    private Inventario inventario = new Inventario();;
    private Object[] productos;
    private String filtro;
    private Object[] rowData = new Object[getColumnCount()];

    public ComprarComidaTableModel() {

        this.productos = inventario.obtenerCatComida().obtenerProductos();
        generateColumns();

        generateRows();
        this.filtro = "Ninguno";

    }

    public void generateColumns() {
        setColumnCount(0);
        for (String str : titles) {
            addColumn(str);
        }
    }

    public void generateRows() {
        setRowCount(0);
        for(int i = 0; i < titles.length; ++i) {
            setRowCount(getRowCount() - 2);
            rowData[i] = "Iteracion" + i;

        }
        addRow(rowData);
//        for (Object obj : productos) {
//            addRow(obtenerAtributosProducto((ProductoComida) obj));
//        }
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

        for(int i = 0; i < titles.length; ++i) {
            rowData[i] = "Iteracion" + i;
        }
//        rowData[0] = producto.obtenerId().toString();
//        rowData[1] = producto.obtenerNombre();
//        rowData[2] = producto.obtenerPrecio();
//        rowData[3] = producto.obtenerMarca();
//        rowData[4] = producto.obtenerCalificacion();
//        rowData[5] = "Here is a panel";

        return rowData;
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
        return titles.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public void newRowsAdded(TableModelEvent e) {
        super.newRowsAdded(e);
    }
}
