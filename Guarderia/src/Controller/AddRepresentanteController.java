/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Lugar;
import Model.LugarDAOImpl;
import Model.Representante;
import Model.RepresentanteDAOImpl;
import View.JDAddRepresentante;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fercon997
 */
public class AddRepresentanteController {
    JDAddRepresentante vistaAG;

    public AddRepresentanteController(JDAddRepresentante vistaAG) {
        this.vistaAG = vistaAG;
    }
    
    public void mostrardirecciones(JTable tabla){
        DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);
        Object [] columna = new Object[3];
        try{
            LugarDAOImpl bdLugar = new LugarDAOImpl();
            ArrayList<Lugar> lugares = bdLugar.getEstados();
            for(int i = 0; i<lugares.size(); i++){
                columna[2] = lugares.get(i).getCodigo();
                columna[0] = lugares.get(i).getNombre();
                columna[1] = lugares.get(i).getTipo();
                modeloTabla.addRow(columna);
            }
            
        } catch(Exception e){
            for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
              modeloTabla.removeRow(i);
        }
        
    }
    
    public void direccionesClickTabla(JTable tabla){
        String tipo = (tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
        if (("Casa".equals(tipo)) || ("Edificio".equals(tipo))){
            vistaAG.Agregar.setEnabled(true);
            vistaAG.direccionTxt.setText(tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
        }
        int codigo = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
        DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);
        Object [] columna = new Object[3];
        try{
            LugarDAOImpl bdLugar = new LugarDAOImpl();
            ArrayList<Lugar> lugares = bdLugar.getAbajo(codigo);
            for(int i = 0; i<lugares.size(); i++){
                System.out.println(i);
                columna[2] = lugares.get(i).getCodigo();
                columna[0] = lugares.get(i).getNombre();
                columna[1] = lugares.get(i).getTipo();
                modeloTabla.addRow(columna);
            }
            
        } catch(Exception e){
            for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
              modeloTabla.removeRow(i);
        }
    }
    public void agregarRepresentante(){
        Representante parent = new Representante();
        try{
            parent.setCi(vistaAG.ciTxt.getText());
            parent.setApellido(vistaAG.apellidoTxt.getText());
            parent.setCelular(Long.parseLong(vistaAG.celularTxt.getText()));
            parent.setCod_direccion(Integer.parseInt(vistaAG.direccionTxt.getText()));
            parent.setEdo_civil(String.valueOf(vistaAG.edoCivilCb.getSelectedItem()).charAt(0));
            parent.setEmail(vistaAG.emailTxt.getText());
            parent.setNombre(vistaAG.nombreTxt.getText());
            parent.setPrincipal(0);
            parent.setProfesion(vistaAG.profesionTxt.getText());
            parent.setTlf_casa(Long.parseLong(vistaAG.tlfCasaTxt.getText()));
            parent.setTlf_oficina(Long.parseLong(vistaAG.tlfOficina.getText()));
            RepresentanteDAOImpl bdParent = new RepresentanteDAOImpl();
            bdParent.insertRepresentante(parent);
            vistaAG.dispose();
        }catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(vistaAG, "No se puede guardar");
        }
    }
    
}
