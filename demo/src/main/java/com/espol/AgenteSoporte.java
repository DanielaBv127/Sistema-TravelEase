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
        incidencia.imprimirResumen();

        System.out.println("Iniciando procesamiento a través de la cadena de escalamiento...\n");
        cadenaEscalamiento.manejar(incidencia);

        incidencia.imprimirResultadoProcesamiento();
    }

    public ManejadorIncidencia getCadenaEscalamiento() {
        return cadenaEscalamiento;
    }
}