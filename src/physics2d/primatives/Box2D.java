package physics2d.primatives;

import main.utils.TITMath;
import physics2d.fundamentals.PhysicsObject;
import physics2d.fundamentals.Vector2;
import physics2d.rigidbody.Rigidbody2D;

public class Box2D extends PhysicsObject {
    private Vector2 size = new Vector2();
    private Vector2 halfSize = new Vector2();

    public Box2D() {
        this.halfSize = size.divideBy(2);
    }

    public Box2D(Vector2 min, Vector2 max) {
        this.size = max.subtract(min);
        this.halfSize = size.divideBy(2);
    }

    public Box2D(Vector2 center, Vector2 size, float rotation) {
        this.size = new Vector2(size);
        this.halfSize = size.divideBy(2);

        this.rigidbody.setPosition(center);
        this.rigidbody.setRotation(rotation);
    }

    public Vector2 getMin() {
        return this.rigidbody.getPosition().subtract(this.halfSize);
    }
    public Vector2 getMax() {
        return this.rigidbody.getPosition().add(this.halfSize);
    }

    public Vector2[] getVertices() {
        Vector2 min = getMin();
        Vector2 max = getMax();

        Vector2[] vertices = {
                new Vector2(min.x, min.y), new Vector2(max.x, min.y),
                new Vector2(max.x, max.y), new Vector2(min.x, max.y)
        };

        if (rigidbody.getRotation() != 0.0f) {
            for (Vector2 vert : vertices) {
                // rotates point (vec2) around center (vec2) by rotation (float - degrees)
                TITMath.rotate(vert, this.rigidbody.getRotation(), this.rigidbody.getPosition());
            }
        }
        return vertices;
    }

    @Override
    public Vector2 getSize() {
        return this.size;
    }
}
