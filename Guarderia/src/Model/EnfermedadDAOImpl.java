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
public class EnfermedadDAOImpl implements EnfermedadDAO{
    DBConnection con = new DBConnection();
    public ArrayList<Enfermedad> getEnfermedades(){
        Connection connection = con.connectToPostgres();
        ArrayList <Enfermedad> enfermedades = new ArrayList();
        Enfermedad enfermedad;
        String sql = "SELECT Descripcion " + 
                "FROM Enfermedad_4;";
         try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                enfermedad = new Enfermedad();
                enfermedad.setDescripcion(rs.getString(1));
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
}
