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
        // El agente puede resolver incidencias simples que no requieren al proveedor
        String desc = incidencia.getDescripcion().toLowerCase();
        
        // Incidencias que NO requieren proveedor (pueden ser resueltas por agente)
        boolean esConsultaSimple = desc.contains("información") || desc.contains("horario") ||desc.contains("consulta") ||desc.contains("pregunta");
        
        // Incidencias que SÍ requieren proveedor (NO puede resolver el agente)
        boolean requiereProveedor = desc.contains("vuelo cancelado") ||desc.contains("vehículo dañado") ||desc.contains("problema técnico") ||desc.contains("conflicto de horario") ||desc.contains("inconveniente");
        
        return esConsultaSimple && !requiereProveedor;
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
