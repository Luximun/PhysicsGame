package main.objects;

public class BaseObject {
    public String name = "baseobject";

    public int mass = 10; // Kilograms
    public int ambientTemperature = 15; // Celsius

    public BaseObject(
            String input_name,
            int input_mass,
            int input_ambientTemperature
    ) {
        name = input_name;
        mass = input_mass;
        ambientTemperature = input_ambientTemperature;
    }
}
