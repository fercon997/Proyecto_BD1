/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.SintomaView;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author gabrielbaron
 */
public class SintomaController {
    private SintomaView initialView;
    private ArrayList<Sintoma> sintomas;
    private SintomaDAO sintomaimpl = new SIntomaDAOImpl();
    public SintomaController(SintomaView initialView) {
        this.initialView = initialView;
    }
     public void getSintomas(){
        this.sintomas = sintomaimpl.getSintomas();
    }
     public void llenarTablaSintomas(){
        DefaultTableModel modelosintomas = (DefaultTableModel)this.initialView.Tabla_Sintomas.getModel();
        getSintomas();
        int rowCount = modelosintomas.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modelosintomas.removeRow(i);
        }
        Object[] column = new Object[1];
        int n = this.sintomas.size();
        for (int i=0; i<n; i++){
              column[0] = sintomas.get(i).getDescripcion();
              modelosintomas.addRow(column);
        }
        
    } 
}
