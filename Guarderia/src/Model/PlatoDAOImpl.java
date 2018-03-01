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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author gabrielbaron
 */
public class PlatoDAOImpl {
     DBConnection con = new DBConnection();
     
     public ArrayList<Plato> getPlatos(int Codigo){
        Connection connection = con.connectToPostgres();
        ArrayList platos = new ArrayList();
        Plato plato;
        String sql = "SELECT DISTINCT p.codigo, p.descripcion FROM plato_4 p, menu_semanal_4 m "
        + "WHERE m.numero_menu = '" + Codigo + "' and m.cod_plato = p.codigo;";  
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                plato = new Plato();
                plato.setCodigo(rs.getInt(1));
                plato.setDescripcion(rs.getString(2));
                plato.setComidas(getComidas(plato.getCodigo()));
                platos.add(plato);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error" + e);
            e.getStackTrace();
        }
        return platos;
     }
     public ArrayList<Comida> getComidas(int Codigo){
        Connection connection = con.connectToPostgres();
        ArrayList comidas = new ArrayList();
        Comida comida;
        String sql = "SELECT DISTINCT c.codigo, c.tipo, c.nombre FROM comida_4 c, comida_plato_4 co "
        + "WHERE co.cod_plato = '" + Codigo + "' and c.codigo = co.cod_comida;";  
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                comida = new Comida();
                comida.setCodigo(rs.getInt(1));
                comida.setTipo(rs.getString(2));
                comida.setNombre(rs.getString(3));
                System.out.println(comida.getNombre());
                System.out.println("ppc");
                comidas.add(comida);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error" + e);
            e.getStackTrace();
        }
        return comidas;
     }
     public ArrayList<Plato> Platos_sin_menu(){
         Connection connection = con.connectToPostgres();
        ArrayList platos = new ArrayList();
        Plato plato;
        String sql = "SELECT DISTINCT p.codigo, p.descripcion FROM plato_4 p, menu_semanal_4 m "
        + "WHERE m.cod_plato = p.codigo;";  
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                plato = new Plato();
                plato.setCodigo(rs.getInt(1));
                plato.setDescripcion(rs.getString(2));
                plato.setComidas(getComidas(plato.getCodigo()));
                platos.add(plato);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error" + e);
            e.getStackTrace();
        }
        return platos;
     }
}
