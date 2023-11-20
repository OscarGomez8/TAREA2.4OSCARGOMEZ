package com.example.tarea24oscargomez.configuracion;

public class objetoFirmas {
    private String id;
    private byte[] firma;
    private String nombre;

    public objetoFirmas(String id, byte[] firma, String nombre) {
        this.id = id;
        this.firma = firma;
        this.nombre = nombre;
    }

    public objetoFirmas() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getFirma() {
        return firma;
    }

    public void setFirma(byte[] firma) {
        this.firma = firma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}


