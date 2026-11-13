import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import itumulator.executable.DisplayInformation;
import itumulator.executable.Program;
import itumulator.simulator.Actor;
import itumulator.world.Location;
import itumulator.world.World;

public class Main {

    public static void main(String[] args) {
        int size = 5;
        Program p = new Program(size, 800, 75);

        World w = p.getWorld();

        // w.setTile(new Location(0, 0), new <MyClass>());

        // p.setDisplayInformation(<MyClass>.class, new DisplayInformation(<Color>, "<ImageName>"));

        p.show();
    }
    public class Person implements Actor {
    private Random RAND = new Random();

    @Override
    public void act(World world) {
        if (world.isNight()) {
            world.delete(this);
            return;
        }

        List<Location> neighbours = new ArrayList<>(world.getEmptySurroundingTiles());

        Location l = neighbours.get(RAND.nextInt(neighbours.size()));
        world.move(this,l);
    }
}

}