import java.awt.Color;
import java.util.*;
import itumulator.executable.DisplayInformation;
import itumulator.executable.Program;
import itumulator.world.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int size = 5;
        Program p = new Program(size, 800, 75);
        World w = p.getWorld();
        Random rand = new Random();

        // Implements all picture to classes:
        // Implements hole.png to Hole class
        DisplayInformation hole = new DisplayInformation(Color.black, "hole");
        p.setDisplayInformation(Hole.class, hole);
        // Implements grass.png to Grass class
        DisplayInformation grass = new DisplayInformation(Color.green, "grass");
        p.setDisplayInformation(Grass.class, grass);
        // implements rabbit.png to Bunny class
        DisplayInformation bunny = new DisplayInformation(Color.white, "rabbit-large");
        p.setDisplayInformation(Bunny.class, bunny);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            
        }

        w.setTile(new Location(rand.nextInt(size), rand.nextInt(size)), new Hole());
        
        /*
        p.show();
        for (int i = 0; i < 200; i++) {
            p.simulate();
        
        }
        */
    }
}