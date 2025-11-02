package com.espol;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reserva {
    private int idReserva;
    private Date fechaReserva;
    private EstadoReserva estado;
    private float total;

    // Relaciones
    private Pasajero pasajero;
    private Vuelo vuelo;
    private Vehiculo vehiculo; // Opcional
    private List<ServicioAdicional> servicios;
    private Pago pago;
    private Notificador notificador;

    public Reserva(int idReserva, Pasajero pasajero, Vuelo vuelo, Notificador notificador) {
        this.idReserva = idReserva;
        this.pasajero = pasajero;
        this.vuelo = vuelo;
        this.notificador = notificador;
        this.fechaReserva = new Date();
        this.estado = EstadoReserva.PENDIENTE;
        this.servicios = new ArrayList<>();
        this.total = 0; // Se calcula al confirmar
    }

    public void agregarServicio(ServicioAdicional servicio) {
        if (estado != EstadoReserva.PENDIENTE) {
            System.out.println("Error: No se pueden añadir servicios a una reserva " + estado);
            return;
        }
        this.servicios.add(servicio);
        System.out.println("Servicio '" + servicio.getDescripcion() + "' agregado.");
    }
    
    public void agregarVehiculo(Vehiculo vehiculo) {
        if (estado != EstadoReserva.PENDIENTE) {
            System.out.println("Error: No se puede añadir vehículo a una reserva " + estado);
            return;
        }
        if (vehiculo.bloquearTemporalmente()) {
            this.vehiculo = vehiculo;
            System.out.println("Vehículo " + vehiculo.getIdVehiculo() + " agregado y bloqueado.");
        } else {
            System.out.println("Error: El vehículo no está disponible.");
        }
    }

    private void calcularTotal() {
        float subtotal = 0;
        // Simulación de costos
        subtotal += 350; // Costo base vuelo
        if (vehiculo != null) subtotal += 120; // Costo base vehiculo
        
        for (ServicioAdicional sa : servicios) {
            subtotal += sa.getCosto();
        }
        this.total = subtotal;
    }

    public void confirmarPago(String metodoPago) {
        if (this.estado != EstadoReserva.PENDIENTE) {
            System.out.println("Error: La reserva no puede ser pagada (Estado: " + this.estado + ")");
            return;
        }
        
        calcularTotal();
        this.pago = new Pago(this.idReserva, this.total, metodoPago);
        
        if (pago.procesarPago()) {
            this.estado = EstadoReserva.CONFIRMADA;
            
            // Confirmación final en los subsistemas
            this.vuelo.confirmarReserva(); 

            String msg = "Tu reserva " + idReserva + " ha sido CONFIRMADA. Total pagado: $" + this.total;
            notificador.notificar(msg);
            pasajero.notificar(msg);
        } else {
            System.out.println("Error: El pago falló. La reserva sigue PENDIENTE.");
        }
    }

    public void cancelarReserva() {
        if (this.estado == EstadoReserva.CANCELADA) {
             System.out.println("La reserva " + idReserva + " ya estaba cancelada.");
            return;
        }

        this.estado = EstadoReserva.CANCELADA;
        
        // Liberar recursos
        this.vuelo.setEstado(EstadoVuelo.DISPONIBLE);
        if (this.vehiculo != null) {
            this.vehiculo.setEstado(EstadoVehiculo.DISPONIBLE);
        }
        
        String msg = "Reserva " + idReserva + " ha sido CANCELADA.";
        notificador.notificar(msg);
        pasajero.notificar(msg);
        
        // Lógica de reembolso
        System.out.println("Iniciando proceso de reembolso para la reserva " + idReserva + ".");
    }

    public int getIdReserva() {
        return idReserva;
    }

    public EstadoReserva getEstado() {
        return estado;
    }
 
}
