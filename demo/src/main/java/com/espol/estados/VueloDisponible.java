package com.espol.estados;
import com.espol.Vuelo;

public class VueloDisponible implements EstadoVuelo {

    @Override
    public String getNombre() {
        return "DISPONIBLE";
    }

    @Override
    public boolean verificarDisponibilidad() {
        return true;
    }

    @Override
    public boolean bloquearTemporalmente(Vuelo vuelo) {
        vuelo.setEstado(new VueloReservado());
        System.out.println("Vuelo " + vuelo.getIdVuelo() + " bloqueado temporalmente.");
        return true;
    }

    @Override
    public void confirmarReserva(Vuelo vuelo) {
        System.out.println("Advertencia: El vuelo no estaba reservado.");
    }

    @Override
    public void cancelar(Vuelo vuelo) {
        System.out.println("El vuelo ya está disponible.");
    }
}
