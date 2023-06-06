package SistemaDeCompras;

import javax.swing.*;

public class VentanaCompra {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JList list1;
    private JComboBox comboBoxFiltros;
    private JTable tableComida;
    private JButton filtrarButton;
    private JSpinner spinner1;
    private JButton añadirACarritoButton;
    private JList listProductosEnCarro;
    private JList listProductosDisponiblesPorCategoria;
    private JComboBox comboBoxCategorias;
    private JButton filtrarCategoriaButton;
    private JButton anteriorButton;
    private JButton siguienteButton;
    private JButton másInformaciónButton;


    public VentanaCompra() {
           }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaCompra");
        frame.setContentPane(new VentanaCompra().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
