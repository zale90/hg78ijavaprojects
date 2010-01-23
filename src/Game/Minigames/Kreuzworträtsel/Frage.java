package Game.Minigames.Kreuzworträtsel;

public class Frage {
	
	private String frage;
	private String antwort;
	private boolean ausrichtung;
	private int x, y;
	
	/*
	 * @param: ausrichtung true : horizontal
	 * 					   false: vertical
	 */
	public Frage (String frage, String antwort, boolean ausrichtung, int x, int y)
	{
		this.frage = frage;
		this.antwort = antwort;
		this.ausrichtung = ausrichtung;
		this.x = x;
		this.y = y;
	}
	
	public String gibFrage()
	{
		return frage;
	}
	
	public String gibAntwort()
	{
		return antwort;
	}
	
	public boolean gibAusrichtung()
	{
		return ausrichtung;
	}
	
	public int gibX()
	{
		return x;
	}
	
	public int gibY()
	{
		return y;
	}
}
