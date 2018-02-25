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

/**
 *
 * @author fercon997
 */
public class AutorizadoDAOImpl implements AutorizadoDAO {
    DBConnection con;

    public AutorizadoDAOImpl() {
        con = new DBConnection();
    }
    
    @Override
    public void insertAutorizado(Autorizado auth) throws SQLException{
        Connection cn = con.connectToPostgres();
        String sql = "INSERT INTO autorizado_4 values(?,?,?,?)";
        PreparedStatement pps = cn.prepareCall(sql);
        pps.setString(1, auth.getCi());
        pps.setString(2, auth.getNombre());
        pps.setString(3, auth.getApellido());
        pps.setLong(4, auth.getCelular());
        pps.executeUpdate();
        pps.close();
        cn.close();
    }
    
    public ArrayList<Autorizado> getAutorizados(){
        Connection cn = con.connectToPostgres();
        ArrayList<Autorizado> auths = new ArrayList();
        String sql = "SELECT * FROM autorizado_4";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Autorizado auth = new Autorizado();
                auth.setApellido(rs.getString("apellido"));
                auth.setCelular(rs.getLong("celular"));
                auth.setCi(rs.getString("ci"));
                auth.setNombre(rs.getString("nombre"));
                auths.add(auth);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AutorizadoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
      return auths;
    }
    
    public void updateAutorizado(Autorizado auth) throws SQLException{
        Connection cn = con.connectToPostgres();
        PreparedStatement pps = cn.prepareCall("UPDATE autorizado_4 set nombre = ?, "+
                "apellido = ?, celular = ? WHERE ci = ?");
        pps.setString(1, auth.getNombre());
        pps.setString(2, auth.getApellido());
        pps.setLong(3, auth.getCelular());
        pps.setString(4, auth.getCi());
        pps.executeUpdate();
        pps.close();
        cn.close();
    }
    
    public void deleteAutorizado(String ci) {
        Connection cn = con.connectToPostgres();
        try {
            PreparedStatement pps = cn.prepareCall("DELETE FROM autorizado_4 WHERE ci=?");
            pps.setString(1, ci);
            pps.executeUpdate();
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AutorizadoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
