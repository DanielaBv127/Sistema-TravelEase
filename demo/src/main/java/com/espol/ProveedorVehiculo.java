package com.espol;
import java.util.ArrayList;
import java.util.List;

import com.espol.estados.EstadoVehiculo;
public class ProveedorVehiculo extends Proveedor implements IProveedor {
    private List<Vehiculo> vehiculosOfertados;

    public ProveedorVehiculo(int idProveedor, String nombre) {
        super(idProveedor, nombre, "Vehiculo");
        this.vehiculosOfertados = new ArrayList<>();
    }
    
    public void agregarVehiculo(Vehiculo vehiculo) {
        this.vehiculosOfertados.add(vehiculo);
    }

    // Implementación de IProveedor
    @Override
    public void obtenerDisponibilidad() {
        System.out.println("--- Disponibilidad de " + this.getNombre() + " ---");
        for (Vehiculo v : vehiculosOfertados) {
            // Delega la llamada al método del vehículo
            v.verificarDisponibilidad();
        }
    }

    @Override
    public void confirmarReserva() {
        // Esta es una confirmación a nivel de proveedor
        System.out.println("Proveedor " + this.getNombre() + " confirmando todas las reservas pendientes.");
        for (Vehiculo v : vehiculosOfertados) {
            if (v.getEstado() == EstadoVehiculo.RESERVADO) {
                v.confirmarReserva(); // Confirma cada vehículo individual
            }
        }
    }

    
}
