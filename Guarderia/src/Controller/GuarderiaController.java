package Controller;

import Model.*;
import View.AddGuarderiaView;
import View.EditGuarderiaView;
import View.InitialView;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class GuarderiaController {

    InitialView initialView;
    GuarderiaDAOImpl modeloGuarderia = new GuarderiaDAOImpl();
    LugarDAOImpl modeloLugar = new LugarDAOImpl();
    NinosController controladorNino;

    public boolean changing = false;
    private ArrayList<String> rifs;

    public GuarderiaController(InitialView initialView, GuarderiaDAOImpl modeloGuarderia) {
        this.initialView = initialView;
        this.modeloGuarderia = modeloGuarderia;
        loadRifs();
        controladorNino = new NinosController(initialView, rifs);
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

    public void tabbedPaneTouched() {
        if (initialView.jTabbedPane1.getSelectedIndex() == 2) {
            controladorNino.llenarNino(initialView.jComboGuarderiasNinos, initialView.tablaNinos);
        }
        if (initialView.jTabbedPane1.getSelectedIndex() == 10){
            controladorNino.llenarNino(initialView.jComboGuarderiasNinos1, initialView.tablaNinos1);
            initialView.transferenciaMultaBtn.setEnabled(false);
            initialView.pagarMultaBtn.setEnabled(false);
        }
        if (initialView.jTabbedPane1.getSelectedIndex() == 9){
            controladorNino.llenarNino(initialView.jComboGuarderiasPagos,initialView.tablaNinos2);
            initialView.pagarMensualidadBtn.setEnabled(false);
            initialView.jComboTipoPago.setEnabled(false);
            initialView.reciboBtn.setEnabled(false);
        }
        if (initialView.jTabbedPane1.getSelectedIndex() == 4){
            initialView.estadoCuentaBtn.setEnabled(false);
        }

    }
    
    public void cargarDatos(){
        controladorNino.llenarNino(initialView.jComboNinos, initialView.tablaNinos2);
    }

    public int guarderiaChanged(JComboBox cb) {
        changing = true;
        int codigo = 0;
        int numGuard = cb.getSelectedIndex();
        if (numGuard == 0) {
            disableButtons();
        } else {
            enableButtons();
        }
        initialView.jComboGuarderias.setSelectedIndex(numGuard);
        initialView.jComboInscripcion.setSelectedIndex(numGuard);
        initialView.jComboGuarderiasNinos.setSelectedIndex(numGuard);
//        initialView.jComboGuarderias3.setSelectedIndex(numGuard);
        initialView.jComboGuarderias4.setSelectedIndex(numGuard);
        initialView.jComboPersonal.setSelectedIndex(numGuard);
        initialView.jComboActNino.setSelectedIndex(numGuard);
        initialView.jComboGuarderias7.setSelectedIndex(numGuard);
//        initialView.jComboGuarderias6.setSelectedIndex(numGuard);
        initialView.jComboGuarderiasPagos.setSelectedIndex(numGuard);
        initialView.jComboGuarderiasNinos1.setSelectedIndex(numGuard);
        initialView.jComboGuarderias10.setSelectedIndex(numGuard);
        changing = false;
        if (cb == initialView.jComboGuarderias) {
            codigo = showDatosGuarderia(numGuard - 1);
            showDireccion(numGuard - 1);
        }
//        if (cb == initialView.jComboGuarderias6){
//            if (numGuard == 0) {
//               DefaultTableModel modelo = (DefaultTableModel)initialView.tablaHorarioNino.getModel();
//               int rowCount = modelo.getRowCount();
//               for (int i = rowCount - 1; i >= 0; i--) {
//                 modelo.removeRow(i);
//               }
//            }
//            else{
//              Guarderia_ActividadDAO Actividad = new Guarderia_ActividadDAOImpl(rifs.get(numGuard-1));
//              initialView.LlenarActividades(Actividad.getactividades());
//            }
//        }
        return codigo;
    }

    private void disableButtons() {
        initialView.editGuarderiaButton.setEnabled(false);
        initialView.deleteGuarderiaButton.setEnabled(false);
    }

    private void enableButtons() {
        initialView.editGuarderiaButton.setEnabled(true);
        initialView.deleteGuarderiaButton.setEnabled(true);
    }

    public int showDatosGuarderia(int index) {
        if (index == -1) {
            initialView.rifLabel.setText("");
        initialView.horaEntradaLabel.setText("");
        initialView.horaSalidaLabel.setText("");
        } else {
            Guarderia guarderia = modeloGuarderia.getDatosGuarderia(rifs.get(index));
            initialView.rifLabel.setText(rifs.get(index).toString());
            initialView.horaEntradaLabel.setText(guarderia.getHoraEntrada().toString());
            initialView.horaSalidaLabel.setText(guarderia.getHoraSalida().toString());
            return guarderia.getCodDireccion();
        }
        return 0;
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

    public void editGuarderia(int codDireccion) {
        if (initialView.editGuarderiaButton.isEnabled()) {
            EditGuarderiaView editGuarderiaView = new EditGuarderiaView(initialView, true, codDireccion);
            editGuarderiaView.setVisible(true);
        }
    }

    public void addGuarderia() {
        AddGuarderiaView addGuarderiaView = new AddGuarderiaView(initialView, true);
        addGuarderiaView.setVisible(true);
    }

    public void deleteGuarderia(int index) {
        modeloGuarderia.deleteGuarderia(rifs.get(index));
    }

    public void mostrarNinos(JTable tabla) {
        controladorNino.mostrarNinos(tabla);
    }

    public void addNino() {
        controladorNino.addNino();
    }

    public void addJuegoNino(JTable tabla){
        controladorNino.addJuego(tabla);
    }

    public void verAsistencia(JTable tabla){
        controladorNino.verAsistencia(tabla);
    }

    public void llenarMultas(JTable tabla){
        controladorNino.llenarMultas(tabla);
    }

    public void pagarMulta(JTable tabla){
        controladorNino.pagarMulta(tabla);
    }
    
    public void generarMensualidad(){
        cargarDatos();
        controladorNino.generarMensualidad();
    }
    
    public void llenarMensualidad(JTable tabla){
        controladorNino.llenarMensualidad(tabla);
    }
    
    public void pagarMensualidad(JTable tabla){
        controladorNino.pagarMensualidad(tabla);
    }
    
    public void verRecibo(JTable tabla){
        controladorNino.verRecibo(tabla);
    }


}
