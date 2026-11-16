import java.awt.Color;

import itumulator.executable.DisplayInformation;
import itumulator.executable.Program;
import itumulator.world.Location;
import itumulator.world.World;

public class Main {

    public static void main(String[] args) {
        int size = 5;
        Program p = new Program(size, 800, 75);

        World w = p.getWorld();
        
        // w.setTile(new Location(0, 0), new <MyClass>());

        // p.setDisplayInformation(<MyClass>.class, new DisplayInformation(<Color>, "<ImageName>"));
        
        // Jamie arbejde ting med hole
        /* 
        Displayinformation Hole = new DisplayInformation(Color.black, "hole");
        p.setDisplayInformation(Hole.class, Hole);
        */

        /* p.show();
        for (int i = 0; i < 200; i++) {
            p.simulate();

        } */
    }
}