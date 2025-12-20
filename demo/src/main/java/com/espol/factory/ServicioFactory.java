package com.espol.factory;

import com.espol.IProveedor;
import com.espol.IReservable;

public interface ServicioFactory {
    IProveedor crearProveedor(int id, String nombre);
    IReservable crearReservable(int id, IProveedor proveedor);
}