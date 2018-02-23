/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Nino;
import Model.NinoDAOImpl;
import View.AddNinoView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ignacio
 */
public class AddNinoController {
    
    AddNinoView anv;
    NinoDAOImpl modeloNino = new NinoDAOImpl();
    
    public AddNinoController(AddNinoView anv) {
        this.anv = anv;
    }
    
    public void addNino() {
        try {
            Nino nino = new Nino();
            nino.setNombre(anv.nombreTextField.getText());
            nino.setCiRepresentante(anv.ciRepresentanteTextField.getText());
            nino.setApellido(anv.apellidoTextField.getText());
            if (anv.jRadioButtonF.isSelected()) {
                nino.setSexo('F');
            } else if (anv.jRadioButtonM.isSelected()){
                nino.setSexo('M');
            }
            char letra = modeloNino.getSiblings(nino.getCiRepresentante());
            System.out.println("Letra: " + letra++);
            nino.setLetra(++letra);
            String fechaNac = anv.fechaNacTextField.getText();
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date date = sdf1.parse(fechaNac);
            java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
            nino.setFechaNacimiento(sqlStartDate);
            modeloNino.saveGuarderia(nino);
            anv.dispose();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "El formato de la fecha es inváido, debe ser dd-MM-aaaa");
        }
    }
}
