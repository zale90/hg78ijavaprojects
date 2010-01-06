/**
 * Repräsentiert jeweils ein Ereignis.
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

import Game.GUI.Ereignisfenster;

public class Ereignis {
	
	private int nummer = 1;
	private String ereignisname;
	private String ereignistext;
	private boolean ereignistyp;
	private Information[] ja;
	private Information[] nein;
	private Ereignisfenster fenster;
	
	public Ereignis() {
		// Ereignis generieren
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
