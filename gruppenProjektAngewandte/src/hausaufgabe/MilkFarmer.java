package hausaufgabe;

import hausaufgabe.Cow;
import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Flower;
import gridworld.framework.actor.MilkTank;

import java.awt.Color;
import java.util.ArrayList;

public class MilkFarmer extends Farmer implements Werewolfication{
	
	public MilkFarmer () {
		setColor(Color.blue);
	}
	
	MilkTank myMilkTank=new MilkTank();
	
	
	//sollen wir ein spezielles konstruktor hier haben um my milktank zu ersetzen?
	
	
	@Override
	   public void processActors(ArrayList<Actor> actors)
	    {
	        for (Actor a : actors)
	        {
	            if (a instanceof Flower){
	                a.removeSelfFromGrid();
	            }
	            if (a instanceof Cow){
	            	++this.myMilkTank.MilkMenge;
	            }
	            
	        }
	        
	    }
    public String toString(){
		return super.toString()+" Milch="+this.myMilkTank;}

}
