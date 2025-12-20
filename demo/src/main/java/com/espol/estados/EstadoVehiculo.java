package com.espol.estados;

import com.espol.Vehiculo;

public interface EstadoVehiculo {
    String getNombre();
    boolean verificarDisponibilidad();
    boolean bloquearTemporalmente(Vehiculo vehiculo);
    void confirmarReserva(Vehiculo vehiculo);
    void enviarMantenimiento(Vehiculo vehiculo);
}
