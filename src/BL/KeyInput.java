package BL;

import handler.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by chris on 10.10.2017.
 */
public class KeyInput extends KeyAdapter{

    private Handler handler;

    public KeyInput(Handler handler){
        this.handler=handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key=e.getKeyCode();
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            handler.setBoolRight(true);
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            handler.setBoolLeft(true);

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key=e.getKeyCode();
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
           handler.setBoolRight(false);
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            handler.setBoolLeft(false);
        }

    }
}
