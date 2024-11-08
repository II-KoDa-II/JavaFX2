package geometry2d;

import Exceptions.AreaException;
import Exceptions.IncorrectValueException;

public class Rectangle implements Figure {
    private final double length;
    private final double height;

    public Rectangle(double lengthInitial, double heightInitial) {
        if (lengthInitial <= 0 || heightInitial <= 0) {
            throw new IncorrectValueException("Стороны введены некорректно");
        }
        length = lengthInitial;
        height = heightInitial;
    }

    public double area() {
        double result = length * height;

        if (result <= 0) {
            throw new AreaException("Площадь меньше или равна нулю");
        }

        return result;
    }

    public String toString() {
        return String.valueOf((length + height) * 2);
    }

    public double getLength(){
        return length;
    }

    public double getHeight(){
        return height;
    }
}