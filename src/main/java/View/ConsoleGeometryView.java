package View;

import Model.IGeometryObject;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleGeometryView implements IGeometryView {
    @Override
    public void showArea(IGeometryObject go) {
        System.out.println("\nArea for " + go.getClass().getSimpleName() + " = " + go.getArea());
    }

    @Override
    public void showProxy(IGeometryObject proxy) {
        System.out.println("\nPROXY:");
        System.out.println(proxy.getX() + "\n");
    }

    @Override
    public void showString(String str) {
        System.out.print(str);
    }

    @Override
    public void showException(Exception e) {
        System.out.println("\nEXCEPTION");
        System.out.println(e.getMessage());
    }

    @Override
    public void showEditorMenu() {
        System.out.println("EDITOR");
        System.out.println("1 - move point");
        System.out.println("2 - change polygon");
        System.out.println("3 - undo");
        System.out.println("4 - exit");
    }

    @Override
    public String getString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
