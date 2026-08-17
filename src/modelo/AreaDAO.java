/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AreaDAO {

    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Area> listar() {

        List<Area> lista = new ArrayList<>();

        String sql = "SELECT * FROM areas";

        try {

            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                Area area = new Area();

                area.setId(rs.getInt("id"));
                area.setNombre(rs.getString("nombre"));
                area.setDescripcion(rs.getString("descripcion"));
                area.setCapacidad(rs.getInt("capacidad"));

                lista.add(area);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar areas: " + e);
        }

        return lista;
    }

    public int agregar(Area area) {

        String sql = "INSERT INTO areas(nombre, descripcion, capacidad) VALUES (?, ?, ?)";

        try {

            con = conexion.conectar();
            ps = con.prepareStatement(sql);

            ps.setString(1, area.getNombre());
            ps.setString(2, area.getDescripcion());
            ps.setInt(3, area.getCapacidad());

            ps.executeUpdate();

            return 1;

        } catch (SQLException e) {

            System.out.println("Error al agregar area: " + e);
            return 0;
        }
    }
    
    public int actualizar(Area area) {

    String sql = "UPDATE areas SET nombre=?, descripcion=?, capacidad=? WHERE id=?";

    try {

        con = conexion.conectar();
        ps = con.prepareStatement(sql);

        ps.setString(1, area.getNombre());
        ps.setString(2, area.getDescripcion());
        ps.setInt(3, area.getCapacidad());
        ps.setInt(4, area.getId());

        return ps.executeUpdate();

    } catch (SQLException e) {

        System.out.println("Error al actualizar area: " + e);

        return 0;
    }
}
    public int eliminar(int id) {

    String sql = "DELETE FROM areas WHERE id=?";

    try {

        con = conexion.conectar();
        ps = con.prepareStatement(sql);

        ps.setInt(1, id);

        return ps.executeUpdate();

    } catch (SQLException e) {

        System.out.println("Error al eliminar area: " + e);

        return 0;
    }
}
    public List<Area> buscar(String valor) {

    List<Area> lista = new ArrayList<>();

    String sql =
            "SELECT * FROM areas "
            + "WHERE nombre LIKE ? "
            + "OR descripcion LIKE ?";

    try {

        con = conexion.conectar();
        ps = con.prepareStatement(sql);

        ps.setString(1, "%" + valor + "%");
        ps.setString(2, "%" + valor + "%");

        rs = ps.executeQuery();

        while (rs.next()) {

            Area area = new Area();

            area.setId(rs.getInt("id"));
            area.setNombre(rs.getString("nombre"));
            area.setDescripcion(rs.getString("descripcion"));
            area.setCapacidad(rs.getInt("capacidad"));

            lista.add(area);
        }

    } catch (SQLException e) {

        System.out.println("Error al buscar area: " + e);
    }

    return lista;
}
   
}