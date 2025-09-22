package physics2d.rigidbody;

import main.utils.TITMath;
import physics2d.fundamentals.Vector2;
import physics2d.fundamentals.Line2D;
import physics2d.primatives.*;

import javax.tools.JavaFileManager;
import java.util.Vector;

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

    public static boolean lineAndAABB(Line2D line, AABB box) {
        if (pointInAABB(line.getStart(), box) || pointInAABB(line.getEnd(), box)) {
            return true;
        }

        // Normalising and unit vectoring
        Vector2 unitVector = new Vector2(line.getEnd()).subtract(line.getStart());
        unitVector.normalize();
        unitVector.x = (unitVector.x != 0) ? 1.0f/unitVector.x : 0f;
        unitVector.y = (unitVector.y != 0) ? 1.0f/unitVector.y : 0f;

        Vector2 min = box.getMin();
        min = min.subtract(line.getStart()).multiplyBy(unitVector);
        Vector2 max = box.getMax();
        max = max.subtract(line.getStart()).multiplyBy(unitVector);

        float tMin = Math.max(Math.min(min.x, max.x), Math.min(min.y, max.y));
        float tMax = Math.min(Math.max(min.x, max.x), Math.max(min.y, max.y));
        if (tMax < 0 || tMin > tMax) {
            return false;
        }

        float t = (tMin < 0f) ? tMax : tMin;
        return t > 0f && t*t < line.lengthSquared();
    }

    public static boolean lineAndBox2D(Line2D line, Box2D box) {
        // determine box angle
        float theta = -box.getRigidbody().getRotation();

        // get positions
        Vector2 center = box.getRigidbody().getPosition();
        Vector2 localStart = new Vector2(line.getStart());
        Vector2 localEnd = new Vector2(line.getEnd());
        TITMath.rotate(localStart, theta, center);
        TITMath.rotate(localEnd, theta, center);

        // create new line and new box
        Line2D localLine = new Line2D(localStart, localEnd);
        AABB aabb = new AABB(box.getMin(), box.getMax());

        // test thing
        return lineAndAABB(localLine, aabb);
    }

    //**********//
    // RAYCASTS //
    //**********//

    public static boolean raycast(Circle circle, Ray2D ray, RaycastResult result) {
        RaycastResult.reset(result);

        Vector2 originToCircleCenter = new Vector2(circle.getCenter()).subtract(ray.getOrigin());
        float radiusSquared = circle.getRadius() * circle.getRadius();
        float originToCircleLengthSquared = originToCircleCenter.lengthSquared();

        //project vector from origin onto ray direction
        float a = originToCircleCenter.dot(ray.getDirection());
        float bSq = originToCircleLengthSquared - (a*a);

        if (radiusSquared - bSq < 0.0f) {
            return false;
        }

        float f = (float) Math.sqrt(radiusSquared - bSq);
        float t = 0;
        if (originToCircleLengthSquared < radiusSquared) {
            // ray starts inside circle
            t = a+f;
        } else {
            t = a-f;
        }

        if (result != null) {
            Vector2 point = new Vector2(ray.getOrigin()).add(ray.getDirection().multiplyBy(t));
            Vector2 normal = new Vector2(point).subtract(circle.getCenter());
            normal.normalize();

            result.init(point, normal, t, true);
        }

        return true;
    }

}
