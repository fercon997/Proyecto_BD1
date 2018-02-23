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
public class Alergia {
    int Codigo;
    String Descripcion;

    public int getCodigo() {
        return Codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
}
