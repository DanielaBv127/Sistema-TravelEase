package com.espol.factory;

import com.espol.proveedor.*;
import com.espol.reservable.*;

public class VehiculoFactory implements ServicioFactory {

    @Override
    public IProveedor crearProveedor() {
        return new ProveedorVehiculo(2, "RentCar Genérico");
    }

    @Override
    public IReservable crearReservable() {
        ProveedorVehiculo proveedor = (ProveedorVehiculo) crearProveedor();
        return new Vehiculo(200, "SUV", proveedor);
    }
}
