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
 * @author gabrielbaron
 */
public class MedicamentoDAOImpl implements MedicamentoDAO{
    DBConnection con = new DBConnection();
    public ArrayList<Medicamento> getMedicamentos(){
        Connection connection = con.connectToPostgres();
        ArrayList <Medicamento> medicamentos = new ArrayList();
        Medicamento medicamento;
        String sql = "SELECT Descripcion " + 
                "FROM Medicamento_4;";
         try {
            Statement st;
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                medicamento = new Medicamento();
                medicamento.setDescripcion(rs.getString(1));
                medicamentos.add(medicamento);
            }
            rs.close();
            st.close();
            connection.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return medicamentos;
        
    }
}
