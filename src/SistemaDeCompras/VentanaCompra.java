package SistemaDeCompras;

import SistemaDeCompras.ElementosDeLaInterfazDeCompras.TableModels.ComprarComidaTableModel;

import javax.swing.*;

public class VentanaCompra {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPaneICategorias;
    private JTable tableInsMedicos;
    private JTable tableAccesorios;
    private JTable tableComida;

    private void createUIComponents() {
        tableComida = new JTable();
        ComprarComidaTableModel tableModel = new ComprarComidaTableModel();
        tableComida.setModel(tableModel);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaCompra");
        frame.setContentPane(new VentanaCompra().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
