package hausaufgabe;

import java.awt.Color;
import java.util.ArrayList;

import hausaufgabe.Calf;
import hausaufgabe.Cow;
import hausaufgabe.Jagdhund;
import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

public class CreatorFarmer extends Farmer {
	
	public CreatorFarmer() {
		setColor(Color.ORANGE);
	}
	
	@Override
	   public void processActors(ArrayList<Actor> actors)
	    {
	        if (this.getCowNumber(actors) == 0){
	        	Grid<Actor> gr = getGrid();
	            if (gr == null)
	                return;
	            Location loc = getLocation();
	            Location next = loc.getAdjacentLocation(getDirection());
	            if (gr.isValid(next)&&gr.get(next)==null){
	                moveTo(next);
	                //creation of the Calf
		            Calf neu= new Calf();
		            neu.setAge(0);
		            neu.putSelfInGrid(gr, loc);
	            	}
	            else
	                //removeSelfFromGrid();
	            	this.setDirection(getDirection()+45);
	        }
	        if (this.getCowNumber(actors) > 20) {
//	        	checking the number of cows, if to many spawn werewolf
		        	Grid<Actor> gr = getGrid();
		            if (gr == null)
		                return;
		            Location loc = getLocation();
		            Location next = loc.getAdjacentLocation(getDirection());
		            if (gr.isValid(next)&&gr.get(next)==null){
		                moveTo(next);
		                //creation of the werewolf when there are to many cows
			            Werewolf w= new Werewolf();
			            w.putSelfInGrid(gr, loc);
		            	}
		            else
		            	this.setDirection(getDirection()+45);
		        
	        }
	        if (this.getWerewolfNumber(actors) == 10) {
	        	Grid<Actor> gr = getGrid();
	            if (gr == null)
	                return;
	            Location loc = getLocation();
	            Location next = loc.getAdjacentLocation(getDirection());
	            if (gr.isValid(next)&&gr.get(next)==null){
	                moveTo(next);
	                //creation of the Jagdhund when there are to many Werewolfs
		            Jagdhund j = new Jagdhund();
		            j.putSelfInGrid(gr, loc);
	            	}
	            else
	            	this.setDirection(getDirection()+45);
	        }
	    }
	
	private int getCowNumber(ArrayList<Actor> actors){
		int CowNumber=0;
		Grid<Actor> gr = getGrid();
		Actor ac;
		for(Location l:gr.getOccupiedLocations()){
			ac=gr.get(l);
			if (ac instanceof Cow||ac instanceof Calf){
                ++CowNumber;
            }
		}

		return CowNumber;
	}
	private int getWerewolfNumber(ArrayList<Actor> actors){
		int WerewolfNumber=0;
		Grid<Actor> gr = getGrid();
		Actor ac;
		for(Location l:gr.getOccupiedLocations()){
			ac=gr.get(l);
			if (ac instanceof Werewolf||ac instanceof AlphaWerewolf){
                ++WerewolfNumber;
            }
		}

		return WerewolfNumber;
	}

}
