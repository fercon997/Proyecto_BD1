
package Controller;

import Model.GuarderiaDAOImpl;
import Model.InscripcionDAOImpl;
import Model.Inscripcion;
import View.InitialView;
import View.JDAddInscripcion;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InscripcionController {
    
    InitialView initialView;
    InscripcionDAOImpl modeloInsc = new InscripcionDAOImpl();
    GuarderiaDAOImpl modeloGuarderia = new GuarderiaDAOImpl();
    ArrayList<Inscripcion> inscripciones = new ArrayList();
    ArrayList<String> rifs = new ArrayList();
    
    public InscripcionController(InitialView initialView) {
        this.initialView = initialView;
        this.rifs = modeloGuarderia.getRifs();
    }
    
    public void tabbedPaneTouched() {
        if (initialView.jTabbedPane1.getSelectedIndex() == 1) {
            llenarInscripcion(initialView.jComboInscripcion, initialView.tablaInscripcion);
            bloquear();
        }
    }
    
    public void addInscripcion() {
        JDAddInscripcion vista = new JDAddInscripcion(initialView, true);
        vista.setVisible(true);
    }
    
    public void llenarInscripcion(JComboBox cb, JTable tabla){
        DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        int numGuard = cb.getSelectedIndex();
        for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);
        Object[] columna = new Object[5];
        try{
            if (cb.getSelectedIndex() == 0) {
                this.inscripciones = modeloInsc.loadAllInscripciones();
            } else {
                this.inscripciones = modeloInsc.loadInscripciones(rifs.get(cb.getSelectedIndex() - 1));
            }
            for(int i = 0; i<inscripciones.size(); i++){
                columna[0] = inscripciones.get(i).getCiRepresentante();
                columna[1] = inscripciones.get(i).getApellidoNino() + ", " + inscripciones.get(i).getNombreNino();
                columna[2] = inscripciones.get(i).getHoraLlegada();
                columna[3] = inscripciones.get(i).getHoraSalida();
                modeloTabla.addRow(columna);
            }
        } catch(Exception e){
            System.out.println("Error cargando los datos de la inscripcion");
        }
    }  
    
    public void mostrarDatosInscripcion() {
        int index = initialView.tablaInscripcion.getSelectedRow();
        initialView.ciRepresentanteInsc.setText(inscripciones.get(index).getCiRepresentante());
        initialView.nombreNinoInscripcion.setText(inscripciones.get(index).getNombreNino());
        initialView.apellidoNinoInscripcion.setText(inscripciones.get(index).getApellidoNino());
        initialView.horaEntradaInscripcion.setValue(inscripciones.get(index).getHoraLlegada());
        initialView.horaSalidaInscripcion.setValue(inscripciones.get(index).getHoraSalida());
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String date = format.format(inscripciones.get(index).getFechaInscripcion());
        initialView.fechaInscripción.setText(date);
    }
    
    public void modificarInscripcion() {
        int index = initialView.tablaInscripcion.getSelectedRow();
        Inscripcion inscripcion = inscripciones.get(index);
        java.util.Date utilDate = (java.util.Date) initialView.horaEntradaInscripcion.getValue();
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
        inscripcion.setHoraLlegada(sqlTime);
        utilDate = (java.util.Date) initialView.horaSalidaInscripcion.getValue();
        sqlTime = new java.sql.Time(utilDate.getTime());
        inscripcion.setHoraSalida(sqlTime);
        modeloInsc.updateInscripcion(inscripcion);
        llenarInscripcion(initialView.jComboInscripcion, initialView.tablaInscripcion);
    }
    
    public void eliminarInscripcion() {
        modeloInsc.deleteInscripcion(inscripciones.get(initialView.tablaInscripcion.getSelectedRow()));
        llenarInscripcion(initialView.jComboInscripcion, initialView.tablaInscripcion);
    }
    
    public void bloquear() {
        initialView.horaEntradaInscripcion.setEnabled(false);
        initialView.horaSalidaInscripcion.setEnabled(false);
    }
    
    public void habilitar() {
        initialView.horaEntradaInscripcion.setEnabled(true);
        initialView.horaSalidaInscripcion.setEnabled(true);
    }
}
