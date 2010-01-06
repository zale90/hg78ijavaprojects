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
	 * 0 = Nahrung
	 * 1 = Gesundheit
	 * 2 = Soziales
	 * 3 = Luxus
	 * 4 = Kinder
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
		
		switch (nr)
		{
			case 0: name = "Nahrung";
					break;
			case 1: name = "Gesundheit";
					break;
			case 2: name = "Soziales";
					break;
			case 3: name = "Luxus";
					break;
			case 4: name = "Kinder";
					break;
		}
		
		return name;
	}
	
	public String getName() {
		return getName(name);
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
	

//Methode ausgelagert in Spiel, diese übernimmt jetzt alle wertändernden Aufgaben, also auch Zeit und andere Faktoren mit Hilfe von Informationen
//Nachteil: Wir müssen immer 'ne Information erstellen Vorteil: Es kann quasi nichts schief gehen und alles ist super einheitlich! (Vorteil überwiegt, ganz klar... ;) )
//	/**
//	 * Ändert den Wert dieses Bedüfnisses und addiert die übergebene Änderung.
//	 * 
//	 * @param änderung Die Wertänderung die zum alten Wert addiert wird. Negative Änderungen möglich.
//	 * @return
//	 */
//	public boolean ändereWert(int änderung) {
//		if(änderung < min) {
//			wert = min;
//			return false;
//		} else if(änderung > max) {
//			wert = max;
//			return false;
//		} else {
//			wert = wert + änderung;
//			return true;
//		}
//	}

}
