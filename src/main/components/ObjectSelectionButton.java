package main.components;

import main.GamePanel;
import main.MouseListener;
import physics2d.fundamentals.PhysicsObject;
import physics2d.fundamentals.Vector2;
import physics2d.primatives.AABB;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ObjectSelectionButton extends Button {

    protected PhysicsObject associatedSelection;

    public ObjectSelectionButton(Vector2 min, Vector2 max,
                                 MouseListener mouseListener, GamePanel gp,
                                 String text, PhysicsObject associatedSelection) {

        super(min, max, mouseListener, gp, text);
        this.associatedSelection = associatedSelection;

    }

    @Override
    public void ButtonClicked() {
        System.out.println("clicked " + this.associatedSelection.toString());
        GamePanel.selectedObject = this.associatedSelection;
    }
}
