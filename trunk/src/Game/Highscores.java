package Game;

import java.util.ArrayList;
import Game.GUI.Highscoreliste;

public class Highscores {

	private static final long serialVersionUID = 4767664434581498500L;

	private ArrayList<Score> list;
	private String avatarName;
	private int avatarNr;

	/**
	 * Zeigt die Highscoreliste des Avatars mit der �bergebenen Nummer an. Dabei
	 * wird der �bergebene Name angezeigt. Der �bergebenen Score wird au�erdem
	 * an passender Stelle eingef�gt und auch angezeigt.
	 * 
	 * @param avatarNr
	 *            Nummer des Avatars und somit der Highscoreliste.
	 * @param avatarName
	 *            Name des Avatars, der �ber der Liste angezeigt werden soll.
	 * @param score
	 *            Der Score der in die Liste an passender Stelle eingef�gt
	 *            werden soll.
	 */
	public Highscores(int avatarNr, String avatarName, Score score) {
		this.avatarNr = avatarNr;
		this.avatarName = avatarName;

		Network net = new Network(Optionen.NETWORK_ADDRESS,
				Optionen.NETWORK_PORT);
		net.sendeScore(score, avatarNr);
		list = net.empfangeHighscores(avatarNr);
		net.beendeKommunikation();

		zeigeHighscores();
	}

	/**
	 * Zeigt die Highscoreliste des Avatars mit der �bergebenen Nummer an. Dabei
	 * wird der �bergebene Name angezeigt.
	 * 
	 * @param avatarNr
	 *            Nummer des Avatars und somit der Highscoreliste.
	 * @param avatarName
	 *            Name des Avatars, der �ber der Liste angezeigt werden soll.
	 */
	public Highscores(int avatarNr, String avatarName) {
		this.avatarName = avatarName;

		Network net = new Network(Optionen.NETWORK_ADDRESS,
				Optionen.NETWORK_PORT);
		list = net.empfangeHighscores(avatarNr);
		net.beendeKommunikation();

		zeigeHighscores();
	}

	public String getAvatarName() {
		return avatarName;
	}

	/**
	 * Aktualisiert die Highscores mit dem Daten vom Server.
	 */
	public void aktualisiereAusNetzwerk() {
		Network net = new Network(Optionen.NETWORK_ADDRESS,
				Optionen.NETWORK_PORT);
		list = net.empfangeHighscores(avatarNr);
		net.beendeKommunikation();
	}

	/**
	 * Gibt die Highscoreliste zur�ck.
	 * 
	 * @return Highscoreliste
	 */
	public ArrayList<Score> getHighscores() {
		return list;
	}

	/**
	 * Liefert den Score auf dem letzten Platz der Highscoreliste.
	 * 
	 * @return Score auf dem letzten Platz der Highscoreliste.
	 */
	public Score getLast() {
		return list.get(list.size() - 1);
	}

	/**
	 * Liefert den Score auf Platz 1 der Highscoreliste.
	 * 
	 * @return Score auf Platz 1 der Highscoreliste.
	 */
	public Score getFirst() {
		return list.get(0);
	}

	/**
	 * Gibt den Score an der �bergebenen Position zur�ck. Achtung Indizierung
	 * beginnt mit 0!
	 * 
	 * @param pos
	 *            Position des zu liefenden Scores.
	 * @return Score an der �bergebenen Position.
	 */
	public Score getScore(int pos) {
		if (pos < list.size()) {
			return list.get(pos);
		}
		return null;
	}

	/**
	 * Gibt die Anzahl der vorhandenen Scores zur�ck.
	 * 
	 * @return Anzahl vorhandener Scores.
	 */
	public int getScoresSize() {
		return list.size();
	}

	/**
	 * Benutzt die aktuelle Highscoreliste um diese im Spiel anzuzeigen.
	 */
	private void zeigeHighscores() {
		Highscoreliste scoreGUI = new Highscoreliste(this);
		SpielAnwendung.mainGUI.zeigePanel(scoreGUI);
	}

}
