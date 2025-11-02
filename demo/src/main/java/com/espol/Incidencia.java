package com.espol;

import java.util.ArrayList;
import java.util.List;

public class Incidencia {
    private int idIncidencia;
    private String descripcion;
    private EstadoIncidencia estado;
    
    private Pasajero pasajeroReporta;
    private AgenteSoporte agenteAsignado;
    private List<Escalamiento> escalamientos;

    public Incidencia(int idIncidencia, String descripcion, Pasajero pasajeroReporta) {
        this.idIncidencia = idIncidencia;
        this.descripcion = descripcion;
        this.pasajeroReporta = pasajeroReporta;
        this.estado = EstadoIncidencia.ABIERTA;
        this.escalamientos = new ArrayList<>();
    }

    public void registrarIncidencia() {
        System.out.println("Incidencia " + idIncidencia + " registrada por " + pasajeroReporta.getNombre() + ". Estado: " + this.estado);
    }

    public void resolverIncidencia() {
        if (agenteAsignado == null) {
            System.out.println("Error: La incidencia no puede cerrarse sin un agente asignado.");
            return;
        }
        this.estado = EstadoIncidencia.CERRADA;
        System.out.println("Incidencia " + idIncidencia + " resuelta por " + agenteAsignado.getNombre());
    }

    public void puedeEscalar(Escalamiento escalamiento) {
        if (this.estado == EstadoIncidencia.CERRADA) {
            System.out.println("Error: No se puede escalar una incidencia que ya está cerrada.");
            return;
        }
        
        escalamiento.escalar();
        
        this.escalamientos.add(escalamiento);
        this.estado = EstadoIncidencia.EN_PROCESO;
        System.out.println("Incidencia " + idIncidencia + " ahora EN PROCESO y escalada.");
    }

    public int getIdIncidencia() { 
        return idIncidencia; 
    }

    public EstadoIncidencia getEstado() { 
        return estado; 
    }

    public void setEstado(EstadoIncidencia estado) { 
        this.estado = estado; 
    }

    public void setAgenteAsignado(AgenteSoporte agenteAsignado) {
        this.agenteAsignado = agenteAsignado;
    }
}