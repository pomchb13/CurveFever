package gui;

import javax.swing.*;
import java.awt.*;

public class GameGUI extends GUIObject{

    public GameGUI(int width, int height, String title, Game game) {
        super(width, height, title, game);

        Init();
    }

    public void Init(){
        // Erstellt das Frame mit dem Panel und den Compoments die es besitzen soll


        // Muss am Ende stehen
        _Frame.setVisible(true);
    }

}
