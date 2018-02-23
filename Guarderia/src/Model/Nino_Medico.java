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
public class Nino_Medico {
    private String Letra;
    private String Ci_representante;
    private String Nombre;
    private String Pediatra;
    private String Alergia;
    private String Enfermedad;
    private String Sintoma;
    private String Medicina;
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getLetra() {
        return Letra;
    }

    public String getCi_representante() {
        return Ci_representante;
    }

    public void setLetra(String Letra) {
        this.Letra = Letra;
    }

    public void setCi_representante(String Ci_representante) {
        this.Ci_representante = Ci_representante;
    }

    public String getPediatra() {
        return Pediatra;
    }

    public String getAlergia() {
        return Alergia;
    }

    public String getEnfermedad() {
        return Enfermedad;
    }

    public String getSintoma() {
        return Sintoma;
    }

    public String getMedicina() {
        return Medicina;
    }

    public void setPediatra(String Pediatra) {
        this.Pediatra = Pediatra;
    }

    public void setAlergia(String Alergia) {
        this.Alergia = Alergia;
    }

    public void setEnfermedad(String Enfermedad) {
        this.Enfermedad = Enfermedad;
    }

    public void setSintoma(String Sintoma) {
        this.Sintoma = Sintoma;
    }

    public void setMedicina(String Medicina) {
        this.Medicina = Medicina;
    }
    
}
