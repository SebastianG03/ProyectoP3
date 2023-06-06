package SistemaDeCompras.ClasesDelSistema;

import Inventario.Categoria.Categoria;
import Inventario.Inventario;
import Producto.Id;
import Producto.Producto;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Carrito {

    public Date date;
    public Map<Producto, Integer> carrito;
    public Categoria categoria;
    private Inventario inventario;
    private Usuario usuario;
    public Carrito(Inventario inventario, Usuario usuario) {
        this.inventario = inventario;
        carrito = new HashMap<>();
        this.usuario = usuario;
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

    //TODO implementar el descuento del producto
    public float descuento() {
        float descuento = 0;
        for(Map.Entry<Producto, Integer> e : carrito.entrySet()) {
            descuento += (e.getValue() * e.getKey().obtenerPrecio() * e.getKey().obtenerDescuento());
        }
        return descuento;
    }

    public float calcularIva(){
        return (float) Math.abs((calcularSubTotal()-descuento()) * 0.12);

    }
    public float calcularTotal(){
        return (calcularSubTotal() - descuento() - calcularIva());
    }

    //TODO Falta acabar el método para registrar la compra
    public void comprar(){
        this.date = new Date();
        for(Map.Entry<Producto, Integer> e : carrito.entrySet()) {
            inventario.solicitudCompra(e.getKey(), e.getValue());
        }
    }

    //Primera forma de imprimir el carrito
    public String toString() {
        Producto producto;

        StringBuilder sb = new StringBuilder();
        String titles = String.format("|%s|%3s|%3s|%3s|\n", "Id", "Nombre", "Cantidad", "Precio Unitario");
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

    //TODO realizar un método para imprimir el precio final con los productos comprados, la fecha y enviar a una lista.

    public String imprimirFactura() {
        DecimalFormat numberFormat = new DecimalFormat("#0.00");
        StringBuilder sb = new StringBuilder();
        sb.append("Fecha: ")
                .append(date.toString())
                .append("\n");

        String[] datosFactura = {"Nombre", "Correo","Subtotal", "Descuento", "Impuestos", "Total"};

        String subTotal = numberFormat.format(calcularSubTotal());
        String descuento = numberFormat.format(descuento());
        String IVA = numberFormat.format(calcularIva());
        String total = numberFormat.format(calcularTotal());

        sb.append(
                String.format("|%s|%5s|\n|%s|%5s|\n|%s|%5s|\n|%s|%5s|\n|%s|%5s|\n|%s|%5s|\n",
                        datosFactura[0], usuario.getNombre(),
                        datosFactura[1], usuario.getCorreo(),
                        datosFactura[2], subTotal,
                        datosFactura[3], descuento,
                        datosFactura[4], IVA,
                        datosFactura[5], total)
        );

        return sb.toString();


    }

    public String imprimirDatosGenerales() {
        return "Pedido de:" + usuario.getNombre() +
                "\nCorreo:" + usuario.getCorreo() +
                "\nFecha:" + date.toString();
    }

    public String imprimirDatosEspecificos() {
        Producto producto;
        DecimalFormat numberFormat = new DecimalFormat("#0.00");

        StringBuilder sb = new StringBuilder();
        String titles = String.format("|%s|%3s|%3s|\n", "Id", "Nombre", "Cantidad");
        String format = "|%s|%3s|%3s|\n";

        sb.append(titles);

        //Ingresa los datos del carrito al StringBuilder
        for(Map.Entry<Producto, Integer> e : carrito.entrySet()) {
            producto = e.getKey();
            sb.append(String.format(format, producto.obtenerId().toString(),
                    producto.obtenerNombre(),
                    e.getValue()));
        }

        return "Pedido de:" + usuario.getNombre() +
                "\nCorreo:" + usuario.getCorreo() +
                "\nFecha:" + date.toString() +
                "\n\nContenido del pedido:" + sb +
                "\n\nCosto total: " + numberFormat.format(calcularTotal());
    }
}