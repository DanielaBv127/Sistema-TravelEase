package com.espol;
import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuario {
    
    private List<Proveedor> proveedores;

    public Administrador(int id, String nombre, String correo, String contrasena) {
        super(id, nombre, correo, contrasena);
        this.proveedores = new ArrayList<>();
    }

    public void gestionarPoliticasCancelacion() {
        System.out.println("Admin " + nombre + ": Actualizando políticas de cancelación.");
    }

    public void gestionarReembolsos() {
        System.out.println("Admin " + nombre + ": Procesando reembolsos maestros.");
    }

    public void integrarProveedores(Proveedor proveedor) {
        if (proveedor == null) return;
        this.proveedores.add(proveedor);
        System.out.println("Proveedor " + proveedor.getNombre() + " integrado al sistema.");
    }
}