/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author fercon997
 */
public class Lugar {
    int codigo;
    String nombre;
    String tipo;
    int codigo_superior;

    public Lugar() {
    }

    public Lugar(int codigo, String nombre, String tipo, int codigo_superior) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.codigo_superior = codigo_superior;
    }

    public Lugar(int codigo, String nombre, String tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCodigo_superior() {
        return codigo_superior;
    }

    public void setCodigo_superior(int codigo_superior) {
        this.codigo_superior = codigo_superior;
    }
    
    
}
