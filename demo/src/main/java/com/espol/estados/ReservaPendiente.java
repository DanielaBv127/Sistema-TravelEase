package com.espol.estados;
import com.espol.Pago;
import com.espol.Reserva;
import com.espol.ServicioAdicional;
import com.espol.Vehiculo;

public class ReservaPendiente implements EstadoReserva{
    @Override
    public String getNombre() {
        return "PENDIENTE";
    }
    
    @Override
    public void confirmarPago(Reserva reserva, String metodoPago) {
        System.out.println("Procesando pago para reserva " + reserva.getIdReserva() + "...");
        
        reserva.calcularTotal();
        
        Pago pago = new Pago(reserva.getIdReserva(), reserva.getTotal(), metodoPago);
        
        if (pago.procesarPago()) {
            reserva.setEstado(new ReservaConfirmada());
            
            reserva.getVuelo().confirmarReserva();
            if (reserva.getVehiculo() != null) {
                reserva.getVehiculo().confirmarReserva();
            }
            
            // Notificar
            String mensaje = "Tu reserva " + reserva.getIdReserva() + " ha sido CONFIRMADA. Total pagado: $" + reserva.getTotal();
            reserva.notificarATodos(mensaje);
            
        } else {
            System.out.println("Error: El pago falló. La reserva sigue PENDIENTE.");
        }
    }
    
    @Override
    public void cancelar(Reserva reserva) {
        System.out.println("Cancelando reserva pendiente " + reserva.getIdReserva() + "...");
        
        reserva.setEstado(new ReservaCancelada());
        
        reserva.getVuelo().setEstado(new VueloDisponible());
        if (reserva.getVehiculo() != null) {
            reserva.getVehiculo().setEstado(new VehiculoDisponible());
        }
        
        String mensaje = "Reserva " + reserva.getIdReserva() + " ha sido CANCELADA (sin penalización).";
        reserva.notificarATodos(mensaje);
    }
    
    @Override
    public void agregarServicio(Reserva reserva, ServicioAdicional servicio) {
        reserva.getServicios().add(servicio);
        System.out.println("Servicio '" + servicio.getDescripcion() + "' agregado a reserva pendiente.");
    }
    
    @Override
    public void agregarVehiculo(Reserva reserva, Vehiculo vehiculo) {
        if (vehiculo.bloquearTemporalmente()) {
            reserva.setVehiculo(vehiculo);
            System.out.println("Vehículo " + vehiculo.getIdVehiculo() + " agregado y bloqueado.");
        } else {
            System.out.println("Error: El vehículo no está disponible.");
        }
    }
}