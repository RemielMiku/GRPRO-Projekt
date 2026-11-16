import java.awt.Color;
import java.util.*;
import itumulator.executable.DisplayInformation;
import itumulator.executable.Program;
import itumulator.world.Location;
import itumulator.world.World;
import itumulator.world.Hole;

public class Main {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int size = 5;
        Program p = new Program(size, 800, 75);
        World w = p.getWorld();
        Random rand = new Random();
        // Implements hole.png to Hole class
        DisplayInformation hole = new DisplayInformation(Color.black, "hole");
        p.setDisplayInformation(Hole.class, hole);
        // Implements grass.png to Grass class
        DisplayInformation grass = new DisplayInformation(Color.green, "grass");
        p.setDisplayInformation(Grass.class, grass);
        
        w.setTile(new Location(rand.nextInt(size), rand.nextInt(size)), new Hole());
        // w.setTile(new Location(0, 0), new <MyClass>());

        /* 
        p.show();
        for (int i = 0; i < 200; i++) {
            p.simulate();

        } */
    }
}