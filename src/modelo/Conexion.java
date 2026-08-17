/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {

    Connection con;

    public Connection conectar() {

        try {
            String url = "jdbc:mysql://127.0.0.1:3306/control_ventilacion_granja";
            String usuario = "root";
            String clave = "";

            con = DriverManager.getConnection(url, usuario, clave);

            System.out.println("Conexion exitosa");

        } catch (SQLException e) {
            System.out.println("Error de conexion: " + e);
        }

        return con;
    }
}