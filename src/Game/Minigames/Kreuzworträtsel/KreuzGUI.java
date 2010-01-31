package Game.Minigames.Kreuzworträtsel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Game.*;
import Game.Minigames.Minispiel;

public class KreuzGUI extends JFrame implements ActionListener, KeyListener,
		MouseListener, Minispiel {

	private static final long serialVersionUID = 1L;
	private Matrix m;
	private JButton exit;
	private JLabel lblCap;
	private Kaestchen[] gelb, fZzt;
	private int pos, punkte;
	private MausLabel lblMaus;
	private Thread threadlblMaus;
	private Spiel spiel;
	private char[] alph;

	public KreuzGUI() {
		super();
		this.setSize(505, 655);
		this.setLayout(null);
		this.setAlwaysOnTop(true);
		this.setUndecorated(true);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		alph = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

		exit = new JButton("Abschicken");
		exit.setSize(250, 60);
		exit.setLocation(127, 545);
		exit.addActionListener(this);
		this.add(exit);
		
		lblCap = new JLabel("Kreuzworträtsel");
		lblCap.setSize(505, 50);
		lblCap.setLocation(0, 15);
		lblCap.setFont(new Font("Arial", Font.BOLD, 50));
		lblCap.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblCap);

		lblMaus = new MausLabel(this);
		this.add(lblMaus);
		threadlblMaus = new Thread(lblMaus);

		m = new Matrix(9, 9);
		matrixEinfuegen(25, 75);
		m.addMouseListener(this);
		m.addKeyListener(this);

		pos = 0;

		matrixSchwaerzen();
	}
	
	public void matrixEinfuegen(int x, int y) {
		m.matrixVerbildschirmen(x, y);

		for (int i = 0; i < m.gibGroesse(); i++) {
			this.add(m.gibKaest()[i]);
		}
	}

	public int antwortenAuslesen() {
		punkte = 0;

		for (int i = 0; i < m.gibFragen().length; i++) {
			Kaestchen ant = m.gibKaest()[((m.gibY()
					* (m.gibFragen()[i].gibY() - 1) + m.gibFragen()[i].gibX()) - 1)];
			String hAnt = ant.toString();

			if (hAnt.equalsIgnoreCase(m.gibFragen()[i].gibAntwort()))
				punkte++;
			else
				ant.markAnt(new Color(255, 0, 0));
		}
		return punkte;
	}

	public void start(Spiel spiel) {
		this.spiel = spiel;
		this.setVisible(true);
		m.gibKaest()[3].requestFocus();
	}
	
	public void reset() {
		
		matrixEntfernen();

		m = new Matrix(9, 9);
		m.addKeyListener(this);
		m.addMouseListener(this);

		pos = 0;
		gelb = null;
		fZzt = null;
		
		matrixEinfuegen(25, 75);
		matrixSchwaerzen();
		
		this.repaint();
	}

	public void matrixEntfernen() {
		for (int i = 0; i < m.gibGroesse(); i++) {
			this.remove(m.gibKaest()[i]);
			m.gibKaest()[i].removeKeyListener(this);
			m.gibKaest()[i].removeMouseListener(this);
		}
	}

	public void matrixSchwaerzen() {
		m.matrixFaerben(1, 1, Color.BLACK, false);
		m.matrixFaerben(1, 8, Color.BLACK, false);
		m.matrixFaerben(2, 1, Color.BLACK, false);
		m.matrixFaerben(4, 5, Color.BLACK, false);
		m.matrixFaerben(4, 6, Color.BLACK, false);
		m.matrixFaerben(4, 7, Color.BLACK, false);
		m.matrixFaerben(5, 5, Color.BLACK, false);
		m.matrixFaerben(5, 6, Color.BLACK, false);
		m.matrixFaerben(5, 7, Color.BLACK, false);
		m.matrixFaerben(6, 5, Color.BLACK, false);
		m.matrixFaerben(6, 6, Color.BLACK, false);
		m.matrixFaerben(6, 7, Color.BLACK, false);
		m.matrixFaerben(7, 4, Color.BLACK, false);
		m.matrixFaerben(7, 5, Color.BLACK, false);
		m.matrixFaerben(7, 6, Color.BLACK, false);
		m.matrixFaerben(7, 7, Color.BLACK, false);
		m.matrixFaerben(7, 8, Color.BLACK, false);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {			
			if (antwortenAuslesen() == 12) {
				JOptionPane.showMessageDialog(this, "Du hast eine Reise nach Bad Münster Eifel und 50 € gewonnen !");
				
				Information[] infos = {
						new Information(Information.AENDERN_LUXUS,
								Information.ART_UM_WERT, 15),
						new Information(Information.AENDERN_SOZIALES,
								Information.ART_UM_WERT, 10), 
						new Information(Information.AENDERN_GELD,
								Information.ART_UM_WERT, 50) };

				spiel
						.minispielEnde(infos,
								"Du hast eine Tagesfahrt nach Bad Münster Eifel und 50 € gewonnen!");
			} else {
				JOptionPane.showMessageDialog(this, "Du hast leider nicht gewonnen.");
				
				spiel
						.minispielEnde(null,
								"Leider hat deine Leistung im Kreuzworträtsel nicht gereicht.");
			}
			reset();
			this.setVisible(false);
		}

	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {
		try {
			char t = Character.toUpperCase(e.getKeyChar());

			for (int i = 0; i < alph.length; i++) {
				if (t == alph[i]) {
					if (pos <= gelb.length)
						gelb[pos].setText(String.valueOf(t));

					pos++;
				}

			}
			gelb[pos].requestFocus();
		} catch (Exception exp) {
		}
	}

	public void mouseClicked(MouseEvent e) {
		try {
			if (gelb != null) {
				for (int i = 0; i < gelb.length; i++)
					gelb[i].setBackground(new Color(255, 255, 255));

			}

			Kaestchen src = (Kaestchen) e.getSource();
			Frage q = src.gibFrage();
			fZzt = m.gibKaest()[((m.gibY() * (q.gibY() - 1) + q.gibX()) - 1)]
					.gibAntw();
			pos = 0;

			for (int i = 0; i < fZzt.length; i++) {
				fZzt[i].setBackground(new Color(255, 246, 133));

				if (!fZzt[0].getText().equals(""))
				{
					for(int j = 0; j< fZzt.length; j++)
						fZzt[j].setText("");
				}
					
			}
			gelb = fZzt;
		} catch (Exception exp) {
		}
	}

	public void mouseEntered(MouseEvent e) {
		try {
			Kaestchen src = (Kaestchen) e.getSource();

			lblMaus.setText(src.gibFrage().gibFrage());
			lblMaus.setVisible(true);

			try {
				threadlblMaus = new Thread(lblMaus);
				threadlblMaus.start();
			} catch (Exception ex) {
			}
		} catch (Exception exp) {
		}
	}

	public void mouseExited(MouseEvent e) {
		lblMaus.setVisible(false);
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}
}
