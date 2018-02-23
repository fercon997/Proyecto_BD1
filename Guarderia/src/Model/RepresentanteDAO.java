/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author fercon997
 */
public interface RepresentanteDAO {
    public void insertRepresentante(Representante parent);
    public ArrayList<Representante> loadRepresentantes(String rif);
    public Representante showDatosRepresentante(String ci);
    public void deleteRepresentante(String ci);
    public void updateRepresentante(Representante parent);
}
