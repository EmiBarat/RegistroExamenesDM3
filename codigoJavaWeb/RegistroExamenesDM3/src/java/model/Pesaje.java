/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Emiliano Barat
 */
public class Pesaje {
    private int idPesaje;
    private Alumno alumno;
    private TipoDeEstadoPeso estadoPeso;
    private String fechaPesaje;
    private double pesoEnKilos;
    private String observaciones;
    private boolean bajoSeguimiento;
    private double indiceMasaCorporal;

    public Pesaje() {
    }
    
    

    public Pesaje(int idPesaje, Alumno alumno, TipoDeEstadoPeso estadoPeso, String fechaPesaje, double pesoEnKilos, String observaciones, boolean bajoSeguimiento, double indiceMasaCorporal) {
        this.idPesaje = idPesaje;
        this.alumno = alumno;
        this.estadoPeso = estadoPeso;
        this.fechaPesaje = fechaPesaje;
        this.pesoEnKilos = pesoEnKilos;
        this.observaciones = observaciones;
        this.bajoSeguimiento = bajoSeguimiento;
        this.indiceMasaCorporal = indiceMasaCorporal;
    }

    public int getIdPesaje() {
        return idPesaje;
    }

    public void setIdPesaje(int idPesaje) {
        this.idPesaje = idPesaje;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public TipoDeEstadoPeso getEstadoPeso() {
        return estadoPeso;
    }

    public void setEstadoPeso(TipoDeEstadoPeso estadoPeso) {
        this.estadoPeso = estadoPeso;
    }

    public String getFechaPesaje() {
        return fechaPesaje;
    }

    public void setFechaPesaje(String fechaPesaje) {
        this.fechaPesaje = fechaPesaje;
    }

    public double getPesoEnKilos() {
        return pesoEnKilos;
    }

    public void setPesoEnKilos(double pesoEnKilos) {
        this.pesoEnKilos = pesoEnKilos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isBajoSeguimiento() {
        return bajoSeguimiento;
    }

    public void setBajoSeguimiento(boolean bajoSeguimiento) {
        this.bajoSeguimiento = bajoSeguimiento;
    }

    public double getIndiceMasaCorporal() {
        return indiceMasaCorporal;
    }

    public void setIndiceMasaCorporal(double indiceMasaCorporal) {
        this.indiceMasaCorporal = indiceMasaCorporal;
    }
    
    //El pesaje genera un IMC
    public double calcularIMC(double pesoEnKilos, double talla){
        double imc = pesoEnKilos / (talla * talla);
        return imc;   
    }
    
}
