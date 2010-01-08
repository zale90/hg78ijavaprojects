/**
 * Repr�sentiert jeweils ein Ereignis.
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

import javax.swing.*;

public class Ereignis {
	
	// Ereignisdaten
	private int nummer = 1;
	private String name;
	private String text;
	// True = Ja / Nein | False = OK (Informationen in ja)
	private boolean typ;
	private Information[] ja;
	private Information[] nein;
	
	public Ereignis(int nummer, String name, String text, boolean typ, Information[] ja, Information[] nein) {
		this.nummer = nummer;
		this.name = name;
		this.text = text;
		this.typ = typ;
		this.ja = ja;
		this.nein = nein;
	}

	public Information[] ausf�hren() {
		int antwort = fensterAnzeigen();
		if(antwort == JOptionPane.YES_OPTION) {
			return ja;
		} else if(antwort == JOptionPane.NO_OPTION) {
			return nein;
		}
		return null;
	}
	
	private int fensterAnzeigen() {
		int optionType = JOptionPane.YES_NO_OPTION;
		Object[] options = {"Ja", "Nein"};
		if(!getTyp()) {
			optionType = JOptionPane.YES_OPTION;
			options = new Object[1];
			options[0] = "OK";
		}
		return JOptionPane.showOptionDialog(SpielAnwendung.mainGUI, getText(), 
				getName(), optionType, JOptionPane.INFORMATION_MESSAGE, null,
				options, options[0]);
	}

	/**
	 * @return the nummer
	 */
	public int getNummer() {
		return nummer;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the typ
	 */
	public boolean getTyp() {
		return typ;
	}

	/**
	 * @param typ the typ to set
	 */
	public void setTyp(boolean typ) {
		this.typ = typ;
	}

	/**
	 * @return the ja
	 */
	public Information[] getJa() {
		return ja;
	}

	/**
	 * @param ja the ja to set
	 */
	public void setJa(Information[] ja) {
		this.ja = ja;
	}

	/**
	 * @return the nein
	 */
	public Information[] getNein() {
		return nein;
	}

	/**
	 * @param nein the nein to set
	 */
	public void setNein(Information[] nein) {
		this.nein = nein;
	}

	/**
	 * @param nummer the nummer to set
	 */
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	
	

}