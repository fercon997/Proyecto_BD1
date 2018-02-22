/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author fercon997
 */
public class RepresentanteDAOImpl implements RepresentanteDAO {
    ArrayList<Representante> representantes;
    DBConnection con;

    public RepresentanteDAOImpl() {
        con = new DBConnection();
    }
   
    @Override
    public void insertRepresentante(Representante parent){
        try {
            Connection cn = con.connectToPostgres();
            PreparedStatement pps = cn.prepareCall("INSERT INTO representante_4 VALUES " +
                    "(?,?,?,?,?,?,?,?,?,?,?)");
            pps.setString(1, parent.getCi());
            pps.setString(2, parent.getNombre());
            pps.setString(3, parent.getApellido());
            pps.setLong(4, parent.getCelular());
            pps.setLong(5, parent.getTlf_casa());
            pps.setLong(6, parent.getTlf_oficina());
            pps.setString(7, parent.getEmail());
            pps.setString(8, parent.getProfesion());
            pps.setString(9, Character.toString(parent.getEdo_civil()));
            pps.setInt(10, parent.getPrincipal());
            pps.setInt(11, parent.getCod_direccion());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos cargados satisfactoriamente");
            pps.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(LugarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Representante> loadRepresentantes(String rif){
        Connection cn = con.connectToPostgres();
        
        ArrayList<Representante> parents = new ArrayList();
        String sql= "SELECT distinct R.ci, R.nombre, R.apellido, R.principal FROM representante_4 R, "+
                    "nino_4 N, inscripcion_4 I WHERE R.ci = N.ci_representante " +
                    "AND N.ci_representante = I.ci_representante AND I.rif_guarderia = '"+rif+"';";
        try {    
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Representante parent = new Representante();
                parent.setNombre(rs.getString("nombre"));
                parent.setApellido(rs.getString("apellido"));
                parent.setCi(rs.getString("ci"));
                parent.setPrincipal(rs.getInt("principal"));
                parents.add(parent);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepresentanteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parents;
        
    }
}
