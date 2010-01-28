package Game.Minigames.Sudoku;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import Game.*;
import Game.Minigames.*;

public class SudokuFenster1 extends JFrame implements ActionListener,
		MouseListener, Minispiel {

	private static final long serialVersionUID = -3632862514735137247L;

	private JLabel lblBeschreibung, label3, label4;
	private JButton btnStart, btnBeenden;
	private JRadioButton rbn, rbn2, rbn3;
	private static String preis;
	private static Spiel spiel;

	public SudokuFenster1() {
		super("Sudoku - Wählen Sie eine Schwierigkeit");
		this.setSize(360, 298);
		this.setLocation(300, 160);
		this.setLayout(null);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setUndecorated(true);

		ButtonGroup btng = new ButtonGroup();
		ImageIcon icon = new ImageIcon("files/minigames/Sudoku/beispiel2.jpg");
		ImageIcon icon2 = new ImageIcon("files/minigames/Sudoku/sudoku3.jpg");

		label3 = new JLabel(icon);
		label3.setSize(360, 300);
		label3.setLocation(0, 0);
		this.add(label3);

		label4 = new JLabel(icon2);
		label4.setSize(100, 100);
		label4.setLocation(210, 70);
		label3.add(label4);

		lblBeschreibung = new JLabel("Schwierigkeitsstufe:");
		lblBeschreibung.setSize(200, 30);
		lblBeschreibung.setLocation(10, 30);
		lblBeschreibung.setFont(new Font("Arial", Font.PLAIN, 16));
		lblBeschreibung.setForeground(Color.white);
		label3.add(lblBeschreibung);

		btnStart = new JButton("Start");
		btnStart.setSize(100, 30);
		btnStart.setLocation(30, 200);
		btnStart.setBorderPainted(false);
		btnStart.setBackground(Color.black);
		btnStart.setFont(new Font("Arial Black", 4, 20));
		btnStart.setForeground(Color.lightGray);
		btnStart.setOpaque(false);
		btnStart.setContentAreaFilled(false);
		btnStart.setFocusPainted(false);
		btnStart.addMouseListener(this);
		btnStart.addActionListener(this);
		label3.add(btnStart);

		btnBeenden = new JButton("Beenden");
		btnBeenden.setSize(150, 30);
		btnBeenden.setLocation(180, 200);
		btnBeenden.setBorderPainted(false);
		btnBeenden.setBackground(Color.black);
		btnBeenden.setFont(new Font("Arial Black", 4, 20));
		btnBeenden.setForeground(Color.lightGray);
		btnBeenden.setOpaque(false);
		btnBeenden.setContentAreaFilled(false);
		btnBeenden.setFocusPainted(false);
		btnBeenden.addMouseListener(this);
		btnBeenden.addActionListener(this);
		label3.add(btnBeenden);

		rbn = new JRadioButton("Leicht", true);
		rbn.setSize(60, 20);
		rbn.setLocation(30, 80);
		rbn.setOpaque(false);
		rbn.setForeground(Color.white);
		rbn.setFocusPainted(false);
		label3.add(rbn);
		btng.add(rbn);

		rbn2 = new JRadioButton("Mittel", true);
		rbn2.setSize(60, 20);
		rbn2.setLocation(30, 110);
		rbn2.setOpaque(false);
		rbn2.setForeground(Color.white);
		rbn2.setFocusPainted(false);
		label3.add(rbn2);
		btng.add(rbn2);

		rbn3 = new JRadioButton("Schwer", true);
		rbn3.setSize(80, 20);
		rbn3.setLocation(30, 140);
		rbn3.setOpaque(false);
		rbn3.setForeground(Color.white);
		rbn3.setFocusPainted(false);
		label3.add(rbn3);
		btng.add(rbn3);
	}

	public void actionPerformed(ActionEvent evt) {

		if (evt.getSource() == btnBeenden) {
			this.dispose();
		} else if (evt.getSource() == btnStart) {
			if (rbn.isSelected() == true) {
				preis = "20";
				Sudoku s = new Sudoku("Leicht", spiel);
				s.setLocationRelativeTo(this);
			} else if (rbn2.isSelected() == true) {
				preis = "50";
				Sudoku s = new Sudoku("Mittel", spiel);
				s.setLocationRelativeTo(this);
			} else if (rbn3.isSelected() == true) {
				preis = "100";
				Sudoku2 s = new Sudoku2(spiel);
				s.setLocationRelativeTo(this);
			}
			this.setVisible(false);
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == btnStart) {
			btnStart.setForeground(new Color(255, 193, 37));
		} else if (e.getSource() == btnBeenden) {
			btnBeenden.setForeground(new Color(255, 193, 37));
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == btnStart) {
			btnStart.setForeground(Color.lightGray);
		} else if (e.getSource() == btnBeenden) {
			btnBeenden.setForeground(Color.lightGray);
		}
	}

	public static String getPreis() {
		return preis;
	}

	public static Spiel getSpiel() {
		return spiel;
	}

	public void start(Spiel spiel) {
		SudokuFenster1.spiel = spiel;
		this.setVisible(true);
	}
}