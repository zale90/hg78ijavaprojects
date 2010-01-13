/**
 * ACHTUNG!
 * WIRD IN DER AKTUELLEN VERSION NICHT MEHR BENUTZT!
 */
package Game.GUI;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import Game.Ereignis;

public class Ereignisfenster extends JPanel {

	private static final long serialVersionUID = 8375152556875337533L;
	
	public Ereignisfenster(Ereignis er) {		
		this.setSize(200,350);
		this.setLocation(400, 175);
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

}
