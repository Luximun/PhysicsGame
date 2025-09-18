package physics2d.fundamentals;

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

    public float lengthSquared() {
        return x*x + y*y;
    }

    public float dot(Vector2 vec) {
        return (this.x*vec.x + this.y*vec.y);
    }

    public void normalize()
    {
        float norm;

        norm = (float)
                (1.0/Math.sqrt(this.x*this.x + this.y*this.y));
        this.x *= norm;
        this.y *= norm;
    }

    // mathematical methods
    /// MULTIPLICATION
    public Vector2 multiplyBy(float multiplier) {
        return new Vector2(x * multiplier, y * multiplier);
    }
    public Vector2 multiplyBy(Vector2 vector) {
        return new Vector2(x * vector.x, y * vector.y);
    }

    ///  DIVISION
    public Vector2 divideBy(float divisor) {
        return new Vector2(x / divisor, y / divisor);
    }
    public Vector2 divideBy(Vector2 vector) {
        return new Vector2(x / vector.x, y / vector.y);
    }

    public Vector2 add(Vector2 vector) {
        return new Vector2(x + vector.x, y + vector.y);
    }

    public Vector2 subtract(Vector2 vector) {
        return new Vector2(x - vector.x, y - vector.y);
    }
}
