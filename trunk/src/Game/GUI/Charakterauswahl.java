package Game.GUI;

import javax.swing.*;
import Game.*;

public class Charakterauswahl extends JPanel
{

	private static final long serialVersionUID = -5037394024976426750L;

	public Charakterauswahl() {
		this.setSize(1000, 500);
		this.setLocation(0, 100);
		this.setLayout(null);
		this.setBackground(null);
		
		JLabel lblÜberschrift = new JLabel("Wählen Sie einen Charakter");
		lblÜberschrift.setLocation(10, 20);
		lblÜberschrift.setSize(200, 20);
		lblÜberschrift.setFont(Optionen.FONT_TITLE);
		this.add(lblÜberschrift);
		
		this.setVisible(true);
	}
}
