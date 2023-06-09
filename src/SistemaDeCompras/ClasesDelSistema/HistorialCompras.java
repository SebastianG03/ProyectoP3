package SistemaDeCompras.ClasesDelSistema;
import Producto.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public String imprimirPaquetesNuevos() {
        StringBuilder sb = new StringBuilder();

        paquetesNuevos.stream().map(x -> sb.append(x.toString()));
        return sb.toString();
    }
    public String imprimirPaquetesEnviados() {
        StringBuilder sb = new StringBuilder();
        paquetesEnviados.stream().map(x -> sb.append(x.toString()));
        return sb.toString();
    }



}
