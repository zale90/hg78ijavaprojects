package Game.GUI;

import javax.swing.*;

import Game.Optionen;

public class Spieloberfläche extends JPanel {

	private static final long serialVersionUID = 846296549789605647L;
	
	private JTextField console;
	private JLabel lblHeading;
	
	public Spieloberfläche() {
		this.setSize(1000, 700);
		this.setLocation(0, 0);
		this.setLayout(null);
		this.setBackground(null);
		
		// GUI-Elemente erzeugen
		lblHeading = new JLabel(Optionen.NAME);
		lblHeading.setFont(Optionen.FONT_TITLE);
		lblHeading.setSize(1000, 20);
		lblHeading.setLocation(20, 20);
		this.add(lblHeading);
		
		console = new JTextField();
		console.setSize(1000, 100);
		console.setLocation(0, 880);
		this.add(console);
		
		this.setVisible(true);
	}

}
