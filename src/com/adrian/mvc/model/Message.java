package com.adrian.mvc.model;

import java.util.Date;

public class Message {

    private String emisor;
    private String[] participantes;
    private String mensaje;
    private Date fecha;
    private int idConversacion;

    public Message(String emisor, String[] participantes, String mensaje, Date fecha, int idConversacion) {
        this.emisor = emisor;
        this.participantes = participantes;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.idConversacion = idConversacion;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String[] getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String[] participantes) {
        this.participantes = participantes;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(int idConversacion) {
        this.idConversacion = idConversacion;
    }
}
