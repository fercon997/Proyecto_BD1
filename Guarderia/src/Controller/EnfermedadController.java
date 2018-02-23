/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.EnfermedadView;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
        Object[] column = new Object[2];
        int n = this.enfermedades.size();
        for (int i=0; i<n; i++){
              column[0] = enfermedades.get(i).getCodigo();
              column[1] = enfermedades.get(i).getDescripcion();
              modeloenfermedades.addRow(column);
        }
        
    }
     public void datosEnfermedad(){
        try{
            EnfermedadDAOImpl bdenfermedad = new EnfermedadDAOImpl();
            int codigo = Integer.parseInt(this.initialView.Tabla_Enfermedades.getValueAt(this.initialView.Tabla_Enfermedades.getSelectedRow(), 0).toString());
            Enfermedad enfermedad = bdenfermedad.informacionEnfermedad(codigo);
            initialView.codigoLabel.setText(String.valueOf(enfermedad.getCodigo()));
            initialView.nombreText1.setText(enfermedad.getDescripcion());
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            initialView.nombreText1.setText("");

        }
    }
     public void editarEnfermedad(){
        Enfermedad enfermedad = new Enfermedad();
        try {
            enfermedad.setCodigo(Integer.parseInt(initialView.codigoLabel.getText()));
            enfermedad.setDescripcion(initialView.nombreText1.getText());
            EnfermedadDAOImpl bdEnfermedad = new EnfermedadDAOImpl();
            bdEnfermedad.updateEnfermedad(enfermedad);
            JOptionPane.showMessageDialog(initialView, "Datos cargados satisfactoriamente");
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(initialView, "No se puede actualizar");
        }
        bloquear();
        limpiarDatos();
        llenarTablaEnfermedades();
    }
    public void eliminarEnfermedad(){
        try{
            int codigo = Integer.parseInt(initialView.codigoLabel.getText());
            int confirmacion = JOptionPane.showConfirmDialog(initialView, "Está seguro que quiere borrar esta Enfermedad?"+
                    "(Se borraran todos los datos relacionados a el)", "Borrar Enfermedad", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirmacion == JOptionPane.YES_OPTION){
                EnfermedadDAOImpl enfermedad = new EnfermedadDAOImpl();
                enfermedad.deleteEnfermedad(codigo);
                JOptionPane.showMessageDialog(initialView, "Borrado");
            }
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(initialView, "No se puede borrar");
        }
    } 
     public void bloquear(){
        initialView.nombreText1.setEnabled(false);
        initialView.saveEnfermedadBtn.setEnabled(false);
    }

    public void habilitar(){
        initialView.nombreText1.setEnabled(true);
        initialView.saveEnfermedadBtn.setEnabled(true);
    }
    
    public void limpiarDatos(){
        initialView.nombreText1.setText("");
        initialView.saveEnfermedadBtn.setText(""); 
    }
}
