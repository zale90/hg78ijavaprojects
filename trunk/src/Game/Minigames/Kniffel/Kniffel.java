package Game.Minigames.Kniffel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.UIManager;

import Game.Spiel;
import Game.Minigames.Minispiel;
import Game.*;

public class Kniffel extends JWindow implements MouseListener, Minispiel,
		ActionListener {

	private static final long serialVersionUID = -7909200209059071915L;

	private enum Zustand {
		Start, Wurf1, Wurf2;
	}

	private Spiel spiel;
	final int anzahlWuerfel = 5;
	protected JLabel[] wuerfel = new JLabel[anzahlWuerfel];
	JLabel geld, einsatz;
	JCheckBox[] checkboxes = new JCheckBox[anzahlWuerfel];
	JLabel btnVerdoppeln, hintergrund, grün;
	JLabel btnAktion, btnbeenden;
	JButton btnPlus, btnMinus;
	JTextField tfEinsatz, tfBargeld;
	protected ImageIcon[] zahlen = new ImageIcon[6];
	Zustand zustand = Zustand.Start;
	JTextArea erklärung;
	JPanel rand1;
	private int bargeld = 0;
	private final String folder = "files/minigames/Gewinnspiel/";
	String text = "Um zu gewinnen, müsst du 3,4 oder 5 gleiche Zahlen Würfeln.\nDu kannst auch gewinnen, indem du ein Fullhouse,eine kleine\nStraße oder eine große Straße würfelst.";
	String text1 = "Markiere nun die Würfel die du halten willst.\nDu kannst auch Verdoppeln um mehr Gewinn zu bekommen.";

	String wurf1 = "1. Wurf", wurf2 = "2. Wurf", neuesSpiel = "Neues Spiel";

	public Kniffel() {
		super();
		this.setSize(400, 400);
		this.setLayout(null);
		this.setLocation(500, 330);

		zahlen[0] = new ImageIcon(folder + "Nummer11.gif");
		zahlen[1] = new ImageIcon(folder + "Nummer12.gif");
		zahlen[2] = new ImageIcon(folder + "Nummer13.gif");
		zahlen[3] = new ImageIcon(folder + "Nummer14.gif");
		zahlen[4] = new ImageIcon(folder + "Nummer15.gif");
		zahlen[5] = new ImageIcon(folder + "Nummer16.gif");

		tfEinsatz = new JTextField("0");
		tfEinsatz.setSize(55, 25);
		tfEinsatz.setLocation(110, 155);
		tfEinsatz.setEditable(false);
		this.add(tfEinsatz);

		tfBargeld = new JTextField(bargeld + "");
		tfBargeld.setSize(55, 25);
		tfBargeld.setLocation(230, 155);
		tfBargeld.setEditable(false);
		this.add(tfBargeld);
		
		try{
      	  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch(Exception e) {
      	  }
		
		int x = 70;
		for (int i = 0; i < anzahlWuerfel; i++) {
			int y = 195;
			wuerfel[i] = new JLabel();
			wuerfel[i].setSize(40, 40);
			wuerfel[i].setLocation(x + 10, y);
			wuerfel[i].setIcon(zahlen[i]);
			this.add(wuerfel[i]);

			y += 50;
			checkboxes[i] = new JCheckBox();
			checkboxes[i].setBorderPainted(false);
			checkboxes[i].setContentAreaFilled(false);
			checkboxes[i].setSize(20, 20);
			checkboxes[i].setBackground(new Color(146, 73, 0));
			checkboxes[i].setLocation(x + 18, y + 5);
			checkboxes[i].setEnabled(false);
			this.add(checkboxes[i]);
			x += 50;
		}
		btnVerdoppeln = new JLabel("Verdoppeln");
		btnVerdoppeln.setSize(100, 35);
		btnVerdoppeln.setLocation(90, 300);
		btnVerdoppeln.setFont(new Font("Impact", 5, 20));
		btnVerdoppeln.setForeground(new Color(159, 250, 138));
		btnVerdoppeln.setEnabled(false);
		btnVerdoppeln.addMouseListener(this);
		this.add(btnVerdoppeln);

		btnAktion = new JLabel(wurf1);
		btnAktion.setSize(100, 35);
		btnAktion.setLocation(250, 300);
		btnAktion.setFont(new Font("Impact", 5, 20));
		btnAktion.setForeground(new Color(159, 250, 138));
		btnAktion.addMouseListener(this);
		this.add(btnAktion);

		btnbeenden = new JLabel("Beenden");
		btnbeenden.setSize(100, 35);
		btnbeenden.setLocation(180, 340);
		btnbeenden.setFont(new Font("Impact", 5, 20));
		btnbeenden.setForeground(new Color(159, 250, 138));
		btnbeenden.addMouseListener(this);
		this.add(btnbeenden);

		btnPlus = new JButton(">");
		btnPlus.setSize(15, 15);
		btnPlus.setMargin(new Insets(0, 0, 0, 0));
		btnPlus.setLocation(175, 159);
		btnPlus.addActionListener(this);
		this.add(btnPlus);

		btnMinus = new JButton("<");
		btnMinus.setSize(15, 15);
		btnMinus.setMargin(new Insets(0, 0, 0, 0));
		btnMinus.setLocation(83, 159);
		btnMinus.addActionListener(this);
		this.add(btnMinus);

		geld = new JLabel("Bargeld");
		geld.setSize(100, 35);
		geld.setLocation(235, 130);
		geld.setForeground(new Color(234, 139, 0));
		this.add(geld);

		einsatz = new JLabel("Einsatz");
		einsatz.setSize(100, 35);
		einsatz.setLocation(115, 130);
		einsatz.setForeground(new Color(234, 139, 0));
		this.add(einsatz);

		erklärung = new JTextArea(text);
		erklärung.setSize(350, 100);
		erklärung.setLocation(25, 25);
		erklärung.setEditable(false);
		erklärung.setForeground(new Color(234, 139, 0));
		erklärung.setOpaque(false);
		erklärung.setFont(new Font("Arial", Font.PLAIN, 12));
		this.add(erklärung);

		rand1 = new JPanel();
		rand1.setSize(370, 125);
		rand1.setLocation(15, 10);
		rand1.setLayout(null);
		this.add(rand1);

		grün = new JLabel(new ImageIcon(folder + "grün11.jpg"));
		grün.setSize(370, 125);
		grün.setLocation(0, 0);
		rand1.add(grün);
		// grün.setBorder(new TitledBorder ("Erklärungen und Hilfen"));

		hintergrund = new JLabel(new ImageIcon(folder + "Holztisch.jpg"));
		hintergrund.setSize(400, 400);
		hintergrund.setLocation(0, 0);
		this.add(hintergrund);

		this.setVisible(false);
		
		try{
	      	  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        }
	        catch(Exception e) {
	      	  }

	}

	private void reset() {
		try{
	      	  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	        }
	        catch(Exception e) {
	      	  }
		zustand = Zustand.Start;
		for (int i = 0; i < checkboxes.length; i++) {
			checkboxes[i].setSelected(false);
			checkboxes[i].setEnabled(false);
		}
		btnAktion.setText(wurf1);
		erklärung.setText(text);
		tfEinsatz.setEnabled(true);
		btnVerdoppeln.setEnabled(false);
		btnPlus.setEnabled(true);
		btnMinus.setEnabled(true);
		try{
	      	  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        }
	        catch(Exception e) {
	      	  }
	}

	public void actionPerformed(ActionEvent e) {
		try{
	      	  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	        }
	        catch(Exception f) {
	      	  }
		if (e.getSource() == btnPlus) {
			double bargeld = Double.parseDouble(tfBargeld.getText());
			int einsatz = Integer.parseInt(tfEinsatz.getText());
			if (bargeld >= 1 && einsatz < 10) {
				tfEinsatz.setText(einsatz + 1 + "");
				tfBargeld.setText(bargeld - 1 + "");
			}
		} else if (e.getSource() == btnMinus) {
			double bargeld = Double.parseDouble(tfBargeld.getText());
			int einsatz = Integer.parseInt(tfEinsatz.getText());
			if (einsatz >= 1) {
				tfEinsatz.setText(einsatz - 1 + "");
				tfBargeld.setText(bargeld + 1 + "");
			}
		}
		try{
	      	  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        }
	        catch(Exception f) {
	      	  }
	}

	public void wuerfelGefallen(int position) {
	
		try{
	      	  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	        }
	        catch(Exception e) {
	      	  }
		checkboxes[position].setEnabled(true);
		boolean ende = true;
		for (JCheckBox cb : checkboxes) {
			if (!cb.isEnabled()) {
				ende = false;
			}
		}
		if (ende && zustand == Zustand.Wurf1)
			endeErsterWurf();
		else if (ende && zustand == Zustand.Wurf2)
			endeZweiterWurf();
		try{
	      	  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        }
	        catch(Exception f) {
	      	  }
	}

	private void endeZweiterWurf() {
		try{
	      	  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	        }
	        catch(Exception e) {
	      	  }
		btnAktion.setEnabled(true);
		int[] ergebnisse = new int[wuerfel.length];
		int[] anzahlZahlen = new int[zahlen.length];
		for (int i = 0; i < wuerfel.length; i++) {
			ergebnisse[i] = getZahl(i);
			anzahlZahlen[ergebnisse[i] - 1]++;
		}
		Arrays.sort(anzahlZahlen);
		int maxAnzahlGleicherZahlen = anzahlZahlen[anzahlZahlen.length - 1];
		int maxAnzahlGleicherZahlen2 = anzahlZahlen[anzahlZahlen.length - 2];
		double bargeld = Double.parseDouble(tfBargeld.getText());
		int einsatz = Integer.parseInt(tfEinsatz.getText());
		if (maxAnzahlGleicherZahlen == 3) {
			if (maxAnzahlGleicherZahlen2 == 2) {
				erklärung
						.setText("Du hast eine Fullhouse!\nDu gewinnst das doppelte deines Einsatzes.\nVersuch dein Glück doch noch einmal!");
				tfEinsatz.setText("0");
				tfBargeld.setText(bargeld + einsatz + einsatz + "");
			} else {
				erklärung
						.setText("Du bekommst deinen Einsatz zurück!\nVersuch dein Glück doch noch einmal!");
				tfEinsatz.setText("0");
				tfBargeld.setText(bargeld + einsatz + "");
			}
		} else if (maxAnzahlGleicherZahlen == 4) {
			erklärung
					.setText("Du hast gewonnen!\nDu bekommst das doppelte deines Einsatzes!\nVersuch dein Glück doch noch einmal!");
			tfEinsatz.setText("0");
			tfBargeld.setText(bargeld + einsatz + einsatz + "");
		} else if (maxAnzahlGleicherZahlen == 5) {
			erklärung
					.setText("Du hast gewonnen!\nDu bekommst das dreifache deines Einsatzes!\nVersuch dein Glück doch noch einmal!");
			tfEinsatz.setText("0");
			tfBargeld.setText(bargeld + einsatz + einsatz + "");
		}

		else if (maxAnzahlGleicherZahlen < 3) {
			erklärung
					.setText("Du hast verloren und dein Einsatz ist weg!\nVersuch dein Glück doch noch einmal!");
			tfEinsatz.setText("0");
			tfBargeld.setText(bargeld + "");
		}
		boolean eins = false;
		boolean zwei = false;
		boolean drei = false;
		boolean vier = false;
		boolean fünf = false;
		boolean sechs = false;

		for (int i = 0; i < wuerfel.length; i++) {
			if (getZahl(i) == 1) {
				eins = true;
			}
			if (getZahl(i) == 2) {
				zwei = true;
			}
			if (getZahl(i) == 3) {
				drei = true;
			}
			if (getZahl(i) == 4) {
				vier = true;
			}
			if (getZahl(i) == 5) {
				fünf = true;
			}
			if (getZahl(i) == 6) {
				sechs = true;
			}
		}
		if (eins == true && zwei == true && drei == true && vier == true) {
			erklärung
					.setText("Du hast eine kleine Straße und du bekommst deinen Einsatz\n zurück! Versuch dein Glück doch noch einmal!");
			tfEinsatz.setText("0");
			tfBargeld.setText(bargeld + einsatz + "");
		}
		if (zwei == true && drei == true && vier == true && fünf == true) {
			erklärung
					.setText("Du hast eine kleine Straße und du bekommst deinen Einsatz\n zurück! Versuch dein Glück doch noch einmal!");
			tfEinsatz.setText("0");
			tfBargeld.setText(bargeld + einsatz + "");
		}
		if (drei == true && vier == true && fünf == true && sechs == true) {
			erklärung
					.setText("Du hast eine kleine Straße und du bekommst deinen Einsatz\n zurück! Versuch dein Glück doch noch einmal!");
			tfEinsatz.setText("0");
			tfBargeld.setText(bargeld + einsatz + "");
		}
		if (eins == true && zwei == true && drei == true && vier == true
				&& fünf == true) {
			erklärung
					.setText("Du hast eine große Straße und du bekommst den doppelten\n Einsatz zurück! Versuch dein Glück doch noch einmal!");
			tfEinsatz.setText("0");
			tfBargeld.setText(bargeld + einsatz + einsatz + "");
		}
		if (sechs == true && zwei == true && drei == true && vier == true
				&& fünf == true) {
			erklärung
					.setText("Du hast eine große Straße und du bekommst den doppelten\n Einsatz zurück! Versuch dein Glück doch noch einmal!");
			tfEinsatz.setText("0");
			tfBargeld.setText(bargeld + einsatz + einsatz + "");
		}
		eins = false;
		zwei = false;
		drei = false;
		vier = false;
		fünf = false;
		sechs = false;
		for (JCheckBox cb : checkboxes) {
			cb.setEnabled(false);
		}
		try{
	      	  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        }
	        catch(Exception f) {
	      	  }
	}

	private int getZahl(int position) {
		try{
	      	  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	        }
	        catch(Exception e) {
	      	  }
		for (int i = 0; i < zahlen.length; i++)
			if (wuerfel[position].getIcon().equals(zahlen[i]))
				return i + 1;
		try{
	      	  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        }
	        catch(Exception f) {
	      	  }
		return 0;
	}

	private void endeErsterWurf() {
		try{
	      	  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	        }
	        catch(Exception e) {
	      	  }
		btnAktion.setEnabled(true);
		btnVerdoppeln.setEnabled(true);
		try{
	      	  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        }
	        catch(Exception f) {
	      	  }
	}

	private void wuerfelWerfen() {
		try{
	      	  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	        }
	        catch(Exception e) {
	      	  }
		for (int i = 0; i < wuerfel.length; i++) {
			if (!checkboxes[i].isSelected()) {
				new Thread(new Wuerfler(i, this)).start();
				checkboxes[i].setEnabled(false);
			}
		}
		try{
	      	  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        }
	        catch(Exception f) {
	      	  }
	}

	// @Override
	public void mouseClicked(MouseEvent e) {
		try{
	      	  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	        }
	        catch(Exception f) {
	      	  }
		if (e.getSource() == btnVerdoppeln && btnVerdoppeln.isEnabled()) {
			double bargeld = Double.parseDouble(tfBargeld.getText());
			int einsatz = Integer.parseInt(tfEinsatz.getText());
			if (bargeld - (einsatz) >= 1) {
				tfEinsatz.setText(einsatz + einsatz + "");
				tfBargeld.setText(bargeld - (einsatz) + "");
				btnVerdoppeln.setEnabled(false);
			}
		}
		if (e.getSource() == btnAktion && zustand == Zustand.Start
				&& btnAktion.isEnabled()) {
			zustand = Zustand.Wurf1;
			btnAktion.setText(wurf2);
			erklärung.setText(text1);
			for (JCheckBox cb : checkboxes)
				cb.setEnabled(true);
			btnAktion.setEnabled(false);
			btnVerdoppeln.setEnabled(false);
			btnPlus.setEnabled(false);
			btnMinus.setEnabled(false);
			btnbeenden.setEnabled(false);
			wuerfelWerfen();
		} else if (e.getSource() == btnAktion && zustand == Zustand.Wurf1
				&& btnAktion.isEnabled()) {
			boolean auswahl = true;
			for (JCheckBox cb : checkboxes) {
				if (!cb.isSelected())
					auswahl = false;
			}
			if (auswahl == false) {
				zustand = Zustand.Wurf2;
				btnAktion.setText(neuesSpiel);
				btnVerdoppeln.setEnabled(false);
				btnAktion.setEnabled(false);
				btnPlus.setEnabled(false);
				btnMinus.setEnabled(false);
				btnbeenden.setEnabled(true);
				wuerfelWerfen();
			} else {
				endeZweiterWurf();
				zustand = Zustand.Wurf2;
				btnAktion.setText(neuesSpiel);
				btnVerdoppeln.setEnabled(false);
				btnAktion.setEnabled(true);
				btnPlus.setEnabled(false);
				btnMinus.setEnabled(false);
				btnbeenden.setEnabled(true);
				wuerfelWerfen();
			}
		} else if (e.getSource() == btnAktion && zustand == Zustand.Wurf2
				&& btnAktion.isEnabled()) {
			reset();
		} else if (e.getSource() == btnbeenden && btnbeenden.isEnabled()) {
			double geld = Double.parseDouble((tfBargeld.getText()));
			int geldInt = (int) geld;

			Information[] infos = { new Information(Information.AENDERN_GELD,
					Information.ART_AUF_WERT, geldInt) };
			this.setVisible(false);
			spiel.minispielEnde(infos);
			try{
		      	  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		        }
		        catch(Exception f) {
		      	  }
		}
		try{
	      	  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        }
	        catch(Exception f) {
	      	  }
	}

	// @Override
	public void mouseEntered(MouseEvent e) {
		try{
	      	  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	        }
	        catch(Exception f) {
	      	  }
		if (e.getSource() == btnVerdoppeln) {
			btnVerdoppeln.setForeground(Color.red);
		} else if (e.getSource() == btnAktion) {
			btnAktion.setForeground(Color.red);
		} else if (e.getSource() == btnbeenden) {
			btnbeenden.setForeground(Color.red);
		}
		try{
	      	  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        }
	        catch(Exception f) {
	      	  }
	}

	// @Override
	public void mouseExited(MouseEvent e) {
		try{
	      	  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	        }
	        catch(Exception f) {
	      	  }
		if (e.getSource() == btnVerdoppeln) {
			btnVerdoppeln.setForeground(new Color(159, 250, 138));
		} else if (e.getSource() == btnAktion) {
			btnAktion.setForeground(new Color(159, 250, 138));
		} else if (e.getSource() == btnbeenden) {
			btnbeenden.setForeground(new Color(159, 250, 138));
		}
		try{
	      	  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        }
	        catch(Exception f) {
	      	  }
	}

	// @Override
	public void mousePressed(MouseEvent arg0) {
		try{
	      	  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	        }
	        catch(Exception e) {
	      	  }
	        try{
		      	  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		        }
		        catch(Exception f) {
		      	  }
	}

	// @Override
	public void mouseReleased(MouseEvent arg0) {
		try{
	      	  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	        }
	        catch(Exception e) {
	      	  }
	        try{
		      	  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		        }
		        catch(Exception f) {
		      	  }
	}

	@Override
	public void start(Spiel spiel) {
		try{
	      	  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	        }
	        catch(Exception e) {
	      	  }
		this.spiel = spiel;
		this.setVisible(true);
		bargeld = spiel.getKontostand();
		tfBargeld.setText(bargeld + "");
		new Kniffel();
		try{
	      	  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        }
	        catch(Exception f) {
	      	  }
	}
}