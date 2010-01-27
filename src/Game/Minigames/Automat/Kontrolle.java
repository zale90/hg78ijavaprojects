package Game.Minigames.Automat;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.MouseListener;

public class Kontrolle extends JFrame implements MouseListener,
		Game.Minigames.Minispiel {

	private static final long serialVersionUID = -6408480780542078083L;

	private int guthaben;
	private JLabel bg, info, gH, info2, money;
	private JLabel v1, v2, v3, beenden;
	private Game.Spiel s;

	public Kontrolle() {
		super("Einsatz");
		this.setLocation(450, 250);
		this.setSize(350, 350);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true);

		info = new JLabel("Wie viel Geld wollen Sie einsetzen?");
		info.setSize(350, 20);
		info.setLocation(5, 10);
		info.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		info.setForeground(Color.white);
		this.add(info);

		info2 = new JLabel("Sie haben zu wenig Guthaben!!!");
		info2.setSize(350, 25);
		info2.setLocation(10, 200);
		info2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		info2.setVisible(false);
		info2.setForeground(Color.red);
		this.add(info2);

		gH = new JLabel();
		gH.setSize(200, 20);
		gH.setLocation(5, 40);
		gH.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		gH.setForeground(Color.white);
		this.add(gH);

		v1 = new JLabel("1 Versuch : 2€");
		v1.setSize(140, 30);
		v1.setLocation(10, 75);
		v1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		v1.setForeground(Color.black);
		v1.addMouseListener(this);
		this.add(v1);

		v2 = new JLabel("2 Versuche : 4€");
		v2.setSize(150, 30);
		v2.setLocation(10, 105);
		v2.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		v2.setForeground(Color.blue.darker());
		v2.addMouseListener(this);
		this.add(v2);

		v3 = new JLabel("3 Versuche : 5€");
		v3.setSize(150, 30);
		v3.setLocation(10, 135);
		v3.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		v3.setForeground(Color.red.darker());
		v3.addMouseListener(this);
		this.add(v3);

		beenden = new JLabel("Beenden");
		beenden.setSize(80, 25);
		beenden.setLocation(10, 250);
		beenden.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		beenden.setForeground(Color.LIGHT_GRAY);
		this.add(beenden);
		beenden.addMouseListener(this);

		money = new JLabel(new ImageIcon("files/Minigames/Automat/money.png"));
		money.setSize(150, 105);
		money.setLocation(195, 240);
		this.add(money);

		bg = new JLabel(new ImageIcon("files/Minigames/Automat/bg.png"));
		bg.setSize(350, 500);
		bg.setLocation(0, 0);
		this.add(bg);

		this.setAlwaysOnTop(true);
	}

	public void start(Game.Spiel game) {
		this.setVisible(true);
		guthaben = game.getKontostand();
		gH.setText("Guthaben: " + String.valueOf(guthaben) + "€");
		s = game;
	}

	public void setGuthaben(int neuesGuthaben) {
		guthaben = neuesGuthaben;
		gH.setText("Guthaben: " + String.valueOf(guthaben) + "€");
	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == v1) {
			v1.setForeground(Color.black);
			info2.setVisible(false);
		}
		if (e.getSource() == v2) {
			v2.setForeground(Color.blue.darker());
			info2.setVisible(false);
		}
		if (e.getSource() == v3) {
			v3.setForeground(Color.red.darker());
			info2.setVisible(false);
		}
		if (e.getSource() == beenden)
			beenden.setForeground(Color.lightGray);
	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == v1) {
			v1.setForeground(Color.white);
			if (guthaben < 2)
				info2.setVisible(true);
		}
		if (e.getSource() == v2) {
			v2.setForeground(new Color(183, 215, 238));
			if (guthaben < 4)
				info2.setVisible(true);
		}
		if (e.getSource() == v3) {
			v3.setForeground(Color.red);
			if (guthaben < 5)
				info2.setVisible(true);
		}
		if (e.getSource() == beenden)
			beenden.setForeground(Color.white);
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == v1 && guthaben >= 2) {
			guthaben = guthaben - 2;
			new Automat(1, guthaben, this);
			this.setVisible(false);
		}
		if (e.getSource() == v2 && guthaben >= 4) {
			guthaben = guthaben - 4;
			new Automat(2, guthaben, this);
			this.setVisible(false);
		}
		if (e.getSource() == v3 && guthaben >= 5) {
			guthaben = guthaben - 5;
			new Automat(3, guthaben, this);
			this.setVisible(false);
		}
		if (e.getSource() == beenden) {
			this.setVisible(false);
			Game.Information[] infos = new Game.Information[1];
			infos[0] = new Game.Information(Game.Information.AENDERN_GELD,
					Game.Information.ART_AUF_WERT, guthaben);
			s.minispielEnde(infos);
		}
	}
}