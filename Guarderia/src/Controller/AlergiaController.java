/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.AlergiaView;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
        Object[] column = new Object[2];
        int n = this.alergias.size();
        for (int i=0; i<n; i++){
              column[0] = alergias.get(i).getCodigo();
              column[1] = alergias.get(i).getDescripcion();
              modeloalergias.addRow(column);
        }
        
    }
    public void datosAlergia(){
        try{
            AlergiaDAOImpl bdalergia = new AlergiaDAOImpl();
            int codigo = Integer.parseInt(this.initialView.Tabla_Alergias.getValueAt(this.initialView.Tabla_Alergias.getSelectedRow(), 0).toString());
            Alergia alergia = bdalergia.informacionAlergia(codigo);
            initialView.codigoLabel.setText(String.valueOf(alergia.getCodigo()));
            initialView.nombreText1.setText(alergia.getDescripcion());
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            initialView.nombreText1.setText("");

        }
    }
     public void editarAlergia(){
        Alergia alergia = new Alergia();
        try {
            alergia.setCodigo(Integer.parseInt(initialView.codigoLabel.getText()));
            alergia.setDescripcion(initialView.nombreText1.getText());
            AlergiaDAOImpl bdAlergia = new AlergiaDAOImpl();
            bdAlergia.updateAlergia(alergia);
            JOptionPane.showMessageDialog(initialView, "Datos cargados satisfactoriamente");
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(initialView, "No se puede actualizar");
        }
        bloquear();
        limpiarDatos();
        llenarTablaAlergias();
    }
    public void eliminarAlergia(){
        try{
            int codigo = Integer.parseInt(initialView.codigoLabel.getText());
            int confirmacion = JOptionPane.showConfirmDialog(initialView, "Está seguro que quiere borrar esta Alergia?"+
                    "(Se borraran todos los datos relacionados a el)", "Borrar Alergia", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirmacion == JOptionPane.YES_OPTION){
                AlergiaDAOImpl alergia = new AlergiaDAOImpl();
                alergia.deleteAlergia(codigo);
                JOptionPane.showMessageDialog(initialView, "Borrado");
            }
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(initialView, "No se puede borrar");
        }
    } 
    public void bloquear(){
        initialView.nombreText1.setEnabled(false);
        initialView.saveAlergiaBtn.setEnabled(false);
    }

    public void habilitar(){
        initialView.nombreText1.setEnabled(true);
        initialView.saveAlergiaBtn.setEnabled(true);
    }
    
    public void limpiarDatos(){
        initialView.nombreText1.setText("");
        initialView.saveAlergiaBtn.setText(""); 
    }
}
