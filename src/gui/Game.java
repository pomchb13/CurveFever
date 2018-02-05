package gui;

import BL.KeyInput;
import BL.Player;
import handler.Handler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

/**
 * Created by Marco on 10.10.2017.
 */
public class Game extends Canvas implements Runnable {

    // Game Handler
    private Handler _Handler;
    //

    // Window Frame Size
    private int _WindowHeight;
    private int _WindowWidth;
    //

    // Current Frame
    private GUIObject _CurrentFrame;
    //

    // Game Thread
    private Thread _Thread;
    //

    private KeyInput keyinput;

    // Game Status
    private GAMEENUMS _CurrentStatus;
    //

    public enum GAMEENUMS {
        STOP(),
        START(),
        END();
    }

    public static void main(String[] args) {
        new Game();
    }


    public void onChange(JFrame frame, GUIS gu) {
        frame.dispose();

        switch (gu) {
            case GAMEGUI:
                _CurrentFrame = new GameGUI(_WindowWidth, _WindowHeight, "Game", this);
                StartGame();
                break;
            case STARTGUI:
                _CurrentFrame = new StartGUI(_WindowWidth, _WindowHeight, "Menu", this);
                break;
        }

    }

    public Game() {
        System.out.println("Hallo Game Konstruktor");

        Init();
    }

    private void Init() {

        // Window Frame size
        this._WindowHeight = 900;
        this._WindowWidth = 700;

        // Handler
        this._Handler = new Handler();
        _Handler.setPlayer(new Player(_WindowWidth, _WindowHeight, _Handler, 200, 200,1));
        _Handler.setPlayer(new Player(_WindowWidth, _WindowHeight, _Handler, 400, 400,2));

        // GUI
        this._CurrentFrame = new StartGUI(_WindowWidth, _WindowHeight, "Menu", this);

        // Listeners

        keyinput = new KeyInput();
        this.addKeyListener(keyinput);

        // Status
        this._CurrentStatus = GAMEENUMS.STOP;

        // Thread
        this._Thread = new Thread(this);
    }


    private void StartGame() {
        this._CurrentStatus = GAMEENUMS.START;

        _Thread.start();
    }

    private void StopGame() {
        _CurrentStatus = GAMEENUMS.STOP;
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
        while (_CurrentStatus == GAMEENUMS.START ? true : false) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;


            while (delta >= 1) {
                tick();
                // updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
                // updates = 0;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        switch (_CurrentStatus) {
            case STOP:
                try {
                    _Thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        this.setBackground(Color.BLACK);
        Graphics g = bs.getDrawGraphics();

        _Handler.render(g);


        g.dispose();
        bs.show();
    }

    private void tick() {
        if (keyinput.isKeyPressed(KeyEvent.VK_A)) {
            _Handler.setBoolLeft1(true);
        }else{
            _Handler.setBoolLeft1(false);
        }

        if (keyinput.isKeyPressed(KeyEvent.VK_D)) {
            _Handler.setBoolRight1(true);
        }else{
            _Handler.setBoolRight1(false);
        }

        if (keyinput.isKeyPressed(KeyEvent.VK_LEFT)) {
            _Handler.setBoolLeft2(true);
        }else{
            _Handler.setBoolLeft2(false);
        }

        if (keyinput.isKeyPressed(KeyEvent.VK_RIGHT)) {
            _Handler.setBoolRight2(true);
        }
        else{
            _Handler.setBoolRight2(false);
        }

        _Handler.tick();
    }


}

