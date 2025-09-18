package physics2d.fundamentals;

import physics2d.rigidbody.Rigidbody2D;

public abstract class PhysicsObject {

    protected Rigidbody2D rigidbody = new Rigidbody2D();

    public Rigidbody2D getRigidbody() {
        return rigidbody;
    }

    public abstract Vector2 getSize();
}
