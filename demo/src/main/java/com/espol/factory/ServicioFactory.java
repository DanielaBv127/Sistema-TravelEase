package com.espol.factory;

import com.espol.IProveedor;
import com.espol.IReservable;

/**
 * Abstract Factory: crea una familia coherente de objetos
 * (Proveedor + Servicio Reservable) según el tipo de servicio.
 */
public interface ServicioFactory {
    IProveedor crearProveedor();
    IReservable crearReservable(IProveedor proveedor);
}

