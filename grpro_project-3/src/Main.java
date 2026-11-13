import java.awt.Color;

import itumulator.executable.DisplayInformation;
import itumulator.executable.Program;
import itumulator.world.Location;
import itumulator.world.Person;
import itumulator.world.World;

public class Main {
    int size = 3; // size of our 'map' (this is always square)
    int delay = 1000; // delay between each step of the simulation (in ms)
    int display_size = 800; // screen resolution (in px)
    Program p = new Program(size, display_size, delay); // create a new program
    World world = p.getWorld(); // get the world out where we will add things!

    public static void main(String[] args) {
        int size = 5;
        Program p = new Program(size, 800, 75);

        World w = p.getWorld();

        // w.setTile(new Location(0, 0), new <MyClass>());
        Person person = new Person();
        Location place = new Location(0, 1);
        w.setTile(place, person);
        // p.setDisplayInformation(<MyClass>.class, new DisplayInformation(<Color>, "<ImageName>"));
        DisplayInformation di = new DisplayInformation(Color.red);
        p.setDisplayInformation(Person.class, di);

        p.show(); // shows the simulation
            for (int i = 0; i < 200; i++) {
            p.simulate(); // runs 200 rounds; 'act' is called 200 times for all placed actors
        }
    }
}