package com.espol.Decorator;

public abstract class DecoradorReserva implements IComponenteReserva {
    // Referencia al objeto que estamos envolviendo (Agregación)
    protected IComponenteReserva reservaEnvuelta;

    public DecoradorReserva(IComponenteReserva reservaEnvuelta) {
        this.reservaEnvuelta = reservaEnvuelta;
    }

    @Override
    public double calcularCosto() {
        return reservaEnvuelta.calcularCosto();
    }

    @Override
    public String obtenerDescripcion() {
        return reservaEnvuelta.obtenerDescripcion();
    }
}
