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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ignacio
 */
public class ActividadNinoDAOImpl {
    
    DBConnection con;
    
    public ActividadNinoDAOImpl() {
        con = new DBConnection();
    }
    
    public ArrayList<HorarioInscripcion> getHorario(String ci, char letra) {
        Connection connection = con.connectToPostgres();
        ArrayList<HorarioInscripcion> horario = new ArrayList();
        String sql = "SELECT g.horario_entrada, g.horario_salida, hag.hora_inicio, hag.hora_fin, a.nombre, ai.fecha_actividad " + 
                "FROM guarderia_4 g, act_inscripcion_4 ai, horario_act_guarderia_4 hag, actividad_4 a " +
                "WHERE ai.ci_representante = '" + ci + "' and ai.letra_nino = '" + letra + "' and " + 
                "ai.rif_guarderia = g.rif and ai.cod_actividad = a.codigo and ai.fecha_actividad = hag.fecha " + 
                "and hag.hora_inicio = ai.hora_inicio_act ORDER BY hag.hora_inicio;";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                HorarioInscripcion horas = new HorarioInscripcion();
                horas.setHoraEntradaGuarderia(rs.getTime(1));
                horas.setHoraSalidaGuarderia(rs.getTime(2));
                horas.setHoraInicioAct(rs.getTime(3));
                horas.setHoraFinAct(rs.getTime(4));
                horas.setNombreAct(rs.getString(5));
                horas.setFechaActividad(rs.getDate(6));
                horario.add(horas);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error");
            System.out.println(e);
        }
        return horario;
    }
    
    public ArrayList<Actividad> loadAllowedActividades(String ci, char letra) {
        Connection connection = con.connectToPostgres();
        ArrayList<Actividad> actividades = new ArrayList();
        String sql = "SELECT A.codigo, A.NOMBRE, A.EDADMINIMA, A.TRANSPORTE, HAG.FECHA, HAG.HORA_INICIO, HAG.HORA_FIN, " + 
                "I.ANO, I.CONSECUTIVO, i.rif_guarderia FROM ACTIVIDAD_4 A, Act_Guarderia_4 AG, INSCRIPCION_4 I, Horario_Act_Guarderia_4 HAG, " + 
                "NINO_4 N WHERE I.CI_REPRESENTANTE = '"+ ci +"' AND I.LETRA_NINO = '"+ letra +"' AND I.CONSECUTIVO = (SELECT MAX(CONSECUTIVO) " + 
                "FROM INSCRIPCION_4 WHERE CI_REPRESENTANTE = '"+ ci +"' AND LETRA_NINO = '"+ letra +"') AND I.RIF_GUARDERIA = AG.RIF_GUARDERIA " + 
                "AND AG.COD_ACTIVIDAD = A.CODIGO AND HAG.RIF_GUARDERIA = AG.RIF_GUARDERIA AND HAG.COD_ACTIVIDAD = AG.COD_ACTIVIDAD and " + 
                "N.CI_REPRESENTANTE = '"+ ci +"' AND N.LETRA = '"+ letra +"' AND (current_date - N.FECHA_NACIMIENTO) >= A.EDADMINIMA*365 and " + 
                "extract(dow from hag.fecha) not in (select EXTRACT(dow from hag.fecha) from Horario_Act_Guarderia_4 hag, act_inscripcion_4 ai " +  
                "where ai.letra_nino = '"+ letra +"' and ai.ci_representante = '"+ ci +"' and ai.cod_actividad = hag.cod_actividad) AND HAG.HORA_INICIO " + 
                "NOT IN (select hag.hora_inicio from horario_act_guarderia_4 hag, act_inscripcion_4 ai where ai.letra_nino = '"+ letra +"' and " + 
                "ai.ci_representante = '"+ ci +"' and ai.cod_actividad = hag.cod_actividad);";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                Actividad actividad = new Actividad();
                actividad.setCodigo(rs.getInt(1));
                actividad.setNombre(rs.getString(2));
                actividad.setEdadMinima(rs.getInt(3));
                actividad.setTransporte(rs.getInt(4));
                actividad.setFecha(rs.getDate(5));
                actividad.setHoraInicio(rs.getTime(6));
                actividad.setHoraFin(rs.getTime(7));
                actividad.setAnoInsc(rs.getInt(8));
                actividad.setConsInsc(rs.getInt(9));
                actividad.setRifGuarderia(rs.getString(10));
                actividad.setCiRepresentante(ci);
                actividad.setLetraNino(letra);
                actividades.add(actividad);
                
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error");
            System.out.println(e);
        }
        return actividades;
    }
    
    public ArrayList<Actividad> loadActividadesInscriptas(String ci, char letra) {
        Connection connection = con.connectToPostgres();
        ArrayList<Actividad> actividades = new ArrayList();
        String sql = "SELECT A.NOMBRE, ai.ano_inscripcion, ai.consecutivo_inscripcion, ai.rif_guarderia, ai.ci_representante, " + 
                "ai.letra_nino, ai.cod_actividad, ai.fecha_actividad, ai.hora_inicio_act FROM ACTIVIDAD_4 A, act_inscripcion_4 ai " + 
                "WHERE ai.CI_REPRESENTANTE = '"+ ci +"' AND ai.LETRA_NINO = '"+ letra +"' and ai.cod_actividad = a.codigo;";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                Actividad actividad = new Actividad();
                actividad.setNombre(rs.getString(1));
                actividad.setAnoInsc(rs.getInt(2));
                actividad.setConsInsc(rs.getInt(3));
                actividad.setRifGuarderia(rs.getString(4));
                actividad.setCiRepresentante(rs.getString(5));
                actividad.setLetraNino(rs.getString(6).charAt(0));
                actividad.setCodigo(rs.getInt(7));
                actividad.setFecha(rs.getDate(8));
                actividad.setHoraInicio(rs.getTime(9));
                actividades.add(actividad);   
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error");
            System.out.println(e);
        }
        return actividades;
    }
    
    public void insertActividad(Actividad actividad) {
        try {
            Connection cn = con.connectToPostgres();
            PreparedStatement pps = cn.prepareCall("INSERT INTO act_inscripcion_4 VALUES " +
                    "(?,?,?,?,?,?,?,?)");
            pps.setInt(1, actividad.getAnoInsc());
            pps.setInt(2, actividad.getConsInsc());
            pps.setString(3, actividad.getRifGuarderia());
            pps.setString(4, actividad.getCiRepresentante());
            pps.setString(5, String.valueOf(actividad.getLetraNino()));
            pps.setInt(6, actividad.getCodigo());
            pps.setDate(7, actividad.getFecha());
            pps.setTime(8, actividad.getHoraInicio());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos cargados satisfactoriamente");
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en los datos");
            Logger.getLogger(ActividadNinoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteActividad(Actividad actividad) {
        try {
            Connection cn = con.connectToPostgres();
            String sql = "DELETE FROM act_inscripcion_4 WHERE ano_inscripcion = ? and consecutivo_inscripcion = ? " + 
                    "and rif_guarderia = ? and ci_representante = ? and letra_nino = ? and cod_actividad = ? " + 
                    "and fecha_actividad = ? and hora_inicio_act = ?";
            PreparedStatement pps = cn.prepareCall(sql);
            pps.setInt(1, actividad.getAnoInsc());
            pps.setInt(2, actividad.getConsInsc());
            pps.setString(3, actividad.getRifGuarderia());
            pps.setString(4, actividad.getCiRepresentante());
            pps.setString(5, String.valueOf(actividad.getLetraNino()));
            pps.setInt(6, actividad.getCodigo());
            pps.setDate(7, actividad.getFecha());
            pps.setTime(8, actividad.getHoraInicio());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos Eliminados");
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en los datos");
            Logger.getLogger(ActividadNinoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
