package com.espol.estados;

import com.espol.Vehiculo;

public class VehiculoReservado implements EstadoVehiculo {
    @Override
    public String getNombre() {
        return "RESERVADO";
    }
    
    @Override
    public boolean verificarDisponibilidad() {
        return false;
    }
    
    @Override
    public boolean bloquearTemporalmente(Vehiculo vehiculo) {
        System.out.println("Error: El vehículo " + vehiculo.getIdVehiculo() + " ya está reservado.");
        return false;
    }
    
    @Override
    public void confirmarReserva(Vehiculo vehiculo) {
        System.out.println("Vehículo " + vehiculo.getIdVehiculo() + " (" + vehiculo.getTipo() + ") reserva confirmada exitosamente.");
    }
    
    @Override
    public void enviarMantenimiento(Vehiculo vehiculo) {
        System.out.println("Error: No se puede enviar a mantenimiento un vehículo reservado.");
    }
}
