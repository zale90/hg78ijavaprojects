package Game.GUI;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;
import Game.*;

public class Spieloberfläche extends JPanel implements MouseListener {

	private static final long serialVersionUID = 846296549789605647L;
	
	private JTextPane console;
	private JLabel lblHeading, lblZeit, lblGeld, lblGeldProMonat, lblZeitpunkt;  //fällt jemandem ein besseres Wort für Einkommen ein, außer Einkommen?
	private JProgressBar[] bedürfnisBars;
	private Spiel spiel;
	/*
	 * interaktive Schaltflächen; erstmal mit JLabels, damit wir flexibler sind
	 */
	private JLabel rundeWeiter, spielNeuStarten;
	
	public Spieloberfläche(Spiel spiel, String charName) {
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
		
		/*
		 * Ich hab die JTextArea durch ein JTextPane ersetzt, weil der JScrollPane immer automatisch runter scrollte,
		 * was man hier mit der Methode "setCaretPosition(Integer)" verhindern kann.
		 * Das es auch in JTextArea diese Methode gibt ist mir erst nachher aufgefallen.
		 * Dafür können wir jetzt, falls wir das noch brauchen sollten, den Text in der Konsole besser designen,
		 * z.B. bestimmte Wörter einzeln hervorheben, Zeilen anders einfärben, u.s.w. 
		 * Außerdem hat das JTextPane einen schönen Rahmen. ^^
		 */
		console = new JTextPane();
		console.setEditable(false);
		console.setSize(800, 90);
		console.setLocation(20, 568);
		console.setText("Willkommen im Spiel " + Optionen.NAME + "!\nSie haben ein neues Spiel gestartet. Viel Spaß!");
		this.add(console);
		
		JScrollPane spConsole = new JScrollPane(console);
		spConsole.setSize(800, 90);
        spConsole.setLocation(20, 568);
        spConsole.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(spConsole);
		
		/*
		 * Nur ein Testpanel, um zu schauen welche Größe wir für das Spielfeld einplanen müssen - ich finds so gut
		 */
		JPanel spielfeldTest = new Spielbereich(0,0, this);
		spielfeldTest.setSize(800, 500);
		spielfeldTest.setLocation(20, 60);
		this.add(spielfeldTest);
		
		JLabel name = new JLabel(charName);
		name.setSize(140, 20);
		name.setLocation(840,60);
		name.setHorizontalAlignment(0);
		name.setFont(new Font("Serif", Font.BOLD, 16));
		this.add(name);
		
		JLabel avatarbild = new JLabel(new ImageIcon("files/avatarImages/" + charName + "Klein.jpg"));
		avatarbild.setSize(140,140);
		avatarbild.setLocation(840, 90);
		this.add(avatarbild);
		
		initializeBars(spiel.getBedürfnisse());
		
		// Verbleibende Zeit anzeigen
		JLabel lblVerbleibendeZeit = new JLabel("Verbleibende Zeit:");
		lblVerbleibendeZeit.setSize(100, 20);
		lblVerbleibendeZeit.setLocation(840, 435);
		this.add(lblVerbleibendeZeit);
		
		lblZeit = new JLabel("" + spiel.getZeit());
		lblZeit.setSize(100, 20);
		lblZeit.setLocation(935, 435);
		this.add(lblZeit);
		
		
		// Kontostand anzeigen
		JLabel lblKontostand = new JLabel("Kontostand:");
		lblKontostand.setSize(100, 20);
		lblKontostand.setLocation(840, 455);
		this.add(lblKontostand);
		
		lblGeld = new JLabel("" + spiel.getKontostand());
		lblGeld.setSize(100, 20);
		lblGeld.setLocation(935, 455);
		this.add(lblGeld);
		
		JLabel lblEinkommen = new JLabel("Einkommen:");
		lblEinkommen.setSize(100, 20);
		lblEinkommen.setLocation(840, 475);
		this.add(lblEinkommen);
		
		lblGeldProMonat = new JLabel(spiel.getGeldProMonat() + "");
		lblGeldProMonat.setSize(100, 20);
		lblGeldProMonat.setLocation(935, 475);
		this.add(lblGeldProMonat);
		
		lblZeitpunkt = new JLabel("1. Woche, 1. Monat");
		lblZeitpunkt.setSize(140,20);
		lblZeitpunkt.setLocation(840, 520);
		lblZeitpunkt.setHorizontalAlignment(0);
		this.add(lblZeitpunkt);
		
		rundeWeiter = new JLabel("Runde beenden");
		rundeWeiter.setSize(140, 40);
		rundeWeiter.setLocation(840 ,568);
		rundeWeiter.setOpaque(true);
		rundeWeiter.setBackground(Color.GREEN);
		rundeWeiter.setHorizontalAlignment(0);
		rundeWeiter.addMouseListener(this);
		this.add(rundeWeiter);
		
		/*
		 * Den sollten wir wahrscheinlich noch woanders hinpacken,
		 * der ist nur an der Stelle, weil ich noch Platz hatte.
		 */
		spielNeuStarten = new JLabel("Neues Spiel starten");
		spielNeuStarten.setSize(140, 40);
		spielNeuStarten.setLocation(840 ,618);
		spielNeuStarten.setOpaque(true);
		spielNeuStarten.setBackground(Color.RED);
		spielNeuStarten.setHorizontalAlignment(0);
		spielNeuStarten.addMouseListener(this);
		this.add(spielNeuStarten);
		
		this.setVisible(true);
	}
	
	public void zeigeNachrichtInKonsole(String nachricht) {
		console.setText(nachricht + "\n" + console.getText());
		console.setCaretPosition(0);
	}
	
	private void initializeBars(Bedürfnis[] bedürfnisse) {
		// BedürnisBars-Arrays initialisieren mit den Werten aus bedürfnisse
		bedürfnisBars = new JProgressBar[bedürfnisse.length];
		for(int i = 0; i < bedürfnisse.length; i++) {
			Point pos = getPositionForBars(i);
			bedürfnisBars[i] = new JProgressBar(bedürfnisse[i].getMin(), bedürfnisse[i].getMax());
			bedürfnisBars[i].setValue(bedürfnisse[i].getWert());
			bedürfnisBars[i].setSize(140, 20);
			bedürfnisBars[i].setLocation(pos);
			this.add(bedürfnisBars[i]);
			JLabel lbl = new JLabel(bedürfnisse[i].getName());
			lbl.setSize(100, 20);
			lbl.setLocation(pos.x, pos.y-20);
			this.add(lbl);
		}
	}
	
	private Point getPositionForBars(int i) {
		int xPos = 840;
		if(i == 0) {
			return new Point(xPos, 255);
		} else if(i == 1) {
			return new Point(xPos, 305);
		} else if(i == 2) {
			return new Point(xPos, 355);
		} else if(i == 3) {
			return new Point(xPos, 405);
		}
		
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == spielNeuStarten)
		{
			SpielAnwendung.zeigeCharakterauswahl();
		}
		else if (arg0.getSource() == rundeWeiter)
		{
			nächsteRunde();
		}
	}
	
	private void nächsteRunde() {
		spiel.naechsteRunde();
		aktualisiereDaten();
	}
	
	private void aktualisiereDaten() {
		Bedürfnis[] bedürfnisse = spiel.getBedürfnisse();
		for(int i = 0; i < bedürfnisse.length; i++) {
			bedürfnisBars[i].setValue(bedürfnisse[i].getWert());
		}
		lblGeld.setText(spiel.getKontostand() + "");
		lblGeldProMonat.setText(spiel.getGeldProMonat() + "");
		lblZeit.setText(spiel.getZeitProRunde() + "");
		lblZeitpunkt.setText(((spiel.getAktuelleRunde() % 4)+1) + ". Woche, " + (((int)spiel.getAktuelleRunde() / 4)+1) + ". Monat");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
