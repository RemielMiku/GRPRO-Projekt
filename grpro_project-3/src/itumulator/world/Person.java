package itumulator.world;
import itumulator.simulator.Actor;
import java.util.*;
public class Person implements Actor{
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