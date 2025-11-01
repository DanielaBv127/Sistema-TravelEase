package com.espol;

import java.util.Date;

public class Notificacion {
    private int idNotificacion;
    private String mensaje;
    private TipoNotificacion tipo;
    private Date fechaEnvio;

    public Notificacion(int idNotificacion, String mensaje, TipoNotificacion tipo) {
        this.idNotificacion = idNotificacion;
        this.mensaje = mensaje;
        this.tipo = tipo;
        this.fechaEnvio = new Date();
    }
    
    // Getters
    public int getIdNotificacion() { return idNotificacion; }
    public String getMensaje() { return mensaje; }
    public TipoNotificacion getTipo() { return tipo; }
    public Date getFechaEnvio() { return fechaEnvio; }
}

