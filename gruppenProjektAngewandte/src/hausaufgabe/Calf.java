package hausaufgabe;

import java.awt.Color;

import hausaufgabe.Eatable;
import hausaufgabe.Animal;
import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

public class Calf extends Animal implements Eatable{
	
	public Calf() {
		setColor(Color.pink);
	}
	
	private Cow mother;
	
	@Override
	public void act(){
		if(this.getAge()==4){
			//searching where to create the cow
			Grid<Actor> gr = getGrid();
	        if (gr == null)
	            return;
			Location loc= this.getLocation();
			Location next = loc.getAdjacentLocation(getDirection());
			Cow neu= new Cow();
        	neu.setAge(4);
        	
	        if (gr.isValid(next)&&gr.get(next)==null){
			//creation of the cow in the next field
	        	neu.putSelfInGrid(gr, next);
	        	this.removeSelfFromGrid();
	        }
	        else{
	        	//this.setLocation(null);
	        	//if (gr.isValid(loc)&&gr.get(loc)==null){
	    			//creation of the cow in the place if the next is not possible
	    	        neu.putSelfInGrid(gr, loc);
	    	     //   }
	        	//this.removeSelfFromGrid();
	        }

		}
		else
			super.act();
	}

	public Cow getMother() {
		return mother;
	}

	public void setMother(Cow mother) {
		this.mother = mother;
	}
	
	
}
