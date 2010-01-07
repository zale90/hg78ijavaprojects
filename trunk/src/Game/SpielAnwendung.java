package Game;

import Game.GUI.*;
import java.util.*;

public class SpielAnwendung {
	
	public static final Hauptfenster mainGUI = new Hauptfenster();
	private static ArrayList<Avatar> avList = Initialisator.gibAvatare();
	private static ArrayList<Ereignis> erList;

	/**
	 * Initialisiert eine neue Anwendung des Spiels 
	 * und zeigt das Hauptfenster an.
	 */
	public SpielAnwendung() {
		avList = Initialisator.gibAvatare();
		zeigeCharakterauswahl();
	}
	
	public static void zeigeCharakterauswahl() {
		mainGUI.zeigePanel(new Charakterauswahl(getAvatare()));
	}
	
	public static ArrayList<Avatar> getAvatare() {
		return avList;
	}
	
	public static ArrayList<Ereignis> getEreignisse() {
		return erList;
	}
	
	public static void starteSpiel(Avatar av) {
		new Spiel(av, mainGUI);
	}
	
}
