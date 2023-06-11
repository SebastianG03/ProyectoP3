import Inventario.Categoria.*;
import Producto.*;
import Inventario.Inventario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
    private JButton btn_agregar_comida;
    private JButton btn_limpiar_agregar_comida;
    private JTable tabla_producto_comida;
    private JComboBox cbo_filtro_comida;
    private JComboBox cboOrdenPComida;
    private JButton btnFiltroPComida;
    private JTextField txt_identificador_producto_comida;
    private JButton btn_buscar_modificar_comida;
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
    private JButton btn_limpiar_modificar_comida;
    private JButton btnModificarPComida;
    private JTextField txtEliminarPComida;
    private JButton btnEliminarPComida;
    private JButton btn_cancelar_modificar_comida;
    private JComboBox cbo_mostrar_productos_comida;
    private JTextField txtBuscarPComida;
    private JButton btnBuscarPComida;
    private JButton btn_cancelar_busqueda_comida;
    private JButton btn_consultar_producto_comida;
    private Inventario inventario = new Inventario();
    private DefaultTableModel modelo_tabla_comida = new DefaultTableModel();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().Principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(780,650);
        frame.setVisible(true);
    }

    //CONSTRUCTOR
    public Ventana() {
        //INICIALIZAR - COMIDA
        inicializar_tabla_comida();
        inicializar_componentes_agregar_comida();
        inicializar_componentes_modificar_comida();
        inicializar_componentes_lista_productos();
        //BOTONES - COMIDA
            //AGREGAR PRODUCTO - COMIDA
        btn_agregar_comida.addActionListener(new ActionListener() {
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

            String[] campos_de_texto = {especie,nombre,precio,descuento,stock,descripcion,marca,fabricante,raza,
            sabor,contenedor,etapaDeVida,tipo};

            if(verificar_campos_de_texto(campos_de_texto)){
                JOptionPane.showMessageDialog(null,"Campos Vacíos",
                        "Error",JOptionPane.ERROR_MESSAGE);
            }else{
                try{
                    double precioDouble = Double.parseDouble(precio);
                    double descuentoDouble = Double.parseDouble(descuento);
                    int stockInt = Integer.parseInt(stock);
                    ProductoComida nuevoProducto = new ProductoComida(categoria,especie,nombre,precioDouble,descuentoDouble,
                            stockInt,descripcion,marca,fabricante,raza,sabor,contenedor,etapaDeVida,tipo);
                    if(inventario.agregar_producto_comida(nuevoProducto)){
                        actualizar_tabla_comida(filtrar_productos_comida());
                        JOptionPane.showMessageDialog(null,"Producto agregado");
                        limpiar_componentes_agregar_comida();
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Formato equivocado",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
            });
        btn_limpiar_agregar_comida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar_componentes_agregar_comida();
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

                String[] campos_de_texto = {nombre,precio,descuento,stock,descripcion,marca,fabricante,raza,
                        sabor,contenedor,etapaDeVida,tipo};

                if(verificar_campos_de_texto(campos_de_texto)){
                    JOptionPane.showMessageDialog(null,"Campos Vacíos",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    try {
                        double precioDouble = Double.parseDouble(precio);
                        double descuentoDouble = Double.parseDouble(descuento);
                        int stockInt = Integer.parseInt(stock);
                        Id identificador = new Id(txt_identificador_producto_comida.getText());
                        inventario.modificar_producto_comida(identificador,nombre,precioDouble,descuentoDouble,
                                stockInt,descripcion,marca,fabricante,raza,sabor,contenedor,etapaDeVida,tipo);
                        habilitar_componentes_modificar_comida(false);
                        txt_identificador_producto_comida.setEnabled(true);
                        btn_buscar_modificar_comida.setEnabled(true);
                        JOptionPane.showMessageDialog(null,"Producto modificado");
                        limpiar_componentes_modificar_comida();
                        actualizar_tabla_comida(filtrar_productos_comida());
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null,"Formato incorrecto",
                                "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        btn_buscar_modificar_comida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto producto = buscar_producto_comida(txt_identificador_producto_comida);
                if(producto!=null){
                    txtModificarNombrePComida.setText(producto.obtener_nombre());
                    txtModificarPrecioPComida.setText(String.valueOf(producto.obtener_precio()));
                    txtModificarDescuentoPComida.setText(String.valueOf(producto.obtener_descuento()));
                    spnModificarStockPComida.setValue(producto.obtener_stock());
                    txtModificarDescripcionPComida.setText(producto.obtener_descripcion());
                    cboModificarMarcaPComida.setSelectedItem(((ProductoComida)producto).obtener_marca());
                    cboModificarFabricantePComida.setSelectedItem(((ProductoComida)producto).obtener_fabricante());
                    cboModificarRazaPComida.setSelectedItem(((ProductoComida)producto).obtener_raza());
                    cboModificarEtapaDeVidaPComida.setSelectedItem(((ProductoComida)producto).obtener_etapa_de_vida());
                    cboModificarSaborPComida.setSelectedItem(((ProductoComida)producto).obtener_sabor());
                    cboModificarContenedorPComida.setSelectedItem(((ProductoComida)producto).obtener_contenedor());
                    cboModificarTipoPComida.setSelectedItem(((ProductoComida)producto).obtener_tipo());
                    habilitar_componentes_modificar_comida(true);
                }
            }
        });
        btn_limpiar_modificar_comida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar_componentes_modificar_comida();
            }
        });
        btn_cancelar_modificar_comida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar_componentes_modificar_comida();
                habilitar_componentes_modificar_comida(false);
            }
        });
            //ELIMINAR PRODCUTO - COMIDA
        btnEliminarPComida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Id identificador = new Id(txtEliminarPComida.getText());

                    inventario.obtener_categoria_comida().eliminar_producto(identificador);
                    actualizar_tabla_comida(filtrar_productos_comida());
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
                Producto producto = buscar_producto_comida(txtBuscarPComida);
                if(producto!=null){
                    modelo_tabla_comida.setRowCount(0);
                    if(cbo_mostrar_productos_comida.getSelectedItem().equals("Características generales")){
                        modelo_tabla_comida.addRow(producto.obtener_atributos_generales());
                    }else{
                        modelo_tabla_comida.addRow(((ProductoComida)producto).obtener_atributos_especificos());
                    }
                    tabla_producto_comida.setModel(modelo_tabla_comida);
                    btn_cancelar_busqueda_comida.setEnabled(true);
                    btn_consultar_producto_comida.setEnabled(true);
                    cbo_filtro_comida.setSelectedItem("Ninguno");
                    cbo_filtro_comida.setEnabled(false);
                    cboOrdenPComida.setSelectedItem("Ninguno");
                    cboOrdenPComida.setEnabled(false);
                }
            }
        });
        btn_cancelar_busqueda_comida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtBuscarPComida.setText("");
                actualizar_tabla_comida(filtrar_productos_comida());
                btn_cancelar_busqueda_comida.setEnabled(false);
                btn_consultar_producto_comida.setEnabled(false);
                cbo_filtro_comida.setEnabled(true);
                cboOrdenPComida.setEnabled(true);
            }
        });
        btn_consultar_producto_comida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto producto = buscar_producto_comida(txtBuscarPComida);
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
                inicializar_tabla_comida();
                actualizar_tabla_comida(filtrar_productos_comida());
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
        txt_identificador_producto_comida.addKeyListener(new KeyAdapter() {
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
    public boolean verificar_campos_de_texto(String[] campos_de_texto){
        boolean aux = false;
        for(String s: campos_de_texto){
            aux = aux || s.isEmpty();
        }
        return aux;
    }
        //AGREGAR PRODUCTO - COMIDA
    public void inicializar_componentes_agregar_comida(){
        Comida comida = inventario.obtener_categoria_comida();
        //INICIALIZAR COMBOBOX's
        for(Object o: comida.obtener_mascotas()){
            cboAgregarEspeciePComida.addItem(o.toString());
        }
        for(String s: comida.obtener_marca()){
            cboAgregarMarcaPComida.addItem(s);
        }
        for(String s: comida.obtener_fabricante()){
            cboAgregarFabricantePComida.addItem(s);
        }
        for(String s: comida.obtener_raza()){
            cboAgregarRazaPComida.addItem(s);
        }
        for(String s: comida.obtener_sabor()){
            cboAgregarSaborPComida.addItem(s);
        }
        for(String s: comida.obtener_contenedor()){
            cboAgregarContenedorPComiad.addItem(s);
        }
        for(String s: comida.obtener_etapa_de_vida()){
            cboAgregarEtapaDeVidaPComida.addItem(s);
        }
        for(String s: comida.obtener_tipo()){
            cboAgregarTipoPComida.addItem(s);
        }
    }
    public void limpiar_componentes_agregar_comida(){
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
        //MODIFICAR PRODUCTO - COMIDA
    public void habilitar_componentes_modificar_comida(boolean b){
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
            btn_limpiar_modificar_comida.setEnabled(b);
            btnModificarPComida.setEnabled(b);
            btn_cancelar_modificar_comida.setEnabled(b);
            btn_buscar_modificar_comida.setEnabled(!b);
            txt_identificador_producto_comida.setEnabled(!b);
    }
    public void inicializar_componentes_modificar_comida(){
        habilitar_componentes_modificar_comida(false);
        //INICIALIZAR COMBOBOX's
        Comida comida = inventario.obtener_categoria_comida();
        for(String s: comida.obtener_marca()){
            cboModificarMarcaPComida.addItem(s);
        }
        for(String s: comida.obtener_fabricante()){
            cboModificarFabricantePComida.addItem(s);
        }
        for(String s: comida.obtener_raza()){
            cboModificarRazaPComida.addItem(s);
        }
        for(String s: comida.obtener_sabor()){
            cboModificarSaborPComida.addItem(s);
        }
        for(String s: comida.obtener_contenedor()){
            cboModificarContenedorPComida.addItem(s);
        }
        for(String s: comida.obtener_etapa_de_vida()){
            cboModificarEtapaDeVidaPComida.addItem(s);
        }
        for(String s: comida.obtener_tipo()){
            cboModificarTipoPComida.addItem(s);
        }
    }
    public void limpiar_componentes_modificar_comida(){
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
    public void inicializar_componentes_lista_productos(){
        //INICIALIZAR COMBOBOX's
        cbo_filtro_comida.addItem("Ninguno");
        for(Object s: inventario.obtener_categoria_comida().obtener_mascotas()){
            cbo_filtro_comida.addItem(s);
        }
        actualizar_tabla_comida(filtrar_productos_comida());
        btn_cancelar_busqueda_comida.setEnabled(false);
        btn_consultar_producto_comida.setEnabled(false);
    }
    public Producto buscar_producto_comida(JTextField campo_de_texto){
        try {
            Id identificador = new Id(campo_de_texto.getText());
            Producto producto = inventario.buscar_producto_comida(identificador);
            return producto;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
        //MOSTRAR PRODUCTOS = COMIDA
    public void inicializar_tabla_comida(){
        tabla_producto_comida.setEnabled(false);
        modelo_tabla_comida.setColumnCount(0);
        if(String.valueOf(cbo_mostrar_productos_comida.getSelectedItem())
                .compareToIgnoreCase("Características generales")==0){
            modelo_tabla_comida.addColumn("Id");
            modelo_tabla_comida.addColumn("Nombre");
            modelo_tabla_comida.addColumn("Macota");
            modelo_tabla_comida.addColumn("Precio");
            modelo_tabla_comida.addColumn("Descuento");
            modelo_tabla_comida.addColumn("Stock");
            modelo_tabla_comida.addColumn("Unidades vendidas");
            modelo_tabla_comida.addColumn("Calificación");
            modelo_tabla_comida.addColumn("Descripción");
        }else{
            modelo_tabla_comida.addColumn("Id");
            modelo_tabla_comida.addColumn("Marca");
            modelo_tabla_comida.addColumn("Fabricante");
            modelo_tabla_comida.addColumn("Raza");
            modelo_tabla_comida.addColumn("Sabor");
            modelo_tabla_comida.addColumn("Contenedor");
            modelo_tabla_comida.addColumn("Etapa de vida");
            modelo_tabla_comida.addColumn("Tipo");
        }
        tabla_producto_comida.setModel(modelo_tabla_comida);
    }
    public void actualizar_tabla_comida(Object[] productos){
        modelo_tabla_comida.setRowCount(0);
        if(String.valueOf(cbo_mostrar_productos_comida.getSelectedItem()).
                compareToIgnoreCase("Características generales")==0){
            for(Object o: productos){
                modelo_tabla_comida.addRow(((ProductoComida)o).obtener_atributos_generales());
            }
        }else{
            for(Object o: productos){
                modelo_tabla_comida.addRow(((ProductoComida)o).obtener_atributos_especificos());
            }
        }
    }
    public Object[] filtrar_productos_comida(){
        modelo_tabla_comida.setRowCount(0);
        String filtro = String.valueOf(cbo_filtro_comida.getSelectedItem());
        Object[] productos;
        if(!txtBuscarPComida.getText().isEmpty()){
            Producto producto = buscar_producto_comida(txtBuscarPComida);
            return new Object[] {producto};
        }
        if(filtro.equals("Ninguno")){
            productos = inventario.obtener_categoria_comida().obtenerProductos();
        }else{
            productos = inventario.obtener_categoria_comida().filtrar_por_mascota(filtro);
        }
        ordenar_productos_comida(productos);
        return productos;
    }
    public void ordenar_productos_comida(Object[] productos){
        if(cboOrdenPComida.getSelectedIndex()==1){
            inventario.obtener_categoria_comida().ordenar_por_precio(productos);
        } else if (cboOrdenPComida.getSelectedIndex()==2) {
            inventario.obtener_categoria_comida().ordenar_por_stock(productos);
        } else if (cboOrdenPComida.getSelectedIndex()==3) {
            inventario.obtener_categoria_comida().ordenar_por_unidades_vendidas(productos);
        }else if(cboOrdenPComida.getSelectedIndex()==4){
            inventario.obtener_categoria_comida().ordenar_por_calificacion(productos);
        }
    }
}
