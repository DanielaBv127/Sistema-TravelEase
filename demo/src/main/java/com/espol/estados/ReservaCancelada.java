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
        System.out.println(MensajesUtil.errorReserva("confirmar pago", getNombre()));
        System.out.println("Debe crear una nueva reserva.");
    }

    @Override
    public void cancelar(Reserva reserva) {
        System.out.println("La reserva " + reserva.getIdReserva() + " ya estaba CANCELADA.");
    }

    @Override
    public void agregarServicio(Reserva reserva, ServicioAdicional servicio) {
        System.out.println(MensajesUtil.errorReserva("agregar servicios", getNombre()));
    }

    @Override
    public void agregarReservable(Reserva reserva, IReservable reservable) {
        System.out.println(MensajesUtil.errorReserva("agregar servicios reservable", getNombre()));
    }
}