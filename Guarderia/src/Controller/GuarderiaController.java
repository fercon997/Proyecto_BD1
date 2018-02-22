package Controller;

import Model.*;
import View.AddGuarderiaView;
import View.EditGuarderiaView;
import View.InitialView;
import java.util.ArrayList;
import javax.swing.JComboBox;

public class GuarderiaController {
    
    InitialView initialView;
    GuarderiaDAOImpl modeloGuarderia = new GuarderiaDAOImpl(); 
    LugarDAOImpl modeloLugar = new LugarDAOImpl();
    
    public boolean changing = false;
    private ArrayList<String> rifs;

    public GuarderiaController(InitialView initialView, GuarderiaDAOImpl modeloGuarderia) {
        this.initialView = initialView;
        this.modeloGuarderia = modeloGuarderia;
        loadRifs();
    }
    
    private void loadRifs() {
        rifs = modeloGuarderia.getRifs();
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
    
    public void guarderiaChanged(JComboBox cb) {
        changing = true;
        int numGuard = cb.getSelectedIndex();
        if (numGuard == 0) {
            disableButtons(); 
        } else {
            enableButtons();
        }
        initialView.jComboGuarderias.setSelectedIndex(numGuard);
        initialView.jComboGuarderias1.setSelectedIndex(numGuard);
        initialView.jComboGuarderias2.setSelectedIndex(numGuard);
        initialView.jComboGuarderias3.setSelectedIndex(numGuard);
        initialView.jComboGuarderias4.setSelectedIndex(numGuard);
        initialView.jComboGuarderias5.setSelectedIndex(numGuard);
        initialView.jComboGuarderias6.setSelectedIndex(numGuard);
        initialView.jComboGuarderias7.setSelectedIndex(numGuard);
        initialView.jComboGuarderias8.setSelectedIndex(numGuard);
        initialView.jComboGuarderias9.setSelectedIndex(numGuard);
        initialView.jComboGuarderias10.setSelectedIndex(numGuard);
        changing = false;
        if (cb == initialView.jComboGuarderias) {
            showDatosGuarderia(numGuard - 1);
            showDireccion(numGuard - 1);
        }
        if (cb == initialView.jComboGuarderias6){
            Guarderia_ActividadDAO Actividad = new Guarderia_ActividadDAOImpl(rifs.get(numGuard - 1)); 
            initialView.LlenarActividades(Actividad.getactividades());
        }
    }
    
    private void disableButtons() {
        initialView.editGuarderiaButton.setEnabled(false);
        initialView.deleteGuarderiaButton.setEnabled(false);
    }
    
    private void enableButtons() {
        initialView.editGuarderiaButton.setEnabled(true);
        initialView.deleteGuarderiaButton.setEnabled(true);
    }
    
    public void showDatosGuarderia(int index) {
        if (index == -1) {
            initialView.rifLabel.setText("");
        initialView.horaEntradaLabel.setText("");
        initialView.horaSalidaLabel.setText("");
        } else {
            Guarderia guarderia = modeloGuarderia.getDatosGuarderia(rifs.get(index));
            initialView.rifLabel.setText(rifs.get(index).toString());
            initialView.horaEntradaLabel.setText(guarderia.getHoraEntrada().toString());
            initialView.horaSalidaLabel.setText(guarderia.getHoraSalida().toString());  
        }
    }
    
    public void showDireccion(int index) {
        if (index == -1) {
            initialView.casaLabel.setText("");
            initialView.calleLabel.setText("");
            initialView.municipioLabel.setText("");
            initialView.ciudadLabel1.setText("");
            initialView.estadoLabel.setText("");
        } else {
            Lugar lugar = modeloLugar.getDatosLugar(rifs.get(index), "rif");
            initialView.casaLabel.setText(lugar.getCasa());
            initialView.calleLabel.setText(lugar.getCalle());
            initialView.municipioLabel.setText(lugar.getMunicipio());
            initialView.ciudadLabel1.setText(lugar.getCiudad());
            initialView.estadoLabel.setText(lugar.getEstado());
        }
    }
    
    public void editGuarderia() {
        if (initialView.editGuarderiaButton.isEnabled()) {
            EditGuarderiaView editGuarderiaView = new EditGuarderiaView(initialView, true);
            editGuarderiaView.setVisible(true);
        }
    }
    
    public void addGuarderia() {
        AddGuarderiaView addGuarderiaView = new AddGuarderiaView(initialView, true);
        addGuarderiaView.setVisible(true);
    }
    
}
