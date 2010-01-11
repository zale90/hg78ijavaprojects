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
	
	public static final int ART_AUF_WERT = 1;
	public static final int ART_UM_WERT = 2;
	public static final int ART_AUF_PROZENTWERT = 3;
	public static final int ART_UM_PROZENTWERT = 4;
	
	/**
	 * @param zu�ndern 1=Nahrung, 2=N.faktor, 3=Gesundheit, 4=G.Faktor, 
	 * 				   5=Soziales, 6=S.Faktor 7=Luxus, 8=L.Faktor,
	 * 				   9=Kinder, 10=K.Faktor, 11=Zeit, 12=Zeit pro Runde, 
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
