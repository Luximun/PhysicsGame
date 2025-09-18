package physics2d.primatives;

import physics2d.fundamentals.PhysicsObject;
import physics2d.fundamentals.Vector2;
import physics2d.rigidbody.Rigidbody2D;

public class Circle extends PhysicsObject {
    private float radius = 1.0f;

    public Circle() {}

    public Circle(float rad) {
        radius = rad;
    }

    public float getRadius() {return this.radius;}
    public Vector2 getCenter() {
        return rigidbody.getPosition();
    }

    @Override
    public Vector2 getSize() {
        return null;
    }
}
