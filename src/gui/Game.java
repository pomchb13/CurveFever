package gui;

import BL.KeyInput;
import BL.Player;
import handler.Handler;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Marco on 10.10.2017.
 */
public class Game extends Canvas implements Runnable{

    private boolean isRunning=false;
    private Thread thread;
    private Handler handler;
    private int frameHeight;
    private int frameWidth;





    public Game(){
        frameHeight = 600;
        frameWidth = 600;

        // new StartUI(frameWidth,frameHeight,"Fuck Orsch",this);

        this.handler = new Handler();

        this.addKeyListener(new KeyInput(handler));

        handler.setPlayer( new Player(frameWidth, frameHeight,handler, 200,200));

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

         handler.render(g);


        g.dispose();
        bs.show();
    }

    private void tick() {
        handler.tick();
    }

    public static void main(String[] args) {
        new Game();
    }





}

