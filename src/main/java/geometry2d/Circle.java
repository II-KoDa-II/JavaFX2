package geometry2d;

import Exceptions.AreaException;
import Exceptions.IncorrectValueException;

public class Circle implements Figure {
    private final double radius;

    public Circle(double radiusInitial) {
        if (radiusInitial <= 0) {
            throw new IncorrectValueException("Радиус введен некорректно");
        }
        radius = radiusInitial;
    }

    public double area() {
        double result = Math.PI * radius * radius;

        if (result <= 0) {
            throw new AreaException("Площадь меньше или равна нулю");
        }

        return result;
    }

    public String toString() {
        return String.valueOf(radius);
    }

    public double getRadius(){
        return radius;
    }
}
