/**
 * ACHTUNG!
 * WIRD IN DER AKTUELLEN VERSION NICHT MEHR BENUTZT!
 */
package Game.GUI;

import javax.swing.JDialog;
import Game.Ereignis;

public class Ereignisfenster extends JDialog {

	private static final long serialVersionUID = 8375152556875337533L;
	
	public Ereignisfenster(Ereignis er) {
		this.setLocationRelativeTo(null);
		this.setSize(400, 200);
	}

}
