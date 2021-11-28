package Model;

import java.math.BigDecimal;

import Annotation.PolygonMethod;
import Annotation.PolygonParameter;
import Exception.MyGeometryException;

public class RegularPolygon extends Point implements Cloneable {
    @PolygonParameter(description = "number of vortexes in polygon. Need to be 3 or greater")
    private int numberOfVortexes;
    @PolygonParameter(description = "polygon side length. Need to be greater than 0")
    private double sideLength;

    public int getNumberOfVortexes() {
        return numberOfVortexes;
    }

    public void setNumberOfVortexes(int numberOfVortexes) {
        this.numberOfVortexes = numberOfVortexes;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    public RegularPolygon(double x, double y, int numberOfVortexes, double sideLength) throws MyGeometryException {
        super(x, y);
        if (numberOfVortexes < 3) {
            throw new MyGeometryException("Too low number of vortexes");
        }
        if (sideLength <= 0) {
            throw new MyGeometryException("Too low side length");
        }
        this.numberOfVortexes = numberOfVortexes;
        this.sideLength = sideLength;
    }

    public RegularPolygon(int numberOfVortexes, double sideLength) throws MyGeometryException {
        if (numberOfVortexes < 3) {
            throw new MyGeometryException("Too low number of vortexes");
        }
        if (sideLength <= 0) {
            throw new MyGeometryException("Too low side length");
        }
        this.numberOfVortexes = numberOfVortexes;
        this.sideLength = sideLength;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        RegularPolygon clone = null;
        try {
            clone = new RegularPolygon(this.x, this.y, this.numberOfVortexes, this.sideLength);
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
        return clone;
    }

    @Override
    public String toString() {
        return "RegularPolygon in (" + x +
                ", " + y +
                ") with number of vortexes = " + numberOfVortexes +
                " and side length = " + sideLength;
    }

    @Override
    @PolygonMethod(annotationMassage = "it is area calculating of a regular polygon")
    public BigDecimal getArea() {
        BigDecimal result = new BigDecimal(0);
        result = result.add(BigDecimal.valueOf(numberOfVortexes * Math.pow(sideLength, 2)));
        result = result.divide(BigDecimal.valueOf(4 * Math.tan(Math.PI/numberOfVortexes)), 6);
        return result;
    }
}
