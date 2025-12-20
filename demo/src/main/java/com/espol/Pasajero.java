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

        if (reservable.bloquearTemporalmente()) {
            Reserva reserva = new Reserva(idReserva, this, notificador);
            reserva.agregarReservable(reservable);
            return reserva;
        }

        return null;
    }
}
