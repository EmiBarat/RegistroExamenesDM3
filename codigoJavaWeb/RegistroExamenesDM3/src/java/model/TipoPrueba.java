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
public class TipoPrueba {
    
    private int tipoPrueba;
    private String descripcion;

    public TipoPrueba(int tipoPrueba, String descripcion) {
        this.tipoPrueba = tipoPrueba;
        this.descripcion = descripcion;
    }

    public int getTipoPrueba() {
        return tipoPrueba;
    }

    public void setTipoPrueba(int tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
