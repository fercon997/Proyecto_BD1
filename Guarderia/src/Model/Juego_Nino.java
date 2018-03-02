/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author fercon997
 */
public class Juego_Nino {
   int codigoJuego;
   String ciRepresentante;
   char  letraNino;

    public Juego_Nino(int codigoJuego, String ciRepresentante, char letraNino) {
        this.codigoJuego = codigoJuego;
        this.ciRepresentante = ciRepresentante;
        this.letraNino = letraNino;
    }

    public Juego_Nino() {
    }

    public int getCodigoJuego() {
        return codigoJuego;
    }

    public void setCodigoJuego(int codigoJuego) {
        this.codigoJuego = codigoJuego;
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
   
   
}
