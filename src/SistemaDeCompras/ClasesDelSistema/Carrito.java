package SistemaDeCompras.ClasesDelSistema;

import Producto.Id;
import Producto.Producto;
import Inventario.Categoria.Categoria;
import Inventario.Inventario;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Carrito {

    private Date date;
    private Map<Producto, Integer> carrito;
    private Categoria categoria;
    private Inventario inventario;
    private Usuario usuario;
    private int size;

    public Carrito(Inventario inventario) {
        this.inventario = inventario;
        carrito = new HashMap<>();
//        this.usuario = null;
        this.size = 0;
    }

    //Agrega el producto a la lista
    public void agregarProducto(Id id, int cantidad) throws Exception {
        Producto producto = categoria.buscar_producto(id);
//                buscarNoNulo(inventario.obtener_categoria_comida().buscar_producto(id),
//                inventario.obtener_categoria_accesorio().buscar_producto(id),
//                inventario.obtener_insumo_medico().buscar_producto(id));

        if(cantidad < producto.obtener_stock()) {
            carrito.put(producto, cantidad);
            ++size;
        } else {
            throw new Exception("Cantidad de stock insuficiente");
        }
    }


//    public void agregarProductoComida(Id id, int cantidad) throws Exception {
//        Producto producto = inventario.obtenerCatComida().buscarProducto(id);
//        if (producto != null) {
//            int stockActual = producto.obtenerStock();
//
//            if (stockActual >= cantidad) {
//                carrito.put(producto, cantidad);
//                ++size;
//            } else {
//                throw new Exception("Cantidad de stock insuficiente");
//            }
//        } else {
//            throw new Exception("Producto no encontrado");
//        }
//    }
//
//    public void agregarAccesorio(Id id, int cantidad) throws Exception {
//        Producto producto = inventario.obtenerCatComida().buscarProducto(id);
//        if (producto != null) {
//            int stockActual = producto.obtenerStock();
//
//            if (stockActual >= cantidad) {
//                carrito.put(producto, cantidad);
//                ++size;
//            } else {
//                throw new Exception("Cantidad de stock insuficiente");
//            }
//        } else {
//            throw new Exception("Producto no encontrado");
//        }
//
//    }
//    public void agregarInsumos(Id id, int cantidad) throws Exception {
//        Producto producto = inventario.obtenerCatInsMedico().buscarProducto(id);
//        if (producto != null) {
//            int stockActual = producto.obtenerStock();
//
//            if (stockActual >= cantidad) {
//                carrito.put(producto, cantidad);
//                ++size;
//            } else {
//                throw new Exception("Cantidad de stock insuficiente");
//            }
//        } else {
//            throw new Exception("Producto no encontrado");
//        }
//
//    }


    public void modificarCantidad(Id id, int cantidadAnterior) throws Exception {
        Producto producto = categoria.buscar_producto(id);
        int cantidadEnCarro = obtenerCantidadEnCarro(id);
        if(cantidadAnterior < producto.obtener_stock()) {
            carrito.replace(producto, cantidadEnCarro, cantidadAnterior);
        } else
            throw new Exception("Cantidad en stock insuficiente.");
    }

    public void modificarCantidad(Producto producto, int cantidad) throws Exception {
        int anteriorCantidad = obtenerCantidadEnCarro(producto);
        if(cantidad < producto.obtener_stock()) {
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
        Producto producto = categoria.buscar_producto(id);
        return (int) carrito.get(producto);
    }

    public int obtenerCantidadEnCarro(Producto producto) {
        return carrito.get(producto);
    }

    public float calcularSubTotal(){
        float subtotal = 0;
        for(Map.Entry<Producto, Integer> e : carrito.entrySet()) {
            subtotal += (e.getValue() * e.getKey().obtener_precio());
        }
        return subtotal;
    }


    public float descuento() {
        float descuento = 0;
        for(Map.Entry<Producto, Integer> e : carrito.entrySet()) {
            descuento += (e.getValue() * e.getKey().obtener_precio() * e.getKey().obtener_descuento());
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
        carrito.clear();
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
            sb.append(String.format(format, producto.obtener_id().toString(),
                    producto.obtener_nombre(),
                    e.getValue(),
                    producto.obtener_precio()));
        }

        return sb.toString();
    }


    public String imprimirFactura() {
        DecimalFormat numberFormat = new DecimalFormat("##.##");
        StringBuilder sb = new StringBuilder();
        date = new Date();
        sb.append("Fecha: ")
                .append(date.toString())
                .append("\n");

        String[] datosFactura = {"Nombre", "Correo","Subtotal", "Descuento", "Impuestos", "Total"};

        String subTotal = numberFormat.format(calcularSubTotal());
        String descuento = numberFormat.format(descuento());
        String IVA = numberFormat.format(calcularIva());
        String total = numberFormat.format(calcularTotal());

        sb.append(
                String.format("|%s|%5s|\n|%s|%5s|\n|%s|%5s|\n|%s|%5s|\n",
                        //datosFactura[0], usuario.getNombre(),
//                        datosFactura[1], usuario.getCorreo(),
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
            sb.append(String.format(format, producto.obtener_id().toString(),
                    producto.obtener_nombre(),
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