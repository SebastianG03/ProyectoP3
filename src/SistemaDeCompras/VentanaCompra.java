package SistemaDeCompras;

import Inventario.Categoria.*;
import Inventario.Inventario;
import Producto.*;;
import SistemaDeCompras.ClasesDelSistema.Carrito;
import SistemaDeCompras.ClasesDelSistema.HistorialCompras;
import SistemaDeCompras.DocumentFilter.FilterFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class VentanaCompra {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JComboBox cboOrdenarFiltros;
    private JButton btoFiltrar;
    private JList listProductosEnCarro;
    private JButton buttonAddToCar;
    private JSpinner spinnerCantidadP;
    private JButton btoSiguiente;
    private JComboBox cboCategorias;
    private JTextField txtBuscarId;
    private JButton btoBuscar;
    private JTable tablaProductos;
    private JComboBox cboEspecies;
    private JButton másInformacionButton;
    private JTextField textFieldID;
    private JTextField textFieldProducto0;
    private JTextField txtIngresarIdCarrito;
    private JButton btnEliminar;
    private JTextArea textAreaFactura;
    private JButton comprarButton;
    private JTextArea textAreaHistorialDeCompras;
    private JList listProductos;
    private JButton eliminarProductoButton;
    private JButton modificarProductoButton;
    private JTabbedPane tabbedPane_General;
    private JPanel panelnicio;
    private JComboBox cboCategoria_Inicio;
    private JButton btnAgregar_Inicio;
    private JButton btnListaProductos_Inicio;
    private JPanel panelAgregarProducto;
    private JTextField txtPrecio_AgregarProducto;
    private JTextField txtDescuento_AgregarProducto;
    private JTextField txtStock_AgregarProducto;
    private JTextArea txtDescripcion_AgregarProducto;
    private JTextField txtNombre_AgregarProducto;
    private JComboBox cboMascota_AgregarProducto;
    private JButton btnRegresar_AgregarProducto;
    private JTabbedPane tabbedPane_AgregarProducto;
    private JComboBox cboMarca_AgregarProductoComida;
    private JComboBox cboFabricante_AgregarProductoComida;
    private JComboBox cboRaza_AgregarProductoComida;
    private JComboBox cboEtapaDeVida_AgregarProductoComida;
    private JComboBox cboSabor_AgregarProductoComida;
    private JComboBox cboContenedor_AgregarProductoComida;
    private JComboBox cboTipo_AgregarProductoComida;
    private JButton btnLimpiar_AgregarProductoComida;
    private JButton btnAgregarProductoComida;
    private JComboBox cboMarca_AgregarProductoAccesorio;
    private JComboBox cboFabricante_AgregarProductoAccesorio;
    private JComboBox cboTipo_AgregarProductoAccesorio;
    private JComboBox cboEtapaDeVida_AgregarProductoAccesorio;
    private JButton btnLimpiar_AgregarProductoAccesorio;
    private JButton btnAgregarProductoAccesorio;
    private JButton btnLimpiar_AgregarProductoInsMedico;
    private JButton btnAgregarProductoInsMedico;
    private JComboBox cboMarca_AgregarProductoInsMedico;
    private JComboBox cboFabricante_AgregarProductoInsMedico;
    private JComboBox cboTipo_AgregarProductoInsMedico;
    private JPanel panelModificarProducto;
    private JTextField txtPrecio_ModificarProducto;
    private JTextField txtDescuento_ModificarProducto;
    private JTextField txtStock_ModificarProducto;
    private JTextArea txtDescripcion_ModificarProducto;
    private JTextField txtNombre_ModificarProducto;
    private JButton btnRegresar_Modificarproducto;
    private JTabbedPane tabbedPane_ModificarProducto;
    private JComboBox cboMarca_ModificarProductoComida;
    private JComboBox cboFabricante_ModificarProductoComida;
    private JComboBox cboRaza_ModificarProductoComida;
    private JComboBox cboEtapaDeVida_ModificarProductoComida;
    private JComboBox cboSabor_ModificarProductoComida;
    private JComboBox cboContenedor_ModificarProductoComida;
    private JComboBox cboTipo_ModificarProductoComida;
    private JButton btnLimpiar_ModificarProductoComida;
    private JButton btnModificarProductoComida;
    private JButton btnEliminar_ModificarProductoComida;
    private JComboBox cboMarca_ModificarProductoAccesorio;
    private JComboBox cboFabricante_ModificarProductoAccesorio;
    private JComboBox cboTipo_ModificarProductoAccesorio;
    private JComboBox cboEtapaDeVida_ModificarProductoAccesorio;
    private JButton btnLimpiar_ModificarProductoAccesorio;
    private JButton btnModificarProductoAccesorio;
    private JButton btnEliminar_ModificarProductoAccesorio;
    private JButton btnLimpiar_ModificarProductoInsMedico;
    private JButton btnModificarProductoInsMedico;
    private JButton btnEliminar_ModificarProductoInsMedico;
    private JComboBox cboMarca_ModificarProductoInsMedico;
    private JComboBox cboFabricante_ModificarProductoInsMedico;
    private JComboBox cboTipo_ModificarProductoInsMedico;
    private JPanel panelListaProductos;
    private JButton btnBuscar_ListaProductos;
    private JTextField txtIdentificador_ListaProductos;
    private JTabbedPane tabbedPane_ListaProductos;
    private JTable tablaDeProductos;
    private JScrollPane jScrollPane2;
    private JTable tablaPAccesorio;
    private JScrollPane jScrollPane3;
    private JTable tablaPInsMedico;
    private JComboBox cboMostrar_ListaProductos;
    private JComboBox cboFiltrar_ListaProductos;
    private JComboBox cboOrdenar_ListaProductos;
    private JButton btnAceptar_ListaProductos;
    private JButton btnRegresar_ListaProductos;
    private DefaultTableModel modeloTabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private Carrito carrito = new Carrito(new Inventario());
    private HistorialCompras historialCompras = new HistorialCompras();
    List<Producto> productosEnCarro = new ArrayList<>();
    private DefaultListModel<String>  listModel = new DefaultListModel<>();
    private Inventario inventario = new Inventario();
    private Categoria categoria;


    public VentanaCompra() {
        //Inicializar
        inicializarTabla();
        spinnerCantidadP.setModel(new SpinnerNumberModel(0, 0, 99, 1));
        actualizarTabla(obtenerTodosLosProductos());


        btoFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filtro = Objects.requireNonNull(cboOrdenarFiltros.getSelectedItem()).toString();
                String categoria = Objects.requireNonNull(cboCategorias.getSelectedItem()).toString();
                String especie = Objects.requireNonNull(cboEspecies.getSelectedItem()).toString();
                inicializarTabla();
                actualizarTabla(filtrar(categoria, especie, filtro));
                txtBuscarId.setText("");
            }
        });

        btoBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String id = txtBuscarId.getText();
                try {
                    Id identificador = new Id(id);
                    inicializarTabla();
                    actualizarTabla(new Object[] {(categoria.buscar_producto(identificador))});
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });
        buttonAddToCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Id identificador = new Id(txtIngresarIdCarrito.getText());
                    if(carrito.size() <= 7) {
                        carrito.agregarProducto(identificador, Integer.parseInt(spinnerCantidadP.getValue().toString()));
                        addToBuyPannel();
                        textAreaFactura.setText(carrito.imprimirFactura());
                    } else JOptionPane.showMessageDialog(null, "El carrito se encuentra lleno\n" +
                            "El número máximo de productos que puede ingresar es 7");

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                listModel.clear(); // Se restablece el contenido del DefaultListModel antes de agregar los nuevos elementos
                listModel.addElement(carrito.getProductos().get(carrito.size() - 1).obtener_nombre() + " Cantidad: " + spinnerCantidadP.getValue().toString());

                listProductosEnCarro.setModel(listModel);
            }


        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Id identificador = new Id(txtIngresarIdCarrito.getText());
                    //carrito.eliminarProductoDelCarrito(identificador);
                    JOptionPane.showMessageDialog(null, "Producto eliminado");
                    textAreaFactura.setText(carrito.imprimirFactura());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        másInformacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textFieldID.getText();
                try {
                    Producto producto = categoria.buscar_producto(new Id(id));
                    String datos;

                    datos = (producto instanceof ProductoAccesorio)? obtenerDatosAccesorios(producto) :
                            (producto instanceof ProductoInsMedico)? obtenerDatosMedicamentos(producto) :
                                    obtenerDatosComida(producto);
                    JOptionPane.showMessageDialog(null, datos);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });


        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historialCompras.agregarHistorialCarrito(carrito);
                carrito.comprar();
                textAreaHistorialDeCompras.setText(historialCompras.imprimirPaquetesNuevos());
                JOptionPane.showMessageDialog(null, "Gracias por su patrocinio!\n" + carrito.imprimirFactura());
            }
        });
    }

    public void inicializarTabla(){
        modeloTabla.setColumnCount(0);
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("NOMBRE");
        modeloTabla.addColumn("PRECIO");
        modeloTabla.addColumn("DESCUENTO");
        modeloTabla.addColumn("CALIFICACIÓN");
        tablaProductos.setModel(modeloTabla);

    }
    public void actualizarTabla(Object[] productos){
        modeloTabla.setRowCount(0);

        for(Object o: productos)
            modeloTabla.addRow(obtenerAtributosProductos(o));
    }

    public Object[] obtenerAtributosProductos(Object o) {
        Producto producto = (Producto) o;
        return new Object[] { producto.obtener_id(), producto.obtener_nombre(), producto.obtener_precio(),
                producto.obtener_descuento(), producto.obtener_calificacion()
        };
    }

    public void addToBuyPannel() {
    }

    public void modificarCantidadEnCarro(int index) throws Exception {
    }

    public void eliminarProductoDeCarro(int index) throws Exception {
        carrito.eliminarProductoDeCarro(productosEnCarro.get(index));
    }

    private void createUIComponents() {
        txtBuscarId = new FilterFormat().filterFormatField(30, "[a-zA-Z0-9\\s]*");
        textFieldID = new FilterFormat().filterFormatField(5, "[0-9]*");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaCompra");
        frame.setContentPane(new VentanaCompra().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public String obtenerDatosAccesorios(Producto producto) {
        String accesorioFormat = "Nombre: %s\n" + "Especie: %s\n" + "Precio: %.2f\n" +
                "Descuento: %.2f\n" + "Marca: %s\n" + "Fabricante: %s\n" +
                "Tipo: %s\n" + "Para: %s\n" + "Calificación: %d\n" +
                "Descripción: %d\n";

        ProductoAccesorio p = (ProductoAccesorio) producto;
        return String.format(accesorioFormat, p.obtener_nombre(), p.obtener_mascota(), p.obtener_precio(),
                p.obtener_descuento(), p.obtener_marca(), p.obtener_fabricante(), p.obtener_tipo(),
                p.obtener_etapa_de_vida(), p.obtener_calificacion(), p.obtener_descripcion());
    }

    public String obtenerDatosMedicamentos(Producto producto) {
        String insMedicoFormat = "Nombre: %s\n" + "Especie: %s\n" + "Precio: %.2f\n" +
                "Descuento: %.2f\n" + "Marca: %s\n" + "Fabricante: %s\n" +
                "Tipo: %s \n" + "Calificación: %d\n" + "Descripción: %d\n";


        ProductoInsMedico p = (ProductoInsMedico) producto;
        return String.format(insMedicoFormat, p.obtener_nombre(), p.obtener_mascota(), p.obtener_precio(),
                p.obtener_descuento(), p.obtener_marca(), p.obtener_fabricante(), p.obtener_tipo(),
                p.obtener_calificacion(), p.obtener_descripcion());
    }

    public String obtenerDatosComida(Producto producto) {
        String comidaFormat = "Nombre: %s\n" + "Especie: %s\n" + "Precio: %.2f\n" +
                "Descuento: %.2f\n" + "Marca: %s\n" + "Fabricante: %s\n" +
                "Raza: %s\n" + "Sabor: %s\n" + "Contenedor: %s\n" + "Para: %s\n" +
                "Tipo: %s \n" + "Calificación: %d\n" + "Descripción: %d\n";


        ProductoComida p = (ProductoComida) producto;
        return String.format(comidaFormat, p.obtener_nombre(), p.obtener_mascota(), p.obtener_precio(),
                p.obtener_descuento(), p.obtener_marca(), p.obtener_fabricante(), p.obtener_raza(), p.obtener_sabor(),
                p.obtener_contenedor(), p.obtener_etapa_de_vida(), p.obtener_calificacion(), p.obtener_descripcion());

    }

    public Object[] filtrar(String categoria, String raza, String filtro) {
        Object[] list;
        if(raza.compareToIgnoreCase("Ninguno") == 0) {
            list = (categoria.compareToIgnoreCase("Comida") == 0)? new Comida[]{inventario.obtenerCategoriaComida()} :
                    (categoria.compareToIgnoreCase("Accesorios") == 0)? new Accesorio[]{inventario.obtenerCategoriaAccesorio()} : new InsMedico[]{inventario.obtenerCategoriaInsMedico()};
        } else {
            list = (categoria.compareToIgnoreCase("Comida") == 0)? inventario.obtenerCategoriaComida().filtrar_por_mascota(raza) :
                    (categoria.compareToIgnoreCase("Accesorios") == 0)? inventario.obtenerCategoriaAccesorio().filtrar_por_mascota(raza) :
                            inventario.obtenerCategoriaInsMedico().filtrar_por_mascota(raza);
        }

        ordenarTodosLosProductos(list, filtro);

        return list;
    }

    private void ordenarTodosLosProductos(Object[] productos, String filtro) { //Se puede añadir la categoria que desea buscar
        if(filtro.compareTo("Precio") == 0) {
            ordenarProductosPorPrecio(productos);
        } else if(filtro.compareTo("Stock") == 0) {
            ordenarProductosPorStock(productos);
        } else if(filtro.compareTo("Unidades Vendidas") == 0) {
            ordenarProductosPorUnidadesVendidas(productos);
        } else {
            ordenarProductosPorCalificacion(productos);
        }
    }

    private void ordenarProductosPorPrecio(Object[] productos) {
        if (productos instanceof ProductoAccesorio[])
            inventario.obtenerCategoriaAccesorio().ordenar_por_precio(productos);
        else if (productos instanceof  ProductoComida[])
            inventario.obtenerCategoriaComida().ordenar_por_precio(productos);
        else
            inventario.obtenerCategoriaInsMedico().ordenar_por_precio(productos);
    }

    private void ordenarProductosPorStock(Object[] productos) {
        if(productos instanceof  ProductoAccesorio[])
            inventario.obtenerCategoriaAccesorio().ordenar_por_stock(productos);
        else if (productos instanceof  ProductoComida[])
            inventario.obtenerCategoriaComida().ordenar_por_stock(productos);
        else
            inventario.obtenerCategoriaInsMedico().ordenar_por_stock(productos);
    }

    private void ordenarProductosPorUnidadesVendidas(Object[] productos) {
        if(productos instanceof  ProductoAccesorio[])
            inventario.obtenerCategoriaAccesorio().ordenar_por_unidades_vendidas(productos);
        else if(productos instanceof  ProductoComida[])
            inventario.obtenerCategoriaComida().ordenar_por_unidades_vendidas(productos);
        else
            inventario.obtenerCategoriaInsMedico().ordenar_por_unidades_vendidas(productos);
    }

    private void ordenarProductosPorCalificacion(Object[] productos) {
        if(productos instanceof ProductoAccesorio[])
            inventario.obtenerCategoriaAccesorio().ordenar_por_calificacion(productos);
        else if(productos instanceof  ProductoComida[])
            inventario.obtenerCategoriaComida().ordenar_por_calificacion(productos);
        else
            inventario.obtenerCategoriaInsMedico().ordenar_por_calificacion(productos);
    }


    public Object[] obtenerTodosLosProductos() {
        List<Object> list = new ArrayList<>();
        list.addAll(List.of(inventario.obtenerCategoriaComida()));
        list.addAll(List.of(inventario.obtenerCategoriaAccesorio()));
        list.addAll(List.of(inventario.obtenerCategoriaInsMedico()));
        return list.toArray();
    }
}
