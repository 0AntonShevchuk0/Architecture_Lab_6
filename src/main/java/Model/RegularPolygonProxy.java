package Model;

public class RegularPolygonProxy {
    RegularPolygon rp;

    public RegularPolygonProxy(RegularPolygon rp) {
        this.rp = rp;
    }

    public double getDistanceToStart() {
        return Math.sqrt(Math.pow(rp.getX(), 2) + Math.pow(rp.getY(), 2));
    }

    public Point[] getVortexesCoords() {
        Point[] vortexes = new Point[rp.getNumberOfVortexes()];
        for (int i = 0; i < rp.getNumberOfVortexes(); i++) {
            double x = rp.getX() + rp.getSideLength() / (2 * Math.sin(Math.PI / rp.getNumberOfVortexes())) * Math.cos(360.0 / rp.getNumberOfVortexes() * i);
            double y = rp.getX() + rp.getSideLength() / (2 * Math.sin(Math.PI / rp.getNumberOfVortexes())) * Math.sin(360.0 / rp.getNumberOfVortexes() * i);
            vortexes[i] = new Point(x, y);
        }
        return vortexes;
    }

    public void reset() {
        rp.setX(0);
        rp.setY(0);
        rp.setNumberOfVortexes(3);
        rp.setSideLength(1);
    }
}
