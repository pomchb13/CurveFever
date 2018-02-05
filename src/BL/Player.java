package BL;

import handler.Handler;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

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
    private int sizeOfPoint;

    private int testCounter;
    private Point testPoint;
    private Point testHeadPoint;

    // unbedingt wieder umtun ist nur wegen der präsentation
     private int pl;

     private Color col;
     private Random rand;

    public Player(int frameWidth, int frameHeight, Handler handler, double xCoord, double yCoord, int pl) {
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
        this.handler = handler;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.omega = 1.5;
        this.xAdd = 0;
        this.yAdd = 0;
        this.sizeOfPoint = 20;

        this.pl = pl;

        this.rand = new Random();

        this.col = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));

    }


    public void render(Graphics g) {
        g.setColor(col);
        if (testCounter > 50) {
            if (collisionAvoidence()) {
                if(pl == 1){
                    JOptionPane.showMessageDialog( null,"Player 2 won");
                }else{
                    JOptionPane.showMessageDialog( null,"Player 1 won");
                }
                //g.drawLine(testPoint.x, testPoint.y, testHeadPoint.x, testHeadPoint.y);
            }

        }
        g.fillOval((int) xCoord, (int) yCoord, sizeOfPoint, sizeOfPoint);
        testCounter++;
    }

    public void tick() {
        xCoord += xAdd;
        yCoord += yAdd;

        // true gibt an das man dort nicht durchfahren kann bei false wäre das loch
         handler.collidePoints.add(new CollidePoints(xCoord, yCoord, true));

        // Liste von Playern im Handler durchgehen und die collision abfragen über den CollidePoints


        if(pl == 1) {
            if (handler.isBoolRight1()) {
                degree = degree + omega > 360 ? 0 : degree + omega;
            }

            if (handler.isBoolLeft1()) {
                degree = degree - omega < 1 ? 360 : degree - omega;
            }
        }else if(pl == 2) {
            if (handler.isBoolRight2()) {
                degree = degree + omega > 360 ? 0 : degree + omega;
            }

            if (handler.isBoolLeft2()) {
                degree = degree - omega < 1 ? 360 : degree - omega;
            }
        }

        xAdd = Math.cos(Math.toRadians(degree));
        yAdd = Math.sin(Math.toRadians(degree));


        if (collisionAvoidence()) {

        }

        if (xAdd < 0 && xCoord < -10) {
            xCoord = frameWidth - 10;
        }
        if (xAdd > 0 && xCoord > frameWidth - 10) {
            xCoord = -10;
        }
        if (yAdd < 0 && yCoord < -10) {
            yCoord = frameHeight - 10;
        }
        if (yAdd > 0 && yCoord > frameHeight - 10) {
            yCoord = -10;
        }


    }


    public boolean collisionAvoidence() {
        CollidePoints head = handler.collidePoints.getLast();
        Point centerPointHead = new Point((int) (head.getX() + (sizeOfPoint) / 2), (int) (head.getY() + (sizeOfPoint) / 2));


        for (int i = 0; i < handler.collidePoints.size() - 50; i += 10) {
            Point p = new Point((int) ((handler.collidePoints.get(i).getX()) + sizeOfPoint / 2), (int) ((handler.collidePoints.get(i).getY()) + sizeOfPoint / 2));
            double y = Math.abs(centerPointHead.getY() - p.getY());
            double x = Math.abs(centerPointHead.getX() - p.getX());
            double c = Math.sqrt(Math.pow(y, 2) + Math.pow(x, 2));
            if ((c - sizeOfPoint) <= 0) {
                testPoint = p;
                testHeadPoint = centerPointHead;
                return true;
            }
        }

        return false;
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


}
