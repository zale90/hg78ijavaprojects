package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Game.Avatar;
import Game.Initialisator;
import Game.Optionen;
import Game.Score;
import Server.GUI.AdminGUI;
import Server.GUI.ServerGUI;

public class ScoreServer {

	private boolean running = true;
	private static Highscores[] list;
	private static ServerGUI gui;

	/**
	 * Startet einen neuen Server und zeigt die grafische Oberfläche an.
	 */
	public ScoreServer() {

		// Verwaltung initialisieren
		ArrayList<Avatar> avList = Initialisator.gibAvatare();
		list = new Highscores[avList.size()];
		for (int i = 0; i < avList.size(); i++) {
			Avatar av = avList.get(i);
			list[i] = new Highscores(av.getAvatarNummer(), av.getName());
		}

		// GUI erzeugen
		gui = new ServerGUI(list);

		// Server starten
		startServer();

		System.out.println("Server beendet!");
	}

	/**
	 * Startet den Server und erstellt für jeden Client der sich anmeldet einen
	 * neuen ServerThread mit dem entsprechenden Socket des Clients.
	 */
	private void startServer() {
		try {
			ServerSocket server = new ServerSocket(Optionen.NETWORK_PORT);
			String message = "Server gestartet:\nIP: "
					+ InetAddress.getLocalHost().getHostName() + "\nPort: "
					+ server.getLocalPort();
			AdminGUI.consoleText(message);

			while (running) {
				Socket client = server.accept();
				ServerThread thread = new ServerThread(client);
				thread.start();
			}
		} catch (IOException e) {
			System.out.println("Fehler beim Erstellen des Servers:");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setzt die Highscores alle Avatare zurück.
	 */
	public static void resetHighscores() {
		for (Highscores scores : list) {
			scores.resetScores();
		}
		gui.aktualisiereTabellen();
		AdminGUI.consoleText("Die Highscores aller Avatare wurden zurück gesetzt!");
	}

	/**
	 * Löscht den Highscore an der übergebenen Position und in der übergebenen
	 * Liste.
	 * 
	 * @param scoreNr
	 *            Position des Scores in seiner Liste.
	 * @param listNr
	 *            Liste aus der der Score gelöscht werden soll (1 - 3).
	 */
	public static void deleteScore(int scoreNr, int listNr) {
		if (listNr >= 1 && listNr <= list.length) {
			Score score = list[listNr-1].deleteScore(scoreNr);
			if(score != null) {
				AdminGUI.consoleText("Score wurde gelöscht: " + score.getValue() + " | " + score.getName());
			} else {
				AdminGUI.consoleText("Highscore konnte nicht gelöscht werden: Score: " + scoreNr + " | Liste: " + listNr);
			}
		}
		gui.aktualisiereTabellen();
	}

	public static void aktualisiereTabellen() {
		gui.aktualisiereTabellen();
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public static Highscores[] getList() {
		return list;
	}

	public static void setList(Highscores[] list) {
		ScoreServer.list = list;
	}

}
