import java.awt.Color;
import java.util.*;
import itumulator.executable.DisplayInformation;
import itumulator.executable.Program;
import itumulator.world.*;

public class Main {
    //static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int size = 5;
        Program program = new Program(size, 800, 75);
        World world = program.getWorld();
        Random rand = new Random();

        // Implements all picture to classes:
        // Implements hole.png to Hole class
        DisplayInformation hole = new DisplayInformation(Color.black, "hole");
        program.setDisplayInformation(Hole.class, hole);
        // Implements grass.png to Grass class
        DisplayInformation grass = new DisplayInformation(Color.green, "grass");
        program.setDisplayInformation(Grass.class, grass);
        // implements rabbit.png to Bunny class
        DisplayInformation bunny = new DisplayInformation(Color.white, "rabbit-large");
        program.setDisplayInformation(Bunny.class, bunny);
        /*
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            
        }
        */

        world.setTile(new Location(rand.nextInt(size), rand.nextInt(size)), new Hole());

        // random location
        Location grassLocation = new Location(rand.nextInt(size), rand.nextInt(size));
        // check if location already has nonblocking
        if (!world.containsNonBlocking(grassLocation)){
            // new grass
            Grass grasss = new Grass(grassLocation);
            world.setTile(grasss.location, grasss);
        } else {
            while (world.containsNonBlocking(grassLocation)){
                // new grass
                Grass grasss = new Grass(grassLocation);
                world.setTile(grasss.location, grasss);
                    if (!world.containsNonBlocking(grassLocation)) {
                        // new grass
                        grasss = new Grass(grassLocation);
                        world.setTile(grasss.location, grasss);
                    }
                // new random location
                grassLocation = new Location(rand.nextInt(size), rand.nextInt(size));
            }
        }

        program.show();
        for (int i = 0; i < 200; i++) {
            program.simulate();
        
        }
    }
}