/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Juego;
import Model.Juego_NinoDAOImpl;
import Model.Lugar;
import Model.Nino;
import Model.NinoDAOImpl;
import View.AddNinoView;
import View.InitialView;
import View.JDAddJuegoNino;
import View.JDAsistencia;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ignacio
 */
public class NinosController {
    
    InitialView initialView;
    AddNinoView anv;
    NinoDAOImpl modeloNino = new NinoDAOImpl();
    MultaController controladorMulta;
    PagoController controladorPagos;
    
    ArrayList<String> rifs;
    ArrayList<Nino> ninos;
    
    public NinosController(InitialView initialView, ArrayList<String> rifs) {
        this.initialView = initialView;
        this.rifs = rifs;
    }
    
    public void llenarNino(JComboBox cb, JTable tabla){
        DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        int numGuard = cb.getSelectedIndex();
        for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);
        Object[] columna = new Object[4];
        try{
            ninos = modeloNino.loadNino(rifs.get(numGuard-1));
            for(int i = 0; i<ninos.size(); i++){
                columna[0] = ninos.get(i).getCiRepresentante();
                columna[1] = ninos.get(i).getNombre();
                columna[2] = ninos.get(i).getApellido();
                columna[3] = ninos.get(i).getEdad();
                modeloTabla.addRow(columna);
            }
        } catch(Exception e){
            ninos = modeloNino.loadNino(null);
            for(int i = 0; i<ninos.size(); i++){
                columna[0] = ninos.get(i).getCiRepresentante();
                columna[1] = ninos.get(i).getNombre();
                columna[2] = ninos.get(i).getApellido();
                columna[3] = ninos.get(i).getEdad();
                modeloTabla.addRow(columna);
            }
        }
    }  
    
    public void mostrarNinos(JTable tabla){
        try{
            int index = tabla.getSelectedRow();
            initialView.nombreNino.setText(ninos.get(index).getNombre());
            initialView.apellidoNino.setText(ninos.get(index).getApellido());
            initialView.sexoNino.setText(String.valueOf(ninos.get(index).getSexo()));
            initialView.fechaNacNino.setText(ninos.get(index).getFechaNacimiento().toString());
            initialView.edadNino.setText(String.valueOf(ninos.get(index).getEdad()));
            Juego_NinoDAOImpl bdGameKid = new Juego_NinoDAOImpl();
            ArrayList<Juego> games = bdGameKid.getJuegosNino(ninos.get(index));
            String juegos = new String();
            for (int i=0; i<games.size(); i++){
                juegos = juegos + games.get(i).getNombre() + "\n";
            }
            initialView.ninoJuegosTxt.setText(juegos);
        } catch(Exception e){
            Logger.getLogger(GuarderiaController.class.getName()).log(Level.SEVERE, null, e);
            initialView.nombreNino.setText("");
            initialView.apellidoNino.setText("");
            initialView.sexoNino.setText("");
            initialView.fechaNacNino.setText("");
            initialView.edadNino.setText("");
            initialView.ninoJuegosTxt.setText("");

        }
    }
    
    public void addNino() {
        AddNinoView anv = new AddNinoView(initialView, true);
        anv.setVisible(true);
    }
    
    public void addJuego(JTable tabla){
        int index = tabla.getSelectedRow();
        String ci = ninos.get(index).getCiRepresentante();
        char letra = ninos.get(index).getLetra();
        String nombre = ninos.get(index).getNombre();
        Nino kid = new Nino();
        kid.setCiRepresentante(ci);
        kid.setLetra(letra);
        kid.setNombre(nombre);
        JDAddJuegoNino ajn = new JDAddJuegoNino(initialView,true, kid);
        ajn.setVisible(true);
    }
    
    public void verAsistencia(JTable tabla){
        int index = tabla.getSelectedRow();
        System.out.println(ninos.get(index).getLetra());
        JDAsistencia asist = new JDAsistencia(initialView, true, ninos.get(index));
        asist.setVisible(true);
    }
    
    public void llenarMultas(JTable tabla){
        int index = tabla.getSelectedRow();
        controladorMulta = new MultaController(initialView, ninos.get(index));
        controladorMulta.loadMultas(initialView.tablaMultas);
    }
    
    public void pagarMulta(JTable tabla){
        controladorMulta.pagarMulta(tabla);
    }
    
    public void generarMensualidad(){
        for (int i = 0; i< ninos.size(); i++){
            System.out.println(i);
           controladorPagos = new PagoController(initialView, ninos.get(i));
           controladorPagos.generarMenusalidad();
        }
    }
    
    public void llenarMensualidad(JTable tabla){
        int index = tabla.getSelectedRow();
        controladorPagos = new PagoController(initialView, ninos.get(index));
        controladorPagos.loadPagos(initialView.tablaPagos);
    }
    
    public void pagarMensualidad(JTable tabla){
        controladorPagos.pagarMensualidad(tabla);
    }

}
