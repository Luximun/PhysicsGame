package physics2d.primatives;

import com.beust.jcommander.internal.Nullable;
import physics2d.fundamentals.PhysicsObject;
import physics2d.fundamentals.Vector2;
import physics2d.rigidbody.Rigidbody2D;

// Axis-aligned Bounding Box
public class AABB extends PhysicsObject {
    private Vector2 size = new Vector2();
    private Vector2 halfSize = new Vector2();

    public AABB(Vector2 inputSize) {
        this.size = new Vector2(inputSize);
        this.halfSize = size.divideBy(2);
    }

    public AABB() {
        this.size = new Vector2(1, 1);
        this.halfSize = size.divideBy(2);
    }

    public AABB(Vector2 centerPosition, float width, float height) {
        this.size = new Vector2(width, height);
        this.halfSize = size.divideBy(2);

        this.rigidbody.setPosition(new Vector2(centerPosition.subtract(this.halfSize)));
    }

    public AABB(Vector2 min, Vector2 max) {
        this.size = max.subtract(min);
        this.halfSize = size.divideBy(2);
    }

    public Vector2 getMin() {
        return this.rigidbody.getPosition().subtract(this.halfSize);
    }
    public Vector2 getMax() {
        return this.rigidbody.getPosition().add(this.halfSize);
    }

    @Override
    public Vector2 getSize() {
        return this.size;
    }
}
