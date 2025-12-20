package com.espol;

public interface IReservable {
    public boolean verificarDisponibilidad();
    public boolean bloquearTemporalmente();
    void confirmarReserva();
}
