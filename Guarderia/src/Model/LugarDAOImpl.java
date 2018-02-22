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
                    "(nextval(lugar_sequence),?,?,?)");
            pps.setString(2, lugar.getNombre());
            pps.setString(3, lugar.getTipo());
            pps.setInt(4, lugar.getCodigo_superior());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos cargados satisfactoriamente");
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(LugarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Lugar getDatosLugar(String codigo, String tipoCodigo) {
        Connection connection = con.connectToPostgres();
        Lugar lugar = new Lugar();
        String sql = "SELECT casa.nombre as casa, calle.nombre as calle, mun.nombre as municipio, " + 
                "ciudad.nombre as ciudad, edo.nombre as estado FROM lugar_4 casa, lugar_4 calle, " + 
                "lugar_4 mun, lugar_4 ciudad, lugar_4 edo WHERE casa.codigo = (SELECT cod_direccion " + 
                " FROM guarderia_4, lugar_4 WHERE " + tipoCodigo + " = '" + codigo + "' AND cod_direccion = codigo) AND " + 
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
    
    public ArrayList<Lugar> getCiudades() {
        Connection connection = con.connectToPostgres();
        ArrayList lugares = new ArrayList();
        String sql = "SELECT nombre FROM lugar_4 WHERE tipo = 'Ciudad';";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                Lugar lugar = new Lugar();
                lugar.setNombre(rs.getString(1));
                lugares.add(lugar);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return lugares;
    }
    
    public ArrayList<Lugar> getEstados() {
        Connection connection = con.connectToPostgres();
        ArrayList lugares = new ArrayList();
        String sql = "SELECT nombre FROM lugar_4 WHERE tipo = 'Estado';";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                Lugar lugar = new Lugar();
                lugar.setNombre(rs.getString(1));
                lugares.add(lugar);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return lugares;
    }
    
    public ArrayList<Lugar> getMunicipios() {
        Connection connection = con.connectToPostgres();
        ArrayList lugares = new ArrayList();
        String sql = "SELECT codigo, nombre FROM lugar_4 WHERE tipo = 'Municipio';";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                Lugar lugar = new Lugar();
                lugar.setCodigo(rs.getInt(1));
                lugar.setNombre(rs.getString(2));
                lugares.add(lugar);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return lugares;
    }
    
    public int getCodigoCalle() {
        Connection connection = con.connectToPostgres();
        String sql = "SELECT codigo FROM lugar_4 WHERE tipo IN ('Calle', 'Avenida');";
        int codigo = 0;
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                codigo = rs.getInt(1);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return codigo;
    }
    
    public int addDireccion(Lugar casa, Lugar calle) {
        Connection connection = con.connectToPostgres();
        String sql = "INSERT INTO lugar_4 (codigo, nombre, tipo, cod_superior) values (nextval('Lugar_sequence'), ?, ?, ?)";
        try {
            PreparedStatement pps = connection.prepareCall(sql);
            pps.setString(1, calle.getNombre());
            pps.setString(2, calle.getTipo());
            pps.setInt(3, calle.getCodigo_superior());
            pps.executeUpdate();
            pps.close();
        } catch (SQLException ex) {
            Logger.getLogger(LugarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql = "INSERT INTO lugar_4 (codigo, nombre, tipo, cod_superior) values (nextval('Lugar_sequence'), ?, ?, ?)";
        try {
            PreparedStatement pps = connection.prepareCall(sql);
            pps.setString(1, casa.getNombre());
            pps.setString(2, casa.getTipo());
            pps.setInt(3, getCodigoCalle());
            pps.executeUpdate();
            pps.close();
         } catch (SQLException ex) {
            Logger.getLogger(LugarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql = "SELECT codigo FROM lugar_4 WHERE tipo IN ('Casa', 'Edificio');";
        int codigo = 0;
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                codigo = rs.getInt(1);
            }
            JOptionPane.showMessageDialog(null, "Datos cargados satisfactoriamente");
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return codigo;
    }
}
