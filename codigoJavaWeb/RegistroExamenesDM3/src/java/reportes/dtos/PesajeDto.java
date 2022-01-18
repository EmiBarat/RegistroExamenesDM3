/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes.dtos;

/**
 *
 * @author Emiliano Barat
 */
public class PesajeDto {
    private String nombre;
    private String estadoPeso;
    private double IMC;
    private double pesoEnKilos;

    public PesajeDto(String nombre, String estadoPeso, double IMC, double pesoEnKilos) {
        this.nombre = nombre;
        this.estadoPeso = estadoPeso;
        this.IMC = IMC;
        this.pesoEnKilos = pesoEnKilos;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstadoPeso() {
        return estadoPeso;
    }

    public void setEstadoPeso(String estadoPeso) {
        this.estadoPeso = estadoPeso;
    }

    public double getIMC() {
        return IMC;
    }

    public void setIMC(double IMC) {
        this.IMC = IMC;
    }

    public double getPesoEnKilos() {
        return pesoEnKilos;
    }

    public void setPesoEnKilos(double pesoEnKilos) {
        this.pesoEnKilos = pesoEnKilos;
    }
    
    
}
