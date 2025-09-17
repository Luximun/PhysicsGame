package physics2d.rigidbody;

import main.utils.Vector2;
import physics2d.Line2D;
import physics2d.primatives.Circle;

public class IntersectionDetector2D {
    // Point vs Primative Test
    public static boolean pointOnLine(Vector2 point, Line2D line) {
        double dy = line.getTo().y - line.getFrom().y;
        double dx = line.getTo().x - line.getFrom().x;
        double m = dy / dx; // gradient

        // y intercept
        double b = line.getTo().y - (m * line.getTo().x);

        return point.y == m * point.x + b;
    }

    public static boolean pointInCircle(Vector2 point, Circle circle) {

    }
}
