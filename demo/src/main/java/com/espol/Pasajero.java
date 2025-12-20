package com.espol;

import java.util.ArrayList;
import java.util.List;

import com.espol.factory.ServicioFactory;

public class Pasajero extends Usuario {

    private final List<Reserva> reservas;
    private final List<Incidencia> incidencias;

    public Pasajero(int id, String nombre, String correo, String contrasena) {
        super(id, nombre, correo, contrasena);
        this.reservas = new ArrayList<>();
        this.incidencias = new ArrayList<>();
    }

    /**
     * Usa Abstract Factory para crear un Proveedor + un Servicio Reservable coherente.
     */
    public Reserva realizarReserva(int idReserva, ServicioFactory factory, Notificador notificador) {
        if (factory == null) {
            System.out.println("Error: Factory inválida.");
            return null;
        }

        // Abstract Factory crea la familia
        IProveedor proveedor = factory.crearProveedor(1, "Proveedor genérico");
        IReservable reservable = factory.crearReservable(1, proveedor);

        System.out.println("Pasajero " + nombre + " iniciando reserva para servicio... ");
        
        if (!reservable.verificarDisponibilidad()) {
            System.out.println("Error: El servicio no está disponible.");
            return null;
        }

        Reserva nuevaReserva = new Reserva(
            idReserva,
            this,
            notificador,
            350,
            "Reserva generada con Abstract Factory"
        );

        nuevaReserva.agregarReservable(reservable);
        reservas.add(nuevaReserva);

        System.out.println("Reserva " + idReserva + " en estado PENDIENTE.");
        return nuevaReserva;
    }

    public Incidencia reportarIncidencia(int idIncidencia, String descripcion) {
        Incidencia nueva = new Incidencia(idIncidencia, descripcion, this);
        incidencias.add(nueva);
        nueva.registrarIncidencia();
        return nueva;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void notificar(String mensaje) {
        System.out.println("AVISO PARA PASAJERO (" + this.nombre + "): " + mensaje);
    }
}

