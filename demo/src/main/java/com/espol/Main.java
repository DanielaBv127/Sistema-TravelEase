package com.espol;

import com.espol.Strategy.EstrategiaNotificacion;
import com.espol.Strategy.NotificacionEmail;
import com.espol.factory.AereoFactory;
import com.espol.factory.ServicioFactory;
import com.espol.factory.VehiculoFactory;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== INICIO TRAVELEASE ===");

        // Infraestructura
        EstrategiaNotificacion estrategia = new NotificacionEmail();
        Notificador notificador = new Notificador(estrategia);


        // Usuario
        Pasajero pasajero = new Pasajero(
                1,
                "Juan Perez",
                "juan@mail.com",
                "1234"
        );

        pasajero.iniciarSesion("juan@mail.com", "1234");

        // ==========================
        // PRUEBA RESERVA AÉREA
        // ==========================
        System.out.println("\n--- Reserva Aérea ---");

        ServicioFactory aereoFactory = new AereoFactory();

        Reserva reservaVuelo = pasajero.realizarReserva(
                1001,
                aereoFactory,
                notificador
        );

        if (reservaVuelo != null) {
            reservaVuelo.confirmarPago("TARJETA");
        }

        // ==========================
        // PRUEBA RESERVA VEHÍCULO
        // ==========================
        System.out.println("\n--- Reserva Vehículo ---");

        ServicioFactory vehiculoFactory = new VehiculoFactory();

        Reserva reservaVehiculo = pasajero.realizarReserva(
                1002,
                vehiculoFactory,
                notificador
        );

        if (reservaVehiculo != null) {
            reservaVehiculo.confirmarPago("TRANSFERENCIA");
        }

        // ==========================
        // CANCELACIÓN
        // ==========================
        System.out.println("\n--- Cancelación ---");

        if (reservaVuelo != null) {
            reservaVuelo.cancelarReserva();
        }

        // ==========================
        // INCIDENCIA
        // ==========================
        System.out.println("\n--- Incidencia ---");

        Incidencia incidencia = pasajero.reportarIncidencia(
                500,
                "Retraso en el servicio"
        );

        AgenteSoporte agente = new AgenteSoporte(
                10,
                "Maria Soporte",
                "soporte@mail.com",
                "abcd"
        );

        agente.gestionarIncidencia(incidencia);

        pasajero.cerrarSesion();

        System.out.println("\n=== FIN TRAVELEASE ===");
    }
}
