package com.espol.estados;

import com.espol.Reserva;
import com.espol.ServicioAdicional;
import com.espol.Vehiculo;

public interface EstadoReserva {
    String getNombre();
    void confirmarPago(Reserva reserva, String metodoPago);
    void cancelar(Reserva reserva);
    void agregarServicio(Reserva reserva, ServicioAdicional servicio);
    void agregarVehiculo(Reserva reserva, Vehiculo vehiculo);
}
