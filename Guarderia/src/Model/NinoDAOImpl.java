/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
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
 * @author Ignacio
 */
public class NinoDAOImpl {
    
    DBConnection con = new DBConnection();
    
    public ArrayList<Nino> loadNino(String rif){
        Connection cn = con.connectToPostgres();
        String sql;
        
        ArrayList<Nino> ninos = new ArrayList();
        if (rif == null) {
            sql= "SELECT distinct n.letra, n.ci_representante, n.nombre, n.apellido, n.fecha_nacimiento, n.sexo FROM "+
                    "nino_4 N ORDER BY n.ci_representante, n.letra ASC;";
        } else {
            sql= "SELECT distinct n.letra, n.ci_representante, n.nombre, n.apellido, n.fecha_nacimiento, n.sexo FROM "+
                    "nino_4 N, inscripcion_4 I WHERE " +
                    "n.ci_representante = I.ci_representante AND I.rif_guarderia = '"+rif+"' ORDER BY n.ci_representante, n.letra ASC;";
        }
        try {    
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Nino nino = new Nino();
                nino.setLetra(rs.getString("letra").charAt(0));
                nino.setNombre(rs.getString("nombre"));
                nino.setApellido(rs.getString("apellido"));
                nino.setCiRepresentante(rs.getString("ci_representante"));
                nino.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                nino.setSexo(rs.getString("sexo").charAt(0));
                ninos.add(nino);
                System.out.println(rs.getString("nombre"));
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepresentanteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ninos;
    }
    
    public ArrayList<Nino> loadNinosDelPadre(String ci) {
        Connection cn = con.connectToPostgres();
        String sql;
        
        ArrayList<Nino> ninos = new ArrayList();
        sql= "SELECT distinct n.letra, n.ci_representante, n.nombre, n.apellido, n.fecha_nacimiento, n.sexo FROM "+
                    "nino_4 N, representante_4 r WHERE " +
                    "n.ci_representante = r.ci AND r.ci = '"+ci+"' ORDER BY n.apellido, n.nombre ASC;";

        try {    
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Nino nino = new Nino();
                nino.setLetra(rs.getString("letra").charAt(0));
                nino.setNombre(rs.getString("nombre"));
                nino.setApellido(rs.getString("apellido"));
                nino.setCiRepresentante(rs.getString("ci_representante"));
                nino.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                nino.setSexo(rs.getString("sexo").charAt(0));
                ninos.add(nino);
                System.out.println(rs.getString("nombre"));
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepresentanteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ninos;
    }
    
    public char getSiblings(String ci) {
        Connection cn = con.connectToPostgres();
        String sql;
        char letra = 'A';
        
        ArrayList<Nino> ninos = new ArrayList();
            sql= "SELECT distinct n.letra FROM "+
                    "nino_4 N, inscripcion_4 I WHERE n.ci_representante = '" + ci +
                    "' AND n.ci_representante = I.ci_representante ORDER BY letra;";
        try {    
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                letra = rs.getString("letra").charAt(0);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepresentanteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return letra;
    }
    
    public void saveGuarderia(Nino nino) {
        Connection connection = con.connectToPostgres();
        String sql = "INSERT INTO nino_4 values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pps = connection.prepareCall(sql);
            pps.setString(1, nino.getCiRepresentante());
            pps.setString(2, String.valueOf(nino.getLetra()));
            pps.setString(3, nino.getNombre());
            pps.setString(4, nino.getApellido());
            pps.setDate(5, nino.getFechaNacimiento());
            pps.setString(6, String.valueOf(nino.getSexo()));
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos cargados satisfactoriamente");
            pps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(LugarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Algún dato se introdujo de manera incorrecta.");
        }
    }
    
    
    
    
}
