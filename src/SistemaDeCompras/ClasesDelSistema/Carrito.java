package SistemaDeCompras.ClasesDelSistema;

import Inventario.Categoria.*;
import Inventario.Inventario;
import Producto.Id;
import Producto.Producto;

import java.text.DecimalFormat;
import java.util.*;

public class Carrito {

    public Date date;
    public Map<Producto, Integer> carrito;
    public Categoria categoria;
    private Inventario inventario;
    private Usuario usuario;
    private int size;
    public Carrito(Inventario inventario) {
        this.inventario = inventario;
        carrito = new HashMap<>();
        this.usuario = null;
        this.size = 0;
    }

    //Agrega el producto a la lista
    public void agregarProductoComida(Id id, int cantidad) throws Exception {
        Producto producto = inventario.obtenerCatComida().buscarProducto(id);
        if (producto != null) {
            int stockActual = producto.obtenerStock();

            if (stockActual >= cantidad) {
                carrito.put(producto, cantidad);
                ++size;
            } else {
                throw new Exception("Cantidad de stock insuficiente");
            }
        } else {
            throw new Exception("Producto no encontrado");
        }
    }

    public void agregarAccesorio(Id id, int cantidad) throws Exception {
        Producto producto = inventario.obtenerCatComida().buscarProducto(id);
        if (producto != null) {
            int stockActual = producto.obtenerStock();

            if (stockActual >= cantidad) {
                carrito.put(producto, cantidad);
                ++size;
            } else {
                throw new Exception("Cantidad de stock insuficiente");
            }
        } else {
            throw new Exception("Producto no encontrado");
        }

    }
    public void agregarInsumos(Id id, int cantidad) throws Exception {
        Producto producto = inventario.obtenerCatInsMedico().buscarProducto(id);
        if (producto != null) {
            int stockActual = producto.obtenerStock();

            if (stockActual >= cantidad) {
                carrito.put(producto, cantidad);
                ++size;
            } else {
                throw new Exception("Cantidad de stock insuficiente");
            }
        } else {
            throw new Exception("Producto no encontrado");
        }

    }


    public void modificarCantidad(Id id, int cantidad) throws Exception {
        Producto producto = categoria.buscarProducto(id);
        int anteriorCantidad = obtenerCantidadEnCarro(id);
        if(cantidad < producto.obtenerStock()) {
            carrito.replace(producto, anteriorCantidad, cantidad);
        } else
            throw new Exception("Cantidad en stock insuficiente.");
    }

    public void modificarCantidad(Producto producto, int cantidad) throws Exception {
        int anteriorCantidad = obtenerCantidadEnCarro(producto);
        if(cantidad < producto.obtenerStock()) {
            carrito.replace(producto, anteriorCantidad, cantidad);
        } else
            throw new Exception("Cantidad en stock insuficiente.");
    }

    public void eliminarProductoDeCarro(Producto producto) throws Exception {
        if(getProductos().contains(producto)) {
            carrito.remove(producto);
            --size;
        } else throw new Exception("Producto no encontrado");
    }

    public int obtenerCantidadEnCarro(Id id) throws Exception {
        Producto producto = categoria.buscarProducto(id);
        return (int) carrito.get(producto);
    }

    public int obtenerCantidadEnCarro(Producto producto) {
        return carrito.get(producto);
    }


    public float calcularSubTotal(){
        float subtotal = 0;
        for(Map.Entry<Producto, Integer> e : carrito.entrySet()) {
            subtotal += (e.getValue() * e.getKey().obtenerPrecio());
        }
        return subtotal;
    }


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

    public void comprar(){
        this.date = new Date();
        for(Map.Entry<Producto, Integer> e : carrito.entrySet()) {
            inventario.solicitudCompra(e.getKey(), e.getValue());
        }
    }

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

    public Map<Producto, Integer> getCarroDeCompras() {
        return carrito;
    }



    public List<Producto> getProductos() {
        List<Producto> productos = new ArrayList<>();
        for(Map.Entry<Producto, Integer> e : carrito.entrySet()) {
            productos.add(e.getKey());
        }
        return productos;
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
                "\n\nCosto total: " + numberFormat.format(calcularTotal()) + "\n";


    }

    public int size() {
        return size;
    }
}