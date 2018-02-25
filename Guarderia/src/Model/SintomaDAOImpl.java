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
public class SintomaDAOImpl implements SintomaDAO{
    DBConnection con = new DBConnection();
    public ArrayList<Sintoma> getSintomas(){
        Connection connection = con.connectToPostgres();
        ArrayList <Sintoma> sintomas = new ArrayList();
        Sintoma sintoma;
        String sql = "SELECT Descripcion, Codigo " + 
                "FROM Sintoma_4;";
         try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                sintoma = new Sintoma();
                sintoma.setDescripcion(rs.getString(1));
                sintoma.setCodigo(rs.getInt(2));
                sintomas.add(sintoma);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return sintomas;
        
    }
    public Sintoma informacionSintoma(int codigo){
        Connection cn = con.connectToPostgres();
        Sintoma sintoma = new Sintoma();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM sintoma_4 WHERE codigo = "+codigo+";");
            while(rs.next()){
                sintoma.setCodigo(rs.getInt("codigo"));
                sintoma.setDescripcion(rs.getString("descripcion"));
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SintomaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sintoma;
    }
    public void updateSintoma(Sintoma sintoma){
        Connection cn = con.connectToPostgres();
        try {
            PreparedStatement pps = cn.prepareCall("UPDATE sintoma_4 set codigo = ?, descripcion = ?"+
                    " WHERE codigo = ?;");
            pps.setInt(1, sintoma.getCodigo());
            pps.setString(2, sintoma.getDescripcion());
            pps.setInt(3, sintoma.getCodigo());
            pps.executeUpdate();
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SintomaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleteSintoma(int codigo){
        Connection cn = con.connectToPostgres();
        try {
            PreparedStatement pps = cn.prepareCall("DELETE FROM sintoma_4 WHERE codigo = ?");
            pps.setInt(1, codigo);
            pps.executeUpdate();
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SintomaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se puede borrar");
        }
        
    }
}
