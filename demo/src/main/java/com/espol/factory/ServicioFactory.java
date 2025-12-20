package com.espol.factory;

import com.espol.proveedor.IProveedor;
import com.espol.reservable.IReservable;

public interface ServicioFactory {
    IProveedor crearProveedor();
    IReservable crearReservable();
}

