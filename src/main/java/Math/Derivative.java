package Math;

public class Derivative {
    double dx;
    double dvx;
    double dy;
    double dvy;
    public Derivative(double dx, double dy, double dvx, double dvy){
        this.dx = dx;
        this.dvx = dvx;
        this.dy = dy;
        this.dvy = dvy;
    }
    public Derivative divideDer(double divider){
        dx = dx / divider;
        dy = dy / divider;
        dvx = dvx / divider;
        dvy = dvy / divider;
        return new Derivative(dx, dy, dvx, dvy);
    }    
}
