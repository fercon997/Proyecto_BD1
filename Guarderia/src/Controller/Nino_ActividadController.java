/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.InitialView;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gabrielbaron
 */
public class Nino_ActividadController {
     private InitialView initialView;
     private Nino_ActividadDAOImpl modeloNino_Actividad;
     private ArrayList<String> codigos;
     public Nino_ActividadController(InitialView initialView, Nino_ActividadDAOImpl Nino_Actividad) {
        this.initialView = initialView;
        this.modeloNino_Actividad = Nino_Actividad;
    }
    public void  LlenarComboBoxNinos(JComboBox cb){
        ArrayList<Nino_Medico> ninos = new ArrayList();
        ninos = modeloNino_Actividad.loadNinos();
        int size = ninos.size();
        cb.addItem("Niños");
        for (int i = 0; i < size; i++) {
            System.out.println(ninos.get(1).getCi_representante());
            cb.addItem(ninos.get(i).getNombre()+" "+ninos.get(i).getLetra()+ninos.get(i).getCi_representante());
        }
    }
    public void loadcodigos(){
        this.codigos = this.modeloNino_Actividad.getcodigos();
    }
    public void nino_actividadChanged(JComboBox cb) {
        int numninos = cb.getSelectedIndex();
        initialView.jComboNinos2.setSelectedIndex(numninos);
         if (numninos == 0) {
             DefaultTableModel modeloactividades = (DefaultTableModel)initialView.Tabla_actividades.getModel();
             int rowCount = modeloactividades.getRowCount();
             for (int i = rowCount - 1; i >= 0; i--) {
                modeloactividades.removeRow(i);
             }
         }
         else
         {
             loadcodigos();
             Nino_ActividadDAOImpl nino_actividad = new Nino_ActividadDAOImpl();
             LlenarActividadesNino(nino_actividad,codigos.get(numninos-1));
         }
    }
     public void LlenarActividadesNino(Nino_ActividadDAOImpl nino_actividad, String Codigo){
        DefaultTableModel modelo = (DefaultTableModel)this.initialView.Tabla_actividades.getModel();
        int rowCount = modelo.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
        Object[] columna = new Object[6];
        ArrayList<Guarderia_Actividad> actividades = nino_actividad.getactividades(Codigo);
        int size = actividades.size();
         System.out.println(size);
        for (int i=0; i<size; i++){
            columna[0] = actividades.get(i).getNombre();
            columna[1] = actividades.get(i).getDescripcion();
            columna[2] = actividades.get(i).getEdad_minima();
            columna[5] = actividades.get(i).getCosto() + " Bolivares";
            modelo.addRow(columna);
        }

    }
    
}
    
