package Game;

import Game.GUI.*;

public class SpielAnwendung {
	
	/**
	 * Das Fenster in dem das Spiel abläuft.
	 */
	public static final Hauptfenster mainGUI = new Hauptfenster();
	
	/**
	 * Zeigt die Charakterauswahl für alle Avatare an. Diese werden aus dem Initialisator geladen.
	 */
	public static void zeigeCharakterauswahl() {
		mainGUI.zeigePanel(new Charakterauswahl(Initialisator.gibAvatare()));
	}
	
	/**
	 * Startet das Spiel für den übergebenen Avatar
	 * 
	 * @param av Avatar für den das Spiel gestartet werden soll.
	 */
	public static void starteSpiel(Avatar av) {
		new Spiel(av, mainGUI);
	}
	
	/**
	 * Beendet das aktuelle Spiel, fügt den übergebenen Highscore an der passenden
	 * stelle in die Highscoreliste ein und zeigt diese anschließend an.
	 * 
	 * @param avatarNr Nr des Avatars für den die Liste angezeigt werden soll.
	 * @param avatarName Name des Avatars für den die Liste angezeigt werden soll.
	 * @param score Highscore der an der passenden Stelle in die vorher definierte Highscoreliste eingefügt werden soll.
	 */
	public static void beendeSpiel(int avatarNr, String avatarName, Score score) {
		new Highscores(avatarNr, avatarName, score);
	}
	
	/**
	 * Beendet das aktuelle Spiel und zeigt die Highscoreliste an.
	 * 
	 * @param avatarNr Nr des Avatars für den die Liste angezeigt werden soll.
	 * @param avatarName Name des Avatars für den die Liste angezeigt werden soll.
	 */
	public static void beendeSpiel(int avatarNr, String avatarName) {
		new Highscores(avatarNr, avatarName);
	}
	
}
