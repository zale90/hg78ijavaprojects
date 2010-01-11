/**
 * Repr�sentiert einen vorgefertigten Charakter.
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

public class Avatar {
	
	private int avatarNummer;
	private String name;
	private String beschreibung;
	private Bed�rfnis[] bed�rfnisse;
	private int kontostand, einkommen, zeitProRunde;
	private String bild;
	
	/**
	 * @param beschreibung
	 * @param bed�rfnisse
	 * @param kontostand
	 */
	public Avatar(int nummer, String name , String beschreibung, Bed�rfnis[] bed�rfnisse, int kontostand, int einkommen, int zeitProRunde) {
		super();
		this.avatarNummer = nummer;
		this.setName(name);
		this.beschreibung = beschreibung;
		this.bed�rfnisse = bed�rfnisse;
		this.kontostand = kontostand;
		this.setEinkommen(einkommen);
		this.setZeitProRunde(zeitProRunde);
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
	
	/**
	 * @return the kontostand
	 */
	public int getAvatarNummer() {
		return avatarNummer;
	}

	/**
	 * @param avatarNummer the avatarNummer to set
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

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setZeitProRunde(int zeitProRunde) {
		this.zeitProRunde = zeitProRunde;
	}

	public int getZeitProRunde() {
		return zeitProRunde;
	}

	public void setEinkommen(int einkommen) {
		this.einkommen = einkommen;
	}

	public int getEinkommen() {
		return einkommen;
	}
	
}
