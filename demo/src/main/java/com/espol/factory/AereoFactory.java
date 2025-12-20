package com.espol.factory;

import java.util.Date;
import com.espol.proveedor.*;
import com.espol.reservable.*;

public class AereoFactory implements ServicioFactory {

    @Override
    public IProveedor crearProveedor() {
        return new ProveedorAereo(1, "Aerolínea Genérica");
    }

    @Override
    public IReservable crearReservable() {
        ProveedorAereo proveedor = (ProveedorAereo) crearProveedor();
        return new Vuelo(
            100,
            "GYE",
            "UIO",
            new Date(),
            ClaseAsiento.ECONOMICA,
            proveedor
        );
    }
}
