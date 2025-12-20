package com.espol;
import com.espol.estados.EstadoVehiculo;
public class Vehiculo implements IReservable {
    private int idVehiculo;
    private String tipo;
    private EstadoVehiculo estado;
    private ProveedorVehiculo proveedorVehiculo;

    public Vehiculo(int idVehiculo, String tipo, ProveedorVehiculo proveedor) {
        this.idVehiculo = idVehiculo;
        this.tipo = tipo; // Ej: "SUV", "Económico"
        this.proveedorVehiculo = proveedor;
        this.estado = EstadoVehiculo.DISPONIBLE;
    }

    @Override
    public boolean verificarDisponibilidad() {
        return this.estado == EstadoVehiculo.DISPONIBLE;
    }

    @Override
    public boolean bloquearTemporalmente() {
        if (verificarDisponibilidad()) {
            this.estado = EstadoVehiculo.RESERVADO;
            System.out.println("Vehículo " + idVehiculo + " ("+ tipo +") bloqueado temporalmente.");
            return true;
        }
        return false;
    }
    public void confirmarReserva() {
        if (this.estado == EstadoVehiculo.RESERVADO) {
            this.estado = EstadoVehiculo.MANTENIMIENTO;
            System.out.println("Vehículo " + idVehiculo + " (" + tipo + ") reserva confirmada exitosamente.");
        } else {
        System.out.println("Vehículo " + idVehiculo + " (" + tipo + ") no está en estado RESERVADO. Estado actual: " + this.estado);
    }
}

    // Getters
    public int getIdVehiculo() { return idVehiculo; }
    public EstadoVehiculo getEstado() { return estado; }
    public void setEstado(EstadoVehiculo estado) { this.estado = estado;}
    public String getTipo() { return tipo; }

}

