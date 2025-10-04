package edu.cerp.checkin.model;

import java.time.LocalDateTime;

public class Inscripcion {
    private String nombre;
    private String documento;
    private String curso;
    private LocalDateTime fechaHora;

    public Inscripcion(String n, String d, String c) {
        this.nombre = n;
        this.documento = d;
        this.curso = c;
        this.fechaHora = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return nombre+" | "+documento+" | "+curso+" | "+fechaHora;
    }
}
