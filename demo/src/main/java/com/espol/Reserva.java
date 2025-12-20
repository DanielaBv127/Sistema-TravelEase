package com.espol;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.*;
import com.espol.reservable.IReservable;
import com.espol.notificacion.Notificador;
import com.espol.pago.Pago;

public class Reserva {

    private int idReserva;
    private Date fechaReserva;
    private EstadoReserva estado;
    private float total;

    private List<IReservable> reservables;
    private Pago pago;
    private Notificador notificador;

    public Reserva(int idReserva, Object pasajero, Notificador notificador) {
        this.idReserva = idReserva;
        this.fechaReserva = new Date();
        this.estado = EstadoReserva.PENDIENTE;
        this.reservables = new ArrayList<>();
        this.notificador = notificador;
    }

    public void agregarReservable(IReservable reservable) {
        if (estado != EstadoReserva.PENDIENTE) return;
        reservables.add(reservable);
    }

    private void calcularTotal() {
        total = reservables.size() * 200; // simulación
    }

    public void confirmarPago(String metodo) {
        calcularTotal();
        pago = new Pago(idReserva, total, metodo);

        if (pago.procesarPago()) {
            estado = EstadoReserva.CONFIRMADA;
            for (IReservable r : reservables) {
                r.confirmarReserva();
            }
            notificador.notificar("Reserva " + idReserva + " confirmada.");
        }
    }
}


