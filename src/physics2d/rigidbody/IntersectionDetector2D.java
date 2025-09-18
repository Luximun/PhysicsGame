package physics2d.rigidbody;

import main.utils.TITMath;
import physics2d.fundamentals.Vector2;
import physics2d.fundamentals.Line2D;
import physics2d.primatives.AABB;
import physics2d.primatives.Box2D;
import physics2d.primatives.Circle;

import javax.tools.JavaFileManager;

public class IntersectionDetector2D {
    // Point vs Primative Test
    public static boolean pointOnLine(Vector2 point, Line2D line) {
        float dy = line.getTo().y - line.getFrom().y;
        float dx = line.getTo().x - line.getFrom().x;
        if (dx == 0f) {
            return TITMath.compare(point.x, line.getFrom().x);
        }
        float m = dy / dx; // gradient

        // y intercept
        float b = line.getTo().y - (m * line.getTo().x);

        return point.y == m * point.x + b;
    }

    public static boolean pointInCircle(Vector2 point, Circle circle) {
        Vector2 circleCenter = circle.getCenter();
        Vector2 centerToPoint = new Vector2(point).subtract(circleCenter); //difference

        return centerToPoint.lengthSquared() <= (circle.getRadius() * circle.getRadius());
    }

    public static boolean pointInAABB(Vector2 point, AABB box) {
        Vector2 min = box.getMin();
        Vector2 max = box.getMax();

        return     point.x <= max.x && min.x <= point.x
                && point.y <= max.y && min.y <= point.y;
    }

    public static boolean pointInBox2D(Vector2 point, Box2D box) {
        // translate point into local space
        Vector2 pointLocalBoxSpace = new Vector2(point);
        TITMath.rotate(pointLocalBoxSpace,
                box.getRigidbody().getRotation(),
                box.getRigidbody().getPosition()
        );

        Vector2 min = box.getMin();
        Vector2 max = box.getMax();

        return     pointLocalBoxSpace.x <= max.x && min.x <= pointLocalBoxSpace.x
                && pointLocalBoxSpace.y <= max.y && min.y <= pointLocalBoxSpace.y;
    }

    // =================
    // LINE VS PRIMITIVE
    // =================

    public static boolean lineAndCircle(Line2D line, Circle circle) {
        if (pointInCircle(line.getFrom(), circle) || pointInCircle(line.getTo(), circle)) {
            return true;
        }

        Vector2 ab = new Vector2(line.getTo()).subtract(line.getFrom());

        // project point (circle pos) onto ab (line seg)
        Vector2 circleCenter = circle.getCenter();
        Vector2 centerToLineStart = new Vector2(circleCenter).subtract(line.getFrom());

        float t = centerToLineStart.dot(ab) / ab.dot(ab);

        if (t < 0.0f || t > 1.0f) {
            return false; // not on line segment at all
        }

        //find the closest point to line segment
        Vector2 closestPoint = new Vector2(line.getFrom()).add(ab.multiplyBy(t));

        return pointInCircle(closestPoint, circle);
    }

}
