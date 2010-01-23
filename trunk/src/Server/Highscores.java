package Server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import Game.Score;

public class Highscores {

	private final String scoreFileFolder = "files/data/";

	private ArrayList<Score> list;
	private String scoreFile = "";
	private String avatarName;

	/**
	 * Erstellt eine neue Highscoreverwaltung mit de Übergebenen Daten.
	 * 
	 * @param avatarNr
	 *            Nummer des Avatars.
	 * @param avatarName
	 *            Name des Avatars. Wird über der Liste angezeigt.
	 */
	public Highscores(int avatarNr, String avatarName) {

		this.avatarName = avatarName;

		scoreFile = scoreFileFolder + "scores/highscores_" + avatarNr + ".dat";

		list = loadListFromFile();

	}

	public String getAvatarName() {
		return avatarName;
	}
	
	/**
	 * Löscht den Highscore an der übergebenen Positioin.
	 * 
	 * @param nr Position des zu löschenden Highscores (1 - anzahl Scores).
	 */
	public Score deleteScore(int nr) {
		if(nr >= 1 && nr <= list.size()) {
			Score score = list.remove(nr-1);
			saveListToFile();
			return score;
		}
		return null;
	}

	/**
	 * Gibt die Highscoreliste zurück.
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
	 * Gibt den Score an der Übergebenen Position zurück. Achtung Indizierung
	 * beginnt mit 0!
	 * 
	 * @param pos
	 *            Position des zu liefenden Scores.
	 * @return Score an der übergebenen Position.
	 */
	public Score getScore(int pos) {
		if (pos < list.size()) {
			return list.get(pos);
		}
		return null;
	}

	/**
	 * Gibt die Anzahl der vorhandenen Scores zurück.
	 * 
	 * @return Anzahl vorhandener Scores.
	 */
	public int getScoresSize() {
		return list.size();
	}

	/**
	 * Fügt den übergebenen Score an der passenden Stelle in die Highscoreliste
	 * ein. Gibt es den Score bereits wird der neue am Platz darunter eingefügt.
	 * 
	 * @param score
	 *            Score der in die Highscoreliste eingefügt werden soll.
	 * @return Platz an dem der neue Highscore eingefügt wurde.
	 */
	public int insertIntoList(Score score) {
		int i = 0;
		while (i < getScoresSize() && getScore(i).getValue() > score.getValue()) {
			i++;
		}
		if (getScoresSize() > i && score.getValue() == getScore(i).getValue()) {
			i++;
		}
		list.add(i, score);
		saveListToFile();
		return i + 1;
	}

	/**
	 * Setzt die Scores zurück auf die voreingestellten.
	 */
	public void resetScores() {
		list = getStandardScores();
		saveListToFile();
	}

	/**
	 * Gibt eine ArrayList mit vorgefertigten Scores zurück.
	 * 
	 * @return Liste mit vorgefertigten Scores.
	 */
	private ArrayList<Score> getStandardScores() {
		list = new ArrayList<Score>();
		list.add(new Score("Spieler 1", 1000));
		list.add(new Score("Spieler 2", 800));
		list.add(new Score("Spieler 3", 600));
		list.add(new Score("Spieler 4", 400));
		list.add(new Score("Spieler 5", 200));

		return list;
	}

	/**
	 * Läd die Highscoreliste aus der in scoreFile definierten Datei und gibt
	 * sie zurück.
	 * 
	 * @return geladene Highscoreliste
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<Score> loadListFromFile() {
		ArrayList<Score> list = new ArrayList<Score>();

		InputStream fis = null;
		try {
			fis = new FileInputStream(scoreFile);
			ObjectInputStream in = new ObjectInputStream(fis);
			list = (ArrayList<Score>) in.readObject();
			fis.close();
			return list;
		} catch (IOException e) {
			list = getStandardScores();
			saveListToFile();
			System.out
					.println("Neue Highscoreliste wurde erstellt und gespeichert!");
			return list;
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	/**
	 * Speichert die Highscoreliste in der in scoreFile definierten Datei.
	 */
	private void saveListToFile() {
		OutputStream fos = null;

		try {
			fos = new FileOutputStream(scoreFile);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
			fos.close();
		} catch (IOException e) {
			System.out.println("Fehler beim Speichern der Highscores: " + e);
		}
	}

}
