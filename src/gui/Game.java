package gui;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

/**
 * Created by Marco on 10.10.2017.
 */
public class Game extends Canvas implements Runnable{

    private boolean isRunning=false;
    private Thread thread;
    private int xCoord;
    private int yCoord;
    public Game(){
        new Main(1000,1000,"Fuck Orsch",this);
        xCoord= 200;
        yCoord=200;
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode()==39)
                {
                    xCoord+=100;
                }else if(e.getKeyCode()==37)yCoord+=100;
                System.out.println(e.getKeyCode());
            }




        });

        start();
    }






    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop(){
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountTicks = 60.0;
        double ns = 1000000000 / amountTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;


            while(delta >= 1){
                tick();
                // updates++;
                delta--;
            }
            render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer+= 1000;
                frames = 0;
                // updates = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null)
        {
            this.createBufferStrategy(3);
            return;
        }
        this.setBackground(Color.BLACK);
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.red);
        g.fillOval(xCoord++, yCoord, 100, 100);

        g.dispose();
        bs.show();
    }

    private void tick() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public static void main(String[] args) {
        new Game();
    }
}

