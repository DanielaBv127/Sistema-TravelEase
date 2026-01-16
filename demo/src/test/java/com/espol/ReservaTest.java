package com.espol;

import com.espol.Strategy.EstrategiaNotificacion;
import com.espol.estados.ReservaPendiente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Sección B

class ReservaTest {

    static class EstrategiaFake implements EstrategiaNotificacion {
        @Override
        public void enviar(String mensaje) {}
    }

    @Test
    void reservaDebeIniciarCorrectamente() {
        Notificador notificador = new Notificador(new EstrategiaFake());
        Pasajero pasajero = new Pasajero(1, "Juan", "a@a.com", "123");

        Reserva reserva = new Reserva(
                1,
                pasajero,
                notificador,
                100,
                "test"
        );

        assertAll(
            () -> assertInstanceOf(ReservaPendiente.class, reserva.getEstado()),

            () -> assertNotNull(reserva.getNotificador()),

            () -> assertTrue(reserva.getReservables().isEmpty()),
            () -> assertTrue(reserva.getServicios().isEmpty()),

            () -> assertEquals(1, reserva.getIdReserva())
        );
    }
}
