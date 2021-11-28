package Model;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.math.BigDecimal;

public interface IGeometryObject {
    @Setter
    void setX(double x);
    @Getter
    double getX();

    @Setter
    void setY(double y);
    @Getter
    double getY();

    BigDecimal getArea();
}
