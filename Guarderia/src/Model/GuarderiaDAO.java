package Model;

import java.util.ArrayList;

public interface GuarderiaDAO {
    public ArrayList<String> getRifs();
    public ArrayList<Guarderia> loadGuarderias();
    public Guarderia getDatosGuarderia(String rif);
    public void saveGuarderia(Guarderia guarderia);
}
