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
	 * @param zu�ndern 1=Nahrung, 2=N.faktor, 3=Gesundheit, 4=G.Faktor, 
	 * 				   5=Soziales, 6=S.Faktor 7=Luxus, 8=L.Faktor,
	 * 				   9=Zeit, 10=Zeit pro Runde, 11=Kinder, 12=K.Faktor
	 * 				   13=Geld, 14=Geld pro Monat
	 * @param �nderungsart 1=auf Wert, 2=um Wert �ndern, 3=auf Wert%, 4=um Wert% �ndern
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
