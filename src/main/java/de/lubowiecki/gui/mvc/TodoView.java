package de.lubowiecki.gui.mvc;

import de.lubowiecki.gui.Todo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TodoView extends JFrame {

    final JTextField input;

    final DefaultListModel<Todo> listModel;

    final JList<Todo> todos;

    final JButton allBtn = new JButton("All");
    final JButton openBtn = new JButton("Open");
    final JButton doneBtn = new JButton("Done");

    public TodoView() {
        this(250, 500); // Verwendet den anderen Konstruktor
    }

    public TodoView(int width, int height) {
        // Konstruktor der Elternklasse wird automatisch ausgeführt...
        // wenn nicht expliziet this(...) oder super(...) aufgerufen wird
        // super();

        setTitle("Swing Todos");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(width, height));

        input = new JTextField();
        add(input, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();

        ScrollPane scrollBox = new ScrollPane();
        todos = new JList<>(listModel);
        scrollBox.add(todos); // Liste wird in eine Box mit Scrollbalken angezeigt
        add(scrollBox, BorderLayout.CENTER);

        JPanel box = new JPanel();// Container
        box.setBackground(Color.BLUE);
        box.setLayout(new GridLayout(0, 2));

        JPanel cell = new JPanel();
        cell.setBackground(Color.GREEN);
        cell.add(allBtn);
        box.add(cell);
        box.add(openBtn);
        box.add(doneBtn);
        add(box, BorderLayout.SOUTH);

        // JFrame (Hauptbühne) sichtbar machen
        setVisible(true);
        pack();
    }
}
