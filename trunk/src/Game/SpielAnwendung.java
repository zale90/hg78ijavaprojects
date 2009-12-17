package Game;

import Game.GUI.*;

public class SpielAnwendung {
	
	public static final Hauptfenster mainGUI = new Hauptfenster();

	/**
	 * Initialisiert eine neue Anwendung des Spiels 
	 * und zeigt das Hauptfenster an.
	 */
	public SpielAnwendung() {
		zeigeCharakterauswahl();
	}
	
	private void zeigeCharakterauswahl() {
		mainGUI.zeigePanel(new Charakterauswahl());
	}
	
}
