/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.SQLException;

/**
 *
 * @author fercon997
 */
public interface JuegoDAO {
    public void insertarJuego(Juego game) throws SQLException;
}
