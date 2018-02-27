/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fercon997
 */
public class AsistenciaDAOImpl {
    DBConnection con;

    public AsistenciaDAOImpl() {
        con = new DBConnection();
    }
    
    public void insertAsistencia(Asistencia assist) throws SQLException{
        String insertar = "'"+String.valueOf(assist.getFecha())+"', "+String.valueOf(assist.getConsecutivo_inscripcion())+", '"
                +String.valueOf(assist.getAno_inscripcion())+"', '"+assist.getCi_representante()+"', '"+
                String.valueOf(assist.getLetra())+"', " +assist.getCi_representante_busco()+", "+
                assist.getCi_auth_busco()+", '"+String.valueOf(assist.getHora_entrada())+"', '"+
                String.valueOf(assist.getHora_salida())+"', '"+assist.getComio()+"'";
        String sql = "Insert into asistencia_4 values("+insertar+");";
        con.insertDatos(sql);
    }
    
    public ArrayList<Asistencia> getAllAsistencia(Nino kid){
        System.out.println(kid.getLetra());
        ArrayList<Asistencia> assists = new ArrayList();
        String sql = "Select * from asistencia_4 A WHERE "+
                "letra_nino = '"+kid.getLetra()+"' AND ci_representante = '"+
                  kid.getCiRepresentante()+"' order by fecha;";
        try {
            ResultSet rs = con.selectAll(sql);
            while(rs.next()){
                Asistencia assist = new Asistencia();
                assist.setAno_inscripcion(rs.getInt("ano_inscripcion"));
                assist.setCi_auth_busco(rs.getString("ci_auth_busco"));
                assist.setCi_representante(rs.getString("ci_representante"));
                assist.setCi_representante_busco(rs.getString("ci_padre_busco"));
                assist.setComio(rs.getString("comio"));
                assist.setConsecutivo_inscripcion(rs.getInt("consecutivo_ins"));
                assist.setFecha(rs.getDate("fecha"));
                assist.setHora_entrada(rs.getTime("hora_entrada"));
                assist.setHora_salida(rs.getTime("hora_salida"));
                assist.setLetra(rs.getString("letra_nino").charAt(0));
                assists.add(assist);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AsistenciaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assists;
    }
    
    public void deleteAsistencia(int consecutivo, Date fecha){
        String sql = "DELETE FROM asistencia_4 WHERE consecutivo_ins = "+consecutivo+
                " AND fecha = '"+String.valueOf(fecha)+"';";
        try {
            con.insertDatos(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AsistenciaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Autorizado> llenarBusco(Nino kid){
       ArrayList<Autorizado> auths = new ArrayList();
       String sql = "Select ci, nombre, apellido, celular, 'Representante' tipo FROM representante_4, parentesco_padre_4 "+
               "WHERE ci = ci_representante AND ci_principal = '"+kid.getCiRepresentante()+"' AND "+
               "letra_nino = '"+kid.getLetra()+"' "+
               "union "+
               "Select ci, nombre, apellido, celular, 'Autorizado' tipo FROM autorizado_4, autorizado_buscar_4 "+
               "WHERE ci = ci_autorizado AND ci_representante = '"+kid.getCiRepresentante()+"' AND "+
               "letra_nino = '"+kid.getLetra()+"' ;";
        try {
            ResultSet rs = con.selectAll(sql);
            while(rs.next()){
                Autorizado auth = new Autorizado();
                auth.setApellido(rs.getString("apellido"));
                auth.setCi(rs.getString("ci"));
                auth.setCelular(rs.getLong("celular"));
                auth.setNombre(rs.getString("nombre"));
                auth.setTipo(rs.getString("tipo"));
                auths.add(auth);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AsistenciaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auths;
       
    }
    
    public InscripcionA getConsIns(Nino kid){
        String sql = "Select consecutivo, ano FROM inscripcion_4 WHERE letra_nino = '"
                + kid.getLetra()+"' AND ci_representante = '"+kid.getCiRepresentante()+"';";
        InscripcionA ins = new InscripcionA();
        try {
            ResultSet rs = con.selectAll(sql);
            while(rs.next()){
                ins.setConsecutivo(rs.getInt(1));
                ins.setFecha(rs.getInt(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsistenciaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ins;
    }
    
}
