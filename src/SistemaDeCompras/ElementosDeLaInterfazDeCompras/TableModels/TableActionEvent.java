package SistemaDeCompras.ElementosDeLaInterfazDeCompras.TableModels;

import Producto.Id;
import SistemaDeCompras.ClasesDelSistema.Carrito;

public interface TableActionEvent {

    /**
     * Implementa un método para añadir el producto al carro
     * */

    public void addToBuy(Carrito carrito, Id id, int cantidad);
}
