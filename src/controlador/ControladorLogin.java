/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.LoginDAO;
import vista.LoginV;
import vista.MenuPrincipal;


public class ControladorLogin implements ActionListener {

    LoginDAO dao = new LoginDAO();
    LoginV vista;

    public ControladorLogin(LoginV vista) {

        this.vista = vista;

        this.vista.getBtnIngresar().addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
    }

    public void ingresar() {

        String usuario = vista.txtUsuario.getText();
        String clave = new String(vista.txtClave.getPassword());

        if (usuario.equals("") || clave.equals("")) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Debe completar todos los campos"
            );

        } else {

            if (dao.realizarLogin(usuario, clave)) {

    JOptionPane.showMessageDialog(
            vista,
            "Inicio de sesion correcto"
    );

    vista.setVisible(false);

    MenuPrincipal menu = new MenuPrincipal();
    ControladorMenu controladorMenu = new ControladorMenu(menu);

    menu.setVisible(true);
    menu.setLocationRelativeTo(null);
}
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnIngresar) {
            ingresar();
        }

        if (e.getSource() == vista.btnCancelar) {
            System.exit(0);
        }
    }
}