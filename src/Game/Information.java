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
	
	public static final int AENDERN_NAHRUNG = 1;
	public static final int AENDERN_NAHRUNGS_FAKTOR = 2;
	public static final int AENDERN_GESUNDHEIT = 3;
	public static final int AENDERN_GESUNDHEITS_FAKTOR = 4;
	public static final int AENDERN_SOZIALES = 5;
	public static final int AENDERN_SOZIALES_FAKTOR = 6;
	public static final int AENDERN_LUXUS = 7;
	public static final int AENDERN_LUXUS_FAKTOR = 8;
	public static final int AENDERN_KINDER = 9;
	public static final int AENDERN_KINDER_FAKTOR = 10;
	public static final int AENDERN_ZEIT = 11;
	public static final int AENDERN_ZEIT_PRO_RUNDE = 12;
	public static final int AENDERN_GELD = 13;
	public static final int AENDERN_GELD_PRO_MONAT = 14;
	
	/**
	 * Setzt den Wert auf den �bergebenen Wert fest. Dies geschieht unabh�ngig vom aktuellen Wert.
	 */
	public static final int ART_AUF_WERT = 1;
	/**
	 * Addiert den neuen Wert auf den aktuellen Wert drauf. Um einen Wert abzuziehen, muss ein negativer Wert �bergeben werden.
	 */
	public static final int ART_UM_WERT = 2;
	/**
	 * Setzt den Wert auf einen bestimmten Prozentsatz fest. Dies geschieht unabh�ngig vom aktuellen Wert.
	 */
	public static final int ART_AUF_PROZENTWERT = 3;
	/**
	 * Erh�ht den Wert um eine bestimmten Prozentsatz.
	 */
	public static final int ART_UM_PROZENTWERT = 4;
	
	/**
	 * @param zu�ndern Gibt den den Wert an, der ge�ndert werden sollte. Dabei k�nnen alle Konstanten mit dem Anfang AENDERN_ benutzt werden.
	 * @param �nderungsart Gibt die Art der �nderung an. Dabei k�nnen die Konstanten mit dem Anfang ART_ benutzt werden.
	 * @param wert Der neue Wert.
	 */
	public Information(int zu�ndern, int �nderungsart, int wert) {
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
