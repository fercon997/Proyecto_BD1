/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.JDAddJuego;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author fercon997
 */
public class AddJuegoController {
    JDAddJuego vistaAJ;

    public AddJuegoController(JDAddJuego vistaAJ) {
        this.vistaAJ = vistaAJ;
    }
    
     public void agregarJuego(){
        Juego game = new Juego();
        try{
            game.setNombre(vistaAJ.nombreTxt.getText());
            JuegoDAOImpl bdGame = new JuegoDAOImpl();
            bdGame.insertarJuego(game);
            JOptionPane.showMessageDialog(vistaAJ, "Datos cargados satisfactoriamente");
            vistaAJ.dispose();
        }catch(Exception e){
            Logger.getLogger(AddJuegoController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(vistaAJ, "Error en los datos");
        }
    }
    
    
}
