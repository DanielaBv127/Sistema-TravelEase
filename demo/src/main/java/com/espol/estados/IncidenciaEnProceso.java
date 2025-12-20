package com.espol.estados;

import com.espol.AgenteSoporte;
import com.espol.Escalamiento;
import com.espol.Incidencia;

public class IncidenciaEnProceso implements EstadoIncidencia{
    @Override
    public String getNombre() {
        return "EN_PROCESO";
    }
    
    @Override
    public void asignarAgente(Incidencia incidencia, AgenteSoporte agente) {
        System.out.println("Reasignando incidencia " + incidencia.getIdIncidencia() + " a nuevo agente: " + agente.getNombre());
        incidencia.setAgenteAsignado(agente);
    }
    
    @Override
    public void escalar(Incidencia incidencia, Escalamiento escalamiento) {
        escalamiento.escalar();
        incidencia.agregarEscalamiento(escalamiento);
        System.out.println("Incidencia " + incidencia.getIdIncidencia() + " escalada a nivel superior.");
    }
    
    @Override
    public void resolver(Incidencia incidencia) {
        if (incidencia.getAgenteAsignado() == null) {
            System.out.println("Error: No hay agente asignado.");
            return;
        }
        incidencia.setEstado(new IncidenciaCerrada());
        System.out.println("Incidencia " + incidencia.getIdIncidencia() + " resuelta por " + incidencia.getAgenteAsignado().getNombre());
    }
}
