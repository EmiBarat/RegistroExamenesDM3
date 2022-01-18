/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtoModel;

/**
 *
 * @author Emiliano Barat
 */
public class PruebasXIdExamenDTO {
    
    private int idExamen;
    private int prueba;
    private String nombrePrueba;
//    private String fechaPrueba;
    private double resultado;
    private int puntaje;

    public PruebasXIdExamenDTO(int idExamen, int prueba, String nombrePrueba,  double resultado, int puntaje) {
        this.idExamen = idExamen;
        this.prueba = prueba;
        this.nombrePrueba = nombrePrueba;
//        this.fechaPrueba = fechaPrueba;
        this.resultado = resultado;
        this.puntaje = puntaje;
    }

    public PruebasXIdExamenDTO() {
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

    public String getNombrePrueba() {
        return nombrePrueba;
    }

    public void setNombrePrueba(String nombrePrueba) {
        this.nombrePrueba = nombrePrueba;
    }

//    public String getFechaPrueba() {
//        return fechaPrueba;
//    }
//
//    public void setFechaPrueba(String fechaPrueba) {
//        this.fechaPrueba = fechaPrueba;
//    }

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
    
    
    
}
