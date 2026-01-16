package com.espol.estados;

import com.espol.AgenteSoporte;
import com.espol.Escalamiento;
import com.espol.Incidencia;

public class IncidenciaAbierta implements EstadoIncidencia{
    @Override
    public String getNombre() {
        return "ABIERTA";
    }
    
    @Override
    public void asignarAgente(Incidencia incidencia, AgenteSoporte agente) {
        incidencia.cambiarAEstadoEnProcesoConAgente(agente);
        System.out.println("Agente " + agente.getNombre() + 
                      " asignado a incidencia " + incidencia.getIdIncidencia());
    }

    
    @Override
    public void escalar(Incidencia incidencia, Escalamiento escalamiento) {
    escalamiento.escalar();
    incidencia.registrarEscalamientoYCambiarEstado(escalamiento);
    System.out.println("Incidencia " + incidencia.getIdIncidencia() + 
                      " escalada y ahora EN PROCESO.");
    }
    
    @Override
    public void resolver(Incidencia incidencia) {
        System.out.println("Error: No se puede resolver una incidencia sin agente asignado.");
    }
}

