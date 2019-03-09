package hausaufgabe;

import java.awt.Color;
import java.util.ArrayList;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Critter;
import gridworld.framework.actor.Flower;

public class Farmer extends Critter {
	
	public Farmer() {
		setColor(Color.MAGENTA);
	}
	
	private int FlowerRemoved;
	
		@Override
	   public void processActors(ArrayList<Actor> actors)
	    {
	        for (Actor a : actors)
	        {
	            if (a instanceof Flower){
	                a.removeSelfFromGrid();
	                ++FlowerRemoved;
	            }
	            
	        }
	    }

}
