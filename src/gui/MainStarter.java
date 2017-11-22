package gui;

import javax.swing.*;

public class MainStarter {

    private StartGUI _StartGUI;
    private GameGUI  _GameGUI;
    private Thread   _Thread;

    public static void main(String[] args) {
        new MainStarter();
    }

    public MainStarter(){
        this._StartGUI = new StartGUI(this);
    }

    public void onChange(JFrame frame, GUIS gu)
    {
        frame.dispose();

        switch (gu){
            case GAMEGUI: _GameGUI = new GameGUI(this);break;
            case STARTGUI: _StartGUI = new StartGUI(this);break;
        }

    }
}
