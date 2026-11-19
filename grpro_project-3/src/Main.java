import itumulator.executable.DisplayInformation;
import itumulator.executable.Program;
import itumulator.world.*;
import java.awt.Color;
import java.util.*;

public class Main {
    //static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int size = 5;
        Program program = new Program(size, 800, 1000);
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
        world.setTile(holeLocation, new Hole());

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


        program.show();
        for (int i = 0; i < 200; i++) {
            program.simulate();
            
            int grassCount = 0;

            // Go through all entities existing in the world and count grass
            for (Object e : world.getEntities().keySet()){
                if (e instanceof Grass){
                    grassCount++;
                }
            }

            if (grassCount<=1){
                // random location
                Location grassLocation = new Location(rand.nextInt(size), rand.nextInt(size));
                // check if location already has nonblocking
                while (world.containsNonBlocking(grassLocation)){
                    // new random location
                    grassLocation = new Location(rand.nextInt(size), rand.nextInt(size));
                }
                Grass grasss = new Grass(world, grassLocation, 0.5);
                world.setTile(grasss.location, grasss);
            }

        }
    }
}