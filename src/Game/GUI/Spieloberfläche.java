package Game.GUI;

import javax.swing.*;
import Game.*;

public class Spieloberfläche extends JPanel {

	private static final long serialVersionUID = 846296549789605647L;
	
	private JTextArea console;
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
		
		console = new JTextArea("Hallo");
		//console.setEditable(false);
        JScrollPane spConsole = new JScrollPane(console);
        //spConsole.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spConsole.setSize(995, 100);
        spConsole.setLocation(0, 568);
		this.add(spConsole);
		
		console.append("Noch mehr Text");
		
		this.setVisible(true);
	}

}
