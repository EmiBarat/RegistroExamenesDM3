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
public class Profesor {
    private int idProfesor;
    private int idGrado;
    private String nombre;
    private String apellido;
    private int codigoEstadistico;
    private int matriculaIndividual;
    private Date fechaBaja;

    public Profesor(int idProfesor, int idGrado, String nombre, String apellido, int codigoEstadistico, int matriculaIndividual, Date fechaBaja) {
        this.idProfesor = idProfesor;
        this.idGrado = idGrado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigoEstadistico = codigoEstadistico;
        this.matriculaIndividual = matriculaIndividual;
        this.fechaBaja = fechaBaja;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(int idGrado) {
        this.idGrado = idGrado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCodigoEstadistico() {
        return codigoEstadistico;
    }

    public void setCodigoEstadistico(int codigoEstadistico) {
        this.codigoEstadistico = codigoEstadistico;
    }

    public int getMatriculaIndividual() {
        return matriculaIndividual;
    }

    public void setMatriculaIndividual(int matriculaIndividual) {
        this.matriculaIndividual = matriculaIndividual;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }
    
    
}
