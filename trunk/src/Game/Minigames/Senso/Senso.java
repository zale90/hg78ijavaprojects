package Game.Minigames.Senso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.sound.sampled.*;
import java.io.*;

public class Senso extends JFrame implements Runnable, MouseListener,
		Game.Minigames.Minispiel {
	// Labels
	private JLabel us;
	private JLabel punkte;
	private JLabel rot;
	private JLabel gruen;
	private JLabel gelb;
	private JLabel blau;
	private JLabel beenden;
	private JLabel neustart;
	private JLabel lblVerloren;
	private JLabel start;
	private JLabel klicks;
	// Thread & Random
	private Thread spiel;
	private Random zufall;
	// Integers
	private int[] farben;
	private int zahl;
	private int durchgang;
	private int runde;
	private int klick;
	private int points;
	private int vKlicks;
	private int wait;
	// Booleans
	private boolean wiedergeben;
	private boolean neu;
	private boolean verloren;
	private boolean intro;
	private boolean abspielen;
	private boolean leuchten;
	private boolean läuft;
	// sound
	private AudioInputStream audioInputStream;
	private AudioFormat af;
	private int size;
	private byte[] audio;
	private DataLine.Info info;
	private Clip clip;
	// soundfiles
	private File soundR;
	private File soundG;
	private File soundB;
	private File soundY;
	private File soundF;

	private Game.Spiel s;

	public Senso() {
		super("Senso");
		this.setSize(640, 480);
		this.setLocation(100, 100);
		this.getContentPane().setBackground(Color.black);
		this.setUndecorated(true);
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setAlwaysOnTop(true);

		klicks = new JLabel();
		klicks.setSize(150, 25);
		klicks.setLocation(20, 300);
		klicks.setFont(new Font("Impact", 5, 25));
		klicks.setForeground(Color.lightGray);
		this.add(klicks);

		start = new JLabel();
		start.setSize(350, 50);
		start.setLocation(350, 200);
		start.setFont(new Font("Impact", 5, 50));
		start.setForeground(Color.lightGray);
		this.add(start);

		lblVerloren = new JLabel();
		lblVerloren.setSize(350, 30);
		lblVerloren.setLocation(200, 215);
		lblVerloren.setFont(new Font("Impact", 5, 30));
		lblVerloren.setForeground(Color.lightGray);
		this.add(lblVerloren);

		neustart = new JLabel("Neustart");
		neustart.setSize(100, 30);
		neustart.setLocation(20, 200);
		neustart.setFont(new Font("Impact", 5, 25));
		neustart.setForeground(Color.darkGray);
		neustart.addMouseListener(this);
		this.add(neustart);

		beenden = new JLabel("Beenden");
		beenden.setSize(100, 30);
		beenden.setLocation(20, 390);
		beenden.setFont(new Font("Impact", 5, 25));
		beenden.setForeground(Color.gray);
		beenden.addMouseListener(this);
		this.add(beenden);

		us = new JLabel("Senso");
		us.setSize(150, 35);
		us.setLocation(10, 30);
		us.setFont(new Font("Impact", 1, 35));
		us.setForeground(Color.white);
		this.add(us);

		punkte = new JLabel("Score: " + String.valueOf(points));
		punkte.setSize(200, 20);
		punkte.setLocation(400, 400);
		punkte.setFont(new Font("Impact", 0, 20));
		punkte.setForeground(Color.white);
		this.add(punkte);

		rot = new JLabel(new ImageIcon("files/Minigames/Senso/red_0.png"));
		rot.setSize(216, 207);
		rot.setLocation(150, 25);
		rot.setBackground(Color.darkGray);
		rot.addMouseListener(this);
		this.add(rot);

		gruen = new JLabel(new ImageIcon("files/Minigames/Senso/green_0.png"));
		gruen.setSize(216, 207);
		gruen.setLocation(366, 25);
		gruen.setBackground(Color.darkGray);
		gruen.addMouseListener(this);
		this.add(gruen);

		gelb = new JLabel(new ImageIcon("files/Minigames/Senso/yellow_0.png"));
		gelb.setSize(216, 207);
		gelb.setLocation(366, 232);
		gelb.setBackground(Color.darkGray);
		gelb.addMouseListener(this);
		this.add(gelb);

		blau = new JLabel(new ImageIcon("files/Minigames/Senso/blue_0.png"));
		blau.setSize(216, 207);
		blau.setLocation(150, 232);
		blau.setBackground(Color.darkGray);
		blau.addMouseListener(this);
		this.add(blau);

		this.addMouseListener(this);

		// spiel = new Thread(this);
		zufall = new Random();
		
		läuft = true;

		zahl = zufall.nextInt(4);
		// this.setVisible(true);
		soundR = new File("files/Minigames/Senso/rot.wav");
		soundG = new File("files/Minigames/Senso/gruen.wav");
		soundB = new File("files/Minigames/Senso/blau.wav");
		soundY = new File("files/Minigames/Senso/gelb.wav");
		soundF = new File("files/Minigames/Senso/falsch.wav");
	}

	public void start(Game.Spiel senso) {
		this.setVisible(true);
		// new Senso();
		s = senso;
		neustarten(true);
		spiel = new Thread(this);
		spiel.start();
	}

	public void mouseExited(MouseEvent evt) {
		if (evt.getSource() == beenden)
			beenden.setForeground(Color.gray);
		else if (evt.getSource() == neustart && verloren)
			neustart.setForeground(Color.gray);
	}

	public void mouseEntered(MouseEvent evt) {
		if (evt.getSource() == beenden)
			beenden.setForeground(Color.white);
		else if (evt.getSource() == neustart && verloren)
			neustart.setForeground(Color.white);
	}

	public void mouseReleased(MouseEvent evt) {
		if (!neu && !wiedergeben && !verloren && !intro) {
			if (evt.getSource() == rot) {
				rot.setIcon(new ImageIcon("files/Minigames/Senso/red_0.png"));
				if (klick == runde) {
					wiedergeben = true;
					neu = false;
					points++;
					punkte.setText("Score: " + String.valueOf(points));
					punkte.updateUI();
				}
			}
			if (evt.getSource() == gruen) {
				gruen
						.setIcon(new ImageIcon(
								"files/Minigames/Senso/green_0.png"));
				if (klick == runde) {
					wiedergeben = true;
					neu = false;
					points++;
					punkte.setText("Score: " + String.valueOf(points));
					punkte.updateUI();
				}
			}
			if (evt.getSource() == blau) {
				blau.setIcon(new ImageIcon("files/Minigames/Senso/blue_0.png"));
				if (klick == runde) {
					wiedergeben = true;
					neu = false;
					points++;
					punkte.setText("Score: " + String.valueOf(points));
					punkte.updateUI();
				}
			}
			if (evt.getSource() == gelb) {
				gelb
						.setIcon(new ImageIcon(
								"files/Minigames/Senso/yellow_0.png"));
				if (klick == runde) {
					wiedergeben = true;
					neu = false;
					points++;
					punkte.setText("Score: " + String.valueOf(points));
					punkte.updateUI();
				}
			}
		}
	}

	public void mousePressed(MouseEvent evt) {
		try {
			if (!neu && !wiedergeben && !verloren && !intro) {
				if (evt.getComponent() == rot) {
					if (farben[klick] != 1)
						verloren = true;
					else {
						klick++;
						rot.setIcon(new ImageIcon(
								"files/Minigames/Senso/red_1.png"));
						soundAbspielen(soundR);
					}
				} else if (evt.getComponent() == gruen) {
					if (farben[klick] != 2)
						verloren = true;
					else {
						klick++;
						gruen.setIcon(new ImageIcon(
								"files/Minigames/Senso/green_1.png"));
						soundAbspielen(soundG);
					}
				} else if (evt.getComponent() == blau) {
					if (farben[klick] != 3)
						verloren = true;
					else {
						klick++;
						blau.setIcon(new ImageIcon(
								"files/Minigames/Senso/blue_1.png"));
						soundAbspielen(soundB);
					}
				} else if (evt.getComponent() == gelb) {
					if (farben[klick] != 4)
						verloren = true;
					else {
						klick++;
						gelb.setIcon(new ImageIcon(
								"files/Minigames/Senso/yellow_1.png"));
						soundAbspielen(soundY);
					}
				} else if (evt.getComponent() == beenden)
					System.exit(0);
			}
		} catch (Exception e) {
		}
	}

	public void mouseClicked(MouseEvent evt) {
		if (evt.getComponent() == beenden) {
			this.setVisible(false);
			Game.Information[] infos = null;
			if (runde > 0)
			{
			infos = new Game.Information[2];
			infos[0] = new Game.Information(
					Game.Information.AENDERN_BEWERBUNGSFAKTOR,
					Game.Information.ART_UM_WERT, runde);
			infos[1] = new Game.Information(Game.Information.AENDERN_SOZIALES,
					Game.Information.ART_UM_WERT, 5*runde);
			}
			s.minispielEnde(infos);
			neustarten(false);
			if (spiel != null)
				spiel.interrupt();
			spiel = new Thread(this);
			if (intro)
				spiel.start();
		} else if (evt.getComponent() == neustart && verloren)
			neustarten(true);

	}

	public void run() {
		try {
			while (läuft) {
				Thread.sleep(1);
				if (verloren) {
					verloren();
				} else {
					if (intro) {
						rot.setIcon(new ImageIcon(
								"files/Minigames/Senso/red_n.png"));
						gruen.setIcon(new ImageIcon(
								"files/Minigames/Senso/green_n.png"));
						blau.setIcon(new ImageIcon(
								"files/Minigames/Senso/blue_n.png"));
						gelb.setIcon(new ImageIcon(
								"files/Minigames/Senso/yellow_n.png"));
						lblVerloren.setText("");
						start.setText("3");
						spiel.sleep(1000);
						start.setText("2");
						spiel.sleep(1000);
						start.setText("1");
						spiel.sleep(1000);
						start.setLocation(start.getX() - 20, start.getY());
						start.setText("Los!");
						spiel.sleep(250);
						start.setText("");
						start.setLocation(start.getX() + 20, start.getY());
						rot.setIcon(new ImageIcon(
								"files/Minigames/Senso/red_0.png"));
						gruen.setIcon(new ImageIcon(
								"files/Minigames/Senso/green_0.png"));
						blau.setIcon(new ImageIcon(
								"files/Minigames/Senso/blue_0.png"));
						gelb.setIcon(new ImageIcon(
								"files/Minigames/Senso/yellow_0.png"));
						intro = false;
						neu = true;
					}
					if (wiedergeben && !verloren) {
						spiel.sleep(500);
						alteFarbe();
					} else if (neu && !verloren)
						neueFarbe();
					if (!wiedergeben && !neu) {
						String s = "Noch ";
						vKlicks = runde - klick;
						if (vKlicks >= 1)
							s = s + String.valueOf(vKlicks);
						else
							s = "";
						klicks.setText(s);
					} else
						klicks.setText("");
				}
			}
		} catch (Exception e) {
		}
	}

	private void kopieren() {
		int[] copy = farben;
		for (int i = 0; i < farben.length; i++)
			copy[i] = farben[i];
		farben = new int[copy.length + 1];
		for (int i = 0; i < copy.length; i++)
			farben[i] = copy[i];
	}

	private void einfuegen(int pos, int farbe) {
		farben[pos] = farbe;
	}

	private void neueFarbe() {
		try {
			if ((runde % 2) == 0 && wait > 25) {
				wait = wait - 25;
			}
			klick = 0;
			durchgang++;
			zahl = zufall.nextInt(4);
			switch (zahl) {
			case (0):
				rot.setIcon(new ImageIcon("files/Minigames/Senso/red_1.png"));
				soundAbspielen(soundR);
				einfuegen(runde, 1);
				spiel.sleep(wait + 25);
				rot.setIcon(new ImageIcon("files/Minigames/Senso/red_0.png"));
				break;
			case (1):
				gruen
						.setIcon(new ImageIcon(
								"files/Minigames/Senso/green_1.png"));
				soundAbspielen(soundG);
				einfuegen(runde, 2);
				spiel.sleep(wait + 25);
				gruen
						.setIcon(new ImageIcon(
								"files/Minigames/Senso/green_0.png"));
				break;
			case (2):
				blau.setIcon(new ImageIcon("files/Minigames/Senso/blue_1.png"));
				soundAbspielen(soundB);
				einfuegen(runde, 3);
				spiel.sleep(wait + 25);
				blau.setIcon(new ImageIcon("files/Minigames/Senso/blue_0.png"));
				break;
			case (3):
				gelb
						.setIcon(new ImageIcon(
								"files/Minigames/Senso/yellow_1.png"));
				soundAbspielen(soundY);
				einfuegen(runde, 4);
				spiel.sleep(wait + 25);
				gelb
						.setIcon(new ImageIcon(
								"files/Minigames/Senso/yellow_0.png"));
				break;
			}
			durchgang = 0;
			runde++;
			neu = false;
			kopieren();
		} catch (Exception ex) {
		}
	}

	private void alteFarbe() {
		try {
			int alt = 0;
			for (int i = 0; i < farben.length; i++) {
				durchgang++;
				alt = farben[i];
				spiel.sleep(wait);
				switch (alt) {
				case 1:
					rot
							.setIcon(new ImageIcon(
									"files/Minigames/Senso/red_1.png"));
					soundAbspielen(soundR);
					spiel.sleep(wait + 25);
					rot
							.setIcon(new ImageIcon(
									"files/Minigames/Senso/red_0.png"));
					break;
				case 2:
					gruen.setIcon(new ImageIcon(
							"files/Minigames/Senso/green_1.png"));
					soundAbspielen(soundG);
					spiel.sleep(wait + 25);
					gruen.setIcon(new ImageIcon(
							"files/Minigames/Senso/green_0.png"));
					break;
				case 3:
					blau.setIcon(new ImageIcon(
							"files/Minigames/Senso/blue_1.png"));
					soundAbspielen(soundB);
					spiel.sleep(wait + 25);
					blau.setIcon(new ImageIcon(
							"files/Minigames/Senso/blue_0.png"));
					break;
				case 4:
					gelb.setIcon(new ImageIcon(
							"files/Minigames/Senso/yellow_1.png"));
					soundAbspielen(soundY);
					spiel.sleep(wait + 25);
					gelb.setIcon(new ImageIcon(
							"files/Minigames/Senso/yellow_0.png"));
					break;
				}
			}
			wiedergeben = false;
			neu = true;
		} catch (Exception e) {
		}
	}

	private void soundAbspielen(File sound) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(sound);
			af = audioInputStream.getFormat();
			size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
			audio = new byte[size];
			info = new DataLine.Info(Clip.class, af, size);
			audioInputStream.read(audio, 0, size);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void verloren() {
		if (!abspielen) {
			soundAbspielen(soundF);
			abspielen = true;
			neustart.setForeground(Color.gray);
			leuchten = false;
			klicks.setText("");
			klicks.updateUI();
			points = 0;
		}
		lblVerloren.setText("Sie haben leider verloren!");
		switch (farben[klick]) {
		case 1:
			leuchten(rot, "files/Minigames/Senso/red");
			gruen.setIcon(new ImageIcon("files/Minigames/Senso/green_n.png"));
			blau.setIcon(new ImageIcon("files/Minigames/Senso/blue_n.png"));
			gelb.setIcon(new ImageIcon("files/Minigames/Senso/yellow_n.png"));
			break;
		case 2:
			rot.setIcon(new ImageIcon("files/Minigames/Senso/red_n.png"));
			leuchten(gruen, "files/Minigames/Senso/green");
			blau.setIcon(new ImageIcon("files/Minigames/Senso/blue_n.png"));
			gelb.setIcon(new ImageIcon("files/Minigames/Senso/yellow_n.png"));
			break;
		case 3:
			rot.setIcon(new ImageIcon("files/Minigames/Senso/red_n.png"));
			gruen.setIcon(new ImageIcon("files/Minigames/Senso/green_n.png"));
			leuchten(blau, "files/Minigames/Senso/blue");
			gelb.setIcon(new ImageIcon("files/Minigames/Senso/yellow_n.png"));
			break;
		case 4:
			rot.setIcon(new ImageIcon("files/Minigames/Senso/red_n.png"));
			gruen.setIcon(new ImageIcon("files/Minigames/Senso/green_n.png"));
			blau.setIcon(new ImageIcon("files/Minigames/Senso/blue_n.png"));
			leuchten(gelb, "files/Minigames/Senso/yellow");
			break;
		}
	}

	private void neustarten(boolean intro) {
		farben = new int[1];
		durchgang = 0;
		runde = 0;
		klick = 0;
		points = 0;
		wait = 250;
		vKlicks = 0;
		wiedergeben = false;
		verloren = false;
		this.intro = intro;
		abspielen = false;
		lblVerloren.setText("");
		klicks.setText("");
		klicks.updateUI();
		neustart.setForeground(Color.darkGray);
	}

	private void leuchten(JLabel label, String farbe) {
		try {
			if (!leuchten) {
				spiel.sleep(200);
				label.setIcon(new ImageIcon(farbe + "_0.png"));
				spiel.sleep(200);
				label.setIcon(new ImageIcon(farbe + "_1.png"));
				spiel.sleep(200);
				label.setIcon(new ImageIcon(farbe + "_0.png"));
				spiel.sleep(200);
				label.setIcon(new ImageIcon(farbe + "_1.png"));
				leuchten = true;
			}
		} catch (Exception e) {
		}
	}

	public int getScore() {
		return points;
	}
}