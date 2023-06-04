import Inventario.Categoria.Categoria;
import Producto.Id;
import Producto.Producto;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Carrito {

    Map<Producto, Integer> carrito;
    Categoria categoria;

    public Carrito() {
        carrito = new HashMap<>();
    }

    //Agrega el producto a la lista
    public void agregarProducto(Id id, int cantidad) throws Exception {
        Producto producto = categoria.buscarProducto(id);
        if(carrito.containsKey(producto) && (producto.obtenerStock() - cantidad) >= 1) {
            producto.establecerStock(cantidad);
            carrito.put(producto, cantidad);
        } else throw new Exception("Cantidad de stock insuficiente");
    }

    //TODO realizar comprobaciones
    public void modificarCantidad(Id id, int cantidad) throws Exception {
        Producto producto = categoria.buscarProducto(id);
        int anteriorCantidad = obtenerCantidadEnCarro(id);
        if(cantidad < producto.obtenerStock()) {
            carrito.replace(producto, anteriorCantidad, cantidad);
        } else
            throw new Exception("Cantidad en stock insuficiente.");
    }
    public void eliminarProductoDelCarrito(Id id) throws Exception {
        Producto producto = categoria.buscarProducto(id);
        int cantidad = obtenerCantidadEnCarro(id);
        carrito.remove(producto, cantidad);
    }


    public int obtenerCantidadEnCarro(Id id) throws Exception {
        Producto producto = categoria.buscarProducto(id);
        return (int) carrito.get(producto);
    }


    public float calcularSubTotal(){
        float subtotal = 0;
        for(Map.Entry<Producto, Integer> e : carrito.entrySet()) {
            subtotal += (e.getValue() * e.getKey().obtenerPrecio());
        }
        return subtotal;
    }

    private float descuento() {
        boolean result = false;
        Random random = new Random();
        int numeroAleatorio = random.nextInt(100) + 1;

        if (numeroAleatorio == 1) {
            return (float) (calcularSubTotal() * 0.10);
        }
        return 0;
    }

    public float calcularIva(){
        return (float) Math.abs((calcularSubTotal()-descuento()) * 0.12);

    }
    public float calcularTotal(){
        return (calcularSubTotal() - descuento() - calcularIva());
    }

    //TODO Falta acabar el método para registrar la compra
    public void comprar(){
        for(Map.Entry<Producto, Integer> e : carrito.entrySet()) {
            //categoria.restarStock(e.getKey().obtenerId(), e.getValue());
        }
    }

    //Primera forma de imprimir el carrito
    public String toString() {
        Producto producto;

        StringBuilder sb = new StringBuilder();
        String titles = String.format("|%s|%3s|%3s|%3s|\n", "Id", "Nombre", "Categoría", "Precio Unitario");
        String format = "|%s|%3s|%3s|%3s|%3s|\n";

        sb.append(titles);

        //Ingresa los datos del carrito al StringBuilder
        for(Map.Entry<Producto, Integer> e : carrito.entrySet()) {
            producto = e.getKey();
            sb.append(String.format(format, producto.obtenerId().toString(),
                    producto.obtenerNombre(),
                    e.getValue(),
                    producto.obtenerPrecio()));
        }

        return sb.toString();
    }

    public String imprimirFactura() {
        DecimalFormat numberFormat = new DecimalFormat("#0.00");
        StringBuilder sb = new StringBuilder();
        String[] datosFactura = {"Subtotal", "Descuento", "Impuestos", "Total"};

        String subTotal = numberFormat.format(calcularSubTotal());
        String descuento = numberFormat.format(descuento());
        String IVA = numberFormat.format(calcularIva());
        String total = numberFormat.format(calcularTotal());

        sb.append(
                String.format("|%s|%5s|\n|%s|%5s|\n|%s|%5s|\n|%s|%5s|\n", datosFactura[0], subTotal,
                        datosFactura[1], descuento,
                        datosFactura[2], IVA,
                        datosFactura[3], total)
        );

        return sb.toString();


    }
}