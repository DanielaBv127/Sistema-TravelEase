package com.espol;

public interface IReservable {
    public boolean verificarDisponibilidad();
    public void bloquearTemporalmente();
}
