package handler;

import BL.CollidePoints;
import BL.Player;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by chris on 10.10.2017.
 */
public class Handler {


    private boolean boolRight1 = false;
    private boolean boolLeft1 = false;

    private boolean boolRight2 = false;
    private boolean boolLeft2 = false;

    private Player[] player = new Player[2];

    public LinkedList<CollidePoints> collidePoints;

    private int index = 0;

    public Handler(){
        collidePoints = new LinkedList<>();
    }

    public void render(Graphics g) {
        for (int i = 0; i < 2; i++){
            player[i].render(g);
        }
    }


    public void tick() {
        for (int i = 0; i < 2; i++){
            player[i].tick();
        }
    }


    public Player getPlayer(int index) {
        return player[index];
    }


    public void setPlayer(Player player) {
        this.player[index++] = player;
    }




    public boolean isBoolRight1() {
        return boolRight1;
    }

    public void setBoolRight1(boolean boolRight) {
        this.boolRight1 = boolRight;
    }

    public boolean isBoolLeft1() {
        return boolLeft1;
    }

    public void setBoolLeft1(boolean boolLeft) {
        this.boolLeft1 = boolLeft;
    }

    public boolean isBoolRight2() {
        return boolRight2;
    }

    public void setBoolRight2(boolean boolRight) {
        this.boolRight2 = boolRight;
    }

    public boolean isBoolLeft2() {
        return boolLeft2;
    }

    public void setBoolLeft2(boolean boolLeft) {
        this.boolLeft2 = boolLeft;
    }
}
