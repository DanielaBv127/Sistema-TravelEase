package com.espol;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PagoTest {

    @Test
    void testPagoValido() {
        Pago pago = new Pago(101, 25.5f, "PayPal");
        assertTrue(pago.procesarPago());
    }

    @Test
    void testMontoNegativo() {
        Pago pago = new Pago(102, -5.0f, "Tarjeta");
        assertFalse(pago.procesarPago(), "No debería procesar montos negativos");
    }

    @Test
    void testMontoCero() {
        Pago pago = new Pago(103, 0.0f, "Efectivo");
        assertFalse(pago.procesarPago(), "El monto debe ser mayor a 0");
    }

    @Test
    void testMetodoNulo() {
        Pago pago = new Pago(104, 10.0f, null);
        assertFalse(pago.procesarPago(), "Debe fallar si el método es null");
    }

    @Test
    void testMetodoVacio() {
        Pago pago = new Pago(105, 10.0f, "");
        assertFalse(pago.procesarPago(), "Debe fallar si el método está vacío");
    }
}