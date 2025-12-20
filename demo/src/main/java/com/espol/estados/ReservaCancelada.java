package com.espol.estados;

import com.espol.IReservable;
import com.espol.Reserva;
import com.espol.ServicioAdicional;

public class ReservaCancelada implements EstadoReserva {

    @Override
    public String getNombre() {
        return "CANCELADA";
    }

    @Override
    public void confirmarPago(Reserva reserva, String metodoPago) {
        System.out.println("Error: No se puede pagar una reserva CANCELADA. Debe crear una nueva reserva.");
    }

    @Override
    public void cancelar(Reserva reserva) {
        System.out.println("La reserva " + reserva.getIdReserva() + " ya estaba CANCELADA.");
    }

    @Override
    public void agregarServicio(Reserva reserva, ServicioAdicional servicio) {
        System.out.println("Error: No se pueden agregar servicios a una reserva CANCELADA.");
    }

    @Override
    public void agregarReservable(Reserva reserva, IReservable reservable) {
        System.out.println("Error: No se puede agregar un servicio reservable a una reserva CANCELADA.");
    }
}