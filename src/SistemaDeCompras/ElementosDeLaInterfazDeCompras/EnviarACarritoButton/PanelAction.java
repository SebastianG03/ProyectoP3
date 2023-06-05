package SistemaDeCompras.ElementosDeLaInterfazDeCompras.EnviarACarritoButton;

import Producto.Id;
import SistemaDeCompras.ClasesDelSistema.Carrito;
import SistemaDeCompras.ElementosDeLaInterfazDeCompras.TableModels.TableActionEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Clase temporal, se intentará utilizar el botón sin el panel.
public class PanelAction extends Container {

    private ActionButton buttonAddToBuy = new ActionButton();

    public PanelAction() {

        GroupLayout layout = new GroupLayout(this);
        //
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonAddToBuy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(buttonAddToBuy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    public void initEvent(TableActionEvent event, int row, Carrito carrito, Id id, int cantidad) {
        buttonAddToBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.addToBuy(carrito, id, cantidad);
            }
        });
    }


    
}
