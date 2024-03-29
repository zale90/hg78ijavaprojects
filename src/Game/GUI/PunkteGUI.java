package Game.GUI;

import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Game.*;

public class PunkteGUI extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1626731797661003093L;
	
	private JButton ok;
	private Spiel spiel;
	private JLabel[] bedürfnisse, bPunkte;
	private JLabel ueberschrift, bedürfnis, bSumme, lGeld, gPunkt, lGesamt,
			gesPunkt, lVorher, pVorher, lPunktestand, pPunktestand;
	private JTextPane einleitung;

	public PunkteGUI(Spiel spiel) {
		this.spiel = spiel;
		int x = 0;
		if (spiel.getAvatarNr() != 1)
			x = 15;

		this.setSize(200, 350);
		this.setLocation(320, 135);
		// this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		ueberschrift = new JLabel("Punkteabrechnung");
		ueberschrift.setSize(195, 30);
		ueberschrift.setLocation(5, 0);
		ueberschrift.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(ueberschrift);

		einleitung = new JTextPane();
		einleitung
				.setText("In der letzten Runde hast du folgende Punkte erspielt:");
		einleitung.setSize(195, 50);
		einleitung.setBackground(null);
		einleitung.setEditable(false);
		einleitung.setLocation(3, 30);
		this.add(einleitung);

		bedürfnis = new JLabel("Bedürfnisse");
		bedürfnis.setSize(195, 20);
		bedürfnis.setLocation(5, 145 + x);
		bedürfnis.setFont(new Font("Arial", Font.BOLD, 14));
		this.add(bedürfnis);

		bedürfnisse = new JLabel[5];
		int ort = 85;
		for (int i = 0; i < 5; i++) {
			bedürfnisse[i] = new JLabel();
			bedürfnisse[i].setSize(95, 15);
			bedürfnisse[i].setLocation(5, ort);
			this.add(bedürfnisse[i]);
			ort = ort + 15;
		}
		bedürfnisse[0].setText("Nahrung");
		bedürfnisse[1].setText("Gesundheit");
		bedürfnisse[2].setText("Soziales");
		bedürfnisse[3].setText("Luxus");
		bedürfnisse[4].setText("Kinder");

		if (spiel.getAvatarNr() == 1)
			bedürfnisse[4].setVisible(false);

		bPunkte = new JLabel[5];
		ort = 85;
		for (int i = 0; i < 5; i++) {
			bPunkte[i] = new JLabel();
			bPunkte[i].setSize(95, 15);
			bPunkte[i].setLocation(100, ort);
			bPunkte[i].setHorizontalAlignment(JLabel.RIGHT);
			this.add(bPunkte[i]);
			ort = ort + 15;
		}

		if (spiel.getAvatarNr() == 1)
			bPunkte[4].setVisible(false);

		bSumme = new JLabel();
		bSumme.setSize(95, 20);
		bSumme.setLocation(100, 145 + x);
		bSumme.setHorizontalAlignment(JLabel.RIGHT);
		bSumme.setFont(new Font("Arial", Font.BOLD, 14));
		this.add(bSumme);

		lGeld = new JLabel("Geld");
		lGeld.setSize(95, 20);
		lGeld.setLocation(5, 170 + x);
		lGeld.setFont(new Font("Arial", Font.BOLD, 14));
		this.add(lGeld);

		gPunkt = new JLabel();
		gPunkt.setSize(95, 20);
		gPunkt.setLocation(100, 170 + x);
		gPunkt.setFont(new Font("Arial", Font.BOLD, 14));
		gPunkt.setHorizontalAlignment(JLabel.RIGHT);
		this.add(gPunkt);

		lGesamt = new JLabel("Gesamt");
		lGesamt.setSize(95, 20);
		lGesamt.setLocation(5, 200 + x);
		lGesamt.setFont(new Font("Arial", Font.BOLD, 16));
		this.add(lGesamt);

		gesPunkt = new JLabel();
		gesPunkt.setSize(95, 20);
		gesPunkt.setLocation(100, 200 + x);
		gesPunkt.setFont(new Font("Arial", Font.BOLD, 16));
		gesPunkt.setHorizontalAlignment(JLabel.RIGHT);
		this.add(gesPunkt);

		lVorher = new JLabel("Punkte vorher");
		lVorher.setSize(95, 20);
		lVorher.setLocation(5, 220 + x);
		lVorher.setFont(new Font("Arial", Font.PLAIN, 14));
		this.add(lVorher);

		pVorher = new JLabel();
		pVorher.setSize(95, 20);
		pVorher.setLocation(100, 220 + x);
		pVorher.setFont(new Font("Arial", Font.PLAIN, 14));
		pVorher.setHorizontalAlignment(JLabel.RIGHT);
		this.add(pVorher);

		lPunktestand = new JLabel("Punktestand");
		lPunktestand.setSize(190, 30);
		lPunktestand.setLocation(5, 260);
		lPunktestand.setFont(new Font("Arial", Font.BOLD, 16));
		lPunktestand.setHorizontalAlignment(JLabel.CENTER);
		this.add(lPunktestand);

		pPunktestand = new JLabel();
		pPunktestand.setSize(190, 30);
		pPunktestand.setLocation(5, 280);
		pPunktestand.setFont(new Font("Arial", Font.BOLD, 20));
		pPunktestand.setHorizontalAlignment(JLabel.CENTER);
		this.add(pPunktestand);

		ok = new JButton("Weiter");
		ok.setSize(90, 30);
		ok.setLocation(55, 315);
		ok.setFont(new Font("Arial", Font.BOLD, 16));
		ok.setForeground(new Color(0, 0, 0));
		ok.setHorizontalAlignment(JLabel.CENTER);
		ok.addActionListener(this);
		this.add(ok);

		this.setVisible(false);
	}

	public void setzeWerte(int hunger, int gesundheit, int soziales, int luxus,
			int kinder, int geld, int alt) {
		this.setVisible(true);
		// OK-Button Fokussieren
		ok.requestFocusInWindow();
		bPunkte[0].setText(Integer.toString(hunger));
		bPunkte[1].setText(Integer.toString(gesundheit));
		bPunkte[2].setText(Integer.toString(soziales));
		bPunkte[3].setText(Integer.toString(luxus));
		bPunkte[4].setText(Integer.toString(kinder));
		int bSummeInt = hunger + gesundheit + soziales + luxus;
		if (spiel.getAvatarNr() != 1)
			bSummeInt = bSummeInt + kinder;
		bSumme.setText(Integer.toString(bSummeInt));
		gPunkt.setText(Integer.toString(geld));
		gesPunkt.setText(Integer.toString(bSummeInt + geld));
		pVorher.setText(Integer.toString(alt));
		pPunktestand.setText(Integer.toString(alt + bSummeInt + geld));
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == ok) {
			this.setVisible(false);
			spiel.zeigeFinanzen();
		}
	}
}
