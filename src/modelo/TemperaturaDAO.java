/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TemperaturaDAO {

    Conexion conexion = new Conexion();

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Temperatura> listar() {

        List<Temperatura> lista = new ArrayList<>();

        String sql =
        "SELECT t.id, a.nombre AS area, t.temperatura, "
        + "t.estado, t.ventilacion, t.fecha "
        + "FROM temperaturas t "
        + "INNER JOIN areas a ON t.id_area = a.id";

        try {

            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

           while (rs.next()) {

        Temperatura t = new Temperatura();

        t.setId(rs.getInt("id"));
        t.setNombreArea(rs.getString("area"));
        t.setTemperatura(rs.getDouble("temperatura"));
        t.setEstado(rs.getString("estado"));
        t.setVentilacion(rs.getString("ventilacion"));
        t.setFecha(rs.getString("fecha"));

        lista.add(t);
    }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar temperaturas: " + e
            );
        }

        return lista;
    }

    public int agregar(Temperatura t) {

        String sql =
                "INSERT INTO temperaturas "
                + "(id_area, temperatura, estado, ventilacion, fecha) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {

            con = conexion.conectar();
            ps = con.prepareStatement(sql);

            ps.setInt(1, t.getIdArea());
            ps.setDouble(2, t.getTemperatura());
            ps.setString(3, t.getEstado());
            ps.setString(4, t.getVentilacion());
            ps.setString(5, t.getFecha());

            return ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println(
                    "Error al guardar temperatura: " + e
            );

            return 0;
        }
    }
    
    public int guardarOActualizar(Temperatura t) {

    String verificar =
            "SELECT id FROM temperaturas WHERE id_area = ?";

    try {

        con = conexion.conectar();

        ps = con.prepareStatement(verificar);
        ps.setInt(1, t.getIdArea());

        rs = ps.executeQuery();

        if (rs.next()) {

            // Ya existe una temperatura para esa area
            int id = rs.getInt("id");

            String sql =
                    "UPDATE temperaturas "
                    + "SET temperatura=?, estado=?, ventilacion=?, fecha=? "
                    + "WHERE id=?";

            ps = con.prepareStatement(sql);

            ps.setDouble(1, t.getTemperatura());
            ps.setString(2, t.getEstado());
            ps.setString(3, t.getVentilacion());
            ps.setString(4, t.getFecha());
            ps.setInt(5, id);

            return ps.executeUpdate();

        } else {

            // No existe una temperatura para esa area
            String sql =
                    "INSERT INTO temperaturas "
                    + "(id_area, temperatura, estado, ventilacion, fecha) "
                    + "VALUES (?, ?, ?, ?, ?)";

            ps = con.prepareStatement(sql);

            ps.setInt(1, t.getIdArea());
            ps.setDouble(2, t.getTemperatura());
            ps.setString(3, t.getEstado());
            ps.setString(4, t.getVentilacion());
            ps.setString(5, t.getFecha());

            return ps.executeUpdate();
        }

    } catch (SQLException e) {

        System.out.println(
                "Error al guardar o actualizar temperatura: " + e
        );

        return 0;
    }
}

    public int actualizar(Temperatura t) {

        String sql =
                "UPDATE temperaturas "
                + "SET id_area=?, temperatura=?, estado=?, "
                + "ventilacion=?, fecha=? "
                + "WHERE id=?";

        try {

            con = conexion.conectar();
            ps = con.prepareStatement(sql);

            ps.setInt(1, t.getIdArea());
            ps.setDouble(2, t.getTemperatura());
            ps.setString(3, t.getEstado());
            ps.setString(4, t.getVentilacion());
            ps.setString(5, t.getFecha());
            ps.setInt(6, t.getId());

            return ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println(
                    "Error al actualizar temperatura: " + e
            );

            return 0;
        }
    }

    public int eliminar(int id) {

        String sql =
                "DELETE FROM temperaturas WHERE id=?";

        try {

            con = conexion.conectar();
            ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            return ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println(
                    "Error al eliminar temperatura: " + e
            );

            return 0;
        }
    }

    public List<Temperatura> buscar(String valor) {

        List<Temperatura> lista = new ArrayList<>();

        String sql =
                "SELECT * FROM temperaturas "
                + "WHERE estado LIKE ? "
                + "OR ventilacion LIKE ?";

        try {

            con = conexion.conectar();
            ps = con.prepareStatement(sql);

            ps.setString(1, "%" + valor + "%");
            ps.setString(2, "%" + valor + "%");

            rs = ps.executeQuery();

            while (rs.next()) {

                Temperatura t = new Temperatura();

                t.setId(rs.getInt("id"));
                t.setIdArea(rs.getInt("id_area"));
                t.setTemperatura(rs.getDouble("temperatura"));
                t.setEstado(rs.getString("estado"));
                t.setVentilacion(rs.getString("ventilacion"));
                t.setFecha(rs.getString("fecha"));

                lista.add(t);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al buscar temperatura: " + e
            );
        }

        return lista;
    }
}