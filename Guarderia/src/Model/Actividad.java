/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Ignacio
 */
public class Actividad {
    int codigo;
    String nombre;
    Time horaInicio;
    Time horaFin;
    Date fecha;
    int anoInsc;
    int consInsc;
    int edadMinima;
    int transporte;
    int cuposDisponible;
    int cupoMax;
    String ciRepresentante;
    char letraNino;
    String rifGuarderia;
    int dia;
    String nombrePersonal;

    public Actividad() {  
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

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getAnoInsc() {
        return anoInsc;
    }

    public void setAnoInsc(int anoInsc) {
        this.anoInsc = anoInsc;
    }

    public int getConsInsc() {
        return consInsc;
    }

    public void setConsInsc(int consInsc) {
        this.consInsc = consInsc;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    public int getTransporte() {
        return transporte;
    }

    public void setTransporte(int transporte) {
        this.transporte = transporte;
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

    public String getRifGuarderia() {
        return rifGuarderia;
    }

    public void setRifGuarderia(String rifGuarderia) {
        this.rifGuarderia = rifGuarderia;
    }

    public int getCuposDisponible() {
        return cuposDisponible;
    }

    public void setCuposDisponible(int cuposDisponible) {
        this.cuposDisponible = cuposDisponible;
    }

    public int getCupoMax() {
        return cupoMax;
    }

    public void setCupoMax(int cupoMax) {
        this.cupoMax = cupoMax;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getNombrePersonal() {
        return nombrePersonal;
    }

    public void setNombrePersonal(String nombrePersonal) {
        this.nombrePersonal = nombrePersonal;
    }
    
}
