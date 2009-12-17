/**
 * Repr�sentiert einen vorgefertigten Charakter.
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

public class Avatar {
	
	private String beschreibung;
	private Bed�rfnis[] bed�rfnisse;
	private int kontostand;
	
	/**
	 * @param beschreibung
	 * @param bed�rfnisse
	 * @param kontostand
	 */
	public Avatar(String beschreibung, Bed�rfnis[] bed�rfnisse, int kontostand) {
		super();
		this.beschreibung = beschreibung;
		this.bed�rfnisse = bed�rfnisse;
		this.kontostand = kontostand;
	}

	/**
	 * @return the beschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * @param beschreibung the beschreibung to set
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * @return the bed�rfnisse
	 */
	public Bed�rfnis[] getBed�rfnisse() {
		return bed�rfnisse;
	}

	/**
	 * @param bed�rfnisse the bed�rfnisse to set
	 */
	public void setBed�rfnisse(Bed�rfnis[] bed�rfnisse) {
		this.bed�rfnisse = bed�rfnisse;
	}

	/**
	 * @return the kontostand
	 */
	public int getKontostand() {
		return kontostand;
	}

	/**
	 * @param kontostand the kontostand to set
	 */
	public void setKontostand(int kontostand) {
		this.kontostand = kontostand;
	}
	
	
	
	
	

}
