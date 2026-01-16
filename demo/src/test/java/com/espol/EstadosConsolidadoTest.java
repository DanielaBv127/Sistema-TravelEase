package com.espol;

import com.espol.estados.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Clase de test consolidada para:
 * - IncidenciaCerrada
 * - ReservaCancelada
 * - ReservaConfirmada
 * - MensajesUtil
 * - Proveedor
 */
class EstadosConsolidadoTest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }
    
    // MENSAJES UTIL TESTS
    
    @Nested
    @DisplayName("MensajesUtil - Utilidades de mensajes de error")
    class MensajesUtilTests {
        
        @Test
        @DisplayName("Generar mensaje de error para incidencia")
        void testErrorIncidencia() {
            String mensaje = MensajesUtil.errorIncidencia("asignar", "CERRADA");
            assertEquals("Error: No se puede asignar una incidencia en estado CERRADA.", mensaje);
        }
        
        @Test
        @DisplayName("Generar mensaje de error para reserva")
        void testErrorReserva() {
            String mensaje = MensajesUtil.errorReserva("cancelar", "CONFIRMADA");
            assertEquals("Error: No se puede cancelar una reserva en estado CONFIRMADA.", mensaje);
        }
        
        @Test
        @DisplayName("Error con acción 'escalar' para incidencia")
        void testErrorIncidenciaEscalar() {
            String mensaje = MensajesUtil.errorIncidencia("escalar", "EN_PROCESO");
            assertEquals("Error: No se puede escalar una incidencia en estado EN_PROCESO.", mensaje);
        }
        
        @Test
        @DisplayName("Error con acción 'agregar servicios' para reserva")
        void testErrorReservaAgregarServicios() {
            String mensaje = MensajesUtil.errorReserva("agregar servicios", "CANCELADA");
            assertEquals("Error: No se puede agregar servicios una reserva en estado CANCELADA.", mensaje);
        }
    }
    
    //INCIDENCIA CERRADA TESTS
    
    @Nested
    @DisplayName("IncidenciaCerrada - Estado terminal de incidencias")
    class IncidenciaCerradaTests {
        
        @Test
        @DisplayName("Obtener nombre del estado")
        void testNombre() {
            IncidenciaCerrada estado = new IncidenciaCerrada();
            assertEquals("CERRADA", estado.getNombre());
        }
        
        @Test
        @DisplayName("No permite asignar agente a incidencia cerrada")
        void testAsignarAgente() {
            IncidenciaCerrada estado = new IncidenciaCerrada();
            Incidencia incidencia = new Incidencia(1, "Problema con reserva", null);
            AgenteSoporte agente = new AgenteSoporte(1, "Juan Perez", null, null);
            
            estado.asignarAgente(incidencia, agente);
            
            String output = outContent.toString();
            assertTrue(output.contains("Error: No se puede asignar agente"));
            assertTrue(output.contains("CERRADA"));
        }
        
        @Test
        @DisplayName("No permite escalar incidencia cerrada")
        void testEscalar() {
            IncidenciaCerrada estado = new IncidenciaCerrada();
            Incidencia incidencia = new Incidencia(1, "Problema crítico", null);
            ProveedorTest proveedor = new ProveedorTest(1, "Soporte", "Técnico");
            Escalamiento escalamiento = new Escalamiento(2, proveedor);
            
            estado.escalar(incidencia, escalamiento);
            
            String output = outContent.toString();
            assertTrue(output.contains("Error: No se puede escalar"));
            assertTrue(output.contains("CERRADA"));
        }
        
        @Test
        @DisplayName("Resolver incidencia ya cerrada")
        void testResolver() {
            IncidenciaCerrada estado = new IncidenciaCerrada();
            Incidencia incidencia = new Incidencia(1, "Problema resuelto", null);
            
            estado.resolver(incidencia);
            
            String output = outContent.toString();
            assertTrue(output.contains("ya estaba cerrada"));
            assertTrue(output.contains("1"));
        }
        
        @Test
        @DisplayName("Reabrir incidencia cerrada")
        void testReabrir() {
            IncidenciaCerrada estado = new IncidenciaCerrada();
            Incidencia incidencia = new Incidencia(2, "Nueva revisión", null);
            incidencia.setEstado(estado);
            
            estado.reabrir(incidencia);
            
            String output = outContent.toString();
            assertTrue(output.contains("reabierta"));
            assertTrue(output.contains("2"));
            assertInstanceOf(IncidenciaAbierta.class, incidencia.getEstado());
        }
    }
    
    // ============ RESERVA CANCELADA TESTS ============
    
    @Nested
    @DisplayName("ReservaCancelada - Estado terminal de reservas")
    class ReservaCanceladaTests {
        
        @Test
        @DisplayName("Obtener nombre del estado")
        void testNombre() {
            ReservaCancelada estado = new ReservaCancelada();
            assertEquals("CANCELADA", estado.getNombre());
        }
        
        @Test
        @DisplayName("No permite confirmar pago en reserva cancelada")
        void testConfirmarPago() {
            ReservaCancelada estado = new ReservaCancelada();
            Reserva reserva = new Reserva(1, null, null, 0, "RES001");
            
            estado.confirmarPago(reserva, "Tarjeta");
            
            String output = outContent.toString();
            assertTrue(output.contains("Error: No se puede confirmar pago"));
            assertTrue(output.contains("CANCELADA"));
            assertTrue(output.contains("Debe crear una nueva reserva"));
        }
        
        @Test
        @DisplayName("Cancelar reserva ya cancelada")
        void testCancelar() {
            ReservaCancelada estado = new ReservaCancelada();
            Reserva reserva = new Reserva(2, null, null, 0, "RES002");
            
            estado.cancelar(reserva);
            
            String output = outContent.toString();
            assertTrue(output.contains("ya estaba CANCELADA"));
            assertTrue(output.contains("2"));
        }
        
        @Test
        @DisplayName("No permite agregar servicio adicional")
        void testAgregarServicio() {
            ReservaCancelada estado = new ReservaCancelada();
            Reserva reserva = new Reserva(3, null, null, 0, "RES003");
            ServicioAdicional servicio = new ServicioAdicional(1, "Desayuno", 50);
            
            estado.agregarServicio(reserva, servicio);
            
            String output = outContent.toString();
            assertTrue(output.contains("Error: No se puede agregar servicios"));
            assertTrue(output.contains("CANCELADA"));
        }
        
        @Test
        @DisplayName("No permite agregar recurso reservable")
        void testAgregarReservable() {
            ReservaCancelada estado = new ReservaCancelada();
            Reserva reserva = new Reserva(4, null, null, 0, "RES004");
            IReservable reservable = new ReservableTest();
            
            estado.agregarReservable(reserva, reservable);
            
            String output = outContent.toString();
            assertTrue(output.contains("Error: No se puede agregar servicios reservable"));
            assertTrue(output.contains("CANCELADA"));
        }
    }
    
    // RESERVA CONFIRMADA TESTS
    
    @Nested
    @DisplayName("ReservaConfirmada - Estado confirmado de reservas")
    class ReservaConfirmadaTests {
        
        @Test
        @DisplayName("Obtener nombre del estado")
        void testNombre() {
            ReservaConfirmada estado = new ReservaConfirmada();
            assertEquals("CONFIRMADA", estado.getNombre());
        }
        
        @Test
        @DisplayName("No permite confirmar pago nuevamente")
        void testConfirmarPago() {
            ReservaConfirmada estado = new ReservaConfirmada();
            Reserva reserva = new Reserva(1, null, null, 0, "RES001");
            
            estado.confirmarPago(reserva, "Tarjeta");
            
            String output = outContent.toString();
            assertTrue(output.contains("ya está CONFIRMADA"));
            assertTrue(output.contains("No se puede pagar nuevamente"));
        }
        
        @Test
        @DisplayName("Cancelar reserva confirmada")
        void testCancelar() {
            ReservaConfirmada estado = new ReservaConfirmada();
            Reserva reserva = new Reserva(2, null, null, 100, "RES002");
            ReservableTest reservable = new ReservableTest();
            reserva.agregarReservableInterno(reservable);
            reserva.setEstado(estado);
            
            estado.cancelar(reserva);
            
            String output = outContent.toString();
            assertTrue(output.contains("Cancelando reserva confirmada"));
            assertTrue(output.contains("CANCELADA"));
            assertTrue(output.contains("reembolso"));
            assertInstanceOf(ReservaCancelada.class, reserva.getEstado());
        }
        
        @Test
        @DisplayName("No permite agregar servicio adicional")
        void testAgregarServicio() {
            ReservaConfirmada estado = new ReservaConfirmada();
            Reserva reserva = new Reserva(3, null, null, 0, "RES003");
            ServicioAdicional servicio = new ServicioAdicional(1, "Spa", 75);
            
            estado.agregarServicio(reserva, servicio);
            
            String output = outContent.toString();
            assertTrue(output.contains("Error: No se puede agregar servicios"));
            assertTrue(output.contains("CONFIRMADA"));
        }
        
        @Test
        @DisplayName("No permite agregar recurso reservable")
        void testAgregarReservable() {
            ReservaConfirmada estado = new ReservaConfirmada();
            Reserva reserva = new Reserva(4, null, null, 0, "RES004");
            IReservable reservable = new ReservableTest();
            
            estado.agregarReservable(reserva, reservable);
            
            String output = outContent.toString();
            assertTrue(output.contains("Error: No se puede agregar servicios reservable"));
            assertTrue(output.contains("Debe cancelar y crear una nueva reserva"));
        }
    }
    
    // PROVEEDOR TESTS
    
    @Nested
    @DisplayName("Proveedor - Clase abstracta base para proveedores")
    class ProveedorTests {
        
        @Test
        @DisplayName("Verificar getters de Proveedor")
        void testGetters() {
            ProveedorTest proveedor = new ProveedorTest(1, "Hotel Plaza", "Hospedaje");
            
            assertEquals(1, proveedor.getIdProveedor());
            assertEquals("Hotel Plaza", proveedor.getNombre());
            assertEquals("Hospedaje", proveedor.getTipoServicio());
        }
        
        @Test
        @DisplayName("Imprimir encabezado de disponibilidad")
        void testImprimirEncabezado() {
            ProveedorTest proveedor = new ProveedorTest(2, "Aerolínea XYZ", "Transporte Aéreo");
            
            proveedor.testImprimirEncabezado();
            
            String output = outContent.toString();
            assertTrue(output.contains("Disponibilidad de Aerolínea XYZ"));
        }
        
        @Test
        @DisplayName("Imprimir mensaje de confirmación")
        void testImprimirMensajeConfirmacion() {
            ProveedorTest proveedor = new ProveedorTest(3, "Rental Cars Plus", "Alquiler Vehiculos");
            
            proveedor.testImprimirMensajeConfirmacion();
            
            String output = outContent.toString();
            assertTrue(output.contains("Proveedor Rental Cars Plus confirmando"));
            assertTrue(output.contains("pendientes"));
        }
        
        @Test
        @DisplayName("Llamar obtenerDisponibilidad")
        void testObtenerDisponibilidad() {
            ProveedorTest proveedor = new ProveedorTest(4, "Hotel Meridiano", "Hospedaje");
            
            proveedor.obtenerDisponibilidad();
            
            String output = outContent.toString();
            assertTrue(output.contains("Disponibilidad de Hotel Meridiano"));
            assertTrue(output.contains("Disponibilidad obtenida"));
        }
        
        @Test
        @DisplayName("Llamar confirmarReserva")
        void testConfirmarReserva() {
            ProveedorTest proveedor = new ProveedorTest(5, "Uber", "Transporte");
            
            proveedor.confirmarReserva();
            
            String output = outContent.toString();
            assertTrue(output.contains("Proveedor Uber confirmando"));
            assertTrue(output.contains("Reserva confirmada"));
        }
    }
    
    // CLASES HELPER PARA TESTS
    
    /**
     * Implementación de prueba de IReservable
     */
    private static class ReservableTest implements IReservable {
        private boolean liberado = false;
        
        @Override
        public void liberar() {
            liberado = true;
        }
        
        @Override
        public boolean verificarDisponibilidad() {
            return true;
        }
        
        @Override
        public boolean bloquearTemporalmente() {
            return true;
        }
        
        @Override
        public void confirmarReserva() {
            // Implementación de prueba
        }
        
        public boolean isLiberado() {
            return liberado;
        }
    }
    
    /**
     * Implementación concreta de Proveedor para pruebas
     */
    private static class ProveedorTest extends Proveedor {
        public ProveedorTest(int id, String nombre, String tipo) {
            super(id, nombre, tipo);
        }
        
        @Override
        public void obtenerDisponibilidad() {
            imprimirEncabezadoDisponibilidad();
            System.out.println("Disponibilidad obtenida");
        }
        
        @Override
        public void confirmarReserva() {
            imprimirMensajeConfirmacion();
            System.out.println("Reserva confirmada");
        }
        
        public void testImprimirEncabezado() {
            imprimirEncabezadoDisponibilidad();
        }
        
        public void testImprimirMensajeConfirmacion() {
            imprimirMensajeConfirmacion();
        }
    }
}
