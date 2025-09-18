package physics2d.rigidbody;

import physics2d.fundamentals.PhysicsObject;
import physics2d.fundamentals.Vector2;

public class Rigidbody2D {
    private Vector2 position = new Vector2();
    private float rotation = 0.0f;

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = new Vector2(position);
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
