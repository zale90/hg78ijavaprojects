package Game.Minigames.Sudoku;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.text.*;
import Game.*;

public class Sudoku extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = -1628212039694469151L;
	private JLabel einleitung, linie, lab;
	private static JButton abschicken;
	private JButton beenden;
	private static JTextField[][] felder;
	private Sudokuspeicher speicher;
	private String einl;
	private Spiel spiel;

	public Sudoku(String grad, Spiel spiel) {
		super("Sudoku");
		this.setSize(355, 470);
		this.setLocation(200, 200);
//		this.setLocation(location);
		this.setLayout(null);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setUndecorated(true);

		ImageIcon icon = new ImageIcon("files/minigames/Sudoku/beispiel2.jpg");
		icon.setImage(icon.getImage().getScaledInstance(500, 700, Image.SCALE_DEFAULT));

		this.spiel = spiel;

		lab = new JLabel(icon);
		lab.setSize(400, 500);
		lab.setLocation(0, 0);
		this.add(lab);

		texten(grad);

		einleitung = new JLabel(einl);
		einleitung.setSize(255, 20);
		einleitung.setLocation(50, 20);
		einleitung.setForeground(Color.white);
		einleitung.setHorizontalAlignment(0);
		lab.add(einleitung);

		speicher = new Sudokuspeicher();

		felder = new JTextField[9][9];
		for (int zeile = 0; zeile < 9; zeile++) {
			for (int spalte = 0; spalte < 9; spalte++) {
				felder[zeile][spalte] = new JTextField("");
				felder[zeile][spalte].setBounds(50 + spalte * 28,
						80 + zeile * 28, 25, 25);
				felder[zeile][spalte].setHorizontalAlignment(0);
				felder[zeile][spalte].setCaretColor(new Color(16, 78, 139)); // ändert
																				// Farbe
																				// des
																				// Cursors
				felder[zeile][spalte].setBackground(Color.gray); // ändert
																	// Hintergrundfarbe
																	// des
																	// Textfeldes
				felder[zeile][spalte].setForeground(new Color(127, 255, 0)); // ändert
																				// die
																				// Schriftfarbe
				felder[zeile][spalte].setFont(new Font("Arial Black", 4, 20)); // ändert
																				// Schriftstil;
																				// Schriftgröße
				((AbstractDocument) felder[zeile][spalte].getDocument())
						.setDocumentFilter(new DocumentSizeFilter(1, "[1-9]"));

				felder[zeile][spalte].addMouseListener(this);
				lab.add(felder[zeile][spalte]);
			}
		}
		// felder[0][0].setOpaque(true);
		felder[0][0].setBackground(new Color(16, 78, 139));

		beginnen(grad);

		abschicken = new JButton("Abschicken");
		abschicken.setBounds(20, 365, 160, 30);
		abschicken.setBorderPainted(false);
		abschicken.setBackground(Color.black);
		abschicken.setForeground(Color.lightGray);
		abschicken.setOpaque(false);
		abschicken.setFont(new Font("Arial Black", 4, 20));
		abschicken.setContentAreaFilled(false);
		abschicken.setFocusPainted(false);
		abschicken.addActionListener(this);
		abschicken.addMouseListener(this);
		lab.add(abschicken);

		beenden = new JButton("Beenden");
		beenden.setBounds(200, 365, 140, 30);
		beenden.setBorderPainted(false);
		beenden.setBackground(Color.black);
		beenden.setForeground(Color.lightGray);
		beenden.setOpaque(false);
		beenden.setFont(new Font("Arial Black", 4, 20));
		beenden.setContentAreaFilled(false);
		beenden.setFocusPainted(false);
		beenden.addActionListener(this);
		beenden.addMouseListener(this);
		lab.add(beenden);

		linie = new JLabel();
		linie.setSize(251, 2);
		linie.setLocation(49, 161);
		linie.setOpaque(true);
		linie.setBackground(new Color(255, 193, 37));
		lab.add(linie);

		linie = new JLabel();
		linie.setSize(251, 2);
		linie.setLocation(49, 245);
		linie.setOpaque(true);
		linie.setBackground(new Color(255, 193, 37));
		lab.add(linie);

		linie = new JLabel();
		linie.setSize(251, 2);
		linie.setLocation(49, 329);
		linie.setOpaque(true);
		linie.setBackground(new Color(255, 193, 37));
		lab.add(linie);

		linie = new JLabel();
		linie.setSize(251, 2);
		linie.setLocation(49, 77);
		linie.setOpaque(true);
		linie.setBackground(new Color(255, 193, 37));
		lab.add(linie);

		linie = new JLabel();
		linie.setSize(2, 254);
		linie.setLocation(47, 77);
		linie.setOpaque(true);
		linie.setBackground(new Color(255, 193, 37));
		lab.add(linie);

		linie = new JLabel();
		linie.setSize(2, 254);
		linie.setLocation(300, 77);
		linie.setOpaque(true);
		linie.setBackground(new Color(255, 193, 37));
		lab.add(linie);

		linie = new JLabel();
		linie.setSize(2, 254);
		linie.setLocation(131, 77);
		linie.setOpaque(true);
		linie.setBackground(new Color(255, 193, 37));
		lab.add(linie);

		linie = new JLabel();
		linie.setSize(2, 254);
		linie.setLocation(215, 77);
		linie.setOpaque(true);
		linie.setBackground(new Color(255, 193, 37));
		lab.add(linie);

		this.setVisible(true);
	}

	// Anfang Methoden
	public void texten(String grad) {
		if (grad.equals("Leicht")) {
			einl = "Lösen Sie das Sudoku und gewinnen Sie 100 Euro!";
		} else if (grad.equals("Mittel")) {
			einl = "Lösen Sie das Sudoku und gewinnen Sie 200 Euro!";
		}
	}

	public void beginnen(String grad) {
		for (int zeile = 0; zeile < 9; zeile++) {
			for (int spalte = 0; spalte < 9; spalte++) {
				if (grad.equals("Leicht")) {
					speicher.startenLeicht();
					felder[zeile][spalte].setText(speicher.getStartLeicht(
							zeile, spalte));
				} else if (grad.equals("Mittel")) {
					speicher.startenMittel();
					felder[zeile][spalte].setText(speicher.getStartMittel(
							zeile, spalte));
				}
				if (!felder[zeile][spalte].getText().equals("")) {
					felder[zeile][spalte]
							.setForeground(new Color(255, 193, 37));
					felder[zeile][spalte].setEditable(false);
				}
				if (felder[zeile][spalte].getText().equals("")) {
					felder[zeile][spalte].setCursor(Cursor
							.getPredefinedCursor(Cursor.HAND_CURSOR)); // Maus
																		// wird
																		// zu
																		// Hand
				}

			}
		}
	}

	public static String getStringAusFeld(int z, int s) {
		return felder[z][s].getText();
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == beenden) {
			spiel.minispielEnde(null);
			this.dispose();
		}
		if (evt.getSource() == abschicken) {
			if (abschicken.isEnabled() == true) {
				abschicken.setEnabled(false);
			}
			Senden s = new Senden(null, this);
			s.setLocationRelativeTo(this);
		}
	}

	public void mousePressed(MouseEvent e) {
		for (int zeile = 0; zeile < 9; zeile++) {
			for (int spalte = 0; spalte < 9; spalte++) {
				if (e.getSource() == felder[zeile][spalte]) {
					felder[zeile][spalte].setBackground(new Color(16, 78, 139));
				}
				for (int z = 0; z < 9; z++) {
					for (int s = 0; s < 9; s++) {
						if (e.getSource() == felder[z][s]) {
							if (felder[zeile][spalte] != felder[z][s]) {
								felder[zeile][spalte].setBackground(Color.gray);
							}
						}
					}
				}
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == beenden) {
			beenden.setForeground(new Color(255, 193, 37));
		} else if (e.getSource() == abschicken) {
			abschicken.setForeground(new Color(255, 193, 37));
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == beenden) {
			beenden.setForeground(Color.lightGray);
		} else if (e.getSource() == abschicken) {
			abschicken.setForeground(Color.lightGray);
		}
	}

	public void setAbschickenEnabled(boolean b) {
		abschicken.setEnabled(b);
	}
}