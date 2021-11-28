package Model;

import Annotation.PolygonMethod;
import Annotation.PolygonParameter;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigDecimal;

@Component("pointBean")
@Scope("prototype")
public class Point implements IGeometryObject {
    @PolygonParameter
    protected double x;
    @PolygonParameter
    protected double y;

    @Override
    @Getter
    public double getX() {
        return x;
    }

    @Override
    @Setter
    public void setX(double x) {
        this.x = x;
    }

    @Override
    @Getter
    public double getY() {
        return y;
    }

    @Override
    @Setter
    public void setY(double y) {
        this.y = y;
    }

    public Point() {

    }

    @Autowired
    public Point(@Value("10.0") double x, @Value("10.0") double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    @PolygonMethod
    public BigDecimal getArea() {
        return BigDecimal.valueOf(0);
    }

    @PostConstruct
    public void doInit() {
        System.out.println("Constructing point...");
    }

    @PreDestroy
    public void doDestroy() {
        System.out.println("Destroying point...");
    }
}
