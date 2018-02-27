/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author fercon997
 */
public class RepresentanteDAOImpl implements RepresentanteDAO {
    ArrayList<Representante> representantes;
    DBConnection con;

    public RepresentanteDAOImpl() {
        con = new DBConnection();
    }
   
    @Override
    public void insertRepresentante(Representante parent) throws SQLException {
        //try {
            Connection cn = con.connectToPostgres();
            PreparedStatement pps = cn.prepareCall("INSERT INTO representante_4 VALUES " +
                    "(?,?,?,?,?,?,?,?,?,?,?)");
            pps.setString(1, parent.getCi());
            pps.setString(2, parent.getNombre());
            pps.setString(3, parent.getApellido());
            pps.setLong(4, parent.getCelular());
            pps.setLong(5, parent.getTlf_casa());
            pps.setLong(6, parent.getTlf_oficina());
            pps.setString(7, parent.getEmail());
            pps.setString(8, parent.getProfesion());
            pps.setString(9, Character.toString(parent.getEdo_civil()));
            pps.setInt(10, parent.getPrincipal());
            pps.setInt(11, parent.getCod_direccion());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos cargados satisfactoriamente");
            pps.close();
            cn.close();
        /*} catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en los datos");
            Logger.getLogger(LugarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    @Override
    public ArrayList<Representante> loadRepresentantes(String rif){
        Connection cn = con.connectToPostgres();
        
        ArrayList<Representante> parents = new ArrayList();
        String sql= "SELECT distinct R.ci, R.nombre, R.apellido, R.principal FROM representante_4 R, "+
                    "nino_4 N, inscripcion_4 I WHERE R.ci = N.ci_representante " +
                    "AND N.ci_representante = I.ci_representante AND I.rif_guarderia = '"+rif+"';";
        try {    
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Representante parent = new Representante();
                parent.setNombre(rs.getString("nombre"));
                parent.setApellido(rs.getString("apellido"));
                parent.setCi(rs.getString("ci"));
                parent.setPrincipal(rs.getInt("principal"));
                parents.add(parent);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepresentanteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parents;
        
    }
    
    public ArrayList<Representante> loadAllRepresentantes(){
        Connection cn = con.connectToPostgres();
        
        ArrayList<Representante> parents = new ArrayList();
        String sql= "SELECT distinct R.ci, R.nombre, R.apellido, R.principal FROM representante_4 R ";
        try {    
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Representante parent = new Representante();
                parent.setNombre(rs.getString("nombre"));
                parent.setApellido(rs.getString("apellido"));
                parent.setCi(rs.getString("ci"));
                parent.setPrincipal(rs.getInt("principal"));
                parents.add(parent);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepresentanteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parents;
        
    }
    
    @Override
    public Representante showDatosRepresentante(String ci){
        Connection cn = con.connectToPostgres();
        Representante parent = new Representante();
        String paramCons = "R.apellido, R.celular, R.ci, R.cod_direccion, R.estado_civil,"+
                "R.email, R.nombre, R.principal, R.profesion, R.tlf_casa, R.tlf_oficina, N.nombre";
        String sql = "SELECT "+paramCons+" FROM representante_4 R LEFT JOIN nino_4 N ON R.ci = N.ci_representante" +
                "  WHERE ci = '"+ci+"';";
        ArrayList<String> ninos = new ArrayList();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                parent.setApellido(rs.getString("apellido"));
                parent.setCelular(rs.getLong("celular"));
                parent.setCi(rs.getString("ci"));
                parent.setCod_direccion(rs.getInt("cod_direccion"));
                parent.setEdo_civil(rs.getString("estado_civil").charAt(0));
                parent.setEmail(rs.getString("email"));
                parent.setNombre(rs.getString("nombre"));
                parent.setPrincipal(rs.getInt("principal"));
                parent.setProfesion(rs.getString("Profesion"));
                parent.setTlf_casa(rs.getLong("tlf_casa"));
                parent.setTlf_oficina(rs.getLong("tlf_oficina"));
                ninos.add(rs.getString(12));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepresentanteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        parent.setNinos(ninos);
        return parent;
    }
    
    @Override
    public void deleteRepresentante(String ci){
        try {
            Connection cn = con.connectToPostgres();
            PreparedStatement pps = cn.prepareCall("DELETE FROM representante_4 WHERE ci = ?");
            pps.setString(1, ci);
            pps.executeUpdate();
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se puede borrar");
            Logger.getLogger(RepresentanteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void updateRepresentante(Representante parent){
        try {
            Connection cn = con.connectToPostgres();
            String sql= "UPDATE representante_4 SET apellido = ?, celular = ?, "+
                    "estado_civil = ?, email = ?, nombre = ?, " +
                    "Profesion = ?, tlf_casa = ?, tlf_oficina = ? WHERE ci = ?";
            PreparedStatement pps = cn.prepareCall(sql);
            pps.setString(1, parent.getApellido());
            pps.setLong(2, parent.getCelular());
            pps.setString(3, Character.toString(parent.getEdo_civil()));
            pps.setString(4, parent.getEmail());
            pps.setString(5, parent.getNombre());
            pps.setString(6, parent.getProfesion());
            pps.setLong(7, parent.getTlf_casa());
            pps.setLong(8, parent.getTlf_oficina());
            pps.setString(9, parent.getCi());
            pps.executeUpdate();
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepresentanteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en los datos");
        }
    }
    
    public ArrayList<Representante> getRepresentantesNuevos() {
        Connection cn = con.connectToPostgres();
        
        ArrayList<Representante> parents = new ArrayList();
        String sql= "SELECT distinct R.ci, R.nombre, R.apellido FROM representante_4 R where " + 
                "NOT EXISTS (Select ci_representante from inscripcion_4 where r.ci = ci_representante);";
        try {    
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Representante parent = new Representante();
                parent.setNombre(rs.getString("nombre"));
                parent.setApellido(rs.getString("apellido"));
                parent.setCi(rs.getString("ci"));
                parents.add(parent);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepresentanteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parents;
    }
    
    
}
