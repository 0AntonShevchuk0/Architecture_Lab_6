package Model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GeometryDecorator implements IGeometryObject {
    private IGeometryObject go;

    public GeometryDecorator(IGeometryObject go) {
        this.go = go;
    }

    @Override
    public void setX(double x) {
        go.setX(Math.abs(x));
    }

    @Override
    public double getX() {
        return go.getX();
    }

    @Override
    public void setY(double y) {
        go.setY(Math.abs(y));
    }

    @Override
    public double getY() {
        return go.getY();
    }

    @Override
    public BigDecimal getArea() {
        return go.getArea().setScale(2, RoundingMode.HALF_UP);
    }
}
