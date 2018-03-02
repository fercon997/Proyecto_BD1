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
public class EnfermedadDAOImpl implements EnfermedadDAO{
    DBConnection con = new DBConnection();
    public ArrayList<Enfermedad> getEnfermedades(){
        Connection connection = con.connectToPostgres();
        ArrayList <Enfermedad> enfermedades = new ArrayList();
        Enfermedad enfermedad;
        String sql = "SELECT Descripcion, Codigo " + 
                "FROM Enfermedad_4;";
         try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                enfermedad = new Enfermedad();
                enfermedad.setDescripcion(rs.getString(1));
                 enfermedad.setCodigo(rs.getInt(2));
                enfermedades.add(enfermedad);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return enfermedades;   
    }
     public Enfermedad informacionEnfermedad(int codigo){
        Connection cn = con.connectToPostgres();
        Enfermedad enfermedad = new Enfermedad();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM enfermedad_4 WHERE codigo = "+codigo+";");
            while(rs.next()){
                enfermedad.setCodigo(rs.getInt("codigo"));
                enfermedad.setDescripcion(rs.getString("descripcion"));
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(EnfermedadDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enfermedad;
    }
    public void updateEnfermedad(Enfermedad enfermedad){
        Connection cn = con.connectToPostgres();
        try {
            PreparedStatement pps = cn.prepareCall("UPDATE enfermedad_4 set codigo = ?, descripcion = ?"+
                    " WHERE codigo = ?;");
            pps.setInt(1, enfermedad.getCodigo());
            pps.setString(2, enfermedad.getDescripcion());
            pps.setInt(3, enfermedad.getCodigo());
            pps.executeUpdate();
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(EnfermedadDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleteEnfermedad(int codigo){
        Connection cn = con.connectToPostgres();
        try {
            PreparedStatement pps = cn.prepareCall("DELETE FROM enfermedad_4 WHERE codigo = ?");
            pps.setInt(1, codigo);
            pps.executeUpdate();
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(EnfermedadDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se puede borrar");
        }
        
    }
}
