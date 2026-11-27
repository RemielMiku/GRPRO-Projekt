import itumulator.world.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;

public class FileHaandtering {
    static Random r = new Random();
    public static int Size(Path file) throws IOException {
        return Integer.parseInt(Files.readAllLines(file).get(0).trim());
    }
    
    public static void bootUp(World world, Path file) throws IOException{
        List<String> lines = Files.readAllLines(file);
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            
            String[] delElementer = line.split("\\s++");
            int mængdeTingTing = parseAmount(delElementer[1]);
            for ( int w = 0; w < mængdeTingTing; w++) {
                place(world, delElementer[0]);
            }
        }
    }
    int parseAmount(String tekst) {
        if (!tekst.contains("-")) {
            return Integer.parseInt(tekst);
        }
        String[] Scoop = tekst.split("-");
        int min = Integer.parseInt(Scoop[0]);
        int max = Integer.parseInt(Scoop[1]);
        return min + r.nextInt(max-min+1);
    }

    static void place(World world, String type) {
        boolean nonBlocking = !type.equals("rabbit");
        List<Location> ledige = new ArrayList<>();
        // x og y var ikke defineret så jeg prøver at erstatte i og n i for-løkkerne
        for ( int x = 0; x < world.getSize(); x++) {
            for (int y = 0; y < world.getSize(); y++) {
                Location loc = new Location(x,y);
                boolean erLedig;
                if (nonBlocking == true) {
                    erLedig = !world.containsNonBlocking(loc);
                } else {
                    erLedig = world.isTileEmpty(loc);
                }
                if (erLedig) {
                ledige.add(loc);
                }
           }
        }
        Location loc = ledige.get(r.nextInt(ledige.size()));

        if (type.equals("grass")) {
            world.setTile(loc, new Grass(world, loc, 0.1));
        } else if (type.equals("rabbit")) {
            world.setTile(loc, new Bunny());
        }else if (type.equals("burrow")) {
            world.setTile(loc, new Hole());
        }
    }
}
        