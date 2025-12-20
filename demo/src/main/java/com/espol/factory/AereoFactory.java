package com.espol.factory;

import java.util.Date;

import com.espol.ClaseAsiento;
import com.espol.IProveedor;
import com.espol.IReservable;
import com.espol.ProveedorAereo;
import com.espol.Vuelo;

public class AereoFactory implements ServicioFactory {

    @Override
    public IProveedor crearProveedor() {
        return new ProveedorAereo(1, "Aerolínea Genérica");
    }

    @Override
    public IReservable crearReservable(IProveedor proveedor) {
        // Garantizamos que vuelo queda asociado a un ProveedorAereo
        ProveedorAereo prov = (ProveedorAereo) proveedor;

        Vuelo vuelo = new Vuelo(
            100,
            "GYE",
            "UIO",
            new Date(),
            ClaseAsiento.ECONOMICA,
            prov
        );

        // El proveedor también puede llevar el histórico de oferta
        prov.agregarVuelo(vuelo);
        return vuelo;
    }
}
