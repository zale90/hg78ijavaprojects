package Game.GUI;

import javax.swing.JDialog;
import Game.Ereignis;
import Game.SpielAnwendung;

public class Ereignisfenster extends JDialog {

	private static final long serialVersionUID = 8375152556875337533L;
	
	public Ereignisfenster(Ereignis er) {
		super();
		this.setLocationRelativeTo(null);
		this.setSize(400, 200);
		this.setVisible(true);
		
	}

}
