package main.components;

import physics2d.fundamentals.Vector2;
import physics2d.primatives.AABB;

public class ScreenComponent {
    public Vector2 min;
    public Vector2 max;

    public ScreenComponent(){}

    public ScreenComponent(Vector2 min, Vector2 max) {
        this.min = min;
        this.max = max;
    }

    public boolean pointWithin(Vector2 point) {
        return     point.x <= max.x && min.x <= point.x
                && point.y <= max.y && min.y <= point.y;
    }
}
