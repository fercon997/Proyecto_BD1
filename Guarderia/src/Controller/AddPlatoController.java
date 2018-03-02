/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.*;
import Model.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author gabrielbaron
 */
public class AddPlatoController {
    JDAddPlato vistaAp;

    public AddPlatoController(JDAddPlato vistaAp) {
        this.vistaAp = vistaAp;
    }
    public void mostrarComidas(JTable tabla,String tipo){
        DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);
        Object [] columna = new Object[2];
        try{
            PlatoDAOImpl bdPlato = new PlatoDAOImpl();
            ArrayList<Comida> comidas = bdPlato.getComidas_Tipo(tipo);
            for(int i = 0; i<comidas.size(); i++){
                columna[0] = comidas.get(i).getCodigo();
                columna[1] = comidas.get(i).getNombre();
                modeloTabla.addRow(columna);
            }
        } catch(Exception e){
            for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
              modeloTabla.removeRow(i);
        }
    }
    public void comidasClickTabla(JTable tabla){
        PlatoDAOImpl bdPlato = new PlatoDAOImpl();
        String cod = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
        int codigo = Integer.parseInt(cod);
        String tipo = bdPlato.Buscarcomida(codigo);
        if (tipo.equals("Proteina")){
            this.vistaAp.ProteinaTxt.setText(cod);
        }
        if (tipo.equals("Carbohidrato")){
            this.vistaAp.CarbohidratoTxt.setText(cod);
        }
        if (tipo.equals("Jugo natural")){
            this.vistaAp.Jugo_naturalTxt.setText(cod);
        }
        if (tipo.equals("Ensalada")){
            this.vistaAp.EnsaladaTxt.setText(cod);
        }
    }
    public void agregarPlato(){
        Plato plato = new Plato();
        ArrayList<Integer> comidas = new ArrayList();
        try{
            plato.setDescripcion(vistaAp.DescripcionTxt.getText());
            comidas.add(Integer.parseInt(vistaAp.ProteinaTxt.getText()));
            comidas.add(Integer.parseInt(vistaAp.CarbohidratoTxt.getText()));
            comidas.add(Integer.parseInt(vistaAp.Jugo_naturalTxt.getText()));
            comidas.add(Integer.parseInt(vistaAp.EnsaladaTxt.getText()));
            PlatoDAOImpl bdPlato = new PlatoDAOImpl();
            bdPlato.InsertarPlato(plato,comidas);
            JOptionPane.showMessageDialog(vistaAp, "Datos cargados satisfactoriamente");
            vistaAp.dispose();
        }catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(vistaAp, "Error en los datos");
        }   
    }
}
