package com.espol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.espol.Decorator.IComponenteReserva;
import com.espol.estados.EstadoReserva;
import com.espol.estados.ReservaPendiente;

public class Reserva implements IComponenteReserva{
    private int idReserva;
    private Date fechaReserva;
    private EstadoReserva estado;
    private double costoBase;
    private float total;
    private String detalle;
    
    // Relaciones
    private Pasajero pasajero;
    private Vuelo vuelo;
    private Vehiculo vehiculo;
    private List <ServicioAdicional> servicios = new ArrayList<>();
    private Pago pago;
    private Notificador notificador;

    public Reserva(int idReserva, Pasajero pasajero, Vuelo vuelo, Notificador notificador, double costoBase, String detalle) {

        this.idReserva = idReserva;
        this.fechaReserva = new Date();
        this.estado = new ReservaPendiente();
        this.costoBase = costoBase;
        this.detalle = detalle;

    }

    public void agregarServicio(ServicioAdicional servicio) {
        estado.agregarServicio(this, servicio);
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

    
    public void confirmarPago(String metodoPago) {
        estado.confirmarPago(this, metodoPago);
    }
    
    public void cancelarReserva() {
        estado.cancelar(this);
    }
    
    
    public void notificarATodos(String mensaje) {
        notificador.notificar(mensaje);
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
    
    
    public Vuelo getVuelo() {
        return vuelo;
    }
    
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    
    public Pasajero getPasajero() {
        return pasajero;
    }
    
    public Notificador getNotificador() {
        return notificador;}


    @Override
    public double calcularCosto() {
        return this.costoBase;
    }

    @Override
    public String obtenerDescripcion() {
        return "Reserva #" + idReserva + ": " + detalle;
    }

    public List<ServicioAdicional> getServicios(){
        return servicios;
    }

    public float getTotal(){
        return total;
    }
}