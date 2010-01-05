package Game;

import Game.GUI.*;
import java.util.*;

public class SpielAnwendung {
	
	private static Hauptfenster mainGUI = new Hauptfenster();
	private static ArrayList<Avatar> avList;

	/**
	 * Initialisiert eine neue Anwendung des Spiels 
	 * und zeigt das Hauptfenster an.
	 */
	public SpielAnwendung() {
		Initialisator init = new Initialisator();
		avList = init.gibAvatare();
		zeigeCharakterauswahl();
	}
	
	private static void zeigeCharakterauswahl() {
		mainGUI.zeigePanel(new Charakterauswahl(getAvatare()));
	}
	
	public static ArrayList<Avatar> getAvatare() {
		return avList;
	}
	
	public static void starteSpiel(Avatar av) {
		Spiel spiel = new Spiel(av, mainGUI);
	}
	
}
