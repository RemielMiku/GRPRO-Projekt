package itumulator.world;
import java.io.ObjectInputStream.GetField;
import java.util.*;
import itumulator.simulator.Actor;

public class Bunny implements Actor{
    int age;
    int daysWithoutFood;
    boolean isNear = false;
    Location location;
    int kidsamount = 1;
    int maxAge = 20;
    int adultAge = 6;
    int oldAge = 15;
    Hole hole;
    int searchRadius = 5;
    boolean sleeping = false;

    Random r = new Random();

    public Bunny() {
        age = 0;
        daysWithoutFood = 0;
    }

    public int getAge() {
        return age;
    }

    @Override
    public void act(World world){
        
        if (world.isDay()) {
            if (sleeping){
                sleeping = !getUpBro(world);
            } else {
                move(world);
            }
        } else {
            sleep(world);
            sleeping = true;
        }

        if (!sleeping){
            if (daysWithoutFood >= 3 || age >= maxAge) {
            world.delete(this);
            return;
        }

        if (world.getCurrentTime() % 20 == 0){
            daysWithoutFood++;
            age++;
        }

        if (daysWithoutFood >= 2) {
            eat(world);
        }

        isNear(world);

        if (isNear == true && age >= adultAge && age < oldAge && r.nextInt(100) < 20) {
            newbunnyloc(world);
            isNear = false;
        }
        }

    }

     public void isNear(World world){
        isNear = false;

        Set<Location> bunnys = world.getSurroundingTiles();
        
        for (Location loc : bunnys) {
            if (world.getTile(loc) instanceof Bunny) {
                Bunny otherObject = (Bunny) world.getTile(loc);
                if (otherObject.getAge() >= adultAge && otherObject.getAge() < oldAge) {
                    isNear = true;
                    break;
                }
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

    public void eat(World world) {
        Set<Location> neighbours = world.getSurroundingTiles(10);
        for (Location loc : neighbours) {
            if (world.getTile(loc) instanceof Grass) {
                world.move(this, loc);
                world.delete(world.getNonBlocking(loc));
                daysWithoutFood = 0;
                break;
            }
        }
    }

    public int distanceCalc(Location a, Location b){
        int dx = a.getX() - b.getX();
        int dy = a.getY() - b.getY();
        return (int) Math.sqrt(dx * dx + dy * dy);
    }

    public Hole getHole(){
        return hole;
    }

    public boolean hasHole(){
        return hole != null;
    }

    public void setHole(Hole hole){
        this.hole = hole;
    }


    public void sleep(World world){
        Location nowLocation = world.getLocation(this);
        if (hole == null){
             hole = findHole(world, world.getLocation(this));
            if (hole == null && !dig(world, world.getLocation(this))){
                move(world);
                return;
            }
        }
        Location holeLocation = world.getLocation(hole);
        if (distanceCalc(nowLocation, holeLocation) <= 1){
            world.remove(this);
        } else {
            goFromHereToHere(world, nowLocation, holeLocation);
        }
    }

    public boolean getUpBro(World world){
        if (hole == null){
            return false;
        }
        Location sleepLocation = world.getLocation(hole);

        if (sleepLocation == null){
            return false;
        }

        List<Location> available = new ArrayList<>(world.getEmptySurroundingTiles(sleepLocation));
        
        if (available.isEmpty()){
            return false;
        }
        
        Location exit = available.get(r.nextInt(available.size()));
        world.setTile(exit, this);
        return true;
    }

    public Hole findHole(World world, Location location){
        Hole h = null;
        int distance = Integer.MAX_VALUE;
        for (Location loc : world.getSurroundingTiles(location, searchRadius)){
            Object tile = world.getNonBlocking(loc);
            if (tile instanceof Hole && distanceCalc(location, loc) < distance){
                h = (Hole) tile;
                distance = distanceCalc(location, loc);
            }
        }
        return h;
    }

    public boolean dig(World world, Location location){
        if (world.containsNonBlocking(location)){
            return false;
        } else {
            Hole h = new Hole();
            world.setTile(location, h);
            this.hole = h;
            return true;
        }
    }

    public void goFromHereToHere(World world, Location from, Location to){
        List<Location> avalable = new ArrayList<>(world.getEmptySurroundingTiles(from));
        Collections.shuffle(avalable, r);
        Location test = null;
        int distance1 = distanceCalc(from, to);
        for (Location l : avalable){
            int distance2 = distanceCalc(l, to);
            if (distance2 < distance1){
                test = l;
                distance1 = distance2;
            }
        }
        if (test != null){
            world.move(this, test);
        }
    }
}
