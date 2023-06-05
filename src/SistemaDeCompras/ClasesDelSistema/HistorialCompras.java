package SistemaDeCompras.ClasesDelSistema;
import java.util.ArrayList;
import java.util.List;

public class HistorialCompras {
    List<Carrito> paquetesNuevos;
    List<Carrito> paquetesEnviados;
    public HistorialCompras() {
        paquetesNuevos = new ArrayList<>();
        paquetesEnviados = new ArrayList<>();
    }
    public List<Carrito> getPaquetesNuevos() {
        return paquetesNuevos;
    }
    public List<Carrito> getPaquetesEnviados() {
        return paquetesEnviados;
    }
    public void agregarHistorialCarrito(Carrito carrito){
        paquetesNuevos.add(carrito);
    }
    public void agregarHistorialEnviados(Carrito carrito){
        paquetesEnviados.add(carrito);
    }


}
