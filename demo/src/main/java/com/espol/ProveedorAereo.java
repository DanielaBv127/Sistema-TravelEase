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

    @Override
    public void obtenerDisponibilidad() {
        imprimirEncabezadoDisponibilidad();
        vuelosOfertados.forEach(Vuelo::obtenerDisponibilidad);
    }

    @Override
    public void confirmarReserva() {
        imprimirMensajeConfirmacion();
        for (Vuelo v : vuelosOfertados) {
            if (v.getEstado() instanceof VueloReservado) {
                v.confirmarReserva();
            }
        }
    }
}
