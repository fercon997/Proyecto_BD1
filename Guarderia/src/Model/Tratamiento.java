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
public class Tratamiento {
    private int Codigo_Sintoma;
    private String Nombre_Sintoma;
    private int Codigo_Medicamento;
    private String Nombre_Medicamento;
    private String Cantidad;

    public int getCodigo_Sintoma() {
        return Codigo_Sintoma;
    }

    public String getNombre_Sintoma() {
        return Nombre_Sintoma;
    }

    public int getCodigo_Medicamento() {
        return Codigo_Medicamento;
    }

    public String getNombre_Medicamento() {
        return Nombre_Medicamento;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCodigo_Sintoma(int Codigo_Sintoma) {
        this.Codigo_Sintoma = Codigo_Sintoma;
    }

    public void setNombre_Sintoma(String Nombre_Sintoma) {
        this.Nombre_Sintoma = Nombre_Sintoma;
    }

    public void setCodigo_Medicamento(int Codigo_Medicamento) {
        this.Codigo_Medicamento = Codigo_Medicamento;
    }

    public void setNombre_Medicamento(String Nombre_Medicamento) {
        this.Nombre_Medicamento = Nombre_Medicamento;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }

}
