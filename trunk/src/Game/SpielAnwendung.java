package Game;

import Game.GUI.*;

public class SpielAnwendung {
	
	public static final Hauptfenster mainGUI = new Hauptfenster();
	
	public static void zeigeCharakterauswahl() {
		mainGUI.zeigePanel(new Charakterauswahl(Initialisator.gibAvatare()));
	}
	
	public static void starteSpiel(Avatar av) {
		new Spiel(av, mainGUI);
	}
	
	public static void beendeSpiel(int listAvatar, Score score) {
		new Highscores(listAvatar, score);
	}
	
	public static void beendeSpiel() {
		new Highscores();
	}
	
}
