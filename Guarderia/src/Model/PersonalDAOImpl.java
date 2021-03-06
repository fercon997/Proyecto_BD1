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
 * @author Ignacio
 */
public class PersonalDAOImpl implements PersonalDAO {
    DBConnection con;
    
    public PersonalDAOImpl() {
        con = new DBConnection();
    }
    
    public void insertPersonal(Personal personal) {
        try {
            Connection cn = con.connectToPostgres();
            PreparedStatement pps = cn.prepareCall("INSERT INTO personal_4 VALUES " +
                    "(?,?,?,?,?,?,?,?,?,?)");
            pps.setString(1, personal.getCi());
            pps.setString(2, personal.getNombre());
            pps.setString(3, personal.getApellido());
            pps.setLong(4, Long.parseLong(personal.getCelular()));
            pps.setString(5, personal.getNivelEstudio());
            pps.setInt(6, personal.getSueldo());
            pps.setInt(7, personal.getEncargada());
            pps.setDate(8, personal.getFechaResponsable());
            pps.setInt(9, personal.getCod_direccion());
            pps.setString(10, personal.getRifGuarderia());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos cargados satisfactoriamente");
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en los datos");
            Logger.getLogger(Personal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Personal> loadPersonal(String rif){
        Connection cn = con.connectToPostgres();
        
        ArrayList<Personal> personal = new ArrayList();
        String sql= "SELECT distinct p.ci, p.nombre, p.apellido, p.celular, p.sueldo FROM personal_4 p, "+
                    "guarderia_4 g WHERE p.rif_guarderia = g.rif " +
                    "AND p.rif_guarderia = '"+rif+"';";
        try {    
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Personal per = new Personal();
                per.setNombre(rs.getString("nombre"));
                per.setApellido(rs.getString("apellido"));
                per.setCi(rs.getString("ci"));
                per.setCelular(rs.getString("celular"));
                per.setSueldo(rs.getInt("sueldo"));
                personal.add(per);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonalDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personal;
        
    }
    
    public ArrayList<Personal> loadAllPersonal(){
        Connection cn = con.connectToPostgres();
        ArrayList<Personal> personal = new ArrayList();
        String sql= "SELECT distinct R.ci, R.nombre, R.apellido, R.celular, R.sueldo FROM personal_4 R;";
        try {    
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Personal per = new Personal();
                per.setNombre(rs.getString("nombre"));
                per.setApellido(rs.getString("apellido"));
                per.setCi(rs.getString("ci"));
                per.setCelular(rs.getString("celular"));
                per.setSueldo(rs.getInt("sueldo"));
                personal.add(per);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonalDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personal;
        
    }
    
    public Personal showDatosPersonal(String ci){
        Connection cn = con.connectToPostgres();
        Personal personal = new Personal();
        String paramCons = "R.apellido, R.celular, R.ci, R.cod_direccion, R.rif_guarderia, "+
                "R.nombre, R.nivel_estudio, R.sueldo, R.señalEncargada, R.fecha_responsable, r.cod_direccion, e.nombre ";
        String sql = "SELECT "+paramCons+" FROM personal_4 r left join experiencia_4 e on r.ci = e.ci_personal WHERE ci = '"+ci+"';";
        ArrayList<String> experiencia = new ArrayList();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                personal.setApellido(rs.getString("apellido"));
                personal.setCelular(rs.getString("celular"));
                personal.setCi(rs.getString("ci"));
                personal.setCod_direccion(rs.getInt("cod_direccion"));
                personal.setNombre(rs.getString(6));
                personal.setRifGuarderia(rs.getString("rif_guarderia"));
                personal.setNivelEstudio(rs.getString("nivel_estudio"));
                personal.setSueldo(rs.getInt("sueldo"));
                personal.setEncargada(rs.getInt("señalEncargada"));
                personal.setFechaResponsable(rs.getDate("fecha_responsable"));
                experiencia.add(rs.getString(12));
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepresentanteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        personal.setExperiencia(experiencia);
        return personal;
    }
    
    public void deletePersonal(String ci){
        try {
            Connection cn = con.connectToPostgres();
            PreparedStatement pps = cn.prepareCall("DELETE FROM personal_4 WHERE ci = ?");
            pps.setString(1, ci);
            pps.executeUpdate();
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se puede borrar");
            Logger.getLogger(RepresentanteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updatePersonal(Personal personal){
        try {
            Connection cn = con.connectToPostgres();
            String sql= "UPDATE personal_4 SET apellido = ?, celular = ?, "+
                    "nombre = ?, señalencargada = ?, fecha_responsable = ?, " +
                    "sueldo = ?, nivel_Estudio = ? WHERE ci = ?";
            PreparedStatement pps = cn.prepareCall(sql);
            pps.setString(1, personal.getApellido());
            pps.setLong(2, Long.parseLong(personal.getCelular()));
            pps.setString(3, personal.getNombre());
            pps.setInt(4, personal.getEncargada());
            pps.setDate(5, personal.getFechaResponsable());
            pps.setInt(6, personal.getSueldo());
            pps.setString(7, personal.getNivelEstudio());
            pps.setString(8, personal.getCi());
            pps.executeUpdate();
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepresentanteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en los datos");
        }
    }
}
