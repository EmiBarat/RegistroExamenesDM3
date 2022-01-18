package model;

import java.util.Date;

/**
 *
 * @author Emiliano Barat
 */
public class DetalleExamen {
    
    private int idExamen;
    private int prueba;
    private String fechaPrueba;
    private double resultado;
    private int puntaje;
    private int prodesor;
    private String observaciones;

    public DetalleExamen(int idExamen, int prueba, String fechaPrueba, double resultado, int puntaje, int prodesor, String observaciones) {
        this.idExamen = idExamen;
        this.prueba = prueba;
        this.fechaPrueba = fechaPrueba;
        this.resultado = resultado;
        this.puntaje = puntaje;
        this.prodesor = prodesor;
        this.observaciones = observaciones;
    }
    
    

    public DetalleExamen( int prueba, String fechaPrueba, double resultado, int puntaje, int prodesor, String observaciones) {
//        this.idExamen = examen;
        this.prueba = prueba;
        this.fechaPrueba = fechaPrueba;
        this.resultado = resultado;
        this.puntaje = puntaje;
        this.prodesor = prodesor;
        this.observaciones = observaciones;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public int getPrueba() {
        return prueba;
    }

    public void setPrueba(int prueba) {
        this.prueba = prueba;
    }

    public String getFechaPrueba() {
        return fechaPrueba;
    }

    public void setFechaPrueba(String fechaPrueba) {
        this.fechaPrueba = fechaPrueba;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getProdesor() {
        return prodesor;
    }

    public void setProdesor(int prodesor) {
        this.prodesor = prodesor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
    
}
