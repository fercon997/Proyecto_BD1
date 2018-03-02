package Model;

import java.sql.Time;

public class Guarderia {
    private String rif;
    private String comboText;
    private String nombre;
    private int costoMensualidad;
    private int costoMulta;
    private int constoTransporte;
    private int costoAgoDic;
    private int costoHoraExtra;
    private Time horaEntrada;
    private Time horaSalida;
    private int codDireccion;

    public int getCodDireccion() {
        return codDireccion;
    }

    public void setCodDireccion(int codDireccion) {
        this.codDireccion = codDireccion;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }
    
    public String getComboText() {
        return comboText;
    }
    
    public void setComboText(String comboText) {
        this.comboText = comboText;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCostoMensualidad() {
        return costoMensualidad;
    }

    public void setCostoMensualidad(int costoMensualidad) {
        this.costoMensualidad = costoMensualidad;
    }

    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }  

    public int getCostoMulta() {
        return costoMulta;
    }

    public void setCostoMulta(int costoMulta) {
        this.costoMulta = costoMulta;
    }

    public int getConstoTransporte() {
        return constoTransporte;
    }

    public void setConstoTransporte(int constoTransporte) {
        this.constoTransporte = constoTransporte;
    }

    public int getCostoAgoDic() {
        return costoAgoDic;
    }

    public void setCostoAgoDic(int costoAgoDic) {
        this.costoAgoDic = costoAgoDic;
    }

    public int getCostoHoraExtra() {
        return costoHoraExtra;
    }

    public void setCostoHoraExtra(int costoHoraExtra) {
        this.costoHoraExtra = costoHoraExtra;
    }
    
    
}
