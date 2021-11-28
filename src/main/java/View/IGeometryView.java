package View;

import Model.IGeometryObject;

public interface IGeometryView {
    void showArea(IGeometryObject go);

    void showProxy(IGeometryObject proxy);

    void showException(Exception e);

    void showString(String str);

    void showEditorMenu();

    String getString();
}
