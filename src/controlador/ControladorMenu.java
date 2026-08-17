/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.MenuPrincipal;
import vista.VistaArea;
import vista.VistaTemperatura;
import vista.AcercaDe;

public class ControladorMenu implements ActionListener {

    MenuPrincipal vista;

    public ControladorMenu(MenuPrincipal vista) {

        this.vista = vista;

        this.vista.btnAreas.addActionListener(this);
        this.vista.btnTemperaturas.addActionListener(this);
        this.vista.btnAcerca.addActionListener(this);
        this.vista.btnSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnAreas) {

    vista.setVisible(false);

    VistaArea vistaArea = new VistaArea();
    ControladorArea controladorArea =
            new ControladorArea(vistaArea);

    vistaArea.setVisible(true);
    vistaArea.setLocationRelativeTo(null);

    vistaArea.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);

    vistaArea.addWindowListener(new java.awt.event.WindowAdapter() {

        @Override
        public void windowClosed(java.awt.event.WindowEvent e) {
            vista.setVisible(true);
        }
    });
}

        if (e.getSource() == vista.btnTemperaturas) {

    vista.setVisible(false);

    VistaTemperatura vistaTemperatura =
            new VistaTemperatura();

    ControladorTemperatura controladorTemperatura =
            new ControladorTemperatura(vistaTemperatura);

    vistaTemperatura.setVisible(true);
    vistaTemperatura.setLocationRelativeTo(null);

    vistaTemperatura.setDefaultCloseOperation(
            javax.swing.JFrame.DISPOSE_ON_CLOSE
    );

    vistaTemperatura.addWindowListener(
            new java.awt.event.WindowAdapter() {

        @Override
        public void windowClosed(java.awt.event.WindowEvent e) {
            vista.setVisible(true);
        }
    });
}
        
        if (e.getSource() == vista.btnAcerca) {

        AcercaDe acerca = new AcercaDe();

        acerca.setVisible(true);
        acerca.setLocationRelativeTo(null);
        
      
    }

        if (e.getSource() == vista.btnSalir) {
            System.exit(0);
        }
    }
}