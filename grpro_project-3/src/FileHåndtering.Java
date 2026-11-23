import itumulator.world.*;
import java.util.*;

public class FileHaandtering {
    Random r = new Random();
    public static int Size(Path file) throws IOException {
        return Integer.parseInt(Files.readAllLines(file).get(0).trim());
    }
    
    public static void bootUp(World world, Path file) throws IOException{
        List<String> linjer = Files.readAllLines(file);
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            
            String[] delElementer = line.split("\\s++");
            int mængdeTingTing = parseAmount(parts[1]);
            for ( int w = 0; w < mængdeTingTing; w++) {
                place(world, delElementer[0]);
            }
        }
    }
    int parseAmount(String tekst) {
        if (!tekst.contain("-")) {
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
        for ( int i = 0; i < world.getSize(); i++) {
            for (int n = 0; n < world.getSize(); n++) {
                Location loc = new Location(x,y);
                boolean ledig;
                if (nonBlocking == true) {
                    ledig = !world.containsNonBlocking(loc);
                } else {
                    ledig = world.isTileEmpty(l);
                }
                if (ledig == true) {
                ledig.add(loc);
                }
           }
        }
        Location loc = ledig.get(r.nextInt(ledig.size()));

        if (type.equals("grass")) {
            world.setTile(loc, new Grass(world, loc, 0.1))
        } else if (type.equals("rabbit")) {
            world.setTile(loc, new Bunny())
        }else if (type.equals("burrow")) {
            world.setTile(loc, new Hole())
        }
    }
}
        