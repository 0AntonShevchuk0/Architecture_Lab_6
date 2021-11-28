package Model;

public class PolygonAdapter extends Circle {
    private RegularPolygon rp;

    public PolygonAdapter(double radius, RegularPolygon rp) {
        super(radius);
        this.rp = rp;
    }

    @Override
    public double getRadius() {
        return rp.getSideLength() / (2 * Math.sin(Math.PI / rp.getNumberOfVortexes()));
    }
}
