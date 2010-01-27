package Game.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import Game.*;

public class Spieloberfläche extends JPanel implements MouseListener {

	private static final long serialVersionUID = 846296549789605647L;

	private JTextPane console;
	private JLabel lblHeading, lblZeit, lblGeld, lblGeldProMonat, lblZeitpunkt;
	private JProgressBar[] bedürfnisBars;
	private Spiel spiel;
	private PunkteGUI punkteGUI;
	private Ereignisfenster ereignisfenster;
	private Spielbereich spielbereich;
	private Thread threadlblMaus;
	private MausLabel lblMaus;
	private JLabel rundeWeiter, spielNeuStarten;

	public Spieloberfläche(Spiel spiel, String charName, Finanzen finanzen) {
		this.setSize(995, 672);
		this.setLocation(0, 0);
		this.setLayout(null);
		this.setBackground(null);

		this.spiel = spiel;

		punkteGUI = new PunkteGUI(spiel);
		this.add(punkteGUI);
		this.add(finanzen);
		ereignisfenster = new Ereignisfenster(spiel, this);
		this.add(ereignisfenster);

		// Maus-Label
		lblMaus = new MausLabel(this);
		this.add(lblMaus);
		threadlblMaus = new Thread(lblMaus);

		// GUI-Elemente erzeugen
		lblHeading = new JLabel(Optionen.NAME);
		lblHeading.setFont(Optionen.FONT_TITLE);
		lblHeading.setSize(1000, 40);
		lblHeading.setLocation(20, 10);
		this.add(lblHeading);

		// Konsole hinzufügen
		console = new JTextPane();
		console.setEditable(false);
		console.setSize(800, 90);
		console.setLocation(20, 568);
		console.setText("Willkommen im Spiel " + Optionen.NAME
				+ "!\nDu hast ein neues Spiel gestartet. Viel Spaß!");
		this.add(console);

		JScrollPane spConsole = new JScrollPane(console);
		spConsole.setSize(800, 90);
		spConsole.setLocation(20, 568);
		spConsole
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(spConsole);

		// Nur ein Testpanel, um zu schauen welche Größe wir für das Spielfeld
		// einplanen müssen - ich finds so gut
		spielbereich = new Spielbereich(0, 0, this);
		spielbereich.setSize(800, 500);
		spielbereich.setLocation(20, 60);
		this.add(spielbereich);

		// Avatar
		JLabel name = new JLabel(charName);
		name.setSize(140, 20);
		name.setLocation(840, 60);
		name.setHorizontalAlignment(0);
		name.setFont(Optionen.FONT_BIGGER);
		this.add(name);

		JLabel avatarbild = new JLabel(new ImageIcon(Optionen.ICON_PATH_AVATAR
				+ charName + "Klein.jpg"));
		avatarbild.setSize(140, 140);
		avatarbild.setLocation(840, 90);
		avatarbild.setBorder(new LineBorder(Color.BLACK, 1));
		this.add(avatarbild);

		initializeBars(spiel.getBedürfnisse());

		// Verbleibende Zeit anzeigen
		JLabel lblVerbleibendeZeit = new JLabel("Verbleibende Zeit:");
		lblVerbleibendeZeit.setSize(100, 20);
		lblVerbleibendeZeit.setLocation(840, 445);
		this.add(lblVerbleibendeZeit);

		lblZeit = new JLabel("" + spiel.getZeit());
		lblZeit.setSize(100, 20);
		lblZeit.setLocation(935, 445);
		this.add(lblZeit);

		// Kontostand anzeigen
		JLabel lblKontostand = new JLabel("Kontostand:");
		lblKontostand.setSize(100, 20);
		lblKontostand.setLocation(840, 465);
		this.add(lblKontostand);

		lblGeld = new JLabel("" + spiel.getKontostand());
		lblGeld.setSize(100, 20);
		lblGeld.setLocation(935, 465);
		this.add(lblGeld);

		JLabel lblEinkommen = new JLabel("Einkommen:");
		lblEinkommen.setSize(100, 20);
		lblEinkommen.setLocation(840, 485);
		this.add(lblEinkommen);

		lblGeldProMonat = new JLabel(spiel.getGeldProMonat() + "");
		lblGeldProMonat.setSize(100, 20);
		lblGeldProMonat.setLocation(935, 485);
		this.add(lblGeldProMonat);

		lblZeitpunkt = new JLabel("1. Woche, 1. Monat");
		lblZeitpunkt.setSize(140, 20);
		lblZeitpunkt.setLocation(840, 520);
		lblZeitpunkt.setHorizontalAlignment(0);
		this.add(lblZeitpunkt);

		rundeWeiter = new JLabel("Runde beenden");
		rundeWeiter.setSize(140, 40);
		rundeWeiter.setLocation(840, 568);
		rundeWeiter.setOpaque(true);
		rundeWeiter.setBackground(Color.GREEN);
		rundeWeiter.setHorizontalAlignment(0);
		rundeWeiter.addMouseListener(this);
		this.add(rundeWeiter);

		spielNeuStarten = new JLabel("Neues Spiel starten");
		spielNeuStarten.setSize(140, 40);
		spielNeuStarten.setLocation(840, 618);
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
		for (int i = 0; i < bedürfnisse.length; i++) {
			Point pos = getPositionForBars(i);
			bedürfnisBars[i] = new JProgressBar(bedürfnisse[i].getMin(),
					bedürfnisse[i].getMax());
			bedürfnisBars[i].setValue(bedürfnisse[i].getWert());
			bedürfnisBars[i].setSize(140, 20);
			bedürfnisBars[i].setLocation(pos);
			bedürfnisBars[i].addMouseListener(this);
			this.add(bedürfnisBars[i]);
			JLabel lbl = new JLabel(bedürfnisse[i].getName());
			lbl.setSize(100, 20);
			lbl.setLocation(pos.x, pos.y - 20);
			this.add(lbl);
		}
	}

	private Point getPositionForBars(int i) {
		int xPos = 840;
		if (i == 0) {
			return new Point(xPos, 255);
		} else if (i == 1) {
			return new Point(xPos, 295);
		} else if (i == 2) {
			return new Point(xPos, 335);
		} else if (i == 3) {
			return new Point(xPos, 375);
		} else if (i == 4) {
			return new Point(xPos, 415);
		}

		return null;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == spielNeuStarten) {
			Object[] options = { "Ja", "Nein" };
			if (JOptionPane.showOptionDialog(SpielAnwendung.mainGUI,
					"Willst du wirklich ein neues Spiel starten?",
					"Bestätigung", JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, options, options[1]) == JOptionPane.YES_OPTION)
				SpielAnwendung.zeigeSplashscreen();
		} else if (arg0.getSource() == rundeWeiter) {
			nächsteRunde();
		}
	}

	private void nächsteRunde() {
		setzeAktiviert(false);
		spiel.punkteBerechnen(punkteGUI);
		aktualisiereDaten();
	}

	public void setzeAktiviert(boolean akt) {
		if (akt) {
			rundeWeiter.addMouseListener(this);
			spielNeuStarten.addMouseListener(this);
		} else {
			rundeWeiter.removeMouseListener(this);
			spielNeuStarten.removeMouseListener(this);
		}
		spielbereich.setzeAktiviert(akt);
	}

	public void aktualisiereDaten() {
		Bedürfnis[] bedürfnisse = spiel.getBedürfnisse();
		for (int i = 0; i < bedürfnisse.length; i++) {
			bedürfnisBars[i].setValue(bedürfnisse[i].getWert());
		}
		lblGeld.setText(spiel.getKontostand() + "");
		lblGeldProMonat.setText(spiel.getGeldProMonat() + "");
		lblZeit.setText(spiel.getZeit() + "");
		lblZeitpunkt.setText(((spiel.getAktuelleRunde() % 4) + 1) + ". Woche, "
				+ (((int) spiel.getAktuelleRunde() / 4) + 1) + ". Monat");
	}

	@Override
	public void mouseEntered(MouseEvent evt) {
		// BedürfnisInfos anzeigen
		for (int i = 0; i < bedürfnisBars.length; i++) {
			JProgressBar bar = bedürfnisBars[i];

			if (evt.getSource() == bar) {
				zeigeMausLabel(Bedürfnis.getName(i) + ": " + bar.getValue()
						+ "%");
			}
		}
	}

	/**
	 * Zeit an der aktuellen Mausposition das Mauslabel an, bis die Maus die
	 * aktuelle Komponente verlässt.
	 * 
	 * @param text
	 *            Text, der im Label angezeigt werden soll.
	 */
	private void zeigeMausLabel(String text) {
		lblMaus.setText(text);
		threadlblMaus = new Thread(lblMaus);
		threadlblMaus.start();
	}

	@Override
	public void mouseExited(MouseEvent evt) {
		lblMaus.setVisible(false);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	public void aktion(Aktion aktion) {
		spiel.aktionAusfuehren(aktion);
	}

	public Ereignisfenster getEreignisfenster() {
		return ereignisfenster;
	}

	public Spiel getSpiel() {
		return spiel;
	}
}
