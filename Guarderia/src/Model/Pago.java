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
public class Pago {
    int consecutivo;
    int cons_inscripcion;
    int ano_inscripcion;
    String ci_representante;
    char letra;
    String concepto;
    float monto;
    Date fecha;
    String forma_pago;
    int mes;

    public Pago() {
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
    
    

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public int getCons_inscripcion() {
        return cons_inscripcion;
    }

    public void setCons_inscripcion(int cons_inscripcion) {
        this.cons_inscripcion = cons_inscripcion;
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
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }
    
    
    
}
