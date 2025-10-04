package edu.cerp.checkin.ui;

import edu.cerp.checkin.logic.SesionService;
import javax.swing.*;
import java.awt.*;

public class CheckInGUI {
    public static void mostrar(SesionService service) {
        JFrame f = new JFrame("Registro Aula");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400,300);

        JTextField txtNom = new JTextField(10);
        JTextField txtDoc = new JTextField(10);
        JComboBox<String> cbCurso = new JComboBox<>(new String[]{"Prog 1","Prog 2","BD"});
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> lista = new JList<>(model);

        JButton btn = new JButton("Registrar");
        btn.addActionListener(e -> {
            service.registrar(txtNom.getText(), txtDoc.getText(), cbCurso.getSelectedItem().toString());
            model.clear();
            service.listar().forEach(i -> model.addElement(i.toString()));
            txtNom.setText("");
            txtDoc.setText("");
        });

        JPanel p = new JPanel();
        p.add(new JLabel("Nombre")); p.add(txtNom);
        p.add(new JLabel("Doc")); p.add(txtDoc);
        p.add(new JLabel("Curso")); p.add(cbCurso);
        p.add(btn);

        f.add(p, BorderLayout.NORTH);
        f.add(new JScrollPane(lista), BorderLayout.CENTER);
        f.setVisible(true);
    }
}
