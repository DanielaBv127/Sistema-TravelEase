package com.espol.estados;

import com.espol.reservable.Vuelo;

public interface EstadoVuelo {
    String getNombre();
    boolean verificarDisponibilidad();
    boolean bloquearTemporalmente(Vuelo vuelo);
    void confirmarReserva(Vuelo vuelo);
    void cancelar(Vuelo vuelo);
}
