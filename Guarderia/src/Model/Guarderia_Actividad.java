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
public class Guarderia_Actividad {
    String Nombre;
    String Descripcion;
    int Edad_minima;
    int Cupo_Maximo;
    int Cupo_Minimo;
    int Costo;

    public String getNombre() {
        return Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public int getEdad_minima() {
        return Edad_minima;
    }

    public int getCupo_Maximo() {
        return Cupo_Maximo;
    }

    public int getCupo_Minimo() {
        return Cupo_Minimo;
    }

    public int getCosto() {
        return Costo;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public void setEdad_minima(int Edad_minima) {
        this.Edad_minima = Edad_minima;
    }

    public void setCupo_Maximo(int Cupo_Maximo) {
        this.Cupo_Maximo = Cupo_Maximo;
    }

    public void setCupo_Minimo(int Cupo_Minimo) {
        this.Cupo_Minimo = Cupo_Minimo;
    }

    public void setCosto(int Costo) {
        this.Costo = Costo;
    }
    
}
