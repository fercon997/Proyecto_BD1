package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos cargados satisfactoriamente");
        } catch (SQLException ex) {
            Logger.getLogger(LugarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
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
