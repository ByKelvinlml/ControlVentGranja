/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controlventilaciongranja;

import controlador.ControladorLogin;
import vista.LoginV;

public class ControlVentilacionGranja {

    public static void main(String[] args) {

        LoginV vista = new LoginV();
        ControladorLogin controlador = new ControladorLogin(vista);

        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
    }
}