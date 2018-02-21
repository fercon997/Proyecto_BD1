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

/**
 *
 * @author fercon997
 */
public class LugarDAOImpl implements LugarDAO {
    DBConnection con;
    ArrayList<Lugar> Lugares;

    public LugarDAOImpl() {
        con = new DBConnection();
    }
    
    @Override
    public void insertLugar(Lugar lugar){
        try {
            Connection cn = con.connectToPostgres();
            PreparedStatement pps = cn.prepareCall("INSERT INTO lugar_4 VALUES " +
                    "(nextval(lugar_sequence),?,?,?");
            pps.setString(1, lugar.getNombre());
            pps.setString(2, lugar.getTipo());
            pps.setInt(3, lugar.getCodigo_superior());
        } catch (SQLException ex) {
            Logger.getLogger(LugarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
