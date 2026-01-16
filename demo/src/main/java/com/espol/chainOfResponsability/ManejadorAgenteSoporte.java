package com.espol.chainOfResponsability;

import com.espol.AgenteSoporte;
import com.espol.Incidencia;

public class ManejadorAgenteSoporte extends ManejadorIncidencia {
    private AgenteSoporte agente;
    
    public ManejadorAgenteSoporte(AgenteSoporte agente) {
        super("AGENTE DE SOPORTE - " + agente.getNombre());
        this.agente = agente;
    }
    
    @Override
    protected boolean puedeResolver(Incidencia incidencia) {
    return esConsultaSimpleDelCliente(incidencia)
            && !requiereIntervencionProveedor(incidencia);
    }
    private boolean esConsultaSimpleDelCliente(Incidencia incidencia) {
    String descripcion = incidencia.getDescripcion().toLowerCase();
    return descripcion.contains("información")
            || descripcion.contains("horario")
            || descripcion.contains("consulta")
            || descripcion.contains("pregunta");
    }

    private boolean requiereIntervencionProveedor(Incidencia incidencia) {
        String descripcion = incidencia.getDescripcion().toLowerCase();
        return descripcion.contains("vuelo cancelado")
                || descripcion.contains("vehículo dañado")
                || descripcion.contains("problema técnico")
                || descripcion.contains("conflicto de horario")
                || descripcion.contains("inconveniente");
    }
    
    @Override
    protected void resolver(Incidencia incidencia) {
        System.out.println("[" + nombreNivel + "] ✓ Resolviendo incidencia simple...");
        
        // Asignar agente a la incidencia
        incidencia.asignarAgente(agente);
        
        // Simular resolución
        System.out.println("[" + nombreNivel + "] Proporcionando información al cliente...");
        System.out.println("[" + nombreNivel + "] Aplicando solución estándar...");
        
        // Cerrar incidencia
        incidencia.resolver();
        
        System.out.println("[" + nombreNivel + "] ✓ Incidencia resuelta exitosamente.");
    }   

}

