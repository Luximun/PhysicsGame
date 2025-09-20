package main;

import main.components.Button;
import main.components.ScreenComponent;
import physics2d.fundamentals.PhysicsObject;
import physics2d.fundamentals.Vector2;
import physics2d.primatives.AABB;
import physics2d.primatives.Box2D;

import javax.script.ScriptEngine;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final static int originalTileSize = 16; // 16x16
    final static int scale = 3;

    public final static int tileSize = originalTileSize * scale; // 48x
    final static int maxScreenCol = 16;
    final static int maxScreenRow = 12;

    public final static int screenWidth = tileSize * maxScreenCol;
    public final static int screenHeight = tileSize * maxScreenRow;

    public final static int FPS = 60;

    // ACTIVE COORDINATES
    public Vector2 activeCoordinates = new Vector2(0, 0);

    // OBJECTS
    public static ArrayList<PhysicsObject> objectList = new ArrayList<>();
    public static ArrayList<ScreenComponent> screenComps = new ArrayList<>();

    public static PhysicsObject selectedObject = null;

    // HANDLERS AND THREADING
    KeyHandler kHandler = new KeyHandler();
    MouseListener mHandler = new MouseListener();
    Thread gameThread;

    /// CONSTRUCTOR
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(kHandler);
        this.addMouseMotionListener(mHandler);
        this.setFocusable(true);

    }

    /// THREAD STARTER
    public void StartGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /// MAIN RUN FUNCTION
    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        screenComps.add(new ScreenComponent(new Vector2(10, 10), new Vector2(70, 50), mHandler, this));
        screenComps.getLast().setColor(Color.MAGENTA);

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                // UPDATE SHIT
                update();

                // REDRAW SCREEN
                repaint();

                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void update() {
//        for (PhysicsObject object : objectList) {
//            object.ApplyGravity();
//            object.UpdatePositionBasedOnVelocity();
//        }
        if (mHandler.mouseClicked) {
            for (ScreenComponent comp : screenComps) {
                // if button
                if (comp instanceof Button) {
                    // if mouse is within
                    if (comp.pointWithin(mHandler.mousePosition)) {
                        ((Button) comp).ButtonClicked();
                    }
                }
            }

            mHandler.mouseClicked = false;
        }

        if (kHandler.ePressed) {
            activeCoordinates = new Vector2(mHandler.mousePosition);
            kHandler.ePressed = false;

            float randomAngle = (float) Math.round(Math.random()*90);

            objectList.add(
//                new AABB(new Vector2(activeCoordinates), (float) Math.random() * 100, (float) Math.random() * 100)
                new Box2D(
                        new Vector2(activeCoordinates),
                        new Vector2((float) Math.random() * 100, (float) Math.random() * 100),
                        randomAngle
                )
            );
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);

        for (PhysicsObject object : objectList) {
            if ((object instanceof AABB) || (object instanceof Box2D && object.getRigidbody().getRotation() == 0.0f)) {
                g2.fillRect((int) object.getRigidbody().getPosition().x, (int) object.getRigidbody().getPosition().y, (int) object.getSize().x, (int) object.getSize().y);
            } else if (object instanceof Box2D) {
                Vector2[] vertices = ((Box2D) object).getVertices();
                int numberOfVertices = vertices.length;

                int[] xPositions = new int[numberOfVertices];
                int[] yPositions = new int[numberOfVertices];

                int index = 0;

                for (Vector2 vertex : vertices) {
                    xPositions[index] = (int) vertex.x;
                    yPositions[index] = (int) vertex.y;

                    index++;
                }

                g2.fillPolygon(xPositions, yPositions, numberOfVertices);
            }
        }

        for (ScreenComponent component : screenComps) {
            g2.setColor(component.color);
            Vector2 size = component.getSize();
            g2.fillRect((int) component.min.x, (int) component.max.y, (int) size.x, (int) size.y);
        }

        g2.dispose();
    }
}
