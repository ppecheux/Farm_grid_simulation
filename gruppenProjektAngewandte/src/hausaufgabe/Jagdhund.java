package hausaufgabe;

import java.awt.Color;

import hausaufgabe.Werewolf;
import gridworld.framework.actor.Actor;
import hausaufgabe.Animal;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

/**
 * 
 * @author Sebastian
 * Der Jagdhund verfolgt Werwölfe und führt den Jäger zu ihnen.
 */

public class Jagdhund extends Animal{
	
	public Jagdhund() {
		
		float h = (float) 0.0555;
		float s = (float) 1;
		float b = (float) 0.5;
		setColor(Color.getHSBColor(h, s, b));
	}
	
	@Override
    public void move() {
		

        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        
        //searching for the nearest Werewolf
        
		Actor ac = null;
		//Initialization of the nearest
		Werewolf closest = null;
		double dis = 0;

		for(Location l:gr.getOccupiedLocations()) {
			ac=gr.get(l);
			if(ac instanceof Werewolf) {
				if (dis == 0) {
					closest = (Werewolf) ac;
					dis++;
				}
				else if (this.getDistance(ac)<this.getDistance(closest)) {
					closest = (Werewolf) ac;
				}
					
			}
		}
			
		if (closest != null) {
			if(this.getDistance(closest) != 0) {
				Location next = loc.getAdjacentLocation(loc.getDirectionToward(closest.getLocation()));//closest Werewolf in the grid
				if (gr.isValid(next)&&gr.get(next)==null)// exactly the same as the canMove function
			    	moveTo(next);
			}     
			//else{doing a classic move}
			
		}
		else 
			super.move();
        
		
	}
    

}
	
	

