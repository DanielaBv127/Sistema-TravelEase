package com.espol.estados;

import com.espol.IReservable;
import com.espol.Reserva;
import com.espol.ServicioAdicional;

public interface EstadoReserva {
    String getNombre();
    void confirmarPago(Reserva reserva, String metodoPago);
    void cancelar(Reserva reserva);
    void agregarServicio(Reserva reserva, ServicioAdicional servicio);

    /**
     * Permite añadir cualquier servicio reservable (vuelo, vehículo, etc.).
     */
    void agregarReservable(Reserva reserva, IReservable reservable);
}

