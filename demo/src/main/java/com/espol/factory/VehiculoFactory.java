package com.espol.factory;

import com.espol.IProveedor;
import com.espol.IReservable;
import com.espol.ProveedorVehiculo;
import com.espol.Vehiculo;

public class VehiculoFactory implements ServicioFactory {

    @Override
    public IProveedor crearProveedor() {
        return new ProveedorVehiculo(2, "RentCar Genérico");
    }

    @Override
    public IReservable crearReservable(IProveedor proveedor) {
        ProveedorVehiculo prov = (ProveedorVehiculo) proveedor;

        Vehiculo vehiculo = new Vehiculo(200, "SUV", prov);
        prov.agregarVehiculo(vehiculo);
        return vehiculo;
    }
}

