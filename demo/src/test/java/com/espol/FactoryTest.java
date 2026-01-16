package com.espol;

import com.espol.factory.AereoFactory;
import com.espol.factory.ServicioFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Sección B

class FactoryTest {

    @Test
    void factoryAereaDebeCrearFamiliaCorrecta() {
        ServicioFactory factory = new AereoFactory();

        IProveedor proveedor = factory.crearProveedor(1, "LATAM");
        IReservable reservable = factory.crearReservable(100, proveedor);

        assertNotNull(proveedor);                  
        assertNotNull(reservable);                 
        assertInstanceOf(Vuelo.class, reservable); 
        assertInstanceOf(ProveedorAereo.class, proveedor);
    }
}
