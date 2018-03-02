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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gabrielbaron
 */
public class MenuDAOImpl {
    DBConnection con = new DBConnection();
    String rif;

    public void setRif(String rif) {
        this.rif = rif;
    }
    public ArrayList<Menu> getmenus(){
        Connection connection = con.connectToPostgres();
        ArrayList menus = new ArrayList();
        Menu menu;
        String sql = "SELECT m.numero, m.costo, m.fecha FROM menu_4 m "
        + "WHERE m.rif_guarderia = '" + this.rif + "';";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                menu = new Menu();
                menu.setNumero(rs.getInt(1));
                menu.setCosto(rs.getFloat(2));
                DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
                menu.setFecha(fecha.format(rs.getDate(3)));
                menus.add(menu);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error" + e);
            e.getStackTrace();
        }
        return menus;
    }
    public Menu InformacionMenu(int Codigo){
        Connection cn = con.connectToPostgres();
        Menu menu = new Menu();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT m.costo, m.fecha, m.fecha_fin FROM menu_4 m WHERE m.numero = "+Codigo+";");
            while(rs.next()){
                menu.setCosto(rs.getInt(1));
                DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
                menu.setFecha(fecha.format(rs.getDate(2)));
                menu.setFecha_fin(fecha.format(rs.getDate(3)));
                menu.setNumero(Codigo);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MenuDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return menu;
    }
     public void updateMenu(Menu menu){
        Connection cn = con.connectToPostgres();
        try {
            PreparedStatement pps = cn.prepareCall("UPDATE menu_4 set numero = ?, costo = ?"+
                    " WHERE numero = ?;");
            pps.setInt(1, menu.getNumero());
            pps.setFloat(2, menu.getCosto());
            pps.setInt(3, menu.getNumero());
            pps.executeUpdate();
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MenuDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    public void deleteMenu(int codigo){
        Connection cn = con.connectToPostgres();
        try {
            PreparedStatement pps = cn.prepareCall("DELETE FROM menu_4 WHERE numero = ?");
            pps.setInt(1, codigo);
            pps.executeUpdate();
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MenuDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se puede borrar");
        }
        
    }
    public int getNumero_Menu(){
        Connection connection = con.connectToPostgres();
        int numero = 0;
        String sql = "SELECT last_value FROM Menu_sequence;";
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
    public void InsertarMenu(Menu menu, ArrayList<Integer> platos) throws SQLException, ParseException{
        Connection cn = con.connectToPostgres();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement pps;
        pps = cn.prepareCall("INSERT INTO menu_4 values(nextval('Menu_sequence'), ?, ?, ?, ?)"); 
        java.util.Date date = sdf1.parse(menu.getFecha());
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        pps.setDate(1, sqlStartDate);
        java.util.Date date1 = sdf1.parse(menu.getFecha_fin());
        java.sql.Date sqlStartDate1 = new java.sql.Date(date1.getTime());
        pps.setDate(2, sqlStartDate1);
        pps.setString(3, menu.getRif());
        pps.setFloat(4, menu.getCosto());
        pps.executeUpdate();
        pps.close();
        cn.close();
        int size = platos.size();
        menu.setNumero(getNumero_Menu());
        for (int i=0; i<size; i++){
            InsertarPlato_Menu(menu,platos.get(i));
        }
    }
    public ArrayList<Integer> VerificarFecha(String Fecha) throws ParseException, SQLException{
       Connection connection = con.connectToPostgres();
       SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");  
       java.util.Date date = sdf1.parse(Fecha);
       java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
       String sql = "SELECT numero FROM menu_4 WHERE '"+sqlStartDate+"' between fecha and fecha_fin;";
       ArrayList numeros = new ArrayList();
       try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                numeros.add(rs.getInt(1));
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error" + e);
            e.getStackTrace();
        }
       return numeros;
    }
    public void InsertarPlato_Menu(Menu menu, int plato) throws SQLException, ParseException{
        Connection cn = con.connectToPostgres();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement pps;
        pps = cn.prepareCall("INSERT INTO menu_semanal_4 values(?, ?, ?)"); 
        pps.setInt(1, menu.getNumero());
        java.util.Date date = sdf1.parse(menu.getFecha());
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        pps.setDate(2, sqlStartDate);
        pps.setInt(3, plato);
        pps.executeUpdate();
        pps.close();
        cn.close(); 
    }
}

