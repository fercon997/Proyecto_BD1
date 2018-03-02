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
        String sql = "SELECT g.horario_entrada, g.horario_salida, hag.hora_inicio, hag.hora_fin, a.nombre, a.transporte, extract(dow from ai.fecha_actividad) dia " + 
                "FROM guarderia_4 g, act_inscripcion_4 ai, horario_act_guarderia_4 hag, actividad_4 a " +
                "WHERE ai.ci_representante = '" + ci + "' and ai.letra_nino = '" + letra + "' and " + 
                "ai.rif_guarderia = g.rif and ai.cod_actividad = a.codigo and ai.fecha_actividad = hag.fecha " + 
                "and hag.hora_inicio = ai.hora_inicio_act ORDER BY dia;";
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
                horas.setTransporte(rs.getInt(6));
                horas.setFechaActividad(rs.getInt(7));
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
    
    public ArrayList<Actividad> loadAllActividades(String rif) {
        System.out.println("Estoy aqui");
        Connection connection = con.connectToPostgres();
        ArrayList<Actividad> actividades = new ArrayList();
        String sql;
        if (rif == null) {
            sql = "Select hag.rif_guarderia, a.nombre, a.edadminima, a.transporte, hag.hora_inicio, hag.hora_fin, extract(dow from hag.fecha), " + 
                "ag.cupoMax, ag.cupoMax - (select count(ai.*) from act_inscripcion_4 ai where ai.cod_actividad = a.codigo) cuposDsiponibles, p.nombre " + 
                "from actividad_4 a, horario_act_guarderia_4 hag, act_guarderia_4 ag, personal_4 p where hag.cod_actividad = a.codigo and " + 
                " ag.cod_actividad = a.codigo and ag.rif_guarderia = hag.rif_guarderia and ag.CI_encargada = p.ci order by hag.rif_guarderia;";
        } else {
               sql = "Select hag.rif_guarderia, a.nombre, a.edadminima, a.transporte, hag.hora_inicio, hag.hora_fin, extract(dow from hag.fecha), " + 
                "ag.cupoMax, ag.cupoMax - (select count(ai.*) from act_inscripcion_4 ai where ai.rif_guarderia = '" + rif + "' and ai.cod_actividad = a.codigo) cuposDsiponible, " + 
                "p.nombre from actividad_4 a, horario_act_guarderia_4 hag, act_guarderia_4 ag, personal_4 p where hag.rif_guarderia = '" + rif + "' and hag.cod_actividad = a.codigo and " + 
                " ag.cod_actividad = a.codigo and ag.rif_guarderia = hag.rif_guarderia and ag.CI_encargada = p.ci;";
        }
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                Actividad actividad = new Actividad();
                actividad.setRifGuarderia(rs.getString(1));
                actividad.setNombre(rs.getString(2));
                actividad.setEdadMinima(rs.getInt(3));
                actividad.setTransporte(rs.getInt(4));
                actividad.setHoraInicio(rs.getTime(5));
                actividad.setHoraFin(rs.getTime(6));
                actividad.setDia(rs.getInt(7));
                actividad.setCupoMax(rs.getInt(8));
                actividad.setCuposDisponible(rs.getInt(9));
                actividad.setNombrePersonal(rs.getString(10));
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
    
    public int cantidadActividades(String ci, char letra) {
        Connection connection = con.connectToPostgres();
        ArrayList<Actividad> actividades = new ArrayList();
        String sql = "select count(ai.*) from act_inscripcion_4 ai, inscripcion_4 i where ai.letra_nino = '"+letra+
                "' and ai.ci_representante = '"+ci+"' and ai.consecutivo_inscripcion = (SELECT MAX(CONSECUTIVO) " + 
                "FROM INSCRIPCION_4 WHERE CI_REPRESENTANTE = '"+ci+"' AND LETRA_NINO = '"+letra+"');";
        int cantidad = 0;
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                cantidad = rs.getInt(1);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error");
            System.out.println(e);
        }
        return cantidad;
    }
    
    public ArrayList<Actividad> loadAllActividadesNino(String ci, char letra) {
        Connection connection = con.connectToPostgres();
        ArrayList<Actividad> actividades = new ArrayList();
        String sql = "select distinct A.codigo, A.NOMBRE, A.EDADMINIMA, A.TRANSPORTE, HAG.FECHA, HAG.HORA_INICIO, HAG.HORA_FIN, " + 
                "I.ANO, I.CONSECUTIVO, i.rif_guarderia from inscripcion_4 i, " + 
                "horario_act_guarderia_4 hag, actividad_4 a where i.ci_representante = '"+ ci + "' and i.letra_nino = '" + letra + "' "+ 
                "and i.rif_guarderia = hag.rif_guarderia "+ 
                "and a.codigo = hag.cod_actividad and i.consecutivo = (SELECT MAX(CONSECUTIVO)" + 
                "FROM INSCRIPCION_4 WHERE CI_REPRESENTANTE = '"+ci+"' AND LETRA_NINO = '"+letra+"');";
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
    
    public ArrayList<Actividad> loadAllowedActividades(String ci, char letra) {
        Connection connection = con.connectToPostgres();
        ArrayList<Actividad> actividades = new ArrayList();
        String sql = "SELECT distinct A.codigo, A.NOMBRE, A.EDADMINIMA, A.TRANSPORTE, HAG.FECHA, HAG.HORA_INICIO, HAG.HORA_FIN, I.ANO, I.CONSECUTIVO, i.rif_guarderia FROM " +
                "ACTIVIDAD_4 A, Act_Guarderia_4 AG, INSCRIPCION_4 I, Horario_Act_Guarderia_4 HAG, NINO_4 N,  (select ai.consecutivo_inscripcion consecutivo, " +
"hag.cod_actividad cod_actividad, hag.hora_inicio hora_inicio, hag.hora_fin hora_fin, ai.rif_guarderia rif_guarderia, a.transporte transporte from "+
"act_inscripcion_4 ai, horario_act_guarderia_4 hag, actividad_4 a where ai.letra_nino = '" + letra + "' and ai.ci_representante = '"+ ci +"' and "+
"ai.rif_guarderia = hag.rif_guarderia and ai.cod_actividad = hag.cod_actividad and ai.fecha_actividad = hag.fecha and "+
"ai.hora_inicio_act = hag.hora_inicio and a.codigo = hag.cod_actividad) hag2 WHERE I.CI_REPRESENTANTE = '"+ ci +"' AND "+
"I.LETRA_NINO = '" + letra + "' AND I.CONSECUTIVO = (SELECT MAX(CONSECUTIVO) FROM INSCRIPCION_4 WHERE CI_REPRESENTANTE = '"+ ci +"' AND "+
"LETRA_NINO = '" + letra + "') AND I.RIF_GUARDERIA = AG.RIF_GUARDERIA AND a.transporte = 0 and AG.COD_ACTIVIDAD = A.CODIGO AND "+
"HAG.RIF_GUARDERIA = AG.RIF_GUARDERIA AND HAG.COD_ACTIVIDAD = AG.COD_ACTIVIDAD and N.CI_REPRESENTANTE = '"+ ci +"' AND N.LETRA = '"+ letra +"' AND "+
"(current_date - N.FECHA_NACIMIENTO) >= A.EDADMINIMA*365 and extract(dow from hag.fecha) not in (select EXTRACT(dow from hag.fecha) "+
"from Horario_Act_Guarderia_4 hag, act_inscripcion_4 ai where ai.letra_nino = '"+ letra +"' and ai.ci_representante = '"+ ci +"' and "+
"ai.cod_actividad = hag.cod_actividad) AND hag2.rif_guarderia = hag.rif_guarderia and hag2.transporte = 0 and "+
"HAG.HORA_INICIO not between hag2.hora_inicio + interval '1 min' and hag2.hora_fin - interval '1 min' and hag.hora_fin not between hag2.hora_inicio + interval '1 min' "+
"and hag2.hora_fin - interval '1 min' and HAG2.HORA_INICIO not between hag.hora_inicio + interval '1 min' and hag.hora_fin - interval '1 min' and hag2.hora_fin "+
"not between hag.hora_inicio + interval '1 min' and hag.hora_fin - interval '1 min' and ag.cupoMax > (Select count(ai.*) from act_inscripcion_4 ai where ai.rif_guarderia = hag.rif_guarderia "+
"and ai.cod_actividad = hag.cod_actividad) union " +
"SELECT distinct A.codigo, A.NOMBRE, A.EDADMINIMA, A.TRANSPORTE, HAG.FECHA, HAG.HORA_INICIO, HAG.HORA_FIN, I.ANO, I.CONSECUTIVO, i.rif_guarderia FROM "+
"ACTIVIDAD_4 A, Act_Guarderia_4 AG, INSCRIPCION_4 I, Horario_Act_Guarderia_4 HAG, NINO_4 N,  (select ai.consecutivo_inscripcion consecutivo, "+
"hag.cod_actividad cod_actividad, hag.hora_inicio hora_inicio, hag.hora_fin hora_fin, ai.rif_guarderia rif_guarderia, a.transporte transporte "+
"from act_inscripcion_4 ai, horario_act_guarderia_4 hag, actividad_4 a where ai.letra_nino = '"+ letra +"' and ai.ci_representante = '"+ ci +"' and "+
"ai.rif_guarderia = hag.rif_guarderia and ai.cod_actividad = hag.cod_actividad and ai.fecha_actividad = hag.fecha "+
"and ai.hora_inicio_act = hag.hora_inicio and a.codigo = hag.cod_actividad) hag2 WHERE I.CI_REPRESENTANTE = '"+ ci +"' AND I.LETRA_NINO = '"+ letra +"' "+
"AND I.CONSECUTIVO = (SELECT MAX(CONSECUTIVO) FROM INSCRIPCION_4 WHERE CI_REPRESENTANTE = '"+ ci +"' AND LETRA_NINO = '"+ letra +"') "+
"AND I.RIF_GUARDERIA = AG.RIF_GUARDERIA AND a.transporte = 1 and AG.COD_ACTIVIDAD = A.CODIGO AND HAG.RIF_GUARDERIA = AG.RIF_GUARDERIA "+
"AND HAG.COD_ACTIVIDAD = AG.COD_ACTIVIDAD and N.CI_REPRESENTANTE = '"+ ci +"' AND N.LETRA = '"+ letra +"' AND "+
"(current_date - N.FECHA_NACIMIENTO) >= A.EDADMINIMA*365 and extract(dow from hag.fecha) not in (select EXTRACT(dow from hag.fecha) "+
"from Horario_Act_Guarderia_4 hag, act_inscripcion_4 ai where ai.letra_nino = '"+ letra +"' and ai.ci_representante = '"+ ci +"' "+
"and ai.cod_actividad = hag.cod_actividad) AND hag2.rif_guarderia = hag.rif_guarderia and hag2.transporte = 0 "+
"and HAG.HORA_INICIO - interval '30 min' not between hag2.hora_inicio and hag2.hora_fin "+
"and hag.hora_fin + interval '30 min' not between hag2.hora_inicio and hag2.hora_fin "+
"and HAG2.HORA_INICIO not between hag.hora_inicio - interval '30 min' and hag.hora_fin + interval '30 min' "+
"and hag2.hora_fin not between hag.hora_inicio - interval '30 min' and hag.hora_fin + interval '30 min' "+
"and ag.cupoMax > (Select count(ai.*) from act_inscripcion_4 ai where ai.rif_guarderia = hag.rif_guarderia "+
"and ai.cod_actividad = hag.cod_actividad) union " +
"SELECT distinct A.codigo, A.NOMBRE, A.EDADMINIMA, A.TRANSPORTE, HAG.FECHA, HAG.HORA_INICIO, HAG.HORA_FIN, I.ANO, I.CONSECUTIVO, i.rif_guarderia "+
"FROM ACTIVIDAD_4 A, Act_Guarderia_4 AG, INSCRIPCION_4 I, Horario_Act_Guarderia_4 HAG, NINO_4 N,  (select ai.consecutivo_inscripcion consecutivo,"+
"hag.cod_actividad cod_actividad, hag.hora_inicio hora_inicio, hag.hora_fin hora_fin, ai.rif_guarderia rif_guarderia, a.transporte transporte "+
"from act_inscripcion_4 ai, horario_act_guarderia_4 hag, actividad_4 a where ai.letra_nino = '"+ letra +"' and ai.ci_representante = '"+ ci +"' "+
"and ai.rif_guarderia = hag.rif_guarderia and ai.cod_actividad = hag.cod_actividad and ai.fecha_actividad = hag.fecha "+
"and ai.hora_inicio_act = hag.hora_inicio and a.codigo = hag.cod_actividad) hag2 WHERE I.CI_REPRESENTANTE = '"+ ci +"' AND I.LETRA_NINO = '"+ letra +"' "+
"AND I.CONSECUTIVO = (SELECT MAX(CONSECUTIVO) FROM INSCRIPCION_4 WHERE CI_REPRESENTANTE = '"+ ci +"' AND LETRA_NINO = '"+ letra +"') "+
"AND I.RIF_GUARDERIA = AG.RIF_GUARDERIA AND a.transporte = 0 and AG.COD_ACTIVIDAD = A.CODIGO AND HAG.RIF_GUARDERIA = AG.RIF_GUARDERIA "+
"AND HAG.COD_ACTIVIDAD = AG.COD_ACTIVIDAD and N.CI_REPRESENTANTE = '"+ ci +"' AND N.LETRA = '"+ letra +"' AND "+
"(current_date - N.FECHA_NACIMIENTO) >= A.EDADMINIMA*365 and extract(dow from hag.fecha) not in (select EXTRACT(dow from hag.fecha) "+
"from Horario_Act_Guarderia_4 hag, act_inscripcion_4 ai where ai.letra_nino = '"+ letra +"' and ai.ci_representante = '"+ ci +"' "+
"and ai.cod_actividad = hag.cod_actividad) AND hag2.rif_guarderia = hag.rif_guarderia and hag2.transporte = 1 "+
"and HAG.HORA_INICIO not between hag2.hora_inicio - interval '30 min' and hag2.hora_fin + interval '30 min' "+
"and hag.hora_fin not between hag2.hora_inicio - interval '30 min' and hag2.hora_fin + interval '30 min' "+
"and HAG2.HORA_INICIO - interval '30 min' not between hag.hora_inicio and hag.hora_fin  "+
"and hag2.hora_fin + interval '30 min' not between hag.hora_inicio and hag.hora_fin "+
"and ag.cupoMax > (Select count(ai.*) from act_inscripcion_4 ai where ai.rif_guarderia = hag.rif_guarderia and ai.cod_actividad = hag.cod_actividad) union " +
        "SELECT distinct A.codigo, A.NOMBRE, A.EDADMINIMA, A.TRANSPORTE, HAG.FECHA, HAG.HORA_INICIO, HAG.HORA_FIN, I.ANO, I.CONSECUTIVO, i.rif_guarderia "+
"FROM ACTIVIDAD_4 A, Act_Guarderia_4 AG, INSCRIPCION_4 I, Horario_Act_Guarderia_4 HAG, NINO_4 N,  (select ai.consecutivo_inscripcion consecutivo, "+
"hag.cod_actividad cod_actividad, hag.hora_inicio hora_inicio, hag.hora_fin hora_fin, ai.rif_guarderia rif_guarderia, a.transporte transporte "+
"from act_inscripcion_4 ai, horario_act_guarderia_4 hag, actividad_4 a where ai.letra_nino = '"+ letra +"' and ai.ci_representante = '"+ ci +"' "+
"and ai.rif_guarderia = hag.rif_guarderia and ai.cod_actividad = hag.cod_actividad and ai.fecha_actividad = hag.fecha "+
"and ai.hora_inicio_act = hag.hora_inicio and a.codigo = hag.cod_actividad) hag2 WHERE I.CI_REPRESENTANTE = '"+ ci +"' AND I.LETRA_NINO = '"+ letra +"' "+
"AND I.CONSECUTIVO = (SELECT MAX(CONSECUTIVO) FROM INSCRIPCION_4 WHERE CI_REPRESENTANTE = '"+ ci +"' AND LETRA_NINO = '"+ letra +"') "+
"AND I.RIF_GUARDERIA = AG.RIF_GUARDERIA AND a.transporte = 1 and AG.COD_ACTIVIDAD = A.CODIGO AND HAG.RIF_GUARDERIA = AG.RIF_GUARDERIA "+
"AND HAG.COD_ACTIVIDAD = AG.COD_ACTIVIDAD and N.CI_REPRESENTANTE = '"+ ci +"' AND N.LETRA = '"+ letra +"' AND "+
"(current_date - N.FECHA_NACIMIENTO) >= A.EDADMINIMA*365 and extract(dow from hag.fecha) not in (select EXTRACT(dow from hag.fecha) "+
"from Horario_Act_Guarderia_4 hag, act_inscripcion_4 ai where ai.letra_nino = '"+ letra +"' and ai.ci_representante = '"+ ci +"' "+
"and ai.cod_actividad = hag.cod_actividad) AND hag2.rif_guarderia = hag.rif_guarderia and hag2.transporte = 1 "+
"and HAG.HORA_INICIO - interval '30 min' not between hag2.hora_inicio - interval '30 min' and hag2.hora_fin + interval '30 min' "+
"and hag.hora_fin + interval '30 min' not between hag2.hora_inicio - interval '30 min' and hag2.hora_fin + interval '30 min' "+
"and HAG2.HORA_INICIO - interval '30 min' not between hag.hora_inicio - interval '30 min' and hag.hora_fin + interval '30 min' "+
"and hag2.hora_fin + interval '30 min' not between hag.hora_inicio - interval '30 min' and hag.hora_fin + interval '30 min' "+
"and ag.cupoMax > (Select count(ai.*) from act_inscripcion_4 ai where ai.rif_guarderia = hag.rif_guarderia "+
"and ai.cod_actividad = hag.cod_actividad);";
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
            PreparedStatement pps = cn.prepareCall("INSERT INTO act_inscripcion_4  VALUES " +
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
    
    
    public ArrayList<String> actividadMasMenosContratada(String rif, String tipo) {
        Connection connection = con.connectToPostgres();
        ArrayList<String> actividades = new ArrayList();
        String sql = "select a.nombre from (select count(ai.*) as cantidad, " + 
                "ai.cod_actividad as cod from act_inscripcion_4 ai where ai.rif_guarderia = '"+ rif +"' " + 
                "group by ai.cod_actividad) contratadas, actividad_4 a where contratadas.cod = a.codigo and " + 
                "contratadas.cantidad = (select "+ tipo +"(contratadas.cantidad) from (select count(ai.*) as cantidad, " + 
                "a.nombre nombre, a.codigo cod from act_inscripcion_4 ai, actividad_4 a where ai.rif_guarderia = '"+ rif +"' " + 
                "and ai.cod_actividad = a.codigo group by a.nombre, a.codigo) contratadas);";
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                actividades.add(rs.getString(1));
                
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
}
