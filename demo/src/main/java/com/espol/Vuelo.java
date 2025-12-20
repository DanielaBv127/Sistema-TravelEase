package com.espol;
import java.util.Date;

import com.espol.estados.*;

public class Vuelo implements IReservable {
    private int idVuelo;
    private String origen;
    private String destino;
    private Date fechaSalida;
    private Date fechaLlegada;
    private ClaseAsiento claseAsiento;
    private EstadoVuelo estado;
      // State
    private ProveedorAereo proveedorAereo;

    public Vuelo(int idVuelo, String origen, String destino,Date fechaSalida, ClaseAsiento clase,ProveedorAereo proveedor) {

        this.idVuelo = idVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.claseAsiento = clase;
        this.proveedorAereo = proveedor;
        this.estado = new VueloDisponible();
    }

    @Override
    public boolean verificarDisponibilidad() {
        return estado.verificarDisponibilidad();
    }

    @Override
    public boolean bloquearTemporalmente() {
        return estado.bloquearTemporalmente(this);
    }
    public void obtenerDisponibilidad() {
        System.out.println("Vuelo " + idVuelo + " (" + origen + " a " + destino + ") - Estado: " + estado.getNombre());
    }
    public void confirmarReserva() {
        estado.confirmarReserva(this);
    }

    public void cancelar() {
        estado.cancelar(this);
    }

    // Getters / setters mínimos
    public int getIdVuelo() {
        return idVuelo;
    }
    public EstadoVuelo getEstado() { 
        return estado;
    }
    public void setEstado(EstadoVuelo estado) {
        this.estado = estado;
    }

    public String getEstadoNombre() {
        return estado.getNombre();
    }
}
