package com.espol.Strategy;

public class NotificacionEmail implements EstrategiaNotificacion {

    @Override
    public void enviar(String mensaje) {
        System.out.println("[EMAIL] Enviando correo: " + mensaje);
    }
}
