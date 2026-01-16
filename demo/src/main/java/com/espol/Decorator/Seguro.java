package com.espol.Decorator;

public class Seguro extends DecoradorReserva {

    public Seguro(IComponenteReserva reservaEnvuelta) {
        super(reservaEnvuelta);
    }

    @Override
    public double calcularCosto() {
        return super.calcularCosto() + ConfiguracionPrecios.COSTO_SEGURO_VIAJE;
    }

    @Override
    public String obtenerDescripcion() {
        return super.obtenerDescripcion() + " + Seguro de Viaje";
    }
}