
package Controller;

import Model.Actividad;
import Model.ActividadNinoDAOImpl;
import Model.GuarderiaDAOImpl;
import Model.Horario;
import Model.HorarioInscripcion;
import Model.ActividadNinoDAOImpl;
import Model.Nino;
import Model.NinoDAOImpl;
import View.InitialView;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ActividadNinoController {
    
    InitialView initialView;
    ActividadNinoDAOImpl modeloAN = new ActividadNinoDAOImpl();
    ArrayList<Nino> ninos;
    ArrayList<String> rifs;
    ArrayList<Actividad> actividades = new ArrayList();
    ArrayList<String> diaSemana;
    
    public ActividadNinoController(InitialView initialView) {
        this.initialView = initialView;
        GuarderiaDAOImpl modeloGuarderia = new GuarderiaDAOImpl();
        rifs = modeloGuarderia.getRifs();
        diaSemana = new ArrayList(Arrays.asList("Doingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado")); 
    }
    
    public void tabbedPaneTouched() {
        if (initialView.jTabbedPane1.getSelectedIndex() == 7) {
            llenarActividades(initialView.tablaActividades);
            llenarActNinos(initialView.tablaActividadNino, "nino");
            initialView.actividadExistente.setEnabled(false);
            initialView.agregarActividadBtn.setEnabled(false);
            initialView.salirActividadBtn.setEnabled(false);
            initialView.eliminarActividadExistente.setEnabled(false);
            if (actividades.size() > 0) {
                actividades.clear();
            }
        }
    }
    
    public void llenarActividades(JTable tabla) {
        DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);
        Object[] columna = new Object[9];
        ArrayList<Actividad> actividades = new ArrayList();
            int numGuard = initialView.jComboActNino.getSelectedIndex();
            System.out.println("Gat "+ numGuard);
            try{
                if (numGuard == 0) {
                    actividades = modeloAN.loadAllActividades(null);
                } else {
                    actividades = modeloAN.loadAllActividades(rifs.get(numGuard -1));
                }
                for(int i = 0; i< actividades.size(); i++){
                    System.out.println(i);
                    columna[0] = actividades.get(i).getRifGuarderia();
                    columna[1] = actividades.get(i).getNombre();
                    columna[2] = diaSemana.get(actividades.get(i).getDia());
                    columna[3] = actividades.get(i).getHoraInicio();
                    columna[4] = actividades.get(i).getHoraFin();
                    columna[5] = actividades.get(i).getCuposDisponible();
                    columna[6] = actividades.get(i).getCupoMax();
                    if (actividades.get(i).getTransporte() == 1) {
                        columna[7] = "Si";
                    } else {
                        columna[7] = "No";
                    }
                    columna[8] = actividades.get(i).getNombrePersonal();
                    modeloTabla.addRow(columna);
                }
            } catch(Exception e){
                System.out.println(e);
            }
    }
    
    public void llenarActNinos(JTable tabla, String tipo) {
        if (tipo == "nino") {
            DefaultTableModel modeloTabla = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            modeloTabla.addColumn("CI Representante");
            modeloTabla.addColumn("Nombre");
            tabla.setModel(modeloTabla);
            NinoDAOImpl modeloNino = new NinoDAOImpl();

            int numGuard = initialView.jComboActNino.getSelectedIndex();

            Object[] columna = new Object[2];
            try{
                if (numGuard == 0) {
                    ninos = modeloNino.loadNino(null);
                } else {
                    ninos = modeloNino.loadNino(rifs.get(numGuard -1 ));
                }
                for(int i = 0; i< ninos.size(); i++){
                    columna[0] = ninos.get(i).getCiRepresentante();
                    columna[1] = ninos.get(i).getApellido() + ", " + ninos.get(i).getNombre();
                    modeloTabla.addRow(columna);
                }
            } catch(Exception e){
                System.out.println(e);
            }
        } else if (tipo == "actividad") {
            Nino nino = ninos.get(initialView.tablaActividadNino.getSelectedRow());
            System.out.println(ninos.get(initialView.tablaActividadNino.getSelectedRow()).getNombre());
            DefaultTableModel modeloTabla = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            modeloTabla.addColumn("Actividad");
            modeloTabla.addColumn("Hora inicio");
            modeloTabla.addColumn("Hora fin");
            tabla.setModel(modeloTabla);
            ActividadNinoDAOImpl modeloActividad = new ActividadNinoDAOImpl();

            int numGuard = initialView.jComboActNino.getSelectedIndex();
            System.out.println(numGuard);
            Object[] columna = new Object[3];
            try{
                if (modeloActividad.cantidadActividades(nino.getCiRepresentante(), nino.getLetra()) == 0) {
                    actividades = modeloActividad.loadAllActividadesNino(nino.getCiRepresentante(), nino.getLetra());
                } else {
                    actividades = modeloActividad.loadAllowedActividades(nino.getCiRepresentante(), nino.getLetra());
                }
                for(int i = 0; i< actividades.size(); i++){
                    columna[0] = actividades.get(i).getNombre();
                    columna[1] = actividades.get(i).getHoraInicio();
                    columna[2] = actividades.get(i).getHoraFin();
                    modeloTabla.addRow(columna);
                }
            } catch(Exception e){
                System.out.println(e);
            }
        } else {
            Nino nino = ninos.get(initialView.tablaActividadNino.getSelectedRow());
            System.out.println(ninos.get(initialView.tablaActividadNino.getSelectedRow()).getNombre());
            DefaultTableModel modeloTabla = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            modeloTabla.addColumn("Actividad");
            tabla.setModel(modeloTabla);
            ActividadNinoDAOImpl modeloActividad = new ActividadNinoDAOImpl();

            int numGuard = initialView.jComboActNino.getSelectedIndex();
            System.out.println(numGuard);
            Object[] columna = new Object[1];
            try{
                actividades = modeloActividad.loadActividadesInscriptas(nino.getCiRepresentante(), nino.getLetra());
                for(int i = 0; i< actividades.size(); i++){
                    columna[0] = actividades.get(i).getNombre();
                    modeloTabla.addRow(columna);
                }
            } catch(Exception e){
                System.out.println(e);
            }
        }
    }
    
    public void insertarActividad() {
        Actividad actividad = actividades.get(initialView.tablaActividadNino.getSelectedRow());
        modeloAN.insertActividad(actividad);
        tabbedPaneTouched();
    }
    
    public void deleteActividad() {
        Actividad actividad = actividades.get(initialView.tablaActividadNino.getSelectedRow());
        modeloAN.deleteActividad(actividad);
        tabbedPaneTouched();
    }
    
    public void llenarHorario(JTable tabla) {
        DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);
        Object[] columna = new Object[6];
        try{
            ArrayList<Horario> horario = leerHorario();
            for(int i = 0; i < horario.size(); i++){
                columna[0] = horario.get(i).getHora();
                columna[1] = horario.get(i).getLunes();
                columna[2] = horario.get(i).getMartes();
                columna[3] = horario.get(i).getMiercoles();
                columna[4] = horario.get(i).getJueves();
                columna[5] = horario.get(i).getViernes();
                modeloTabla.addRow(columna);
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }
    
    public ArrayList<Horario> leerHorario() {
        ArrayList<HorarioInscripcion> horario = new ArrayList();
        ArrayList<Horario> horarioActividades = new ArrayList();
        ArrayList<String> actividades = new ArrayList();
        String ci = ninos.get(initialView.tablaActividadNino.getSelectedRow()).getCiRepresentante();
        char letra = ninos.get(initialView.tablaActividadNino.getSelectedRow()).getLetra();
        horario = modeloAN.getHorario(ci, letra);
        Time horarioEntrada = stringToHora("00:00");
        Time horarioSalida = stringToHora("00:00");
        int dia = 0;
        if (horario.size() > 0 && initialView.tablaActividadNino.getColumnName(0) == "CI Representante") {
            horarioEntrada = horario.get(0).getHoraEntradaGuarderia();
            horarioSalida = horario.get(0).getHoraSalidaGuarderia();
        }
        Time inicioAct = horarioEntrada;
        Time finAct = sumarHoras(inicioAct);
        while (!inicioAct.equals(horarioSalida)) {
            Horario horarioAct = new Horario();
            horarioAct.setHora(convertirHora(inicioAct));
            for (int i = 0; i < horario.size(); i++) {
                dia = horario.get(i).getFechaActividad();
                if (horario.get(i).getTransporte() == 1) {
                    if (!esta(horario.get(i).getNombreAct(), actividades)) {
                        horario.get(i).setHoraInicioAct(restarHoras(horario.get(i).getHoraInicioAct()));
                        System.out.println(horario.get(i).getHoraInicioAct());
                        horario.get(i).setHoraFinAct(sumarHoras(horario.get(i).getHoraFinAct()));
                        System.out.println(horario.get(i).getHoraFinAct());
                        actividades.add(horario.get(i).getNombreAct());
                    }
                }
                if (inicioAct.after(horario.get(i).getHoraInicioAct()) && inicioAct.before(horario.get(i).getHoraFinAct())) {
                    switch (dia) {
                        case 1: horarioAct.setLunes(horario.get(i).getNombreAct());
                            break;
                        case 2: horarioAct.setMartes(horario.get(i).getNombreAct());
                            break;
                        case 3: horarioAct.setMiercoles(horario.get(i).getNombreAct());
                            break;
                        case 4: horarioAct.setJueves(horario.get(i).getNombreAct());
                            break;
                        case 5: horarioAct.setViernes(horario.get(i).getNombreAct());
                            break;
                    }
                }
                if (finAct.after(horario.get(i).getHoraInicioAct()) && finAct.before(horario.get(i).getHoraFinAct())) {
                   switch (dia) {
                        case 1: horarioAct.setLunes(horario.get(i).getNombreAct());
                            break;
                        case 2: horarioAct.setMartes(horario.get(i).getNombreAct());
                            break;
                        case 3: horarioAct.setMiercoles(horario.get(i).getNombreAct());
                            break;
                        case 4: horarioAct.setJueves(horario.get(i).getNombreAct());
                            break;
                        case 5: horarioAct.setViernes(horario.get(i).getNombreAct());
                            break;
                    } 
                }
            }
            horarioActividades.add(horarioAct);
            inicioAct = finAct;
            finAct = sumarHoras(finAct);
        }
        return horarioActividades;
    }
    
    public boolean esta(String actividad, ArrayList<String> actividades) {
        for (int i = 0; i < actividades.size(); i++) {
            if (actividad == actividades.get(i))
                return true;
        }
        return false;
    }
    
    public int calcularDiferenciaHoras(Time hora1, Time hora2) {
        Timestamp horaEntrada = new Timestamp(hora1.getTime());
        Timestamp horaSalida =  new Timestamp(hora2.getTime());
        
        long milliseconds = horaSalida.getTime() - horaEntrada.getTime();
        int seconds = (int) milliseconds / 1000;
        return seconds / 3600;
    }
    
    public Time sumarHoras(Time hora) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hora);
        calendar.add(Calendar.MINUTE, 30);
        return new Time(calendar.getTime().getTime());
    }
    
    public Time restarHoras(Time hora) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hora);
        calendar.add(Calendar.MINUTE, -30);
        return new Time(calendar.getTime().getTime());
    }
    
    public String convertirHora(Time hora) {
        DateFormat date1 = new SimpleDateFormat("HH:mm");
        return date1.format(hora);
    }
    
    public Time stringToHora(String hora) {
        DateFormat date1 = new SimpleDateFormat("HH:mm");
        Time time = new Time(0);
        try {
            return new Time(date1.parse(hora).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(ActividadNinoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return time;
    }
    
    public void masMenosContratada() {
        ArrayList<String> actividades;
        if (initialView.jComboGuarderias.getSelectedIndex() > 0) {
            actividades = modeloAN.actividadMasMenosContratada(rifs.get(initialView.jComboGuarderias.getSelectedIndex() - 1), "max");
            String contratadas = "";
            for (int i = 0; i < actividades.size(); i ++) {
                contratadas = contratadas + actividades.get(i) + "\n";
            }
            initialView.actMasContratadasText.setText(contratadas);
            actividades = modeloAN.actividadMasMenosContratada(rifs.get(initialView.jComboGuarderias.getSelectedIndex() - 1), "min");
            contratadas = "";
            for (int i = 0; i < actividades.size(); i ++) {
                contratadas = contratadas + actividades.get(i) + "\n";
            }
            initialView.actMenosContratadasText.setText(contratadas);
        } else {
            initialView.actMasContratadasText.setText("");
            initialView.actMenosContratadasText.setText("");
        }
    }
       
}
