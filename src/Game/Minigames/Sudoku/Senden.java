package Game.Minigames.Sudoku;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Write a description of class FensterAbschicken here.
 * 
 * @version 22.01.2010
 */
public class Senden extends JFrame implements ActionListener, MouseListener {
	// Anfang Attribute
	private JButton best�tigen, abbrechen;
	private JTextArea text;
	private JLabel lab;
	private Sudoku2 su2;
	private Sudoku su;

	// Ende Attribute

	public Senden(Sudoku2 s2, Sudoku s) {
		super("Sudoku");
		this.setSize(300, 280);
		this.setLocation(220, 240);
		this.getContentPane().setBackground(Color.lightGray);
		this.setLayout(null);
		this.setAlwaysOnTop(true);

		su2 = s2;
		su = s;

		ImageIcon icon = new ImageIcon("files/minigames/Sudoku/beispiel3.jpg");
		icon.setImage(icon.getImage().getScaledInstance(300, 280,
				Image.SCALE_DEFAULT));

		lab = new JLabel(icon);
		lab.setSize(300, 280);
		lab.setLocation(0, 0);
		this.add(lab);

		text = new JTextArea("Wenn Sie Ihre L�sung dieses Sudokus \n"
				+ "abschicken m�chten, um " + SudokuFenster1.getPreis()
				+ " Euro zu \n" + "gewinnen m�ssen Sie einen frankierten \n"
				+ "Brief im Wert von 0,55 Euro bis zum \n"
				+ "31.03.2010 an folgende Adresse schicken: \n\n"
				+ "         D�lmener Zeitung \n" + "         Marktstra�e 25 \n"
				+ "         48249 D�lmen");
		text.setSize(250, 145);
		text.setLocation(20, 10);
		text.setEditable(false);
		text.setBackground(Color.black);
		text.setOpaque(false);
		text.setForeground(Color.white);
		lab.add(text);

		best�tigen = new JButton("Best�tigen");
		best�tigen.setSize(152, 30);
		best�tigen.setLocation(5, 180);
		best�tigen.setBorderPainted(false);
		best�tigen.setBackground(Color.lightGray);
		best�tigen.setForeground(Color.lightGray);
		best�tigen.setFont(new Font("Arial Black", 4, 20));
		best�tigen.setOpaque(false);
		best�tigen.setContentAreaFilled(false);
		best�tigen.setFocusPainted(false);
		best�tigen.addActionListener(this);
		best�tigen.addMouseListener(this);
		lab.add(best�tigen);

		abbrechen = new JButton("Abbrechen");
		abbrechen.setSize(150, 30);
		abbrechen.setLocation(140, 180);
		abbrechen.setBorderPainted(false);
		abbrechen.setBackground(Color.lightGray);
		abbrechen.setForeground(Color.lightGray);
		abbrechen.setFont(new Font("Arial Black", 4, 20));
		abbrechen.setOpaque(false);
		abbrechen.setContentAreaFilled(false);
		abbrechen.setFocusPainted(false);
		abbrechen.addActionListener(this);
		abbrechen.addMouseListener(this);
		lab.add(abbrechen);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == abbrechen) {
			this.setVisible(false);
			if (su != null)
				su.setAbschickenEnabled(true);
			if (su2 != null)
				su2.setAbschickenEnabled(true);
		} else if (e.getSource() == best�tigen) {
			this.setVisible(false);
			if (su != null) {
				int i = 0;
				for (int zeile = 0; zeile < 9; zeile++) {
					for (int spalte = 0; spalte < 9; spalte++) {
						if (Sudoku.getStringAusFeld(zeile, spalte).equals(
								Sudokuspeicher.getReihe().substring(
										(zeile * 9 + spalte),
										(zeile * 9 + spalte) + 1))) {
							i++; // richtig gel�st
						} else {
							i--; // falsch gel�st
						}
					}
				}
				if (i == 81) {
					new Abschluss("Richtig", su, su2);
				} else {
					new Abschluss("Falsch", su, su2);
				}
			} else if (su2 != null) {
				int i = 0;
				while (i <= 80) {
					if (su2.getTextfeld().get(i).getText().equals(
							su2.getMuster().sudokuL�sungPr�fen(i, su2.getR()))) {
						if (i == 80) {
							new Abschluss("Richtig", su, su2);
						}
					} else {
						new Abschluss("Falsch", su, su2);
						i = 80;
					}
					i++;
				}
			}
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == best�tigen) {
			best�tigen.setForeground(new Color(255, 193, 37));
		} else if (e.getSource() == abbrechen) {
			abbrechen.setForeground(new Color(255, 193, 37));
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == best�tigen) {
			best�tigen.setForeground(Color.lightGray);
		} else if (e.getSource() == abbrechen) {
			abbrechen.setForeground(Color.lightGray);
		}
	}
	// Ende Methoden
}