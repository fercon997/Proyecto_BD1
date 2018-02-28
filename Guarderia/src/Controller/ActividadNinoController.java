
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
    ArrayList<Actividad> actividades;
    
    public ActividadNinoController(InitialView initialView) {
        this.initialView = initialView;
        GuarderiaDAOImpl modeloGuarderia = new GuarderiaDAOImpl();
        rifs = modeloGuarderia.getRifs();
    }
    
    public void tabbedPaneTouched() {
        if (initialView.jTabbedPane1.getSelectedIndex() == 7) {
            llenarActNinos(initialView.tablaActividadNino, "nino");
            //llenarHorario(initialView.tablaHorarioNino);
            initialView.actividadExistente.setEnabled(false);
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
                actividades = modeloActividad.loadAllowedActividades(nino.getCiRepresentante(), nino.getLetra());
                for(int i = 0; i< actividades.size(); i++){
                    columna[0] = actividades.get(i).getNombre();
                    columna[1] = actividades.get(i).getHoraInicio();
                    columna[2] = actividades.get(i).getHoraInicio();
                    modeloTabla.addRow(columna);
                }
            } catch(Exception e){
                System.out.println(e);
            }
        }
    }
    
    public void llenarHorario(JTable tabla) {
        DefaultTableModel modeloTabla = (DefaultTableModel)tabla.getModel();
        for (int i = modeloTabla.getRowCount() -1; i >=0; i--)
          modeloTabla.removeRow(i);
        Object[] columna = new Object[6];
        try{
            ArrayList<Horario> horario = imprimir();
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

            }
        }
    
    public ArrayList<Horario> imprimir() {
        ArrayList<HorarioInscripcion> horario = new ArrayList();
        ArrayList<Horario> horarioActividades = new ArrayList();
        horario = modeloAN.getHorario("V8108418", 'A');
        if (horario.size() > 0) {
            //for (int i = 0; i < horario.size(); i ++) {
                horarioActividades = cargarLunes(horario);
            //}
        }
        return horarioActividades;
//        System.out.println("hey");
//        if (horario.size() > 0) {
//            int horasGuarderiaAbierta = calcularDiferenciaHoras(horario.get(0).getHoraEntradaGuarderia(), horario.get(0).getHoraSalidaGuarderia());
//        
//            System.out.println("Horas: " + horasGuarderiaAbierta);
//            System.out.println("Hora de Entrada: " + horario.get(0).getHoraEntradaGuarderia());
//            System.out.println("Hora de Salida: " + horario.get(0).getHoraSalidaGuarderia());
//            Time horaInicio = horario.get(0).getHoraEntradaGuarderia();
//            int k = 0;
//        while (!convertirHora(horaInicio).equals(convertirHora(horario.get(0).getHoraSalidaGuarderia()))) {
//            String hora = convertirHora(horaInicio);
//            System.out.println(k + ": " + horario.get(k).getNombreAct());
//            if (k < horario.size() && convertirHora(horario.get(k).getHoraInicioAct()).equals(hora)) {
//                Calendar c = Calendar.getInstance();
//                c.setTime(horario.get(k).getFechaActividad());
//                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
//                if (dayOfWeek == 2) {
//                    for (int j = 0; j < calcularDiferenciaHoras(horario.get(k).getHoraInicioAct(), horario.get(k).getHoraFinAct()) * 2; j ++) {
//                        Horario hor = new Horario();
//                        hor.setHora(convertirHora(horaInicio));
//                        hor.setLunes(horario.get(k).getNombreAct());
//                        horarioActividades.add(hor);
//                        horaInicio = sumarHoras(horaInicio);
//                    }
//                } if (dayOfWeek == 3) {
//                    for (int j = 0; j < calcularDiferenciaHoras(horario.get(k).getHoraInicioAct(), horario.get(k).getHoraFinAct())  * 2; j ++) {
//                        Horario hor = new Horario();
//                        hor.setHora(convertirHora(horaInicio));
//                        hor.setMartes(horario.get(k).getNombreAct());
//                        horarioActividades.add(hor);
//                        horaInicio = sumarHoras(horaInicio);
//                    }
//                } if (dayOfWeek == 4) {
//                    for (int j = 0; j < calcularDiferenciaHoras(horario.get(k).getHoraInicioAct(), horario.get(k).getHoraFinAct())  * 2; j ++) {
//                        Horario hor = new Horario();
//                        hor.setHora(convertirHora(horaInicio));
//                        hor.setMiercoles(horario.get(k).getNombreAct());
//                        horarioActividades.add(hor);
//                        horaInicio = sumarHoras(horaInicio);
//                    }
//                } if (dayOfWeek == 5) {
//                    for (int j = 0; j < calcularDiferenciaHoras(horario.get(k).getHoraInicioAct(), horario.get(k).getHoraFinAct())  * 2; j ++) {
//                        Horario hor = new Horario();
//                        hor.setHora(convertirHora(horaInicio));
//                        hor.setJueves(horario.get(k).getNombreAct());
//                        horarioActividades.add(hor);
//                        horaInicio = sumarHoras(horaInicio);
//                    }
//                } if (dayOfWeek == 6) {
//                    for (int j = 0; j < calcularDiferenciaHoras(horario.get(k).getHoraInicioAct(), horario.get(k).getHoraFinAct())  * 2; j ++) {
//                        Horario hor = new Horario();
//                        hor.setHora(convertirHora(horaInicio));
//                        hor.setViernes(horario.get(k).getNombreAct());
//                        horarioActividades.add(hor);
//                        horaInicio = sumarHoras(horaInicio);
//                    }
//                }
//                k++;
//            } else {
//                Horario hor = new Horario();
//                hor.setHora(convertirHora(horaInicio));
//                horarioActividades.add(hor);
//                horaInicio = sumarHoras(horaInicio);
//            }
//        }
//        }
//        System.out.println("Horario Actividades");
//        return horarioActividades;
    }
    
    public ArrayList<Horario> cargarLunes(ArrayList<HorarioInscripcion> horario) {
        ArrayList<Horario> horarioActividades = new ArrayList();
        if (horario.size() > 0) {
            int horasGuarderiaAbierta = calcularDiferenciaHoras(horario.get(0).getHoraEntradaGuarderia(), horario.get(0).getHoraSalidaGuarderia());
        
            System.out.println("Horas: " + horasGuarderiaAbierta);
            System.out.println("Hora de Entrada: " + horario.get(0).getHoraEntradaGuarderia());
            System.out.println("Hora de Salida: " + horario.get(0).getHoraSalidaGuarderia());
            Time horaInicio = horario.get(0).getHoraEntradaGuarderia();
        while (!convertirHora(horaInicio).equals(convertirHora(horario.get(0).getHoraSalidaGuarderia()))) {
            for (int k = 0; k < horario.size(); k++) {
            String hora = convertirHora(horaInicio);
            System.out.println(k + ": " + horario.get(k).getNombreAct());
            if (k < horario.size() && convertirHora(horario.get(k).getHoraInicioAct()).equals(hora)) {
                Calendar c = Calendar.getInstance();
                c.setTime(horario.get(k).getFechaActividad());
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                if (dayOfWeek == 2) {
                    for (int j = 0; j < calcularDiferenciaHoras(horario.get(k).getHoraInicioAct(), horario.get(k).getHoraFinAct()) * 2; j ++) {
                        Horario hor = new Horario();
                        hor.setHora(convertirHora(horaInicio));
                        hor.setLunes(horario.get(k).getNombreAct());
                        horarioActividades.add(hor);
                        horaInicio = sumarHoras(horaInicio);
                    }
                } else {
                    Horario hor = new Horario();
                    hor.setHora(convertirHora(horaInicio));
                    horarioActividades.add(hor);
                    horaInicio = sumarHoras(horaInicio);
                }
                k++;
            } else {
                Horario hor = new Horario();
                hor.setHora(convertirHora(horaInicio));
                horarioActividades.add(hor);
                horaInicio = sumarHoras(horaInicio);
            }
            }
        }
        }
        return horarioActividades;
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
    
    public String convertirHora(Time hora) {
        DateFormat date1 = new SimpleDateFormat("HH:mm");
        return date1.format(hora);
    }
       
}
