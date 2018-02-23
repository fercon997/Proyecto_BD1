/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        String sql = "SELECT Descripcion " + 
                "FROM Alergia_4;";
         try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                alergia = new Alergia();
                alergia.setDescripcion(rs.getString(1));
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
}
