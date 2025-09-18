package main.objects;

import physics2d.fundamentals.Vector2;

public class WeightObject extends BaseObject {

    public WeightObject() {
        super(
                "10ton Weight",
                10000,
                15,
                new Vector2()
        );
    }
}
