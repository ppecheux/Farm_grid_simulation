package gridworld.framework.actor;

public class MilkTank extends Actor {
	
	public int MilkMenge;
	
	public MilkTank(){
		this.MilkMenge=0;
	}
	
	public String toString(){
		return "MilkTank gefüllt mit : " + this.MilkMenge + " Litern.";
	}
	

}
