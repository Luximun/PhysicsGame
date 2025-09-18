package physics2d.fundamentals;

public class Line2D {
    private Vector2 from;
    private Vector2 to;

    public Line2D() {
        from = new Vector2(); to = new Vector2();}

    public Line2D(Vector2 pos1, Vector2 pos2) {
        from = new Vector2(pos1);
        to = new Vector2(pos2);
    }

    //*********//
    // GETTERS //
    //*********//

    public Vector2 getFrom() {return from;}
    public Vector2 getTo()   {return to;}

    //*********//
    // SETTERS //
    //*********//

    public void SetPosition(int index, Vector2 newPosition) {
        if (index == 1) {
            from = new Vector2(newPosition);
        } else if (index == 2) {
            to = new Vector2(newPosition);
        } else {
            System.out.println("Index " + index + " is not a valid line index.");
        }
    }
}
