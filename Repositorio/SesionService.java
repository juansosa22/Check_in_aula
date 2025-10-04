package edu.cerp.checkin.logic;

import edu.cerp.checkin.model.Inscripcion;
import java.util.*;

public class SesionService {
    private List<Inscripcion> inscripciones = new ArrayList<>();

    public void registrar(String nombre, String doc, String curso) {
        inscripciones.add(new Inscripcion(nombre, doc, curso));
    }

    public List<Inscripcion> listar() {
        return inscripciones;
    }

    public void cargarDatosDemo() {
        registrar("Ana","123","Programación 1");
        registrar("Luis","456","Base de Datos");
    }
}
