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
    protected void imprimirEncabezadoDisponibilidad(){
        System.out.println("---- Disponibilidad de " + this.getNombre() + " ----");
    }
    protected void imprimirMensajeConfirmacion(){
        System.out.println(
            "Proveedor " + this.getNombre() + " confirmando todas las reservas pendientes."
        );
    }
    public abstract void obtenerDisponibilidad();
    public abstract void confirmarReserva();

    public String getNombre(){
        return nombre;
    }

    public int getIdProveedor(){
        return idProveedor;
    }
    public String getTipoServicio(){
        return tipoServicio;
    }
}
