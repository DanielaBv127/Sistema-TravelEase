package com.espol;

import com.espol.Strategy.EstrategiaNotificacion;

public class Notificador {

    private EstrategiaNotificacion estrategia;

    public Notificador(EstrategiaNotificacion estrategia) {
        this.estrategia = estrategia;
    }

    public void notificar(String mensaje) {
        estrategia.enviar(mensaje);
    }
}