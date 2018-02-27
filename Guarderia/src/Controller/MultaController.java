/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.InitialView;
import java.sql.SQLException;
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
public class MultaController {
   Nino nino;
   InitialView initialView;
   ArrayList<Asistencia> asistencias;

    public MultaController(InitialView initialView, Nino nino) {
        this.nino = nino;
        this.initialView = initialView;
    }
    
    public void loadMultas(JTable tabla){
       DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);
        AsistenciaDAOImpl bdAssist = new AsistenciaDAOImpl();
        asistencias = bdAssist.getMultas(nino);
        Object[] columna = new Object[3];
        try{
            for(int i = 0; i<asistencias.size(); i++){
                columna[0] = asistencias.get(i).getFecha();
                columna[1] = asistencias.get(i).getCostoMulta();
                System.out.println(asistencias.get(i).getNumTransferencia());
                if (asistencias.get(i).getNumTransferencia() == 0)
                    columna[2] = "No";
                else
                    columna[2] = "Si";
                modeloTabla.addRow(columna);
            }
        } catch(Exception e) {
            for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);

        }
    }
   
    public void pagarMulta(JTable tabla){
        int index = tabla.getSelectedRow();
        System.out.println(asistencias.size());
        Asistencia assist = asistencias.get(index);
        assist.setNumTransferencia(Long.parseLong(initialView.transferenciaMultaBtn.getText()));
        AsistenciaDAOImpl bdAssist = new AsistenciaDAOImpl();
       try {
           bdAssist.pagarMulta(assist);
           JOptionPane.showMessageDialog(initialView, "Datos cargads satisfactoriamente");
           loadMultas(tabla);
       } catch (SQLException ex) {
           Logger.getLogger(MultaController.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(initialView, "Error en los datos");
       }
        
    }
   
   
}
