import Inventario.Inventario;
import Producto.Producto;
import SistemaDeCompras.ClasesDelSistema.Carrito;
import SistemaDeCompras.ClasesDelSistema.Filtros;
import SistemaDeCompras.ClasesDelSistema.HistorialCompras;
import SistemaDeCompras.DocumentFilter.FilterFormat;
import Producto.*;
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
    private JButton eliminarButton;
    private JButton eliminarButton1;
    private JButton eliminarButton2;
    private JButton eliminarButton3;
    private JButton eliminarButton4;
    private JButton eliminarButton6;
    private JButton eliminarButton5;
    private JButton modificarButton0;
    private JSpinner spinnerCantidadProducto0;
    private JButton modificarButton1;
    private JButton modificarButton2;
    private JButton modificarButton3;
    private JButton modificarButton4;
    private JButton modificarButton5;
    private JButton modificarButton6;
    private JSpinner spinnerCantidadProducto1;
    private JSpinner spinnerCantidadProducto2;
    private JSpinner spinnerCantidadProducto3;
    private JSpinner spinnerCantidadProducto4;
    private JSpinner spinnerCantidadProducto5;
    private JSpinner spinnerCantidadProducto6;
    private JTextField textFieldProducto1;
    private JTextField textFieldProducto2;
    private JTextField textFieldProducto3;
    private JTextField textFieldProducto4;
    private JTextField textFieldProducto5;
    private JTextField textFieldProducto6;
    private JTextField textFieldProductoCantidad0;
    private JTextField textFieldProductoCantidad1;
    private JTextField textFieldProductoCantidad2;
    private JTextField textFieldProductoCantidad3;
    private JTextField textFieldProductoCantidad4;
    private JTextField textFieldProductoCantidad5;
    private JTextField textFieldProductoCantidad6;
    private JComboBox cboEspecies;
    private JButton másInformacionButton;
    private JTextField textFieldID;
    private JTextField textFieldProducto0;
    private JTextField txtIngresarIdCarrito;
    private JButton btnEliminar;
    private JTextArea textAreaFactura;
    private JButton comprarButton;
    private JTextArea textAreaHistorialDeCompras;
    private DefaultTableModel modeloTabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private Carrito carrito = new Carrito(new Inventario());
    private Filtros filtroTable = new Filtros();
    private HistorialCompras historialCompras = new HistorialCompras();
    List<Producto> productosEnCarro = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private List<JTextField> textFieldProductosCompras = Arrays.asList(textFieldProducto0, textFieldProducto1, textFieldProducto2,
            textFieldProducto3, textFieldProducto4, textFieldProducto5, textFieldProducto6);
    private List<JTextField> textFieldCantidadPorProducto = Arrays.asList(textFieldProductoCantidad0, textFieldProductoCantidad1,
            textFieldProductoCantidad2, textFieldProductoCantidad3, textFieldProductoCantidad4,
            textFieldProductoCantidad5, textFieldProductoCantidad6);
    private List<JSpinner> spinnersModificarCantidad = Arrays.asList(spinnerCantidadProducto0, spinnerCantidadProducto1,
            spinnerCantidadProducto2, spinnerCantidadProducto3, spinnerCantidadProducto4, spinnerCantidadProducto5, spinnerCantidadProducto6);


    public VentanaCompra() {
        //Inicializar
        inicializarTabla();
        spinnerCantidadProducto0.setModel(new SpinnerNumberModel(0, 0, 99, 1));
        spinnerCantidadProducto1.setModel(new SpinnerNumberModel(0, 0, 99, 1));
        spinnerCantidadProducto2.setModel(new SpinnerNumberModel(0, 0, 99, 1));
        spinnerCantidadProducto3.setModel(new SpinnerNumberModel(0, 0, 99, 1));
        spinnerCantidadProducto4.setModel(new SpinnerNumberModel(0, 0, 99, 1));
        spinnerCantidadProducto5.setModel(new SpinnerNumberModel(0, 0, 99, 1));
        spinnerCantidadProducto6.setModel(new SpinnerNumberModel(0, 0, 99, 1));
        spinnerCantidadP.setModel(new SpinnerNumberModel(0, 0, 99, 1));
        actualizarTabla(filtroTable.obtenerTodosLosProductos());


        btoFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filtro = Objects.requireNonNull(cboOrdenarFiltros.getSelectedItem()).toString();
                String categoria = Objects.requireNonNull(cboCategorias.getSelectedItem()).toString();
                String especie = Objects.requireNonNull(cboEspecies.getSelectedItem()).toString();
                inicializarTabla();
                actualizarTabla(filtroTable.filtrar(categoria, especie, filtro));
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
                    actualizarTabla(new Object[] {filtroTable.buscarProductoPorId(identificador)});
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
                listModel.addElement(carrito.getProductos().get(carrito.size() - 1).obtenerNombre() + " Cantidad: " + spinnerCantidadP.getValue().toString());

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
        modificarButton0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modificarCantidadEnCarro(0);
                    textAreaFactura.setText(carrito.imprimirFactura());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        modificarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modificarCantidadEnCarro(1);
                    textAreaFactura.setText(carrito.imprimirFactura());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        modificarButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modificarCantidadEnCarro(2);
                    textAreaFactura.setText(carrito.imprimirFactura());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        modificarButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modificarCantidadEnCarro(3);
                    textAreaFactura.setText(carrito.imprimirFactura());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        modificarButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modificarCantidadEnCarro(4);
                    textAreaFactura.setText(carrito.imprimirFactura());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        modificarButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modificarCantidadEnCarro(5);
                    textAreaFactura.setText(carrito.imprimirFactura());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        modificarButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modificarCantidadEnCarro(6);
                    textAreaFactura.setText(carrito.imprimirFactura());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminarProductoDeCarro(0);
                    textAreaFactura.setText(carrito.imprimirFactura());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        eliminarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminarProductoDeCarro(1);
                    textAreaFactura.setText(carrito.imprimirFactura());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        eliminarButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminarProductoDeCarro(2);
                    textAreaFactura.setText(carrito.imprimirFactura());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        eliminarButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminarProductoDeCarro(3);
                    textAreaFactura.setText(carrito.imprimirFactura());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        eliminarButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminarProductoDeCarro(4);
                    textAreaFactura.setText(carrito.imprimirFactura());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        eliminarButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminarProductoDeCarro(5);
                    textAreaFactura.setText(carrito.imprimirFactura());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        eliminarButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminarProductoDeCarro(6);
                    textAreaFactura.setText(carrito.imprimirFactura());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });


        másInformacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textFieldID.getText();
                try {
                    Producto producto = filtroTable.buscarProductoPorId(new Id(id));
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
        return new Object[] { producto.obtenerId(), producto.obtenerNombre(), producto.obtenerPrecio(),
                producto.obtenerDescuento(), producto.obtenerCalificacion()
        };
    }

    public void addToBuyPannel() {
        List<Producto> productosEnCarro = carrito.getProductos();

        for(int i = 0; i < productosEnCarro.size(); ++i) {
            textFieldProductosCompras.get(i).setText(productosEnCarro.get(i).obtenerNombre());
            textFieldCantidadPorProducto.get(i).setText(Integer.toString(carrito.obtenerCantidadEnCarro(productosEnCarro.get(i))));
        }
    }

    public void modificarCantidadEnCarro(int index) throws Exception {
        int cantidad = Integer.parseInt(spinnersModificarCantidad.get(index).getValue().toString());
        textFieldCantidadPorProducto.get(index).setText(Integer.toString(cantidad));
        carrito.modificarCantidad(productosEnCarro.get(index), cantidad);
    }

    public void eliminarProductoDeCarro(int index) throws Exception {
        carrito.eliminarProductoDeCarro(productosEnCarro.get(index));
    }

    private void createUIComponents() {
        txtBuscarId = new FilterFormat().filterFormatField(30, "[a-zA-Z0-9\\s]*");
        textFieldID = new FilterFormat().filterFormatField(5, "[0-9]*");
    }

    public String obtenerDatosAccesorios(Producto producto) {
        String accesorioFormat = "Nombre: %s\n" + "Especie: %s\n" + "Precio: %.2f\n" +
                "Descuento: %.2f\n" + "Marca: %s\n" + "Fabricante: %s\n" +
                "Tipo: %s\n" + "Para: %s\n" + "Calificación: %d\n" +
                "Descripción: %d\n";

        ProductoAccesorio p = (ProductoAccesorio) producto;
        return String.format(accesorioFormat, p.obtenerNombre(), p.obtenerEspecie(), p.obtenerPrecio(),
                p.obtenerDescuento(), p.obtenerMarca(), p.obtenerFabricante(), p.obtenerTipo(),
                p.obtenerEtapaDeVida(), p.obtenerCalificacion(), p.obtenerDescripcion());
    }

    public String obtenerDatosMedicamentos(Producto producto) {
        String insMedicoFormat = "Nombre: %s\n" + "Especie: %s\n" + "Precio: %.2f\n" +
                "Descuento: %.2f\n" + "Marca: %s\n" + "Fabricante: %s\n" +
                "Tipo: %s \n" + "Calificación: %d\n" + "Descripción: %d\n";


        ProductoInsMedico p = (ProductoInsMedico) producto;
        return String.format(insMedicoFormat, p.obtenerNombre(), p.obtenerEspecie(), p.obtenerPrecio(),
                p.obtenerDescuento(), p.obtenerMarca(), p.obtenerFabricante(), p.obtenerTipo(),
                p.obtenerCalificacion(), p.obtenerDescripcion());
    }

    public String obtenerDatosComida(Producto producto) {
        String comidaFormat = "Nombre: %s\n" + "Especie: %s\n" + "Precio: %.2f\n" +
                "Descuento: %.2f\n" + "Marca: %s\n" + "Fabricante: %s\n" +
                "Raza: %s\n" + "Sabor: %s\n" + "Contenedor: %s\n" + "Para: %s\n" +
                "Tipo: %s \n" + "Calificación: %d\n" + "Descripción: %d\n";


        ProductoComida p = (ProductoComida) producto;
        return String.format(comidaFormat, p.obtenerNombre(), p.obtenerEspecie(), p.obtenerPrecio(),
                p.obtenerDescuento(), p.obtenerMarca(), p.obtenerFabricante(), p.obtenerRaza(), p.obtenerSabor(),
                p.obtenerContenedor(), p.obtenerEtapaDeVida(), p.obtenerCalificacion(), p.obtenerDescripcion());

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaCompra");
        frame.setContentPane(new VentanaCompra().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
