package Game;

import Game.GUI.*;
import java.util.*;

public class SpielAnwendung {
	
	public static final Hauptfenster mainGUI = new Hauptfenster();
	private static final ArrayList<Avatar> avList = Initialisator.gibAvatare();

	/**
	 * Initialisiert eine neue Anwendung des Spiels 
	 * und zeigt das Hauptfenster an.
	 */
	public SpielAnwendung() {
//		Initialisator init = new Initialisator();
//		avList = init.gibAvatare();
//		zeigeCharakterauswahl();
	}
	
	public static void zeigeCharakterauswahl() {
		mainGUI.zeigePanel(new Charakterauswahl(getAvatare()));
	}
	
	public static ArrayList<Avatar> getAvatare() {
		return avList;
	}
	
	public static void starteSpiel(Avatar av) {
		new Spiel(av, mainGUI);
	}
	
}
