/**
 * Repräsentiert einen vorgefertigten Charakter.
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

public class Avatar {
	
	private int avatarNummer;
	private String beschreibung;
	private Bedürfnis[] bedürfnisse;
	private int kontostand;
	private String bild;
	
	/**
	 * @param beschreibung
	 * @param bedürfnisse
	 * @param kontostand
	 */
	public Avatar(int nummer, String beschreibung, Bedürfnis[] bedürfnisse, int kontostand, String bild) {
		super();
		this.avatarNummer = nummer;
		this.beschreibung = beschreibung;
		this.bedürfnisse = bedürfnisse;
		this.kontostand = kontostand;
		this.bild = bild;
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
	
	/**
	 * @return the kontostand
	 */
	public int getAvatarNummer() {
		return avatarNummer;
	}

	/**
	 * @param kontostand the kontostand to set
	 */
	public void setAvatarNummer(int avatarNummer) {
		this.avatarNummer = avatarNummer;
	}
	
	/**
	 * @return the URL to the Avatar-Image
	 */
	public String getBildURL() {
		return bild;
	}
	
	/**
	 * @param bildURL the URL to the Image of the Avatar
	 */
	public void setBildURL(String bildURL) {
		this.bild = bildURL;
	}
	
}
