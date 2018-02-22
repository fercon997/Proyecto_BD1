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
public class Guarderia_ActividadDAOImpl implements Guarderia_ActividadDAO{
    DBConnection con = new DBConnection();
    String Rif;
    ArrayList<Guarderia_Actividad> actividades;
    
    public Guarderia_ActividadDAOImpl(String Rif) {
        this.Rif = Rif;
    }
    
    public ArrayList<Guarderia_Actividad> getactividades(){
        Connection connection = con.connectToPostgres();
        ArrayList actividades = new ArrayList();
        Guarderia_Actividad actividad = new Guarderia_Actividad();
        String sql = "SELECT Actividad.Nombre, Actividad.Descripcion, Actividad.EdadMinima, Act_guard.CupoMax,"
        + " Act_guard.CupoMinimo, Act_guard.Costo_actividad"
        + " FROM Actividad_4 Actividad, Act_Guarderia_4 Act_guard "
        + " WHERE Actividad.Codigo = Act_guard.Cod_actividad AND Act_guard.RIF_guarderia = '" + this.Rif + "';";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                actividad.setNombre(rs.getString(1));
                actividad.setDescripcion(rs.getString(2));
                actividad.setEdad_minima(rs.getInt(3));
                actividad.setCupo_Maximo(rs.getInt(4));
                actividad.setCupo_Minimo(rs.getInt(5));
                actividad.setCosto(rs.getInt(6));
                actividades.add(actividad);
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