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
import View.AddGuarderiaView;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Ignacio
 */
public class AddGuarderiaController {
    
    AddGuarderiaView addGuarderiaView;
    LugarDAOImpl modeloLugar = new LugarDAOImpl();
    GuarderiaDAOImpl modeloGuarderia = new GuarderiaDAOImpl();
    ArrayList<Lugar> municipios = new ArrayList();
    
    public AddGuarderiaController(AddGuarderiaView addGuarderiaView) {
        this.addGuarderiaView = addGuarderiaView;
    }
    
    public void loadLugares() { 
        ArrayList<Lugar> ciudades = new ArrayList();
        ArrayList<Lugar> estados = new ArrayList();
        ciudades = modeloLugar.getCiudades();
        estados = modeloLugar.getEstados();
        municipios = modeloLugar.getMunicipios();
        
        int ciudadSize = ciudades.size();
        int estadoSize = estados.size();
        int municipioSize = municipios.size();
        
        for (int i = 0; i < ciudadSize; i++) {
            addGuarderiaView.ciudadComboBox.addItem(ciudades.get(i).getNombre());
        }
        
        for (int i = 0; i < estadoSize; i++) {
            addGuarderiaView.estadoComboBox.addItem(estados.get(i).getNombre());
        }
        
        for (int i = 0; i < municipioSize; i++) {
            addGuarderiaView.municipioComboBox.addItem(municipios.get(i).getNombre());
        }
    }
    
    public void saveGuarderia() {
        Lugar casa = new Lugar();
        Lugar calle = new Lugar();
        casa.setNombre(addGuarderiaView.casaTextField.getText());
        casa.setTipo(String.valueOf(addGuarderiaView.casaComboBox.getSelectedItem()));
        calle.setNombre(addGuarderiaView.calleTextField.getText());
        calle.setTipo(String.valueOf(addGuarderiaView.calleComboBox.getSelectedItem()));
        calle.setCodigo_superior(municipios.get(addGuarderiaView.municipioComboBox.getSelectedIndex()).getCodigo());
        int codigo = modeloLugar.addDireccion(casa, calle);
        
        Guarderia guarderia = new Guarderia();
        guarderia.setRif(addGuarderiaView.rifTextField.getText());
        guarderia.setNombre(addGuarderiaView.nombreTextField.getText());
        guarderia.setCostoMensualidad(0);
        guarderia.setCostoMulta(0);
        guarderia.setConstoTransporte(0);
        guarderia.setCostoAgoDic(0);
        guarderia.setCostoHoraExtra(0);
        java.util.Date utilDate = (java.util.Date) addGuarderiaView.horaEntradaSpinner.getValue();
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
        guarderia.setHoraEntrada(sqlTime);
        utilDate = (java.util.Date) addGuarderiaView.horaSalidaSpinner.getValue();
        sqlTime = new java.sql.Time(utilDate.getTime());
        guarderia.setHoraSalida(sqlTime);
        guarderia.setCodDireccion(codigo);
        modeloGuarderia.saveGuarderia(guarderia);
        addGuarderiaView.dispose();
    }
}
