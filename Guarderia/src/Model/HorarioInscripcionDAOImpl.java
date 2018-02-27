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
import java.util.ArrayList;

/**
 *
 * @author Ignacio
 */
public class HorarioInscripcionDAOImpl {
    
    DBConnection con;
    
    public HorarioInscripcionDAOImpl() {
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
}
