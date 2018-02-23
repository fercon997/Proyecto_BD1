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
public class SIntomaDAOImpl implements SintomaDAO{
    DBConnection con = new DBConnection();
    public ArrayList<Sintoma> getSintomas(){
        Connection connection = con.connectToPostgres();
        ArrayList <Sintoma> sintomas = new ArrayList();
        Sintoma sintoma;
        String sql = "SELECT Descripcion " + 
                "FROM Sintoma_4;";
         try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                sintoma = new Sintoma();
                sintoma.setDescripcion(rs.getString(1));
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
}
