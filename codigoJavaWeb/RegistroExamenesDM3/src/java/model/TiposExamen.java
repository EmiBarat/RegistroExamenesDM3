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
public class TiposExamen {
    private int idTipoExamen;
    private String descripcion;
    private String observaciones;

    public TiposExamen(int idTipoExamen, String descripcion, String observaciones) {
        this.idTipoExamen = idTipoExamen;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
    }

    public int getIdTipoExamen() {
        return idTipoExamen;
    }

    public void setIdTipoExamen(int idTipoExamen) {
        this.idTipoExamen = idTipoExamen;
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
    
    
}
