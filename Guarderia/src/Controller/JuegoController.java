/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.InitialView;
import View.JDAddJuego;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fercon997
 */
public class JuegoController {
    InitialView initialView;

    public JuegoController(InitialView initialView) {
        this.initialView = initialView;
    }
    
    public void mostrarTabla(JTable tabla){
        DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);
        Object[] columna = new Object[2];
        try{
             JuegoDAOImpl bdGames = new JuegoDAOImpl();
            ArrayList<Juego> games = bdGames.getJuegos();
            for(int i = 0; i<games.size(); i++){
                System.out.println(games.get(i).getCodigo());
                columna[0] = games.get(i).getCodigo();
                columna[1] = games.get(i).getNombre();
                modeloTabla.addRow(columna);
            }
        } catch(Exception e){
            for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
                modeloTabla.removeRow(i);   
        }
    }
    
    public void datosJuego(JTable tabla){
        try{
            JuegoDAOImpl bdGame = new JuegoDAOImpl();
            int codigo = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
            Juego game = bdGame.informacionJuego(codigo);
            initialView.codigoLabel.setText(String.valueOf(game.getCodigo()));
            initialView.nombreText1.setText(game.getNombre());
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            initialView.codigoLabel.setText("");
            initialView.nombreText1.setText("");

        }
    }
    
    public void editarJuego(){
        Juego game = new Juego();
        try {
            game.setCodigo(Integer.parseInt(initialView.codigoLabel.getText()));
            game.setNombre(initialView.nombreText1.getText());
            JuegoDAOImpl bdGame = new JuegoDAOImpl();
            bdGame.updateJuego(game);
            JOptionPane.showMessageDialog(initialView, "Datos cargados satisfactoriamente");
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(initialView, "No se puede actualizar");
        }
        bloquear();
        limpiarDatos();
        mostrarTabla(initialView.tablaJuegos);
    }
    
    public void bloquear(){
        initialView.nombreText1.setEnabled(false);
        initialView.saveJuegoBtn.setEnabled(false);
    }

    public void habilitar(){
        initialView.nombreText1.setEnabled(true);
        initialView.saveJuegoBtn.setEnabled(true);
    }
    
    public void limpiarDatos(){
        initialView.nombreText1.setText("");
        initialView.saveJuegoBtn.setText("");
        
        
    }
    
    public void eliminarJuego(){
        try{
            int codigo = Integer.parseInt(initialView.codigoLabel.getText());
            int confirmacion = JOptionPane.showConfirmDialog(initialView, "Está seguro que quiere borrar este Juego?"+
                    "(Se borraran todos los datos relacionados a el)", "Borrar Juego", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirmacion == JOptionPane.YES_OPTION){
                JuegoDAOImpl bdGame = new JuegoDAOImpl();
                bdGame.deleteJuego(codigo);
                JOptionPane.showMessageDialog(initialView, "Borrado");
            }
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(initialView, "No se puede borrar");
        }
    }
    
    public void agregarJuego(){
        JDAddJuego vistaAJ = new JDAddJuego(initialView, true);
        vistaAJ.setVisible(true);
    }
}
