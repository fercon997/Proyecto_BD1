/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author fercon997
 */
public class JuegoDAOImpl implements JuegoDAO{
    DBConnection con;
    ArrayList<Juego>  juegos;

    public JuegoDAOImpl() {
        this.con = new DBConnection();
    }
    
    @Override
    public void insertarJuego(Juego game) throws SQLException{
            Connection cn = con.connectToPostgres();
            PreparedStatement pps;
            pps = cn.prepareCall("INSERT INTO juego_4 values(nextval('Juego_sequence'), ?)"); 
            pps.setString(1, game.getNombre());
            pps.executeUpdate();
            pps.close();
            cn.close();
    }
    
    public ArrayList<Juego> getJuegos(){
        Connection cn = con.connectToPostgres();
        ArrayList<Juego> games = new ArrayList();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM juego_4");
            while(rs.next()){
                Juego game = new Juego();
                game.setCodigo(rs.getInt("codigo"));
                game.setNombre(rs.getString("nombre"));
                games.add(game);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JuegoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return games;
    }
    
    public Juego informacionJuego(int codigo){
        Connection cn = con.connectToPostgres();
        Juego game = new Juego();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM juego_4 WHERE codigo = "+codigo+";");
            while(rs.next()){
                game.setCodigo(rs.getInt("codigo"));
                game.setNombre(rs.getString("nombre"));
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JuegoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return game;
    }
    
    public void updateJuego(Juego game){
        Connection cn = con.connectToPostgres();
        try {
            PreparedStatement pps = cn.prepareCall("UPDATE juego_4 set codigo = ?, nombre = ?"+
                    " WHERE codigo = ?;");
            pps.setInt(1, game.getCodigo());
            pps.setString(2, game.getNombre());
            pps.setInt(3, game.getCodigo());
            pps.executeUpdate();
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JuegoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteJuego(int codigo){
        Connection cn = con.connectToPostgres();
        try {
            PreparedStatement pps = cn.prepareCall("DELETE FROM juego_4 WHERE codigo = ?");
            pps.setInt(1, codigo);
            pps.executeUpdate();
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JuegoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se puede borrar");
        }
        
    }
    
    
}
