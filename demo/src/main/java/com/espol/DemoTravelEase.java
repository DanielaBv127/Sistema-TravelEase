package com.espol;

import com.espol.factory.*;
import com.espol.chainOfResponsability.*;
import com.espol.Decorator.*;
import com.espol.Strategy.*;

public class DemoTravelEase {

    private Pasajero pasajero1, pasajero2;
    private AgenteSoporte agente1;
    private Administrador admin;

    private ProveedorAereo aereolinea;
    private ProveedorVehiculo rentACar;

    private ServicioFactory aereoFactory;
    private ServicioFactory vehiculoFactory;

    private Reserva reservaDemo;

    // =======================
    // MÉTODO PRINCIPAL DEMO
    // =======================
    public void ejecutar() {
        imprimirEncabezado();
        configurarSistema();
        configurarCadenaEscalamiento();
        demostrarReserva();
        demostrarIncidencias();
        imprimirResumenFinal();
    }

    // =======================
    // SECCIONES
    // =======================
    private void imprimirEncabezado() {
        System.out.println("-".repeat(80));
        System.out.println("           SISTEMA TRAVELEASE");
        System.out.println("-".repeat(80));
    }

    private void imprimirSeccion(String titulo) {
        System.out.println("\n" + "-".repeat(80));
        System.out.println(titulo);
        System.out.println("-".repeat(80));
    }

    // =======================
    // CONFIGURACIÓN
    // =======================
    private void configurarSistema() {
        imprimirSeccion("1. CONFIGURACIÓN INICIAL DEL SISTEMA");
        crearUsuarios();
        crearProveedores();
    }

    private void crearUsuarios() {
        pasajero1 = new Pasajero(1, "María González", "maria@email.com", "pass123");
        pasajero2 = new Pasajero(2, "Carlos Pérez", "carlos@email.com", "pass456");
        agente1 = new AgenteSoporte(10, "Ana Rodríguez", "ana@support.com", "agent123");
        admin = new Administrador(100, "Jorge Admin", "admin@travelease.com", "admin456");

        System.out.println("Usuarios creados correctamente");
    }

    private void crearProveedores() {
        aereoFactory = new AereoFactory();
        vehiculoFactory = new VehiculoFactory();

        aereolinea = (ProveedorAereo) aereoFactory.crearProveedor(1, "AeroEcuador");
        rentACar = (ProveedorVehiculo) vehiculoFactory.crearProveedor(2, "RentACar Premium");

        admin.integrarProveedores(aereolinea);
        admin.integrarProveedores(rentACar);

        System.out.println("Proveedores integrados al sistema");
    }

    // =======================
    // CADENA DE INCIDENCIAS
    // =======================
    private void configurarCadenaEscalamiento() {
        imprimirSeccion("2. CADENA DE ESCALAMIENTO");

        ManejadorIncidencia m1 = new ManejadorAgenteSoporte(agente1);
        ManejadorIncidencia m2 = new ManejadorProveedorAereo(aereolinea);
        ManejadorIncidencia m3 = new ManejadorProveedorVehiculo("RentACar Premium");

        m1.setSiguiente(m2).setSiguiente(m3);
        agente1.setCadenaEscalamiento(m1);

        System.out.println("Cadena configurada correctamente");
    }

    // =======================
    // RESERVAS
    // =======================
    private void demostrarReserva() {
        imprimirSeccion("3. RESERVA (Abstract Factory + Decorator + State)");

        Notificador notificador = new Notificador(new NotificacionEmail());

        reservaDemo = pasajero1.realizarReserva(1001, aereoFactory, notificador);

        if (reservaDemo == null) {
            System.out.println("No se pudo crear la reserva");
            return;
        }

        IReservable vehiculo = vehiculoFactory.crearReservable(200, rentACar);
        reservaDemo.agregarReservable(vehiculo);

        reservaDemo.agregarServicio(new ServicioAdicional(1, "Seguro de Viaje", 50));
        reservaDemo.agregarServicio(new ServicioAdicional(2, "WiFi", 15));

        System.out.println("Confirmando pago...");
        reservaDemo.confirmarPago("Tarjeta");
    }

    // =======================
    // INCIDENCIAS
    // =======================
    private void demostrarIncidencias() {
        imprimirSeccion("4. GESTIÓN DE INCIDENCIAS");

        Incidencia inc1 = pasajero1.reportarIncidencia(
                5001,
                "Necesito información sobre mi vuelo"
        );
        agente1.gestionarIncidencia(inc1);

        Incidencia inc2 = pasajero2.reportarIncidencia(
                5002,
                "Problema con el vehículo alquilado"
        );
        agente1.gestionarIncidencia(inc2);
    }

    // =======================
    // RESUMEN
    // =======================
    private void imprimirResumenFinal() {
        imprimirSeccion("RESUMEN FINAL");

        System.out.println("Patrones demostrados:");
        System.out.println("✔ Abstract Factory");
        System.out.println("✔ State");
        System.out.println("✔ Chain of Responsibility");
        System.out.println("✔ Decorator");
        System.out.println("✔ Strategy");

        System.out.println("\nFIN DE LA DEMOSTRACIÓN");
        System.out.println("-".repeat(80));
    }
}
