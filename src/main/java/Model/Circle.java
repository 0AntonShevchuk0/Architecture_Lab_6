package Model;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.math.BigDecimal;

public class Circle extends Point implements IGeometryObject {
    private double radius;

    @Getter
    public double getRadius() {
        return radius;
    }

    @Setter
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public BigDecimal getArea() {
        return BigDecimal.valueOf(Math.PI * Math.pow(radius, 2));
    }
}
