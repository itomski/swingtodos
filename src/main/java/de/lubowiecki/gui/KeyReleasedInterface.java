package de.lubowiecki.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public interface KeyReleasedInterface extends KeyListener {

    default void keyTyped(KeyEvent e) {}

    default void keyPressed(KeyEvent e) {}

    public void keyReleased(KeyEvent e);
}
