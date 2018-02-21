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

/**
 *
 * @author Ignacio
 */
public class LugarDAO {
    
    DBConnection con = new DBConnection();
    
    public Lugar getDatosLugar(String rif) {
        Connection connection = con.connectToPostgres();
        Lugar lugar = new Lugar();
        String sql = "SELECT casa.nombre as casa, calle.nombre as calle, mun.nombre as municipio, " + 
                "ciudad.nombre as ciudad, edo.nombre as estado FROM lugar_4 casa, lugar_4 calle, " + 
                "lugar_4 mun, lugar_4 ciudad, lugar_4 edo WHERE casa.codigo = (SELECT cod_direccion " + 
                " FROM guarderia_4, lugar_4 WHERE rif = '" + rif + "' AND cod_direccion = codigo) AND " + 
                "casa.tipo IN ('Casa', 'Edificio') AND casa.cod_superior = calle.codigo AND " + 
                "calle.tipo IN ('Calle', 'Avenida') AND calle.cod_superior = mun.codigo AND " + 
                "mun.tipo IN ('Municipio', 'Urbanizacion') AND mun.cod_superior = ciudad.codigo " + 
                "AND ciudad.tipo = 'Ciudad' AND ciudad.cod_superior = edo.codigo AND edo.tipo = 'Estado';";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                lugar.setCasa(rs.getString(1));
                lugar.setCalle(rs.getString(2));
                lugar.setMunicipio(rs.getString(3));
                lugar.setCiudad(rs.getString(4));
                lugar.setEstado(rs.getString(5));
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error");
            e.getStackTrace();
        }
        return lugar;
    }
}
