package Model;

import jdk.nashorn.internal.objects.annotations.Getter;

public class Plane extends Point {
    //private static Plane object;

    private int colorCode;

    @Getter
    public int getColorCode() {
        return colorCode;
    }

    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }

//    private Plane(int colorCode) {
//        this.colorCode = colorCode;
//    }
//
//    private Plane(double x, double y, int colorCode) {
//        super(x, y);
//        this.colorCode = colorCode;
//    }
//
//    public static Plane getObject(int colorCode) {
//        Plane plane = null;
//        if (object == null) {
//            plane = new Plane(colorCode);
//        }
//        object = plane;
//        return object;
//    }

//    public static Plane getObject(double x, double y, int colorCode) {
//        Plane plane = null;
//        if (object == null) {
//            plane = new Plane(x, y, colorCode);
//        }
//        object = plane;
//        return object;
//    }


    @Override
    public String toString() {
        return "Plane{" +
                "colorCode=" + colorCode +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public void doInit() {
        System.out.println("Constructing plane...");
    }

    public void doDestroy() {
        System.out.println("Destroying plane...");
    }

}
