
package Model;

import java.sql.Date;
import java.util.ArrayList;

public class Personal {
    String ci;
    String nombre;
    String apellido;
    String celular;
    String nivelEstudio;
    int sueldo;
    int encargada;
    Date fechaResponsable;
    String rifGuarderia;
    int cod_direccion;
    ArrayList<String> experiencia;

    public Personal(String ci, String nombre, String apellido, String celular, String nivelEstudio, int sueldo, int encargada, Date fechaResponsable, String rifGuarderia, int cod_direccion, ArrayList<String> experiencia) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = "0" + celular;
        this.nivelEstudio = nivelEstudio;
        this.sueldo = sueldo;
        this.encargada = encargada;
        this.fechaResponsable = fechaResponsable;
        this.rifGuarderia = rifGuarderia;
        this.cod_direccion = cod_direccion;
        this.experiencia = experiencia;
    }

    public Personal() {
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = "0" + celular;
    }

    public String getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(String nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public int getEncargada() {
        return encargada;
    }

    public void setEncargada(int encargada) {
        this.encargada = encargada;
    }

    public Date getFechaResponsable() {
        return fechaResponsable;
    }

    public void setFechaResponsable(Date fechaResponsable) {
        this.fechaResponsable = fechaResponsable;
    }

    public String getRifGuarderia() {
        return rifGuarderia;
    }

    public void setRifGuarderia(String rifGuarderia) {
        this.rifGuarderia = rifGuarderia;
    }

    public int getCod_direccion() {
        return cod_direccion;
    }

    public void setCod_direccion(int cod_direccion) {
        this.cod_direccion = cod_direccion;
    }

    public ArrayList<String> getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(ArrayList<String> experiencia) {
        this.experiencia = experiencia;
    }

    
}
