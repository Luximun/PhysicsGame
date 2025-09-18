package main.utils;

public class Vector2 {

    public float x;
    public float y;

//    public float hypotenuse;

    //**************//
    // CONSTRUCTORS //
    //**************//

    public Vector2(float inst_x, float inst_y) {
        x = inst_x;
        y = inst_y;
        /*hypotenuse = calculate_hypotenuse();*/
    }

    public Vector2(Vector2 vector) {
        x = vector.x;
        y = vector.y;
        /*hypotenuse = calculate_hypotenuse();*/
    }

    public Vector2(float size) {x = size; y = size; /*hypotenuse = calculate_hypotenuse();*/}

    public Vector2() {x=0f; y=0f; /*hypotenuse=0f;*/}

    //*********//
    // METHODS //
    //*********//

//    public float calculate_hypotenuse() {return Math.sqrt(Math.pow((double) x, 2) + Math.pow((double) y, 2));}

    // mathematical methods
    /// MULTIPLICATION
    public Vector2 MultiplyBy(float multiplier) {
        return new Vector2(x * multiplier, y * multiplier);
    }
    public Vector2 MultiplyBy(Vector2 vector) {
        return new Vector2(x * vector.x, y * vector.y);
    }

    ///  DIVISION
    public Vector2 DivideBy(float divisor) {
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
