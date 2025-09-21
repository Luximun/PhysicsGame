package main.utils;

import physics2d.fundamentals.Vector2;

public class TITMath {

    public static Vector2 getCenter(Vector2 topLeft, Vector2 size) {
        return new Vector2((topLeft.x - (size.x/2)), (topLeft.y - (size.y / 2)));
    }

    // ROTATION

    public static void rotate(Vector2 vec, float angleDeg, Vector2 origin) {
        float cos = (float) Math.cos(Math.toRadians(angleDeg));
        float sin = (float) Math.sin(Math.toRadians(angleDeg));

        float newX = (origin.x + (vec.x-origin.x)*cos - (vec.y-origin.y)*sin);
        float newY = (origin.y + (vec.x-origin.x)*sin + (vec.y-origin.y)*cos);

        vec.x = newX;
        vec.y = newY;
    }

    // COMPARISONS

    public static boolean compare(float x, float y, float epsilon) {
        return Math.abs(x - y) < epsilon * Math.max(1.0f, Math.max(Math.abs(x), Math.abs(y)));
    }

    public static boolean compare(Vector2 vec1, Vector2 vec2, float epsilon) {
        return compare(vec1.x, vec2.x, epsilon) && compare(vec1.y, vec2.y, epsilon);
    }

    // overloads
    public static boolean compare(float x, float y) {
        return Math.abs(x - y) < Float.MIN_VALUE * Math.max(1.0f, Math.max(Math.abs(x), Math.abs(y)));
    }

    public static boolean compare(Vector2 vec1, Vector2 vec2) {
        return compare(vec1.x, vec2.x) && compare(vec1.y, vec2.y);
    }
}
