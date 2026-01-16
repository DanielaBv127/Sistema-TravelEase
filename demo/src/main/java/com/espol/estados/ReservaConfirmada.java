package com.espol.estados;

import com.espol.IReservable;
import com.espol.Reserva;
import com.espol.ServicioAdicional;

public class ReservaConfirmada implements EstadoReserva {

    @Override
    public String getNombre() {
        return "CONFIRMADA";
    }

    @Override
    public void confirmarPago(Reserva reserva, String metodoPago) {
        System.out.println(
            "Error: La reserva " + reserva.getIdReserva() +
            " ya está CONFIRMADA. No se puede pagar nuevamente."
        );
    }

    @Override
    public void cancelar(Reserva reserva) {
        System.out.println(
            "Cancelando reserva confirmada " + reserva.getIdReserva() + "..."
        );

        reserva.setEstado(new ReservaCancelada());

        // Liberar todos los servicios reservados (Abstract Factory + State)
        reserva.liberarTodosLosReservables();

        String mensaje ="Reserva " + reserva.getIdReserva()+" ha sido CANCELADA. Iniciando proceso de reembolso...";

        reserva.notificarATodos(mensaje);
        System.out.println("Aplicando política de cancelación y calculando reembolso...");
    }

    @Override
    public void agregarServicio(Reserva reserva, ServicioAdicional servicio) {
        System.out.println(MensajesUtil.errorReserva("agregar servicios", getNombre()));
    }

    @Override
    public void agregarReservable(Reserva reserva, IReservable reservable) {
        System.out.println(MensajesUtil.errorReserva("agregar servicios reservable", getNombre()));
        System.out.println("Debe cancelar y crear una nueva reserva.");
    }
}
