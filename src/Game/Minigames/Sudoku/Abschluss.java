package Game.Minigames.Sudoku;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

import Game.Information;

public class Abschluss extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = -7668760602333069984L;

	private JLabel text, lab, text2;
	private JButton ok;
	private int r;
	private Random zufall;
	private Sudoku su;
	private Sudoku2 su2;
	private String w;

	public Abschluss(String wahl, Sudoku s, Sudoku2 s2) {
		super("Sudoku-Abschluss");
		this.setSize(270, 135);
		this.setLocation(250, 300);
		this.setLayout(null);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setUndecorated(true);
		if (s == null)
			this.setLocationRelativeTo(s2);
		else
			this.setLocationRelativeTo(s);

		su = s;
		su2 = s2;
		w = wahl;

		ImageIcon icon = new ImageIcon("files/minigames/Sudoku/beispiel2.jpg");
		icon.setImage(icon.getImage().getScaledInstance(290, 135,
				Image.SCALE_DEFAULT));

		lab = new JLabel(icon);
		lab.setSize(270, 135);
		lab.setLocation(0, 0);
		this.add(lab);

		text = new JLabel();
		text.setSize(250, 20);
		text.setLocation(10, 10);
		text.setForeground(Color.white);
		text.setHorizontalAlignment(0);
		lab.add(text);

		text2 = new JLabel();
		text2.setSize(270, 20);
		text2.setLocation(10, 30);
		text2.setForeground(Color.white);
		lab.add(text2);

		ok = new JButton("OK");
		ok.setSize(90, 30);
		ok.setLocation(80, 50);
		ok.setBorderPainted(false);
		ok.setBackground(Color.lightGray);
		ok.setForeground(Color.lightGray);
		ok.setOpaque(false);
		ok.setFont(new Font("Arial Black", 4, 20));
		ok.setContentAreaFilled(false);
		ok.setFocusPainted(false);
		ok.addActionListener(this);
		ok.addMouseListener(this);
		lab.add(ok);

		textWahl(wahl);

		this.setVisible(true);
	}

	public void textWahl(String w) {
		if (w.equals("Richtig")) {
			text.setText("Sie haben das Sudoku richtig gelöst!");
			if (r == 0) {
				text2.setText(getGlück());
			} else {
				text2.setText(getGlück());
			}
		} else if (w.equals("Falsch")) {
			text.setText("Sie haben das Sudoku nicht richtig gelöst!");
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			Information[] infos = new Information[1];
			if (w.equals("Richtig")) {
//				if (SudokuFenster1.getPreis().equals("20")) {
					infos[0] = new Information(Information.AENDERN_GELD,
							Information.ART_UM_WERT, Integer.parseInt(SudokuFenster1.getPreis()));
					SudokuFenster1.getSpiel().minispielEnde(infos);
//				} else if (SudokuFenster1.getPreis().equals("50")) {
//					infos[0] = new Information(Information.AENDERN_GELD,
//							Information.ART_UM_WERT, 50);
//					SudokuFenster1.getSpiel().minispielEnde(infos);
//				} else {
//					infos[0] = new Information(Information.AENDERN_GELD,
//							Information.ART_UM_WERT, 100);
//					SudokuFenster1.getSpiel().minispielEnde(infos);
//				}
			}
			else
			{
				infos[0] = new Information(Information.AENDERN_GELD,
						Information.ART_UM_WERT, -5);
				SudokuFenster1.getSpiel().minispielEnde(infos);
			}
			this.setVisible(false);
			if (su2 != null)
				su2.setVisible(false);
			if (su != null)
				su.setVisible(false);
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == ok) {
			ok.setForeground(new Color(255, 193, 37));
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == ok) {
			ok.setForeground(Color.lightGray);
		}
	}

	public String getGlück() {
		if (SudokuFenster1.getPreis().equals("20")) {
			zufall = new Random();
			r = zufall.nextInt(6);

			if (r == 0) {
				return "Sie haben bei der Verlosung 20 € gewonnen!";
			} else {
				return "Sie haben bei der Verlosung leider verloren!";
			}
		} else if (SudokuFenster1.getPreis().equals("50")) {
			zufall = new Random();
			r = zufall.nextInt(5);

			if (r == 0) {
				return "Sie haben bei der Verlosung 50 € gewonnen!";
			} else {
				return "Sie haben bei der Verlosung leider verloren!";
			}
		} else if (SudokuFenster1.getPreis().equals("100")) {
			zufall = new Random();
			r = zufall.nextInt(4);

			if (r == 0) {
				return "Sie haben bei der Verlosung 100 € gewonnen!";
			} else {
				return "Sie haben bei der Verlosung leider verloren!";
			}
		}
		return "Error";
	}
}