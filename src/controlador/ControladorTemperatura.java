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
import modelo.Temperatura;
import modelo.TemperaturaDAO;
import modelo.Area;
import modelo.AreaDAO;
import vista.VistaTemperatura;
import java.time.LocalDate;

public class ControladorTemperatura implements ActionListener {

    TemperaturaDAO dao = new TemperaturaDAO();
    AreaDAO areaDAO = new AreaDAO();

    Temperatura temperatura = new Temperatura();

    VistaTemperatura vista;

    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorTemperatura(VistaTemperatura vista) {

        this.vista = vista;
        
        vista.txtFecha.setText(LocalDate.now().toString());


        this.vista.btnProcesar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);

        cargarAreas();

        listar(vista.tblTemperaturas);

    }

    public void cargarAreas() {

        vista.cbArea.removeAllItems();

        List<Area> lista = areaDAO.listar();

        for (int i = 0; i < lista.size(); i++) {

            vista.cbArea.addItem(
                    lista.get(i).getId()
                    + " - "
                    + lista.get(i).getNombre()
            );
        }
    }

    public int obtenerIdArea() {

        String seleccion =
                vista.cbArea.getSelectedItem().toString();

        String[] partes = seleccion.split(" - ");

        int idArea = Integer.parseInt(partes[0]);

        return idArea;
    }

    public void procesar() {
        

        try {

            double temp =
                    Double.parseDouble(vista.txtTemperatura.getText());

            String estado;
            String ventilacion;

            if (temp < 24) {

                estado = "Frio";
                ventilacion = "Cerrar ventilacion";

            } else if (temp <= 27) {

                estado = "Normal";
                ventilacion = "Abrir 25%";

            } else if (temp <= 30) {

                estado = "Normal";
                ventilacion = "Abrir 50%";

            } else if (temp <= 33) {

                estado = "Caliente";
                ventilacion = "Abrir 75%";

            } else {

                estado = "Muy caliente";
                ventilacion = "Abrir 100%";
            }

            vista.txtEstado.setText(estado);
            vista.txtVentilacion.setText(ventilacion);
            
            guardar();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Debe ingresar una temperatura valida"
            );
        }
    }

    public void guardar() {

        try {

            int idArea = obtenerIdArea();

            double temp =
                    Double.parseDouble(vista.txtTemperatura.getText());

            String estado =
                    vista.txtEstado.getText();

            String ventilacion =
                    vista.txtVentilacion.getText();

            String fecha =
                    vista.txtFecha.getText();

            if (estado.equals("") || ventilacion.equals("")) {

                JOptionPane.showMessageDialog(
                        vista,
                        "Primero debe presionar Procesar"
                );

                return;
            }

            temperatura.setIdArea(idArea);
            temperatura.setTemperatura(temp);
            temperatura.setEstado(estado);
            temperatura.setVentilacion(ventilacion);
            temperatura.setFecha(fecha);

            int respuesta = dao.guardarOActualizar(temperatura);

            if (respuesta == 1) {

                JOptionPane.showMessageDialog(
                        vista,
                        "Temperatura guardada correctamente"
                );

                limpiarCampos();
                listar(vista.tblTemperaturas);

            } else {

                JOptionPane.showMessageDialog(
                        vista,
                        "Error al guardar la temperatura"
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Verifique ID Area y temperatura"
            );
        }
    }

    public void listar(JTable tabla) {

        limpiarTabla();

        modelo = (DefaultTableModel) tabla.getModel();

        List<Temperatura> lista = dao.listar();

        Object[] fila = new Object[6];

        for (int i = 0; i < lista.size(); i++) {

            fila[0] = lista.get(i).getId();
            fila[1] = lista.get(i).getNombreArea();
            fila[2] = lista.get(i).getTemperatura();
            fila[3] = lista.get(i).getEstado();
            fila[4] = lista.get(i).getVentilacion();
            fila[5] = lista.get(i).getFecha();

            modelo.addRow(fila);
        }

        tabla.setModel(modelo);
    }

    public void editar() {

        int fila = vista.tblTemperaturas.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Debe seleccionar un registro"
            );

        } else {


        String nombreArea =
        vista.tblTemperaturas.getValueAt(fila, 1).toString();

    for (int i = 0; i < vista.cbArea.getItemCount(); i++) {

    String item = vista.cbArea.getItemAt(i).toString();

    if (item.endsWith(nombreArea)) {

        vista.cbArea.setSelectedIndex(i);
        break;
    }


    vista.txtTemperatura.setText(
        vista.tblTemperaturas.getValueAt(fila, 2).toString()
    );

    vista.txtEstado.setText(
        vista.tblTemperaturas.getValueAt(fila, 3).toString()
    );

    vista.txtVentilacion.setText(
        vista.tblTemperaturas.getValueAt(fila, 4).toString()
    );

    vista.txtFecha.setText(
        vista.tblTemperaturas.getValueAt(fila, 5).toString()
    );

            
            
        }
        }
    }

    public void actualizar() {

        try {


            int idArea = obtenerIdArea();

            double temp =
                    Double.parseDouble(vista.txtTemperatura.getText());

            String estado =
                    vista.txtEstado.getText();

            String ventilacion =
                    vista.txtVentilacion.getText();

            String fecha =
                    vista.txtFecha.getText();

            
            temperatura.setIdArea(idArea);
            temperatura.setTemperatura(temp);
            temperatura.setEstado(estado);
            temperatura.setVentilacion(ventilacion);
            temperatura.setFecha(fecha);

            int respuesta =
                    dao.actualizar(temperatura);

            if (respuesta == 1) {

                JOptionPane.showMessageDialog(
                        vista,
                        "Temperatura actualizada correctamente"
                );

                limpiarCampos();
                listar(vista.tblTemperaturas);

                
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Verifique los datos"
            );
        }
    }

    public void eliminar() {

        int fila =
                vista.tblTemperaturas.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Debe seleccionar un registro"
            );

        } else {

            int opcion =
                    JOptionPane.showConfirmDialog(
                            vista,
                            "¿Desea eliminar esta temperatura?",
                            "Confirmar",
                            JOptionPane.YES_NO_OPTION
                    );

            if (opcion == JOptionPane.YES_OPTION) {

                int id =
                        Integer.parseInt(
                                vista.tblTemperaturas
                                        .getValueAt(fila, 0)
                                        .toString()
                        );

                dao.eliminar(id);

                JOptionPane.showMessageDialog(
                        vista,
                        "Temperatura eliminada correctamente"
                );

                listar(vista.tblTemperaturas);
            }
        }
    }
    
 

    public void buscar() {

        limpiarTabla();

        modelo =
                (DefaultTableModel)
                        vista.tblTemperaturas.getModel();

        List<Temperatura> lista =
                dao.buscar(vista.txtBuscar.getText());

        Object[] fila = new Object[6];

        for (int i = 0; i < lista.size(); i++) {

            fila[0] = lista.get(i).getId();
            fila[1] = lista.get(i).getIdArea();
            fila[2] = lista.get(i).getTemperatura();
            fila[3] = lista.get(i).getEstado();
            fila[4] = lista.get(i).getVentilacion();
            fila[5] = lista.get(i).getFecha();

            modelo.addRow(fila);
        }
    }

    public void limpiarCampos() {

        vista.txtTemperatura.setText("");
        vista.txtEstado.setText("");
        vista.txtVentilacion.setText("");
        vista.txtFecha.setText(LocalDate.now().toString());
        vista.txtFecha.setText(LocalDate.now().toString());
    }

    public void limpiarTabla() {

        modelo =
                (DefaultTableModel)
                        vista.tblTemperaturas.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnProcesar) {
            procesar();
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

            listar(vista.tblTemperaturas);
        }
    }
}