/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
/**
 *
 * @author gabrielbaron
 */
public interface Nino_MedicoDAO {
    public ArrayList<Nino_Medico> loadNinos();
    public ArrayList<String> getcodigos();
    public ArrayList<Pediatra> getpediatra(String codigo);
    public ArrayList<Alergia> getAlergia(String codigo);
    public ArrayList<Enfermedad> getEnfermedad(String codigo);
    public ArrayList<Tratamiento> getTratamiento(String codigo);
}
