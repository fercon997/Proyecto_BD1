package Model;

import java.util.ArrayList;

public interface LugarDAO {
    public void insertLugar(Lugar lugar);
    public Lugar getDatosLugar(String query);
    public ArrayList<Lugar> getCiudades();
    public ArrayList<Lugar> getEstados();
    public ArrayList<Lugar> getMunicipios();
    public int getCodigoCalle();
    public int addDireccion(Lugar casa, Lugar calle);
}
