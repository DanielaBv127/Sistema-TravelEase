package com.espol;

import com.espol.estados.EstadoVehiculo;
import com.espol.estados.VehiculoDisponible;

public class Vehiculo implements IReservable {

    private int idVehiculo;
    private String tipo;

    private EstadoVehiculo estado; // State
    private ProveedorVehiculo proveedorVehiculo;

    public Vehiculo(int idVehiculo, String tipo, ProveedorVehiculo proveedor) {
        this.idVehiculo = idVehiculo;
        this.tipo = tipo;
        this.proveedorVehiculo = proveedor;
        this.estado = new VehiculoDisponible(); // estado inicial
    }

    @Override
    public boolean verificarDisponibilidad() {
        return estado.verificarDisponibilidad();
    }

    @Override
    public boolean bloquearTemporalmente() {
        return estado.bloquearTemporalmente(this);
    }

    @Override
    public void confirmarReserva() {
        estado.confirmarReserva(this);
    }

    @Override
    public void liberar() {
        this.estado = new VehiculoDisponible();
    }

    public void enviarMantenimiento() {
        estado.enviarMantenimiento(this);
    }

    // ===== getters / setters =====

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public String getTipo() {
        return tipo;
    }

    public EstadoVehiculo getEstado() {
        return estado;
    }

    public void setEstado(EstadoVehiculo estado) {
        this.estado = estado;
    }
}

