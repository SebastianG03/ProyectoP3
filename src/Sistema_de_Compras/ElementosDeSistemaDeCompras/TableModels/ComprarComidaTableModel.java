package Sistema_de_Compras.ElementosDeSistemaDeCompras.TableModels;

import Inventario.Inventario;
import Producto.ProductoComida;
import Sistema_de_Compras.ElementosDeSistemaDeCompras.EnviarACarritoButton.PanelAction;

import javax.swing.table.DefaultTableModel;


public class ComprarComidaTableModel extends DefaultTableModel {
    private static final String[] titles = {
            "Id", "Nombre", "Sabor", "Especie", "Precio", "Descuento", "Marca",
            "Tipo", "Calificación", "Descripcion", "Añadir a carrito"
    };//La última fila es la fila del botón.

    private Object[] productos;
    private final Inventario inventario = new Inventario();
    private String filtro;

    public ComprarComidaTableModel(ProductoComida producto) {
        super(0, titles.length);

        this.productos = inventario.obtenerCatComida().obtenerProductos();
        addColumns();
        this.filtro = "Ninguno";

    }

    private void addColumns() {
        for (String str : titles) {
            super.addColumn(str);
        }
    }

    public void generateRows() {
        for (Object obj : productos) {
            super.addRow(obtenerAtributosProducto((ProductoComida) obj));
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
        *Los atributos se obtendrán en el siguiente orden:  "Id", "Nombre", "Sabor",
        *  "Especie", "Precio", "Descuento", "Marca",
        * "Tipo", "Calificación", "Descripcion"
        * */
        return new Object[]{producto.obtenerId().toString(), producto.obtenerNombre(),
                producto.obtenerSabor(), producto.obtenerEspecie(), producto.obtenerPrecio(),
                producto.obtenerDescuento(), producto.obtenerMarca(), producto.obtenerTipo(),
                producto.obtenerCalificacion(), producto.obtenerDescripcion(), new PanelAction()};
    }

    @Override
    public int getRowCount() {
        return productos.length;
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
