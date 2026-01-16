package com.espol;
import com.espol.chainOfResponsability.ManejadorIncidencia;

public class AgenteSoporte extends Usuario {
    private ManejadorIncidencia cadenaEscalamiento;

    public AgenteSoporte(int id, String nombre, String correo, String contrasena) {
        super(id, nombre, correo, contrasena);
        this.cadenaEscalamiento = null;
    }
    public void gestionarIncidencia(Incidencia incidencia) {
        if (incidencia == null) {
            System.out.println("Error: No se puede gestionar una incidencia nula.");
            return;
        }
        
        if (incidencia.getEstadoNombre().equals("CERRADA")) {
            System.out.println("Error: No se puede gestionar una incidencia cerrada.");
            return;
        }

        if (cadenaEscalamiento == null) {
            System.out.println("Advertencia: No hay cadena de escalamiento configurada.");
            incidencia.asignarAgente(this);
            return;
        }

        System.out.println("Agente receptor: " + this.nombre);

        System.out.println("ID Incidencia: #" + incidencia.getIdIncidencia());
        System.out.println("Descripción: " + incidencia.getDescripcion());
        System.out.println("Estado actual: " + incidencia.getEstadoNombre());
        System.out.println("Reportada por: " + incidencia.getNombrePasajero());
        System.out.println("─".repeat(70));

        System.out.println("Iniciando procesamiento a través de la cadena de escalamiento...\n");
        cadenaEscalamiento.manejar(incidencia);
        
        System.out.println("\n" + "─".repeat(70));
        System.out.println("✓ PROCESAMIENTO COMPLETADO");
        System.out.println("─".repeat(70));
        System.out.println("Estado final: " + incidencia.getEstadoNombre());
        
        if (incidencia.tieneAgenteAsignado()) {
            System.out.println("Agente asignado: " + incidencia.getNombreAgenteAsignado());
        }

        
        if (!incidencia.getEscalamientos().isEmpty()) {
            System.out.println("Número de escalamientos: " + incidencia.getEscalamientos().size());
        }
        
        System.out.println("─".repeat(70) + "\n");
    }
    @Deprecated
    public void escalarIncidencia(Incidencia incidencia, Proveedor proveedor) {
        if (incidencia == null || proveedor == null) {
            System.out.println("Error: Incidencia o proveedor nulo.");
            return;
        }
        
        System.out.println("⚠️ ADVERTENCIA: Usando método legacy de escalamiento");
        System.out.println("   Se recomienda migrar a gestionarIncidencia() con cadena.");
        System.out.println();
        System.out.println("Agente " + this.nombre + " escalando incidencia #" + incidencia.getIdIncidencia() + " a " + proveedor.getNombre());
        
        // Crear escalamiento manual
        Escalamiento escalamiento = new Escalamiento(1, proveedor);
        incidencia.escalar(escalamiento);

        incidencia.imprimirResumen();

        System.out.println("Iniciando procesamiento a través de la cadena de escalamiento...\n");
        cadenaEscalamiento.manejar(incidencia);

        incidencia.imprimirResultadoProcesamiento();

    }

    public ManejadorIncidencia getCadenaEscalamiento() {
        return cadenaEscalamiento;
    }
}