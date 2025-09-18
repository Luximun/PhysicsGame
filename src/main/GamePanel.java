package main;

import physics2d.fundamentals.PhysicsObject;
import physics2d.fundamentals.Vector2;
import physics2d.primatives.AABB;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    public static ArrayList<PhysicsObject> objectList = new ArrayList<PhysicsObject>();

    // HANDLERS AND THREADING
    KeyHandler kHandler = new KeyHandler();
    MouseListener mHandler = new MouseListener();
    Thread gameThread;

    // CONSTRUCTOR
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(kHandler);
        this.addMouseMotionListener(mHandler);
        this.setFocusable(true);

    }

    public void StartGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS;
        double nextFrameTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            long currentTime = System.nanoTime();

            // UPDATE SHIT
            update();

            // REDRAW SCREEN
            repaint();

            try {
                double remainingFrameTime = nextFrameTime - System.nanoTime();
                remainingFrameTime = remainingFrameTime / 1000000;

                if (remainingFrameTime < 0) {
                    remainingFrameTime = 0;
                }

                Thread.sleep((long) remainingFrameTime);

                nextFrameTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void update() {
//        for (PhysicsObject object : objectList) {
//            object.ApplyGravity();
//            object.UpdatePositionBasedOnVelocity();
//        }

        if (kHandler.ePressed) {
            activeCoordinates = new Vector2(mHandler.mousePosition);
            kHandler.ePressed = false;

//            System.out.println("owiuefhiuwergerg");
            objectList.add(
                    new AABB(new Vector2(activeCoordinates), 40, 20)
            );
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);

        for (PhysicsObject object : objectList) {
            g2.fillRect((int) object.getRigidbody().getPosition().x, (int) object.getRigidbody().getPosition().y, (int) object.getSize().x, (int) object.getSize().y);
        }

        //g2.fillRect((int) Math.round(activeCoordinates.x), (int) Math.round(activeCoordinates.y), tileSize, tileSize);

        g2.dispose();
    }
}
