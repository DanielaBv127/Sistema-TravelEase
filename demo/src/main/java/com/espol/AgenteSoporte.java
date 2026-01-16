package com.espol;
import com.espol.chainOfResponsability.ManejadorIncidencia;

public class AgenteSoporte extends Usuario {
    private ManejadorIncidencia cadenaEscalamiento;

    public AgenteSoporte(int id, String nombre, String correo, String contrasena) {
        super(id, nombre, correo, contrasena);
        this.cadenaEscalamiento = null;
    }
    public void gestionarIncidencia(Incidencia incidencia) {
        if (!validarIncidencia(incidencia)) {
            return;
        }

        if (debeAsignarDirectamente()) {
            asignarDirectamente(incidencia);
            return;
        }
        procesarConCadena(incidencia);
    }
    private boolean validarIncidencia(Incidencia incidencia) {
        if (incidencia == null) {
            System.out.println("Error: No se puede gestionar una incidencia nula.");
            return false;
        }

        if (incidencia.getEstadoNombre().equals("CERRADA")) {
            System.out.println("Error: No se puede gestionar una incidencia cerrada.");
            System.out.println("  Incidencia #" + incidencia.getIdIncidencia()
                    + " ya fue resuelta previamente.");
            return false;
        }
        return true;
    }

    private boolean debeAsignarDirectamente() {
        return cadenaEscalamiento == null;
    }

    private void asignarDirectamente(Incidencia incidencia) {
        System.out.println("Advertencia: No hay cadena de escalamiento configurada.");
        System.out.println("→ Asignando incidencia directamente al agente " + this.nombre + "...");
        incidencia.asignarAgente(this);
    }

    private void procesarConCadena(Incidencia incidencia) {
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