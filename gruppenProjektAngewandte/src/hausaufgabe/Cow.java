package hausaufgabe;

import java.awt.Color;

import gridworld.framework.actor.Actor;
import hausaufgabe.Animal;
import gridworld.framework.actor.Critter;
import gridworld.framework.actor.Flower;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;
import gridworld.framework.world.World;

public class Cow extends Animal {
	
	public Cow() {
		setColor(Color.pink);
	}
	
	/**
     * Sie schaut mit einer Wahrscheinlichkeit von 1/6 
     * um sich (sie muss sich dafür nicht extra drehen)
     * und wenn ein Feld um sie herum frei ist, generiert sie eine weitere Cow und setzt sie dort hin.
     * 
     * Wenn sie älter als 12 Zeitschritte ist, 
     * löst sie sich mit einer Wahrscheinlichkeit von 1/4
     * auf und hinterlässt eine Flower.
     */
	
	@Override
	public void act()
    {
		if(this.getAge()>12&&Math.random()<0.25){//here is the probability to die
			Grid<Actor> gr = getGrid();
			Flower flower = new Flower(getColor());
			Location loc = this.getLocation();
			this.removeSelfFromGrid();
			flower.putSelfInGrid(gr, loc);
			return;
		}
		this.setAge(this.getAge() + 1);
		if (Math.random()<0.83){//probability to do a normal move
			if (canMove())
				super.move();
			else
				super.turn();
		}
		else{
			//sich umschaut / se retourner?
			for(int i = 0;i<4;i++){
				super.turn();
			}
			//generiert ein cow auf einer freie feld
			int direction=0;//variable to search the direction to have the claf
			while(!canHaveACalf(direction)&&direction<8)
				++direction;
			if (canHaveACalf(direction)){
				//move to the next location
				Grid<Actor> gr = getGrid();
		        if (gr == null)
		            return;
		        Location loc = getLocation();
		        Location next = loc.getAdjacentLocation(getDirection());
		        for(int i=0;i<direction;++i){
	        		next = loc.getAdjacentLocation(getDirection()+ Location.HALF_RIGHT);
	        	}
		        if (gr.isValid(next)&&gr.get(next)==null){
		            moveTo(next);
		        	}
		        else
		            removeSelfFromGrid();
		        //creation of the Calf
		        Calf neu= new Calf();
		        neu.setAge(0);
		        neu.setColor(Color.pink);
		        neu.putSelfInGrid(gr, loc);
			}
				
		}
    }
	
	public boolean canHaveACalf(int dir)
    {
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
        // ok to move into empty location 
        // not ok to move onto any other actor or onto flower
    }
	
	/**
     * Moves the cow in the direction of the oldest cow
     *
	@Override
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        
        //searching for the oldest cow in the grid
        
		Actor ac;
		//Initialization of the oldest
		Cow older=new Cow();
		

		for(Location l:gr.getOccupiedLocations()){
			ac=gr.get(l);
			if (ac instanceof Cow&&ac!=this){//does not work for the age of a Calf (sorry!)
				if(((Animal)(ac)).getAge()>older.getAge())
					older=(Cow)ac;//test to know whether the cow is the older
            }
		}
        
        //here the condition( is there another cow in this grid?)
        //if yes then:
		if(older.getAge()!=0){
			Location next = loc.getAdjacentLocation(loc.getDirectionToward(older.getLocation()));//here the oldest cow of the grid
        	if (gr.isValid(next)&&gr.get(next)==null)// exactly the same as the canMove function
            	moveTo(next);
        }
        
        //else{doing a classic move}{
		else{
			Location next = loc.getAdjacentLocation(getDirection());
			if (gr.isValid(next)&&gr.get(next)==null)// exactly the same as the canMove function
				moveTo(next);
			else
				removeSelfFromGrid();
		}
        
        
    }	*/
	

}
