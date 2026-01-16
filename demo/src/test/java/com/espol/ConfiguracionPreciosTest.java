package com.espol;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.espol.Decorator.ConfiguracionPrecios;
import com.espol.Decorator.DecoradorReserva;
import com.espol.Decorator.Equipaje;
import com.espol.Decorator.Seguro;

public class ConfiguracionPreciosTest {
    @Test
    void testCalculoCostoConConfiguracionPrecios() {

        Reserva reservaBase = new Reserva(1, null, null, 100.0, "Vuelo de prueba");

        DecoradorReserva conEquipaje = new Equipaje(reservaBase);
        DecoradorReserva conSeguro = new Seguro(conEquipaje);

        double resultadoTotal = conSeguro.calcularCosto();

        double esperado = 100.0 + 
                          ConfiguracionPrecios.COSTO_MALETA_ADICIONAL + 
                          ConfiguracionPrecios.COSTO_SEGURO_VIAJE;

        assertEquals(esperado, resultadoTotal, 0.01, "El total debe usar las constantes de ConfiguracionPrecios");
    }
}