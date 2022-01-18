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
public class GrupoSanguineo {
    
    private int idGrupoSanguineo;
    private String grupoSanguineo;

    public GrupoSanguineo(int idGrupoSanguineo, String grupoSanguineo) {
        this.idGrupoSanguineo = idGrupoSanguineo;
        this.grupoSanguineo = grupoSanguineo;
    }

    public GrupoSanguineo() {
        
    }

    public int getIdGrupoSanguineo() {
        return idGrupoSanguineo;
    }

    public void setIdGrupoSanguineo(int idGrupoSanguineo) {
        this.idGrupoSanguineo = idGrupoSanguineo;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }
    
    
}
