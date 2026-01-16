package com.espol.estados;

public class MensajesUtil {
    public static String errorIncidencia (String accion, String estado){
        return "Error: No se puede " + accion + " una incidencia en estado " + estado + ".";
    }
    public static String errorReserva(String accion, String estado){
        return "Error: No se puede " + accion + " una reserva en estado " + estado + ".";
    }

}
