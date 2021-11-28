package Main;

import Controller.GeometryController;
import Model.Circle;
import Model.Point;
import Model.RegularPolygon;
import Spring.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );
        ClassPathXmlApplicationContext context2 = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );
        GeometryController controller = context.getBean("geometryControllerBean", GeometryController.class);

        Point p = context2.getBean("pointBean", Point.class);
        RegularPolygon rp = context2.getBean("regularPolygonBean", RegularPolygon.class);
        Circle c = context2.getBean("circleBean", Circle.class);

        System.out.println(controller.getArea(rp));
        System.out.println(controller.makeProxy(p));
        System.out.println(controller.reflect(rp));
        System.out.println(controller.singleton());
        System.out.println(controller.geometryEditor(p, rp));
        System.out.println(controller.compareRadiuses(c, rp));
        System.out.println(controller.decorator());

        context.close();
    }
}

