/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.InitialView;
import View.JDAddMenu;
import java.util.ArrayList;
import javax.swing.JComboBox;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author gabrielbaron
 */
public class MenuController {
    InitialView initialView;
    MenuDAOImpl modelomenu;
    private ArrayList<String> rifs;

    public MenuController(InitialView initialView, MenuDAOImpl modelomenu) {
        this.initialView = initialView;
        this.modelomenu = modelomenu;
    }
    private void loadRifs() {
        GuarderiaDAOImpl modeloGuarderia = new GuarderiaDAOImpl();
        rifs = modeloGuarderia.getRifs();
    }
    public int guarderiaChanged(JComboBox cb) {
        int codigo = 0;
        int numGuard = cb.getSelectedIndex();
        initialView.jComboGuarderias7.setSelectedIndex(numGuard);
        if (numGuard == 0) {
               DefaultTableModel modelo = (DefaultTableModel)initialView.Tabla_Menus.getModel();
               int rowCount = modelo.getRowCount();
               for (int i = rowCount - 1; i >= 0; i--) {
                 modelo.removeRow(i);
               }
            }
            else{
              MenuDAOImpl menu = new MenuDAOImpl();
              loadRifs();
              menu.setRif(rifs.get(numGuard-1));
              LlenarMenus(menu.getmenus());
            }
       return codigo;
    }
    public void LlenarMenus(ArrayList<Menu> menus){
        DefaultTableModel modelo = (DefaultTableModel)this.initialView.Tabla_Menus.getModel();
        int rowCount = modelo.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
        Object[] columna = new Object[3];
        int size = menus.size();
        for (int i=0; i<size; i++){
            columna[0] = menus.get(i).getNumero();
            columna[1] = menus.get(i).getCosto();
            columna[2] = menus.get(i).getFecha();
            modelo.addRow(columna);
        }
    }
    public void datosMenu(JTable tabla){
        try{
            MenuDAOImpl bdMenu = new MenuDAOImpl();
            int codigo = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
            Menu menu = bdMenu.InformacionMenu(codigo);
            initialView.Numero_MenuLabel1.setText(String.valueOf(menu.getNumero()));
            initialView.CostoText.setText(Float.toString(menu.getCosto()));
            initialView.Menu_FechaLabel.setText(menu.getFecha());
            initialView.Menu_FechaLabel1.setText(menu.getFecha_fin());
            PlatoDAOImpl bdPlato = new PlatoDAOImpl();
            PlatoController ControladorPlato = new PlatoController(this.initialView,bdPlato);
            ControladorPlato.PlatosMenu(codigo);
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            initialView.Numero_MenuLabel1.setText("");
            initialView.CostoText.setText("");
            initialView.Menu_FechaLabel.setText("");
            initialView.Menu_FechaLabel1.setText("");
            initialView.Plato1_MenuLabel1.setText("");
            initialView.Plato2_MenuLabel1.setText("");
            initialView.Plato3_MenuLabel2.setText("");
            initialView.Plato4_MenuLabel1.setText("");
            initialView.Plato5_MenuLabel1.setText("");
        }
    }
     public void editarMenu(){
        Menu menu = new Menu();
        MenuDAOImpl bdMenu = new MenuDAOImpl();
        try {
            menu.setNumero(Integer.parseInt(initialView.Numero_MenuLabel1.getText()));
            menu.setCosto(Float.parseFloat(initialView.CostoText.getText()));
            menu.setFecha(initialView.Menu_FechaLabel.getText());
            bdMenu.updateMenu(menu);
            JOptionPane.showMessageDialog(initialView, "Datos cargados satisfactoriamente");
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(initialView, "No se puede actualizar");
        }
        bloquear();
        limpiarDatos();
        LlenarMenus(bdMenu.getmenus());
    }
    public void eliminarMenu(){
        try{
            int codigo = Integer.parseInt(initialView.Numero_MenuLabel1.getText());
            int confirmacion = JOptionPane.showConfirmDialog(initialView, "Está seguro que quiere borrar este Menu?"+
                    "(Se borraran todos los datos relacionados a el)", "Borrar Menu", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirmacion == JOptionPane.YES_OPTION){
                MenuDAOImpl bdMenu = new MenuDAOImpl();
                bdMenu.deleteMenu(codigo);
                JOptionPane.showMessageDialog(initialView, "Borrado");
            }
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(initialView, "No se puede borrar");
        }
    }
    public void bloquear(){
        initialView.CostoText.setEnabled(false);
        initialView.Menu_FechaLabel.setEnabled(false);
        initialView.saveMenuBtn.setEnabled(false);
    }

    public void habilitar(){
        initialView.CostoText.setEnabled(true);
        initialView.Menu_FechaLabel.setEnabled(true);
        initialView.saveMenuBtn.setEnabled(true);
    }
    
    public void limpiarDatos(){
        initialView.CostoText.setText("");
        initialView.Menu_FechaLabel.setText("");
        initialView.saveMenuBtn.setText("");  
        initialView.Plato1_MenuLabel1.setText("");
        initialView.Plato2_MenuLabel1.setText("");
        initialView.Plato3_MenuLabel2.setText("");
        initialView.Plato4_MenuLabel1.setText("");
        initialView.Plato5_MenuLabel1.setText("");
    }
    
    public void addMenu(JTable tabla){
         JDAddMenu vistaAM = new JDAddMenu(initialView, true);
         vistaAM.setVisible(true);
    }
    
    
}
