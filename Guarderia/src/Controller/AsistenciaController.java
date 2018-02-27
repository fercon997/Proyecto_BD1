/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Asistencia;
import Model.AsistenciaDAOImpl;
import Model.Autorizado;
import Model.InscripcionA;
import Model.Juego;
import Model.Juego_NinoDAOImpl;
import Model.Nino;
import View.JDAsistencia;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fercon997
 */
public class AsistenciaController {
    Nino nino;
    ArrayList<Asistencia> asistencias;
    JDAsistencia vistaAsistencias;
    ArrayList<Autorizado> auths;

    public AsistenciaController(JDAsistencia vistaAsistencias, Nino nino) {
        this.nino = nino;
        this.vistaAsistencias = vistaAsistencias;
    }
    
    public void loadAsistencias(JTable tabla){
          DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);
        AsistenciaDAOImpl bdAssist = new AsistenciaDAOImpl();
        asistencias = bdAssist.getAllAsistencia(nino);
        Object[] columna = new Object[4];
        try{
            for(int i = 0; i<asistencias.size(); i++){
                columna[0] = asistencias.get(i).getFecha();
                columna[1] = asistencias.get(i).getHora_entrada();
                columna[2] = asistencias.get(i).getHora_salida();
                columna[3] = asistencias.get(i).getComio();
                modeloTabla.addRow(columna);
            }
        } catch(Exception e) {
            for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);

        }
    }
    
    public void loadBusca(JTable tabla){
       DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);
        AsistenciaDAOImpl bdAssist = new AsistenciaDAOImpl();
        auths = bdAssist.llenarBusco(nino);
        Object[] columna = new Object[4];
        try{
            for(int i = 0; i<auths.size(); i++){
                columna[0] = auths.get(i).getCi();
                columna[1] = auths.get(i).getNombre();
                columna[2] = auths.get(i).getApellido();
                columna[3] = auths.get(i).getCelular();
                modeloTabla.addRow(columna);
            }
        } catch(Exception e) {
            for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);

        }
    }
    
    public void insertAsistencia(JComboBox cb, JTable tabla){
        AsistenciaDAOImpl bdAssist = new AsistenciaDAOImpl();
        InscripcionA ins = bdAssist.getConsIns(nino);
        Asistencia assist = new Asistencia();
        int index = tabla.getSelectedRow();
        if (auths.get(index).getTipo().equals("Representante"))
            assist.setCi_representante_busco("'"+auths.get(index).getCi()+"'");
        else
            assist.setCi_auth_busco("'"+auths.get(index).getCi()+"'");
        assist.setAno_inscripcion(ins.getFecha());
        assist.setConsecutivo_inscripcion(ins.getConsecutivo());
        assist.setCi_representante(nino.getCiRepresentante());
        assist.setLetra(nino.getLetra());
        assist.setComio(String.valueOf(cb.getSelectedItem()));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        //System.out.println(Date.valueOf(localDate));
        assist.setFecha(Date.valueOf(localDate));
        java.util.Date utilDate = (java.util.Date) vistaAsistencias.horaEntradaSpinner.getValue();
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
        assist.setHora_entrada(sqlTime);
        utilDate = (java.util.Date) vistaAsistencias.horaSalidaSpinner.getValue();
        sqlTime = new java.sql.Time(utilDate.getTime());
        assist.setHora_salida(sqlTime);
        try {
            bdAssist.insertAsistencia(assist);
            JOptionPane.showMessageDialog(null, "Datos cargados satisfactoriamente");
            vistaAsistencias.dispose();
        } catch (Exception ex) {
            Logger.getLogger(AsistenciaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en los datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void deleteAsistencia(JTable tabla){
        int index = tabla.getSelectedRow();
        AsistenciaDAOImpl bdAssist = new AsistenciaDAOImpl();
        Asistencia assist = asistencias.get(index);
        int confirmacion = JOptionPane.showConfirmDialog(vistaAsistencias, "Está seguro que quiere borrar este Reporte de asistencia?"
                    , "Borrar Reporte de asistencia", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirmacion == JOptionPane.YES_OPTION){
                bdAssist.deleteAsistencia(assist.getConsecutivo_inscripcion(), assist.getFecha());
                this.loadAsistencias(tabla);
            }
    }
    
    
    
}
