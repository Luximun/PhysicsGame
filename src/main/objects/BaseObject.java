package main.objects;

import main.Environment;
import main.GamePanel;
import main.utils.Vector2;

public class BaseObject {
    public String name = "baseobject";

    public int mass = 10; // Kilograms
    public int ambientTemperature = 15; // Celsius

    //POSITION, SIZING, VELOCITY, ETC
    public Vector2 position;
    public Vector2 velocity = new Vector2();
    public Vector2 acceleration = new Vector2();

    public Vector2 size = new Vector2((double) GamePanel.tileSize);

    //**************//
    // CONSTRUCTORS //
    //**************//

    public BaseObject(
            String input_name,
            int input_mass,
            int input_ambientTemperature,
            Vector2 position
    ) {
        name = input_name;
        mass = input_mass;
        ambientTemperature = input_ambientTemperature;
    }

    public BaseObject(Vector2 pos) {
        position = pos.subtract(size.DivideBy(2));
    }

    //*********//
    // PHYSICS //
    //*********//

    public void UpdatePositionBasedOnVelocity() {
        position = position.add(velocity.DivideBy(GamePanel.FPS));
        velocity = velocity.add(acceleration.DivideBy(GamePanel.FPS));
    }

    public void ApplyGravity() {
        if (position.y + GamePanel.tileSize < GamePanel.screenHeight) {
            acceleration = acceleration.add(new Vector2(0, Environment.GRAVITY / GamePanel.FPS));
        } else {
            acceleration.y = 0;
            velocity.y = 0;
        }
    }

    //**************//
    // DATA SETTING //
    //**************//

    public void SetPosition(Vector2 pos) {position = pos;}
    public void SetVelocity(Vector2 vel) {velocity = vel;}
    public void SetAcceleration(Vector2 acc) {acceleration = acc;}

    public void SetTemperature(int newValue) {ambientTemperature = newValue;}
}
