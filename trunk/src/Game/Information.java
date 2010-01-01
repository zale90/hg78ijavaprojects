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
	 * @param zuÄndern 1=Nahrung, 2=N.faktor, 3=Gesundheit, 4=G.Faktor, 
	 * 				   5=Soziales, 6=S.Faktor 7=Luxus, 8=L.Faktor,
	 * 				   9=Zeit, 10=Zeit pro Runde, 11=Kinder, 12=K.Faktor
	 * 				   13=Geld, 14=Geld pro Monat
	 * @param änderungsart 1=auf Wert, 2=um Wert ändern, 3=auf Wert%, 4=um Wert% ändern
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
