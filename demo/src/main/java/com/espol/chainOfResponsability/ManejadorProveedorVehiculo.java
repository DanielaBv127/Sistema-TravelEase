package com.espol.chainOfResponsability;

import com.espol.Incidencia;

public class ManejadorProveedorVehiculo extends ManejadorIncidencia {
    private String nombreProveedor;

    public ManejadorProveedorVehiculo(String nombreProveedor) {
        super("Proveedor de Vehículos: " + nombreProveedor);
        this.nombreProveedor = nombreProveedor;
    }

    @Override
    protected boolean puedeResolver(Incidencia incidencia) {
        // El proveedor de vehículos resuelve problemas relacionados con vehículos
        String desc = incidencia.getDescripcion().toLowerCase();
        return desc.contains("vehículo") || desc.contains("auto") || desc.contains("coche") ||
               desc.contains("alquiler") || desc.contains("conductor") || desc.contains("ruta") ||
               desc.contains("dañado") || desc.contains("accidente");
    }

    @Override
    protected void resolver(Incidencia incidencia) {
        System.out.println("[" + nombreNivel + "] El proveedor de vehículos '" + nombreProveedor +
                           "' ha resuelto la incidencia #" + incidencia.getIdIncidencia() + ": " +
                           incidencia.getDescripcion());
    }

}
