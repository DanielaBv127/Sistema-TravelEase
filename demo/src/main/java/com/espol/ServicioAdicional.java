package com.espol;
public class ServicioAdicional {
    private int idServicio;
    private String descripcion;
    private float costo;

    public ServicioAdicional(int idServicio, String descripcion, float costo) {
        this.idServicio = idServicio;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getCosto() {
        return costo;
    }
}