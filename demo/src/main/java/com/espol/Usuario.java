package com.espol;

public class Usuario {
    protected int id;
    protected String nombre;
    protected String correo;
    protected String contrasena;

    public Usuario(int id, String nombre, String correo, String contrasena){
        this.id= id;
        this.nombre= nombre;
        this.correo= correo;
        this.contrasena= contrasena;
    }

    public boolean iniciarSesion(String correo, String contrasena){
        boolean exito= this.correo.equals(correo) && this.contrasena.equals(contrasena);
        if (exito){
            System.out.println("Inicio de sesión exitoso: "+ this.nombre);
        }else{
            System.out.println("Error: Credenciales incorrectas.");
        }
        return exito;
    }

    public void cerrarSesion(){
        System.out.println("Cerrando sesión de "+ this.nombre);
    }

    public String getNombre(){
        return nombre;
    }

    public int getId(){
        return id;
    }
}
