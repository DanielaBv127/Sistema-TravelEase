package com.espol;
import com.espol.estados.EstadoVehiculo;
import com.espol.estados.VehiculoDisponible;
import com.espol.estados.VehiculoMantenimiento;
import com.espol.estados.VehiculoReservado;
public class Vehiculo implements IReservable {
    private int idVehiculo;
    private String tipo;
    private EstadoVehiculo estado;
    private ProveedorVehiculo proveedorVehiculo;

    public Vehiculo(int idVehiculo, String tipo, ProveedorVehiculo proveedor) {
        this.idVehiculo = idVehiculo;
        this.tipo = tipo; // Ej: "SUV", "Económico"
        this.proveedorVehiculo = proveedor;
        this.estado = new VehiculoDisponible();
    }

    @Override
    public boolean verificarDisponibilidad() {
        return this.estado == new VehiculoDisponible();
    }

    @Override
    public boolean bloquearTemporalmente() {
        if (verificarDisponibilidad()) {
            this.estado = new VehiculoReservado();
            System.out.println("Vehículo " + idVehiculo + " ("+ tipo +") bloqueado temporalmente.");
            return true;
        }
        return false;
    }
    public void confirmarReserva() {
        if (this.estado == new VehiculoReservado()) {
            this.estado = new VehiculoMantenimiento();
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

