package hausaufgabe;

import gridworld.framework.actor.ActorWorld;
import gridworld.framework.grid.Location;
import java.awt.Color;
import hausaufgabe.Cow;
import hausaufgabe.Jagdhund;
import hausaufgabe.AlphaWerewolf;
import hausaufgabe.CreatorFarmer;
import hausaufgabe.Farmer;
import hausaufgabe.Werewolf;
import hausaufgabe.Jaeger;
import hausaufgabe.MilkFarmer;
public class FarmWorldRunner {

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
//        Cow alice = new Cow();
        Farmer didier = new Farmer();
        world.add(new Location(3,5), didier);    
//        alice.setColor(Color.ORANGE);
        CreatorFarmer Gabriel= new CreatorFarmer();
//        Werewolf Sophie = new Werewolf();
        world.add(new Location(5,3),Gabriel);
//        world.add(new Location(4, 8), didier);
//        world.add(new Location(7, 8), alice);
//        world.add(new Location(3,7), Sophie);
        world.show();
        Werewolf w = new Werewolf();
        world.add(new Location(2,2), w);
//        AlphaWerewolf aw = new AlphaWerewolf();
//        world.add(new Location(2,5), aw);
        MilkFarmer m = new MilkFarmer();
        world.add(new Location(2,7), m);
        Jaeger j = new Jaeger();
        world.add(new Location(10,10),j);
       Jagdhund fifi = new Jagdhund();
       world.add(new Location(5,5), fifi);
        
    }
}
