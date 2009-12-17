/**
 * Repräsentiert einen vorgefertigten Charakter.
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

public class Avatar {
	
	private String beschreibung;
	private Bedürfnis[] bedürfnisse;
	private int kontostand;
	
	/**
	 * @param beschreibung
	 * @param bedürfnisse
	 * @param kontostand
	 */
	public Avatar(String beschreibung, Bedürfnis[] bedürfnisse, int kontostand) {
		super();
		this.beschreibung = beschreibung;
		this.bedürfnisse = bedürfnisse;
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
	 * @return the bedürfnisse
	 */
	public Bedürfnis[] getBedürfnisse() {
		return bedürfnisse;
	}

	/**
	 * @param bedürfnisse the bedürfnisse to set
	 */
	public void setBedürfnisse(Bedürfnis[] bedürfnisse) {
		this.bedürfnisse = bedürfnisse;
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
