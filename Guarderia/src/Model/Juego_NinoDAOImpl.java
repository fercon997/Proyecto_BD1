/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;
import java.util.ArrayList;

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
    
    
}
