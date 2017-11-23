package gui;

import javax.swing.*;
import java.awt.*;

public abstract class GUIObject {

    protected JFrame _Frame;
    protected Game _Game;

    public GUIObject(int width, int height, String title, Game game){
        this._Game = game;

        this._Frame = new JFrame(title);
        this._Frame.setPreferredSize(new Dimension(width, height));
        this._Frame.setMaximumSize(new Dimension(width, height));
        this._Frame.setMinimumSize(new Dimension(width, height));

        this._Frame.add(game);
        this._Frame.setResizable(false);
        this._Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this._Frame.setLocationRelativeTo(null);
    }

}
