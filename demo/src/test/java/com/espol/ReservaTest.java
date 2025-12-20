package com.espol;

import com.espol.Strategy.EstrategiaNotificacion;
import com.espol.estados.ReservaPendiente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {

    static class EstrategiaFake implements EstrategiaNotificacion {
        @Override
        public void enviar(String mensaje) {}
    }

    @Test
    void reservaDebeIniciarEnEstadoPendiente() {
        Notificador notificador = new Notificador(new EstrategiaFake());
        Pasajero pasajero = new Pasajero(1, "Juan", "a@a.com", "123");

        Reserva reserva = new Reserva(
                1,
                pasajero,
                notificador,
                100,
                "test"
        );

        assertTrue(reserva.getEstado() instanceof ReservaPendiente);
    }
}
