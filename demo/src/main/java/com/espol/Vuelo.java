package com.espol;

import java.util.Date;

public class Vuelo implements IReservable {
    private int idVuelo;
    private String origen;
    private String destino;
    private Date fechaSalida;
    private Date fechaLlegada;
    private ClaseAsiento claseAsiento;
    private EstadoVuelo estado;
    
    private ProveedorAereo proveedorAereo;

    public Vuelo(int idVuelo, String origen, String destino, Date fechaSalida, ClaseAsiento clase, ProveedorAereo proveedor) {
        this.idVuelo = idVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.claseAsiento = clase;
        this.proveedorAereo = proveedor;
        this.estado = EstadoVuelo.DISPONIBLE;
    }

    // Métodos de la interfaz IReservable
    @Override
    public boolean verificarDisponibilidad() {
        return this.estado == EstadoVuelo.DISPONIBLE;
    }

    @Override
    public boolean bloquearTemporalmente() {
        if (verificarDisponibilidad()) {
            this.estado = EstadoVuelo.RESERVADO;
            System.out.println("Vuelo " + idVuelo + " bloqueado temporalmente.");
            return true;
        }
        return false;
    }

    // Métodos propios (del diagrama original)
    public void obtenerDisponibilidad() {
        System.out.println("Vuelo " + idVuelo + " (" + origen + " a " + destino + ") - Estado: " + this.estado);
    }

    public void confirmarReserva() {
        if (this.estado == EstadoVuelo.RESERVADO) {
            System.out.println("Reserva de vuelo " + idVuelo + " confirmada definitivamente.");
        } else {
            System.out.println("Advertencia: Se intentó confirmar un vuelo que no estaba bloqueado.");
        }
    }
    
    // Getters
    public int getIdVuelo() { return idVuelo; }
    public EstadoVuelo getEstado() { return estado; }
    public void setEstado(EstadoVuelo estado) { this.estado = estado; }
}

