/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.MedicamentoView;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author gabrielbaron
 */
public class MedicamentoController {
    private MedicamentoView initialView;
    private ArrayList<Medicamento> medicamentos;
    private MedicamentoDAO medicamentoimpl = new MedicamentoDAOImpl();
    public MedicamentoController(MedicamentoView initialView) {
        this.initialView = initialView;
    }
     public void getMedicamentos(){
        this.medicamentos = medicamentoimpl.getMedicamentos();
    }
     public void llenarTablaMedicamentos(){
        DefaultTableModel modelomedicamentos = (DefaultTableModel)this.initialView.Tabla_Medicamentos.getModel();
        getMedicamentos();
        int rowCount = modelomedicamentos.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modelomedicamentos.removeRow(i);
        }
        Object[] column = new Object[1];
        int n = this.medicamentos.size();
        for (int i=0; i<n; i++){
              column[0] = medicamentos.get(i).getDescripcion();
              modelomedicamentos.addRow(column);
        }
        
    } 
}
