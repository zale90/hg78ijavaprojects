package Game.Minigames.Automat;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.awt.event.MouseListener;

public class Automat extends JFrame implements MouseListener, Runnable {

	private static final long serialVersionUID = -4829006466801396677L;

	private JLabel gewonnenKasten, background;
	private Panel left, mid, right;
	private JPanel points;
	private JLabel stopL, stopM, stopR, start, beenden, punkte, pktAkt;
	private JLabel moegl, fruechte, sApfel, sEuro, sBanane, sKirsche, sGlocke,
			versuche, penny;
	private Thread threadL, threadM, threadR, automat;
	private int blinken, score, stand, l, m, r, vs, guthaben;
	private boolean blinke = false;
	private boolean druck = false;

	private Kontrolle kontrolle;

	public Automat(int v, int ein, Kontrolle kontrolle) {
		super("SpielAutomat");
		this.setSize(350, 500);
		this.setLocationRelativeTo(kontrolle);
		this.setLayout(null);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setBackground(new Color(0, 10, 0));
		this.setForeground(new Color(100, 10, 5));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		vs = v;
		guthaben = ein;
		this.kontrolle = kontrolle;

		// Labels
		pktAkt = new JLabel(String.valueOf(score));
		pktAkt.setSize(190, 65);
		pktAkt.setLocation(195, 135);
		pktAkt.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		pktAkt.setVisible(false);
		pktAkt.setForeground(Color.red);
		this.add(pktAkt);

		gewonnenKasten = new JLabel(new ImageIcon(
				"files/Minigames/Automat/kasten.png"));
		gewonnenKasten.setSize(190, 60);
		gewonnenKasten.setLocation(125, 135);
		gewonnenKasten.setVisible(false);
		this.add(gewonnenKasten);

		// Panels erstellen
		points = new JPanel();
		points.setSize(190, 60);
		points.setLocation(125, 135);
		points.setOpaque(false);
		points.setLayout(null);
		points.setBorder(new EtchedBorder(5, Color.yellow, Color.red.darker()));
		this.add(points);

		left = new Panel(5);
		left.setLocation(125, 75);
		this.add(left);

		mid = new Panel(15);
		mid.setLocation(190, 75);
		this.add(mid);

		right = new Panel(25);
		right.setLocation(255, 75);
		this.add(right);

		// Buttons erstellen
		stopL = new JLabel(new ImageIcon(
				"files/Minigames/Automat/stop_button_deact.png"));
		stopL.setSize(60, 60);
		stopL.setLocation(125, 270);
		this.add(stopL);
		stopL.addMouseListener(this);

		stopM = new JLabel(new ImageIcon(
				"files/Minigames/Automat/stop_button_deact.png"));
		stopM.setSize(60, 60);
		stopM.setLocation(190, 270);
		this.add(stopM);
		stopM.addMouseListener(this);

		stopR = new JLabel(new ImageIcon(
				"files/Minigames/Automat/stop_button_deact.png"));
		stopR.setSize(60, 60);
		stopR.setLocation(255, 270);
		this.add(stopR);
		stopR.addMouseListener(this);

		start = new JLabel("Starten");
		start.setSize(80, 25);
		start.setLocation(185, 350);
		start.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		start.setForeground(Color.LIGHT_GRAY);
		start.setEnabled(true);
		this.add(start);
		start.addMouseListener(this);

		beenden = new JLabel("Zurück");
		beenden.setSize(80, 25);
		beenden.setLocation(10, 425);
		beenden.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		beenden.setForeground(Color.LIGHT_GRAY);
		this.add(beenden);
		beenden.addMouseListener(this);

		versuche = new JLabel("Versuche: " + String.valueOf(vs));
		versuche.setSize(150, 25);
		versuche.setLocation(10, 400);
		versuche.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		versuche.setForeground(Color.white);
		this.add(versuche);
		versuche.addMouseListener(this);

		score = 0;
		stand = 0;
		punkte = new JLabel("Gewinn: " + String.valueOf(stand) + "€");
		punkte.setSize(250, 25);
		punkte.setLocation(10, 10);
		punkte.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		punkte.setForeground(Color.white);
		this.add(punkte);

		// Symbole
		moegl = new JLabel("Gewinn bei:");
		moegl.setSize(150, 30);
		moegl.setLocation(10, 70);
		moegl.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		moegl.setForeground(Color.white);
		this.add(moegl);

		fruechte = new JLabel("3X Frucht>1€");
		fruechte.setSize(120, 30);
		fruechte.setLocation(10, 100);
		fruechte.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		fruechte.setForeground(Color.white);
		this.add(fruechte);

		sApfel = new JLabel("X3>5€", new ImageIcon(
				"files/Minigames/Automat/symbole/apfel.png"), 0);
		sApfel.setSize(120, 30);
		sApfel.setLocation(0, 130);
		sApfel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		sApfel.setForeground(Color.white);
		this.add(sApfel);

		sEuro = new JLabel("X3>10€", new ImageIcon(
				"files/Minigames/Automat/symbole/euro.png"), 0);
		sEuro.setSize(120, 30);
		sEuro.setLocation(0, 160);
		sEuro.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		sEuro.setForeground(Color.white);
		this.add(sEuro);

		sBanane = new JLabel("X3>20€", new ImageIcon(
				"files/Minigames/Automat/symbole/banane.png"), 0);
		sBanane.setSize(120, 30);
		sBanane.setLocation(0, 190);
		sBanane.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		sBanane.setForeground(Color.white);
		this.add(sBanane);

		sKirsche = new JLabel("X3>50€", new ImageIcon(
				"files/Minigames/Automat/symbole/kirsche.png"), 0);
		sKirsche.setSize(120, 30);
		sKirsche.setLocation(0, 220);
		sKirsche.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		sKirsche.setForeground(Color.white);
		this.add(sKirsche);

		sGlocke = new JLabel("X3>100€", new ImageIcon(
				"files/Minigames/Automat/symbole/glocke.png"), 0);
		sGlocke.setSize(120, 30);
		sGlocke.setLocation(5, 250);
		sGlocke.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		sGlocke.setForeground(Color.white);
		this.add(sGlocke);

		penny = new JLabel(new ImageIcon("files/Minigames/Automat/penny.png"));
		penny.setSize(111, 127);
		penny.setLocation(225, 370);
		this.add(penny);

		background = new JLabel(new ImageIcon("files/Minigames/Automat/bg.png"));
		background.setSize(350, 500);
		background.setLocation(0, 0);
		this.add(background);

		this.setAlwaysOnTop(true);
		this.setVisible(true);

		automat = new Thread(this);
		automat.start();
	}

	public void run() {
		try {
			while (true) {
				points.updateUI();
				Thread.sleep(25);
				if (left.getSpeed() == 0 && right.getSpeed() == 0
						&& mid.getSpeed() == 0 && !start.isEnabled()) {
					if ((left.getWinner() == 1 && mid.getWinner() == 3 && right
							.getWinner() == 4)
							|| (left.getWinner() == 1 && mid.getWinner() == 4 && right
									.getWinner() == 3)
							|| (left.getWinner() == 4 && mid.getWinner() == 1 && right
									.getWinner() == 3)
							|| (left.getWinner() == 4 && mid.getWinner() == 3 && right
									.getWinner() == 1)
							|| (left.getWinner() == 3 && mid.getWinner() == 4 && right
									.getWinner() == 1)
							|| (left.getWinner() == 3 && mid.getWinner() == 1 && right
									.getWinner() == 4)
							|| (left.getWinner() == 1 && mid.getWinner() == 1 && right
									.getWinner() == 3)
							|| (left.getWinner() == 1 && mid.getWinner() == 1 && right
									.getWinner() == 4)
							|| (left.getWinner() == 1 && mid.getWinner() == 3 && right
									.getWinner() == 1)
							|| (left.getWinner() == 1 && mid.getWinner() == 4 && right
									.getWinner() == 1)
							|| (left.getWinner() == 3 && mid.getWinner() == 1 && right
									.getWinner() == 1)
							|| (left.getWinner() == 4 && mid.getWinner() == 1 && right
									.getWinner() == 1)
							|| (left.getWinner() == 3 && mid.getWinner() == 3 && right
									.getWinner() == 1)
							|| (left.getWinner() == 3 && mid.getWinner() == 3 && right
									.getWinner() == 4)
							|| (left.getWinner() == 3 && mid.getWinner() == 1 && right
									.getWinner() == 3)
							|| (left.getWinner() == 3 && mid.getWinner() == 4 && right
									.getWinner() == 3)
							|| (left.getWinner() == 4 && mid.getWinner() == 3 && right
									.getWinner() == 3)
							|| (left.getWinner() == 4 && mid.getWinner() == 4 && right
									.getWinner() == 1)
							|| (left.getWinner() == 4 && mid.getWinner() == 4 && right
									.getWinner() == 3)
							|| (left.getWinner() == 4 && mid.getWinner() == 1 && right
									.getWinner() == 4)
							|| (left.getWinner() == 4 && mid.getWinner() == 3 && right
									.getWinner() == 4)
							|| (left.getWinner() == 1 && mid.getWinner() == 4 && right
									.getWinner() == 4)
							|| (left.getWinner() == 1 && mid.getWinner() == 3 && right
									.getWinner() == 3)
							|| (left.getWinner() == 3 && mid.getWinner() == 4 && right
									.getWinner() == 4)) {
						if (score != 1) {
							score = 1;
							stand = stand + score;
						}
						pktAkt.setText(String.valueOf(score) + "€");
						punkte
								.setText("Gewinn: " + String.valueOf(stand)
										+ "€");
						pktAkt.setLocation(195, 135);
						blinke = true;
					}
					if (left.getWinner() == 1 && mid.getWinner() == 1
							&& right.getWinner() == 1) {
						if (score != 5) {
							score = 5;
							stand = stand + score;
						}
						pktAkt.setText(String.valueOf(score) + "€");
						punkte
								.setText("Gewinn: " + String.valueOf(stand)
										+ "€");
						pktAkt.setLocation(195, 135);
						blinke = true;
					}
					if (left.getWinner() == 2 && mid.getWinner() == 2
							&& right.getWinner() == 2) {
						if (score != 10) {
							score = 10;
							stand = stand + score;
						}
						pktAkt.setText(String.valueOf(score) + "€");
						punkte
								.setText("Gewinn: " + String.valueOf(stand)
										+ "€");
						pktAkt.setLocation(185, 135);
						blinke = true;
					}
					if (left.getWinner() == 3 && mid.getWinner() == 3
							&& right.getWinner() == 3) {
						if (score != 20) {
							score = 20;
							stand = stand + score;
						}
						pktAkt.setText(String.valueOf(score) + "€");
						punkte
								.setText("Gewinn: " + String.valueOf(stand)
										+ "€");
						pktAkt.setLocation(185, 135);
						blinke = true;
					}
					if (left.getWinner() == 4 && mid.getWinner() == 4
							&& right.getWinner() == 4) {
						if (score != 50) {
							score = 50;
							stand = stand + score;
						}
						pktAkt.setText(String.valueOf(score) + "€");
						punkte
								.setText("Gewinn: " + String.valueOf(stand)
										+ "€");
						pktAkt.setLocation(185, 135);
						blinke = true;
					}
					if (left.getWinner() == 5 && mid.getWinner() == 5
							&& right.getWinner() == 5) {
						if (score != 100) {
							score = 100;
							stand = stand + score;
						}
						pktAkt.setText(String.valueOf(score) + "€");
						punkte
								.setText("Gewinn: " + String.valueOf(stand)
										+ "€");
						pktAkt.setLocation(175, 135);
						blinke = true;
					}
				}
				if (left.getSpeed() != 0 && right.getSpeed() != 0
						&& mid.getSpeed() != 0) {
					if (start.isEnabled())
						start.setEnabled(false);
					if (l == 0 && left.getSpeed() == 10 && !druck)
						stopL.setIcon(new ImageIcon(
								"files/Minigames/Automat/stop_button.png"));
					else if (!druck)
						stopL
								.setIcon(new ImageIcon(
										"files/Minigames/Automat/stop_button_deact.png"));
					if (m == 0 && mid.getSpeed() == 10 && !druck)
						stopM.setIcon(new ImageIcon(
								"files/Minigames/Automat/stop_button.png"));
					else if (!druck)
						stopM
								.setIcon(new ImageIcon(
										"files/Minigames/Automat/stop_button_deact.png"));
					if (r == 0 && right.getSpeed() == 10 && !druck)
						stopR.setIcon(new ImageIcon(
								"files/Minigames/Automat/stop_button.png"));
					else if (!druck)
						stopR
								.setIcon(new ImageIcon(
										"files/Minigames/Automat/stop_button_deact.png"));
				}

				if (blinke && blinken < 10) {
					start.setEnabled(false);
					Thread.sleep(200);
					gewonnenKasten.setVisible(true);
					pktAkt.setVisible(false);
					Thread.sleep(200);
					gewonnenKasten.setVisible(false);
					pktAkt.setVisible(true);
					blinken++;
				}
				if (blinken == 10) {
					pktAkt.setVisible(false);
					blinke = false;
				}
				if ((!blinke && left.getSpeed() == 0 && right.getSpeed() == 0 && mid
						.getSpeed() == 0)
						&& vs > 0) {
					start.setEnabled(true);
					beenden.setEnabled(false);
				}
				if (vs == 0) {
					if (left.getSpeed() == 0 && right.getSpeed() == 0
							&& mid.getSpeed() == 0)
						beenden.setEnabled(true);
					start.setEnabled(false);
				}
			}
		} catch (Exception e) {
		}
	}

	public void mouseClicked(MouseEvent evt) {
		if (evt.getSource() == stopL && left.getSpeed() == 10) {
			stopL.setIcon(new ImageIcon(
					"files/Minigames/Automat/stop_button_deact.png"));
			left.stop();
			threadL = null;
			blinken = 0;
			score = 0;
			l++;
		}
		if (evt.getSource() == stopM && mid.getSpeed() == 10) {
			stopM.setIcon(new ImageIcon(
					"files/Minigames/Automat/stop_button_deact.png"));
			mid.stop();
			threadM = null;
			blinken = 0;
			score = 0;
			m++;
		}
		if (evt.getSource() == stopR && right.getSpeed() == 10) {
			stopR.setIcon(new ImageIcon(
					"files/Minigames/Automat/stop_button_deact.png"));
			right.stop();
			threadR = null;
			blinken = 0;
			score = 0;
			r++;
		}
		if (evt.getSource() == start && start.isEnabled()) {
			left.setLaufen(true);
			mid.setLaufen(true);
			right.setLaufen(true);
			left.setWait(31);
			mid.setWait(41);
			right.setWait(51);
			l = 0;
			m = 0;
			r = 0;
			start.setEnabled(false);
			stopL.setIcon(new ImageIcon(
					"files/Minigames/Automat/stop_button.png"));
			stopM.setIcon(new ImageIcon(
					"files/Minigames/Automat/stop_button.png"));
			stopR.setIcon(new ImageIcon(
					"files/Minigames/Automat/stop_button.png"));

			threadL = new Thread(left);
			threadM = new Thread(mid);
			threadR = new Thread(right);
			threadL.start();
			threadM.start();
			threadR.start();

			stopL.setIcon(new ImageIcon(
					"files/Minigames/Automat/stop_button_deact.png"));
			stopM.setIcon(new ImageIcon(
					"files/Minigames/Automat/stop_button_deact.png"));
			stopR.setIcon(new ImageIcon(
					"files/Minigames/Automat/stop_button_deact.png"));
			vs--;
			versuche.setText("Versuche: " + String.valueOf(vs));
		}
		if (evt.getSource() == beenden && vs == 0 && beenden.isEnabled()) {
			this.dispose();
			kontrolle.setGuthaben(guthaben + stand);
			kontrolle.setVisible(true);
		}
	}

	public void mouseExited(MouseEvent evt) {
		if (evt.getSource() == start)
			start.setForeground(Color.LIGHT_GRAY);
		if (evt.getSource() == beenden && beenden.isEnabled())
			beenden.setForeground(Color.LIGHT_GRAY);
	}

	public void mouseEntered(MouseEvent evt) {
		if (evt.getSource() == start)
			start.setForeground(Color.white);
		if (evt.getSource() == beenden && beenden.isEnabled())
			beenden.setForeground(Color.white);

	}

	public void mousePressed(MouseEvent evt) {
		if (evt.getSource() == stopL && left.getSpeed() == 10) {
			stopL.setIcon(new ImageIcon(
					"files/Minigames/Automat/stop_button_pressed.png"));
			druck = true;
		}
		if (evt.getSource() == stopM && mid.getSpeed() == 10) {
			stopM.setIcon(new ImageIcon(
					"files/Minigames/Automat/stop_button_pressed.png"));
			druck = true;
		}
		if (evt.getSource() == stopR && right.getSpeed() == 10) {
			stopR.setIcon(new ImageIcon(
					"files/Minigames/Automat/stop_button_pressed.png"));
			druck = true;
		}
	}

	public void mouseReleased(MouseEvent evt) {
		if (evt.getSource() == stopL && left.getSpeed() == 10)
			druck = false;
		if (evt.getSource() == stopM && mid.getSpeed() == 10)
			druck = false;
		if (evt.getSource() == stopR && right.getSpeed() == 10)
			druck = false;
	}
}
