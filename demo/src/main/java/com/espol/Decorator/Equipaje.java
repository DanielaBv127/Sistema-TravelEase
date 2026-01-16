package com.espol.Decorator;

public class Equipaje extends DecoradorReserva {

    public Equipaje(IComponenteReserva reservaEnvuelta) {
        super(reservaEnvuelta);
    }

    @Override
    public double calcularCosto() {
        return super.calcularCosto() +
               ConfiguracionPrecios.COSTO_MALETA_ADICIONAL;
    }
}