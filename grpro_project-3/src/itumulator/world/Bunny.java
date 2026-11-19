package itumulator.world;
import java.util.*;
import itumulator.simulator.Actor;

public class Bunny implements Actor{
    int age;
    int daysWithoutFood;
    boolean isNear;
    Location location;

    /*
    public Bunny() {
        age = 0;
        daysWithoutFood = 0;
    }

    
    public void isNear(World world){
        for (int i = 0; i < world.getSurroundingTiles().size(); i++) {
            if (world.getSurroundingTiles(i).contains(Bunny.class)) {
                isNear = true;
            }
        }
        isNear = false;
    }

    void repruduce() {
        if(hasEaten && isNear && age > 2 && new Random().nextInt(100) < 10){
            Bunny newBunny = new Bunny(new Location(new Random().nextInt(5), new Random().nextInt(5)));
        }
    }
*/
    @Override
    public void act(World world){
        
        if (world.isDay()) {
            move(world);
        } else {
            daysWithoutFood++;
            age++;
        }

        if (daysWithoutFood >= 3 || age >= 20) {
            world.delete(this);
        }

        if (daysWithoutFood < 2) {
            eat(world);
        }

    }

    public void move(World world) {
            Set<Location> neighbours = world.getEmptySurroundingTiles();
            List<Location> list = new ArrayList<>(neighbours);
            Location one = list.get(new Random().nextInt(list.size()));
            world.move(this, one);
    }

    void eat(World world) {
        Set<Location> neighbours = world.getSurroundingTiles(10);
        for (Location loc : neighbours) {
            if (world.getTile(loc) instanceof Grass) {
                world.delete(world.getNonBlocking(loc));
                daysWithoutFood = 0;
                break;
            }
        }
    }
/* 
    void resetDaysWithoutFood() {
        if(hasEaten){
            daysWithoutFood = 0;
            hasEaten = false;
        }
    }

    void die(World world) {
        if (!hasEaten && daysWithoutFood < 3) {
            isAlive = false;
           world.delete(this);
        }
    }
*/
}
