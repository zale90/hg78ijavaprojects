/**
 * Repr�sentiert die Ver�nderung an einer Information
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

public class Information {

	private int zu�ndern;
	private int �nderungsart;
	private int wert;
	
	
	/**
	 * @param zu�ndern
	 * @param �nderungsart
	 * @param wert
	 */
	public Information(int zu�ndern, int �nderungsart, int wert) {
		super();
		this.zu�ndern = zu�ndern;
		this.�nderungsart = �nderungsart;
		this.wert = wert;
	}


	/**
	 * @return the zu�ndern
	 */
	public int getZu�ndern() {
		return zu�ndern;
	}


	/**
	 * @return the �nderungsart
	 */
	public int get�nderungsart() {
		return �nderungsart;
	}


	/**
	 * @return the wert
	 */
	public int getWert() {
		return wert;
	}
	
}
