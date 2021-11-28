package Annotation;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PolygonMethod {
    String annotationMassage() default "It is method of a regular polygon";
}
