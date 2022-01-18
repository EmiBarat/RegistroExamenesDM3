/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Emiliano Barat
 */
public class Examen {
    private int idExamen;
    private int tipoExamen;
    private String fechaExamen; //esta va a ser la que vale, hay que sacar la de la prueba...
    private int idProfesor;
    private int idAlumno;
    private double notaFinal;
    private String observaciones;
    private int idCategoria; //Aca estoy probando si puedo NO guardar todo el Obj, sino solo el dato.
    //puede que falten atributos... peso
    private ArrayList<DetalleExamen> listaDetalleExamen;

    public ArrayList<DetalleExamen> getListaDetalleExamen() {
        return listaDetalleExamen;
    }

    public void setListaDetalleExamen(ArrayList<DetalleExamen> listaDetalleExamen) {
        this.listaDetalleExamen = listaDetalleExamen;
    }

    public Examen() {
    }
    
    

    public Examen(int idExamen, int tipoExamen, String fechaExamen, int idProfesor, int idAlumno, double notaFinal, String observaciones, int idCategoria) {
        this.idExamen = idExamen;
        this.tipoExamen = tipoExamen;
        this.fechaExamen = fechaExamen;
        this.idProfesor = idProfesor;
        this.idAlumno = idAlumno;
        this.notaFinal = notaFinal;
        this.observaciones = observaciones;
        this.idCategoria = idCategoria;
        this.listaDetalleExamen = new ArrayList<>();
    }
    
    

    public Examen(int tipoExamen, String fechaExamen, int idProfesor, int idAlumno, String observaciones, int idCategoria) {
        
        this.tipoExamen = tipoExamen;
        this.fechaExamen = fechaExamen;
        this.idProfesor = idProfesor;
        this.idAlumno = idAlumno;
        
        this.observaciones = observaciones;
        this.idCategoria = idCategoria;
        this.listaDetalleExamen = new ArrayList<>();
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public int getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(int tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public String getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(String fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    public void agregarDetalles(DetalleExamen de){
        this.listaDetalleExamen.add(de);
    }
    
    public int calcularNotaFinal (){
        int acum = 0;
        int cantPruebas = 0;
        for (DetalleExamen detalleExamen : listaDetalleExamen) {
            if (detalleExamen.getResultado() > 0) {
                cantPruebas++;
                acum += detalleExamen.getPuntaje();
            }
        }
        return acum/cantPruebas;
    }
}
