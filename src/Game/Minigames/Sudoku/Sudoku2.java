package Game.Minigames.Sudoku;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.text.*;

import Game.Spiel;

import java.util.Random;

public class Sudoku2 extends JFrame implements ActionListener, MouseListener {
	
	private static final long serialVersionUID = 1899933329606022846L;
	
	private JButton btn1;
	private JButton btn2;
	private JLabel lbl1;
	private JLabel lbl2;
	private JTextField txtfeld;
	private int x;
	private int y;
	private int r;
	private SudokuMuster muster;
	private ArrayList<JTextField> textfeld;
	private JLabel linie;
	private Spiel spiel;

	public Sudoku2(Spiel spiel) {
		super("Schwer");
		this.setSize(400, 500);
		this.setLocation(300, 100);
		this.setLayout(null);
		this.setAlwaysOnTop(true);

		Icon bild = new ImageIcon(
				"files/minigames/Sudoku/sudokuBildFenster.jpg");
		lbl1 = new JLabel(bild);
		lbl1.setSize(400, 500);
		this.add(lbl1);

		this.spiel = spiel;

		lbl2 = new JLabel("Sudoku");
		lbl2.setSize(200, 75);
		lbl2.setLocation(120, 14);
		lbl2.setFont(new Font("Vladimir Script", Font.BOLD, 60));// "Bickley Script"
		lbl2.setForeground(new Color(50, 200, 225));
		lbl1.add(lbl2);

		btn1 = new JButton("Abschicken");
		btn1.setSize(160, 30);
		btn1.setLocation(30, 400);
		lbl1.add(btn1);
		btn1.addActionListener(this);
		btn1.setFont(new Font("Comic Sans", Font.ITALIC, 20));
		btn1.setBorderPainted(false);
		btn1.setBackground(Color.lightGray);
		btn1.setContentAreaFilled(false);
		btn1.setFocusPainted(false);
		btn1.setOpaque(false);
		btn1.setForeground(new Color(140, 220, 225));
		btn1.addMouseListener(this);

		btn2 = new JButton("Beenden");
		btn2.setSize(120, 30);
		btn2.setLocation(230, 400);
		btn2.setFont(new Font("Comic Sans", Font.ITALIC, 20));
		btn2.setBorderPainted(false);
		btn2.setBackground(Color.lightGray);
		btn2.setContentAreaFilled(false);
		btn2.setFocusPainted(false);
		btn2.setOpaque(false);
		btn2.setForeground(new Color(140, 220, 225));
		btn2.addActionListener(this);
		btn2.addMouseListener(this);
		lbl1.add(btn2);

		zufallsZahlenAusgeben();

		int g = 60;
		int h = 99;
		int z = 0;
		int j = 0;
		int k = 271;
		int l = 4;

		for (int i = 0; i <= 7; i++) {
			linie = new JLabel();
			linie.setSize(k, l);
			linie.setLocation(g + j, h - z);
			linie.setOpaque(true);
			linie.setBackground(Color.black);
			lbl1.add(linie);

			h = h + 90;
			if (i == 3) {
				k = 4;
				l = 271;
				g = 59;
				h = 100;
			}
			if (i > 3) {
				j = j + 90;
				z = z + 90;
			}
		}
		int a = 60;
		int b = 99;
		int e = 0;
		int f = 0;
		int c = 271;
		int d = 3;

		for (int i = 0; i <= 19; i++) {
			if (i == 0 || i == 3 || i == 6 || i == 9 || i == 10 || i == 13
					|| i == 16 || i == 19) {
			} else {
				linie = new JLabel();
				linie.setSize(c, d);
				linie.setLocation(a + f, b - e);
				linie.setOpaque(true);
				linie.setBackground(new Color(140, 220, 225));
				lbl1.add(linie);
			}
			b = b + 30;
			if (i == 9) {
				c = 3;
				d = 271;
				a = 59;
				b = 100;
			}
			if (i > 9) {
				f = f + 30;
				e = e + 30;
			}
		}
		x = 0;
		y = 0;
		textfeld = new ArrayList<JTextField>();
		muster = new SudokuMuster();
		muster.sudokuMusterFüllen(r);

		for (int i = 0; i <= 80; i++) {
			txtfeld = new JTextField(muster.listeAusgeben(i));
			txtfeld.setSize(30, 30);
			txtfeld.setLocation(60 + x, 100 + y);
			lbl1.add(txtfeld);
			txtfeld.setHorizontalAlignment(0);
			txtfeld.setFont(new Font("Arial", Font.BOLD, 20));
			textfeld.add(txtfeld);
			textfeld.get(i).setOpaque(false);
			textfeld.get(i).setCaretColor(Color.green);
			textfeld.get(i).setForeground(Color.green); // 20,180,225 50,200,225

			x = x + 30;

			if (i == 8 || i == 17 || i == 26 || i == 35 || i == 44 || i == 53
					|| i == 62 || i == 71) {
				x = 0;
				y = y + 30;
			}
		}
		festeZahlen();
		this.setVisible(true);
	}

	public ArrayList<JTextField> getTextfeld() {
		return textfeld;
	}

	public SudokuMuster getMuster() {
		return muster;
	}

	public int getR() {
		return r;
	}

	private void festeZahlen() {
		for (int i = 0; i <= 80; i++) {
			if (textfeld.get(i).getText().equals("")) {
				textfeld.get(i).setEnabled(true);
				int MAX = 1;
				new JTextField(MAX);
				((AbstractDocument) textfeld.get(i).getDocument())
						.setDocumentFilter(new DocumentSizeFilter(MAX, "[1-9]"));
			} else {
				textfeld.get(i).setForeground(new Color(50, 225, 200)); // 20,180,225
																		// 50,225,200
				textfeld.get(i).setEditable(false);
				textfeld.get(i).setFont(new Font("Comic Sans", Font.BOLD, 20));
			}
		}
	}

	private void überprüfen() {
		new Senden(this, null);
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == btn1) {
			btn1.setEnabled(false);
			überprüfen();
		}
		if (evt.getSource() == btn2) {
			spiel.minispielEnde(null);
			this.dispose();
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == btn1) {
			btn1.setForeground(new Color(20, 180, 225));
		}
		if (e.getSource() == btn2) {
			btn2.setForeground(new Color(20, 180, 225));
		}
	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == btn1) {
			btn1.setForeground(new Color(140, 220, 225));
		}
		if (e.getSource() == btn2) {
			btn2.setForeground(new Color(140, 220, 225));
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	private void zufallsZahlenAusgeben() {
		Random zufallsgenerator;
		zufallsgenerator = new Random();

		for (int i = 1; i <= 1; i++) {
			r = zufallsgenerator.nextInt(2);
		}
	}

	public void setAbschickenEnabled(boolean b) {
		btn1.setEnabled(b);
	}
}
