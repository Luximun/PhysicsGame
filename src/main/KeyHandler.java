package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean ePressed;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // codes:
            // 8: Backspace, 9: Tab, 10: Enter, 12: Clear, 16: Shift, 17: Control, 18: Alt
            // 65: A, 65+ onwards

        if (keyCode == KeyEvent.VK_E) {
            ePressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_E) {
            ePressed = false;
        }
    }
}
