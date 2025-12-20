package com.espol.factory;

import com.espol.IProveedor;
import com.espol.IReservable;
import com.espol.ProveedorVehiculo;
import com.espol.Vehiculo;

public class VehiculoFactory implements ServicioFactory {

    @Override
    public IProveedor crearProveedor(int id, String nombre) {
        return new ProveedorVehiculo(id, nombre);
    }

    @Override
    public IReservable crearReservable(int id, IProveedor proveedor) {
        if (!(proveedor instanceof ProveedorVehiculo)){
            throw new IllegalArgumentException("Proveedor no vehicular");
        }
        return new Vehiculo(id, "SUV", (ProveedorVehiculo) proveedor);
    }
}