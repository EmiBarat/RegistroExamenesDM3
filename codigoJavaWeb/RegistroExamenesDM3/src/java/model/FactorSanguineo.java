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
public class FactorSanguineo {
    
    private int idFactorSanguineo;
    private String factorSanguineo;

    public FactorSanguineo(int idFactorSanguineo, String factorSanguineo) {
        this.idFactorSanguineo = idFactorSanguineo;
        this.factorSanguineo = factorSanguineo;
    }

    public FactorSanguineo() {
        
    }

    public int getIdFactorSanguineo() {
        return idFactorSanguineo;
    }

    public void setIdFactorSanguineo(int idFactorSanguineo) {
        this.idFactorSanguineo = idFactorSanguineo;
    }

    public String getFactorSanguineo() {
        return factorSanguineo;
    }

    public void setFactorSanguineo(String factorSanguineo) {
        this.factorSanguineo = factorSanguineo;
    }
    
    
    
}
