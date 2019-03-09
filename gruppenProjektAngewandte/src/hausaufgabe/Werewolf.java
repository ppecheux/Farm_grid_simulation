package hausaufgabe;

import hausaufgabe.Cow;
import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Flower;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

public class Werewolf extends Farmer {
//	jeder Werwolf bekommt einen eigenen Z�hler f�r gerissene K�lber
	Deathtoll myDeathtoll = new Deathtoll();
	
	public Werewolf () {
		float h = (float) 0;
		float s = (float) 0;
		float b = (float) 0.5;
		setColor(Color.getHSBColor(h, s, b));

	}
//	stellt sicher, dass der J�ger nicht auf einen angrenzenden Actor etc. gesetzt wird
	//ist nicht wichtig!!
	public boolean cancallJaeger(int dir) {
		Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        	Location next = loc.getAdjacentLocation(getDirection());
        	for(int i=0;i<dir;++i){
        		next = loc.getAdjacentLocation(getDirection()+ Location.HALF_RIGHT);
        	}
        	if (!gr.isValid(next)||gr.get(next)!=null)
        		return false;
        	Actor neighbor = gr.get(next);
        	return (neighbor == null) && !(neighbor instanceof Flower) && !(neighbor instanceof Actor);
	}
	
	@Override
	   public void processActors(ArrayList<Actor> actors)
	    {
//		suche nach essbaren K�lbern
		
	        for (Actor a : actors)
	        {
	            if (a instanceof Eatable){
	                a.removeSelfFromGrid();
	                ++this.myDeathtoll.deathtoll;
	            }
	            if (this.myDeathtoll.deathtoll  == 10) {
//	            	spawns hunter in adjacent Location to the Creator 
//	                Der Creator ruft einen J�ger, wenn zu viele K�lber sterben.
		            
	            	for (Actor b : actors)
			        {
			            if (b instanceof CreatorFarmer){
			            	int direction = 0;
			            	while(!cancallJaeger(direction)&&direction<8)
			    				++direction;
			            	if (cancallJaeger(direction)) {
			            		Grid<Actor> gr = getGrid();
				            	Jaeger neu =new Jaeger();
				            	if (gr == null) 
				            		return;
								Location loc = b.getLocation();
								Location adj = loc.getAdjacentLocation(getDirection());
								neu.putSelfInGrid(gr, adj);
			            	}
			            }
			        }
	            }
	            
	        }
	    }
		
//    Wenn der Werwolf genug K�lber frisst wird er zum Alpha Werwolf.
	@Override
	public void act() {
		if (this.myDeathtoll.deathtoll == 15) {
			Grid<Actor> gr = getGrid();
	        if (gr == null)
	            return;
			Location loc= this.getLocation();
			Location next = loc.getAdjacentLocation(getDirection());
	        if (gr.isValid(next)&& gr.get(next)== null){
	        	AlphaWerewolf neu= new AlphaWerewolf();
	        	neu.putSelfInGrid(gr, next);
	        }
	        this.removeSelfFromGrid();
		}
		else 
			super.act();
	}
	
}
