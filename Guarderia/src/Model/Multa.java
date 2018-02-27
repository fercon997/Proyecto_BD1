/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author fercon997
 */
public class Multa {
    String ci_representante;
    String letra_nino;
    int consecutivo_inscripcion;
    int ano_inscripcion;
    Date fecha_asistencia;
    float monto;
    int num_transferencia;

    public Multa(String ci_representante, String letra_nino, int consecutivo_inscripcion, int ano_inscripcion, Date fecha_asistencia, float monto, int num_transferencia) {
        this.ci_representante = ci_representante;
        this.letra_nino = letra_nino;
        this.consecutivo_inscripcion = consecutivo_inscripcion;
        this.ano_inscripcion = ano_inscripcion;
        this.fecha_asistencia = fecha_asistencia;
        this.monto = monto;
        this.num_transferencia = num_transferencia;
    }

    public Multa() {
    }

    public String getCi_representante() {
        return ci_representante;
    }

    public void setCi_representante(String ci_representante) {
        this.ci_representante = ci_representante;
    }

    public String getLetra_nino() {
        return letra_nino;
    }

    public void setLetra_nino(String letra_nino) {
        this.letra_nino = letra_nino;
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

    public Date getFecha_asistencia() {
        return fecha_asistencia;
    }

    public void setFecha_asistencia(Date fecha_asistencia) {
        this.fecha_asistencia = fecha_asistencia;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public int getNum_transferencia() {
        return num_transferencia;
    }

    public void setNum_transferencia(int num_transferencia) {
        this.num_transferencia = num_transferencia;
    }
    
    
    
    
    
}
