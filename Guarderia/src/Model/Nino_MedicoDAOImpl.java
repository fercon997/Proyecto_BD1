/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author gabrielbaron
 */
public class Nino_MedicoDAOImpl implements Nino_MedicoDAO{
    DBConnection con = new DBConnection();
    ArrayList<Nino_Medico> ninos_medico;
   
    public ArrayList<Nino_Medico> loadNinos() {
        Connection connection = con.connectToPostgres();
        ArrayList listaNinos = new ArrayList();
        Nino_Medico nino;
        String sql = "SELECT n.Nombre,n.CI_representante,n.Letra " + 
                "FROM Nino_4 n order by Nombre;";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nino = new Nino_Medico();
                nino.setNombre(rs.getString(1));
                nino.setCi_representante(rs.getString(2));
                nino.setLetra(rs.getString(3));
                listaNinos.add(nino);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return listaNinos;
    }
    public ArrayList<String> getcodigos(){
        Connection connection = con.connectToPostgres();
        ArrayList codigos = new ArrayList();
        String sql = "SELECT Letra, CI_representante FROM Nino_4 order by Nombre;";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                codigos.add(rs.getString(1)+rs.getString(2));
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
            e.getStackTrace();
        }
        return codigos;
    }
    public ArrayList<Pediatra> getpediatra(String codigo){
        Connection connection = con.connectToPostgres();
        ArrayList listaPediatras = new ArrayList();
        Pediatra ped;
        String Letra = codigo.substring(0,1);
        String CI = codigo.substring(1);
        String sql = "SELECT distinct p.Codigo, p.Nombre, p.Tlf_movil, p.Tlf_oficina FROM Pediatra_4 p, Atencion_4 at "
        + "WHERE at.Letra_nino ='"+ Letra + "' and at.CI_ppal ='"+ CI + "' and at.Codigo_pediatra = p.Codigo;"; 
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                ped = new Pediatra();
                ped.setCodigo(rs.getInt(1));
                ped.setNombre(rs.getString(2));
                ped.setTlf_movil(rs.getString(3));
                ped.setTlf_oficina(rs.getString(4));
                listaPediatras.add(ped);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
            e.getStackTrace();
        }
        return listaPediatras;
    }
    public ArrayList<Alergia> getAlergia(String codigo){
        Connection connection = con.connectToPostgres();
        ArrayList listaAlergias = new ArrayList();
        Alergia al;
        String Letra = codigo.substring(0,1);
        String CI = codigo.substring(1);
        String sql = "SELECT distinct al.Codigo, al.Descripcion FROM Alergia_4 al, Padecimiento_alergia_4 p_al "
        + "WHERE p_al.Letra_nino ='"+ Letra + "' and p_al.CI_representante ='"+ CI + "' and p_al.Codigo_alergia = al.Codigo;"; 
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                al = new Alergia();
                al.setCodigo(rs.getInt(1));
                al.setDescripcion(rs.getString(2));
                listaAlergias.add(al);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
            e.getStackTrace();
        }
        return listaAlergias;
    }
    public ArrayList<Enfermedad> getEnfermedad(String codigo){
        Connection connection = con.connectToPostgres();
        ArrayList listaEnfermedades = new ArrayList();
        Enfermedad en;
        String Letra = codigo.substring(0,1);
        String CI = codigo.substring(1);
        String sql = "SELECT distinct en.Codigo, en.Descripcion FROM Enfermedad_4 en, Padecimiento_enfermedad_4 p_en "
        + "WHERE p_en.Letra_nino ='"+ Letra + "' and p_en.CI_representante ='"+ CI + "' and p_en.Codigo_enfermedad = en.Codigo;"; 
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                en = new Enfermedad();
                en.setCodigo(rs.getInt(1));
                en.setDescripcion(rs.getString(2));
                listaEnfermedades.add(en);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
            e.getStackTrace();
        }
        return listaEnfermedades;
    }
    public ArrayList<Tratamiento> getTratamiento(String codigo){
        Connection connection = con.connectToPostgres();
        ArrayList listaTratamientos = new ArrayList();
        Tratamiento tr;
        String Letra = codigo.substring(0,1);
        String CI = codigo.substring(1);
        String sql = "SELECT distinct si.Codigo, si.Descripcion, me.Codigo, me.Descripcion, tr.Cantidad"
        + " FROM Sintoma_4 si, Medicamento_4 me, Tratamiento_4 tr "
        + "WHERE tr.Letra_nino ='"+ Letra + "' and tr.CI_representante ='"+ CI + "' and tr.Codigo_sintoma = si.Codigo and "
        + "tr.Codigo_medicamento = me.Codigo;"; 
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                tr = new Tratamiento();
                tr.setCodigo_Sintoma(rs.getInt(1));
                tr.setNombre_Sintoma(rs.getString(2));
                tr.setCodigo_Medicamento(rs.getInt(3));
                tr.setNombre_Medicamento(rs.getString(4));
                tr.setCantidad(rs.getString(5));
                listaTratamientos.add(tr);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
            e.getStackTrace();
        }
        return listaTratamientos;
    }
}
