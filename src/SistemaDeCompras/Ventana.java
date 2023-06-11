package SistemaDeCompras;

import Inventario.Categoria.*;
import Producto.Id;
import Producto.ProductoComida;
import Producto.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class Ventana {
    private JPanel Principal;
    private JTabbedPane tabbedPane1;
    private JComboBox cboAgregarEspeciePComida;
    private JTextField txtAgregarNombrePComida;
    private JTextField txtAgregarPrecioPComida;
    private JTextField txtAgregarDescuentoPComida;
    private JSpinner spnAgregarStockPComida;
    private JTextArea txtAgregarDescripcionPComida;
    private JComboBox cboAgregarMarcaPComida;
    private JComboBox cboAgregarFabricantePComida;
    private JComboBox cboAgregarRazaPComida;
    private JComboBox cboAgregarSaborPComida;
    private JComboBox cboAgregarContenedorPComiad;
    private JComboBox cboAgregarTipoPComida;
    private JComboBox cboAgregarEtapaDeVidaPComida;
    private JButton btnAgregarPComida;
    private JButton btnLimpiarPComida1;
    private JTable tablaPComida;
    private JComboBox cboFiltroPComida;
    private JComboBox cboOrdenPComida;
    private JButton btnFiltroPComida;
    private JTextField txtIdModificarPComida;
    private JButton btnBuscarMPComida;
    private JTextField txtModificarNombrePComida;
    private JTextField txtModificarPrecioPComida;
    private JTextField txtModificarDescuentoPComida;
    private JSpinner spnModificarStockPComida;
    private JTextArea txtModificarDescripcionPComida;
    private JComboBox cboModificarMarcaPComida;
    private JComboBox cboModificarFabricantePComida;
    private JComboBox cboModificarRazaPComida;
    private JComboBox cboModificarEtapaDeVidaPComida;
    private JComboBox cboModificarSaborPComida;
    private JComboBox cboModificarContenedorPComida;
    private JComboBox cboModificarTipoPComida;
    private JButton btnLimpiarPComida2;
    private JButton btnModificarPComida;
    private JTextField txtEliminarPComida;
    private JButton btnEliminarPComida;
    private JButton btnCancelarModificarPComida;
    private JComboBox cboMostrarPComida;
    private JTextField txtBuscarPComida;
    private JButton btnBuscarPComida;
    private JButton btnCancelarBusquedaPComida;
    private JButton btnConsultarPComida;
    public static Inventario inventario = new Inventario();
    private DefaultTableModel modeloTablaPComida = new DefaultTableModel();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().Principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(780,650);
        frame.setVisible(true);
    }

    public Ventana() {
        //INICIALIZAR - COMIDA
        inicializarTablaPComida();
        inicializarAgregarPComida();
        inicializarComponentesModificarPComida();
        tablaPComida.setEnabled(false);
        cboFiltroPComida.addItem("Ninguno");
        for(String s: inventario.obtenerCatComida().obtenerEspecies()){
            cboFiltroPComida.addItem(s);
        }
        actualizarTablaPComida(filtrarProductosComida());
        btnCancelarBusquedaPComida.setEnabled(false);
        btnConsultarPComida.setEnabled(false);

        //BOTONES - COMIDA
            //AGREGAR PRODUCTO - COMIDA
        btnAgregarPComida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //CARACTERISTICAS GENERALES
            String categoria = "Comida";
            String especie = cboAgregarEspeciePComida.getSelectedItem().toString();
            String nombre = txtAgregarNombrePComida.getText();
            String precio = txtAgregarPrecioPComida.getText();
            String descuento = txtAgregarDescuentoPComida.getText();
            String stock = spnAgregarStockPComida.getValue().toString();
            String descripcion = txtAgregarDescripcionPComida.getText();
            //CARACTERISTICAS ESPECIFICAS
            String marca = cboAgregarMarcaPComida.getSelectedItem().toString();
            String fabricante = cboAgregarFabricantePComida.getSelectedItem().toString();
            String raza = cboAgregarRazaPComida.getSelectedItem().toString();
            String sabor = cboAgregarSaborPComida.getSelectedItem().toString();
            String contenedor = cboAgregarContenedorPComiad.getSelectedItem().toString();
            String etapaDeVida = cboAgregarEtapaDeVidaPComida.getSelectedItem().toString();
            String tipo = cboAgregarTipoPComida.getSelectedItem().toString();
            if(especie.isEmpty() || nombre.isEmpty() || precio.isEmpty() || descuento.isEmpty() || stock.isEmpty() ||
            descripcion.isEmpty() || marca.isEmpty() || fabricante.isEmpty() || raza.isEmpty() || sabor.isEmpty() ||
            contenedor.isEmpty() || etapaDeVida.isEmpty() || tipo.isEmpty()){
                JOptionPane.showMessageDialog(null,"Campos Vacíos",
                        "Error",JOptionPane.ERROR_MESSAGE);
            }else{
                try{
                    double precioDouble = Double.parseDouble(precio);
                    double descuentoDouble = Double.parseDouble(descuento);
                    int stockInt = Integer.parseInt(stock);
                    ProductoComida nuevoProducto = new ProductoComida(categoria,especie,nombre,precioDouble,descuentoDouble,
                            stockInt,descripcion,marca,fabricante,raza,sabor,contenedor,etapaDeVida,tipo);
                    inventario.agregarProducto(nuevoProducto);
                    actualizarTablaPComida(filtrarProductosComida());
                    JOptionPane.showMessageDialog(null,"Producto agregado");
                    limpiarAgregarPComida();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Formato equivocado",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
            });
        btnLimpiarPComida1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarAgregarPComida();
            }
        });
            //MODIFICAR PRODUCTO - COMIDA
        btnModificarPComida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CARACTERISTICAS GENERALES
                String categoria = "Comida";
                String nombre = txtModificarNombrePComida.getText();
                String precio = txtModificarPrecioPComida.getText();
                String descuento = txtModificarDescuentoPComida.getText();
                String stock = spnModificarStockPComida.getValue().toString();
                String descripcion = txtModificarDescripcionPComida.getText();
                //CARACTERISTICAS ESPECIFICAS
                String marca = cboModificarMarcaPComida.getSelectedItem().toString();
                String fabricante = cboModificarFabricantePComida.getSelectedItem().toString();
                String raza = cboModificarRazaPComida.getSelectedItem().toString();
                String sabor = cboModificarSaborPComida.getSelectedItem().toString();
                String contenedor = cboModificarContenedorPComida.getSelectedItem().toString();
                String etapaDeVida = cboModificarEtapaDeVidaPComida.getSelectedItem().toString();
                String tipo = cboModificarTipoPComida.getSelectedItem().toString();
                if(nombre.isEmpty() || precio.isEmpty() || descuento.isEmpty() || stock.isEmpty() ||
                        descripcion.isEmpty() || marca.isEmpty() || fabricante.isEmpty() || raza.isEmpty() || sabor.isEmpty() ||
                        contenedor.isEmpty() || etapaDeVida.isEmpty() || tipo.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Campos Vacíos",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    try {
                        double precioDouble = Double.parseDouble(precio);
                        double descuentoDouble = Double.parseDouble(descuento);
                        int stockInt = Integer.parseInt(stock);
                        Id identificador = new Id(txtIdModificarPComida.getText());
                        inventario.obtenerCatComida().modificarProducto(identificador,nombre,precioDouble,descuentoDouble,
                                stockInt,descripcion);
                        inventario.obtenerCatComida().modificarProductoComida(identificador,marca,fabricante,raza,sabor,
                                contenedor,etapaDeVida,tipo);
                        habilitarComponentesModificarPComida(false);
                        txtIdModificarPComida.setEnabled(true);
                        btnBuscarMPComida.setEnabled(true);
                        JOptionPane.showMessageDialog(null,"Producto modificado");
                        limpiarModificarPComida();
                        actualizarTablaPComida(filtrarProductosComida());
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null,"Formato incorrecto",
                                "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        btnBuscarMPComida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto producto = buscarProductoComida(txtIdModificarPComida);
                if(producto!=null){
                    txtModificarNombrePComida.setText(producto.obtenerNombre());
                    txtModificarPrecioPComida.setText(String.valueOf(producto.obtenerPrecio()));
                    txtModificarDescuentoPComida.setText(String.valueOf(producto.obtenerDescuento()));
                    spnModificarStockPComida.setValue(producto.obtenerStock());
                    txtModificarDescripcionPComida.setText(producto.obtenerDescripcion());
                    cboModificarMarcaPComida.setSelectedItem(((ProductoComida)producto).obtenerMarca());
                    cboModificarFabricantePComida.setSelectedItem(((ProductoComida)producto).obtenerFabricante());
                    cboModificarRazaPComida.setSelectedItem(((ProductoComida)producto).obtenerRaza());
                    cboModificarEtapaDeVidaPComida.setSelectedItem(((ProductoComida)producto).obtenerEtapaDeVida());
                    cboModificarSaborPComida.setSelectedItem(((ProductoComida)producto).obtenerSabor());
                    cboModificarContenedorPComida.setSelectedItem(((ProductoComida)producto).obtenerContenedor());
                    cboModificarTipoPComida.setSelectedItem(((ProductoComida)producto).obtenerTipo());
                    habilitarComponentesModificarPComida(true);
                    txtIdModificarPComida.setEnabled(false);
                    btnBuscarMPComida.setEnabled(false);
                }
            }
        });
        btnLimpiarPComida2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarModificarPComida();
            }
        });
        btnCancelarModificarPComida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarModificarPComida();
                habilitarComponentesModificarPComida(false);
                btnBuscarMPComida.setEnabled(true);
                txtIdModificarPComida.setEnabled(true);
            }
        });
            //ELIMINAR PRODCUTO - COMIDA
        btnEliminarPComida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Id identificador = new Id(txtEliminarPComida.getText());

                    inventario.obtenerCatComida().eliminarProducto(identificador);
                    actualizarTablaPComida(filtrarProductosComida());
                    JOptionPane.showMessageDialog(null,"Producto eliminado");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage(),
                            "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
            //BUSCAR PRODUCTO - COMIDA
        btnBuscarPComida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto producto = buscarProductoComida(txtBuscarPComida);
                if(producto!=null){
                    modeloTablaPComida.setRowCount(0);
                    if(cboMostrarPComida.getSelectedItem().equals("Características generales")){
                        modeloTablaPComida.addRow(((ProductoComida)producto).obtenerAtributosGenerales());
                    }else{
                        modeloTablaPComida.addRow(((ProductoComida)producto).obtenerAtributosEspecificos());
                    }
                    tablaPComida.setModel(modeloTablaPComida);
                    btnCancelarBusquedaPComida.setEnabled(true);
                    btnConsultarPComida.setEnabled(true);
                    cboFiltroPComida.setSelectedItem("Ninguno");
                    cboFiltroPComida.setEnabled(false);
                    cboOrdenPComida.setSelectedItem("Ninguno");
                    cboOrdenPComida.setEnabled(false);
                }
            }
        });
        btnCancelarBusquedaPComida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtBuscarPComida.setText("");
                actualizarTablaPComida(filtrarProductosComida());
                btnCancelarBusquedaPComida.setEnabled(false);
                btnConsultarPComida.setEnabled(false);
                cboFiltroPComida.setEnabled(true);
                cboOrdenPComida.setEnabled(true);
            }
        });
        btnConsultarPComida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto producto = buscarProductoComida(txtBuscarPComida);
                String accesibilidad = "INHABILITADO DE LA TIENDA";
                if(producto.obtenerAccesibilidad()){
                    accesibilidad = "HABILITADO EN LA TIENDA";
                }
                JOptionPane.showMessageDialog(null,accesibilidad);
            }
        });
        btnFiltroPComida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicializarTablaPComida();
                actualizarTablaPComida(filtrarProductosComida());
            }
        });


        //CAMPOS DE TEXTO - COMIDA
        txtAgregarPrecioPComida.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if(!Character.isDigit(c) && c != '.'){
                    e.consume();
                }
            }
        });

        txtAgregarDescuentoPComida.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if(!Character.isDigit(c) && c != '.'){
                    e.consume();
                }
            }
        });
        txtModificarPrecioPComida.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if(!Character.isDigit(c) && c != '.'){
                    e.consume();
                }
            }
        });

        txtModificarDescuentoPComida.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if(!Character.isDigit(c) && c != '.'){
                    e.consume();
                }
            }
        });
        txtIdModificarPComida.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if(!Character.isDigit(c)){
                    e.consume();
                }
            }
        });
        txtEliminarPComida.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if(!Character.isDigit(c)){
                    e.consume();
                }
            }
        });

        txtBuscarPComida.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if(!Character.isDigit(c)){
                    e.consume();
                }
            }
        });
    }

    //METODOS AUXILIARES - COMIDA
        //AGREGAR PRODUCTO - COMIDA
    public void inicializarAgregarPComida(){
        Comida comida = inventario.obtenerCatComida();
        for(String s: comida.obtenerEspecies()){
            cboAgregarEspeciePComida.addItem(s);
        }
        for(String s: comida.obtenerMARCA()){
            cboAgregarMarcaPComida.addItem(s);
        }
        for(String s: comida.obtenerFABRICANTE()){
            cboAgregarFabricantePComida.addItem(s);
        }
        for(String s: comida.obtenerRAZA()){
            cboAgregarRazaPComida.addItem(s);
        }
        for(String s: comida.obtenerSABOR()){
            cboAgregarSaborPComida.addItem(s);
        }
        for(String s: comida.obtenerCONTENEDOR()){
            cboAgregarContenedorPComiad.addItem(s);
        }
        for(String s: comida.obtenerETAPADEVIDA()){
            cboAgregarEtapaDeVidaPComida.addItem(s);
        }
        for(String s: comida.obtenerTIPO()){
            cboAgregarTipoPComida.addItem(s);
        }
    }
    public void limpiarAgregarPComida(){
        cboAgregarEspeciePComida.setSelectedIndex(0);
        txtAgregarNombrePComida.setText("");
        txtAgregarPrecioPComida.setText("");
        txtAgregarDescuentoPComida.setText("");
        spnAgregarStockPComida.setValue(0);
        txtAgregarDescripcionPComida.setText("");
        cboAgregarMarcaPComida.setSelectedIndex(0);
        cboAgregarFabricantePComida.setSelectedIndex(0);
        cboAgregarRazaPComida.setSelectedIndex(0);
        cboAgregarEtapaDeVidaPComida.setSelectedIndex(0);
        cboAgregarSaborPComida.setSelectedIndex(0);
        cboAgregarContenedorPComiad.setSelectedIndex(0);
        cboAgregarTipoPComida.setSelectedIndex(0);
    }
        //ELIMINAR PRODUCTO - COMIDA
        //MODIFICAR PRODUCTO - COMIDA
    public void habilitarComponentesModificarPComida(boolean b){
            txtModificarNombrePComida.setEnabled(b);
            txtModificarPrecioPComida.setEnabled(b);
            txtModificarDescuentoPComida.setEnabled(b);
            spnModificarStockPComida.setEnabled(b);
            txtModificarDescripcionPComida.setEnabled(b);
            cboModificarMarcaPComida.setEnabled(b);
            cboModificarFabricantePComida.setEnabled(b);
            cboModificarRazaPComida.setEnabled(b);
            cboModificarEtapaDeVidaPComida.setEnabled(b);
            cboModificarSaborPComida.setEnabled(b);
            cboModificarContenedorPComida.setEnabled(b);
            cboModificarTipoPComida.setEnabled(b);
            btnLimpiarPComida2.setEnabled(b);
            btnModificarPComida.setEnabled(b);
            btnCancelarModificarPComida.setEnabled(b);
    }
    public void inicializarComponentesModificarPComida(){
        habilitarComponentesModificarPComida(false);
        Comida comida = inventario.obtenerCatComida();
        for(String s: comida.obtenerMARCA()){
            cboModificarMarcaPComida.addItem(s);
        }
        for(String s: comida.obtenerFABRICANTE()){
            cboModificarFabricantePComida.addItem(s);
        }
        for(String s: comida.obtenerRAZA()){
            cboModificarRazaPComida.addItem(s);
        }
        for(String s: comida.obtenerSABOR()){
            cboModificarSaborPComida.addItem(s);
        }
        for(String s: comida.obtenerCONTENEDOR()){
            cboModificarContenedorPComida.addItem(s);
        }
        for(String s: comida.obtenerETAPADEVIDA()){
            cboModificarEtapaDeVidaPComida.addItem(s);
        }
        for(String s: comida.obtenerTIPO()){
            cboModificarTipoPComida.addItem(s);
        }
    }
    public void limpiarModificarPComida(){
        txtModificarNombrePComida.setText("");
        txtModificarPrecioPComida.setText("");
        txtModificarDescuentoPComida.setText("");
        spnModificarStockPComida.setValue(0);
        txtModificarDescripcionPComida.setText("");
        cboModificarMarcaPComida.setSelectedIndex(0);
        cboModificarFabricantePComida.setSelectedIndex(0);
        cboModificarRazaPComida.setSelectedIndex(0);
        cboModificarEtapaDeVidaPComida.setSelectedIndex(0);
        cboModificarSaborPComida.setSelectedIndex(0);
        cboModificarContenedorPComida.setSelectedIndex(0);
        cboModificarTipoPComida.setSelectedIndex(0);
    }
        //BUSCAR PRODUCTO - COMIDA
    public Producto buscarProductoComida(JTextField campoTexto){
        try {
            Id identificador = new Id(campoTexto.getText());
            Producto producto = inventario.obtenerCatComida().buscarProducto(identificador);
            return producto;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
        //MOSTRAR PRODUCTOS = COMIDA
    public void inicializarTablaPComida(){
        modeloTablaPComida.setColumnCount(0);
        if(cboMostrarPComida.getSelectedItem().equals("Características generales")){
            modeloTablaPComida.addColumn("ID");
            modeloTablaPComida.addColumn("NOMBRE");
            modeloTablaPComida.addColumn("ESPECIE");
            modeloTablaPComida.addColumn("PRECIO");
            modeloTablaPComida.addColumn("DESCUENTO");
            modeloTablaPComida.addColumn("STOCK");
            modeloTablaPComida.addColumn("UNIDADES VENDIDAS");
            modeloTablaPComida.addColumn("CALIFICACIÓN");
            modeloTablaPComida.addColumn("DESCRIPCIÓN");
        }else{
            modeloTablaPComida.addColumn("ID");
            modeloTablaPComida.addColumn("MARCA");
            modeloTablaPComida.addColumn("FABRICANTE");
            modeloTablaPComida.addColumn("RAZA");
            modeloTablaPComida.addColumn("SABOR");
            modeloTablaPComida.addColumn("CONTENEDOR");
            modeloTablaPComida.addColumn("ETAPA DE VIDA");
            modeloTablaPComida.addColumn("TIPO");
        }

        tablaPComida.setModel(modeloTablaPComida);
    }
    public void actualizarTablaPComida(Object[] productos){
        modeloTablaPComida.setRowCount(0);
        if(cboMostrarPComida.getSelectedItem().equals("Características generales")){
            for(Object o: productos){
                modeloTablaPComida.addRow(((ProductoComida)o).obtenerAtributosGenerales());
            }
        }else{
            for(Object o: productos){
                modeloTablaPComida.addRow(((ProductoComida)o).obtenerAtributosEspecificos());
            }
        }
    }
    public Object[] filtrarProductosComida(){
        modeloTablaPComida.setRowCount(0);
        String filtro = String.valueOf(cboFiltroPComida.getSelectedItem());
        Object[] productos;
        if(!txtBuscarPComida.getText().isEmpty()){
            Producto producto = buscarProductoComida(txtBuscarPComida);
            return new Object[] {producto};
        }
        if(filtro.equals("Ninguno")){
            productos = inventario.obtenerCatComida().obtenerProductos();
        }else{
            productos = inventario.obtenerCatComida().filtrarEspecieMascota(filtro);
        }
        ordenarProductosComida(productos);
        return productos;
    }
    public void ordenarProductosComida(Object[] productos){
        if(cboOrdenPComida.getSelectedIndex()==1){
            inventario.obtenerCatComida().ordenarPorPrecio(productos);
        } else if (cboOrdenPComida.getSelectedIndex()==2) {
            inventario.obtenerCatComida().ordenarPorStock(productos);
        } else if (cboOrdenPComida.getSelectedIndex()==3) {
            inventario.obtenerCatComida().ordenarUnVendidas(productos);
        }else if(cboOrdenPComida.getSelectedIndex()==4){
            inventario.obtenerCatComida().ordenarCalificacion(productos);
        }
    }

    //ACCESORIO
    //INSUMO MEDICO

}
