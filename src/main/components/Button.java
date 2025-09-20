package main.components;

import main.GamePanel;
import main.MouseListener;
import physics2d.fundamentals.Vector2;

public abstract class Button extends ScreenComponent {

    public String text = "";

    public Button(Vector2 min, Vector2 max, MouseListener mouseListener, GamePanel gp, String text) {
        super(min, max, mouseListener, gp);
        this.text = text;
    }

     public abstract void ButtonClicked();

}
