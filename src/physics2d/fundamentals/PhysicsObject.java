package physics2d.fundamentals;

import physics2d.rigidbody.Rigidbody2D;

import java.awt.*;

public abstract class PhysicsObject {

    protected Rigidbody2D rigidbody = new Rigidbody2D();

    public static PhysicsObject createNewDefault(Vector2 center) {return null;}

    public Rigidbody2D getRigidbody() {
        return rigidbody;
    }
    public abstract Vector2 getSize();
    public abstract void render(Graphics2D g2D);
}
