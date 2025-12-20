package com.espol;
import java.util.ArrayList;
import java.util.List;

import com.espol.estados.VueloReservado;

public class ProveedorAereo extends Proveedor implements IProveedor {
    private List<Vuelo> vuelosOfertados;

    public ProveedorAereo(int idProveedor, String nombre) {
        super(idProveedor, nombre, "Aereo");
        this.vuelosOfertados = new ArrayList<>();
    }
    
    public void agregarVuelo(Vuelo vuelo) {
        this.vuelosOfertados.add(vuelo);
    }

    // Implementación de IProveedor
    @Override
    public void obtenerDisponibilidad() {
        System.out.println("--- Disponibilidad de " + this.getNombre() + " ---");
        for (Vuelo v : vuelosOfertados) {
            // Delega la llamada al método del vuelo
            v.obtenerDisponibilidad();
        }
    }

    @Override
    public void confirmarReserva() {
        // Esta es una confirmación a nivel de proveedor
        System.out.println("Proveedor " + this.getNombre() + " confirmando todas las reservas pendientes.");
        for (Vuelo v : vuelosOfertados) {
            if (v.getEstado() == new VueloReservado()) {
                v.confirmarReserva(); // Confirma cada vuelo individual
            }
        }
    }
    
}
