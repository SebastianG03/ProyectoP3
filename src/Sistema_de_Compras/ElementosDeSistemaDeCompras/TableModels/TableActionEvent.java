package Sistema_de_Compras.ElementosDeSistemaDeCompras.TableModels;

import Producto.Id;
import Sistema_de_Compras.Carrito;

public interface TableActionEvent {

    /**
     * Implementa un método para añadir el producto al carro
     * */

    public void addToBuy(Carrito carrito, Id id, int cantidad);
}
