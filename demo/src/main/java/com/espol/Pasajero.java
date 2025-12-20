package com.espol;
import java.util.ArrayList;
import java.util.List;


import com.espol.factory.ServicioFactory;
import com.espol.reserva.Reserva;
import com.espol.notificacion.Notificador;
import com.espol.reservable.IReservable;

public class Pasajero extends Usuario {

    public Pasajero(int id, String nombre, String correo, String contrasena) {
        super(id, nombre, correo, contrasena);
    }

    public Reserva realizarReserva(int idReserva,
                                   ServicioFactory factory,
                                   Notificador notificador) {

        IReservable reservable = factory.crearReservable();

        if (!reservable.verificarDisponibilidad()) {
            System.out.println("Servicio no disponible.");
            return null;
        }

    }  return reserva;

        // Lógica de bloqueo
        if (vuelo.bloquearTemporalmente()) {
            Reserva nuevaReserva = new Reserva(idReserva, this, vuelo, notificador, 100, "Wifi");
            this.reservas.add(nuevaReserva);
            System.out.println("Reserva " + idReserva + " en estado PENDIENTE.");
            return nuevaReserva;
        } else {
            System.out.println("Error: No se pudo bloquear el vuelo.");
            return null;

        }

        return null;
    }

