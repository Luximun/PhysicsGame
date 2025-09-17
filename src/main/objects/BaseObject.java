package main.objects;

import main.utils.Vector2;

public class BaseObject {
    public String name = "baseobject";

    public int mass = 10; // Kilograms
    public int ambientTemperature = 15; // Celsius

    //POSITION, SIZING, VELOCITY, ETC
    public Vector2 position;
    public Vector2 velocity;
    public Vector2 acceleration;

    //**************//
    // CONSTRUCTORS //
    //**************//

    public BaseObject(
            String input_name,
            int input_mass,
            int input_ambientTemperature
    ) {
        name = input_name;
        mass = input_mass;
        ambientTemperature = input_ambientTemperature;
    }

    public BaseObject() {}

    //**************//
    // DATA SETTING //
    //**************//

    public void UpdatePositionBasedOnVelocity() {
        position =
    }

    public void SetPosition(Vector2 pos) {
        position = pos;
    }

    public void SetTemperature(int newValue) {
        ambientTemperature = newValue;
    }
}
