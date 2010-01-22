package Game;

import java.io.Serializable;

public class Score implements Serializable {

	private static final long serialVersionUID = 1455247205828865451L;

	private String name;
	private long value;

	/**
	 * Kombination von Name und Punkte für einen Highscore.
	 * 
	 * @param name
	 *            Name für die Highscoreliste.
	 * @param value
	 *            Punkte für die Highscoreliste.
	 */
	public Score(String name, long value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public long getValue() {
		return value;
	}

}