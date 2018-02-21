/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Guarderia;
import Model.GuarderiaDAO;
import View.InitialView;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Ignacio
 */
public class GuarderiaController {
    
    InitialView initialView;
    GuarderiaDAO modeloGuarderia;
    
    public boolean changing = false;

    public GuarderiaController(InitialView initialView, GuarderiaDAO modeloGuarderia) {
        this.initialView = initialView;
        this.modeloGuarderia = modeloGuarderia;
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
    
    public void guarderiaChanged(JComboBox cb) {
        changing = true;
        int guarderia = cb.getSelectedIndex();
        initialView.jComboGuarderias.setSelectedIndex(guarderia);
        initialView.jComboGuarderias1.setSelectedIndex(guarderia);
        initialView.jComboGuarderias2.setSelectedIndex(guarderia);
        initialView.jComboGuarderias3.setSelectedIndex(guarderia);
        initialView.jComboGuarderias4.setSelectedIndex(guarderia);
        initialView.jComboGuarderias5.setSelectedIndex(guarderia);
        initialView.jComboGuarderias6.setSelectedIndex(guarderia);
        initialView.jComboGuarderias7.setSelectedIndex(guarderia);
        initialView.jComboGuarderias8.setSelectedIndex(guarderia);
        initialView.jComboGuarderias9.setSelectedIndex(guarderia);
        initialView.jComboGuarderias10.setSelectedIndex(guarderia);
        changing = false;
    }
}
