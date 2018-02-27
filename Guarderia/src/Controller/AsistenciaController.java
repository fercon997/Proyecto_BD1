/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Asistencia;
import Model.AsistenciaDAOImpl;
import Model.Autorizado;
import Model.Guarderia;
import Model.GuarderiaDAOImpl;
import Model.Inscripcion;
import Model.InscripcionDAOImpl;
import Model.Juego;
import Model.Juego_NinoDAOImpl;
import Model.Nino;
import View.JDAsistencia;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
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
    Inscripcion inscripcion;

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
        try{
            AsistenciaDAOImpl  bdAssist = new AsistenciaDAOImpl();
            InscripcionDAOImpl bdIns = new InscripcionDAOImpl();
            inscripcion = bdIns.getInsNino(nino);
            Asistencia assist = new Asistencia();
            int index = tabla.getSelectedRow();
            if (auths.get(index).getTipo().equals("Representante"))
                assist.setCi_representante_busco("'"+auths.get(index).getCi()+"'");
            else
                assist.setCi_auth_busco("'"+auths.get(index).getCi()+"'");
            assist.setAno_inscripcion(inscripcion.getAno());
            assist.setConsecutivo_inscripcion(inscripcion.getConsecutivo());
            assist.setCi_representante(nino.getCiRepresentante());
            assist.setLetra(nino.getLetra());
            assist.setComio(String.valueOf(cb.getSelectedItem()));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate localDate = LocalDate.now();
        //System.out.println(Date.valueOf(localDate));
            assist.setFecha(Date.valueOf(localDate));
            java.util.Date utilDate= (java.util.Date) vistaAsistencias.horaEntradaSpinner.getValue();
            java.sql.Time horaEntrada = new java.sql.Time(utilDate.getTime());
            utilDate = (java.util.Date) vistaAsistencias.horaSalidaSpinner.getValue();
            java.sql.Time horaSalida = new java.sql.Time(utilDate.getTime());
            validarHoras(horaEntrada, horaSalida);
            assist.setHora_entrada(horaEntrada);
            assist.setHora_salida(horaSalida);
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
    
    
    public void validarHoras(Time horaEntrada, Time horaSalida) throws Exception{
        AsistenciaDAOImpl bdAssist = new AsistenciaDAOImpl();
        String rif = bdAssist.getRif(nino);
        GuarderiaDAOImpl bdGuard = new GuarderiaDAOImpl();
        Guarderia guard = bdGuard.getDatosGuarderia(rif);
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        if ( (horaEntrada.before(guard.getHoraEntrada())) || (horaSalida.after(guard.getHoraSalida())) )
            throw new Exception("El nino no puede ser llevado antes de las "
                    + String.valueOf(guard.getHoraEntrada())+" ni buscado despues "
                    + "de las "+String.valueOf(guard.getHoraSalida()));
        else {
            if ( (horaSalida.after(inscripcion.getHoraSalida())) ){
                int recargo = recargoMultas(horaSalida, inscripcion.getHoraSalida());
                JOptionPane.showMessageDialog(vistaAsistencias, "Se le cobrara una multa de recargo de "
                        + String.valueOf(recargo)+" horas");
            }
        }
    }
    
    public int recargoMultas(Time horaSalida, Time horaSalidaIns){
        String horaS = String.valueOf(horaSalida);
        String horaSIn = String.valueOf(horaSalidaIns);
        System.out.println(horaS+" "+horaSIn);
        char[] horaSC = horaS.toCharArray();
        char[] horaSInC = horaSIn.toCharArray();
        horaS = new String();
        horaSIn = new String();
        for (int i=0; i<2; i++){
            horaS = horaS+horaSC[i];
            horaSIn = horaSIn + horaSInC[i];
        }
        int horaSInt = Integer.valueOf(horaS);
        int horaSInInt = Integer.valueOf(horaSIn);
        if (Integer.valueOf(String.valueOf(horaSC[3])) >= 3 )
          horaSInt++;
        System.out.println(horaSInt+ " "+horaSInInt);
        return horaSInt-horaSInInt;
        
    }
    
    
}
