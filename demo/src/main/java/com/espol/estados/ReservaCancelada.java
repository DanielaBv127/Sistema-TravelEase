package com.espol.estados;

import com.espol.Reserva;
import com.espol.ServicioAdicional;
import com.espol.Vehiculo;

public class ReservaCancelada implements EstadoReserva {
        @Override
    public String getNombre() {
        return "CANCELADA";
    }
    
    @Override
    public void confirmarPago(Reserva reserva, String metodoPago) {
        System.out.println("Error: No se puede pagar una reserva CANCELADA.");
        System.out.println("Debe crear una nueva reserva.");
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
    public void agregarVehiculo(Reserva reserva, Vehiculo vehiculo) {
        System.out.println("Error: No se puede agregar vehículo a una reserva CANCELADA.");
    }

}
