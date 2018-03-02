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
import Model.Inscripcion;
import Model.InscripcionDAOImpl;
import Model.Nino;
import Model.NinoDAOImpl;
import Model.Representante;
import Model.RepresentanteDAOImpl;
import View.JDAddInscripcion;
import View.JDAddRepresentante;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
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
public class AddInscripcionController {
    JDAddInscripcion vistaAG;
    InscripcionDAOImpl modeloInscripcion;
    ArrayList<String> rifs;
    GuarderiaDAOImpl modeloGuarderia = new GuarderiaDAOImpl();

    public AddInscripcionController(JDAddInscripcion vistaAG) {
        this.vistaAG = vistaAG;
        modeloInscripcion = new InscripcionDAOImpl();
        this.rifs = modeloGuarderia.getRifs();
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

    public void clearFields() {
        vistaAG.ciTxt.setText("");
        vistaAG.letraNinoTxt.setText("");
//        if (vistaAG.guarderias.getSelectedIndex() == 0) {
//            vistaAG.ninoExistente.setEnabled(false);
//            vistaAG.nuevoNino.setEnabled(false);
//            vistaAG.representanteExistente.setEnabled(false);
//            vistaAG.nuevoRepresentante.setEnabled(false);
//            vistaAG.tabla.setEnabled(false);
//        } else {
            if (vistaAG.representanteExistente.isEnabled()) {
                mostrarTabla(vistaAG.tabla, "representante");
            }
            vistaAG.representanteExistente.setEnabled(true);
            vistaAG.nuevoRepresentante.setEnabled(true);
            vistaAG.tabla.setEnabled(true);
//        }
    }

    public void mostrarTabla(JTable tabla, String tipo){
        if (tipo == "representante") {
            DefaultTableModel modeloTabla = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            modeloTabla.addColumn("Cédula");
            modeloTabla.addColumn("Nombre");
            tabla.setModel(modeloTabla);
//            for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
//                modeloTabla.removeRow(i);
            Object [] columna = new Object[2];
            try{
                RepresentanteDAOImpl modeloRepresentante = new RepresentanteDAOImpl();
                ArrayList<Representante> representantes;
                if (vistaAG.guarderias.getSelectedIndex() - 1 < 0) {
                    representantes = modeloRepresentante.getRepresentantesNuevos("");
                } else {
                    representantes = modeloRepresentante.getRepresentantesNuevos(rifs.get(vistaAG.guarderias.getSelectedIndex() - 1));
                }
                for(int i = 0; i < representantes.size(); i++){
                    columna[0] = representantes.get(i).getCi();
                    System.out.println(representantes.get(i).getCi());
                    columna[1] = representantes.get(i).getNombre() + " " + representantes.get(i).getApellido();
                    modeloTabla.addRow(columna);
                }

            } catch(Exception e){
                for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
                modeloTabla.removeRow(i);
            }
        } else if (tipo == "nino") {
            DefaultTableModel modeloTabla = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            modeloTabla.addColumn("Letra");
            modeloTabla.addColumn("Nombre");
            tabla.setModel(modeloTabla);
//            for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
//                modeloTabla.removeRow(i);
            Object [] columna = new Object[2];
            try{
                NinoDAOImpl modeloNino = new NinoDAOImpl();
                ArrayList<Nino> ninos;
                if (vistaAG.guarderias.getSelectedIndex() - 1 < 0) {
                    ninos = modeloNino.loadNino("");
                } else {
                    ninos = modeloNino.loadNinosDelPadre(vistaAG.ciTxt.getText());
                }
                for(int i = 0; i < ninos.size(); i++){
                    columna[0] = ninos.get(i).getLetra();
                    columna[1] = ninos.get(i).getNombre() + " " + ninos.get(i).getApellido();
                    modeloTabla.addRow(columna);
                }

            } catch(Exception e){
                for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
                modeloTabla.removeRow(i);
            }
        }


    }

    public void tablaClicked(JTable tabla){
        if ((tabla.getSelectedRow() >= 0) && (tabla.getValueAt(tabla.getSelectedRow(), 0).toString().length() > 1)){
            vistaAG.ciTxt.setText(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
            vistaAG.letraNinoTxt.setText("");
            vistaAG.ninoExistente.setEnabled(true);
            vistaAG.nuevoNino.setEnabled(true);
        } else if ((tabla.getSelectedRow() >= 0) && (tabla.getValueAt(tabla.getSelectedRow(), 0).toString().length() == 1)) {
            vistaAG.letraNinoTxt.setText(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
            vistaAG.Agregar.setEnabled(true);
            vistaAG.Agregar.setEnabled(true);
        }
    }

    public void agregarInscripcion(){
        Inscripcion inscripcion = new Inscripcion();
        try{
            inscripcion.setAno(Calendar.getInstance().get(Calendar.YEAR));
            inscripcion.setLetraNino(vistaAG.letraNinoTxt.getText().charAt(0));
            inscripcion.setCiRepresentante(vistaAG.ciTxt.getText());
            inscripcion.setRifGuarderia(rifs.get(vistaAG.guarderias.getSelectedIndex() - 1));
            LocalDate localDate = LocalDate.now();
            Date date = Date.valueOf(localDate);
            inscripcion.setFechaInscripcion(date);
            java.util.Date utilDate = (java.util.Date) vistaAG.horaEntradaInscripcion.getValue();
            java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
            inscripcion.setHoraLlegada(sqlTime);
            utilDate = (java.util.Date) vistaAG.horaSalidaInscripcion.getValue();
            sqlTime = new java.sql.Time(utilDate.getTime());
            inscripcion.setHoraSalida(sqlTime);
            modeloInscripcion.insertInscripcion(inscripcion);
            Nino kid = new Nino();
            kid.setCiRepresentante(inscripcion.getCiRepresentante());
            kid.setLetra(inscripcion.getLetraNino());
            PagoController payView = new PagoController(vistaAG.parent, kid);
            payView.generarMenusalidad(13);
            vistaAG.dispose();
        }catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(vistaAG, "Error en los datos");
        }
    }


    public void agregarRepresentante() {
        JDAddRepresentante ar = new JDAddRepresentante(vistaAG, true);
        ar.setVisible(true);
    }

}
