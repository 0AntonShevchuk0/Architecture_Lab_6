package Controller;

import Model.IGeometryObject;
import View.IGeometryView;

public class MoveObjectCommand implements ICommand {
    private IGeometryObject point;

    private IGeometryView view;

    private double changedX;
    private double changedY;

    public MoveObjectCommand(IGeometryObject point, IGeometryView view) {
        this.point = point;
        this.view = view;
        changedX = 0;
        changedY = 0;
    }

    @Override
    public void execute() {
        view.showString("Where do you want to move object\n");
        view.showString("x = \n");
        String xValue = view.getString();
        view.showString("y = \n");
        String yValue = view.getString();

        try {
            changedX = Double.parseDouble(xValue);
            changedY = Double.parseDouble(yValue);
        }
        catch (Exception e) {
            view.showException(e);
        }
        point.setX(point.getX() +changedX);
        point.setY(point.getY() + changedY);
        view.showString("Point moved and now at (" + point.getX() + ", " + point.getY() + ")\n");
    }

    @Override
    public void undo() {
        point.setX(point.getX() - changedX);
        point.setY(point.getY() - changedY);
        view.showString("Point returned to (" + point.getX() + ",  " + point.getY() + ")\n");
    }
}
