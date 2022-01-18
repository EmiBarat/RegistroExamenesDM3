/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Emiliano Barat
 */
public class TipoDeEstadoPeso {
    private int idTipoEstadoPeso;
    private String descripcion;
    private String observaciones;
    private double pesoMin;
    private double pesoMax;

    public TipoDeEstadoPeso(int idTipoEstadoPeso, String descripcion, String observaciones, double pesoMin, double pesoMax) {
        this.idTipoEstadoPeso = idTipoEstadoPeso;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
        this.pesoMin = pesoMin;
        this.pesoMax = pesoMax;
    }

    public int getIdTipoEstadoPeso() {
        return idTipoEstadoPeso;
    }

    public void setIdTipoEstadoPeso(int idTipoEstadoPeso) {
        this.idTipoEstadoPeso = idTipoEstadoPeso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public double getPesoMin() {
        return pesoMin;
    }

    public void setPesoMin(double pesoMin) {
        this.pesoMin = pesoMin;
    }

    public double getPesoMax() {
        return pesoMax;
    }

    public void setPesoMax(double pesoMax) {
        this.pesoMax = pesoMax;
    }
    
    
}
