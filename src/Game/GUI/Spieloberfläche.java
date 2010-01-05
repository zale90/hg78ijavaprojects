package Game.GUI;

import java.awt.*;
import javax.swing.*;
import Game.*;

public class Spieloberfl�che extends JPanel {

	private static final long serialVersionUID = 846296549789605647L;
	
	private JTextArea console;
	private JLabel lblHeading;
	private JProgressBar[] bed�rfnisBars;
	private Spiel spiel;
	
	public Spieloberfl�che(Spiel spiel) {
		this.setSize(1000, 700);
		this.setLocation(0, 0);
		this.setLayout(null);
		this.setBackground(null);
		
		this.spiel = spiel;
		
		// GUI-Elemente erzeugen
		lblHeading = new JLabel(Optionen.NAME);
		lblHeading.setFont(Optionen.FONT_TITLE);
		lblHeading.setSize(1000, 20);
		lblHeading.setLocation(20, 20);
		this.add(lblHeading);
		
		
		/* Scrollpane funktioniert noch nicht
			console = new JTextArea("Hallo");
			//console.setEditable(false);
	        JScrollPane spConsole = new JScrollPane(console);
	        //spConsole.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	        spConsole.setSize(995, 100);
	        spConsole.setLocation(0, 568);
			this.add(spConsole);
	 	*/
		
		console = new JTextArea("Willkommen im Spiel " + Optionen.NAME + "!\nSie haben ein neues Spiel gestartet. Viel Spa�!");
		console.setEditable(false);
		console.setSize(995, 100);
		console.setLocation(0, 568);
		this.add(console);
		
		initializeBars(spiel.getBed�rfnisse());
		
		this.setVisible(true);
	}
	
	public void zeigeNachrichtInKonsole(String nachricht) {
		console.setText(nachricht + "\n" + console.getText());
	}
	
	private void initializeBars(Bed�rfnis[] bed�rfnisse) {
		// Bed�rnisBars-Arrays initialisieren mit den Werden aus bed�rfnisse
		bed�rfnisBars = new JProgressBar[bed�rfnisse.length];
		for(int i = 0; i < bed�rfnisse.length; i++) {
			Point pos = getPositionForBars(i);
			bed�rfnisBars[i] = new JProgressBar(bed�rfnisse[i].getMin(), bed�rfnisse[i].getMax());
			bed�rfnisBars[i].setValue(bed�rfnisse[i].getWert());
			bed�rfnisBars[i].setSize(100, 20);
			bed�rfnisBars[i].setLocation(pos);
			this.add(bed�rfnisBars[i]);
			JLabel lbl = new JLabel(bed�rfnisse[i].getName());
			lbl.setSize(100, 20);
			lbl.setLocation(pos.x, pos.y-20);
			this.add(lbl);
		}
	}
	
	private Point getPositionForBars(int i) {
		int xPos = 870;
		if(i == 0) {
			return new Point(xPos, 100);
		} else if(i == 1) {
			return new Point(xPos, 150);
		} else if(i == 2) {
			return new Point(xPos, 200);
		} else if(i == 3) {
			return new Point(xPos, 250);
		}
		
		return null;
	}

}
