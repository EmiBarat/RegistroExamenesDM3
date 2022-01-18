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
public class MatrizPuntos {
    
    private Genero genero;
    private TipoPrueba tipoPrueba;
    private Categoria categoria;
    private double exigenciaMinima;
    private double exigenciaMaxima;
    private int puntosObtenidos;
    //exigencia min y max funcionan tanto para la carrera como para las pruebas 
    //de cantidad de repeticiones
    // Modificar en la DB!!!!

    public MatrizPuntos() {
    }

    public MatrizPuntos(Genero genero, TipoPrueba tipoPrueba, Categoria categoria, double exigenciaMinima, double exigenciaMaxima, int puntosObtenidos) {
        this.genero = genero;
        this.tipoPrueba = tipoPrueba;
        this.categoria = categoria;
        this.exigenciaMinima = exigenciaMinima;
        this.exigenciaMaxima = exigenciaMaxima;
        this.puntosObtenidos = puntosObtenidos;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public TipoPrueba getTipoPrueba() {
        return tipoPrueba;
    }

    public void setTipoPrueba(TipoPrueba tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getExigenciaMinima() {
        return exigenciaMinima;
    }

    public void setExigenciaMinima(double exigenciaMinima) {
        this.exigenciaMinima = exigenciaMinima;
    }

    public double getExigenciaMaxima() {
        return exigenciaMaxima;
    }

    public void setExigenciaMaxima(double exigenciaMaxima) {
        this.exigenciaMaxima = exigenciaMaxima;
    }

    public int getPuntosObtenidos() {
        return puntosObtenidos;
    }

    public void setPuntosObtenidos(int puntosObtenidos) {
        this.puntosObtenidos = puntosObtenidos;
    }
    
    
    
}
