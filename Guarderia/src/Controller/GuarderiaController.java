package Controller;

import Model.*;
import View.AddGuarderiaView;
import View.EditGuarderiaView;
import View.InitialView;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
            if (numGuard == 0) {
               DefaultTableModel modelo = (DefaultTableModel)initialView.Tabla_actividades.getModel();
               int rowCount = modelo.getRowCount();
               for (int i = rowCount - 1; i >= 0; i--) {
                 modelo.removeRow(i);
               }
            }
            else{
              Guarderia_ActividadDAO Actividad = new Guarderia_ActividadDAOImpl(rifs.get(numGuard-1));
              initialView.LlenarActividades(Actividad.getactividades());
            }
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
            Lugar lugar = modeloLugar.getDatosLugar("SELECT cod_direccion " + 
                    " FROM guarderia_4, lugar_4 WHERE rif = '" + rifs.get(index) + 
                    "' AND cod_direccion = codigo");
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
            Lugar lugar = modeloLugar.getDatosLugar("SELECT cod_direccion " + 
                    " FROM representante_4, lugar_4 WHERE ci = '" + parent.getCi() + 
                    "' AND cod_direccion = codigo");
            initialView.ciLabel.setText(String.valueOf(parent.getCi()));
            initialView.nombreLabel.setText(parent.getNombre());
            initialView.apellidoLabel.setText(parent.getApellido());
            initialView.celularLabel.setText(String.valueOf(parent.getCelular()));
            initialView.tlfcasaLabel.setText(String.valueOf(parent.getTlf_casa()));
            initialView.tlfoficinaLabel.setText(String.valueOf(parent.getTlf_oficina()));
            initialView.emailLabel.setText(parent.getEmail());
            initialView.ciudadLabel.setText(lugar.getCiudad());
            initialView.municipioLabel3.setText(lugar.getMunicipio());
            initialView.calleLabel3.setText(lugar.getCalle());
            initialView.casaLabel3.setText(lugar.getCasa());
            initialView.profesionLabel.setText(parent.getProfesion());
            if (parent.getEdo_civil() == 'C')
              initialView.edoCivilLabel.setText("Casado/a");
            else
                initialView.edoCivilLabel.setText("Soltera/o");
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            initialView.ciLabel.setText("");
            initialView.nombreLabel.setText("");
            initialView.apellidoLabel.setText("");
            initialView.celularLabel.setText("");
            initialView.tlfcasaLabel.setText("");
            initialView.tlfoficinaLabel.setText("");
            initialView.emailLabel.setText("");
            initialView.ciudadLabel.setText("");
            initialView.municipioLabel3.setText("");
            initialView.calleLabel3.setText("");
            initialView.casaLabel3.setText("");
            initialView.profesionLabel.setText("");
            initialView.edoCivilLabel.setText("");

        }
    }



}
