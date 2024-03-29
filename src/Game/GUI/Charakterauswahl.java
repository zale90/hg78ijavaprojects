package Game.GUI;

import java.util.*;
import javax.swing.*;
import Game.*;

public class Charakterauswahl extends JPanel {

	private static final long serialVersionUID = -5037394024976426750L;

	public Charakterauswahl(ArrayList<Avatar> avaArray) {

		this.setSize(995, 672);
		this.setLocation(0, 0);
		this.setLayout(null);
		this.setBackground(null);

		JLabel lblÜberschrift = new JLabel("Wähle einen Charakter!");
		lblÜberschrift.setLocation(300, 10);
		lblÜberschrift.setSize(400, 50);
		lblÜberschrift.setFont(Optionen.FONT_TITLE);
		this.add(lblÜberschrift);

		JPanel pnlCharacter1 = new CharakterGUI(avaArray.get(0), 0, 100);
		JPanel pnlCharacter2 = new CharakterGUI(avaArray.get(1), 330, 100);
		JPanel pnlCharacter3 = new CharakterGUI(avaArray.get(2), 660, 100);

		this.add(pnlCharacter1);
		this.add(pnlCharacter2);
		this.add(pnlCharacter3);

		this.setVisible(true);
	}

}
