package de.lubowiecki.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Collections;
import java.util.Enumeration;

public class App extends JFrame {

    // Hier wird der Controller und die View in einer Klasse verbaut

    private JTextField input;

    private DefaultListModel<Todo> listModel;

    private JList<Todo> todos;

    public static void main(String[] args) {
        new App().start();
    }

    private void start() {

        // Fenstertitel festlegen
        setTitle("Swing Todos");

        // Layout-Manager festlegen
        // FlowLayout: horizontale Ausrichtung, bis kein Platz mehr in der gleichen Zeile verfügbar ist.
        // Dann Zeilenumbruch
        // BorderLayout: Elemente werden nach Himmelsrichtungen verteilt
        // GridLayout: Raster mit Spalten und Zeilen
        // CardLayout: Übereinander liegende Layer, die nach vorne geholt werden können

        setLayout(new BorderLayout());

        // Programm verlassen, wenn das Fenster geschlossen wird
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Fenstergröße festlegen
        setPreferredSize(new Dimension(260, 100));

        input = new JTextField();
        add(input, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();

        // Todo ist ein Model
        // JList ist eine View
        // DefaultListModel ist ein ViewModel

        todos = new JList<>(listModel);
        add(todos, BorderLayout.CENTER);

        // Events verarbeiten

        /*
        // Umsetzung mit KeyListener
        input.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                return;
            }

            @Override
            public void keyPressed(KeyEvent e) {
                return;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String title = input.getText();
                    listModel.addElement(new Todo(title));
                    input.setText("");
                }
            }
        });
        */

        // Hier wird eine Klasse ohne Namen instanziert, die von KeyAdapter erbt
        // Diese Klasse überschreibt die keyReleased-Methode von KeyAdapter
        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String title = input.getText();
                    listModel.addElement(new Todo(title));
                    input.setText("");
                }
            }
        });

        /*
        // Das Lambda implementiert die abstrakte Methode (keyReleased) des Interface
        KeyReleasedInterface keyAction = e -> {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                String title = input.getText();
                listModel.addElement(new Todo(title));
                input.setText("");
            }
        };
        input.addKeyListener(keyAction); // keyAction instanceof KeyListener
        input.addKeyListener(e -> { // ERROR: KeyListener ist kein FunctionalInterface
            //...
        });
        */

        // Bei SPACE und ENTF reagieren
        todos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                switch(e.getKeyCode()) {
                    case KeyEvent.VK_SPACE -> {
                        Todo selected = todos.getSelectedValue();
                        int idx = todos.getSelectedIndex(); // Index der selektierten Zeile abfragen
                        selected.toggleDone();
                        updateList(); // Anzeige aktuallisieren
                        todos.setSelectedIndex(idx); // Selektierung wieder auf die ursrüngleich Zeile setzen
                    }
                    case KeyEvent.VK_BACK_SPACE -> {
                        // Liefert das eine Referenz auf das selektierte Todo-Objekt
                        Todo selected = todos.getSelectedValue();
                        // Todo aus dem ViewModel entfernen
                        listModel.removeElement(selected);
                    }
                }
            }
        });

        // JFrame (Hauptbühne) sichtbar machen
        setVisible(true);
        pack();
    }

    private void updateList() {
        List<Todo> data = Collections.list(listModel.elements());
        listModel.clear();
        listModel.addAll(data);
    }
}