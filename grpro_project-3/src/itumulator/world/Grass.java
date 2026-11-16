package itumulator.world;
import itumulator.world.*;
import java.util.*;

public class Grass implements NonBlocking {
    public Location location;
    private World world;

    public Grass(World world, Location location) {
        this.location = location;
        this.world = world;
    }

    void grow(){
        for (Location l : world.getSurroundingTiles(location)){
            if (!world.containsNonBlocking(location)){
            System.out.println(l);
            }
        }
    }

}