/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import View.*;
import Model.*;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author gabrielbaron
 */
public class PediatraController {
    private PediatraView initialView;
    private ArrayList<Pediatra> pediatras;
    private PediatraDAO pediatraimpl = new PediatraDAOImpl();
    public PediatraController(PediatraView initialView) {
        this.initialView = initialView;
    }
    public void getPediatras(){
        this.pediatras = pediatraimpl.getPediatras();
    }
    public void llenarComboBoxPediatras(){
        DefaultTableModel modelopediatras = (DefaultTableModel)this.initialView.Tabla_Pediatras.getModel();
        getPediatras();
        int rowCount = modelopediatras.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modelopediatras.removeRow(i);
        }
        Object[] column = new Object[3];
        int n = this.pediatras.size();
        for (int i=0; i<n; i++){
              column[0] = pediatras.get(i).getNombre();
              column[1] = pediatras.get(i).getTlf_movil();
              column[2] = pediatras.get(i).getTlf_oficina();
              modelopediatras.addRow(column);
        }
        
    }
    
    
}
