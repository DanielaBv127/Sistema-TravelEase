package com.espol.estados;

import com.espol.Vehiculo;

public interface EstadoVehiculo {
    EstadoVehiculo MANTENIMIENTO = null;
    EstadoVehiculo RESERVADO = null;
    EstadoVehiculo DISPONIBLE = null;
    String getNombre();
    boolean verificarDisponibilidad();
    boolean bloquearTemporalmente(Vehiculo vehiculo);
    void confirmarReserva(Vehiculo vehiculo);
    void enviarMantenimiento(Vehiculo vehiculo);
}
