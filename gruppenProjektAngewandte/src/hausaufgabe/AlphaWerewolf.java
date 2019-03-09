package hausaufgabe;
import hausaufgabe.Cow;
import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Der Alpha Werwolf ist ein besonderer Werwolf, der dadurchm dass er genug Kälber gefressen hat in die Lage 
 * versetzt wird Milchfarmer (und nur solche) in Werwölfe zu verwandeln.
 * 
 */

public class AlphaWerewolf extends Farmer {
	
	public AlphaWerewolf() {
		setColor(Color.DARK_GRAY);
	}

	Deathtoll myDeathtoll = new Deathtoll ();
	
	@Override
	   public void processActors(ArrayList<Actor> actors)
	    {
		 for (Actor a : actors)
	        {
	        	if (a instanceof Eatable)
	            {
	            	a.removeSelfFromGrid();
	                ++this.myDeathtoll.deathtoll;
	            }
	        	
	        	else if (a instanceof Werewolfication) {
	        		 Grid<Actor> gr = getGrid();
	        		 if (gr == null)
	     	            return;
	        		Location loc= a.getLocation();
	        		a.removeSelfFromGrid();
	     			if (gr.isValid(loc)&&gr.get(loc)==null){
	     				//makes Farmer into Werewolf
	     		        	Werewolf neu= new Werewolf();
	     		        	neu.putSelfInGrid(gr, loc);
	     		        	neu.setColor(Color.gray);
	     		        	
	     		        }
	     		        
	     		        
	        	}
	        	
	        	else 
	        		return;
	        }
		 
		
	    }
}
	
	
		
	       
	            
	                
	     
	
		
		

				            	
	