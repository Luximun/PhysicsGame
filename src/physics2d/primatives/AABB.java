package physics2d.primatives;

import main.utils.Vector2;
import physics2d.rigidbody.Rigidbody2D;

// Axis-aligned Bounding Box
public class AABB {
    private Vector2 size = new Vector2();
    private Vector2 halfSize = new Vector2();
    private Rigidbody2D rigidbody;

    public AABB() {
        this.halfSize = size.DivideBy(2);
    }

    public AABB(Vector2 min, Vector2 max) {
        this.size = max.subtract(min);
        this.halfSize = size.DivideBy(2);
    }

    public Vector2 getMin() {
        return this.rigidbody.getPosition().subtract(this.halfSize);
    }
    public Vector2 getMax() {
        return this.rigidbody.getPosition().add(this.halfSize);
    }

}
