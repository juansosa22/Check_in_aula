Check in Aula

🎯 Descripción
Sistema simple para registrar inscripciones al inicio de clases.
Permite:
•	Registrar alumnos con Nombre, Documento y Curso.
•	Listar todas las inscripciones.
•	Buscar inscripciones por nombre o documento.
•	Mostrar un resumen básico de inscripciones por curso.
•	Cargar datos de ejemplo para pruebas rápidas.

________________________________________
📂 Estructura del proyecto
checkin-aula/
│
├─ src/
│   ├─ edu/cerp/checkin/
│   │   ├─ App.java
│   │   ├─ console/MainConsole.java
│   │   ├─ logic/SesionService.java
│   │   └─ model/Inscripcion.java
│
└─ README.md
________________________________________

💻 Código principal
App.java
package edu.cerp.checkin;

import edu.cerp.checkin.console.MainConsole;
import edu.cerp.checkin.logic.SesionService;

public class App {
    public static void main(String[] args) {
        boolean usarGui = false;
        for (String a : args) {
            if ("--gui".equalsIgnoreCase(a)) {
                usarGui = true;
                break;
            }
        }

        SesionService service = new SesionService();
        service.cargarDatosDemo();

        if (usarGui) {
            System.out.println("⚠ GUI no implementada. Corre sin --gui para usar consola.");
        } else {
            MainConsole.run(service);
        }
    }
}

________________________________________

MainConsole.java
package edu.cerp.checkin.console;

import edu.cerp.checkin.logic.SesionService;
import edu.cerp.checkin.model.Inscripcion;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class MainConsole {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void run(SesionService service) {
        Scanner sc = new Scanner(System.in);
        int op = -1;
        while (op != 0) {
            System.out.println("\n== Check-in Aula (Consola) ==");
            System.out.println("1) Registrar  2) Listar  3) Buscar  4) Resumen  0) Salir");
            System.out.print("> ");
            String s = sc.nextLine().trim();
            if (s.isEmpty()) continue;
            try { op = Integer.parseInt(s); } catch(Exception e){ op = -1; }

            switch (op) {
                case 1 -> {
                    System.out.print("Nombre: "); String n = sc.nextLine();
                    System.out.print("Documento: "); String d = sc.nextLine();
                    System.out.print("Curso [Prog 1/Prog 2/Base de Datos]: "); String c = sc.nextLine();
                    service.registrar(n,d,c);
                    System.out.println("✔ Registrado");
                }
                case 2 -> listar(service.listar());
                case 3 -> {
                    System.out.print("Buscar: "); String q = sc.nextLine();
                    listar(service.buscar(q));
                }
                case 4 -> System.out.println(service.resumen());
                case 0 -> System.out.println("Adiós");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private static void listar(List<Inscripcion> ls){
        System.out.println("Nombre | Documento | Curso | Hora");
        for (Inscripcion i: ls) {
            System.out.println(i.getNombre()+" | "+i.getDocumento()+" | "+i.getCurso()+" | "+i.getFechaHora().format(FORMAT));
        }
    }
}

________________________________________

SesionService.java
package edu.cerp.checkin.logic;

import edu.cerp.checkin.model.Inscripcion;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class SesionService {
    private final List<Inscripcion> inscripciones = new ArrayList<>();
    private final Set<String> cursosValidos = Set.of("Prog 1","Prog 2","Base de Datos");

    public void registrar(String nombre, String documento, String curso) {
        if (nombre == null || nombre.isBlank()) nombre = "(sin nombre)";
        if (documento == null) documento = "";
        if (curso == null || curso.isBlank() || !cursosValidos.contains(curso)) curso = "Prog 1";
        inscripciones.add(new Inscripcion(nombre.trim(), documento.trim(), curso.trim(), LocalDateTime.now()));
    }

    public List<Inscripcion> listar() {
        return Collections.unmodifiableList(inscripciones);
    }

    public List<Inscripcion> buscar(String q) {
        if (q == null || q.isBlank()) return listar();
        String s = q.toLowerCase();
        return inscripciones.stream()
                .filter(i -> i.getNombre().toLowerCase().contains(s) 
                          || i.getDocumento().toLowerCase().contains(s))
                .collect(Collectors.toList());
    }

    public String resumen() {
        Map<String, Long> porCurso = inscripciones.stream()
                .collect(Collectors.groupingBy(Inscripcion::getCurso, LinkedHashMap::new, Collectors.counting()));
        StringBuilder sb = new StringBuilder();
        sb.append("Total: ").append(inscripciones.size()).append("\nPor curso:\n");
        for (var e : porCurso.entrySet()) {
            sb.append(" - ").append(e.getKey()).append(": ").append(e.getValue()).append("\n");
        }
        return sb.toString();
    }

    public void cargarDatosDemo() {
        registrar("Ana Pérez", "51234567", "Prog 2");
        registrar("Luis Gómez", "49887766", "Prog 1");
        registrar("Camila Díaz", "53422110", "Base de Datos");
    }
}

________________________________________
Inscripcion.java
package edu.cerp.checkin.model;

import java.time.LocalDateTime;

public class Inscripcion {
    private final String nombre;
    private final String documento;
    private final String curso;
    private final LocalDateTime fechaHora;

    public Inscripcion(String nombre, String documento, String curso, LocalDateTime fechaHora) {
        this.nombre = nombre;
        this.documento = documento;
        this.curso = curso;
        this.fechaHora = fechaHora;
    }

    public String getNombre() { return nombre; }
    public String getDocumento() { return documento; }
    public String getCurso() { return curso; }
    public LocalDateTime getFechaHora() { return fechaHora; }
}

________________________________________

🛠️ Cómo ejecutar
1.	Compilar:
javac -d out src/edu/cerp/checkin/*.java src/edu/cerp/checkin/console/*.java src/edu/cerp/checkin/logic/*.java src/edu/cerp/checkin/model/*.java
2.	Ejecutar en consola:
java -cp out edu.cerp.checkin.App

