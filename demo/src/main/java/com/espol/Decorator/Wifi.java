package com.espol.Decorator;

public class Wifi extends DecoradorReserva {

    public Wifi(IComponenteReserva reservaEnvuelta) {
        super(reservaEnvuelta);
    }

    @Override
    public double calcularCosto() {
        return super.calcularCosto() + ConfiguracionPrecios.COSTO_KIT_WIFI_5G;
    }

    @Override
    public String obtenerDescripcion() {
        return super.obtenerDescripcion() + " + Kit Wifi 5G";
    }
}
