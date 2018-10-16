package Punkt2D;

import static java.lang.StrictMath.sqrt;

public class Punkt3D extends  Punkt2D {
    double z;

    public Punkt3D(Punkt2D punkt,double z){
        super(punkt.x,punkt.y);
        this.z=z;
    }

    public void setZ(double z){
        this.z=z;
    }

    public double getZ(){
        return z;
    }

    public double distance(Punkt3D punkt) {
        return sqrt(punkt.x*punkt.x+punkt.y*punkt.y+punkt.z*punkt.z);
    }
}
