package BL;

import handler.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by chris on 10.10.2017.
 */
public class KeyInput extends KeyAdapter{

    private boolean[] keyPressed = new boolean[1024];

    public boolean isKeyPressed(int key) {
        return keyPressed[key];
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        keyPressed[key] = true;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        keyPressed[key] = false;
    }
}
