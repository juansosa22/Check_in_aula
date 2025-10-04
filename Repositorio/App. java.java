package edu.cerp.checkin;

import edu.cerp.checkin.logic.SesionService;
import edu.cerp.checkin.ui.CheckInGUI;

public class App {
    public static void main(String[] args) {
        SesionService service = new SesionService();
        boolean gui = args.length > 0 && args[0].equalsIgnoreCase("--gui");

        if (gui) {
            CheckInGUI.mostrar(service);
        } else {
            service.cargarDatosDemo();
            service.listar().forEach(System.out::println);
        }
    }
}

