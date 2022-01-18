/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtoModel;

import model.Categoria;

/**
 *
 * @author Emiliano Barat
 */
public class ListadoAlumnosDTO {
    
    private int idAlumno;
    private String grado;
    private String apellido;
    private String nombre;
    private String fechaNac;
    private int dni;
    private int codigoEst;
    private int idGenero;
    private String genero;
    private Categoria categoria;
    

    public ListadoAlumnosDTO() {
    }
        
    public ListadoAlumnosDTO(int idAlumno, String grado, String apellido, String nombre, String fechaNac, int dni, int codigoEst, int idGenero, String genero, Categoria categoria) {
        this.idAlumno = idAlumno;
        this.grado = grado;
        this.apellido = apellido;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.dni = dni;
        this.codigoEst = codigoEst;
        this.idGenero = idGenero;
        this.genero = genero;
        this.categoria = categoria;
        
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    
    
    
    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }
    
    

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getCodigoEst() {
        return codigoEst;
    }

    public void setCodigoEst(int codigoEst) {
        this.codigoEst = codigoEst;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    
    
}
