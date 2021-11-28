package Controller;

import Model.RegularPolygon;
import View.IGeometryView;

public class ChangePolygonCommand implements ICommand {
    private RegularPolygon rp;

    private IGeometryView view;

    private int changedNumberOfVortexes;
    private double changedSidesLength;

    public ChangePolygonCommand(RegularPolygon rp, IGeometryView view) {
        this.rp = rp;
        this.view = view;
        changedNumberOfVortexes = 0;
        changedSidesLength = 0;
    }

    @Override
    public void execute() {
        view.showString("How do you want to change polygon\n");
        view.showString("number of vortexes = \n");
        String numberValue = view.getString();
        view.showString("side length = \n");
        String lengthValue = view.getString();

        try {
            changedNumberOfVortexes = Integer.parseInt(numberValue);
            changedSidesLength = Double.parseDouble(lengthValue);
        }
        catch (Exception e) {
            view.showException(e);
        }
        rp.setNumberOfVortexes(rp.getNumberOfVortexes() + changedNumberOfVortexes);
        rp.setSideLength(rp.getSideLength() + changedSidesLength);
        view.showString("Polygon changed and now has " + rp.getNumberOfVortexes() + " vortexes and " + rp.getSideLength() + " side length\n");
    }

    @Override
    public void undo() {
        rp.setNumberOfVortexes(rp.getNumberOfVortexes() - changedNumberOfVortexes);
        rp.setSideLength(rp.getSideLength() - changedSidesLength);

        view.showString("Polygon returned to " + rp.getNumberOfVortexes() + " number of vortexes and " + rp.getSideLength());
    }
}
