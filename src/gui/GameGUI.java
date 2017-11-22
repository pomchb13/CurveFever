package gui;

import javax.swing.*;
import java.awt.*;

public class GameGUI {

    private MainStarter _MainStarter;

    public Frame _Frame;

    public GameGUI(MainStarter mainStarter){
        System.out.println("GAME");
        this._MainStarter = mainStarter;
        this._Frame = new Frame();
    }

    public void dispose(){
        this._Frame.dispose();
    }

}
