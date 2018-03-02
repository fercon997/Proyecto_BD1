/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.InitialView;
import java.util.ArrayList;

/**
 *
 * @author gabrielbaron
 */
public class PlatoController {
    InitialView initialView;
    PlatoDAOImpl modeloplato;

    public PlatoController(InitialView initialView, PlatoDAOImpl modeloplato) {
        this.initialView = initialView;
        this.modeloplato = modeloplato;
    }
    
    public void PlatosMenu(int codigo){
        ArrayList<Plato> platos = this.modeloplato.getPlatos(codigo);
        String Ingredientes = new String();
        int size = platos.size();
        if  (size >=1){
        for (int i=0;i<4;i++){
            if (i!=3){ 
              Ingredientes = Ingredientes + platos.get(0).getComidas().get(i).getNombre() + ",";
            }          
            else{
              Ingredientes = Ingredientes + platos.get(0).getComidas().get(i).getNombre();
            }
            System.out.println(platos.get(0).getComidas().get(i).getNombre());
        }
        this.initialView.Plato1_MenuLabel1.setText(Ingredientes);
        }
        if (size >= 2){
        for (int i=0;i<4;i++){
            if (i!=3){ 
              Ingredientes = Ingredientes + platos.get(1).getComidas().get(i).getNombre() + ",";
            }          
            else{
              Ingredientes = Ingredientes + platos.get(1).getComidas().get(i).getNombre();
            }
        }
        this.initialView.Plato2_MenuLabel1.setText(Ingredientes);
        }
        if (size >= 3){
        for (int i=0;i<4;i++){
            if (i!=3){ 
              Ingredientes = Ingredientes + platos.get(2).getComidas().get(i).getNombre() + ",";
            }          
            else{
              Ingredientes = Ingredientes + platos.get(2).getComidas().get(i).getNombre();
            }
        }
        this.initialView.Plato3_MenuLabel2.setText(Ingredientes);
        }
        if (size >=4){
        for (int i=0;i<4;i++){
            if (i!=3){ 
              Ingredientes = Ingredientes + platos.get(3).getComidas().get(i).getNombre() + ",";
            }          
            else{
              Ingredientes = Ingredientes + platos.get(3).getComidas().get(i).getNombre();
            }
        }
        this.initialView.Plato4_MenuLabel1.setText(Ingredientes);
        }
        if (size == 5){
        for (int i=0;i<4;i++){
            if (i!=3){ 
              Ingredientes = Ingredientes + platos.get(4).getComidas().get(i).getNombre() + ",";
            }          
            else{
              Ingredientes = Ingredientes + platos.get(4).getComidas().get(i).getNombre();
            }
        }
        this.initialView.Plato5_MenuLabel1.setText(Ingredientes);
        }
    }
}
