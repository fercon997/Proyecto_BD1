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
public class PediatraDAOImpl implements PediatraDAO{
    DBConnection con = new DBConnection();
    public ArrayList<Pediatra> getPediatras(){
        Connection connection = con.connectToPostgres();
        ArrayList <Pediatra> pediatras = new ArrayList();
        Pediatra pediatra;
        String sql = "SELECT Nombre, Tlf_movil, Tlf_oficina  " + 
                "FROM Pediatra_4;";
         try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                pediatra = new Pediatra();
                pediatra.setNombre(rs.getString(1));
                pediatra.setTlf_movil(rs.getString(2));
                pediatra.setTlf_oficina(rs.getString(3));
                pediatras.add(pediatra);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return pediatras;
        
    }
}
