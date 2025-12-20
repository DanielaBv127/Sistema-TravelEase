package com.espol.estados;
import com.espol.Vuelo;

public class VueloReservado implements EstadoVuelo {

    @Override
    public String getNombre() {
        return "RESERVADO";
    }

    @Override
    public boolean verificarDisponibilidad() {
        return false;
    }

    @Override
    public boolean bloquearTemporalmente(Vuelo vuelo) {
        System.out.println("Error: El vuelo ya está reservado.");
        return false;
    }

    @Override
    public void confirmarReserva(Vuelo vuelo) {
        System.out.println("Reserva del vuelo " + vuelo.getIdVuelo() + " confirmada.");
    }

    @Override
    public void cancelar(Vuelo vuelo) {
        vuelo.setEstado(new VueloCancelado());
        System.out.println("Vuelo " + vuelo.getIdVuelo() + " cancelado.");
    }
}
