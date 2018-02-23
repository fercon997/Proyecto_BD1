/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Guarderia;
import Model.GuarderiaDAOImpl;
import Model.Lugar;
import Model.LugarDAOImpl;
import Model.Personal;
import Model.PersonalDAOImpl;
import Model.Representante;
import Model.RepresentanteDAOImpl;
import View.JDAddPersonal;
import View.JDAddRepresentante;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fercon997
 */
public class AddPersonalController {
    JDAddPersonal vistaAG;
    ArrayList<String> rifs;
    GuarderiaDAOImpl modeloGuarderia = new GuarderiaDAOImpl();
    
    PersonalDAOImpl modeloPersonal = new PersonalDAOImpl();

    public AddPersonalController(JDAddPersonal vistaAG) {
        this.vistaAG = vistaAG;
        this.rifs = modeloGuarderia.getRifs();
    }
    
    public void mostrardirecciones(JTable tabla){
        DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);
        Object [] columna = new Object[3];
        try{
            LugarDAOImpl bdLugar = new LugarDAOImpl();
            ArrayList<Lugar> lugares = bdLugar.getEstados();
            for(int i = 0; i<lugares.size(); i++){
                columna[2] = lugares.get(i).getCodigo();
                columna[0] = lugares.get(i).getNombre();
                columna[1] = lugares.get(i).getTipo();
                modeloTabla.addRow(columna);
            }
            
        } catch(Exception e){
            for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
              modeloTabla.removeRow(i);
        }
        
    }
    
    public void direccionesClickTabla(JTable tabla){
        String tipo = (tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
        if (("Casa".equals(tipo)) || ("Edificio".equals(tipo))){
            vistaAG.Agregar.setEnabled(true);
            vistaAG.direccionTxt.setText(tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
        }
        int codigo = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
        DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);
        Object [] columna = new Object[3];
        try{
            LugarDAOImpl bdLugar = new LugarDAOImpl();
            ArrayList<Lugar> lugares = bdLugar.getAbajo(codigo);
            for(int i = 0; i<lugares.size(); i++){
                System.out.println(i);
                columna[2] = lugares.get(i).getCodigo();
                columna[0] = lugares.get(i).getNombre();
                columna[1] = lugares.get(i).getTipo();
                modeloTabla.addRow(columna);
            }
            
        } catch(Exception e){
            for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
              modeloTabla.removeRow(i);
        }
    }
    
    public void agregarPersonal(){
        Personal personal = new Personal();
        try{
            personal.setCi(vistaAG.ciTxt.getText());
            personal.setApellido(vistaAG.apellidoTxt.getText());
            personal.setCelular(vistaAG.celularTxt.getText());
            personal.setCod_direccion(Integer.parseInt(vistaAG.direccionTxt.getText()));
            personal.setNombre(vistaAG.nombreTxt.getText());
            if (vistaAG.encargadaCheck.isSelected()) {
                personal.setEncargada(1);
            } else {
                personal.setEncargada(0);
            }
            personal.setNivelEstudio(vistaAG.nivelEstudio.getSelectedItem().toString());
            personal.setSueldo(Integer.parseInt(vistaAG.sueldoText.getText()));
            String fecha = vistaAG.fechaEncargadaText.getText();
            if (fecha == "") {
                personal.setFechaResponsable(null);
            } else {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf1.parse(fecha);
                java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
                personal.setFechaResponsable(sqlStartDate);
            }
            personal.setRifGuarderia(rifs.get(vistaAG.guarderiaCombo.getSelectedIndex() - 1));
            modeloPersonal.insertPersonal(personal);
            vistaAG.dispose();
        }catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(vistaAG, "Error en los datos");
        }
    }
    
    public void llenarComboBoxGuarderias(JComboBox cb) {
        ArrayList<Guarderia> guarderias = new ArrayList();
        guarderias = modeloGuarderia.loadGuarderias();
        int size = guarderias.size();
        cb.addItem("Guarderías");
        for (int i = 0; i < size; i++) {
            cb.addItem(guarderias.get(i).getComboText());
        }
    }
    
}
