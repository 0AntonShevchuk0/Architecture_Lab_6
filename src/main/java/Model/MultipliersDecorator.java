package Model;

import java.math.BigDecimal;

public class MultipliersDecorator implements IGeometryObject {
    IGeometryObject go;

    public MultipliersDecorator(IGeometryObject go) {
        this.go = go;
    }

    @Override
    public void setX(double x) {
        go.setX(x);
    }

    @Override
    public double getX() {
        return multiply(go.getX(), 10);
    }

    @Override
    public void setY(double y) {
        go.setY(y);
    }

    @Override
    public double getY() {
        return multiply(go.getY(), 10);
    }

    @Override
    public BigDecimal getArea() {
        return BigDecimal.valueOf(multiply(go.getArea().doubleValue(), 10));
    }

    private double multiply(double value, double coef) {
        return value * coef;
    }
}
