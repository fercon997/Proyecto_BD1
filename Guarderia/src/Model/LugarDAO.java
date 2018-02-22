package Model;

import java.util.ArrayList;

public interface LugarDAO {
    public void insertLugar(Lugar lugar);
    public Lugar getDatosLugar(String codigo, String tipoCodigo);
    public ArrayList<Lugar> getCiudades();
    public ArrayList<Lugar> getEstados();
    public ArrayList<Lugar> getMunicipios();
}
