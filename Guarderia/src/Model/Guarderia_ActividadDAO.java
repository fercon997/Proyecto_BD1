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
public class Guarderia_ActividadDAO {
    DBConnection con = new DBConnection(); 
    
    public ArrayList<String> getactividades(String rif){
        Connection connection = con.connectToPostgres();
        ArrayList actividades = new ArrayList();
        String sql = "SELECT Actividad.Nombre FROM Actividad_4 Actividad, Act_Guarderia_4 Act_guard "
        + " WHERE Actividad.Codigo = Act_guard.Cod_actividad AND Act_guard.RIF_guarderia = '" + rif + "';";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                actividades.add(rs.getString(1));
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error");
            e.getStackTrace();
        }
        return actividades;
    }
}