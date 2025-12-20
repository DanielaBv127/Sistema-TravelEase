package com.espol.estados;

import com.espol.Vehiculo;

public class VehiculoMantenimiento implements EstadoVehiculo {
    
    @Override
    public String getNombre() {
        return "MANTENIMIENTO";
    }
    
    @Override
    public boolean verificarDisponibilidad() {
        return false;
    }
    
    @Override
    public boolean bloquearTemporalmente(Vehiculo vehiculo) {
        System.out.println("Error: El vehículo " + vehiculo.getIdVehiculo() + " está en mantenimiento.");
        return false;
    }
    
    @Override
    public void confirmarReserva(Vehiculo vehiculo) {
        System.out.println("Error: No se puede confirmar un vehículo en mantenimiento.");
    }
    
    @Override
    public void enviarMantenimiento(Vehiculo vehiculo) {
        System.out.println("El vehículo " + vehiculo.getIdVehiculo() + " ya está en mantenimiento.");
    }
    
    public void completarMantenimiento(Vehiculo vehiculo) {
        vehiculo.setEstado(new VehiculoDisponible());
        System.out.println("Vehículo " + vehiculo.getIdVehiculo() + " mantenimiento completado. Ahora disponible.");
    }
}
