package com.espol;

public class Wifi extends DecoradorReserva {
    private double costoWifi = 15.00;

    public Wifi(IComponenteReserva reservaEnvuelta) {
        super(reservaEnvuelta);
    }

    @Override
    public double calcularCosto() {
        return super.calcularCosto() + costoWifi;
    }

    @Override
    public String obtenerDescripcion() {
        return super.obtenerDescripcion() + " + Kit Wifi 5G";
    }
}
