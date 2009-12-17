package Game.GUI;

import javax.swing.JPanel;

import Game.*;

public class CharakterGUI extends JPanel {
	
	private static final long serialVersionUID = 3611040866747202320L;
	
	private Avatar avatar;

	public CharakterGUI(Avatar avatar, int x, int y) {
		this.setSize(300, 150);
		this.setLocation(x, y);
		this.setLayout(null);
		
		this.avatar = avatar;
	}

}
