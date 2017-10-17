package BL;

import handler.Handler;

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
    private int sizeOfPoint;

    private int testCounter;
    private Point testPoint;
    private Point testHeadPoint;

    private LinkedList<CollidePoints> collidePointsList; // Gibt den Weg an anhand von Points wo man sich bewegt hat



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
        this.sizeOfPoint=20;
        this.collidePointsList = new LinkedList<>();
    }



    public void render(Graphics g)
    {
        g.setColor(Color.red);
        if(testCounter > 50) {
            if(collisionAvoidence()){
                g.setColor(Color.blue);
                //g.drawLine(testPoint.x, testPoint.y, testHeadPoint.x, testHeadPoint.y);
            }

        }
        g.fillOval((int)xCoord, (int)yCoord, sizeOfPoint, sizeOfPoint);
        testCounter++;
    }

    public void tick()
    {
        xCoord += xAdd;
        yCoord += yAdd;

        // true gibt an das man dort nicht durchfahren kann bei false wäre das loch
        collidePointsList.add(new CollidePoints(xCoord, yCoord, true));

        // Liste von Playern im Handler durchgehen und die collision abfragen über den CollidePoints


        if(handler.isBoolRight()) {
            degree = degree + omega > 360 ? 0 : degree + omega;
        }

        if(handler.isBoolLeft()) {
            degree = degree - omega < 1 ? 360 : degree - omega;
        }

        xAdd = Math.cos(Math.toRadians(degree));
        yAdd = Math.sin(Math.toRadians(degree));
        

        if(collisionAvoidence())
        {

        }

        if(xAdd < 0 && xCoord < -10) { xCoord = frameWidth -10; }
        if(xAdd > 0 && xCoord > frameWidth -10) { xCoord = -10; }
        if(yAdd < 0 && yCoord < -10) { yCoord = frameHeight -10; }
        if(yAdd > 0 && yCoord > frameHeight -10) { yCoord = -10; }



    }



    public boolean collisionAvoidence()
    {
        CollidePoints head = collidePointsList.getLast();
        Point centerPointHead= new Point((int)(head.getX()+(sizeOfPoint)/2),(int)(head.getY()+(sizeOfPoint)/2));


       for(int i=0;i<collidePointsList.size()-50;i+=10)
       {
         Point p = new Point((int)((collidePointsList.get(i).getX())+sizeOfPoint/2),(int)((collidePointsList.get(i).getY())+sizeOfPoint/2));
         double y = Math.abs(centerPointHead.getY()-p.getY());
         double x = Math.abs(centerPointHead.getX()-p.getX());
         double c = Math.sqrt(Math.pow(y,2)+Math.pow(x,2));
         if((c-sizeOfPoint)<=0)
         {
             testPoint = p;
             testHeadPoint = centerPointHead;
             System.out.println(p.getX() + ":" + p.getY());
             System.out.println("x --> "+x+" y--> "+y+" c-->"+c+" c-sizeOfPoint --> "+(c-sizeOfPoint));
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

    public LinkedList<CollidePoints> getS() {
        return collidePointsList;
    }

    public void setS(LinkedList<CollidePoints> s) {
        this.collidePointsList = s;
    }
}
