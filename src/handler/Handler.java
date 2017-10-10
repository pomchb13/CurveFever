package handler;

import BL.Player;

import java.awt.*;

/**
 * Created by chris on 10.10.2017.
 */
public class Handler {
    private Player player;
    private boolean boolRight=false;
    private boolean boolLeft=false;



    public void render(Graphics g)
    {
        player.render(g);
    }



    public void tick()
    {
        player.tick();
    }





    public Player getPlayer() {
        return player;
    }



    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isBoolRight() {
        return boolRight;
    }

    public void setBoolRight(boolean boolRight) {
        this.boolRight = boolRight;
    }

    public boolean isBoolLeft() {
        return boolLeft;
    }

    public void setBoolLeft(boolean boolLeft) {
        this.boolLeft = boolLeft;
    }
}
