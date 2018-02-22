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
import View.EditGuarderiaView;
import View.InitialView;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ignacio
 */
public class EditGuarderiaController {
    
    EditGuarderiaView editGuarderiaView;
    InitialView initialView;
    LugarDAOImpl modeloLugar = new LugarDAOImpl();
    GuarderiaDAOImpl modeloGuarderia = new GuarderiaDAOImpl();
    ArrayList<Lugar> municipios = new ArrayList();
    int codDireccion = 0;
    
    public EditGuarderiaController(EditGuarderiaView editGuarderiaView, InitialView initialView, int codDireccion) {
        this.editGuarderiaView = editGuarderiaView;
        this.initialView = initialView;
        this.codDireccion = codDireccion;
        loadFields();
    }
    
    private void loadFields() {
        loadLugares();
        editGuarderiaView.calleComboBox.setSelectedIndex(1);
        editGuarderiaView.casaComboBox.setSelectedIndex(0);
        editGuarderiaView.ciudadComboBox.setSelectedItem(initialView.ciudadLabel.getText());
        editGuarderiaView.estadoComboBox.setSelectedItem(initialView.estadoLabel.getText());
        editGuarderiaView.municipioComboBox.setSelectedItem(initialView.municipioLabel.getText());
        editGuarderiaView.calleTextField.setText(initialView.calleLabel.getText());
        editGuarderiaView.casaTextField.setText(initialView.casaLabel.getText());
        editGuarderiaView.rifTextField.setText(initialView.rifLabel.getText());
        editGuarderiaView.nombreTextField.setText(initialView.jComboGuarderias.getSelectedItem().toString());
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        try {
            java.util.Date horaEntrada = (java.util.Date) formatter.parse(initialView.horaEntradaLabel.getText());
            java.util.Date horaSalida = (java.util.Date) formatter.parse(initialView.horaSalidaLabel.getText());
            editGuarderiaView.horaEntradaSpinner.setValue(horaEntrada);
            editGuarderiaView.horaSalidaSpinner.setValue(horaSalida);
        } catch (ParseException ex) {
            Logger.getLogger(EditGuarderiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadLugares() { 
        ArrayList<Lugar> ciudades = new ArrayList();
        ArrayList<Lugar> estados = new ArrayList();
        ciudades = modeloLugar.getCiudades();
        estados = modeloLugar.getEstados();
        municipios = modeloLugar.getMunicipios();
        
        int ciudadSize = ciudades.size();
        int estadoSize = estados.size();
        int municipioSize = municipios.size();
        
        for (int i = 0; i < ciudadSize; i++) {
            editGuarderiaView.ciudadComboBox.addItem(ciudades.get(i).getNombre());
        }
        
        for (int i = 0; i < estadoSize; i++) {
            editGuarderiaView.estadoComboBox.addItem(estados.get(i).getNombre());
        }
        
        for (int i = 0; i < municipioSize; i++) {
            editGuarderiaView.municipioComboBox.addItem(municipios.get(i).getNombre());
        }
    }
    
    public void updateGuarderia() {
        int codigo = codDireccion;
        Guarderia guarderia = new Guarderia();
        guarderia.setCodDireccion(codigo);
        guarderia.setNombre(editGuarderiaView.nombreTextField.getText());
        guarderia.setRif(editGuarderiaView.rifTextField.getText());
        guarderia.setCostoMensualidad(0);
        guarderia.setCostoMulta(0);
        guarderia.setConstoTransporte(0);
        guarderia.setCostoAgoDic(0);
        guarderia.setCostoHoraExtra(0);
        java.util.Date utilDate = (java.util.Date) editGuarderiaView.horaEntradaSpinner.getValue();
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
        guarderia.setHoraEntrada(sqlTime);
        utilDate = (java.util.Date) editGuarderiaView.horaSalidaSpinner.getValue();
        sqlTime = new java.sql.Time(utilDate.getTime());
        guarderia.setHoraSalida(sqlTime);
        modeloGuarderia.updateGuarderia(guarderia);
    }
}
