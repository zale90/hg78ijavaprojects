package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Network {

	private PrintWriter pw;
	private BufferedReader br;
	private ObjectInputStream ois;
	private Socket server;
	private boolean hasError = false;

	/**
	 * Erstellt ein neues Netzwerk-Objekt zur Kommunikation mit dem Game-Server.
	 * 
	 * @param host
	 *            Netzwerkadresse des Servers.
	 * @param port
	 *            Port des Servers.
	 */
	public Network(String host, int port) {

		try {
			server = new Socket(host, port);
			pw = new PrintWriter(server.getOutputStream(), true);
			br = new BufferedReader(new InputStreamReader(server
					.getInputStream()));
			ois = new ObjectInputStream(server.getInputStream());
		} catch (UnknownHostException e) {
			printError(new Exception("Highscore-Server konnte nicht gefunden werden!"));
		} catch (IOException e) {
			printError(e);
		}

	}

	/**
	 * Leite die Kommunikation mit dem Server ein.
	 * 
	 * @return true wenn Kommunikation reibungslos gestartet wurde.
	 * @throws IOException
	 */
	private boolean starteKommunikation() throws IOException {
		if (br.readLine().equals("StarteKommunikation")) {
			pw.println("StarteKommunikation");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gibt die Highscores des Severs zurück.
	 * 
	 * @param avatarNr
	 *            Nummer des Avatars, dessen Liste geholt werden soll.
	 * @return ArrayList<Score>
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Score> empfangeHighscores(int avatarNr) {
		try {
			if (starteKommunikation()) {
				pw.println("WarteAufListe");
				if (br.readLine().equals("SendeScores")) {
					pw.println("EmpfangeScores");
					pw.println(avatarNr);
					ArrayList<Score> scores = (ArrayList<Score>) ois
							.readObject();
					if (br.readLine().equals("SendeScoresAbgeschlossen")) {
						return scores;
					}
				}
			}
			System.out.println("Konnte Highscores nicht empfangen!");
			return null;
		} catch (IOException e) {
			printError(e);
		} catch (ClassNotFoundException e) {
			printError(e);
		}
		return null;
	}

	/**
	 * Fügt einen neuen Score in die Highscoreliste auf dem Server ein.
	 * 
	 * @param score
	 *            Score der eingefügt werden soll.
	 * @param avNummer
	 *            Nummer des Avatars, in dessen Liste der Score eingefügt werden
	 *            soll.
	 * @return true wenn erfolgreich eingefügt.
	 */
	public boolean sendeScore(Score score, int avatarNr) {
		try {
			if (starteKommunikation()) {
				pw.println("ScoreEinfuegen");
				if (br.readLine().equals("WarteAufScore")) {
					pw.println("SendeScore");
					pw.println(avatarNr);
					pw.println(score.getName());
					pw.println(score.getValue());

					if (br.readLine().equals("EmpfangenAbgeschlossen")) {
						return true;
					}
				}
			}
		} catch (IOException e) {
			printError(e);
		}
		return false;
	}

	/**
	 * Beendet die Kommunikation mit dem Server und schließt die Verbindung.
	 */
	public void beendeKommunikation() {
		try {
			if (starteKommunikation()) {
				pw.println("Bye");
				if (br.readLine().equals("Bye")) {
					server.close();
				} else {
					hasError = true;
				}
			} else {
				hasError = true;
			}
		} catch (IOException e) {
			printError(e);
		}
	}

	/**
	 * Gibt eine Fehlermeldung auf der KONSOLE aus!
	 * 
	 * @param e
	 *            Exception dessen Fehler ausgegeben werden soll.
	 */
	private void printError(Exception e) {
		hasError = true;
		System.out.println("Netzwerk-Fehler:");
		System.out.println(e.getMessage());
	}
	
	public boolean hasError() {
		return hasError;
	}
}
