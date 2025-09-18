package physics2d.rigidbody;

import org.junit.Test;
import physics2d.fundamentals.Line2D;
import physics2d.fundamentals.Vector2;
import physics2d.primatives.AABB;
import physics2d.primatives.Box2D;
import physics2d.primatives.Circle;

import static org.junit.jupiter.api.Assertions.*;

public class IntersectionDetector2DTest {

    @Test
    public void pointOnPerfectDiagonalLineShouldReturnTrue() {
        Line2D line = new Line2D(new Vector2(0, 0), new Vector2(10, 10));
        Vector2 point = new Vector2(3,3);

        assertTrue(IntersectionDetector2D.pointOnLine(point, line));
    }

    @Test
    public void pointOnDiagonalLineShouldReturnTrue() {
        Line2D line = new Line2D(new Vector2(0, 0), new Vector2(3, 10));
        Vector2 point = new Vector2(2.7f,9f);

        assertTrue(IntersectionDetector2D.pointOnLine(point, line));
    }

    @Test
    public void pointOnVerticalLineShouldReturnTrue() {
        Line2D line = new Line2D(new Vector2(0, 0), new Vector2(0, 10));
        Vector2 point = new Vector2(0,3);

        assertTrue(IntersectionDetector2D.pointOnLine(point, line));
    }

    @Test
    public void pointInCircleShouldReturnTrue() {
        Circle circle = new Circle(5f);
        Vector2 point = new Vector2(0,3);

        assertTrue( IntersectionDetector2D.pointInCircle(point, circle) );
    }

    @Test
    public void pointOnCircleShouldReturnTrue() {
        Circle circle = new Circle(5f);
        Vector2 point = new Vector2(0,5f);

        assertTrue(IntersectionDetector2D.pointInCircle(point, circle));
    }

    @Test
    public void pointInAABB() {
        AABB aabb = new AABB(new Vector2(10f, 20f));
        Vector2 point = new Vector2(3f,5f);

        assertTrue(IntersectionDetector2D.pointInAABB(point, aabb));
    }

    @Test
    public void pointInBox2D() {
        Box2D box = new Box2D(new Vector2(1, 2), new Vector2(50, 30));
        Vector2 point = new Vector2(3,5);

        assertTrue(IntersectionDetector2D.pointInBox2D(point, box));
    }

    @Test
    public void lineAndCircleShouldReturnTrue() {
        Circle circle = new Circle(5f);
        Line2D line = new Line2D(new Vector2(5,-5), new Vector2(4, 5));

        assertTrue( IntersectionDetector2D.lineAndCircle(line, circle) );
    }
}