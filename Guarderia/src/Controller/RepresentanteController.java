/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.InitialView;
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
public class RepresentanteController {
    InitialView initialView;
    ArrayList<String> rifs;
    
   public RepresentanteController(InitialView initialView){
        this.initialView = initialView;
        loadRifs();
    }
    
    private void loadRifs() {
        GuarderiaDAOImpl modeloGuarderia = new GuarderiaDAOImpl();
        rifs = modeloGuarderia.getRifs();
    }
    
    public void llenarRepresentantes(JComboBox cb, JTable tabla){
        DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        int numGuard = cb.getSelectedIndex();
        for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);
        Object[] columna = new Object[4];
        try{
            RepresentanteDAOImpl bdParents = new RepresentanteDAOImpl();
            ArrayList<Representante> parents = bdParents.loadRepresentantes(rifs.get(numGuard-1));
            for(int i = 0; i<parents.size(); i++){
                columna[0] = parents.get(i).getCi();
                columna[1] = parents.get(i).getNombre();
                columna[2] = parents.get(i).getApellido();
                columna[3] = parents.get(i).getPrincipal();
                modeloTabla.addRow(columna);
            }
        } catch(Exception e){
            for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
              modeloTabla.removeRow(i);
        }
    }  
    
    public void mostrarRepresentante(JTable tabla){
        try{
            RepresentanteDAOImpl bdParent = new RepresentanteDAOImpl();
            String ci = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
  
            Representante parent = bdParent.showDatosRepresentante(ci);
            LugarDAOImpl modeloLugar = new LugarDAOImpl();
            Lugar lugar = modeloLugar.getDatosLugar("SELECT cod_direccion " + 
                    " FROM representante_4, lugar_4 WHERE ci = '" + parent.getCi() + 
                    "' AND cod_direccion = codigo");
            initialView.ciLabel.setText(String.valueOf(parent.getCi()));
            initialView.nombreText.setText(parent.getNombre());
            initialView.apellidoText.setText(parent.getApellido());
            initialView.celularText.setText(String.valueOf(parent.getCelular()));
            initialView.tlfCasaText.setText(String.valueOf(parent.getTlf_casa()));
            initialView.tlfOficinaText.setText(String.valueOf(parent.getTlf_oficina()));
            initialView.emailText.setText(parent.getEmail());
            initialView.ciudadLabel.setText(lugar.getCiudad());
            initialView.municipioLabel3.setText(lugar.getMunicipio());
            initialView.calleLabel3.setText(lugar.getCalle());
            initialView.casaLabel3.setText(lugar.getCasa());
            initialView.profesionText.setText(parent.getProfesion());
            if (parent.getEdo_civil() == 'C')
              initialView.edoCivilText.setText("Casado/a");
            else
                initialView.edoCivilText.setText("Soltera/o");
            String ninos = new String();
            for (int i = 0; i<parent.getNinos().size(); i++){
                String aux = parent.getNinos().get(i)+" ";
                ninos = ninos + aux;
            }
            initialView.ninosLabel.setText(ninos);
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
    
    public void eliminarRepresentante(){
        try{
            String ci = initialView.ciLabel.getText();
            System.out.println(ci);
            int confirmacion = JOptionPane.showConfirmDialog(initialView, "Está seguro que quiere borrar este Representante?"+
                    "(Se borraran todos los datos relacionados a el)", "Borrar Representante", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirmacion == JOptionPane.YES_OPTION){
                RepresentanteDAOImpl bdParent = new RepresentanteDAOImpl();
                bdParent.deleteRepresentante(ci);
                JOptionPane.showMessageDialog(initialView, "Borrado");
            }
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(initialView, "No se puede borrar");
        }
    }
    
    public void editarRepresentante(){
        Representante parent = new Representante();
        try {
            parent.setCi(initialView.ciLabel.getText());
            parent.setApellido(initialView.apellidoText.getText());
            parent.setCelular(Long.parseLong(initialView.celularText.getText()));
            parent.setEdo_civil(initialView.edoCivilText.getText().charAt(0));
            parent.setEmail(initialView.emailText.getText());
            parent.setNombre(initialView.nombreText.getText());
            parent.setProfesion(initialView.profesionText.getText());
            parent.setTlf_casa(Long.parseLong(initialView.tlfCasaText.getText()));
            parent.setTlf_oficina(Long.parseLong(initialView.tlfOficinaText.getText()));
            RepresentanteDAOImpl bdParent = new RepresentanteDAOImpl();
            bdParent.updateRepresentante(parent);
            JOptionPane.showMessageDialog(initialView, "Datos cargados satisfactoriamente");
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(initialView, "No se puede actualizar");
        }
        bloquear();
        limpiarDatos();
        llenarRepresentantes(initialView.jComboGuarderias4, initialView.tablaRepresentantes);
    }
    
    public void bloquear(){
        initialView.nombreText.setEnabled(false);
        initialView.apellidoText.setEnabled(false);
        initialView.celularText.setEnabled(false);
        initialView.tlfCasaText.setEnabled(false);
        initialView.tlfOficinaText.setEnabled(false);
        initialView.emailText.setEnabled(false);
        initialView.profesionText.setEnabled(false);
        initialView.edoCivilText.setEnabled(false);
        initialView.saveRepresentanteBtn.setEnabled(false);
    }

    public void habilitar(){
        initialView.nombreText.setEnabled(true);
        initialView.apellidoText.setEnabled(true);
        initialView.celularText.setEnabled(true);
        initialView.tlfCasaText.setEnabled(true);
        initialView.tlfOficinaText.setEnabled(true);
        initialView.emailText.setEnabled(true);
        initialView.profesionText.setEnabled(true);
        initialView.edoCivilText.setEnabled(true);
        initialView.saveRepresentanteBtn.setEnabled(true);
    }
    
    public void limpiarDatos(){
        initialView.nombreText.setText("");
        initialView.apellidoText.setText("");
        initialView.celularText.setText("");
        initialView.tlfCasaText.setText("");
        initialView.tlfOficinaText.setText("");
        initialView.emailText.setText("");
        initialView.profesionText.setText("");
        initialView.edoCivilText.setText("");
        initialView.ciLabel.setText("");
        initialView.ciudadLabel.setText("");
        initialView.municipioLabel3.setText("");
        initialView.calleLabel3.setText("");
        initialView.casaLabel3.setText("");
        initialView.ninosLabel.setText("");
        
    }
    

}
