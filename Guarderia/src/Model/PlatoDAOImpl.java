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
import java.text.DateFormat;
import java.text.ParseException;
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
        String sql = "SELECT DISTINCT p.codigo, p.descripcion FROM plato_4 p, menu_semanal_4 m;";
        //+ "WHERE m.cod_plato = p.codigo;";  
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
        System.out.println(platos.size());
        return platos;
     }
     public ArrayList<Comida> getComidas_Tipo(String tipo){
        Connection connection = con.connectToPostgres();
        ArrayList comidas = new ArrayList();
        Comida comida;
        String sql = "SELECT DISTINCT c.codigo, c.nombre FROM comida_4 c "
        + "WHERE c.tipo = '"+ tipo +"';";  
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                comida = new Comida();
                comida.setCodigo(rs.getInt(1));
                comida.setTipo(tipo);
                comida.setNombre(rs.getString(2));
                System.out.println(comida.getNombre());
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
     public String Buscarcomida(int codigo){
        Connection connection = con.connectToPostgres();
        String tipo = new String();
        String sql = "SELECT c.tipo FROM comida_4 c "
        + "WHERE c.codigo = '"+ codigo +"';";  
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
            tipo = rs.getString(1);
            System.out.println(tipo);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error" + e);
            e.getStackTrace();
        }
        return tipo;
     }
     public int getNumero_Plato(){
        Connection connection = con.connectToPostgres();
        int numero = 0;
        String sql = "SELECT last_value FROM Plato_sequence;";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
              numero = rs.getInt(1);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error" + e);
            e.getStackTrace();
        }
        return numero;
    }
     public void InsertarPlato(Plato plato, ArrayList<Integer> comidas) throws SQLException, ParseException{
        Connection cn = con.connectToPostgres();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement pps;
        pps = cn.prepareCall("INSERT INTO plato_4 values(nextval('Plato_sequence'), ?)"); 
        pps.setString(1, plato.getDescripcion());
        pps.executeUpdate();
        pps.close();
        cn.close();
        int size = comidas.size();
        plato.setCodigo(getNumero_Plato());
        for (int i=0; i<size; i++){
            InsertarComida_Plato(plato,comidas.get(i));
        }
     }
     public void InsertarComida_Plato(Plato plato, int comida) throws SQLException, ParseException{
        Connection cn = con.connectToPostgres();
        PreparedStatement pps;
        pps = cn.prepareCall("INSERT INTO comida_plato_4 values(?, ?)"); 
        pps.setInt(1, plato.getCodigo());
        pps.setInt(2, comida);
        pps.executeUpdate();
        pps.close();
        cn.close(); 
    }
     
}
