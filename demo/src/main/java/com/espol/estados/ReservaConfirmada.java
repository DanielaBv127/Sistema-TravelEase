package com.espol.estados;
import com.espol.Reserva;
import com.espol.ServicioAdicional;
import com.espol.Vehiculo;

public class ReservaConfirmada implements EstadoReserva {
    @Override
    public String getNombre() {
        return "CONFIRMADA";
    }
    
    @Override
    public void confirmarPago(Reserva reserva, String metodoPago) {
        System.out.println("Error: La reserva " + reserva.getIdReserva()+" ya está CONFIRMADA. No se puede pagar nuevamente.");
    }
    
    @Override
    public void cancelar(Reserva reserva) {
        System.out.println("Cancelando reserva confirmada " + reserva.getIdReserva() + "...");
        
        reserva.setEstado(new ReservaCancelada());
        
        reserva.getVuelo().setEstado(new VueloDisponible());
        if (reserva.getVehiculo() != null) {
            reserva.getVehiculo().setEstado(new VehiculoDisponible());
        }
        
        String mensaje = "Reserva " + reserva.getIdReserva() + " ha sido CANCELADA. Iniciando proceso de reembolso...";
        reserva.notificarATodos(mensaje);
        System.out.println("Aplicando política de cancelación y calculando reembolso...");
    }

    @Override
    public void agregarServicio(Reserva reserva, ServicioAdicional servicio) {
        System.out.println("Error: No se pueden agregar servicios a una reserva CONFIRMADA.");
        System.out.println("Debe contactar con soporte para modificaciones.");
    }
    
    @Override
    public void agregarVehiculo(Reserva reserva, Vehiculo vehiculo) {
        System.out.println("Error: No se puede agregar vehículo a una reserva CONFIRMADA.");
        System.out.println("Debe cancelar y crear una nueva reserva.");
    }   
}