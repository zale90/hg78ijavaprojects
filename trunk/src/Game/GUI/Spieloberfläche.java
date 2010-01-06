package Game.GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;
import Game.*;

public class Spieloberfläche extends JPanel implements MouseListener {

	private static final long serialVersionUID = 846296549789605647L;
	
	private JTextPane console;
	private JLabel lblHeading, lblZeit, lblGeld, lblEinkommen;
	private JProgressBar[] bedürfnisBars;
	private Spiel spiel;
	/*
	 * interaktive Schaltflächen; erstmal mit JLabels, damit wir flexibler sind
	 */
	private JLabel rundeWeiter, spielNeuStarten;
	
	public Spieloberfläche(Spiel spiel, String bild) {
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
		console.setSize(960, 90);
		console.setLocation(20, 568);
		console.setText("Willkommen im Spiel " + Optionen.NAME + "!\nSie haben ein neues Spiel gestartet. Viel Spaß!");
		this.add(console);
		
		JScrollPane spConsole = new JScrollPane(console);
		spConsole.setSize(960, 90);
        spConsole.setLocation(20, 568);
        spConsole.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(spConsole);
		
		/*
		 * Nur ein Testpanel, um zu schauen welche Größe wir für das Spielfeld einplanen müssen - ich finds so gut
		 */
		JPanel spielfeldTest = new JPanel();
		spielfeldTest.setSize(800, 500);
		spielfeldTest.setLocation(20, 60);
		spielfeldTest.setBackground(Color.BLACK);
		this.add(spielfeldTest);
		
		JLabel avatarbild = new JLabel(new ImageIcon("files/avatarImages/" + bild + "Klein.jpg"));
		avatarbild.setSize(140,140);
		avatarbild.setLocation(840, 60);
		this.add(avatarbild);
		
		initializeBars(spiel.getBedürfnisse());
		
		// Verbleibende Zeit anzeigen
		JLabel lblVerbleibendeZeit = new JLabel("Verbleibende Zeit:");
		lblVerbleibendeZeit.setSize(100, 20);
		lblVerbleibendeZeit.setLocation(840, 405);
		this.add(lblVerbleibendeZeit);
		
		lblZeit = new JLabel("" + spiel.getZeit());
		lblZeit.setSize(100, 20);
		lblZeit.setLocation(935, 405);
		this.add(lblZeit);
		
		
		// Kontostand anzeigen
		JLabel lblKontostand = new JLabel("Kontostand:");
		lblKontostand.setSize(100, 20);
		lblKontostand.setLocation(840, 425);
		this.add(lblKontostand);
		
		lblGeld = new JLabel("" + spiel.getKontostand());
		lblGeld.setSize(100, 20);
		lblGeld.setLocation(935, 425);
		this.add(lblGeld);
		
		JLabel lblEinkommen = new JLabel("Einkommen:");
		lblEinkommen.setSize(100, 20);
		lblEinkommen.setLocation(840, 445);
		this.add(lblEinkommen);
		
		lblEinkommen = new JLabel("?");
		lblEinkommen.setSize(100, 20);
		lblEinkommen.setLocation(935, 445);
		this.add(lblEinkommen);
		
		rundeWeiter = new JLabel("Runde beenden");
		rundeWeiter.setSize(140, 40);
		rundeWeiter.setLocation(840 ,470);
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
		spielNeuStarten.setLocation(840 ,520);
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
		// BedürnisBars-Arrays initialisieren mit den Werden aus bedürfnisse
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
			return new Point(xPos, 230);
		} else if(i == 1) {
			return new Point(xPos, 280);
		} else if(i == 2) {
			return new Point(xPos, 330);
		} else if(i == 3) {
			return new Point(xPos, 380);
		}
		
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == spielNeuStarten)
		{
			SpielAnwendung.zeigeCharakterauswahl();
		}
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
