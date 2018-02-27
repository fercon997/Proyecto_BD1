
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

public class InscripcionDAOImpl {
    
    DBConnection con;
    
    public InscripcionDAOImpl() {
        con = new DBConnection();
    }
    
    public ArrayList<Inscripcion> loadAllInscripciones() {
        Connection connection = con.connectToPostgres();
        ArrayList<Inscripcion> inscripciones = new ArrayList();
        String sql = "SELECT i.ano, i.consecutivo, i.rif_guarderia, i.ci_representante, i.letra_nino, i.fecha_inscripcion, i.hora_desde, i.hora_hasta, n.nombre, n.apellido " + 
                "FROM inscripcion_4 i inner join nino_4 n on i.ci_representante = n.ci_representante and i.letra_nino = n.letra order by i.ci_representante;";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setAno(rs.getInt(1));
                inscripcion.setConsecutivo(rs.getInt(2));
                inscripcion.setRifGuarderia(rs.getString(3));
                inscripcion.setCiRepresentante(rs.getString(4));
                inscripcion.setLetraNino(rs.getString(5).charAt(0));
                inscripcion.setFechaInscripcion(rs.getDate(6));
                inscripcion.setHoraLlegada(rs.getTime(7));
                inscripcion.setHoraSalida(rs.getTime(8));
                inscripcion.setNombreNino(rs.getString(9));
                inscripcion.setApellidoNino(rs.getString(10));
                inscripciones.add(inscripcion);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error");
            e.getStackTrace();
        }
        return inscripciones;
    }
    
    public ArrayList<Inscripcion> loadInscripciones(String rif){
        Connection cn = con.connectToPostgres();
        
        ArrayList<Inscripcion> inscripciones = new ArrayList();
        String sql= "SELECT i.ano, i.consecutivo, i.ci_representante, i.letra_nino, i.fecha_inscripcion, i.hora_desde, i.hora_hasta, n.nombre, n.apellido " + 
                "FROM inscripcion_4 i inner join nino_4 n on i.ci_representante = n.ci_representante and i.letra_nino = n.letra " + 
                "WHERE rif_guarderia = '"+rif+"' order by n.apellido, n.nombre;";
        try {    
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setAno(rs.getInt(1));
                inscripcion.setConsecutivo(rs.getInt(2));
                inscripcion.setRifGuarderia(rif);
                inscripcion.setCiRepresentante(rs.getString(3));
                inscripcion.setLetraNino(rs.getString(4).charAt(0));
                inscripcion.setFechaInscripcion(rs.getDate(5));
                inscripcion.setHoraLlegada(rs.getTime(6));
                inscripcion.setHoraSalida(rs.getTime(7));
                inscripcion.setNombreNino(rs.getString(8));
                inscripcion.setApellidoNino(rs.getString(9));
                inscripciones.add(inscripcion);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepresentanteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inscripciones;
        
    }
    
    public void insertInscripcion(Inscripcion inscripcion) {
        try {
            Connection cn = con.connectToPostgres();
            String sql= "INSERT INTO inscripcion_4 VALUES (?, nextval('Insc_sequence'), ?, ?, ?, ?, ?, ?)";
            PreparedStatement pps = cn.prepareCall(sql);
            pps.setInt(1, inscripcion.getAno());
            pps.setString(2, inscripcion.getRifGuarderia());
            pps.setString(3, inscripcion.getCiRepresentante());
            pps.setString(4, String.valueOf(inscripcion.getLetraNino()));
            pps.setDate(5, inscripcion.getFechaInscripcion());
            pps.setTime(6, inscripcion.getHoraLlegada());
            pps.setTime(7, inscripcion.getHoraSalida());
            pps.executeUpdate();
            pps.close();
            cn.close();
            JOptionPane.showMessageDialog(null, "Datos enviados satsisfactoriamente");
        } catch (SQLException ex) {
            Logger.getLogger(RepresentanteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en los datos");
        }
    }
    
    public void updateInscripcion(Inscripcion inscripcion){
        try {
            Connection cn = con.connectToPostgres();
            String sql= "UPDATE inscripcion_4 SET hora_desde = ?, hora_hasta = ? "+
                    "WHERE ano = ? and consecutivo = ? and rif_guarderia = ?";
            PreparedStatement pps = cn.prepareCall(sql);
            pps.setTime(1, inscripcion.getHoraLlegada());
            pps.setTime(2, inscripcion.getHoraSalida());
            pps.setInt(3, inscripcion.getAno());
            pps.setInt(4, inscripcion.getConsecutivo());
            pps.setString(5, inscripcion.getRifGuarderia());
            pps.executeUpdate();
            pps.close();
            cn.close();
            JOptionPane.showMessageDialog(null, "Datos enviados satsisfactoriamente");
        } catch (SQLException ex) {
            Logger.getLogger(RepresentanteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en los datos");
        }
    }
    
    public void deleteInscripcion(Inscripcion inscripcion){
        try {
            Connection cn = con.connectToPostgres();
            PreparedStatement pps = cn.prepareCall("DELETE FROM inscripcion_4 WHERE ano = ? and consecutivo = ?and rif_guarderia = ?;");
            pps.setInt(1, inscripcion.getAno());
            pps.setInt(2, inscripcion.getConsecutivo());
            pps.setString(3, inscripcion.getRifGuarderia());
            pps.executeUpdate();
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se puede borrar");
            Logger.getLogger(RepresentanteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Inscripcion getInsNino(Nino kid){
        String sql = "Select * FROM inscripcion_4 WHERE letra_nino = '"
                + kid.getLetra()+"' AND ci_representante = '"+kid.getCiRepresentante()+"';";
        Inscripcion ins = new Inscripcion();
        try {
            ResultSet rs = con.selectAll(sql);
            while(rs.next()){
                ins.setConsecutivo(rs.getInt("consecutivo"));
                ins.setAno(rs.getInt("ano"));
                ins.setFechaInscripcion(rs.getDate("fecha_inscripcion"));
                ins.setHoraLlegada(rs.getTime("hora_desde"));
                ins.setHoraSalida(rs.getTime("hora_hasta"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsistenciaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ins;
    }
}
