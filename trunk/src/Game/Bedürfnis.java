/**
 * Repr�sentiert ein Bed�rfnis.
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

public class Bed�rfnis {
	
	private int name;
	private int wert;
	private int min;
	private int max;
	private int abfallfaktor;
	
	
	/**
	 * Erstellt ein neues Bed�rfnis.
	 * 
	 * Name:
	 * 1 = Nahrung
	 * 2 = Gesundheit
	 * 3 = Soziales
	 * 4 = Luxus
	 * 
	 * @param name
	 * @param wert
	 * @param min
	 * @param max
	 * @param abfallfaktor
	 */
	public Bed�rfnis(int name, int wert, int min, int max, int abfallfaktor) {
		super();
		this.name = name;
		this.wert = wert;
		this.min = min;
		this.max = max;
		this.abfallfaktor = abfallfaktor;
	}
	
	/**
	 * @return the name
	 */
	public String getName(int nr) {
		String name = new String("");
		
		switch (this.name)
		{
		case 1: name = "Hunger";
		case 2: name = "Gesundheit";
		case 3: name = "Soziales";
		case 4: name = "Luxus";
		
		}
		
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(int name) {
		this.name = name;
	}
	
	/**
	 * @return the wert
	 */
	public int getWert() {
		return wert;
	}
	
	/**
	 * @param wert the wert to set
	 */
	public void setWert(int wert) {
		this.wert = wert;
	}
	
	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}
	
	/**
	 * @param min the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}
	
	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}
	
	/**
	 * @param max the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}
	
	/**
	 * @return the abfallfaktor
	 */
	public int getAbfallfaktor() {
		return abfallfaktor;
	}
	
	/**
	 * @param abfallfaktor the abfallfaktor to set
	 */
	public void setAbfallfaktor(int abfallfaktor) {
		this.abfallfaktor = abfallfaktor;
	}
	
	/**
	 * �ndert den Wert dieses Bed�fnisses und addiert die �bergebene �nderung.
	 * 
	 * @param �nderung Die Wert�nderung die zum alten Wert addiert wird. Negative �nderungen m�glich.
	 * @return
	 */
	public boolean �ndereWert(int �nderung) {
		if(�nderung < min) {
			wert = min;
			return false;
		} else if(�nderung > max) {
			wert = max;
			return false;
		} else {
			wert = wert + �nderung;
			return true;
		}
	}

}
