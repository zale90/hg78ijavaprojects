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
	 * @param zuÄndern 1=Nahrung, 2=N.faktor, 3=Gesundheit, 4=G.Faktor, 
	 * 				   5=Soziales, 6=S.Faktor 7=Luxus, 8=L.Faktor,
	 * 				   9=Kinder, 10=K.Faktor, 11=Zeit, 12=Zeit pro Runde, 
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
