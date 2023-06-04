package InterfacesCarrito.table_action;

public interface TableActionEvent {

    /**
     * Contiene cuatro métodos, de los cuales se van
     * a utilizar en la interfaz onDelete para eliminar
     * el producto del carro de compras
     * y onAdd para añadir el producto al carro.
     * */
    public void onEdit(int row);
    public void onDelete(int row);
    public void onView(int row);
    public void onAdd(int row);
}
