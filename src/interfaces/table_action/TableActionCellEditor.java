package interfaces.table_action;

import javax.swing.*;
public class TableActionCellEditor extends DefaultCellEditor {

    private TableActionEvent event;

    public TableActionCellEditor(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    //TODO Aplicar la creación del botón dentro de la celda
    /*
    @Override
    public Component getTableCellEditorComponent() {
    }
    */
}
