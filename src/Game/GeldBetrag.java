package Game;

public class GeldBetrag 
{
	private String name;
	private int betrag;
	
	public GeldBetrag(String name, int betrag){
		this.name = name;
		this.betrag = betrag;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setBetrag(int betrag){
		this.betrag = betrag;
	}
	
	public String getName(){
		return name;
	}
	
	public int getBetrag(){
		return betrag;
	}
}
