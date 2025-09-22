package main;

import main.components.Button;
import main.components.ObjectSelectionButton;
import main.components.ScreenComponent;
import physics2d.fundamentals.PhysicsObject;
import physics2d.fundamentals.Vector2;
import physics2d.primatives.AABB;
import physics2d.primatives.Box2D;
import physics2d.primatives.Circle;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
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
    public static ArrayList<PhysicsObject> objectList = new ArrayList<>();
    public static ArrayList<ScreenComponent> screenComps = new ArrayList<>();

    public static PhysicsObject selectedObject = new AABB();

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
        this.addMouseListener(mHandler);
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

        screenComps.add(new ObjectSelectionButton(new Vector2(10, 10), new Vector2(70, 50), mHandler, this, "AABB", new AABB()));
        screenComps.getLast().setColor(Color.DARK_GRAY);

        screenComps.add(new ObjectSelectionButton(new Vector2(10, 55), new Vector2(70, 95), mHandler, this, "Box2D", new Box2D()));
        screenComps.getLast().setColor(Color.CYAN);

        screenComps.add(new ObjectSelectionButton(new Vector2(10, 100), new Vector2(70, 140), mHandler, this, "Circle", new Circle()));
        screenComps.getLast().setColor(Color.GREEN);

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

        for (ScreenComponent comp : screenComps) {
            if ((comp instanceof Button) && !(((Button) comp).MOUSE_REGISTERED)) {
                this.addMouseListener((Button) comp);
                ((Button) comp).REGISTER();
            }
        }

        if (kHandler.ePressed && selectedObject != null) {
            activeCoordinates = new Vector2(mHandler.mousePosition);
            kHandler.ePressed = false;

            try {
                PhysicsObject newObject = (PhysicsObject) selectedObject.getClass().getMethod("createNewDefault", Vector2.class).invoke(null, activeCoordinates);

                objectList.add(
                        newObject
                );

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.setFont(Font.getFont("Comic Sans MS"));

        for (PhysicsObject object : objectList) {
            try {
                object.render(g2);
            } catch (NoSuchMethodError e) {
                System.err.println("Object {" + object + "} is unrenderable - deleting. See below for exact error.");
                System.err.println(e);
                objectList.remove(object);
            }
        }

        for (ScreenComponent component : screenComps) {
            g2.setColor(component.color);
            Vector2 size = component.getSize();
            g2.fillRect((int) component.min.x, (int) component.min.y, (int) size.x, (int) size.y);

            g2.setColor(Color.BLACK);
            if (component instanceof Button) {
                FontMetrics fm = g2.getFontMetrics(g.getFont());
                int yOffset = fm.getAscent();
                g.drawString(((Button) component).text, (int) component.min.x, (int) component.min.y+yOffset);
            }

        }

        g2.dispose();
    }
}
