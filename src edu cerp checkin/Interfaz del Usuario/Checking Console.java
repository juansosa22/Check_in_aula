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
            // Aquí después se agregará la GUI
            System.out.println("⚠ GUI no implementada. Corre sin --gui para usar consola.");
        } else {
            MainConsole.run(service);
        }
    }
}
