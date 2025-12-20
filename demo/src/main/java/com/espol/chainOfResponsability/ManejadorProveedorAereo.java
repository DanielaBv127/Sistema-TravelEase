package com.espol.chainOfResponsability;

import com.espol.Escalamiento;
import com.espol.Incidencia;
import com.espol.ProveedorAereo;

public class ManejadorProveedorAereo extends ManejadorIncidencia {
    private ProveedorAereo proveedor;
    
    public ManejadorProveedorAereo(ProveedorAereo proveedor) {
        super("DPTO. SERVICIO AL CLIENTE - " + proveedor.getNombre());
        this.proveedor = proveedor;
    }
    
    @Override
    protected boolean puedeResolver(Incidencia incidencia) {
        // El proveedor aéreo resuelve problemas relacionados con vuelos
        String desc = incidencia.getDescripcion().toLowerCase();
        return desc.contains("vuelo") || desc.contains("avión") ||  desc.contains("aerolínea") ||
               desc.contains("tripulación") ||
               desc.contains("equipaje") ||
               desc.contains("horario de vuelo") ||
               desc.contains("conflicto de horario");
    }
    
    @Override
    protected void resolver(Incidencia incidencia) {
        System.out.println("[" + nombreNivel + "] ✓ Departamento de servicio al cliente gestionando...");
        
        // Registrar escalamiento al proveedor
        Escalamiento escalamiento = new Escalamiento(1, proveedor);
        incidencia.escalar(escalamiento);
        
        System.out.println("[" + nombreNivel + "] Contactando con operaciones de vuelo...");
        System.out.println("[" + nombreNivel + "] Verificando disponibilidad de alternativas...");
        
        // Simular acciones del proveedor
        if (incidencia.getDescripcion().toLowerCase().contains("cancelado")) {
            System.out.println("[" + nombreNivel + "] Ofreciendo vuelo alternativo...");
            System.out.println("[" + nombreNivel + "] Proporcionando compensación por cancelación");
        } else if (incidencia.getDescripcion().toLowerCase().contains("retraso")) {
            System.out.println("[" + nombreNivel + "] Informando nueva hora de salida...");
            System.out.println("[" + nombreNivel + "] Ofreciendo voucher de cortesía");
        } else if (incidencia.getDescripcion().toLowerCase().contains("conflicto")) {
            System.out.println("[" + nombreNivel + "] Resolviendo conflicto de horario...");
            System.out.println("[" + nombreNivel + "] Reprogramando vuelo");
        }
        
        incidencia.resolver();
        System.out.println("[" + nombreNivel + "] ✓ Incidencia resuelta por la aerolínea.");
    }

}
