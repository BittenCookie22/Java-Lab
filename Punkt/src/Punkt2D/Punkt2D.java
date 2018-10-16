package Punkt2D;

import static java.lang.StrictMath.sqrt;

public class Punkt2D {
    double x;
    double y;

    public Punkt2D(double x,double y){
        this.x=x;
        this.y=y;
    }

    public void setX(double x){
        this.x=x;
    }

    public void setY(double y){
        this.y=y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return y;
    }

    public double distance(Punkt2D punkt){
        return sqrt(punkt.x*punkt.x+punkt.y*punkt.y);
    }
}
