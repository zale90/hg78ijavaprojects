package Game;

import Game.GUI.*;
import java.util.*;

public class SpielAnwendung {
	
	private Hauptfenster mainGUI = new Hauptfenster();
	private ArrayList<Avatar> avList;

	/**
	 * Initialisiert eine neue Anwendung des Spiels 
	 * und zeigt das Hauptfenster an.
	 */
	public SpielAnwendung() {
		Initialisator init = new Initialisator();
		avList = init.gibAvatare();
		zeigeCharakterauswahl();
	}
	
	private void zeigeCharakterauswahl() {
		mainGUI.zeigePanel(new Charakterauswahl(getAvatare()));
	}
	
	public ArrayList<Avatar> getAvatare() {
		return avList;
	}
	
}
