/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Lugar;
import Model.LugarDAOImpl;
import View.AddGuarderiaView;
import java.util.ArrayList;

/**
 *
 * @author Ignacio
 */
public class AddGuarderiaController {
    
    AddGuarderiaView addGuarderiaView;
    LugarDAOImpl modeloLugar = new LugarDAOImpl();
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
        System.out.println("Hola: " + addGuarderiaView.municipioComboBox.getSelectedIndex());
        casa.setNombre(addGuarderiaView.casaTextField.getText());
        casa.setTipo(String.valueOf(addGuarderiaView.casaComboBox.getSelectedItem()));
        calle.setNombre(addGuarderiaView.calleTextField.getText());
        calle.setTipo(String.valueOf(addGuarderiaView.calleComboBox.getSelectedItem()));
        System.out.println("Hola 2 " + municipios.get(addGuarderiaView.municipioComboBox.getSelectedIndex()).getCodigo());
        calle.setCodigo_superior(municipios.get(addGuarderiaView.municipioComboBox.getSelectedIndex()).getCodigo());
        int codigo = modeloLugar.addDireccion(casa, calle);
    }
}
