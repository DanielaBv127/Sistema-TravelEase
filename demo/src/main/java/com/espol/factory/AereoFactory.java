package com.espol.factory;

import com.espol.*;

public class AereoFactory implements ServicioFactory {

    @Override
    public IProveedor crearProveedor(int id, String nombre) {
        return new ProveedorAereo(id, nombre);
    }

    @Override
    public IReservable crearReservable(int id, IProveedor proveedor) {
        if(!(proveedor instanceof ProveedorAereo)){
            throw new IllegalArgumentException("Proveedor no aéreo");
        }
        return new Vuelo(id, "Quito", "Guayaquil", new java.util.Date(), ClaseAsiento.ECONOMICA, (ProveedorAereo) proveedor);
    }
}