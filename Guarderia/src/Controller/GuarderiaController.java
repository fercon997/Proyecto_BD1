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

    public GuarderiaController(InitialView initialView, GuarderiaDAO modeloGuarderia) {
        this.initialView = initialView;
        this.modeloGuarderia = modeloGuarderia;
        this.llenarComboBoxGuarderias(initialView.jComboGuarderias);
    }
    
    public void llenarComboBoxGuarderias(JComboBox cb) {
        ArrayList<Guarderia> guarderias = new ArrayList();
        guarderias = modeloGuarderia.loadGuarderias();
        int size = guarderias.size();
        for (int i = 0; i < size; i++) {
            System.out.println(guarderias.get(i).getComboText());
            //cb.addItem(guarderias.get(i).getComboText());
        }
    }
}
