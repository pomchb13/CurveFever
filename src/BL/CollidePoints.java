package BL;

/**
 * Created by Christoph on 11.10.2017.
 */
public class CollidePoints {

    private double x;
    private double y;
    private boolean isFree;

    public CollidePoints(double x, double y, boolean isFree) {
        this.x = x;
        this.y = y;
        this.isFree = isFree;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
