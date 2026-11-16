package itumulator.world;
import java.util.Random;

import com.sun.tools.javac.Main;

import itumulator.simulator.Actor;

public class Bunny implements Actor{
    boolean hasEaten;
    boolean isAlive;
    int age;
    int daysWithoutFood;
    boolean isNear;
    Location location;

    Bunny(Location location) {
        this.location = location;
        hasEaten = false;
        isAlive = true;
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

    @Override
    public void act(World world){
        System.out.println("Bunny is acting");
    }

    void eat(World world) {
        for (int i = 0; i < world.getSurroundingTiles().size(); i++) {
            if (world.getSurroundingTiles(i).contains(Grass.class)) {
                hasEaten = true;
                resetDaysWithoutFood();
            }
        }
    }

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

}
