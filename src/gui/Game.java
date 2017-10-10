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
    private double xCoord;
    private double yCoord;
    private double xAdd;
    private double yAdd;
    private int degree;
    private int frameHeight;
    private int frameWidth;
    public Game(){
        frameHeight = 600;
        frameWidth = 600;
        new Main(frameWidth,frameHeight,"Fuck Orsch",this);
        xCoord= 200;
        yCoord=200;
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode()==39)
                {
                    degree += 5;
                    if(degree > 360)
                    {
                        degree = 0;
                    }
                }else if(e.getKeyCode()==37)
                {
                    degree -= 5;
                    if(degree < 1 )
                    {
                        degree = 360;
                    }
                }
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
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
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

        if(xAdd < 0 && xCoord < -10) { xCoord = frameWidth -10; }
        if(xAdd > 0 && xCoord > frameWidth -10) { xCoord = -10; }
        if(yAdd < 0 && yCoord < -10) { yCoord = frameHeight -10; }
        if(yAdd > 0 && yCoord > frameHeight -10) { yCoord = -10; }

        xAdd = Math.cos(Math.toRadians(degree));
        yAdd = Math.sin(Math.toRadians(degree));
        xCoord += xAdd;
        yCoord += yAdd;
        g.fillOval((int)xCoord, (int)yCoord, 20, 20);
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

