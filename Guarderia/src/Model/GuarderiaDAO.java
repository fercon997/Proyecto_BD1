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
 * @author Ignacio
 */
public interface GuarderiaDAO {
    public ArrayList<String> getRifs();
    public ArrayList<Guarderia> loadGuarderias();
    public Guarderia getDatosGuarderia(String rif);
}
