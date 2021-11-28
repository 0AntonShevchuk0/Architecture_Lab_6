package Controller;

import Model.Circle;
import Model.IGeometryObject;
import Model.Point;
import Model.RegularPolygon;

import java.math.BigDecimal;

public interface IController {
    String makeProxy(IGeometryObject go);

    BigDecimal getArea(IGeometryObject go);

    String reflect(RegularPolygon rp);

    String singleton();

    String geometryEditor(Point p, RegularPolygon rp);

    int compareRadiuses(Circle c, RegularPolygon rp);

    String decorator();
}
