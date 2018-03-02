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
 * @author Ignacio
 */
public class Inscripcion {
    
    int ano;
    int consecutivo;
    String rifGuarderia;
    String ciRepresentante;
    char letraNino;
    Date fechaInscripcion;
    Time horaLlegada;
    Time horaSalida;
    String nombreNino;
    String apellidoNino;
    int horasExtra;
    
    public Inscripcion() {
        
    }

    public Inscripcion(int ano, int consecutivo, String rifGuarderia, String ciRepresentante, char letraNino, Date fechaInscripcion, Time horaLlegada, Time horaSalida) {
        this.ano = ano;
        this.consecutivo = consecutivo;
        this.rifGuarderia = rifGuarderia;
        this.ciRepresentante = ciRepresentante;
        this.letraNino = letraNino;
        this.fechaInscripcion = fechaInscripcion;
        this.horaLlegada = horaLlegada;
        this.horaSalida = horaSalida;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getRifGuarderia() {
        return rifGuarderia;
    }

    public void setRifGuarderia(String rifGuarderia) {
        this.rifGuarderia = rifGuarderia;
    }

    public String getCiRepresentante() {
        return ciRepresentante;
    }

    public void setCiRepresentante(String ciRepresentante) {
        this.ciRepresentante = ciRepresentante;
    }

    public char getLetraNino() {
        return letraNino;
    }

    public void setLetraNino(char letraNino) {
        this.letraNino = letraNino;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Time getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Time horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getNombreNino() {
        return nombreNino;
    }

    public void setNombreNino(String nombreNino) {
        this.nombreNino = nombreNino;
    }

    public String getApellidoNino() {
        return apellidoNino;
    }

    public void setApellidoNino(String apellidoNino) {
        this.apellidoNino = apellidoNino;
    } 

    public int getHorasExtra() {
        return horasExtra;
    }

    public void setHorasExtra(int horasExtra) {
        this.horasExtra = horasExtra;
    }

}
