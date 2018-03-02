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
public class Nino_ActividadDAOImpl {
    DBConnection con = new DBConnection();
    ArrayList<Nino_Medico> ninos_medico;
    public ArrayList<Nino_Medico> loadNinos() {
        Connection connection = con.connectToPostgres();
        ArrayList listaNinos = new ArrayList();
        Nino_Medico nino;
        String sql = "SELECT n.Nombre,n.CI_representante,n.Letra " + 
                "FROM Nino_4 n order by Nombre;";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nino = new Nino_Medico();
                nino.setNombre(rs.getString(1));
                nino.setCi_representante(rs.getString(2));
                nino.setLetra(rs.getString(3));
                listaNinos.add(nino);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return listaNinos;
    }
    public ArrayList<String> getcodigos(){
        Connection connection = con.connectToPostgres();
        ArrayList codigos = new ArrayList();
        String sql = "SELECT Letra, CI_representante FROM Nino_4 order by Nombre;";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                codigos.add(rs.getString(1)+rs.getString(2));
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
            e.getStackTrace();
        }
        return codigos;
    }
    public ArrayList<Guarderia_Actividad> getactividades(String codigo){
        Connection connection = con.connectToPostgres();
        ArrayList actividades = new ArrayList();
        Guarderia_Actividad actividad = new Guarderia_Actividad();
        String Letra = codigo.substring(0,1);
        String CI = codigo.substring(1);
        String sql = "SELECT distinct Actividad.Nombre, Actividad.Descripcion, Actividad.EdadMinima, "
        + "act_inscripcion.consto_actividad FROM Actividad_4 Actividad, act_inscripcion_4 act_inscripcion"
        + " WHERE Actividad.codigo = act_inscripcion.cod_actividad and act_inscripcion.ci_representante = '"+ CI + "' and act_inscripcion.letra_nino = '"+ Letra + "' ;";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                actividad.setNombre(rs.getString(1));
                actividad.setDescripcion(rs.getString(2));
                actividad.setEdad_minima(rs.getInt(3));
                actividad.setCosto(rs.getInt(4));
                actividades.add(actividad);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: "+ e);
            e.getStackTrace();
        }
        return actividades;
    }
}
