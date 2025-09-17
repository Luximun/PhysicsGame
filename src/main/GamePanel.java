package main;

import main.objects.BaseObject;
import main.utils.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final static int originalTileSize = 16; // 16x16
    final static int scale = 3;

    final static int tileSize = originalTileSize * scale; // 48x
    final static int maxScreenCol = 16;
    final static int maxScreenRow = 12;

    final static int screenWidth = tileSize * maxScreenCol;
    final static int screenHeight = tileSize * maxScreenRow;

    final static int FPS = 60;

    // ACTIVE COORDINATES
    public Vector2 activeCoordinates = new Vector2(0, 0);

    // OBJECTS
    public ArrayList<BaseObject> objectList = new ArrayList<BaseObject>();

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

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            long currentTime = System.nanoTime();

            // UPDATE SHIT
            update();

            // REDRAW SCREEN
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void update() {
        if (kHandler.ePressed) {
            activeCoordinates = mHandler.mousePosition;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.fillRect((int) Math.round(activeCoordinates.x), (int) Math.round(activeCoordinates.y), tileSize, tileSize);

        g2.dispose();
    }
}
