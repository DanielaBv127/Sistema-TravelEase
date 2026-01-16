package com.espol;

import java.util.ArrayList;
import java.util.List;

import com.espol.estados.VehiculoReservado;

public class ProveedorVehiculo extends Proveedor implements IProveedor {

    private List<Vehiculo> vehiculosOfertados;

    public ProveedorVehiculo(int idProveedor, String nombre) {
        super(idProveedor, nombre, "Vehiculo");
        this.vehiculosOfertados = new ArrayList<>();
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        this.vehiculosOfertados.add(vehiculo);
    }

    @Override
    public void obtenerDisponibilidad() {
        imprimirEncabezadoDisponibilidad();
        vehiculosOfertados.forEach(Vehiculo::verificarDisponibilidad);
    }

    @Override
    public void confirmarReserva() {
        imprimirMensajeConfirmacion();

        for (Vehiculo v : vehiculosOfertados) {
            if (v.getEstado() instanceof VehiculoReservado) {
                v.confirmarReserva();
            }
        }
    }
}
