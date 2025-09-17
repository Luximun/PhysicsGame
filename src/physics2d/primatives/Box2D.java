package physics2d.primatives;

import main.utils.Vector2;
import physics2d.rigidbody.Rigidbody2D;

public class Box2D {
    private Vector2 size = new Vector2();
    private Vector2 halfSize = new Vector2();
    private Rigidbody2D rigidbody = null;

    public Box2D() {
        this.halfSize = size.DivideBy(2);
    }

    public Box2D(Vector2 min, Vector2 max) {
        this.size = max.subtract(min);
        this.halfSize = size.DivideBy(2);
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
                new Vector2(min.x, min.y), new Vector2(min.x, max.y),
                new Vector2(max.x, min.y), new Vector2(max.x, max.y)
        };

        if (rigidbody.getRotation() != 0.0f) {
            for (Vector2 vert : vertices) {
                // TODO: implement this
                // rotates point (vec2) around center (vec2) by rotation (float - degrees)
                //TITMath.rotate(vert, this.rigidbody.getPosition(), this.rigidbody.getRotation());
            }
        }
        return vertices;
    }
}
