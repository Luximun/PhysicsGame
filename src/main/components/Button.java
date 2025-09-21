package main.components;

import main.GamePanel;
import main.MouseListener;
import physics2d.fundamentals.Vector2;

import java.awt.event.MouseEvent;

public abstract class Button extends ScreenComponent implements java.awt.event.MouseListener {

    public String text = "";
    public boolean MOUSE_REGISTERED = false;

    public Button(Vector2 min, Vector2 max, MouseListener mouseListener, GamePanel gp, String text) {
        super(min, max, mouseListener, gp);
        this.text = text;
    }

    public void REGISTER() {this.MOUSE_REGISTERED = true;}

     public abstract void ButtonClicked();

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.pointWithin(this.mListener.mousePosition)) {
            // if button
            this.ButtonClicked();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}
