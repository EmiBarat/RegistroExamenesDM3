/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.AccesoBaseDatos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Emiliano Barat
 */
public class Alumno {
    private int idAlumno;
    private int grado;
    private int genero;
    private String nombres;
    private String apellidos;
    private String fechaNacimiento;
    private int codigoEstadistico;
    private int matriculaIndividual;
    private int nroIOSFA;
    private int grupoSanguineo;
    private int factorSanguineo;
    private String obsSanitarias;
    private String obsGenerales;
    private double tallaEnMetros;
    private String fechaAlta;
    private String fechaBaja;
    private int idCategoria;

    public Alumno() {
    }

    public Alumno(int idAlumno, int grado, int genero, String nombres, String apellidos, String fechaNacimiento, int codigoEstadistico, int matriculaIndividual, int nroIOSFA, int grupoSanguineo, int factorSanguineo, String obsSanitarias, String obsGenerales, double tallaEnMetros, String fechaAlta, String fechaBaja) {
        this.idAlumno = idAlumno;
        this.grado = grado;
        this.genero = genero;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.codigoEstadistico = codigoEstadistico;
        this.matriculaIndividual = matriculaIndividual;
        this.nroIOSFA = nroIOSFA;
        this.grupoSanguineo = grupoSanguineo;
        this.factorSanguineo = factorSanguineo;
        this.obsSanitarias = obsSanitarias;
        this.obsGenerales = obsGenerales;
        this.tallaEnMetros = tallaEnMetros;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        
    }
    
    

    public Alumno(int grado, int genero, String nombres, String apellidos, String fechaNacimiento, int codigoEstadistico, int matriculaIndividual, int nroIOSFA, int grupoSanguineo, int factorSanguineo, String obsSanitarias, String obsGenerales, double tallaEnMetros, String fechaAlta, String fechaBaja, int idCategoria) {
        
        this.grado = grado;
        this.genero = genero;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.codigoEstadistico = codigoEstadistico;
        this.matriculaIndividual = matriculaIndividual;
        this.nroIOSFA = nroIOSFA;
        this.grupoSanguineo = grupoSanguineo;
        this.factorSanguineo = factorSanguineo;
        this.obsSanitarias = obsSanitarias;
        this.obsGenerales = obsGenerales;
        this.tallaEnMetros = tallaEnMetros;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.idCategoria = idCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    
    public String getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }    
    
    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
        
    public int getFactorSanguineo(){
        return factorSanguineo;
    }
    
    public void setFactorSanguineo(int factorSanguineo){
        this.factorSanguineo = factorSanguineo;
    }
    
    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public int getNroIOSFA() {
        return nroIOSFA;
    }

    public void setNroIOSFA(int nroIOSFA) {
        this.nroIOSFA = nroIOSFA;
    }

    public int getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(int grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getObsSanitarias() {
        return obsSanitarias;
    }

    public void setObsSanitarias(String obsSanitarias) {
        this.obsSanitarias = obsSanitarias;
    }

    public String getObsGenerales() {
        return obsGenerales;
    }

    public void setObsGenerales(String obsGenerales) {
        this.obsGenerales = obsGenerales;
    }

    public double getTallaEnMetros() {
        return tallaEnMetros;
    }

    public void setTallaEnMetros(double tallaEnMetros) {
        this.tallaEnMetros = tallaEnMetros;
    }
    
    //este metodo se reemplaza calculando la edad en SQL
//    public int calcularCategoria(String fechaNac){
//        
//        Calendar nacimiento = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        
//        try {
//            nacimiento.setTime(sdf.parse(fechaNac));
//        } catch (ParseException ex) {
//            System.out.println("error");
//        }
//        
//        Calendar actual = Calendar.getInstance();
//        
//        int birthYear = nacimiento.get(Calendar.YEAR);
//        
//        int edad = actual.get(Calendar.YEAR) - birthYear;
//        
////        edad = edad - 1;
//        
//        if(nacimiento.get(Calendar.DAY_OF_YEAR) < actual.get(Calendar.DAY_OF_YEAR)){
//            --edad;
//        }
//        
//        AccesoBaseDatos gestor = new AccesoBaseDatos();
//        
//        int idCategoria = gestor.buscarCategoria(edad);
//        
//        
//        
//        return idCategoria;
//    }
    
    public int calcularCategConSql(String fechaNac){
        
        AccesoBaseDatos gestor = new AccesoBaseDatos();
        int edad = gestor.buscarEdad(fechaNac);
        int idCategoria = gestor.buscarCategoria(edad);
        
        return idCategoria;
        
        
    }
    
    
    
}
