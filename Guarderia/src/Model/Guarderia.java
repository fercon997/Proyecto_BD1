/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalTime;

/**
 *
 * @author Ignacio
 */
public class Guarderia {
    private String rif;
    private String comboText;
    private String nombre;
    private String costoMensualidad;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }
    
    public String getComboText() {
        return comboText;
    }
    
    public void setComboText(String comboText) {
        this.comboText = comboText;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCostoMensualidad() {
        return costoMensualidad;
    }

    public void setCostoMensualidad(String costoMensualidad) {
        this.costoMensualidad = costoMensualidad;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }    
}
