package com.espol;

import java.util.Date;

public class Escalamiento {
    private int nivel;
    private Date fechaEscalamiento;
    private Proveedor proveedor;

    public Escalamiento(int nivel, Proveedor proveedor) {
        this.nivel = nivel;
        this.proveedor = proveedor;
        this.fechaEscalamiento = new Date();
    }

    public void escalar() {
        System.out.println("ACCIÓN: Escalando incidencia (Nivel " + this.nivel + ") al proveedor: " + proveedor.getNombre());
    }

    public int getNivel() {
        return nivel;
    }

    public Date getFechaEscalamiento() {
        return fechaEscalamiento;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }
}