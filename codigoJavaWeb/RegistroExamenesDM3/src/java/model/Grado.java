
package model;

/**
 *
 * @author Emiliano Barat
 */
public class Grado {
    private int idGrado;
    private String nombreGrado;

    public Grado() {
    }

    public Grado(int idGrado, String nombreGrado) {
        this.idGrado = idGrado;
        this.nombreGrado = nombreGrado;
    }

    public int getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(int idGrado) {
        this.idGrado = idGrado;
    }
    
    
    public String getNombreGrado() {
        return nombreGrado;
    }

    public void setNombreGrado(String nombreGrado) {
        this.nombreGrado = nombreGrado;
    }
    
    
    
}