/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean realizarLogin(String usuario, String clave) {

        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND clave = ?";

        try {

            con = conexion.conectar();
            ps = con.prepareStatement(sql);

            ps.setString(1, usuario);
            ps.setString(2, clave);

            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {

            System.out.println("Error en login: " + e);
        }

        return false;
    }
}