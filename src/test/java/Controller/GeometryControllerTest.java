package Controller;

import Model.Point;
import Model.RegularPolygon;
import Proxy.GetterProxy;
import View.ConsoleGeometryView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import Exception.MyGeometryException;

import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GeometryControllerTest {

    GeometryController mockGC;

    Point mockPoint;

    RegularPolygon mockPolygon;

    ConsoleGeometryView cgv;

    @BeforeEach
    void SetUp() throws MyGeometryException {
        cgv = mock(ConsoleGeometryView.class);
        mockPoint = spy(new Point(3, 3));
        mockPolygon = spy(new RegularPolygon(3, 3, 4, 2));
        //mockGC = new GeometryController(mockPoint, mockPolygon, cgv);
    }

    @Test
    void getArea() {
        when(mockGC.getArea(mockPoint)).thenReturn(BigDecimal.valueOf(0.0));
        when(mockGC.getArea(mockPolygon)).thenReturn(BigDecimal.valueOf(4.0));

        mockGC.getArea(mockPoint);
        mockGC.getArea(mockPolygon);

        Mockito.verify(cgv, times(4)).showArea(any());

        assertEquals(mockGC.getArea(mockPoint), BigDecimal.valueOf(0.0));
        assertEquals(mockGC.getArea(mockPolygon), BigDecimal.valueOf(4.0));
    }

    //не работает потому что статический метод
//    @Test
//    void makeProxy() {
//        mockGC.makeProxy(mockPoint);
//        mockGC.makeProxy(mockPolygon);
//
//        assertEquals(mockGC.makeProxy(mockPoint), "COMPLETED");
//        assertEquals(mockGC.makeProxy(mockPolygon), "COMPLETED");
//    }

    @Test
    void reflect() {
        mockGC.reflect(mockPolygon);
        assertEquals(mockGC.reflect(mockPolygon), "COMPLETED");
    }
}