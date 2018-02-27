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
public class HorarioInscripcion {
    Time horaEntradaGuarderia;
    Time horaSalidaGuarderia;
    Time horaInicioAct;
    Time horaFinAct;
    String nombreAct;
    Date fechaActividad;

    public HorarioInscripcion() {
    }

    public Time getHoraEntradaGuarderia() {
        return horaEntradaGuarderia;
    }

    public void setHoraEntradaGuarderia(Time horaEntradaGuarderia) {
        this.horaEntradaGuarderia = horaEntradaGuarderia;
    }

    public Time getHoraSalidaGuarderia() {
        return horaSalidaGuarderia;
    }

    public void setHoraSalidaGuarderia(Time horaSalidaGuarderia) {
        this.horaSalidaGuarderia = horaSalidaGuarderia;
    }

    public Time getHoraInicioAct() {
        return horaInicioAct;
    }

    public void setHoraInicioAct(Time horaInicioAct) {
        this.horaInicioAct = horaInicioAct;
    }

    public Time getHoraFinAct() {
        return horaFinAct;
    }

    public void setHoraFinAct(Time horaFinAct) {
        this.horaFinAct = horaFinAct;
    }

    public String getNombreAct() {
        return nombreAct;
    }

    public void setNombreAct(String nombreAct) {
        this.nombreAct = nombreAct;
    }

    public Date getFechaActividad() {
        return fechaActividad;
    }

    public void setFechaActividad(Date fechaActividad) {
        this.fechaActividad = fechaActividad;
    }
    
    
}
