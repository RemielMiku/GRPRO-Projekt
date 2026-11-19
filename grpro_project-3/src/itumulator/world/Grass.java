package itumulator.world;
import itumulator.simulator.*;
import java.util.ArrayList;
import java.util.Random;

public class Grass implements Actor, NonBlocking {
    public Location location;
    World world;
    double spreadRate;
    Random random = new Random();

    public Grass(World world, Location location, double spreadRate) {
        this.location = location;
        this.world = world;
        this.spreadRate = spreadRate;
    }

    public ArrayList<Location> getSurroundingTilesWithoutNonBlocking(){
        ArrayList<Location> SurroundingNonblockingTiles = new ArrayList<>();
        for (Location l : world.getSurroundingTiles(location)){
            if (!world.containsNonBlocking(l)){
            SurroundingNonblockingTiles.add(l);
            }
        }
        return SurroundingNonblockingTiles;
    }

    @Override
    public void act(World world){
        ArrayList<Location> empty = getSurroundingTilesWithoutNonBlocking();
        if (!empty.isEmpty()){
            Location tile = empty.get(random.nextInt(empty.size()));
            world.setTile(tile, new Grass(world, tile, spreadRate));
        }

    }

}