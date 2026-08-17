/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Temperatura {

    private int id;
    private int idArea;
    private double temperatura;
    private String estado;
    private String ventilacion;
    private String fecha;
    private String nombreArea;

    public Temperatura() {
    }

    public Temperatura(int id, int idArea, double temperatura,
                       String estado, String ventilacion, String fecha) {

        this.id = id;
        this.idArea = idArea;
        this.temperatura = temperatura;
        this.estado = estado;
        this.ventilacion = ventilacion;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }
    
    public String getNombreArea() {
    return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
    this.nombreArea = nombreArea;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getVentilacion() {
        return ventilacion;
    }

    public void setVentilacion(String ventilacion) {
        this.ventilacion = ventilacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}