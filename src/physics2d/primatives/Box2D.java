package physics2d.primatives;

import main.utils.TITMath;
import physics2d.fundamentals.PhysicsObject;
import physics2d.fundamentals.Vector2;
import physics2d.rigidbody.Rigidbody2D;

import java.awt.*;

public class Box2D extends PhysicsObject {
    private Vector2 size = new Vector2();
    private Vector2 halfSize = new Vector2();

    public static Box2D createNewDefault(Vector2 center) {
        return new Box2D(center, new Vector2(20, 20), (float) Math.random()*90);
    }

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

    @Override
    public void render(Graphics2D g2D) {
        if (this.getRigidbody().getRotation() == 0.0f) {
            g2D.fillRect((int) this.getRigidbody().getPosition().x, (int) this.getRigidbody().getPosition().y, (int) this.getSize().x, (int) this.getSize().y);
        } else {
            Vector2[] vertices = this.getVertices();
            int numberOfVertices = vertices.length;

            int[] xPositions = new int[numberOfVertices];
            int[] yPositions = new int[numberOfVertices];

            int index = 0;

            for (Vector2 vertex : vertices) {
                xPositions[index] = (int) vertex.x;
                yPositions[index] = (int) vertex.y;

                index++;
            }

            g2D.fillPolygon(xPositions, yPositions, numberOfVertices);
        }
    }
}
