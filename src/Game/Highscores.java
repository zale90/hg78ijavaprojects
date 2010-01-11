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

	public Highscores(int avatarNr, String avatarName, Score score) {
		this.avatarName = avatarName;
		
		scoreFile = scoreFileFolder + "scores/highscores_" + avatarNr + ".dat";
		
		list = loadListFromFile();
		
		insertIntoList(score);
		
		zeigeHighscores();
	}
	
	public String getAvatarName() {
		return avatarName;
	}

	public void setAvatarName(String avatarName) {
		this.avatarName = avatarName;
	}

	public Highscores() {
		list = loadListFromFile();
		
		zeigeHighscores();
	}
	
	public ArrayList<Score> getHighscores() {
		return list;
	}
	
	public Score getLast() {
		return list.get(list.size()-1);
	}
	
	public Score getFirst() {
		return list.get(0);
	}
	
	public Score getScore(int pos) {
		if(pos < list.size()) {
			return list.get(pos);
		}
		return null;
	}
	
	public int getScoresSize() {
		return list.size();
	}
	
	public int insertIntoList(Score score) {
		int i = 0;
		while(i < getScoresSize() && getScore(i).getValue() >  score.getValue()) {
			i++;
		}
		if(getScoresSize() > i && score.getValue() == getScore(i).getValue()) {
			i++;
		}
		list.add(i, score);
		saveListToFile();
		return i;
	}
	
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
	
	private void zeigeHighscores() {
		Highscoreliste scoreGUI = new Highscoreliste(this);
		SpielAnwendung.mainGUI.zeigePanel(scoreGUI);
	}

}
