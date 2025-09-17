package main.utils;

public class Vector2 {

    public double x;
    public double y;

    public double hypotenuse;

    public Vector2(double inst_x, double inst_y) {
        x = inst_x;
        y = inst_y;
        hypotenuse = calculate_hypotenuse();
    }

    // fundamental ah shit
    public double calculate_hypotenuse() {return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));}

    // methods
    public void AddVector(Vector2 vector) {
        x += vector.x;
        y += vector.y;
        hypotenuse = calculate_hypotenuse();
    }

    public void SubtractVector(Vector2 vector) {
        x -= vector.x;
        y -= vector.y;
        hypotenuse = calculate_hypotenuse();
    }
}
