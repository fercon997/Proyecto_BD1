/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.EnfermedadView;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author gabrielbaron
 */
public class EnfermedadController {
    private EnfermedadView initialView;
    private ArrayList<Enfermedad> enfermedades;
    private EnfermedadDAO enfermedadimpl = new EnfermedadDAOImpl();
    public EnfermedadController(EnfermedadView initialView) {
        this.initialView = initialView;
    }
     public void getEnfermedades(){
        this.enfermedades = enfermedadimpl.getEnfermedades();
    }
     public void llenarTablaEnfermedades(){
        DefaultTableModel modeloenfermedades = (DefaultTableModel)this.initialView.Tabla_Enfermedades.getModel();
        getEnfermedades();
        int rowCount = modeloenfermedades.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modeloenfermedades.removeRow(i);
        }
        Object[] column = new Object[1];
        int n = this.enfermedades.size();
        for (int i=0; i<n; i++){
              column[0] = enfermedades.get(i).getDescripcion();
              modeloenfermedades.addRow(column);
        }
        
    } 
}
