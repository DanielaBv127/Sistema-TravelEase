package com.espol.Strategy;

public class NotificacionMensajeria implements EstrategiaNotificacion {

    @Override
    public void enviar(String mensaje) {
        System.out.println("[MENSAJERIA] Enviando mensaje: " + mensaje);
    }
}