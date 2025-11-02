package com.espol;
import java.util.ArrayList;
import java.util.List;

public class Pasajero extends Usuario implements INotificable {
    
    private List<Reserva> reservas;
    private List<Incidencia> incidencias;

    public Pasajero(int id, String nombre, String correo, String contrasena) {
        super(id, nombre, correo, contrasena);
        this.reservas = new ArrayList<>();
        this.incidencias = new ArrayList<>();
    }

    public Reserva realizarReserva(int idReserva, Vuelo vuelo, Notificador notificador) {
        System.out.println("Pasajero " + nombre + " iniciando reserva para vuelo " + vuelo.getIdVuelo() + "...");
        
        // Validación de disponibilidad
        if (!vuelo.verificarDisponibilidad()) {
            System.out.println("Error: El vuelo no está disponible.");
            return null;
        }

        // Lógica de bloqueo
        if (vuelo.bloquearTemporalmente()) {
            Reserva nuevaReserva = new Reserva(idReserva, this, vuelo, notificador);
            this.reservas.add(nuevaReserva);
            System.out.println("Reserva " + idReserva + " en estado PENDIENTE.");
            return nuevaReserva;
        } else {
            System.out.println("Error: No se pudo bloquear el vuelo.");
            return null;
        }
    }

    public void gestionarCambios(Reserva reserva) {
        if (reserva == null || !this.reservas.contains(reserva)) {
            System.out.println("Error: La reserva no pertenece a este pasajero.");
            return;
        }
        System.out.println("Gestionando cambios para la reserva " + reserva.getIdReserva() + "...");
        // Lógica de cambios (ej. llamar a reserva.cancelarReserva() y crear una nueva)
    }

    public Incidencia reportarIncidencia(int idIncidencia, String descripcion) {
        System.out.println("Pasajero " + nombre + " reportando incidencia: " + descripcion);
        Incidencia nuevaIncidencia = new Incidencia(idIncidencia, descripcion, this);
        this.incidencias.add(nuevaIncidencia);
        nuevaIncidencia.registrarIncidencia();
        return nuevaIncidencia;
    }

    @Override
    public void notificar(String mensaje) {
        // Notificación específica para el pasajero
        System.out.println("AVISO URGENTE PARA PASAJERO (" + this.nombre + "): " + mensaje);
    }
}