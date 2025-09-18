package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import physics2d.fundamentals.Vector2;

public class MouseListener extends MouseAdapter {

    public Vector2 mousePosition = new Vector2(0, 0);;

//    @Override
    public void mouseMoved(MouseEvent e) {

        mousePosition = new Vector2(e.getX(), e.getY());

    }

}
