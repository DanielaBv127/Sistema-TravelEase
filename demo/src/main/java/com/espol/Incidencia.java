package com.espol;

import java.util.ArrayList;
import java.util.List;

import com.espol.estados.EstadoIncidencia;
import com.espol.estados.IncidenciaAbierta;

public class Incidencia {
    private int idIncidencia;
    private String descripcion;
    private EstadoIncidencia estado; // State Pattern
    
    // Relaciones
    private Pasajero pasajeroReporta;
    private AgenteSoporte agenteAsignado; // Puede ser null inicialmente
    private List<Escalamiento> escalamientos; // Historial de escalamientos

    public Incidencia(int idIncidencia, String descripcion, Pasajero pasajeroReporta) {
        this.idIncidencia = idIncidencia;
        this.descripcion = descripcion;
        this.pasajeroReporta = pasajeroReporta;
        this.estado = new IncidenciaAbierta(); // Estado inicial
        this.escalamientos = new ArrayList<>();
        this.agenteAsignado = null; // Inicialmente sin agente
    }
    public void registrarIncidencia() {
        System.out.println("Incidencia #" + idIncidencia + " registrada por " + pasajeroReporta.getNombre() + ". Estado: " + estado.getNombre());
    }

    public void asignarAgente(AgenteSoporte agente) {
        estado.asignarAgente(this, agente);
    }
    
    public void escalar(Escalamiento escalamiento) {
        estado.escalar(this, escalamiento);
    }
    
    public void resolver() {
        estado.resolver(this);
    }

    public void setAgenteAsignado(AgenteSoporte agenteAsignado) {
        this.agenteAsignado = agenteAsignado;
    }
    
    public void agregarEscalamiento(Escalamiento escalamiento) {
        this.escalamientos.add(escalamiento);
    }

    public int getIdIncidencia() {
        return idIncidencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public EstadoIncidencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoIncidencia estado) {
        this.estado = estado;
    }
    
    public String getEstadoNombre() {
        return estado.getNombre();
    }
    
    public AgenteSoporte getAgenteAsignado() {
        return agenteAsignado;
    }
    
    public List<Escalamiento> getEscalamientos() {
        return escalamientos;
    }
    
    public Pasajero getPasajeroReporta() {
        return pasajeroReporta;
    }
    
    /**
     * Muestra el historial completo de escalamientos
     */
    public void mostrarHistorialEscalamientos() {
        if (escalamientos.isEmpty()) {
            System.out.println("No hay escalamientos registrados para esta incidencia.");
            return;
        }
        
        System.out.println("\nHistorial de escalamientos - Incidencia #" + idIncidencia + ":");
        for (int i = 0; i < escalamientos.size(); i++) {
            Escalamiento e = escalamientos.get(i);
            System.out.println("  " + (i+1) + ". Nivel " + e.getNivel() + " - Proveedor: " + e.getProveedor().getNombre() + " - Fecha: " + e.getFechaEscalamiento());
        }
    }
}