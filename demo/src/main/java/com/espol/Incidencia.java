package com.espol;

import java.util.ArrayList;
import java.util.List;

import com.espol.estados.EstadoIncidencia;
import com.espol.estados.IncidenciaAbierta;
import com.espol.estados.IncidenciaCerrada;
import com.espol.estados.IncidenciaEnProceso;

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

    public void imprimirResumen() {
        System.out.println("\n" + "─".repeat(70));
        System.out.println("NUEVA INCIDENCIA RECIBIDA");
        System.out.println("─".repeat(70));
        System.out.println("ID Incidencia: #" + idIncidencia);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Estado actual: " + getEstadoNombre());
        System.out.println("Reportada por: " + pasajeroReporta.getNombre());
        System.out.println("─".repeat(70));
    }

    public void imprimirResultadoProcesamiento() {
        System.out.println("\n" + "─".repeat(70));
        System.out.println("✓ PROCESAMIENTO COMPLETADO");
        System.out.println("─".repeat(70));
        System.out.println("Estado final: " + getEstadoNombre());

        if (agenteAsignado != null) {
            System.out.println("Agente asignado: " + agenteAsignado.getNombre());
        }

        if (!escalamientos.isEmpty()) {
            System.out.println("Número de escalamientos: " + escalamientos.size());
        }

        System.out.println("─".repeat(70) + "\n");
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

    public void cambiarAEstadoEnProcesoConAgente(AgenteSoporte agente) {
        this.agenteAsignado = agente;
        this.estado = new IncidenciaEnProceso();
    }

    public void registrarEscalamientoYCambiarEstado(Escalamiento escalamiento) {
        this.escalamientos.add(escalamiento);
        this.estado = new IncidenciaEnProceso();
    }

    public void cerrarConAgente() {
        this.estado = new IncidenciaCerrada();
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
    
    public List<Escalamiento> getEscalamientos() {
        return escalamientos;
    }

    public String getNombrePasajero() {
        return pasajeroReporta.getNombre();
    }
    
    public String getNombreAgenteAsignado() {
        return agenteAsignado != null ? agenteAsignado.getNombre() : "Sin asignar";
    }
    
    public boolean tieneAgenteAsignado() {
        return agenteAsignado != null;
    }

    public AgenteSoporte getAgenteAsignado(){
        return agenteAsignado;
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