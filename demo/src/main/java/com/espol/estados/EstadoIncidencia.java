package com.espol.estados;

import com.espol.AgenteSoporte;
import com.espol.Escalamiento;
import com.espol.Incidencia;

public interface EstadoIncidencia {
    String getNombre();
    void asignarAgente(Incidencia incidencia, AgenteSoporte agente);
    void escalar(Incidencia incidencia, Escalamiento escalamiento);
    void resolver(Incidencia incidencia);
}
