package hausaufgabe;

import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

public class Animal extends Actor {
	private int age;
	
	/**
     * Ein Animal soll auch nicht, wie etwa der Bug,
     * andere Dinge aus dem Weg räumen, 
     * wie der Buges zumindest mit Blumen macht.
     * Es soll nur freie Felder betreten.
     */
	
	public void act()
    {
		this.setAge(this.getAge() + 1);
        if (canMove())
            move();
        else
            turn();
    }

    /**
     * Turns the bug 45 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * Moves the animal forward
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next;
        
        //searching for other animals next to this
        for(Location location:gr.getOccupiedLocations()){
        	Actor actor=gr.get(location);
        	if(actor instanceof Animal && actor!=this){
        		
        		//if the next position of the actor is in the adjacent position of the this
        		if(gr.getEmptyAdjacentLocations(loc).contains(actor.getLocation().getAdjacentLocation(actor.getDirection()))){
        			//look in direction of your friend
        			this.setDirection(loc.getDirectionToward(actor.getLocation().getAdjacentLocation(actor.getDirection())));
        			return;
        		}
        		
        		//if actor is at two boxes from this
        		if(this.getDistance(actor)<=2*Math.sqrt(2)&&this.getDistance(actor)>Math.sqrt(2)){
        			//then go towards him
        			next = loc.getAdjacentLocation(loc.getDirectionToward(actor.getLocation()));
                	if (gr.isValid(next)&&gr.get(next)==null){// exactly the same as the canMove function
                    	moveTo(next);
                    	return;
                	}
        		}
        		
        		//searching for the oldest Animal in the grid
        		Animal older=new Animal();
        		// iterer pour chercher le older
        		if(((Animal)(actor)).getAge()>older.getAge())
					older=(Animal)actor;//test to know whether the cow is the older
        		
        		//moving towards the oldest if there is one
        		if(older.getAge()!=0){
        			next = loc.getAdjacentLocation(loc.getDirectionToward(older.getLocation()));//here the oldest cow of the grid
                	if (gr.isValid(next)&&gr.get(next)==null){// exactly the same as the canMove function
                    	moveTo(next);
                    	return;
                	}
                }
        	}
        }

        //else doing the normal move
        next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
    }
    
    //Returns the minimal distance between two actors
    public double getDistance(Actor actor){
    	double xDistance,//Row
    	yDistance,//Col
    	totalDistance;
    	
    	xDistance=Math.abs(this.getLocation().getRow()-actor.getLocation().getRow());
    	yDistance=Math.abs(this.getLocation().getCol()-actor.getLocation().getCol());
    	totalDistance=Math.sqrt(Math.pow(xDistance, 2)+Math.pow(yDistance, 2));
    	
    	return totalDistance;
    }

    /**
     * Tests whether this bug can move forward into a location 
     * that is empty.
     * @return true if this bug can move.
     */
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next))
            return false;
        Actor neighbor = gr.get(next);
        return (neighbor == null);
        //return (neighbor == null) || !(neighbor instanceof Flower);
        // ok to move into empty location 
        // not ok to move onto any other actor or onto flower
    }

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public String toString(){
		return super.toString()+" age="+this.age;
	}

}
