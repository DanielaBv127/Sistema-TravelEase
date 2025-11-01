package com.espol;

public abstract class Proveedor {
    private int idProveedor;
    private String nombre;
    private String tipoServicio;

    public Proveedor(int idProveedor, String nombre, String tipoServicio){
        this.idProveedor= idProveedor;
        this.nombre= nombre;
        this.tipoServicio= tipoServicio;
    }

    public String getNombre(){
        return nombre;
    }

    public int getIdProveedor(){
        return idProveedor;
    }
}
