package Controller;

import Annotation.PolygonMethod;
import Model.*;
import Proxy.GetterProxy;
import View.ConsoleGeometryView;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.util.Scanner;

public class GeometryController implements IController {
    private IGeometryObject go;
    private ConsoleGeometryView cgv;
    private RegularPolygon rp;

    @Getter
    public IGeometryObject getGo() {
        return go;
    }

    @Setter
    public void setGo(IGeometryObject go) {
        this.go = go;
    }

    @Getter
    public ConsoleGeometryView getCgv() {
        return cgv;
    }

    @Setter
    public void setCgv(ConsoleGeometryView cgv) {
        this.cgv = cgv;
    }

    @Getter
    public RegularPolygon getRp() {
        return rp;
    }

    @Setter
    public void setRp(RegularPolygon rp) {
        this.rp = rp;
    }

    private GeometryController(IGeometryObject go, RegularPolygon rp, ConsoleGeometryView cgv) {
        this.go = go;
        this.cgv = cgv;
        this.rp = rp;
    }

    public static GeometryController getController(IGeometryObject go, RegularPolygon rp, ConsoleGeometryView cgv) {
        return new GeometryController(go, rp, cgv);
    }

    @Override
    public String makeProxy(IGeometryObject go) {
        cgv.showString("TEST PROXY\n");
        IGeometryObject proxy = (IGeometryObject) GetterProxy.newProxyInstance(go);
        cgv.showProxy(proxy);
        cgv.showString("MY PROXY\n");
        RegularPolygonProxy rpp = new RegularPolygonProxy(rp);
        rpp.reset();
        cgv.showString(rpp.getDistanceToStart() + "\n");
        for (Point p : rpp.getVortexesCoords()) {
            cgv.showString("(" + p.getX() + ", " + p.getY() + ")\n");
        }
        return "COMPLETED";
    }

    @Override
    public BigDecimal getArea(IGeometryObject go) {
        cgv.showArea(go);
        return go.getArea();
    }

    @Override
    public String reflect(RegularPolygon rp) {
        Class clazz = rp.getClass();
        cgv.showString("\nREFLECTION\n");
        //перебираем также всех родителей класса
        while (clazz.getSuperclass() != null) {
            try {
                cgv.showString("\nClass name: " + clazz.getName());
                for (Method method : clazz.getDeclaredMethods()) {
                    //вызываем методы, отмеченные созданной аннотацией
                    method.setAccessible(true);
                    if (method.isAnnotationPresent(PolygonMethod.class)) {
                        cgv.showString("\n" + method.getAnnotation(PolygonMethod.class).annotationMassage());
                        cgv.showString("\nInvoking method " + method.getName());
                        cgv.showString("\n\tResult: " + method.invoke(rp) + "\n");
                    }
                    //отображаем полный список методов с аннотациями, параметрами и типом возвращаемого значения
                    for (Annotation annotation : method.getDeclaredAnnotations()) {
                        cgv.showString("\n@" + annotation.annotationType().getName() + "\n");
                    }
                    cgv.showString(Modifier.toString(method.getModifiers()));
                    cgv.showString(" " + method.getReturnType().getName());
                    cgv.showString(" " + method.getName() + "(");
                    for (Parameter parameter : method.getParameters()) {
                        cgv.showString(parameter.getType().getName());
                        cgv.showString(" " + parameter.getName() + " ");
                    }
                    cgv.showString(")\n");
                }
                for (Field field : clazz.getDeclaredFields()) {
                    //отображаем полный список полей с аннотациями и модификаторами доступа
                    field.setAccessible(true);
                    cgv.showString("\n" + field.getDouble(rp));
                    for (Annotation annotation : field.getDeclaredAnnotations()) {
                        System.out.println("@" + annotation.annotationType().getName() + "\n");
                    }
                    cgv.showString(Modifier.toString(field.getModifiers()));
                    cgv.showString(" " + field.getType().getName());
                    cgv.showString(" " + field.getName() + "\n");
                }
            } catch (Exception e) {
                cgv.showException(e);
            }
            //переходим к родительскому классу
            clazz = clazz.getSuperclass();
        }
        return "COMPLETED";
    }

    @Override
    public String singleton() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );
        Plane plane1 = context.getBean("planeBean", Plane.class);
        Plane plane2 = context.getBean("planeBean", Plane.class);
        plane1.setColorCode(0x00FF00);
        try {
            cgv.showString(plane1.toString());
            cgv.showString(plane2.toString());
        }
        catch (Exception e) {
            cgv.showException(e);
        }
        context.close();
        return "COMPLETED";
    }

    @Override
    public String geometryEditor(Point p, RegularPolygon rp) {
        cgv.showEditorMenu();
        Scanner scanner = new Scanner(System.in);
        String input;
        CommandController cc = new CommandController(100, cgv);

        do {
            input = scanner.next();
            switch (input) {
                case "1": {
                    cc.execute(new MoveObjectCommand(p, cgv));
                }
                break;
                case "2": {
                    cc.execute(new ChangePolygonCommand(rp, cgv));
                }
                break;
                case "3": {
                    cc.getHistory().pop();
                }
                break;
            }
        }
        while (!input.equals("4"));
        return "COMPLETED";
    }

    @Override
    public int compareRadiuses(Circle c, RegularPolygon rp) {
        double circleRadius = c.getRadius();
        PolygonAdapter pa = new PolygonAdapter(0, rp);
        double polygonRadius = pa.getRadius();
        return (int)(circleRadius - polygonRadius);
    }

    @Override
    public String decorator() {
        cgv.showString("DECORATOR\n");
        MultipliersDecorator md;
        try {
            md = new MultipliersDecorator(new GeometryDecorator(new Point(0, 0)));
            cgv.showString(md.getArea() + "\n");
            md.setX(1);
            cgv.showString(md.getX() + "\n");
        }
        catch (Exception e) {
            cgv.showException(e);
        }
        return "COMPLETED";
    }
}
