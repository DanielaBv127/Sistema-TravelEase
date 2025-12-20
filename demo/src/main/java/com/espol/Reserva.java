package com.espol;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.espol.estados.EstadoReserva;
import com.espol.estados.ReservaConfirmada;
import com.espol.estados.ReservaPendiente;
public class Reserva {
    private int idReserva;
    private Date fechaReserva;
    private EstadoReserva estado; // CAMBIO: Ahora es interfaz, no enum
    private float total;
    
    // Relaciones
    private Pasajero pasajero;
    private Vuelo vuelo;
    private Vehiculo vehiculo;
    private List<ServicioAdicional> servicios;
    private Pago pago;
    private Notificador notificador;

    public Reserva(int idReserva, Pasajero pasajero, Vuelo vuelo, Notificador notificador) {
        this.idReserva = idReserva;
        this.pasajero = pasajero;
        this.vuelo = vuelo;
        this.notificador = notificador;
        this.fechaReserva = new Date();
        this.estado = new ReservaPendiente(); // CAMBIO: Instancia del estado inicial
        this.servicios = new ArrayList<>();
        this.total = 0;
    }

    public void agregarServicio(ServicioAdicional servicio) {
        estado.agregarServicio(this, servicio);
    }
    
    public void agregarVehiculo(Vehiculo vehiculo) {
        estado.agregarVehiculo(this, vehiculo);
    }
    
    public void confirmarPago(String metodoPago) {
        estado.confirmarPago(this, metodoPago);
    }
    
    public void cancelarReserva() {
        estado.cancelar(this);
    }
    public void calcularTotal() {
        float subtotal = 0;
        subtotal += 350;
    
        if (vehiculo != null) subtotal += 120;
        for (ServicioAdicional sa : servicios) {
            subtotal += sa.getCosto();
        }
        
        this.total = subtotal;
    }
    
    public void notificarATodos(String mensaje) {
        notificador.notificar(mensaje);
        pasajero.notificar(mensaje);
    }
    
    public int getIdReserva() {
        return idReserva;
    }
    
    public EstadoReserva getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoReserva estado) {
        System.out.println("Reserva " + idReserva + " cambió de estado: " + this.estado.getNombre() + " -> " + estado.getNombre());
        this.estado = estado;
    }
    
    public String getEstadoNombre() {
        return estado.getNombre();
    }
    
    public float getTotal() {
        return total;
    }
    
    public Vuelo getVuelo() {
        return vuelo;
    }
    
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    public List<ServicioAdicional> getServicios() {
        return servicios;
    }
    
    public Pasajero getPasajero() {
        return pasajero;
    }
    
    public Notificador getNotificador() {
        return notificador;
    }
}

