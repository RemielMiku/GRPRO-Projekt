package itumulator.world;
import java.util.*;
import itumulator.simulator.Actor;

public class Bunny implements Actor{
    int age;
    int daysWithoutFood;
    boolean isNear;
    Location location;
    int kidsamount = 1;
    int maxAge = 20;
    int adultAge = 6;
    int oldAge = 15;

    Random r = new Random();

    public Bunny() {
        age = 0;
        daysWithoutFood = 0;
    }

    @Override
    public void act(World world){
        
        if (world.isDay()) {
            move(world);
        } 
        if (world.getCurrentTime() % 20 == 0){
            daysWithoutFood++;
            age++;
        }

        if (daysWithoutFood >= 3 || age >= maxAge) {
            world.delete(this);
            return;
        }

        if (daysWithoutFood < 1) {
            eat(world);
        }

        isNear(world);

        if (isNear == true && age >= adultAge && age < oldAge && r.nextInt(100) > 10) {
            newbunnyloc(world);
            isNear = false;
        }

    }

     public void isNear(World world){
        isNear = false;
        
        Set<Location> bunnys = world.getSurroundingTiles();
        
        for (Location loc : bunnys) {
            if (world.getTile(loc) instanceof Bunny) {
                isNear = true;
                System.out.println("Bunny is near");
                break;
            }
        }
    }

    public void newbunnyloc(World world){
            Set<Location> bunloc = world.getEmptySurroundingTiles();
            List<Location> list = new ArrayList<>(bunloc);

        for (int i = 0; i < kidsamount; i++) {
            int index = r.nextInt(list.size());
            Location place = list.remove(index);
            world.setTile(place, new Bunny());
        }
    }

    public void move(World world) {
            Set<Location> neighbours = world.getEmptySurroundingTiles();
            List<Location> list = new ArrayList<>(neighbours);
                if (list.isEmpty()) {
                 return;
                }       
            Location one = list.get(r.nextInt(list.size()));
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
}
