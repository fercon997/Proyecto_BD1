/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.InitialView;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author fercon997
 */
public class AutorizadoController {
    ArrayList<Autorizado> auths;
    InitialView initialView;

    public AutorizadoController(InitialView initialView) {
        this.initialView = initialView;
    }
    
    
    public void loadAutorizados(JTable tabla){
        DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);
        AutorizadoDAOImpl bdAuth = new AutorizadoDAOImpl();
        Object[] columna = new Object[4];
        try{
            auths = bdAuth.getAutorizados();
            for(int i = 0; i<auths.size(); i++){
                columna[0] = auths.get(i).getCi();
                columna[1] = auths.get(i).getNombre();
                columna[2] = auths.get(i).getApellido();
                columna[3] = auths.get(i).getCelular();
                modeloTabla.addRow(columna);
            }
        } catch(Exception e){
            for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);
        }
    }
    
    
    
}
