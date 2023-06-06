package SistemaDeCompras;

import SistemaDeCompras.ElementosDeLaInterfazDeCompras.TableModels.ComprarComidaTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCompra {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPaneICategorias;
    private JTable tableInsMedicos;
    private JTable tableAccesorios;
    private JTable tableComida;
    private JButton filtrarButton;
    private ComprarComidaTableModel tableComidaModel = new ComprarComidaTableModel();


    public VentanaCompra() {
        tableComida.setModel(tableComidaModel);
        filtrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaCompra");
        frame.setContentPane(new VentanaCompra().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
