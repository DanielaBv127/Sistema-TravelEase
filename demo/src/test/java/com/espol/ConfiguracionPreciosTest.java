package com.espol;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.espol.Decorator.ConfiguracionPrecios;
import com.espol.Decorator.Equipaje;
import com.espol.Decorator.IComponenteReserva;
import com.espol.Decorator.Seguro;
import com.espol.Decorator.Wifi;
import com.espol.Strategy.EstrategiaNotificacion;
import com.espol.Strategy.NotificacionEmail;

public class ConfiguracionPreciosTest {
    @Test
    void testCalculoCostoConServiciosReales() {
        Pasajero pasajero1 = new Pasajero(1, "María González", "maria@email.com", "pass123");
        EstrategiaNotificacion estrategiaEmail = new NotificacionEmail();
        Notificador notificador = new Notificador(estrategiaEmail);
        
        double costoBase = 100.0;
        IComponenteReserva reserva = new Reserva(1001, pasajero1, notificador, costoBase, "Vuelo a Galápagos");

        reserva = new Seguro(reserva);
        reserva = new Wifi(reserva);
        reserva = new Equipaje(reserva);

        double totalCalculado = reserva.calcularCosto();

        double esperado = costoBase + ConfiguracionPrecios.COSTO_SEGURO_VIAJE + ConfiguracionPrecios.COSTO_KIT_WIFI_5G + ConfiguracionPrecios.COSTO_MALETA_ADICIONAL;

        assertEquals(200.5, totalCalculado, 0.001, "El costo total decorado debe ser $200.5");
        assertEquals(esperado, totalCalculado, "El total debe coincidir exactamente con las constantes de configuración");
    }

    @Test
    void testDescripcionCompleta() {
        Pasajero pasajero = new Pasajero(2, "Carlos Pérez", "carlos@email.com", "pass456");
        IComponenteReserva reserva = new Reserva(1002, pasajero, null, 280.0, "Reserva express");
        
        reserva = new Seguro(reserva);
        reserva = new Wifi(reserva);
        
        String desc = reserva.obtenerDescripcion();
        
        assertTrue(desc.contains("Seguro de Viaje"), "Debe incluir el Seguro");
        assertTrue(desc.contains("Kit Wifi 5G"), "Debe incluir el Wifi");
    }
}