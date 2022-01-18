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
public class ExamenDto {
    
    private int idExamen;
    private String tipoExamen;
    private String fechaExamen;
    private String nombreProfe;
    private String apellidoProfe;
    private String nombreAlumno;
    private String apellidoAlumno;
    private double notaFinal;
    private String observaciones;
    private String categoria;

    public ExamenDto(int idExamen, String tipoExamen, String fechaExamen, String nombreProfe, String apellidoProfe, String nombreAlumno, String apellidoAlumno, double notaFinal, String observaciones, String categoria) {
        this.idExamen = idExamen;
        this.tipoExamen = tipoExamen;
        this.fechaExamen = fechaExamen;
        this.nombreProfe = nombreProfe;
        this.nombreAlumno = nombreAlumno;
        this.apellidoAlumno = apellidoAlumno;
        this.notaFinal = notaFinal;
        this.observaciones = observaciones;
        this.categoria = categoria;
        this.apellidoProfe = apellidoProfe;
    }

    public ExamenDto() {
        
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public String getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(String fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    public String getNombreProfe() {
        return nombreProfe;
    }

    public void setNombreProfe(String nombreProfe) {
        this.nombreProfe = nombreProfe;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidoAlumno() {
        return apellidoAlumno;
    }

    public void setApellidoAlumno(String apellidoAlumno) {
        this.apellidoAlumno = apellidoAlumno;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getApellidoProfe() {
        return apellidoProfe;
    }

    public void setApellidoProfe(String apellidoProfe) {
        this.apellidoProfe = apellidoProfe;
    }
    
    
    
}
