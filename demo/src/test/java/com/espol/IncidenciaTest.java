package com.espol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.espol.estados.*; // Asegúrate de importar tus estados

import static org.junit.jupiter.api.Assertions.*;

class IncidenciaTest {

    private Incidencia incidencia;
    private Pasajero pasajeroMock;
    private AgenteSoporte agenteMock;

    @BeforeEach
    void setUp() {
        // Preparación de datos antes de cada prueba
        // Asumiendo que Pasajero y Agente tienen constructores simples (id, nombre, correo, clave)
        pasajeroMock = new Pasajero(1, "Juan Perez", "juan@mail.com", "1234");
        agenteMock = new AgenteSoporte(10, "Ana Soporte", "ana@mail.com", "admin123");
        
        // Creamos la instancia a probar
        incidencia = new Incidencia(100, "Problema con equipaje", pasajeroMock);
    }

    @Test
    void testInicializacionCorrecta() {
        // Verificar ID y Descripción
        assertEquals(100, incidencia.getIdIncidencia());
        assertEquals("Problema con equipaje", incidencia.getDescripcion());
        
        // Verificar Estado Inicial (Debe ser Abierta)
        assertTrue(incidencia.getEstado() instanceof IncidenciaAbierta, 
            "La incidencia debería iniciar en estado Abierta");
        
        // Verificar que no tiene agente asignado al inicio
        assertFalse(incidencia.tieneAgenteAsignado());
        assertEquals("Sin asignar", incidencia.getNombreAgenteAsignado());
    }

    @Test
    void testCambiarAEstadoEnProcesoConAgente() {
        // Ejecución: Probamos el método refactorizado que asigna agente y cambia estado
        incidencia.cambiarAEstadoEnProcesoConAgente(agenteMock);

        // Verificación
        assertTrue(incidencia.tieneAgenteAsignado(), "Debería tener agente asignado");
        assertEquals("Ana Soporte", incidencia.getNombreAgenteAsignado());
        assertTrue(incidencia.getEstado() instanceof IncidenciaEnProceso, 
            "El estado debería haber cambiado a En Proceso");
    }

    @Test
    void testCerrarConAgente() {
        // Configuración previa: Asignamos un agente primero
        incidencia.cambiarAEstadoEnProcesoConAgente(agenteMock);

        // Ejecución: Cerramos la incidencia
        incidencia.cerrarConAgente();

        // Verificación
        assertTrue(incidencia.getEstado() instanceof IncidenciaCerrada, 
            "El estado debería ser Cerrada");
        assertTrue(incidencia.tieneAgenteAsignado(), 
            "El agente debería mantenerse asignado al cerrar");
    }
    
    @Test
    void testGetNombreAgenteAsignado_ManejoDeNulos() {
        // Caso 1: Sin agente (establecido en setUp)
        assertEquals("Sin asignar", incidencia.getNombreAgenteAsignado());
        
        // Caso 2: Con agente
        incidencia.setAgenteAsignado(agenteMock);
        assertEquals("Ana Soporte", incidencia.getNombreAgenteAsignado());
    }
}