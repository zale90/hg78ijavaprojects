package Game;

import Game.GUI.*;

public class SpielAnwendung {
	
	/**
	 * Das Fenster in dem das Spiel abl�uft.
	 */
	public static final Hauptfenster mainGUI = new Hauptfenster();
	
	/**
	 * Zeigt die Charakterauswahl f�r alle Avatare an. Diese werden aus dem Initialisator geladen.
	 */
	public static void zeigeCharakterauswahl() {
		mainGUI.zeigePanel(new Charakterauswahl(Initialisator.gibAvatare()));
	}
	
	/**
	 * Startet das Spiel f�r den �bergebenen Avatar
	 * 
	 * @param av Avatar f�r den das Spiel gestartet werden soll.
	 */
	public static void starteSpiel(Avatar av) {
		new Spiel(av, mainGUI);
	}
	
	/**
	 * Beendet das aktuelle Spiel, f�gt den �bergebenen Highscore an der passenden
	 * stelle in die Highscoreliste ein und zeigt diese anschlie�end an.
	 * 
	 * @param avatarNr Nr des Avatars f�r den die Liste angezeigt werden soll.
	 * @param avatarName Name des Avatars f�r den die Liste angezeigt werden soll.
	 * @param score Highscore der an der passenden Stelle in die vorher definierte Highscoreliste eingef�gt werden soll.
	 */
	public static void beendeSpiel(int avatarNr, String avatarName, Score score) {
		new Highscores(avatarNr, avatarName, score);
	}
	
	/**
	 * Beendet das aktuelle Spiel und zeigt die Highscoreliste an.
	 * 
	 * @param avatarNr Nr des Avatars f�r den die Liste angezeigt werden soll.
	 * @param avatarName Name des Avatars f�r den die Liste angezeigt werden soll.
	 */
	public static void beendeSpiel(int avatarNr, String avatarName) {
		new Highscores(avatarNr, avatarName);
	}
	
}
