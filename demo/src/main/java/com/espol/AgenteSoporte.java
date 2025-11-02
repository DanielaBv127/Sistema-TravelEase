package com.espol;

public class AgenteSoporte extends Usuario {

    public AgenteSoporte(int id, String nombre, String correo, String contrasena) {
        super(id, nombre, correo, contrasena);
    }

    public void gestionarIncidencia(Incidencia incidencia) {
        if (incidencia == null || incidencia.getEstado() == EstadoIncidencia.CERRADA) {
            System.out.println("Error: No se puede gestionar la incidencia.");
            return;
        }
        System.out.println("Agente " + nombre + " asignado a incidencia " + incidencia.getIdIncidencia());
        incidencia.setAgenteAsignado(this);
        incidencia.setEstado(EstadoIncidencia.EN_PROCESO);
    }

    public void escalarIncidencia(Incidencia incidencia, Proveedor proveedor) {
        if (incidencia == null) return;
        
        System.out.println("Agente " + nombre + " escalando incidencia " + incidencia.getIdIncidencia() + " a " + proveedor.getNombre());
        Escalamiento escalamiento = new Escalamiento(1, proveedor); // Nivel 1
        incidencia.puedeEscalar(escalamiento);
    }
}