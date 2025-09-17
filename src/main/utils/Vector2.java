package main.utils;

public class Vector2 {

    public double x;
    public double y;

    public double hypotenuse;

    //**************//
    // CONSTRUCTORS //
    //**************//

    public Vector2(double inst_x, double inst_y) {
        x = inst_x;
        y = inst_y;
        hypotenuse = calculate_hypotenuse();
    }

    public Vector2(double size) {x = size; y = size; hypotenuse = calculate_hypotenuse();}

    public Vector2() {x=0; y=0; hypotenuse=0;}

    //*********//
    // METHODS //
    //*********//

    public double calculate_hypotenuse() {return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));}

    // mathematical methods
    /// MULTIPLICATION
    public Vector2 MultiplyBy(double multiplier) {
        return new Vector2(x * multiplier, y * multiplier);
    }
    public Vector2 MultiplyBy(Vector2 vector) {
        return new Vector2(x * vector.x, y * vector.y);
    }

    ///  DIVISION
    public Vector2 DivideBy(double divisor) {
        return new Vector2(x / divisor, y / divisor);
    }
    public Vector2 DivideBy(Vector2 vector) {
        return new Vector2(x / vector.x, y / vector.y);
    }

    public Vector2 add(Vector2 vector) {
        return new Vector2(x + vector.x, y + vector.y);
    }

    public Vector2 subtract(Vector2 vector) {
        return new Vector2(x - vector.x, y - vector.y);
    }
}
