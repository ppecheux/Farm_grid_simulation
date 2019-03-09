package hausaufgabe;
/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import java.awt.Color;

import hausaufgabe.Cow;
import hausaufgabe.Jagdhund;
import hausaufgabe.AlphaWerewolf;
import hausaufgabe.CreatorFarmer;
import hausaufgabe.Farmer;
import hausaufgabe.Werewolf;
import gridworld.framework.actor.ActorWorld;
import gridworld.framework.grid.Location;
import hausaufgabe.Jaeger;
import hausaufgabe.MilkFarmer;

/**
 * This class runs a world that contains box Animals. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
/**
 *Die auskommentierten Akteure wurden zum Überprüfen der Funktionalität benutzt und 
 *drin gelassen.
 *
 */
public class BoxfarmRunner
{
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