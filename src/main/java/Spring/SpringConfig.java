package Spring;

import Controller.GeometryController;
import Model.Point;
import Model.RegularPolygon;
import View.ConsoleGeometryView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
@PropertySource("classpath:objects.properties")
public class SpringConfig {

    @Bean
    public GeometryController geometryControllerBean() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );
        try {
            Point p = context.getBean("pointBean", Point.class);
            RegularPolygon rp = context.getBean("regularPolygonBean", RegularPolygon.class);
            ConsoleGeometryView cgv = context.getBean("consoleGeometryView", ConsoleGeometryView.class);
            context.close();
            return GeometryController.getController(p, rp, cgv);
        }
        catch (Exception e) {
            context.close();
            return null;
        }
    }

//    @Bean
//    public RegularPolygon regularPolygonBean() {
//        try {
//            return new RegularPolygon(3, 3, 3, 3);
//        }
//        catch (Exception e) {
//            System.out.println(e.toString());
//            return null;
//        }
//    }
}
