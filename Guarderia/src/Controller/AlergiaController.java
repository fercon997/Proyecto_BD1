/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.AlergiaView;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gabrielbaron
 */
public class AlergiaController {
    private AlergiaView initialView;
    private ArrayList<Alergia> alergias;
    private AlergiaDAO alergiaimpl = new AlergiaDAOImpl();
    public AlergiaController(AlergiaView initialView) {
        this.initialView = initialView;
    }
    public void getAlergias(){
        this.alergias = alergiaimpl.getAlergias();
    }
    public void llenarTablaAlergias(){
        DefaultTableModel modeloalergias = (DefaultTableModel)this.initialView.Tabla_Alergias.getModel();
        getAlergias();
        int rowCount = modeloalergias.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modeloalergias.removeRow(i);
        }
        Object[] column = new Object[1];
        int n = this.alergias.size();
        for (int i=0; i<n; i++){
              column[0] = alergias.get(i).getDescripcion();
              modeloalergias.addRow(column);
        }
        
    }
}
