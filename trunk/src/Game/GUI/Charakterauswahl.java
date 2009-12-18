package Game.GUI;

import javax.swing.*;

public class Charakterauswahl extends JPanel {

	private static final long serialVersionUID = -5037394024976426750L;

	public Charakterauswahl() {
		this.setSize(1000, 500);
		this.setLocation(0, 100);
		this.setLayout(null);
		this.setBackground(null);
		
		JLabel lbl‹berschrift = new JLabel("W‰hlen Sie einen Charakter");
		lbl‹berschrift.setLocation(10, 20);
		lbl‹berschrift.setSize(200, 20);
		this.add(lbl‹berschrift);
		
		this.setVisible(true);
	}
}
