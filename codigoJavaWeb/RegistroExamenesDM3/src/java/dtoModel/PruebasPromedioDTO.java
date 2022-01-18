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
public class PruebasPromedioDTO {
    private String prueba;
    private double promedio;

    public PruebasPromedioDTO(String prueba, double promedio) {
        this.prueba = prueba;
        this.promedio = promedio;
    }

    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
    
    
}
