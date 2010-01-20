package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import Game.Score;
import Server.GUI.AdminGUI;

public class ServerThread extends Thread {
	
	private Socket client;
	private BufferedReader br;
	private PrintWriter pw;
	private ObjectOutputStream oos;
	
	public ServerThread(Socket client) {
		this.client = client;
	}
	
	public void run() {
		kommunizieren();
	}
	
	private void kommunizieren() {
		AdminGUI.consoleText("Kommunikation mit Client (" + client.getLocalAddress().getHostName() + ") wird aufgenommen!");
		try {
			
			// Streams erzeugen
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
			oos = new ObjectOutputStream(client.getOutputStream());
			
			// Kommunikation
			bearbeiteKommunikation();
		} catch (IOException e) {
			AdminGUI.consoleText("Fehler: " + e.getMessage());
		}
	}
	
	private void bearbeiteKommunikation() throws IOException {
		String input = "";
		
		pw.println("StarteKommunikation");
		if(br.readLine().equals("StarteKommunikation")) {
			
			// Anfragen unterscheiden
			input = br.readLine();
			//System.out.println(input);
			if(input.equals("WarteAufListe")) {
				sendeListe();
				bearbeiteKommunikation();
			} else if(input.equals("ScoreEinfuegen")) {
				scoreEinfügen();
				bearbeiteKommunikation();
			} else if(input.equals("Bye")) {
				pw.println("Bye");
				client.close();
			}
		}
		
	}
	
	private void sendeListe() throws IOException {
		pw.println("SendeScores");
		if(br.readLine().equals("EmpfangeScores")) {
			int avNummer = Integer.parseInt(br.readLine());
			oos.writeObject(ScoreServer.getList()[avNummer-1].getHighscores());
			pw.println("SendeScoresAbgeschlossen");
		}
	}
	
	private void scoreEinfügen() throws IOException {
		pw.println("WarteAufScore");
		if(br.readLine().equals("SendeScore")) {
			int avNummer = Integer.parseInt(br.readLine());
			String name = br.readLine();
			int score = Integer.parseInt(br.readLine());
			
			Highscores scores = ScoreServer.getList()[avNummer-1];
			scores.insertIntoList(new Score(name, score));
			AdminGUI.consoleText("Highscore eingefügt: " + name + " | " + score);
			
			pw.println("EmpfangenAbgeschlossen");
		}
	}
}
