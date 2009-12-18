package Game;

import Game.GUI.*;
import java.util.*;

public class SpielAnwendung {
	
	private Hauptfenster mainGUI = new Hauptfenster();
	private ArrayList<Avatar> avatarliste;

	/**
	 * Initialisiert eine neue Anwendung des Spiels 
	 * und zeigt das Hauptfenster an.
	 */
	public SpielAnwendung() {
		Initialisator init = new Initialisator();
		avatarliste = init.gibAvatare();
		zeigeCharakterauswahl();
	}
	
	private void zeigeCharakterauswahl() {
		mainGUI.zeigePanel(new Charakterauswahl());
	}
	
}
