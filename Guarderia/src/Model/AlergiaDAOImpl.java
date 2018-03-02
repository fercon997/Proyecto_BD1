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
public class AlergiaDAOImpl implements AlergiaDAO{
    DBConnection con = new DBConnection();
    public ArrayList<Alergia> getAlergias(){
        Connection connection = con.connectToPostgres();
        ArrayList <Alergia> alergias = new ArrayList();
        Alergia alergia;
        String sql = "SELECT Descripcion, Codigo " + 
                "FROM Alergia_4;";
         try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                alergia = new Alergia();
                alergia.setDescripcion(rs.getString(1));
                alergia.setCodigo(rs.getInt(2));
                alergias.add(alergia);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return alergias;
        
    }
    public Alergia informacionAlergia(int codigo){
        Connection cn = con.connectToPostgres();
        Alergia alergia = new Alergia();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM alergia_4 WHERE codigo = "+codigo+";");
            while(rs.next()){
                alergia.setCodigo(rs.getInt("codigo"));
                alergia.setDescripcion(rs.getString("descripcion"));
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlergiaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alergia;
    }
    public void updateAlergia(Alergia alergia){
        Connection cn = con.connectToPostgres();
        try {
            PreparedStatement pps = cn.prepareCall("UPDATE alergia_4 set codigo = ?, descripcion = ?"+
                    " WHERE codigo = ?;");
            pps.setInt(1, alergia.getCodigo());
            pps.setString(2, alergia.getDescripcion());
            pps.setInt(3, alergia.getCodigo());
            pps.executeUpdate();
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlergiaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleteAlergia(int codigo){
        Connection cn = con.connectToPostgres();
        try {
            PreparedStatement pps = cn.prepareCall("DELETE FROM alergia_4 WHERE codigo = ?");
            pps.setInt(1, codigo);
            pps.executeUpdate();
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlergiaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se puede borrar");
        }
        
    }
}
