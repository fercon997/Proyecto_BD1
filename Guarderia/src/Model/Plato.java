/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author gabrielbaron
 */
public class Plato {
    int Codigo;
    ArrayList<Comida> comidas;
    String Descripcion;

    public int getCodigo() {
        return Codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    
    public ArrayList<Comida> getComidas() {
        return comidas;
    }


    public void setComidas(ArrayList<Comida> comidas) {
        this.comidas = comidas;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    
    
}
