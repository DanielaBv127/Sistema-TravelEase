package com.espol.Decorator;

public class Seguro extends DecoradorReserva {
    private double costoSeguro = 50.00;

    public Seguro(IComponenteReserva reservaEnvuelta) {
        super(reservaEnvuelta);
    }

    @Override
    public double calcularCosto() {
        return super.calcularCosto() + costoSeguro;
    }

    @Override
    public String obtenerDescripcion() {
        return super.obtenerDescripcion() + " + Seguro de Viaje";
    }
}
