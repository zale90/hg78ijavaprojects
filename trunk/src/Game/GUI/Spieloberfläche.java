package Game.GUI;

import java.awt.*;
import javax.swing.*;
import Game.*;

public class Spieloberfläche extends JPanel {

	private static final long serialVersionUID = 846296549789605647L;
	
	private JTextArea console;
	private JLabel lblHeading, lblZeit, lblGeld;
	private JProgressBar[] bedürfnisBars;
	private Spiel spiel;
	
	public Spieloberfläche(Spiel spiel) {
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
		
		console = new JTextArea("Willkommen im Spiel " + Optionen.NAME + "!\nSie haben ein neues Spiel gestartet. Viel Spaß!");
		console.setEditable(false);
		console.setSize(995, 100);
		console.setLocation(0, 568);
		this.add(console);
		
		initializeBars(spiel.getBedürfnisse());
		
		// Verbleibende Zeit anzeigen
		JLabel lblVerbleibendeZeit = new JLabel("Verbleibende Zeit:");
		lblVerbleibendeZeit.setSize(100, 20);
		lblVerbleibendeZeit.setLocation(850, 280);
		this.add(lblVerbleibendeZeit);
		
		lblZeit = new JLabel("" + spiel.getZeit());
		lblZeit.setSize(100, 20);
		lblZeit.setLocation(945, 280);
		this.add(lblZeit);
		
		
		// Kontostand anzeigen
		JLabel lblKontostand = new JLabel("Kontostand:");
		lblKontostand.setSize(100, 20);
		lblKontostand.setLocation(850, 300);
		this.add(lblKontostand);
		
		lblGeld = new JLabel("" + spiel.getKontostand());
		lblGeld.setSize(100, 20);
		lblGeld.setLocation(945, 300);
		this.add(lblGeld);
		
		this.setVisible(true);
	}
	
	public void zeigeNachrichtInKonsole(String nachricht) {
		console.setText(nachricht + "\n" + console.getText());
	}
	
	private void initializeBars(Bedürfnis[] bedürfnisse) {
		// BedürnisBars-Arrays initialisieren mit den Werden aus bedürfnisse
		bedürfnisBars = new JProgressBar[bedürfnisse.length];
		for(int i = 0; i < bedürfnisse.length; i++) {
			Point pos = getPositionForBars(i);
			bedürfnisBars[i] = new JProgressBar(bedürfnisse[i].getMin(), bedürfnisse[i].getMax());
			bedürfnisBars[i].setValue(bedürfnisse[i].getWert());
			bedürfnisBars[i].setSize(130, 20);
			bedürfnisBars[i].setLocation(pos);
			this.add(bedürfnisBars[i]);
			JLabel lbl = new JLabel(bedürfnisse[i].getName());
			lbl.setSize(100, 20);
			lbl.setLocation(pos.x, pos.y-20);
			this.add(lbl);
		}
	}
	
	private Point getPositionForBars(int i) {
		int xPos = 850;
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
