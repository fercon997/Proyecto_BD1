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
 * @author Ignacio
 */
public class GuarderiaDAO {
    DBConnection con = new DBConnection();
    Connection connection = con.connectToPostgres();
    
    public ArrayList<Guarderia> loadGuarderias() {
        ArrayList listaGuarderias = new ArrayList();
        Guarderia guarderia;
        String sql = "SELECT g.rif, l.nombre " + 
                "FROM guarderia_4 g, lugar_4 l, lugar_4 ll " +
                "where g.cod_direccion = ll.codigo and ll.cod_superior = l.codigo;";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                guarderia = new Guarderia();
                guarderia.setComboText(rs.getString(2) + " (" + rs.getString(1) + ")");
                listaGuarderias.add(guarderia);
            }
        } catch(SQLException e) {
            System.out.println("Error");
            e.getStackTrace();
        }
        
        return listaGuarderias;
    }
}
