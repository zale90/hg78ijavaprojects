package Main;

import Game.Score;
import Game.SpielAnwendung;

public class Launcher {

	/**
	 * Startet eine neue Anwendung des Spiels.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpielAnwendung.zeigeCharakterauswahl();
//		SpielAnwendung.beendeSpiel(new Score("Testspieler", 550));
	}

}
