package Game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import Game.GUI.Highscoreliste;

public class Highscores {
	
	private static final long serialVersionUID = 4767664434581498500L;

	private final String scoreFileFolder = "files/data/";
	
	private ArrayList<Score> list;
	private String scoreFile = "";
	private String avatarName;
	
	/**
	 * Zeigt die Highscoreliste des Avatars mit der �bergebenen Nummer an.
	 * Dabei wird der �bergebene Name angezeigt.
	 * Der �bergebenen Score wird au�erdem an passender Stelle eingef�gt
	 * und auch angezeigt.
	 * 
	 * @param avatarNr Nummer des Avatars und somit der Highscoreliste.
	 * @param avatarName Name des Avatars, der �ber der Liste angezeigt werden soll.
	 * @param score Der Score der in die Liste an passender Stelle eingef�gt werden soll.
	 */
	public Highscores(int avatarNr, String avatarName, Score score) {
		this.avatarName = avatarName;
		
		scoreFile = scoreFileFolder + "scores/highscores_" + avatarNr + ".dat";
		
		list = loadListFromFile();
		
		insertIntoList(score);
		
		zeigeHighscores();
	}
	
	/**
	 * Zeigt die Highscoreliste des Avatars mit der �bergebenen Nummer an.
	 * Dabei wird der �bergebene Name angezeigt.
	 * 
	 * @param avatarNr Nummer des Avatars und somit der Highscoreliste.
	 * @param avatarName Name des Avatars, der �ber der Liste angezeigt werden soll.
	 */
	public Highscores(int avatarNr, String avatarName) {
		this.avatarName = avatarName;
		
		scoreFile = scoreFileFolder + "scores/highscores_" + avatarNr + ".dat";
		
		list = loadListFromFile();
		
		zeigeHighscores();
	}
	
	public String getAvatarName() {
		return avatarName;
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
		return list.get(list.size()-1);
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
	 * Gibt den Score an der �bergebenen Position zur�ck.
	 * Achtung Indizierung beginnt mit 0!
	 * 
	 * @param pos Position des zu liefenden Scores.
	 * @return Score an der �bergebenen Position.
	 */
	public Score getScore(int pos) {
		if(pos < list.size()) {
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
	 * F�gt den �bergebenen Score an der passenden Stelle in die Highscoreliste ein.
	 * Gibt es den Score bereits wird der neue am Platz darunter eingef�gt.
	 * 
	 * @param score Score der in die Highscoreliste eingef�gt werden soll.
	 * @return Platz an dem der neue Highscore eingef�gt wurde.
	 */
	private int insertIntoList(Score score) {
		int i = 0;
		while(i < getScoresSize() && getScore(i).getValue() >  score.getValue()) {
			i++;
		}
		if(getScoresSize() > i && score.getValue() == getScore(i).getValue()) {
			i++;
		}
		list.add(i, score);
		saveListToFile();
		return i+1;
	}
	
	/**
	 * L�d die Highscoreliste aus der in scoreFile definierten Datei und gibt sie zur�ck.
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
		} catch(IOException e) {
			list.add(new Score("Spieler 1", 1000));
			list.add(new Score("Spieler 2", 800));
			list.add(new Score("Spieler 3", 600));
			list.add(new Score("Spieler 4", 400));
			list.add(new Score("Spieler 5", 200));
			saveListToFile();
			System.out.println("Neue Highscoreliste wurde erstellt und gespeichert!");
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
	
	/**
	 * Benutzt die aktuelle Highscoreliste um diese im Spiel anzuzeigen.
	 */
	private void zeigeHighscores() {
		Highscoreliste scoreGUI = new Highscoreliste(this);
		SpielAnwendung.mainGUI.zeigePanel(scoreGUI);
	}

}
