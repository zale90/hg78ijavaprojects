/**
 * Repräsentiert ein Bedürfnis.
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

public class Bedürfnis {
	
	private int name;
	private int wert;
	private int min;
	private int max;
	private int abfallfaktor;
	
	
	/**
	 * Erstellt ein neues Bedürfnis.
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
	public Bedürfnis(int name, int wert, int min, int max, int abfallfaktor) {
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
	 * Ändert den Wert dieses Bedüfnisses und addiert die übergebene Änderung.
	 * 
	 * @param änderung Die Wertänderung die zum alten Wert addiert wird. Negative Änderungen möglich.
	 * @return
	 */
	public boolean ändereWert(int änderung) {
		if(änderung < min) {
			wert = min;
			return false;
		} else if(änderung > max) {
			wert = max;
			return false;
		} else {
			wert = wert + änderung;
			return true;
		}
	}

}
