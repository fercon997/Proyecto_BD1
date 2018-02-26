/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.SintomaView;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author gabrielbaron
 */
public class SintomaController {
    private SintomaView initialView;
    private ArrayList<Sintoma> sintomas;
    private SintomaDAO sintomaimpl = new SintomaDAOImpl();
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
        Object[] column = new Object[2];
        int n = this.sintomas.size();
        for (int i=0; i<n; i++){
              column[0] = sintomas.get(i).getCodigo();
              column[1] = sintomas.get(i).getDescripcion();
              modelosintomas.addRow(column);
        }
        
    } 
     public void datosSintoma(){
        try{
            SintomaDAOImpl bdsintoma = new SintomaDAOImpl();
            int codigo = Integer.parseInt(this.initialView.Tabla_Sintomas.getValueAt(this.initialView.Tabla_Sintomas.getSelectedRow(), 0).toString());
            Sintoma sintoma = bdsintoma.informacionSintoma(codigo);
            initialView.codigoLabel.setText(String.valueOf(sintoma.getCodigo()));
            initialView.nombreText1.setText(sintoma.getDescripcion());
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            initialView.nombreText1.setText("");

        }
    }
    public void editarSintoma(){
        Sintoma sintoma = new Sintoma();
        try {
            sintoma.setCodigo(Integer.parseInt(initialView.codigoLabel.getText()));
            sintoma.setDescripcion(initialView.nombreText1.getText());
            SintomaDAOImpl bdSintoma = new SintomaDAOImpl();
            bdSintoma.updateSintoma(sintoma);
            JOptionPane.showMessageDialog(initialView, "Datos cargados satisfactoriamente");
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(initialView, "No se puede actualizar");
        }
        bloquear();
        limpiarDatos();
        llenarTablaSintomas();
    }
    public void eliminarSintoma(){
        try{
            int codigo = Integer.parseInt(initialView.codigoLabel.getText());
            int confirmacion = JOptionPane.showConfirmDialog(initialView, "Está seguro que quiere borrar este Sintoma?"+
                    "(Se borraran todos los datos relacionados a el)", "Borrar Sintoma", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirmacion == JOptionPane.YES_OPTION){
                SintomaDAOImpl sintoma = new SintomaDAOImpl();
                sintoma.deleteSintoma(codigo);
                JOptionPane.showMessageDialog(initialView, "Borrado");
            }
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(initialView, "No se puede borrar");
        }
    } 
     public void bloquear(){
        initialView.nombreText1.setEnabled(false);
        initialView.saveSintomaBtn.setEnabled(false);
    }

    public void habilitar(){
        initialView.nombreText1.setEnabled(true);
        initialView.saveSintomaBtn.setEnabled(true);
    }
    
    public void limpiarDatos(){
        initialView.nombreText1.setText("");
        initialView.saveSintomaBtn.setText(""); 
    }
}
