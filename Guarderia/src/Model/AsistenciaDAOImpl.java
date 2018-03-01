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
    
    public String getRif(Nino kid){
        String sql = "Select rif_guarderia FROM inscripcion_4 WHERE letra_nino = '"
                + kid.getLetra()+"' AND ci_representante = '"+kid.getCiRepresentante()+"';";
        String rif = new String();
        try {
            ResultSet rs = con.selectAll(sql);
            while (rs.next()){
                rif = rs.getString(1);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AsistenciaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rif;
    }
    
    public void ponerMulta(Asistencia assist){
        String sql = "Update asistencia_4 set monto_multa = "
        + assist.getCostoMulta()+" WHERE fecha = '"
        + String.valueOf(assist.getFecha())+"' AND consecutivo_ins = "
        +assist.getConsecutivo_inscripcion()+"";
        try {
            con.insertDatos(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AsistenciaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Asistencia> getMultas(Nino kid){
        ArrayList<Asistencia> assists = new ArrayList();
        String sql = "Select fecha, monto_multa, num_transferencia, consecutivo_ins from asistencia_4 WHERE "+
                "letra_nino = '"+kid.getLetra()+"' AND ci_representante = '"+
                  kid.getCiRepresentante()+"' AND monto_multa IS NOT NULL "
                + "order by fecha;";
        try {
            ResultSet rs = con.selectAll(sql);
            while(rs.next()){
                Asistencia assist = new Asistencia();
                assist.setFecha(rs.getDate(1));
                assist.setCostoMulta(rs.getInt(2));
                assist.setNumTransferencia(rs.getLong(3));
                assist.setConsecutivo_inscripcion(rs.getInt(4));
                assists.add(assist);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AsistenciaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assists;
    }
    
    public void pagarMulta(Asistencia assist) throws SQLException{
        String sql = "UPDATE asistencia_4 set num_transferencia = "
                + assist.getNumTransferencia()+" WHERE fecha = '"
                + String.valueOf(assist.getFecha())+"' and consecutivo_ins = "
                + assist.getConsecutivo_inscripcion()+";";
        System.out.println(String.valueOf(assist.getFecha()));
        con.insertDatos(sql);
    }
    
    public ArrayList<Asistencia> getEstadoCuenta(String ci){
        ArrayList<Asistencia> assists = new ArrayList();
        String sql = "Select fecha, 'multa' as concepto, monto_multa as monto, "
                + "num_transferencia  as pagado, letra_nino, 0 as mes FROM asistencia_4  WHERE "
                + "ci_representante = '"+ci+"' AND monto_multa is not null "
                + "union "
                + "select fecha, 'mensualidad' as concepto, monto, 1 as pagado, letra_nino, mes "
                + "from pago_mensual_4 WHERE ci_representante = '"+ci+"' AND"
                + " forma_pago is not null "
                + "union all "
                + "select CURRENT_DATE as fecha,'mensualidad' as concepto, monto,"
                + " 0 as pagado, letra_nino, mes from pago_mensual_4 WHERE ci_representante = '"
                + ci+"' AND forma_pago is null;";
        try {
            ResultSet rs = con.selectAll(sql);
            while(rs.next()){
                Asistencia assist = new Asistencia();
                assist.setFecha(rs.getDate("fecha"));
                assist.setCostoMulta(rs.getInt("monto"));
                assist.setComio(rs.getString("concepto"));
                assist.setNumTransferencia(rs.getInt("pagado"));
                assist.setLetra(rs.getString("letra_nino").charAt(0));
                assist.setConsecutivo_inscripcion(rs.getInt("mes"));
                assists.add(assist);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AsistenciaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assists;
    }
}
