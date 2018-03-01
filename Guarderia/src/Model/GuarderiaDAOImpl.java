package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class GuarderiaDAOImpl implements GuarderiaDAO {
    DBConnection con = new DBConnection();
    ArrayList<Guarderia> guarderias;

    public GuarderiaDAOImpl() {
        con = new DBConnection();
    }
    
    
    
    @Override
    public ArrayList<String> getRifs() {
        Connection connection = con.connectToPostgres();
        ArrayList rifs = new ArrayList();
        String sql = "SELECT rif FROM Guarderia_4";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                rifs.add(rs.getString(1));
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error");
            e.getStackTrace();
        }
        return rifs;
    }
    
    @Override
    public ArrayList<Guarderia> loadGuarderias() {
        Connection connection = con.connectToPostgres();
        ArrayList listaGuarderias = new ArrayList();
        Guarderia guarderia;
        String sql = "SELECT nombre " + 
                "FROM guarderia_4;";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                guarderia = new Guarderia();
                guarderia.setComboText(rs.getString(1));
                listaGuarderias.add(guarderia);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return listaGuarderias;
    }
    
    @Override
    public Guarderia getDatosGuarderia(String rif) {
        Connection connection = con.connectToPostgres();
        Guarderia guarderia = new Guarderia();
        String sql = "SELECT horario_entrada, horario_salida, costo_multa, costo_mensualidad, costo_agodic FROM Guarderia_4 WHERE RIF = '" + rif + "';";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                guarderia.setHoraEntrada(rs.getTime(1));
                guarderia.setHoraSalida(rs.getTime(2));
                guarderia.setCostoMulta(rs.getInt(3));
                guarderia.setCostoMensualidad(rs.getInt(4));
                guarderia.setCostoAgoDic(rs.getInt(5));
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error");
            e.getStackTrace();
        }
        return guarderia;
    }
    
    public void saveGuarderia(Guarderia guarderia) {
        Connection connection = con.connectToPostgres();
        String sql = "INSERT INTO guarderia_4 values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pps = connection.prepareCall(sql);
            pps.setString(1, guarderia.getRif());
            pps.setString(2, guarderia.getNombre());
            pps.setInt(3, guarderia.getCostoMensualidad());
            pps.setInt(4, guarderia.getCostoMulta());
            pps.setInt(5, guarderia.getConstoTransporte());
            pps.setInt(6, guarderia.getCostoAgoDic());
            pps.setInt(7, guarderia.getCostoHoraExtra());
            pps.setTime(8, guarderia.getHoraEntrada());
            pps.setTime(9, guarderia.getHoraSalida());
            pps.setInt(10, guarderia.getCodDireccion());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos cargados satisfactoriamente");
            pps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(LugarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteGuarderia(String rif) {
        Connection connection = con.connectToPostgres();
        String sql = "DELETE FROM guarderia_4 WHERE Rif = '" + rif + "';";
        try {
            PreparedStatement pps = connection.prepareStatement(sql);
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos eliminados");
            pps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GuarderiaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateGuarderia(Guarderia guarderia) {
        Connection connection = con.connectToPostgres();
        String sql = "UPDATE guarderia_4 SET rif = '" + guarderia.getRif() + "', "+
                    "nombre = '" + guarderia.getNombre() + "', Horario_entrada = '" + guarderia.getHoraEntrada() + "', " +
                    "horario_salida = '" + guarderia.getHoraSalida() + "', cod_direccion = " + guarderia.getCodDireccion() +
                " WHERE rif = '" + guarderia.getRif() + "';";
        try {
            PreparedStatement pps = connection.prepareStatement(sql);
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos cargados satisfactoriamente");
            pps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(LugarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
