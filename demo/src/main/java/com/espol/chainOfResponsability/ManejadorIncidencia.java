package com.espol.chainOfResponsability;

import com.espol.Incidencia;

public abstract class ManejadorIncidencia {
    protected ManejadorIncidencia siguiente;
    protected String nombreNivel;
    
    public ManejadorIncidencia(String nombreNivel) {
        this.nombreNivel = nombreNivel;
    }
    public ManejadorIncidencia setSiguiente(ManejadorIncidencia siguiente) {
        this.siguiente = siguiente;
        return siguiente;
    }
    
    public final void manejar(Incidencia incidencia) {
        System.out.println("\n[" + nombreNivel + "] Evaluando incidencia #" + incidencia.getIdIncidencia() + "...");
        
        if (puedeResolver(incidencia)) {
            resolver(incidencia);
        } else {
            System.out.println("[" + nombreNivel + "] No puedo resolver esta incidencia.");
            escalar(incidencia);
        }
    }
    protected abstract boolean puedeResolver(Incidencia incidencia);
    
    protected abstract void resolver(Incidencia incidencia);
    
    protected void escalar(Incidencia incidencia) {
        if (siguiente != null) {
            System.out.println("[" + nombreNivel + "] Escalando al siguiente nivel...");
            siguiente.manejar(incidencia);
        } else {
            System.out.println("[" + nombreNivel + "]  No hay más niveles disponibles.");
            System.out.println("La incidencia #" + incidencia.getIdIncidencia() + " requiere atención especial o intervención manual.");
        }
    }
    
    public String getNombreNivel() {
        return nombreNivel;
    }
}
