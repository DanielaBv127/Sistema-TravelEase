package com.espol;

/**
 * Contrato base de cualquier servicio que se pueda reservar (vuelo, vehículo, etc.).
 */
public interface IReservable {
    boolean verificarDisponibilidad();
    boolean bloquearTemporalmente();
    void confirmarReserva();

    /**
     * Libera el recurso (vuelve a estado DISPONIBLE) cuando la reserva se cancela.
     */
    void liberar();
}
