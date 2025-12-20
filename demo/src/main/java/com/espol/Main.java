package com.espol;

import com.espol.Decorator.*;
import com.espol.Strategy.*;
import com.espol.chainOfResponsability.*;
import com.espol.factory.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("-".repeat(80));
        System.out.println(" SISTEMA TRAVELEASE ");
        System.out.println("-".repeat(80));
        
        // 1. CONFIGURACIÓN INICIAL DEL SISTEMA
        System.out.println("\n" + "-".repeat(80));
        System.out.println("1. CONFIGURACIÓN INICIAL DEL SISTEMA");
        System.out.println("-".repeat(80));
        
        Pasajero pasajero1 = new Pasajero(1, "María González", "maria@email.com", "pass123");
        Pasajero pasajero2 = new Pasajero(2, "Carlos Pérez", "carlos@email.com", "pass456");
        AgenteSoporte agente1 = new AgenteSoporte(10, "Ana Rodríguez", "ana@support.com", "agent123");
        Administrador admin = new Administrador(100, "Jorge Admin", "admin@travelease.com", "admin456");
        
        System.out.println("Usuarios creados exitosamente");

        ServicioFactory aereoFactory = new AereoFactory();
        ServicioFactory vehiculoFactory = new VehiculoFactory();
        
        ProveedorAereo aereolinea = (ProveedorAereo) aereoFactory.crearProveedor(1, "AeroEcuador");
        ProveedorVehiculo rentACar = (ProveedorVehiculo) vehiculoFactory.crearProveedor(2, "RentACar Premium");

        admin.integrarProveedores(aereolinea);
        admin.integrarProveedores(rentACar);
        
        System.out.println("Proveedores integrados al sistema\n");
        
        // 2. CONFIGURACIÓN DE CADENA DE RESPONSABILIDAD (CHAIN OF RESPONSIBILITY)
        System.out.println("\n" + "-".repeat(80));
        System.out.println("2. CONFIGURACIÓN DE CADENA DE ESCALAMIENTO");
        System.out.println("-".repeat(80));
        
        ManejadorIncidencia manejadorAgente = new ManejadorAgenteSoporte(agente1);
        ManejadorIncidencia manejadorAereo = new ManejadorProveedorAereo(aereolinea);
        ManejadorIncidencia manejadorVehiculo = new ManejadorProveedorVehiculo("RentACar Premium");
        
        manejadorAgente.setSiguiente(manejadorAereo).setSiguiente(manejadorVehiculo);
        
        System.out.println("Cadena de escalamiento configurada:");
        System.out.println("  1. " + manejadorAgente.getNombreNivel());
        System.out.println("  2. " + manejadorAereo.getNombreNivel());
        System.out.println("  3. " + manejadorVehiculo.getNombreNivel() + "\n");
        
        // 3. CREAR RESERVA CON ABSTRACT FACTORY Y DECORATOR
        System.out.println("\n" + "-".repeat(80));
        System.out.println("3. CREACIÓN DE RESERVA (Abstract Factory + Decorator)");
        System.out.println("-".repeat(80));
        
        EstrategiaNotificacion estrategiaEmail = new NotificacionEmail();
        Notificador notificador = new Notificador(estrategiaEmail);
        
        System.out.println("\n>>> RESERVA DE VUELO <<<");
        Reserva reserva1 = pasajero1.realizarReserva(1001, aereoFactory, notificador);
        
        System.out.println("\n>>> AGREGANDO VEHÍCULO A LA RESERVA <<<");
        IReservable vehiculo = vehiculoFactory.crearReservable(101, rentACar);
        reserva1.agregarReservable(vehiculo);
        
        // 4. APLICAR DECORATOR PATTERN - SERVICIOS ADICIONALES
        System.out.println("\n"+"-".repeat(80));
        System.out.println("4. APLICACIÓN DE SERVICIOS ADICIONALES (Decorator Pattern)");
        System.out.println("-".repeat(80));
    
        IComponenteReserva reservaBase = reserva1;
        
        System.out.println("\nReserva base:");
        System.out.println("  " + reservaBase.obtenerDescripcion());
        System.out.println("  Costo: $" + reservaBase.calcularCosto());
        
        IComponenteReserva conSeguro = new Seguro(reservaBase);
        System.out.println("\nAgregando Seguro de Viaje:");
        System.out.println("  " + conSeguro.obtenerDescripcion());
        System.out.println("  Costo: $" + conSeguro.calcularCosto());
        
        IComponenteReserva conWifi = new Wifi(conSeguro);
        System.out.println("\nAgregando Kit WiFi 5G:");
        System.out.println("  " + conWifi.obtenerDescripcion());
        System.out.println("  Costo: $" + conWifi.calcularCosto());
        
        IComponenteReserva completa = new Equipaje(conWifi);
        System.out.println("\n→ Agregando Maleta Adicional:");
        System.out.println("  " + completa.obtenerDescripcion());
        System.out.println("  Costo final: $" + completa.calcularCosto());
        
        reserva1.agregarServicio(new ServicioAdicional(1, "Seguro de Viaje", 50.00f));
        reserva1.agregarServicio(new ServicioAdicional(2, "Kit WiFi 5G", 15.00f));
        reserva1.agregarServicio(new ServicioAdicional(3, "Maleta Adicional", 35.50f));

        // 5. CONFIRMAR PAGO - STATE PATTERN
        System.out.println("\n"+"-".repeat(80));
        System.out.println("5. CONFIRMACIÓN DE PAGO (State Pattern)");
        System.out.println("-".repeat(80));
        
        System.out.println("\nEstado actual de la reserva: " + reserva1.getEstadoNombre());
        System.out.println("\n>>> Confirmando pago con tarjeta de crédito <<<");
        reserva1.confirmarPago("Tarjeta de Crédito");
        System.out.println("\nEstado final de la reserva: " + reserva1.getEstadoNombre());
        
        // 6. CAMBIAR ESTRATEGIA DE NOTIFICACIÓN (STRATEGY PATTERN)
        System.out.println("\n"+"-".repeat(80));
        System.out.println("6. CAMBIO DE ESTRATEGIA DE NOTIFICACIÓN (Strategy Pattern)");
        System.out.println("-".repeat(80));
        
        System.out.println("\n>>> Cambiando a notificaciones por Mensajeria <<<");
        EstrategiaNotificacion estrategiaMensajeria = new NotificacionMensajeria();
        Notificador notificadorWhatsApp = new Notificador(estrategiaMensajeria);
        
        Reserva reserva2 = new Reserva(1002, pasajero2, notificadorWhatsApp, 280.0, "Reserva express");
        IReservable vuelo2 = aereoFactory.crearReservable(202, aereolinea);
        reserva2.agregarReservable(vuelo2);
        reserva2.confirmarPago("PayPal");

        // 7. GESTIÓN DE INCIDENCIAS - CHAIN OF RESPONSIBILITY
        System.out.println("\n"+"-".repeat(80));
        System.out.println("7. GESTIÓN DE INCIDENCIAS (Chain of Responsibility)");
        System.out.println("-".repeat(80));
        
        System.out.println("\n>>> CASO 1: Consulta Simple <<<");
        Incidencia incidencia1 = pasajero1.reportarIncidencia(
            5001,
            "Necesito información sobre el horario de mi vuelo"
        );
        agente1.gestionarIncidencia(incidencia1);
        
        System.out.println("\n>>> CASO 2: Problema con Vuelo <<<");
        Incidencia incidencia2 = pasajero1.reportarIncidencia(
            5002,
            "Mi vuelo fue cancelado y necesito alternativas"
        );
        agente1.gestionarIncidencia(incidencia2);

        System.out.println("\n>>> CASO 3: Problema con Vehículo <<<");
        Incidencia incidencia3 = pasajero2.reportarIncidencia(
            5003,
            "El vehículo alquilado tiene un problema mecánico"
        );
        agente1.gestionarIncidencia(incidencia3);
        
        System.out.println("\n>>> CASO 4: Conflicto de Horario <<<");
        Incidencia incidencia4 = pasajero1.reportarIncidencia(
            5004,
            "Tengo un conflicto de horario con mi vuelo de conexión"
        );
        agente1.gestionarIncidencia(incidencia4);
        
        // 8. CANCELACIÓN DE RESERVA - STATE PATTERN
        System.out.println("\n"+"-".repeat(80));
        System.out.println("8. CANCELACIÓN DE RESERVA (State Pattern)");
        System.out.println("-".repeat(80));
        
        System.out.println("\n>>> Cancelando reserva confirmada <<<");
        System.out.println("Estado antes de cancelar: " + reserva2.getEstadoNombre());
        reserva2.cancelarReserva();
        System.out.println("Estado después de cancelar: " + reserva2.getEstadoNombre());
        
        System.out.println("\n>>> Intentando agregar servicio a reserva cancelada <<<");
        reserva2.agregarServicio(new ServicioAdicional(10, "Upgrade", 100.0f));
        
        // 9. GESTIÓN ADMINISTRATIVA
        System.out.println("\n"+"".repeat(80));
        System.out.println("9. GESTIÓN ADMINISTRATIVA");
        System.out.println("-".repeat(80));
        
        admin.gestionarPoliticasCancelacion();
        admin.gestionarReembolsos();
        
        // 10. VERIFICACIÓN DE DISPONIBILIDAD DE PROVEEDORES
        System.out.println("\n"+"-".repeat(80));
        System.out.println("10. VERIFICACIÓN DE DISPONIBILIDAD");
        System.out.println("-".repeat(80));
        
        System.out.println("\n>>> Disponibilidad de vuelos <<<");
        aereolinea.obtenerDisponibilidad();
        
        System.out.println("\n>>> Disponibilidad de vehículos <<<");
        rentACar.obtenerDisponibilidad();
        
        // 11. RESUMEN FINAL
        System.out.println("\n" + "-".repeat(80));
        System.out.println("11. RESUMEN FINAL DEL SISTEMA");
        System.out.println("-".repeat(80));
        
        System.out.println("\nESTADÍSTICAS:");
        System.out.println("    Reservas creadas: 2");
        System.out.println("    Reservas confirmadas: 1");
        System.out.println("    Reservas canceladas: 1");
        System.out.println("    Incidencias reportadas: 4");
        System.out.println("    Incidencias resueltas: 4");
        System.out.println("    Proveedores integrados: 2");
        
        System.out.println("\nPATRONES DE DISEÑO DEMOSTRADOS:");
        System.out.println("  1. Abstract Factory - Creación de proveedores y servicios");
        System.out.println("  2. State Pattern - Gestión de estados de reservas, vuelos y vehículos");
        System.out.println("  3. Chain of Responsibility - Escalamiento de incidencias");
        System.out.println("  4. Decorator Pattern - Servicios adicionales a reservas");
        System.out.println("  5. Strategy Pattern - Estrategias de notificación");
        
        System.out.println("\n" + "-".repeat(80));
        System.out.println("          FIN DE LA DEMOSTRACIÓN - SISTEMA TRAVELEASE");
        System.out.println("-".repeat(80));
    }
}