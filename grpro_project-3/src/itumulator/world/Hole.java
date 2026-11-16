package itumulator.world;
import java.awt.Color;
import itumulator.executable.DisplayInformation;

public class Hole implements NonBlocking {
    private int x;
    private int y;
    boolean working;

    public Hole() {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}
