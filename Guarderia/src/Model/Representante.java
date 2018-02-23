/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author fercon997
 */
public class Representante {
    String ci;
    String nombre;
    String apellido;
    long celular;
    long tlf_casa;
    long tlf_oficina;
    String email;
    String profesion;
    char edo_civil;
    int principal;
    int cod_direccion;
    ArrayList<String> ninos;

    public Representante(String ci, String nombre, String apellido, long celular, long tlf_casa, long tlf_oficina, String email, String profesion, char edo_civil, int principal, int cod_direccion, ArrayList<String> ninos) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.tlf_casa = tlf_casa;
        this.tlf_oficina = tlf_oficina;
        this.email = email;
        this.profesion = profesion;
        this.edo_civil = edo_civil;
        this.principal = principal;
        this.cod_direccion = cod_direccion;
        this.ninos = ninos;
    }

    public Representante() {
    }

    public ArrayList<String> getNinos() {
        return ninos;
    }

    public void setNinos(ArrayList<String> ninos) {
        this.ninos = ninos;
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

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public long getTlf_casa() {
        return tlf_casa;
    }

    public void setTlf_casa(long tlf_casa) {
        this.tlf_casa = tlf_casa;
    }

    public long getTlf_oficina() {
        return tlf_oficina;
    }

    public void setTlf_oficina(long tlf_oficina) {
        this.tlf_oficina = tlf_oficina;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public char getEdo_civil() {
        return edo_civil;
    }

    public void setEdo_civil(char edo_civil) {
        this.edo_civil = edo_civil;
    }

    public int getPrincipal() {
        return principal;
    }

    public void setPrincipal(int principal) {
        this.principal = principal;
    }

    public int getCod_direccion() {
        return cod_direccion;
    }

    public void setCod_direccion(int cod_direccion) {
        this.cod_direccion = cod_direccion;
    }
    
    
    
    
}
