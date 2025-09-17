package main.utils;

public class Vector2 {

    public double x;
    public double y;

    public double hypotenuse;

    public Vector2(double inst_x, double inst_y) {
        x = inst_x;
        y = inst_y;
        hypotenuse = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
