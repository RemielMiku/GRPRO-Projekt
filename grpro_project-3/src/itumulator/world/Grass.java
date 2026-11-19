package itumulator.world;
import itumulator.simulator.*;
import java.util.ArrayList;
import java.util.Random;

public class Grass implements Actor, NonBlocking {
    public Location location;
    World world;
    double spreadRate;
    Random random = new Random();
    private int stepCounter;

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
        stepCounter++;
        ArrayList<Location> empty = getSurroundingTilesWithoutNonBlocking();
        if (!empty.isEmpty()){
            Location tile = empty.get(random.nextInt(empty.size()));
            if (stepCounter>=1/spreadRate) {
            // spread grass on selected tile and set spreadrate of the new tile to random between 0.1 and 1.0 (spread every 20 ticks to spread every other tick)
            world.setTile(tile, new Grass(world, tile, random.nextDouble(0.1, 1.0)));
            stepCounter = 0;
            }
        }
    }

}