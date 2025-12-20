package com.espol.estados;

import com.espol.AgenteSoporte;
import com.espol.Escalamiento;
import com.espol.Incidencia;

public class IncidenciaCerrada implements EstadoIncidencia {
    @Override
    public String getNombre() {
        return "CERRADA";
    }
    
    @Override
    public void asignarAgente(Incidencia incidencia, AgenteSoporte agente) {
        System.out.println("Error: No se puede asignar agente a una incidencia cerrada.");
    }
    
    @Override
    public void escalar(Incidencia incidencia, Escalamiento escalamiento) {
        System.out.println("Error: No se puede escalar una incidencia cerrada.");
    }
    
    @Override
    public void resolver(Incidencia incidencia) {
        System.out.println("La incidencia " + incidencia.getIdIncidencia()+" ya estaba cerrada.");
    }
    
    public void reabrir(Incidencia incidencia) {
        incidencia.setEstado(new IncidenciaAbierta());
        System.out.println("Incidencia " + incidencia.getIdIncidencia() + " reabierta.");
    }
}
