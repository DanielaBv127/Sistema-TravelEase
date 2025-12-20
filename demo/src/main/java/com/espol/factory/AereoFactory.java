package com.espol.factory;

import java.util.Date;

import com.espol.ClaseAsiento;
import com.espol.IProveedor;
import com.espol.IReservable;
import com.espol.ProveedorAereo;
import com.espol.Vuelo;

public class AereoFactory implements ServicioFactory {

    @Override
    public IProveedor crearProveedor(int id, String nombre) {
        return new ProveedorAereo(id, nombre);
    }

    @Override
    public IReservable crearReservable(int id, IProveedor proveedor) {
        if (!(proveedor instanceof ProveedorAereo)) {
            throw new IllegalArgumentException("Proveedor no aéreo");
        }

        ProveedorAereo prov = (ProveedorAereo) proveedor;

        Vuelo vuelo = new Vuelo(
            id,
            "GYE",
            "UIO",
            new Date(),
            ClaseAsiento.ECONOMICA,
            prov
        );

        // El proveedor mantiene la oferta de vuelos
        prov.agregarVuelo(vuelo);

        return vuelo;
    }
}
