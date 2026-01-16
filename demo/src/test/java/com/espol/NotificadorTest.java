package com.espol;

import com.espol.Strategy.EstrategiaNotificacion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Sección B

class NotificadorTest {

    static class EstrategiaFake implements EstrategiaNotificacion {
        String mensaje;

        @Override
        public void enviar(String mensaje) {
            this.mensaje = mensaje;
        }
    }

    @Test
    void debeEnviarMensajeUsandoEstrategia() {
        EstrategiaFake fake = new EstrategiaFake();
        Notificador notificador = new Notificador(fake);

        notificador.notificar("hola");

        assertAll(                                  
            () -> assertNotNull(fake.mensaje),
            () -> assertEquals("hola", fake.mensaje) 
        );
    }
}

