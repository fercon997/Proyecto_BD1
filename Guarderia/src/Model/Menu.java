/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author gabrielbaron
 */
public class Menu {
    private int Numero;
    private String Fecha;
    private float costo;
    private String Fecha_fin;
    private String Rif;
    public int getNumero() {
        return Numero;
    }

    public String getFecha() {
        return Fecha;
    }

    public float getCosto() {
        return costo;
    }

    public String getFecha_fin() {
        return Fecha_fin;
    }

    public String getRif() {
        return Rif;
    }
    
    
    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public void setFecha_fin(String Fecha_fin) {
        this.Fecha_fin = Fecha_fin;
    }

    public void setRif(String Rif) {
        this.Rif = Rif;
    }
    
}
