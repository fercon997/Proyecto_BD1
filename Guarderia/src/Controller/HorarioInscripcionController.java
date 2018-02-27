
package Controller;

import Model.Horario;
import View.InitialView;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class HorarioInscripcionController {
    
    InitialView initialView;
    
    public HorarioInscripcionController(InitialView initialView) {
        this.initialView = initialView;
    }
    
//    public void llenarTabla(JTable tabla) {
//        DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
//        for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
//          modeloTabla.removeRow(i);
//        Object[] columna = new Object[4];
//        try{
//            for(int i = 0; i < 7; i++){
//                columna[0] = parents.get(i).getCi();
//                columna[1] = parents.get(i).getNombre();
//                columna[2] = parents.get(i).getApellido();
//                columna[3] = parents.get(i).getPrincipal();
//                modeloTabla.addRow(columna);
//            }
//        } catch(Exception e){
//
//            }
//        }
    
    public ArrayList<Horario> llenarHorario() {
        Horario horario = new Horario();
        ArrayList<Horario> horas = new ArrayList();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String hora = "9:00";
        long hey;
        try {
            hey = dateFormat.parse(hora).getTime();
            horario.setHora(new Time(hey));
        } catch (ParseException ex) {
            Logger.getLogger(HorarioInscripcionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }   
}
