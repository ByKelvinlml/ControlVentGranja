/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Area;
import modelo.AreaDAO;
import vista.VistaArea;

public class ControladorArea implements ActionListener {

    AreaDAO dao = new AreaDAO();
    Area area = new Area();
    VistaArea vista;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorArea(VistaArea vista) {

        this.vista = vista;

        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);

        listar(vista.tblAreas);

        vista.btnActualizar.setEnabled(false);
    }

    public void listar(JTable tabla) {

        limpiarTabla();

        modelo = (DefaultTableModel) tabla.getModel();

        List<Area> lista = dao.listar();

        Object[] fila = new Object[4];

        for (int i = 0; i < lista.size(); i++) {

            fila[0] = lista.get(i).getId();
            fila[1] = lista.get(i).getNombre();
            fila[2] = lista.get(i).getDescripcion();
            fila[3] = lista.get(i).getCapacidad();

            modelo.addRow(fila);
        }

        tabla.setModel(modelo);
    }

    public void guardar() {

        try {

            String nombre = vista.txtNombre.getText();
            String descripcion = vista.txtDescripcion.getText();
            int capacidad = Integer.parseInt(vista.txtCapacidad.getText());

            if (nombre.equals("") || descripcion.equals("")) {

                JOptionPane.showMessageDialog(
                        vista,
                        "Debe completar todos los campos"
                );

            } else {

                area.setNombre(nombre);
                area.setDescripcion(descripcion);
                area.setCapacidad(capacidad);

                int respuesta = dao.agregar(area);

                if (respuesta == 1) {

                    JOptionPane.showMessageDialog(
                            vista,
                            "Area guardada correctamente"
                    );

                    limpiarCampos();
                    listar(vista.tblAreas);

                } else {

                    JOptionPane.showMessageDialog(
                            vista,
                            "Error al guardar el area"
                    );
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    vista,
                    "La capacidad debe ser un numero"
            );
        }
    }

    public void editar() {

        int fila = vista.tblAreas.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Debe seleccionar un registro"
            );

        } else {

            vista.txtID.setText(
                    vista.tblAreas.getValueAt(fila, 0).toString()
            );

            vista.txtNombre.setText(
                    vista.tblAreas.getValueAt(fila, 1).toString()
            );

            vista.txtDescripcion.setText(
                    vista.tblAreas.getValueAt(fila, 2).toString()
            );

            vista.txtCapacidad.setText(
                    vista.tblAreas.getValueAt(fila, 3).toString()
            );

            vista.btnGuardar.setEnabled(false);
            vista.btnActualizar.setEnabled(true);
        }
    }
     public void actualizar() {

    try {

        int id = Integer.parseInt(vista.txtID.getText());

        String nombre = vista.txtNombre.getText();
        String descripcion = vista.txtDescripcion.getText();

        int capacidad =
                Integer.parseInt(vista.txtCapacidad.getText());

        area.setId(id);
        area.setNombre(nombre);
        area.setDescripcion(descripcion);
        area.setCapacidad(capacidad);

        int respuesta = dao.actualizar(area);

        if (respuesta == 1) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Area actualizada correctamente"
            );

            limpiarCampos();
            listar(vista.tblAreas);

            vista.btnGuardar.setEnabled(true);
            vista.btnActualizar.setEnabled(false);
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                vista,
                "Verifique los datos ingresados"
        );
    }
}
    
    public void eliminar() {

    int fila = vista.tblAreas.getSelectedRow();

    if (fila == -1) {

        JOptionPane.showMessageDialog(
                vista,
                "Debe seleccionar un registro"
        );

    } else {

        int opcion = JOptionPane.showConfirmDialog(
                vista,
                "¿Desea eliminar esta area?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {

            int id = Integer.parseInt(
                    vista.tblAreas.getValueAt(fila, 0).toString()
            );

            dao.eliminar(id);

            JOptionPane.showMessageDialog(
                    vista,
                    "Area eliminada correctamente"
            );

            listar(vista.tblAreas);
        }
    }
}
    public void buscar() {

    limpiarTabla();

    modelo =
            (DefaultTableModel) vista.tblAreas.getModel();

    List<Area> lista =
            dao.buscar(vista.txtBuscar.getText());

    Object[] fila = new Object[4];

    for (int i = 0; i < lista.size(); i++) {

        fila[0] = lista.get(i).getId();
        fila[1] = lista.get(i).getNombre();
        fila[2] = lista.get(i).getDescripcion();
        fila[3] = lista.get(i).getCapacidad();

        modelo.addRow(fila);
    }
}

    public void limpiarCampos() {

        vista.txtID.setText("");
        vista.txtNombre.setText("");
        vista.txtDescripcion.setText("");
        vista.txtCapacidad.setText("");
    }

    public void limpiarTabla() {

        modelo = (DefaultTableModel) vista.tblAreas.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    @Override
public void actionPerformed(ActionEvent e) {

    if (e.getSource() == vista.btnGuardar) {
        guardar();
    }

    if (e.getSource() == vista.btnEditar) {
        editar();
    }

    if (e.getSource() == vista.btnActualizar) {
        actualizar();
    }

    if (e.getSource() == vista.btnEliminar) {
        eliminar();
    }

    if (e.getSource() == vista.btnBuscar) {
        buscar();
    }

    if (e.getSource() == vista.btnCancelar) {

        limpiarCampos();

        vista.txtBuscar.setText("");

        vista.btnGuardar.setEnabled(true);
        vista.btnActualizar.setEnabled(false);

        listar(vista.tblAreas);
    }
}
}