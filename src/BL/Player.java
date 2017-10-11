package BL;

import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import com.sun.xml.internal.bind.v2.runtime.Coordinator;
import handler.Handler;
import sun.awt.image.ImageWatched;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by chris on 10.10.2017.
 */
public class Player {

    private double xCoord;
    private double yCoord;
    private double xAdd;
    private double yAdd;
    private int frameWidth;
    private int frameHeight;
    private double degree;
    private double omega;
    private Handler handler;

    private LinkedList<CollidePoints> s; // Gibt den Weg an anhand von Points wo man sich bewegt hat



    public Player(int frameWidth, int frameHeight, Handler handler,double xCoord,double yCoord)
    {
        this.frameHeight=frameHeight;
        this.frameWidth=frameWidth;
        this.handler=handler;
        this.xCoord=xCoord;
        this.yCoord=yCoord;
        this.omega = 1.5;
        this.xAdd=0;
        this.yAdd=0;
        this.s = new LinkedList<>();
    }



    public void render(Graphics g)
    {
        g.setColor(Color.red);
        g.fillOval((int)xCoord, (int)yCoord, 20, 20);
    }

    public void tick()
    {
        xCoord += xAdd;
        yCoord += yAdd;

        // true gibt an das man dort nicht durchfahren kann bei false wäre das loch
        s.add(new CollidePoints(xCoord, yCoord, true));

        // Liste von Playern im Handler durchgehen und die collision abfragen über den CollidePoints

        if(handler.isBoolRight()) {
            degree = degree + omega > 360 ? 0 : degree + omega;
        }

        if(handler.isBoolLeft()) {
            degree = degree - omega < 1 ? 360 : degree - omega;
        }

        xAdd = Math.cos(Math.toRadians(degree));
        yAdd = Math.sin(Math.toRadians(degree));

        if(xAdd < 0 && xCoord < -10) { xCoord = frameWidth -10; }
        if(xAdd > 0 && xCoord > frameWidth -10) { xCoord = -10; }
        if(yAdd < 0 && yCoord < -10) { yCoord = frameHeight -10; }
        if(yAdd > 0 && yCoord > frameHeight -10) { yCoord = -10; }


    }




    public double getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public double getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public double getxAdd() {
        return xAdd;
    }

    public void setxAdd(int xAdd) {
        this.xAdd = xAdd;
    }

    public double getyAdd() {
        return yAdd;
    }

    public void setyAdd(int yAdd) {
        this.yAdd = yAdd;
    }

    public LinkedList<CollidePoints> getS() {
        return s;
    }

    public void setS(LinkedList<CollidePoints> s) {
        this.s = s;
    }
}