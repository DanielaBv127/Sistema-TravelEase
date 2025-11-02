package com.espol;

import java.util.ArrayList;
import java.util.List;

public class Notificador implements INotificable {
    
    private List<Notificacion> historialNotificaciones;

    public Notificador() {
        this.historialNotificaciones = new ArrayList<>();
    }

    public void enviarCorreo(String mensaje) {
        System.out.println("[SISTEMA-EMAIL] Enviando correo: " + mensaje);
        historialNotificaciones.add(new Notificacion(historialNotificaciones.size() + 1, mensaje, TipoNotificacion.EMAIL));
    }

    public void enviarMensaje(String mensaje) {
        System.out.println("[SISTEMA-SMS] Enviando mensaje: " + mensaje);
        historialNotificaciones.add(new Notificacion(historialNotificaciones.size() + 1, mensaje, TipoNotificacion.MENSAJERIA));
    }

    @Override
    public void notificar(String mensaje) {
        // Notificación genérica del sistema
        enviarCorreo(mensaje);
        enviarMensaje(mensaje);
    }
}

