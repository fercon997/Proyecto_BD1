/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author fercon997
 */
public class Asistencia {
    Date fecha;
    int consecutivo_inscripcion;
    int ano_inscripcion;
    String ci_representante;
    char Letra;
    String ci_representante_busco;
    String ci_auth_busco;
    Time hora_entrada;
    Time hora_salida;
    String comio;
    int costoMulta;
    long numTransferencia;

    public Asistencia() {
    }

    public int getCostoMulta() {
        return costoMulta;
    }

    public void setCostoMulta(int costoMulta) {
        this.costoMulta = costoMulta;
    }

    public long getNumTransferencia() {
        return numTransferencia;
    }

    public void setNumTransferencia(long numTransferencia) {
        this.numTransferencia = numTransferencia;
    }

    
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getConsecutivo_inscripcion() {
        return consecutivo_inscripcion;
    }

    public void setConsecutivo_inscripcion(int consecutivo_inscripcion) {
        this.consecutivo_inscripcion = consecutivo_inscripcion;
    }

    public int getAno_inscripcion() {
        return ano_inscripcion;
    }

    public void setAno_inscripcion(int ano_inscripcion) {
        this.ano_inscripcion = ano_inscripcion;
    }

    public String getCi_representante() {
        return ci_representante;
    }

    public void setCi_representante(String ci_representante) {
        this.ci_representante = ci_representante;
    }

    public char getLetra() {
        return Letra;
    }

    public void setLetra(char Letra) {
        this.Letra = Letra;
    }

    public String getCi_representante_busco() {
        return ci_representante_busco;
    }

    public void setCi_representante_busco(String ci_representante_busco) {
        this.ci_representante_busco = ci_representante_busco;
    }

    public String getCi_auth_busco() {
        return ci_auth_busco;
    }

    public void setCi_auth_busco(String ci_auth_busco) {
        this.ci_auth_busco = ci_auth_busco;
    }

    public Time getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(Time hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public Time getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(Time hora_salida) {
        this.hora_salida = hora_salida;
    }

    public String getComio() {
        return comio;
    }

    public void setComio(String comio) {
        this.comio = comio;
    }
    
    
}
