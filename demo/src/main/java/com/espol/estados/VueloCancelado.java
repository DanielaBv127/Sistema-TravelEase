package com.espol.estados;
import com.espol.Vuelo;

public class VueloCancelado implements EstadoVuelo {

    @Override
    public String getNombre() {
        return "CANCELADO";
    }

    @Override
    public boolean verificarDisponibilidad() {
        return false;
    }

    @Override
    public boolean bloquearTemporalmente(Vuelo vuelo) {
        System.out.println("Error: El vuelo está cancelado.");
        return false;
    }

    @Override
    public void confirmarReserva(Vuelo vuelo) {
        System.out.println("Error: No se puede confirmar un vuelo cancelado.");
    }

    @Override
    public void cancelar(Vuelo vuelo) {
        System.out.println("El vuelo ya estaba cancelado.");
    }
}
