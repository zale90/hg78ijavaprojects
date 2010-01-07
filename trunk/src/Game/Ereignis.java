/**
 * Repräsentiert jeweils ein Ereignis.
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

import Game.GUI.Ereignisfenster;

public class Ereignis {
	
	// Ereignisdaten
	private int nummer = 1;
	private String name;
	private String text;
	// True = Ja / Nein | False = OK (Informationen in ja)
	private boolean typ;
	private Information[] ja;
	private Information[] nein;
	
	private Ereignisfenster fenster;
	
	public Ereignis(int nummer, String name, String text, boolean typ, Information[] ja, Information[] nein) {
		this.nummer = nummer;
		this.name = name;
		this.text = text;
		this.typ = typ;
		this.ja = ja;
		this.nein = nein;
	}

	public void ausführen() {
		fensterErzeugen();
	}
	
	private void fensterErzeugen() {
		fenster = new Ereignisfenster(this);
	}
	
	public Information[] ja() {
		return null;
	}
	
	public Information[] nein() {
		return null;
	}
	
	public int getNummer() {
		return nummer;
	}

}
