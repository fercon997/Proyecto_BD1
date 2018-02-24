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
public class Juego_NinoDAOImpl {
    DBConnection con;
    ArrayList<Juego_Nino> juegosNinos;

    public Juego_NinoDAOImpl() {
        con = new DBConnection();
    }
    
    public void insertarJuegoNino(Juego_Nino gamesKid) throws SQLException{
        Connection cn = con.connectToPostgres();
        PreparedStatement pps = cn.prepareCall("INSERT into gusto_4 VALUES(?,?,?)");
        pps.setInt(1, gamesKid.getCodigoJuego());
        pps.setString(2, Character.toString(gamesKid.getLetraNino()));
        pps.setString(3, gamesKid.getCiRepresentante());
        pps.executeUpdate();
        pps.close();
        cn.close();
    }
    
    public ArrayList<Juego> getJuegosNino(Nino kid){
        ArrayList<Juego> games = new ArrayList();
        String sql = "select j.nombre, j.codigo from juego_4 j, gusto_4 g"+
            " where j.codigo = g.codigo_juego and g.ci_representante = '"
            +kid.getCiRepresentante()+"' and g.letra_nino = '"+kid.getLetra()+"';";
        Connection cn = con.connectToPostgres();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Juego game = new Juego();
                game.setCodigo(rs.getInt("codigo"));
                game.setNombre(rs.getString("nombre"));
                games.add(game);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Juego_NinoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return games;
    }
    
    public void deleteJuego(Nino kid, int codigo){
        try {
            Connection cn = con.connectToPostgres();
            PreparedStatement pps = cn.prepareCall("DELETE FROM gusto_4 WHERE ci_representante = ? "+
                    "AND letra_nino = ? AND codigo_juego = ?");
            pps.setString(1, kid.getCiRepresentante());
            pps.setString(2, String.valueOf(kid.getLetra()));
            pps.setInt(3, codigo);
            pps.executeUpdate();
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se puede borrar");
            Logger.getLogger(RepresentanteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
