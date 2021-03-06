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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fercon997
 */
public class PagoDAOImpl {
    DBConnection con;

    public PagoDAOImpl() {
        con = new DBConnection();
    }
    
    
    public void insertPago(Pago pay){
        if (pay.concepto.equals("Pago de inscripcion"))
            pay.consecutivo = 1;
        String params = pay.getConsecutivo()+", "+pay.getCons_inscripcion()+", "
                +pay.getAno_inscripcion()+", '"+pay.getCi_representante()+"', '"
                + pay.getLetra()+"', "+pay.getMes()+", '"+pay.getConcepto()+"', "
                + pay.getMonto();
        String sql = "INSERT INTO pago_mensual_4 values( "+params+");";
        try {
            con.insertDatos(sql);
            System.out.println("Mensualidad Cargada");
        } catch (SQLException ex) {
            Logger.getLogger(PagoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public int getMesPago(int mes, int cons_ins){
        int mess = -1;
        String sql = "Select mes from pago_mensual_4 WHERE mes = "+mes+""
                + " AND cons_inscripcion = "+cons_ins+";";
        try {
            ResultSet rs = con.selectAll(sql);
            while(rs.next()){
               mess = rs.getInt("mes");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PagoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mess;
    }
    
    public int getConsAnterior(int cons_ins){
        int consecutivo = 0;
        String sql = "Select consecutivo from pago_mensual_4 WHERE cons_inscripcion = "
                +cons_ins+";";
        try {
            ResultSet rs = con.selectAll(sql);
            while(rs.next())
              consecutivo = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(PagoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consecutivo;
    }
    
    public ArrayList<Pago> getPagos(Nino kid){
        ArrayList<Pago> pays = new ArrayList();
        String sql = "SELECT * from pago_mensual_4 WHERE ci_representante = '"
                +kid.getCiRepresentante()+"' AND letra_nino= '"
                + kid.getLetra()+"';";
        try {
            ResultSet rs = con.selectAll(sql);
            while(rs.next()){
                Pago pay = new Pago();
                pay.setAno_inscripcion(rs.getInt("ano_inscripcion"));
                pay.setCi_representante(rs.getString("ci_representante"));
                pay.setConcepto(rs.getString("concepto"));
                pay.setCons_inscripcion(rs.getInt("cons_inscripcion"));
                pay.setConsecutivo(rs.getInt("consecutivo"));
                pay.setFecha(rs.getDate("fecha"));
                pay.setForma_pago(rs.getString("forma_pago"));
                pay.setLetra(rs.getString("letra_nino").charAt(0));
                pay.setMes(rs.getInt("mes"));
                pay.setMonto(rs.getFloat("monto"));
                pays.add(pay);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PagoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pays;
    }
    
    public void pagarMensualidad(Pago pay) throws SQLException{
        String sql  = "UPDATE pago_mensual_4 set forma_pago = '"
                + pay.getForma_pago()+"', fecha = '"+pay.getFecha()+"', monto = "
                + pay.getMonto()+" WHERE mes = "
                + pay.getMes()+" and cons_inscripcion = "
                + pay.getCons_inscripcion()+";";
        con.insertDatos(sql);
    }
    
    public float pagoActividades(String ci, char letra) {
        Connection connection = con.connectToPostgres();
        ArrayList<Actividad> actividades = new ArrayList();
        String sql = "select sum(ag.costo_actividad) from act_inscripcion_4 ai, act_guarderia_4 ag where ai.letra_nino = '"+letra+
                "' and ai.ci_representante = '"+ci+"' and ai.consecutivo_inscripcion = (SELECT MAX(CONSECUTIVO) " + 
                "FROM INSCRIPCION_4 WHERE CI_REPRESENTANTE = '"+ci+"' AND LETRA_NINO = '"+letra+"') and ai.cod_actividad = ag.cod_actividad " + 
                "and ag.rif_guarderia = ai.rif_guarderia;";
        int costo = 0;
        try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                costo = rs.getInt(1);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error");
            System.out.println(e);
        }
        return costo;
    }
    
}
