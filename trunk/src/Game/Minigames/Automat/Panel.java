package Game.Minigames.Automat;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Panel extends JPanel implements Runnable {
	private static final long serialVersionUID = -1177136871446292336L;

	private JLabel apfel, euro, banane, kirsche, glocke, outside;
	public Thread thread;
	private boolean laeuft = false;
	private boolean stoppen = false;
	public boolean bild = false;
	private int wait, speed, winner;

	public Panel(int wait) {
		this.wait = wait;
		speed = 0;
		this.setSize(60, 180);
		this.setBackground(Color.yellow.darker().darker());
		this.setLayout(null);
		this.setBorder(new EtchedBorder(5, Color.yellow, Color.green.darker()));

		outside = new JLabel();
		outside.setLocation(1, -120);
		outside.setSize(60, 60);
		this.add(outside);

		apfel = new JLabel(new ImageIcon("files/Minigames/Automat/apfel.png"));
		apfel.setLocation(1, 0);
		apfel.setSize(60, 60);
		this.add(apfel);

		euro = new JLabel(new ImageIcon("files/Minigames/Automat/euro.png"));
		euro.setLocation(1, 60);
		euro.setSize(60, 60);
		this.add(euro);

		banane = new JLabel(new ImageIcon("files/Minigames/Automat/banane.png"));
		banane.setLocation(1, 120);
		banane.setSize(60, 60);
		this.add(banane);

		kirsche = new JLabel(new ImageIcon(
				"files/Minigames/Automat/kirsche.png"));
		kirsche.setLocation(1, 180);
		kirsche.setSize(60, 60);
		this.add(kirsche);

		glocke = new JLabel(new ImageIcon("files/Minigames/Automat/glocke.png"));
		glocke.setLocation(1, -60);
		glocke.setSize(60, 60);
		this.add(glocke);

		thread = new Thread(this);
		winner = 0;

		this.setVisible(true);
	}

	public void run() {
		speed = 1;
		int verzoegern = 0;
		int speedRunter = 0;
		try {
			while (laeuft) {
				Thread.sleep(wait);
				apfel.setLocation(1, apfel.getY() + speed);
				euro.setLocation(1, euro.getY() + speed);
				banane.setLocation(1, banane.getY() + speed);
				kirsche.setLocation(1, kirsche.getY() + speed);
				glocke.setLocation(1, glocke.getY() + speed);
				if (speed >= 5 && wait <= 10)
					setBild();
				else {
					apfel.setIcon(new ImageIcon(
							"files/Minigames/Automat/apfel.png"));
					apfel.setSize(60, 60);
					euro
							.setIcon(new ImageIcon(
									"files/Minigames/Automat/euro.png"));
					euro.setSize(60, 60);
					banane.setIcon(new ImageIcon(
							"files/Minigames/Automat/banane.png"));
					banane.setSize(60, 60);
					kirsche.setIcon(new ImageIcon(
							"files/Minigames/Automat/kirsche.png"));
					kirsche.setSize(60, 60);
					glocke.setIcon(new ImageIcon(
							"files/Minigames/Automat/glocke.png"));
					glocke.setSize(60, 60);
				}
				this.updateUI();
				if (apfel.getY() > 180)
					lblRunter(apfel);
				if (euro.getY() > 180)
					lblRunter(euro);
				if (banane.getY() > 180)
					lblRunter(banane);
				if (kirsche.getY() > 180)
					lblRunter(kirsche);
				if (glocke.getY() > 180)
					lblRunter(glocke);
				if (wait > 1 && !stoppen)
					wait = wait - 1;
				if (speed < 10 && !stoppen)
					speed++;
				if (stoppen) {
					verzoegern++;
					speedRunter++;
					if (wait < 50 && verzoegern == 2) {
						wait = wait + 1;
						verzoegern = 0;
					}
					if (speed > 1 && speedRunter == 10) {
						Thread.sleep(25);
						speed--;
						speedRunter = 0;
					}
					if (apfel.getY() == 60 && wait >= 45) {
						apfel.setLocation(1, 60);
						euro.setLocation(1, 120);
						banane.setLocation(1, 180);
						kirsche.setLocation(1, -60);
						glocke.setLocation(1, 0);
						outside.setLocation(1, -120);
						winner = 1;
						speed = 0;
						stoppen = false;
						laeuft = false;
					}
					if (euro.getY() == 60 && wait >= 45) {
						apfel.setLocation(1, 0);
						euro.setLocation(1, 60);
						banane.setLocation(1, 120);
						kirsche.setLocation(1, 180);
						glocke.setLocation(1, -60);
						outside.setLocation(1, -120);
						winner = 2;
						speed = 0;
						stoppen = false;
						laeuft = false;
					}
					if (banane.getY() == 60 && wait >= 45) {
						apfel.setLocation(1, -60);
						euro.setLocation(1, 0);
						banane.setLocation(1, 60);
						kirsche.setLocation(1, 120);
						glocke.setLocation(1, 180);
						outside.setLocation(1, -120);
						winner = 3;
						speed = 0;
						stoppen = false;
						laeuft = false;
					}
					if (kirsche.getY() == 60 && wait >= 45) {
						apfel.setLocation(1, 180);
						euro.setLocation(1, -60);
						banane.setLocation(1, 0);
						kirsche.setLocation(1, 60);
						glocke.setLocation(1, 120);
						outside.setLocation(1, -120);
						winner = 4;
						speed = 0;
						stoppen = false;
						laeuft = false;
					}
					if (glocke.getY() == 60 && wait >= 45) {
						apfel.setLocation(1, 120);
						euro.setLocation(1, 180);
						banane.setLocation(1, -60);
						kirsche.setLocation(1, 0);
						glocke.setLocation(1, 60);
						outside.setLocation(1, -120);
						winner = 5;
						speed = 0;
						stoppen = false;
						laeuft = false;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Fehler!");
		}
	}

	public void stop() {
		stoppen = true;
	}

	private void lblRunter(JLabel icon) {
		outside.setIcon(icon.getIcon());
		outside.setLocation(1, outside.getY() + speed);
		if (outside.getY() >= -60 && icon.getY() >= 240) {
			icon.setLocation(1, -60);
			outside.setLocation(1, -120);
		}
	}

	private void setBild() {
		apfel.setIcon(new ImageIcon("files/Minigames/Automat/apfel_blur.png"));
		apfel.setSize(58, 78);
		euro.setIcon(new ImageIcon("files/Minigames/Automat/euro_blur.png"));
		euro.setSize(42, 74);
		banane
				.setIcon(new ImageIcon(
						"files/Minigames/Automat/banane_blur.png"));
		banane.setSize(54, 73);
		kirsche.setIcon(new ImageIcon(
				"files/Minigames/Automat/kirsche_blur.png"));
		kirsche.setSize(60, 73);
		glocke
				.setIcon(new ImageIcon(
						"files/Minigames/Automat/glocke_blur.png"));
		glocke.setSize(60, 73);
		bild = true;
	}

	public int getWait() {
		return wait;
	}

	public void setLaufen(boolean neu) {
		laeuft = neu;
	}

	public void setWait(int neu) {
		wait = neu;
	}

	public int getSpeed() {
		return speed;
	}

	public int getWinner() {
		return winner;
	}
}