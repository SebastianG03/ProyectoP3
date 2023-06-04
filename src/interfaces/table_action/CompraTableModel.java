package interfaces.table_action;

import javax.swing.table.AbstractTableModel;

enum Titles {
    ID,
    NOMBRE,
    ESPECIE,
    PRECIO,
    DESCUENTO,
    CALIFICACION,
    DESCRIPCION,
    BOTON
}

public class CompraTableModel extends AbstractTableModel {
    private static Object[] titles = {
            Titles.ID.toString(), Titles.NOMBRE.toString(), Titles.ESPECIE.toString(),
            Titles.PRECIO.toString(), Titles.DESCUENTO.toString(), Titles.CALIFICACION.toString(),
            Titles.DESCRIPCION.toString(), Titles.BOTON
    };//La última fila es la fila del botón.
    private Object[][] rows;

    public CompraTableModel() {
    }

    @Override
    public int getRowCount() {
        return rows.length;
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
