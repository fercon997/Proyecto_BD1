package Model;

public class Lugar {
    int codigo;
    String nombre;
    String tipo;
    int codigo_superior;
    private String casa;
    private String calle;
    private String municipio;
    private String ciudad;
    private String estado;

    public Lugar() {
        
    }

    public Lugar(int codigo, String nombre, String tipo, int codigo_superior) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.codigo_superior = codigo_superior;
    }

    public Lugar(int codigo, String nombre, String tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCodigo_superior() {
        return codigo_superior;
    }

    public void setCodigo_superior(int codigo_superior) {
        this.codigo_superior = codigo_superior;
    }
    
    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;

    }
    
    
}
