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
public class Pediatra {
    private int codigo;
    private String nombre;
    private String Tlf_movil;
    private String Tlf_oficina;

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTlf_movil() {
        return Tlf_movil;
    }

    public String getTlf_oficina() {
        return Tlf_oficina;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTlf_movil(String Tlf_movil) {
        this.Tlf_movil = Tlf_movil;
    }

    public void setTlf_oficina(String Tlf_oficina) {
        this.Tlf_oficina = Tlf_oficina;
    }
    
}
