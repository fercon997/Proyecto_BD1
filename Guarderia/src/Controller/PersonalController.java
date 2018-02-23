/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.GuarderiaDAOImpl;
import Model.Lugar;
import Model.LugarDAOImpl;
import Model.Personal;
import Model.PersonalDAOImpl;
import View.InitialView;
import View.JDAddPersonal;
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
 * @author Ignacio
 */
public class PersonalController {
    InitialView initialView;
    ArrayList<String> rifs;
    PersonalDAOImpl modeloPersonal;
    
   public PersonalController(InitialView initialView){
        this.initialView = initialView;
        loadRifs();
        modeloPersonal = new PersonalDAOImpl();
    }
    
    private void loadRifs() {
        GuarderiaDAOImpl modeloGuarderia = new GuarderiaDAOImpl();
        rifs = modeloGuarderia.getRifs();
    }
    
    public void tabbedPaneTouched() {
        if (initialView.jTabbedPane1.getSelectedIndex() == 5) {
            bloquear();
            llenarPersonal(initialView.jComboPersonal, initialView.tablaPersonal);
        }
    }
    
    public void llenarPersonal(JComboBox cb, JTable tabla){
        DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        int numGuard = cb.getSelectedIndex();
        for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);
        Object[] columna = new Object[5];
        try{
            ArrayList<Personal> personal = modeloPersonal.loadPersonal(rifs.get(numGuard-1));
            for(int i = 0; i<personal.size(); i++){
                columna[0] = personal.get(i).getCi();
                columna[1] = personal.get(i).getNombre();
                columna[2] = personal.get(i).getApellido();
                columna[3] = personal.get(i).getCelular();
                columna[4] = personal.get(i).getSueldo();
                modeloTabla.addRow(columna);
            }
        } catch(Exception e){
            ArrayList<Personal> personal = modeloPersonal.loadAllPersonal();
            for(int i = 0; i<personal.size(); i++){
                columna[0] = personal.get(i).getCi();
                columna[1] = personal.get(i).getNombre();
                columna[2] = personal.get(i).getApellido();
                columna[3] = personal.get(i).getCelular();
                columna[4] = personal.get(i).getSueldo();
                modeloTabla.addRow(columna);
            }
        }
    }  
    
    public void mostrarPersonal(JTable tabla){
        try{
            String ci = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
  
            Personal personal = modeloPersonal.showDatosPersonal(ci);
            LugarDAOImpl modeloLugar = new LugarDAOImpl();
            Lugar lugar = modeloLugar.getDatosLugar("SELECT cod_direccion " + 
                    " FROM personal_4, lugar_4 WHERE ci = '" + personal.getCi() + 
                    "' AND cod_direccion = codigo");
            initialView.ciPersonalLabel.setText(String.valueOf(personal.getCi()));
            initialView.nombrePersonalText.setText(personal.getNombre());
            initialView.apellidoPersonalText.setText(personal.getApellido());
            initialView.celularPersonalText.setText(String.valueOf(personal.getCelular()));
            initialView.nivelEstudio.setSelectedItem(personal.getNivelEstudio());
            initialView.sueldoPersonalText.setText(String.valueOf(personal.getSueldo()));
            if (personal.getEncargada() == 1) {
                initialView.encargadaCheckBox.setSelected(true);
            } else {
                initialView.encargadaCheckBox.setSelected(false);
            }
            initialView.fechaEncargadaText.setText(String.valueOf(personal.getFechaResponsable()));
            initialView.ciudadPersonalLabel.setText(lugar.getCiudad());
            initialView.municipioPersonalLabel.setText(lugar.getMunicipio());
            initialView.callePersonalLabel.setText(lugar.getCalle());
            initialView.casaPersonalLabel.setText(lugar.getCasa());
            String experiencia = new String();
            for (int i = 0; i<personal.getExperiencia().size(); i++){
                String aux = personal.getExperiencia().get(i)+" ";
                experiencia = experiencia + aux;
            }
            initialView.experienciaTextArea.setText(experiencia);
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            initialView.ciLabel.setText("");
            initialView.nombreText.setText("");
            initialView.apellidoText.setText("");
            initialView.celularText.setText("");
            initialView.tlfCasaText.setText("");
            initialView.tlfOficinaText.setText("");
            initialView.emailText.setText("");
            initialView.ciudadLabel.setText("");
            initialView.municipioLabel3.setText("");
            initialView.calleLabel3.setText("");
            initialView.casaLabel3.setText("");
            initialView.profesionText.setText("");
            initialView.edoCivilText.setText("");
            initialView.ninosLabel.setText("");

        }
    }
    
    public void eliminarPersonal(){
        try{
            String ci = initialView.ciPersonalLabel.getText();
            System.out.println(ci);
            int confirmacion = JOptionPane.showConfirmDialog(initialView, "Está seguro que quiere borrar este Representante?"+
                    "(Se borraran todos los datos relacionados a el)", "Borrar Representante", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirmacion == JOptionPane.YES_OPTION){
                modeloPersonal.deletePersonal(ci);
                JOptionPane.showMessageDialog(initialView, "Borrado");
            }
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(initialView, "No se puede borrar");
        }
    }
    
    public void editarPersonal(){
        Personal personal = new Personal();
        try {
            personal.setCi(initialView.ciPersonalLabel.getText());
            personal.setApellido(initialView.apellidoPersonalText.getText());
            personal.setCelular(initialView.celularPersonalText.getText());
            personal.setNombre(initialView.nombrePersonalText.getText());
            if (initialView.encargadaCheckBox.isSelected()) {
                personal.setEncargada(1);
            } else {
                personal.setEncargada(0);
            }
            String fecha = initialView.fechaEncargadaText.getText();
            if (fecha == "") {
                personal.setFechaResponsable(null);
            } else {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf1.parse(fecha);
                java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
                personal.setFechaResponsable(sqlStartDate);
            }
            personal.setNivelEstudio(String.valueOf(initialView.nivelEstudio.getSelectedItem()));
            personal.setSueldo(Integer.parseInt(initialView.sueldoPersonalText.getText()));
            modeloPersonal.updatePersonal(personal);
            JOptionPane.showMessageDialog(initialView, "Datos cargados satisfactoriamente");
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(initialView, "No se puede actualizar");
        }
        bloquear();
        limpiarDatos();
        llenarPersonal(initialView.jComboGuarderias4, initialView.tablaRepresentantes);
    }
    
    public void bloquear(){
        initialView.nombrePersonalText.setEnabled(false);
        initialView.apellidoPersonalText.setEnabled(false);
        initialView.celularPersonalText.setEnabled(false);
        initialView.ciPersonalLabel.setEnabled(false);
        initialView.ciudadPersonalLabel.setEnabled(false);
        initialView.municipioPersonalLabel.setEnabled(false);
        initialView.callePersonalLabel.setEnabled(false);
        initialView.casaPersonalLabel.setEnabled(false);
        initialView.experienciaTextArea.setEnabled(false);
        initialView.encargadaCheckBox.setEnabled(false);
        initialView.fechaEncargadaText.setEnabled(false);
        initialView.nivelEstudio.setEnabled(false);
        initialView.sueldoPersonalText.setEnabled(false);
    }

    public void habilitar(){
        initialView.nombrePersonalText.setEnabled(true);
        initialView.apellidoPersonalText.setEnabled(true);
        initialView.celularPersonalText.setEnabled(true);
        initialView.ciPersonalLabel.setEnabled(true);
        initialView.ciudadPersonalLabel.setEnabled(true);
        initialView.municipioPersonalLabel.setEnabled(true);
        initialView.callePersonalLabel.setEnabled(true);
        initialView.casaPersonalLabel.setEnabled(true);
        initialView.encargadaCheckBox.setEnabled(true);
        initialView.fechaEncargadaText.setEnabled(true);
        initialView.nivelEstudio.setEnabled(true);
        initialView.sueldoPersonalText.setEnabled(true);
    }
    
    public void limpiarDatos(){
        initialView.nombrePersonalText.setText("");
        initialView.apellidoPersonalText.setText("");
        initialView.celularPersonalText.setText("");
        initialView.ciPersonalLabel.setText("");
        initialView.ciudadPersonalLabel.setText("");
        initialView.municipioPersonalLabel.setText("");
        initialView.callePersonalLabel.setText("");
        initialView.casaPersonalLabel.setText("");
        initialView.experienciaTextArea.setText("");
        initialView.encargadaCheckBox.setSelected(false);
        initialView.fechaEncargadaText.setText("aaaa-mm-dd");
        initialView.nivelEstudio.setSelectedIndex(0);
        initialView.sueldoPersonalText.setText("");
    }
    
    public void agregarPersonal(){
        JDAddPersonal vistaAR = new JDAddPersonal(initialView, true);
        vistaAR.setVisible(true);
    }
        
}
