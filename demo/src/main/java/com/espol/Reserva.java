package com.espol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.espol.Decorator.IComponenteReserva;
import com.espol.estados.EstadoReserva;
import com.espol.estados.ReservaPendiente;

/**
 * Reserva base (Componente) para Decorator.
 * También aplica State para controlar el ciclo de vida de la reserva.
 */
public class Reserva implements IComponenteReserva {

    private int idReserva;
    private Date fechaReserva;
    private EstadoReserva estado;

    // Para el Decorator
    private double costoBase;
    private String detalle;

    // Contenido de la reserva
    private final List<IReservable> reservables;
    private final List<ServicioAdicional> servicios;

    // Infra
    private Pago pago;
    private Notificador notificador;
    private Pasajero pasajero;

    public Reserva(int idReserva, Pasajero pasajero, Notificador notificador, double costoBase, String detalle) {
        this.idReserva = idReserva;
        this.pasajero = pasajero;
        this.notificador = notificador;
        this.fechaReserva = new Date();
        this.estado = new ReservaPendiente();

        this.costoBase = costoBase;
        this.detalle = detalle;

        this.reservables = new ArrayList<>();
        this.servicios = new ArrayList<>();
    }

    public boolean procesarPago(String metodoPago) {
        calcularTotal();

        Pago nuevoPago = new Pago(idReserva, total, metodoPago);
        this.pago = nuevoPago;

        return nuevoPago.procesarPago();
    }

    // ========= Acciones delegadas al State =========

    public void agregarServicio(ServicioAdicional servicio) {
        estado.agregarServicio(this, servicio);
    }

    public void agregarReservable(IReservable reservable) {
        estado.agregarReservable(this, reservable);
    }

    public void confirmarPago(String metodoPago) {
        estado.confirmarPago(this, metodoPago);
    }

    public void cancelarReserva() {
        estado.cancelar(this);
    }

    // ========= Lógica de negocio interna =========
    public void notificarATodos(String mensaje) {
        if (notificador != null) {
            notificador.notificar(mensaje);
        }
        if (pasajero != null) {
            pasajero.notificar(mensaje);
        }
    }

    // ========= Getters/Setters usados por los States =========

    public int getIdReserva() {
        return idReserva;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        System.out.println(
            "Reserva " + idReserva + " cambió de estado: " + this.estado.getNombre() + " -> " + estado.getNombre()
        );
        this.estado = estado;
    }

    public String getEstadoNombre() {
        return estado.getNombre();
    }
    
    public void agregarServicioInterno(ServicioAdicional servicio) {
        this.servicios.add(servicio);
    }
    
    public void agregarReservableInterno(IReservable reservable) {
        this.reservables.add(reservable);
    }

    public void liberarTodosLosReservables() {
        for (IReservable r : reservables) {
            r.liberar();
        }
    }
    
    public void confirmarTodosLosReservables() {
        for (IReservable r : reservables) {
            r.confirmarReserva();
        }
    }

    public int cantidadReservables() {
        return reservables.size();
    }



    public float getTotal() {
        double subtotal = costoBase;
        for (ServicioAdicional sa : servicios) {
            subtotal += sa.getCosto();
        }
        if (reservables.size() > 1) {
        subtotal += 120.0 * (reservables.size() - 1);
        }
        return (float) subtotal;
    }


    public Pago getPago() {
        return pago;
    }

    public Notificador getNotificador() {
        return notificador;
    }

    // ========= Decorator =========

    @Override
    public double calcularCosto() {
        return this.costoBase;
    }

    @Override
    public String obtenerDescripcion() {
        return "Reserva #" + idReserva + ": " + detalle;
    }
}