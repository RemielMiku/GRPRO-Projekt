package itumulator.world;
import java.util.*;
import itumulator.simulator.Actor;

public class Bunny implements Actor{
    int age;
    int daysWithoutFood;
    int kidsAmount = 2;
    int reproduceTimer = 0;
    int cooldown_days = 2;
    int maxAge = 20;
    int adultAge = 2;
    int oldAge = 15;
    Hole hole;
    int searchRadius = 5;
    int chanceOfBaby = 50;

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
        if (world.getCurrentTime() % 20 == 0){
            daysWithoutFood++;
            age++;
            if (reproduceTimer > 0) {
                reproduceTimer--;

            } 
        }
        if (daysWithoutFood >= 3 || age >= maxAge) {
            world.delete(this);
            return;
        }
        if (!world.isOnTile(this)) {
            if (world.isDay()) {
                getUpBro(world);
            }
            return;
        }

        if (world.isNight()) {
            sleep(world);
            return;
        }
        move(world);

        if (daysWithoutFood >= 1) {
            eat(world);
        }
        if(canReproduce() && r.nextInt(100) < chanceOfBaby) {
            Bunny partner = findFuck(world);
            if (partner != null && newbunnyloc(world)) {
                this.reproduceTimer = cooldown_days;
                partner.reproduceTimer = cooldown_days;
            }

        }


    }

    boolean canReproduce() {
        return age >= adultAge && age < oldAge && reproduceTimer <= 0;
    }   
    private Bunny findFuck(World world) {
        Location thisLocation = world.getLocation(this);
        for(Location loc : world.getSurroundingTiles(thisLocation)) {
            Object bun = world.getTile(loc);
            if(bun instanceof Bunny && bun != this && ((Bunny) bun).canReproduce()) {
                return (Bunny) bun;
            }
        }
        return null;

    }

    public boolean newbunnyloc(World world){
            Set<Location> bunloc = world.getEmptySurroundingTiles(world.getLocation(this));
            List<Location> list = new ArrayList<>(bunloc);
            boolean born = false;

        for (int i = 0; i < kidsAmount && !list.isEmpty() ; i++) {
            int index = r.nextInt(list.size());
            Location place = list.remove(index);
            world.setTile(place, new Bunny());
            born = true;
        }
        return born;
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
        Location nowLocation = world.getLocation(this);
        Location eatLocation = null;
        int Dist = Integer.MAX_VALUE;
        for (Location loc : world.getSurroundingTiles(nowLocation, 10)) {
            if(world.getNonBlocking(loc) instanceof Grass && world.isTileEmpty(loc)){
                int dis = distanceCalc(nowLocation, loc);
                if (dis < Dist) {
                eatLocation = loc;
                Dist = dis;
                }
            }
        }
        if (eatLocation != null) {
           Object grass = world.getNonBlocking(eatLocation);
            world.move(this,eatLocation);
            world.delete(grass);
            daysWithoutFood = 0;

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
             hole = findHole(world, nowLocation);
            if (hole == null && !dig(world, nowLocation)){
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

        Location holeLocation = world.getLocation(hole);

        List<Location> available = new ArrayList<>(world.getEmptySurroundingTiles(holeLocation));
        if (world.isTileEmpty(holeLocation)) {
            available.add(holeLocation);
        }
        if (available.isEmpty()){
            return false;
        }
        world.setTile(available.get(r.nextInt(available.size())), this);
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
