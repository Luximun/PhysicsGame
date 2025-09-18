package physics2d.primatives;

import physics2d.fundamentals.Vector2;
import physics2d.rigidbody.Rigidbody2D;

public class Circle {
    private float radius = 1.0f;
    private Rigidbody2D rigidbody = new Rigidbody2D();

    public Circle() {}

    public Circle(float rad) {
        radius = rad;
    }

    public float getRadius() {return this.radius;}
    public Vector2 getCenter() {
        return rigidbody.getPosition();
    }
 }
