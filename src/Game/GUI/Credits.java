package Game.GUI;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Credits extends JPanel {
	
	private static final long serialVersionUID = 2500415931767014676L;

	public Credits() {
		this.setSize(494, 672);
		this.setLocation(500, 0);
		this.setLayout(null);
		this.setBackground(null);
		
		this.setBorder(new LineBorder(Color.BLACK, 2));
		
		this.setVisible(true);
	}
}
