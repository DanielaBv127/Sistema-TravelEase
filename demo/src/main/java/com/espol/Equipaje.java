package com.espol;

public class Equipaje extends DecoradorReserva {
    private double costoMaleta = 35.50;

    public Equipaje(IComponenteReserva reservaEnvuelta) {
        super(reservaEnvuelta);
    }

    @Override
    public double calcularCosto() {
        return super.calcularCosto() + costoMaleta;
    }

    @Override
    public String obtenerDescripcion() {
        return super.obtenerDescripcion() + " + Maleta Adicional";
    }
}
