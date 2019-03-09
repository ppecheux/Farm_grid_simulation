package hausaufgabe;

import hausaufgabe.Jagdhund;
import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Critter;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

public class Jaeger extends Critter {
	
	public Jaeger() {
	
		float h = (float) 0.33;
		float s = (float) 1;
		float b = (float) 0.5;
		setColor(Color.getHSBColor(h, s, b));
	}
	
	@Override
	   public void processActors(ArrayList<Actor> actors)
	    {
	        for (Actor a : actors)
	        {
	            if (a instanceof Werewolf){
	                a.removeSelfFromGrid();
	            }
	            else if (a instanceof AlphaWerewolf) {
	            	a.removeSelfFromGrid();
	            }
	        }
	    }
	@Override
	public void act()
    {
        if (getGrid() == null)
            return;
        ArrayList<Actor> actors = getActors();
        processActors(actors);
        ArrayList<Location> moveLocs = getMoveLocations();
        Location loc = selectMoveLocation(moveLocs);
        makeMove(loc);
       
//        if (this.getAge() > 10 ) {
//        	this.removeSelfFromGrid();
//        }
    }
	@Override
    public void makeMove(Location loc) {
		
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc1 = getLocation();
        
        //searching for the Jagdhund
        
		Actor ac = null;
		//Initialization of the nearest
		Jagdhund closest = null;
		double dis = 0;

		for(Location l:gr.getOccupiedLocations()) {
			ac=gr.get(l);
			if(ac instanceof Jagdhund) {
				if (dis == 0) {
					closest = (Jagdhund) ac;
					dis++;
				}
				else if (this.getDistance(ac)<this.getDistance(closest)) {
					closest = (Jagdhund) ac;
				}
					
			}
		}
			
		if (closest != null) {
			if(this.getDistance(closest) != 0) {
				Location next = loc1.getAdjacentLocation(loc1.getDirectionToward(closest.getLocation()));//closest Werewolf in the grid
				if (gr.isValid(next)&&gr.get(next)==null)// exactly the same as the canMove function
			    	moveTo(next);
			}     
			//else{doing a classic move}
			
		}
		else {
			super.makeMove(loc);
		}
		
	}

}
