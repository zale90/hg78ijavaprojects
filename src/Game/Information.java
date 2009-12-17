/**
 * Repräsentiert die Veränderung an einer Information
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

public class Information {

	private int zuÄndern;
	private int änderungsart;
	private int wert;
	
	
	/**
	 * @param zuÄndern
	 * @param änderungsart
	 * @param wert
	 */
	public Information(int zuÄndern, int änderungsart, int wert) {
		super();
		this.zuÄndern = zuÄndern;
		this.änderungsart = änderungsart;
		this.wert = wert;
	}


	/**
	 * @return the zuÄndern
	 */
	public int getZuÄndern() {
		return zuÄndern;
	}


	/**
	 * @return the änderungsart
	 */
	public int getÄnderungsart() {
		return änderungsart;
	}


	/**
	 * @return the wert
	 */
	public int getWert() {
		return wert;
	}
	
}
