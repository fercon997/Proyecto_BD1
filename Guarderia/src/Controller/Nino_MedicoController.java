/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.AddGuarderiaView;
import View.EditGuarderiaView;
import View.InitialView;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author gabrielbaron
 */
 public class Nino_MedicoController {
     private InitialView initialView;
     private Nino_MedicoDAOImpl modeloNino_Medico;
     private ArrayList<String> codigos;
     public Nino_MedicoController(InitialView initialView, Nino_MedicoDAOImpl Nino_Medico) {
        this.initialView = initialView;
        this.modeloNino_Medico = Nino_Medico;
    }
    public void  LlenarComboBoxNinos(JComboBox cb){
        ArrayList<Nino_Medico> ninos = new ArrayList();
        ninos = modeloNino_Medico.loadNinos();
        int size = ninos.size();
        cb.addItem("Niños");
        for (int i = 0; i < size; i++) {
            System.out.println(ninos.get(1).getCi_representante());
            cb.addItem(ninos.get(i).getNombre()+" "+ninos.get(i).getLetra()+ninos.get(i).getCi_representante());
        }
    }
    public void loadcodigos(){
        this.codigos = this.modeloNino_Medico.getcodigos();
    }
    public void nino_medicoChanged(JComboBox cb) {
        int numninos = cb.getSelectedIndex();
        initialView.jComboNinos.setSelectedIndex(numninos);
        if (numninos == 0) {
            DefaultTableModel modelopediatras = (DefaultTableModel)initialView.Tabla_Enfermedades.getModel();
            DefaultTableModel modeloenfermedades = (DefaultTableModel)initialView.Tabla_Enfermedades.getModel();
            DefaultTableModel modeloalergias = (DefaultTableModel)initialView.Tabla_Enfermedades.getModel();
            DefaultTableModel modelotratamientos = (DefaultTableModel)initialView.Tabla_Tratamientos.getModel();
               int rowCount = modelopediatras.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                modelopediatras.removeRow(i);
            }
            rowCount = modeloenfermedades.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                modeloenfermedades.removeRow(i);
            }
            rowCount = modeloalergias.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                modeloalergias.removeRow(i);
            }
            rowCount = modelotratamientos.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                modelotratamientos.removeRow(i);
            }
        }
        else{
            loadcodigos();
            Nino_MedicoDAO nino_medico = new Nino_MedicoDAOImpl();
            LlenarInformeMedico(nino_medico,codigos.get(numninos-1));
        }
        
    } 
    public void LlenarInformeMedico(Nino_MedicoDAO nino, String cod){
        DefaultTableModel modelopediatras = (DefaultTableModel)initialView.Tabla_Pediatras.getModel();
        DefaultTableModel modeloenfermedades = (DefaultTableModel)initialView.Tabla_Enfermedades.getModel();
        DefaultTableModel modeloalergias = (DefaultTableModel)initialView.Tabla_Alergias.getModel();
        DefaultTableModel modelotratamientos = (DefaultTableModel)initialView.Tabla_Tratamientos.getModel();
        int rowCount = modelopediatras.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modelopediatras.removeRow(i);
        }
        rowCount = modeloenfermedades.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modeloenfermedades.removeRow(i);
        }
        rowCount = modeloalergias.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modeloalergias.removeRow(i);
        }
        rowCount = modelotratamientos.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modelotratamientos.removeRow(i);
        }
        Object[] columnapediatras = new Object[1];
        Object[] columnaenfermedades = new Object[1];
        Object[] columnaalergias = new Object[1];
        Object[] columnatratamientos = new Object[3];
        ArrayList<Pediatra> pediatras = nino.getpediatra(cod);
        int n_p = pediatras.size();
        ArrayList<Alergia> alergias = nino.getAlergia(cod);
        int n_a = alergias.size();
        ArrayList<Enfermedad> enfermedades = nino.getEnfermedad(cod);
        int n_e = enfermedades.size();
        ArrayList<Tratamiento> tratamientos = nino.getTratamiento(cod);
        int n_t = tratamientos.size();
        for (int i=0; i<n_p; i++){
              columnapediatras[0] = pediatras.get(i).getNombre();
              modelopediatras.addRow(columnapediatras);
        }
        for (int i=0; i<n_a; i++){
              columnaalergias[0] = alergias.get(i).getDescripcion();
              modeloalergias.addRow(columnaalergias);
        }
        for (int i=0; i<n_e; i++){
              columnaenfermedades[0] = enfermedades.get(i).getDescripcion();
              modeloenfermedades.addRow(columnaenfermedades);
        }   
        for (int i=0; i<n_t; i++){
              columnatratamientos[0] = tratamientos.get(i).getNombre_Sintoma();
              columnatratamientos[1] = tratamientos.get(i).getNombre_Medicamento();
              columnatratamientos[2] = tratamientos.get(i).getCantidad();
              modelotratamientos.addRow(columnatratamientos);
        }

    }
}
