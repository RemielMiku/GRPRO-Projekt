package itumulator.world;
import itumulator.simulator.Actor;;

public class Person implements Actor {
    @Override
    public void act(World world) {
        System.out.println("I ain't doin’ nothin’!");
    }
}
