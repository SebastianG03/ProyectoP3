package SistemaDeCompras;

import Inventario.Categorias.Accesorio;
import Inventario.Categorias.Comida;
import Inventario.Categorias.InsMedico;
import Inventario.Inventario;
import Inventario.Producto.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaGestionInventario {
    private JPanel Principal;
    private JTabbedPane tabbedPane_General;
    private JComboBox cboCategoria_Inicio;
    private JButton btnAgregar_Inicio;
    private JButton btnListaProductos_Inicio;
    private JTabbedPane tabbedPane_AgregarProducto;
    private JComboBox cboMascota_AgregarProducto;
    private JTextField txtPrecio_AgregarProducto;
    private JTextField txtDescuento_AgregarProducto;
    private JTextField txtStock_AgregarProducto;
    private JTextArea txtDescripcion_AgregarProducto;
    private JButton btnRegresar_AgregarProducto;
    private JComboBox cboMarca_AgregarProductoComida;
    private JComboBox cboFabricante_AgregarProductoComida;
    private JComboBox cboRaza_AgregarProductoComida;
    private JComboBox cboEtapaDeVida_AgregarProductoComida;
    private JComboBox cboSabor_AgregarProductoComida;
    private JComboBox cboContenedor_AgregarProductoComida;
    private JComboBox cboTipo_AgregarProductoComida;
    private JButton btnAgregarProductoComida;
    private JTextField txtNombre_AgregarProducto;
    private JButton btnLimpiar_AgregarProductoComida;
    private JTextField txtIdentificador_ListaProductos;
    private JButton btnBuscar_ListaProductos;
    private JButton btnListaPConsultarP;
    private JButton btnListaPCancelarB;
    private JTabbedPane tabbedPane_ListaProductos;
    private JTable tablaDeProductos;
    private JTable tablaPAccesorio;
    private JTable tablaPInsMedico;
    private JScrollPane jScrollPane;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JComboBox cboMostrar_ListaProductos;
    private JComboBox cboFiltrar_ListaProductos;
    private JComboBox cboOrdenar_ListaProductos;
    private JButton btnAceptar_ListaProductos;
    private JButton btnRegresar_ListaProductos;
    private JComboBox cboMarca_AgregarProductoAccesorio;
    private JComboBox cboFabricante_AgregarProductoAccesorio;
    private JComboBox cboTipo_AgregarProductoAccesorio;
    private JComboBox cboEtapaDeVida_AgregarProductoAccesorio;
    private JButton btnAgregarProductoAccesorio;
    private JButton btnLimpiar_AgregarProductoAccesorio;
    private JComboBox cboMarca_AgregarProductoInsMedico;
    private JComboBox cboFabricante_AgregarProductoInsMedico;
    private JComboBox cboTipo_AgregarProductoInsMedico;
    private JButton btnAgregarProductoInsMedico;
    private JButton btnLimpiar_AgregarProductoInsMedico;
    private JPanel panelAgregarProducto;
    private JPanel panelListaProductos;
    private JPanel panelnicio;
    private JComboBox cboTipo_ModificarProductoAccesorio;
    private JComboBox cboMascota_ModificarProducto;
    private JComboBox cboMarca_ModificarProductoInsMedico;
    private JTextArea txtDescripcion_ModificarProducto;
    private JButton btnLimpiar_ModificarProductoAccesorio;
    private JComboBox cboFabricante_ModificarProductoInsMedico;
    private JButton btnRegresar_Modificarproducto;
    private JButton btnModificarProductoInsMedico;
    private JTextField txtNombre_ModificarProducto;
    private JComboBox cboFabricante_ModificarProductoComida;
    private JTextField txtStock_ModificarProducto;
    private JComboBox cboMarca_ModificarProductoAccesorio;
    private JComboBox cboFabricante_ModificarProductoAccesorio;
    private JComboBox cboRaza_ModificarProductoComida;
    private JButton btnModificarProductoComida;
    private JComboBox cboTipo_ModificarProductoComida;
    private JComboBox cboTipo_ModificarProductoInsMedico;
    private JTextField txtPrecio_ModificarProducto;
    private JButton btnLimpiar_ModificarProductoInsMedico;
    private JComboBox cboEtapaDeVida_ModificarProductoAccesorio;
    private JTabbedPane tabbedPane_ModificarProducto;
    private JComboBox cboContenedor_ModificarProductoComida;
    private JComboBox cboEtapaDeVida_ModificarProductoComida;
    private JButton btnModificarProductoAccesorio;
    private JTextField txtDescuento_ModificarProducto;
    private JPanel panelModificarProducto;
    private JComboBox cboSabor_ModificarProductoComida;
    private JComboBox cboMarca_ModificarProductoComida;
    private JButton btnLimpiar_ModificarProductoComida;
    private JButton btnEliminar_ModificarProductoComida;
    private JButton btnEliminar_ModificarProductoAccesorio;
    private JButton btnEliminar_ModificarProductoInsMedico;
    private final DefaultTableModel modeloTablaPComida = new DefaultTableModel();
    private final DefaultTableModel modeloTablaPAccesorio = new DefaultTableModel();
    private final DefaultTableModel modeloTablaPInsMedico = new DefaultTableModel();
    private final Inventario inventario = new Inventario();
    private final Component agregarPrductoComida = tabbedPane_AgregarProducto.getComponentAt(0);
    private final Component agregarProductoAccesorio = tabbedPane_AgregarProducto.getComponentAt(1);
    private final Component agregarProductoInsMedico = tabbedPane_AgregarProducto.getComponentAt(2);
    private final Component listaProductosComida = tabbedPane_ListaProductos.getComponentAt(0);
    private final Component listaProductosAccesorio = tabbedPane_ListaProductos.getComponentAt(1);
    private final Component listaProductosInsMedico = tabbedPane_ListaProductos.getComponentAt(2);
    private final Component modificarProductoComida = tabbedPane_ModificarProducto.getComponentAt(0);
    private final Component modificarProductoAccesorio = tabbedPane_ModificarProducto.getComponentAt(1);
    private final Component modificarProductoInsMedico = tabbedPane_ModificarProducto.getComponentAt(2);
    private static JFrame frame = new JFrame("VentanaGestionInventario");

    public VentanaGestionInventario() {
        //ELIMINAR PESTAÑAS
        tabbedPane_AgregarProducto.removeAll();
        tabbedPane_ListaProductos.removeAll();
        tabbedPane_ModificarProducto.removeAll();
        //BLOQUEAR PESTAÑAS
        tabbedPane_General.setEnabled(false);
        //INICIALIZAR TABLAS DE PRODUCTOS
        inicializarTablaPComida();
        inicializarTablaPAccesorio();
        inicializarTablaPInsMedico();
        //INICIALIZAR COMPONENETES SWING
        inicializarComponentesAgregarProducto();
        inicializarComponentesMEProducto();
        inicializarComponentesListaProductos();
        actualizarTablaProductos("comida",modeloTablaPComida);
        actualizarTablaProductos("accesorio",modeloTablaPAccesorio);
        actualizarTablaProductos("insumo médico",modeloTablaPInsMedico);

        //INICIO
        btnAgregar_Inicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoria = String.valueOf(cboCategoria_Inicio.getSelectedItem()).toLowerCase();
                tabbedPane_General.setSelectedIndex(1);
                if(categoria.equals("comida")){
                    tabbedPane_AgregarProducto.addTab("COMIDA",agregarPrductoComida);
                }
                if(categoria.equals("accesorio")){
                    tabbedPane_AgregarProducto.addTab("ACCESORIO",agregarProductoAccesorio);
                }
                if(categoria.equals("insumo médico")){
                    tabbedPane_AgregarProducto.addTab("INSUMO MÉDICO",agregarProductoInsMedico);
                }
            }
        });
        btnListaProductos_Inicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoria = String.valueOf(cboCategoria_Inicio.getSelectedItem()).toLowerCase();
                tabbedPane_General.setSelectedIndex(3);
                if(categoria.equals("comida")){
                    tabbedPane_ListaProductos.addTab("COMIDA",listaProductosComida);
                }
                if(categoria.equals("accesorio")){
                    tabbedPane_ListaProductos.addTab("ACCESORIO",listaProductosAccesorio);
                }
                if(categoria.equals("insumo médico")){
                    tabbedPane_ListaProductos.addTab("INSUMO MÉDICO",listaProductosInsMedico);
                }
            }
        });
        //AGREGAR PRODUCTO
        btnAgregarProductoComida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ATRIBUTOS PRODUCTO COMIDA
                String marca = String.valueOf(cboMarca_AgregarProductoComida.getSelectedItem());
                String fabricante = String.valueOf(cboFabricante_AgregarProductoComida.getSelectedItem());
                String raza = String.valueOf(cboRaza_AgregarProductoComida.getSelectedItem());
                String sabor = String.valueOf(cboSabor_AgregarProductoComida.getSelectedItem());
                String contenedor = String.valueOf(cboContenedor_AgregarProductoComida.getSelectedItem());
                String etapaDeVida = String.valueOf(cboEtapaDeVida_AgregarProductoComida.getSelectedItem());
                String tipo = String.valueOf(cboTipo_AgregarProductoComida.getSelectedItem());
                //INSTANCIA NUEVO PRODUCTO COMIDA
                Producto nuevo = instanciarProductoComida(marca,fabricante,raza,sabor,contenedor,etapaDeVida,tipo);
                if(nuevo!=null){
                    if(inventario.agregarProducto(nuevo.obtenerCategoria(),nuevo)){ //Añade el producto al inventario
                        actualizarTablaProductos(nuevo.obtenerCategoria(), modeloTablaPComida);
                        mensajeInfo("Producto agregado existosamente");
                        limpiarComponentesAgregarProducto();
                        limpiarComponenetesAgregarPComida();
                        tabbedPane_General.setSelectedIndex(0);
                    }else {
                        mensajeError("Ingresó un producto con el mismo nombre o descripción, inténtelo de nuevo");
                    }
                }
            }
        });
        btnAgregarProductoAccesorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ATRIBUTOS PRODUCTO ACCESORIO
                String marca = String.valueOf(cboMarca_AgregarProductoAccesorio.getSelectedItem());
                String fabricante = String.valueOf(cboFabricante_AgregarProductoAccesorio.getSelectedItem());
                String etapaDeVida = String.valueOf(cboEtapaDeVida_AgregarProductoAccesorio.getSelectedItem());
                String tipo = String.valueOf(cboTipo_AgregarProductoAccesorio.getSelectedItem());
                //INSTANCIA NUEVO PRODUCTO ACCESORIO
                Producto nuevo = instanciarProductoAccesorio(marca,fabricante,etapaDeVida,tipo);
                if(nuevo!=null){
                    if(inventario.agregarProducto(nuevo.obtenerCategoria(),nuevo)){ //Añade el producto al inventario
                        actualizarTablaProductos(nuevo.obtenerCategoria(), modeloTablaPAccesorio);
                        mensajeInfo("Producto agregado existosamente");
                        limpiarComponentesAgregarProducto();
                        limpiarComponenetesAgregarPAccesorio();
                        tabbedPane_General.setSelectedIndex(0);
                    }else {
                        mensajeError("Ingresó un producto con el mismo nombre o descripción, inténtelo de nuevo");
                    }
                }
            }
        });
        btnAgregarProductoInsMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ATRIBUTOS PRODUCTO INSUMO MEDICO
                String marca = String.valueOf(cboMarca_AgregarProductoAccesorio.getSelectedItem());
                String fabricante = String.valueOf(cboFabricante_AgregarProductoAccesorio.getSelectedItem());
                String tipo = String.valueOf(cboTipo_AgregarProductoAccesorio.getSelectedItem());
                //INSTANCIA NUEVO PRODUCTO INSUMO MEDICO
                Producto nuevo = instanciarProductoInsMedico(marca,fabricante,tipo);
                if(nuevo!=null){
                    if(inventario.agregarProducto(nuevo.obtenerCategoria(),nuevo)){ //Añade el producto al inventario
                        actualizarTablaProductos(nuevo.obtenerCategoria(), modeloTablaPInsMedico);
                        mensajeInfo("Producto agregado existosamente");
                        limpiarComponentesAgregarProducto();
                        limpiarComponentesAgregarPInsMedico();
                        tabbedPane_General.setSelectedIndex(0);
                    }else {
                        mensajeError("Ingresó un producto con el mismo nombre o descripción, inténtelo de nuevo");
                    }
                }
            }
        });
        //LIMPIAR CAMPOS AGREGAR PRODUCTO
        btnLimpiar_AgregarProductoComida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarComponentesAgregarProducto();
                limpiarComponenetesAgregarPComida();
            }
        });
        btnLimpiar_AgregarProductoAccesorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarComponentesAgregarProducto();
                limpiarComponenetesAgregarPAccesorio();
            }
        });
        btnLimpiar_AgregarProductoInsMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarComponentesAgregarProducto();
                limpiarComponentesAgregarPInsMedico();
            }
        });
        //MODIFICAR PRODUCTO
        btnModificarProductoComida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CARACTERISTICAS COMIDA
                String marca = String.valueOf(cboMarca_ModificarProductoComida.getSelectedItem());
                String fabricate = String.valueOf(cboFabricante_ModificarProductoComida.getSelectedItem());
                String raza = String.valueOf(cboRaza_ModificarProductoComida.getSelectedItem());
                String etapaDeVida = String.valueOf(cboEtapaDeVida_ModificarProductoComida.getSelectedItem());
                String sabor = String.valueOf(cboSabor_ModificarProductoComida.getSelectedItem());
                String contenedor = String.valueOf(cboContenedor_ModificarProductoComida.getSelectedItem());
                String tipo = String.valueOf(cboTipo_ModificarProductoComida.getSelectedItem());
                if(modificarProductoComida(marca,fabricate,raza,etapaDeVida,sabor,contenedor,tipo)){
                    mensajeInfo("Producto modificado con éxito");
                    actualizarTablaProductos("comida",modeloTablaPComida);
                    tabbedPane_General.setSelectedIndex(3);
                }
            }
        });
        btnModificarProductoAccesorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CARACTERISTICAS ACCESORIO
                String marca = String.valueOf(cboMarca_ModificarProductoAccesorio.getSelectedItem());
                String fabricate = String.valueOf(cboFabricante_ModificarProductoAccesorio.getSelectedItem());
                String tipo = String.valueOf(cboTipo_ModificarProductoAccesorio.getSelectedItem());
                String etapaDeVida = String.valueOf(cboEtapaDeVida_ModificarProductoAccesorio.getSelectedItem());
                if(modificarProductoAccesorio(marca,fabricate,tipo,etapaDeVida)){
                    mensajeInfo("Producto modificado con éxito");
                    actualizarTablaProductos("accesorio",modeloTablaPAccesorio);
                    tabbedPane_General.setSelectedIndex(3);
                }
            }
        });
        btnModificarProductoInsMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CARACTERISTICAS INSUMO MEDICO
                String marca = String.valueOf(cboMarca_ModificarProductoInsMedico.getSelectedItem());
                String fabricate = String.valueOf(cboFabricante_ModificarProductoInsMedico.getSelectedItem());
                String tipo = String.valueOf(cboTipo_ModificarProductoInsMedico.getSelectedItem());
                if(modificarProductoInsMedico(marca,fabricate,tipo)){
                    mensajeInfo("Producto modificado con éxito");
                    actualizarTablaProductos("insumo médico",modeloTablaPInsMedico);
                    tabbedPane_General.setSelectedIndex(3);
                }
            }
        });
        //ELIMINAR PRODUCTO
        btnEliminar_ModificarProductoComida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(eliminarProducto("comida",txtIdentificador_ListaProductos)){
                    mensajeInfo("El producto fue eliminado con éxito");
                    actualizarTablaProductos("comida",modeloTablaPComida);
                    tabbedPane_General.setSelectedIndex(3);
                }
            }
        });
        btnEliminar_ModificarProductoAccesorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(eliminarProducto("accesorio",txtIdentificador_ListaProductos)){
                    mensajeInfo("El producto fue eliminado con éxito");
                    actualizarTablaProductos("accesorio",modeloTablaPAccesorio);
                    tabbedPane_General.setSelectedIndex(3);
                }
            }
        });
        btnEliminar_ModificarProductoInsMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(eliminarProducto("insumo médico",txtIdentificador_ListaProductos)){
                    mensajeInfo("El producto fue eliminado con éxito");
                    actualizarTablaProductos("insumo médico",modeloTablaPInsMedico);
                    tabbedPane_General.setSelectedIndex(3);
                }
            }
        });
        //BUSCAR PRODUCTO
        btnBuscar_ListaProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoria = String.valueOf(cboCategoria_Inicio.getSelectedItem()).toLowerCase();
                Producto encontrado = buscarProducto(categoria,txtIdentificador_ListaProductos);
                if(encontrado!=null) {
                    tabbedPane_General.setSelectedIndex(2);
                    if (categoria.equals("comida")) {
                        //CARACTERISTICAS ESPECIFICAS
                        tabbedPane_ModificarProducto.addTab("COMIDA", modificarProductoComida);
                        cboMarca_ModificarProductoComida.setSelectedItem(((ProductoComida)encontrado).obtener_marca());
                        cboFabricante_ModificarProductoComida.setSelectedItem(((ProductoComida)encontrado).obtener_fabricante());
                        cboRaza_ModificarProductoComida.setSelectedItem(((ProductoComida)encontrado).obtener_raza());
                        cboEtapaDeVida_ModificarProductoComida.setSelectedItem(((ProductoComida)encontrado).obtener_etapa_de_vida());
                        cboSabor_ModificarProductoComida.setSelectedItem(((ProductoComida)encontrado).obtener_sabor());
                        cboContenedor_ModificarProductoComida.setSelectedItem(((ProductoComida)encontrado).obtener_contenedor());
                        cboTipo_ModificarProductoComida.setSelectedItem(((ProductoComida)encontrado).obtener_tipo());
                    }
                    if (categoria.equals("accesorio")) {
                        tabbedPane_ModificarProducto.addTab("ACCESORIO", modificarProductoAccesorio);
                        //CARACTERISTICAS ESPECIFICAS
                        cboMarca_ModificarProductoAccesorio.setSelectedItem(((ProductoAccesorio)encontrado).obtener_marca());
                        cboFabricante_ModificarProductoAccesorio.setSelectedItem(((ProductoAccesorio)encontrado).obtener_fabricante());
                        cboTipo_ModificarProductoAccesorio.setSelectedItem(((ProductoAccesorio)encontrado).obtener_tipo());
                        cboEtapaDeVida_ModificarProductoAccesorio.setSelectedItem(((ProductoAccesorio)encontrado).obtener_etapa_de_vida());
                    }
                    if (categoria.equals("insumo médico")) {
                        tabbedPane_ModificarProducto.addTab("INSUMO MÉDICO", modificarProductoInsMedico);
                        //CARACTERISTICAS ESPECIFICAS
                        cboMarca_ModificarProductoInsMedico.setSelectedItem(((ProductoInsMedico)encontrado).obtener_marca());
                        cboFabricante_ModificarProductoInsMedico.setSelectedItem(((ProductoInsMedico)encontrado).obtener_fabricante());
                        cboTipo_ModificarProductoInsMedico.setSelectedItem(((ProductoInsMedico)encontrado).obtener_tipo());
                    }
                    //CARACTERISTICAS GENERALES
                    txtNombre_ModificarProducto.setText(encontrado.obtener_nombre());
                    txtPrecio_ModificarProducto.setText(String.valueOf(encontrado.obtener_precio()));
                    txtDescuento_ModificarProducto.setText(String.valueOf(encontrado.obtener_descuento()));
                    txtStock_ModificarProducto.setText(String.valueOf(encontrado.obtener_stock()));
                    txtDescripcion_ModificarProducto.setText(String.valueOf(encontrado.obtener_descripcion()));
                }
            }
        });
        //FILTRAR Y ORDENAR PRODUCTOS
        btnAceptar_ListaProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoria = tabbedPane_ListaProductos.getTitleAt(tabbedPane_ListaProductos.getSelectedIndex());
                if(categoria.compareToIgnoreCase("comida")==0){
                    inicializarTablaPComida();
                    actualizarTablaProductos(categoria.toLowerCase(),modeloTablaPComida);
                }
                if(categoria.compareToIgnoreCase("accesorio")==0){
                    inicializarTablaPAccesorio();
                    actualizarTablaProductos(categoria.toLowerCase(),modeloTablaPAccesorio);
                }
                if(categoria.compareToIgnoreCase("insumo médico")==0){
                    inicializarTablaPInsMedico();
                    actualizarTablaProductos(categoria.toLowerCase(),modeloTablaPInsMedico);
                }
            }
        });
        //BOTONES REGRESAR
        btnRegresar_AgregarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane_General.setSelectedIndex(0);
                tabbedPane_AgregarProducto.removeAll();
            }
        });
        btnRegresar_ListaProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane_General.setSelectedIndex(0);
                tabbedPane_ListaProductos.removeAll();
            }
        });
        btnRegresar_Modificarproducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane_General.setSelectedIndex(3);
                tabbedPane_ModificarProducto.removeAll();
            }
        });

        //
        txtPrecio_AgregarProducto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if(!Character.isDigit(c) && c != '.'){
                    e.consume();
                }
            }
        });
        txtDescuento_AgregarProducto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if(!Character.isDigit(c) && c != '.'){
                    e.consume();
                }
            }
        });
        txtStock_AgregarProducto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if(!Character.isDigit(c)){
                    e.consume();
                }
            }
        });
        txtIdentificador_ListaProductos.addKeyListener(new KeyAdapter() {
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

    public static void main(String[] args) {
        frame.setContentPane(new VentanaGestionInventario().Principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,700);
        frame.setVisible(true);
    }

    //INSTANCIAR PRODUCTOS
    public Producto instanciarProductoComida(String marca,String fabricante,String raza,String sabor,String contenedor,String etapaDeVida, String tipo){
        //CARACTERISTICAS GENERALES
        String categoria = String.valueOf(cboCategoria_Inicio.getSelectedItem());
        String mascota = String.valueOf(cboMascota_AgregarProducto.getSelectedItem()).toLowerCase();
        String nombre = txtNombre_AgregarProducto.getText();
        String precio = txtPrecio_AgregarProducto.getText();
        String descuento = txtDescuento_AgregarProducto.getText();
        String stock = txtStock_AgregarProducto.getText();
        String descripcion = txtDescripcion_AgregarProducto.getText();

        String[] caracteristicasGenerales = new String[]{categoria,mascota,nombre,precio,descuento,stock,descripcion};
        if(!verificarCamposDeTexto(caracteristicasGenerales)){ //Verifica que los campos de textos no estén vacíos
            try{ // Verifica que la entrada ingresada cumpla con el formato de decimal o entero
                double precioDouble = Double.parseDouble(precio);
                double descuentoDouble = Double.parseDouble(descuento);
                int stockInt = Integer.parseInt(stock);
                return new ProductoComida(categoria,mascota,nombre,precioDouble,descuentoDouble,stockInt,
                        descripcion,marca,fabricante,raza,sabor,contenedor,etapaDeVida,tipo);
            }catch (Exception ex){
                mensajeError("Formato equivocado, vuelva a intentarlo");
            }
        }else{
            mensajeError("Campos Vacíos, vuleva a intentarlo");
        }
        return null;
    }
    public Producto instanciarProductoAccesorio(String marca,String fabricante,String tipo,String etapaDeVida){
        //CARACTERISTICAS GENERALES
        String categoria = String.valueOf(cboCategoria_Inicio.getSelectedItem());
        String mascota = String.valueOf(cboMascota_AgregarProducto.getSelectedItem()).toLowerCase();
        String nombre = txtNombre_AgregarProducto.getText();
        String precio = txtPrecio_AgregarProducto.getText();
        String descuento = txtDescuento_AgregarProducto.getText();
        String stock = txtStock_AgregarProducto.getText();
        String descripcion = txtDescripcion_AgregarProducto.getText();

        String[] caracteristicasGenerales = new String[]{categoria,mascota,nombre,precio,descuento,stock,descripcion};
        if(!verificarCamposDeTexto(caracteristicasGenerales)){ //Verifica que los campos de textos no estén vacíos
            try{ // Verifica que la entrada ingresada cumpla con el formato de decimal o entero
                double precioDouble = Double.parseDouble(precio);
                double descuentoDouble = Double.parseDouble(descuento);
                int stockInt = Integer.parseInt(stock);
                return new ProductoAccesorio(categoria,mascota,nombre,precioDouble,descuentoDouble,stockInt,
                        descripcion,marca,fabricante,tipo,etapaDeVida);
            }catch (Exception ex){
                mensajeError("Formato equivocado, vuelva a intentarlo");
            }
        }else{
            mensajeError("Campos Vacíos, vuleva a intentarlo");
        }
        return null;
    }
    public Producto instanciarProductoInsMedico(String marca,String fabricante,String tipo){
        //CARACTERISTICAS GENERALES
        String categoria = String.valueOf(cboCategoria_Inicio.getSelectedItem());
        String mascota = String.valueOf(cboMascota_AgregarProducto.getSelectedItem()).toLowerCase();
        String nombre = txtNombre_AgregarProducto.getText();
        String precio = txtPrecio_AgregarProducto.getText();
        String descuento = txtDescuento_AgregarProducto.getText();
        String stock = txtStock_AgregarProducto.getText();
        String descripcion = txtDescripcion_AgregarProducto.getText();

        String[] caracteristicasGenerales = new String[]{categoria,mascota,nombre,precio,descuento,stock,descripcion};
        if(!verificarCamposDeTexto(caracteristicasGenerales)){ //Verifica que los campos de textos no estén vacíos
            try{ // Verifica que la entrada ingresada cumpla con el formato de decimal o entero
                double precioDouble = Double.parseDouble(precio);
                double descuentoDouble = Double.parseDouble(descuento);
                int stockInt = Integer.parseInt(stock);
                return new ProductoInsMedico(categoria,mascota,nombre,precioDouble,descuentoDouble,stockInt,
                        descripcion,marca,fabricante,tipo);
            }catch (Exception ex){
                mensajeError("Formato equivocado, vuelva a intentarlo");
            }
        }else{
            mensajeError("Campos Vacíos, vuleva a intentarlo");
        }
        return null;
    }

    //MODIFICAR PRODUCTOS
    public boolean modificarProductoComida(String marca,String fabricante,String raza,String sabor,String contenedor,String etapaDeVida,String tipo){
        try{
            Id identificador = new Id(txtIdentificador_ListaProductos.getText());
            Comida comida = inventario.obtenerCategoriaComida();
            //CARACTERISTICAS GENERALES
            String nombre = txtNombre_ModificarProducto.getText();
            String precio = txtPrecio_ModificarProducto.getText();
            String descuento = txtDescuento_ModificarProducto.getText();
            String stock = txtStock_ModificarProducto.getText();
            String descripcion = txtDescripcion_ModificarProducto.getText();

            String[] caracteristicasGenerales = new String[]{nombre,precio,descuento,stock,descripcion};
            if(!verificarCamposDeTexto(caracteristicasGenerales)){
                try{ // Verifica que la entrada ingresada cumpla con el formato de decimal o entero
                    double precioDouble = Double.parseDouble(precio);
                    double descuentoDouble = Double.parseDouble(descuento);
                    int stockInt = Integer.parseInt(stock);
                    comida.modificar_producto(identificador,nombre,precioDouble,descuentoDouble,stockInt,descripcion);
                    comida.modificar_producto_comida(identificador,marca,fabricante,raza,sabor,contenedor,etapaDeVida,tipo);
                    return true;
                }catch (Exception ex){
                    mensajeError("Formato equivocado, vuelva a intentarlo");
                }
            }else{
                mensajeError("Campos Vacíos, vuleva a intentarlo");
            }
        }catch (Exception ex){
            mensajeError(ex.getMessage());
        }
        return false;
    }
    public boolean modificarProductoAccesorio(String marca,String fabricante,String etapaDeVida,String tipo){
        try{
            Id identificador = new Id(txtIdentificador_ListaProductos.getText());
            Accesorio accesorio = inventario.obtenerCategoriaAccesorio();
            //CARACTERISTICAS GENERALES
            String nombre = txtNombre_ModificarProducto.getText();
            String precio = txtPrecio_ModificarProducto.getText();
            String descuento = txtDescuento_ModificarProducto.getText();
            String stock = txtStock_ModificarProducto.getText();
            String descripcion = txtDescripcion_ModificarProducto.getText();

            String[] caracteristicasGenerales = new String[]{nombre,precio,descuento,stock,descripcion};
            if(!verificarCamposDeTexto(caracteristicasGenerales)){
                try{ // Verifica que la entrada ingresada cumpla con el formato de decimal o entero
                    double precioDouble = Double.parseDouble(precio);
                    double descuentoDouble = Double.parseDouble(descuento);
                    int stockInt = Integer.parseInt(stock);
                    accesorio.modificar_producto(identificador,nombre,precioDouble,descuentoDouble,stockInt,descripcion);
                    accesorio.modificarProductoAccesorio(identificador,marca,fabricante,tipo,etapaDeVida);
                    return true;
                }catch (Exception ex){
                    mensajeError("Formato equivocado, vuelva a intentarlo");
                }
            }else{
                mensajeError("Campos Vacíos, vuleva a intentarlo");
            }
        }catch (Exception ex){
            mensajeError(ex.getMessage());
        }
        return false;
    }
    public boolean modificarProductoInsMedico(String marca,String fabricante,String tipo){
        try{
            Id identificador = new Id(txtIdentificador_ListaProductos.getText());
            InsMedico insMedico = inventario.obtenerCategoriaInsMedico();
            //CARACTERISTICAS GENERALES
            String nombre = txtNombre_ModificarProducto.getText();
            String precio = txtPrecio_ModificarProducto.getText();
            String descuento = txtDescuento_ModificarProducto.getText();
            String stock = txtStock_ModificarProducto.getText();
            String descripcion = txtDescripcion_ModificarProducto.getText();

            String[] caracteristicasGenerales = new String[]{nombre,precio,descuento,stock,descripcion};
            if(!verificarCamposDeTexto(caracteristicasGenerales)){
                try{ // Verifica que la entrada ingresada cumpla con el formato de decimal o entero
                    double precioDouble = Double.parseDouble(precio);
                    double descuentoDouble = Double.parseDouble(descuento);
                    int stockInt = Integer.parseInt(stock);
                    insMedico.modificar_producto(identificador,nombre,precioDouble,descuentoDouble,stockInt,descripcion);
                    insMedico.modificarProductoInsMedico(identificador,marca,fabricante,tipo);
                    return true;
                }catch (Exception ex){
                    mensajeError("Formato equivocado, vuelva a intentarlo");
                }
            }else{
                mensajeError("Campos Vacíos, vuleva a intentarlo");
            }
        }catch (Exception ex){
            mensajeError(ex.getMessage());
        }
        return false;
    }

    //ELIMINAR PRODUCTOS
    public boolean eliminarProducto(String categoria, JTextField campoTexto){
        try{
            Id identificador = new Id(campoTexto.getText());
            inventario.obtenerCategoria(categoria).eliminar_producto(identificador);
            return true;
        }catch (Exception ex){
            mensajeError(ex.getMessage());
        }
        return false;
    }

    //INICIALIZAR COMBOBOX's - AGREGAR PRODUCTO
    public void inicializarComponentesAgregarProducto(){
        for(Object o: inventario.obtenerMascotas()){
            cboMascota_AgregarProducto.addItem(o.toString().toUpperCase());
        }
        inicializarComponentesAgregarPComida();
        inicializarComponenetesAgregarPAccesorio();
        inicializarComponenetesAgregarPInsMedico();
    }
    public void inicializarComponentesAgregarPComida(){
        String categoria = "comida";
        Comida comida = (Comida) inventario.obtenerCategoria(categoria);
        for(String s: comida.obtener_marca()){
            cboMarca_AgregarProductoComida.addItem(s);
        }
        for(String s: comida.obtener_fabricante()){
            cboFabricante_AgregarProductoComida.addItem(s);
        }
        for(String s: comida.obtener_raza()){
            cboRaza_AgregarProductoComida.addItem(s);
        }
        for(String s: comida.obtener_sabor()){
            cboSabor_AgregarProductoComida.addItem(s);
        }
        for(String s: comida.obtener_contenedor()){
            cboContenedor_AgregarProductoComida.addItem(s);
        }
        for(String s: comida.obtener_etapa_de_vida()){
            cboEtapaDeVida_AgregarProductoComida.addItem(s);
        }
        for(String s: comida.obtener_tipo()){
            cboTipo_AgregarProductoComida.addItem(s);
        }
    }
    public void inicializarComponenetesAgregarPAccesorio(){
        String categoria = "accesorio";
        Accesorio accesorio = (Accesorio) inventario.obtenerCategoria(categoria);
        for(String s: accesorio.obtenerMarca()){
            cboMarca_AgregarProductoAccesorio.addItem(s);
        }
        for(String s: accesorio.obtenerFabricante()){
            cboFabricante_AgregarProductoAccesorio.addItem(s);
        }
        for(String s: accesorio.obtenerEtapaDeVida()){
            cboEtapaDeVida_AgregarProductoAccesorio.addItem(s);
        }
        for(String s: accesorio.obtenerTipo()){
            cboTipo_AgregarProductoAccesorio.addItem(s);
        }
    }
    public void inicializarComponenetesAgregarPInsMedico(){
        String categoria = "insumo médico";
        InsMedico insMedico = (InsMedico) inventario.obtenerCategoria(categoria);
        for(String s: insMedico.obtenerMarca()){
            cboMarca_AgregarProductoInsMedico.addItem(s);
        }
        for(String s: insMedico.obtenerFabricante()){
            cboFabricante_AgregarProductoInsMedico.addItem(s);
        }
        for(String s: insMedico.obtenerTipo()){
            cboTipo_AgregarProductoInsMedico.addItem(s);
        }
    }

    //INICIALIZAR COMBOBOX's - MODIFICAR/ELIMINAR PRODUCTO
    public void inicializarComponentesMEProducto(){
        inicializarComponentesMEComida();
        inicializarComponentesMEAccesorio();
        inicializarComponenetesMEInsMedico();
    }
    public void inicializarComponentesMEComida(){
        String categoria = "comida";
        Comida comida = (Comida) inventario.obtenerCategoria(categoria);
        for(String s: comida.obtener_marca()){
            cboMarca_ModificarProductoComida.addItem(s);
        }
        for(String s: comida.obtener_fabricante()){
            cboFabricante_ModificarProductoComida.addItem(s);
        }
        for(String s: comida.obtener_raza()){
            cboRaza_ModificarProductoComida.addItem(s);
        }
        for(String s: comida.obtener_sabor()){
            cboSabor_ModificarProductoComida.addItem(s);
        }
        for(String s: comida.obtener_contenedor()){
            cboContenedor_ModificarProductoComida.addItem(s);
        }
        for(String s: comida.obtener_etapa_de_vida()){
            cboEtapaDeVida_ModificarProductoComida.addItem(s);
        }
        for(String s: comida.obtener_tipo()){
            cboTipo_ModificarProductoComida.addItem(s);
        }
    }
    public void inicializarComponentesMEAccesorio(){
        String categoria = "accesorio";
        Accesorio accesorio = (Accesorio) inventario.obtenerCategoria(categoria);
        for(String s: accesorio.obtenerMarca()){
            cboMarca_ModificarProductoAccesorio.addItem(s);
        }
        for(String s: accesorio.obtenerFabricante()){
            cboFabricante_ModificarProductoAccesorio.addItem(s);
        }
        for(String s: accesorio.obtenerEtapaDeVida()){
            cboEtapaDeVida_ModificarProductoAccesorio.addItem(s);
        }
        for(String s: accesorio.obtenerTipo()){
            cboTipo_ModificarProductoAccesorio.addItem(s);
        }
    }
    public void inicializarComponenetesMEInsMedico(){
        String categoria = "insumo médico";
        InsMedico insMedico = (InsMedico) inventario.obtenerCategoria(categoria);
        for(String s: insMedico.obtenerMarca()){
            cboMarca_ModificarProductoInsMedico.addItem(s);
        }
        for(String s: insMedico.obtenerFabricante()){
            cboFabricante_ModificarProductoInsMedico.addItem(s);
        }
        for(String s: insMedico.obtenerTipo()){
            cboTipo_ModificarProductoInsMedico.addItem(s);
        }
    }

    //LIMPIAR PRODUCTOS
    public void limpiarComponentesAgregarProducto(){
        txtNombre_AgregarProducto.setText("");
        txtPrecio_AgregarProducto.setText("");
        txtDescuento_AgregarProducto.setText("");
        txtStock_AgregarProducto.setText("");
        txtDescripcion_AgregarProducto.setText("");
    }
    public void limpiarComponenetesAgregarPComida(){
        cboMarca_AgregarProductoComida.setSelectedIndex(0);
        cboFabricante_AgregarProductoComida.setSelectedIndex(0);
        cboRaza_AgregarProductoComida.setSelectedIndex(0);
        cboEtapaDeVida_AgregarProductoComida.setSelectedIndex(0);
        cboSabor_AgregarProductoComida.setSelectedIndex(0);
        cboContenedor_AgregarProductoComida.setSelectedIndex(0);
        cboTipo_AgregarProductoComida.setSelectedIndex(0);
    }
    public void limpiarComponenetesAgregarPAccesorio(){
        cboMarca_AgregarProductoAccesorio.setSelectedIndex(0);
        cboFabricante_AgregarProductoAccesorio.setSelectedIndex(0);
        cboTipo_AgregarProductoAccesorio.setSelectedIndex(0);
        cboEtapaDeVida_AgregarProductoAccesorio.setSelectedIndex(0);
    }
    public void limpiarComponentesAgregarPInsMedico(){
        cboMarca_AgregarProductoInsMedico.setSelectedIndex(0);
        cboFabricante_AgregarProductoInsMedico.setSelectedIndex(0);
        cboTipo_AgregarProductoInsMedico.setSelectedIndex(0);
    }

    //LISTA DE PRODUCTOS
    public void inicializarTablaProductos(DefaultTableModel modeloTabla){
        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Macota");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Descuento");
        modeloTabla.addColumn("Stock");
        modeloTabla.addColumn("Unidades vendidas");
        modeloTabla.addColumn("Calificación");
        modeloTabla.addColumn("Descripción");
    }
    public void inicializarComponentesListaProductos(){
        //INICIALIZAR COMBOBOX's
        cboFiltrar_ListaProductos.addItem("Ninguno");
        for(Object s: inventario.obtenerMascotas()){
            cboFiltrar_ListaProductos.addItem(s);
        }
    }
    public void actualizarTablaProductos(String categoria, DefaultTableModel modeloTabla){
        Object[] productos = filtrarProductos(categoria); //Filtra los productos según la especie de la mascota
        ordenarProductos(categoria,productos); //Ordena los prouctos segun lo seleccionado en el comboBox
        modeloTabla.setRowCount(0); //Vacia la tabla
        if(String.valueOf(cboMostrar_ListaProductos.getSelectedItem()).
                compareToIgnoreCase("Características generales")==0){
            for(Object o: productos){ //Muestra las caratrísticas generales del producto
                modeloTabla.addRow(((Producto)o).obtener_atributos_generales());
            }
        }else{
            if(categoria.equals("comida")){
                for(Object o: productos){ //Muestra las características específicas propios del producto comida
                    modeloTabla.addRow(((ProductoComida)o).obtener_atributos_especificos());
                }
            }
            if(categoria.equals("accesorio")){
                for(Object o: productos){ //Muestra las características específicas propios del producto comida
                    modeloTabla.addRow(((ProductoAccesorio)o).obtener_atributos_especificos());
                }
            }
            if(categoria.equals("insumo médico")){
                for(Object o: productos){ //Muestra las características específicas propios del producto comida
                    modeloTabla.addRow(((ProductoInsMedico)o).obtener_atributos_especificos());
                }
            }
        }
    }
    public Object[] filtrarProductos(String categoria){
        String filtro = String.valueOf(cboFiltrar_ListaProductos.getSelectedItem());
        Object[] productos;
        if(filtro.equals("Ninguno")){
            productos = inventario.obtenerCategoria(categoria).obtenerProductos();
        }else{
            productos = inventario.obtenerCategoria(categoria).filtrar_por_mascota(filtro.toLowerCase());
        }
        return productos;
    }
    public void ordenarProductos(String categoria, Object[] productos){
        if(cboOrdenar_ListaProductos.getSelectedIndex()==1){
            inventario.obtenerCategoria(categoria).ordenar_por_precio(productos);
        } else if (cboOrdenar_ListaProductos.getSelectedIndex()==2) {
            inventario.obtenerCategoria(categoria).ordenar_por_stock(productos);
        } else if (cboOrdenar_ListaProductos.getSelectedIndex()==3) {
            inventario.obtenerCategoria(categoria).ordenar_por_unidades_vendidas(productos);
        }else if(cboOrdenar_ListaProductos.getSelectedIndex()==4){
            inventario.obtenerCategoria(categoria).ordenar_por_calificacion(productos);
        }
    }

    //INTERACCIÓN CON EL CLIENTE
    public void mensajeInfo(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public void mensajeError(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje,"Error",JOptionPane.ERROR_MESSAGE);
    }

    //METODOS AUXILIARES
    public boolean verificarCamposDeTexto(String[] campos_de_texto){
        boolean aux = false;
        for(String s: campos_de_texto){
            aux = aux || s.isEmpty();
        }
        return aux;
    }
    public Producto buscarProducto(String categoria, JTextField campo_de_texto){
        try {
            Id identificador = new Id(campo_de_texto.getText());
            Producto producto = inventario.buscarProducto(categoria,identificador);
            return producto;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    //LISTA DE PRODUCTOS COMIDA
    public void inicializarTablaPComida(){
        tablaDeProductos.setEnabled(false);
        modeloTablaPComida.setColumnCount(0);
        if(String.valueOf(cboMostrar_ListaProductos.getSelectedItem())
                .compareToIgnoreCase("Características generales")==0){
            inicializarTablaProductos(modeloTablaPComida);
        }else{
            modeloTablaPComida.addColumn("Id");
            modeloTablaPComida.addColumn("Marca");
            modeloTablaPComida.addColumn("Fabricante");
            modeloTablaPComida.addColumn("Raza");
            modeloTablaPComida.addColumn("Sabor");
            modeloTablaPComida.addColumn("Contenedor");
            modeloTablaPComida.addColumn("Etapa de vida");
            modeloTablaPComida.addColumn("Tipo");
        }
        tablaDeProductos.setModel(modeloTablaPComida);
    }
    //LISTA DE PRRODUCTOS ACCESORIO
    public  void inicializarTablaPAccesorio(){
        tablaPAccesorio.setEnabled(false);
        modeloTablaPAccesorio.setColumnCount(0);
        if(String.valueOf(cboMostrar_ListaProductos.getSelectedItem())
                .compareToIgnoreCase("Características generales")==0){
            inicializarTablaProductos(modeloTablaPAccesorio);
        }else{
            modeloTablaPAccesorio.addColumn("Id");
            modeloTablaPAccesorio.addColumn("Marca");
            modeloTablaPAccesorio.addColumn("Fabricante");
            modeloTablaPAccesorio.addColumn("Etapa de vida");
            modeloTablaPAccesorio.addColumn("Tipo");
        }
        tablaPAccesorio.setModel(modeloTablaPAccesorio);
    }
    //LISTA DE PRODUCTOS INSUMO MÉDICO
    public  void inicializarTablaPInsMedico(){
        tablaPInsMedico.setEnabled(false);
        modeloTablaPInsMedico.setColumnCount(0);
        if(String.valueOf(cboMostrar_ListaProductos.getSelectedItem())
                .compareToIgnoreCase("Características generales")==0){
            inicializarTablaProductos(modeloTablaPInsMedico);
        }else{
            modeloTablaPInsMedico.addColumn("Id");
            modeloTablaPInsMedico.addColumn("Marca");
            modeloTablaPInsMedico.addColumn("Fabricante");
            modeloTablaPInsMedico.addColumn("Tipo");
        }
        tablaPInsMedico.setModel(modeloTablaPInsMedico);
    }
}
