import java.awt.Color;
import java.util.*;
import itumulator.executable.DisplayInformation;
import itumulator.executable.Program;
import itumulator.world.*;
import java.awt.Color;
import java.util.*;

public class Main {
    //static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int size = 5;
        Program program = new Program(size, 800, 100);
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
        Location holeLocation = new Location(rand.nextInt(size), rand.nextInt(size));
        Hole hole1 = new Hole(holeLocation);
        world.setTile(holeLocation, hole1);

        int amount = 2;
        Random r = new Random();
        for (int i = 0; i < amount; i++) {
            int x = r.nextInt(size);
            int y = r.nextInt(size);

            Location place = new Location(x,y);

            while (!world.isTileEmpty(place)) {
                x = r.nextInt(size);
                y = r.nextInt(size);

                place = new Location(x,y);
            }
            world.setTile(place, new Bunny());
        }

        // random location
        Location grassLocation = new Location(rand.nextInt(size), rand.nextInt(size));
        // check if location already has nonblocking
        if (!world.containsNonBlocking(grassLocation)){
            // new grass
            Grass grasss = new Grass(world, grassLocation);
            world.setTile(grasss.location, grasss);
        } else {
            while (world.containsNonBlocking(grassLocation)){
                // new grass
                Grass grasss = new Grass(world, grassLocation);
                world.setTile(grasss.location, grasss);
                    if (!world.containsNonBlocking(grassLocation)) {
                        // new grass
                        grasss = new Grass(world, grassLocation);
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