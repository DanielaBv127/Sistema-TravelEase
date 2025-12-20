package com.espol.estados;
import com.espol.Vehiculo;

public class VehiculoDisponible implements EstadoVehiculo {
    
    @Override
    public String getNombre() {
        return "DISPONIBLE";
    }
    
    @Override
    public boolean verificarDisponibilidad() {
        return true;
    }
    
    @Override
    public boolean bloquearTemporalmente(Vehiculo vehiculo) {
        vehiculo.setEstado(new VehiculoReservado());
        System.out.println("Vehículo " + vehiculo.getIdVehiculo() + " (" + vehiculo.getTipo() + ") bloqueado temporalmente.");
        return true;
    }
    
    @Override
    public void confirmarReserva(Vehiculo vehiculo) {
        System.out.println("Advertencia: Se intentó confirmar un vehículo que no estaba reservado.");
    }
    
    @Override
    public void enviarMantenimiento(Vehiculo vehiculo) {
        vehiculo.setEstado(new VehiculoMantenimiento());
        System.out.println("Vehículo " + vehiculo.getIdVehiculo() + " enviado a mantenimiento.");
    }
}
