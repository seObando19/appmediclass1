package com.example.appmediclass1;

public class Citas {
    private String nombre;
    private String fecha;
    private String hora;
    private String especialista;
    private int foto;

    public Citas()
    {

    }

    public Citas(String nombre, String fecha, String hora, String especialista, int foto) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.especialista = especialista;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEspecialista() {
        return especialista;
    }

    public void setEspecialista(String especialista) {
        this.especialista = especialista;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
