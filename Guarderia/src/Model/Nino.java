package Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class Nino {
    char letra;
    String ciRepresentante;
    String nombre;
    String apellido;
    Date fechaNacimiento;
    char sexo;
    ArrayList<Juego> juegos;

    public Nino(char letra, String ciRepresentante, String nombre, String apellido, Date fechaNacimiento, char sexo, ArrayList<Juego> juegos) {
        this.letra = letra;
        this.ciRepresentante = ciRepresentante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.juegos = juegos;
    }

    public Nino() {
        
    }

    public ArrayList<Juego> getJuegos() {
        return juegos;
    }

    public void setJuegos(ArrayList<Juego> juegos) {
        this.juegos = juegos;
    }
    
    

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public String getCiRepresentante() {
        return ciRepresentante;
    }

    public void setCiRepresentante(String ciRepresentante) {
        this.ciRepresentante = ciRepresentante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    
    public int getEdad() {
        Calendar today = Calendar.getInstance();
        Calendar fechaNac = Calendar.getInstance();
        fechaNac.setTime(this.fechaNacimiento);

        int diff_year = today.get(Calendar.YEAR) -  fechaNac.get(Calendar.YEAR);
        int diff_month = today.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
        int diff_day = today.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);

        if (diff_month < 0 || (diff_month == 0 && diff_day < 0)) {
            diff_day--;
        }
        return diff_year; 
    }
}
