package main.components;

import main.GamePanel;
import main.MouseListener;
import physics2d.fundamentals.Vector2;
import physics2d.primatives.AABB;

import java.awt.*;

public class ScreenComponent {
    public Vector2 min;
    public Vector2 max;

    public Color color = Color.WHITE;

    protected MouseListener mListener;
    protected GamePanel gp;

    // CONSTRUCTORS
    public ScreenComponent(MouseListener mouseListener, GamePanel gp){
        this.mListener = mouseListener;
        this.gp = gp;
    }

    public ScreenComponent(Vector2 min, Vector2 max, MouseListener mouseListener, GamePanel gp) {
        this.mListener = mouseListener;
        this.min = min;
        this.max = max;
        this.gp = gp;
    }

    // METHODS
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    public boolean pointWithin(Vector2 point) {
        return     point.x <= max.x && min.x <= point.x
                && point.y <= max.y && min.y <= point.y;
    }

    public Vector2 getSize() {
        return new Vector2(max.x - min.x, max.y - min.y);
    }
}
