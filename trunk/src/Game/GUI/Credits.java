package Game.GUI;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

public class Credits extends JPanel {
	
	private static final long serialVersionUID = 2500415931767014676L;
	
	private final String text = "" +
			"Hier findet ihr die Cedits!\n" +
			"";
	
	private JTextPane tpInfos;
	
	public Credits() {
		this.setSize(494, 672);
		this.setLocation(500, 0);
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		
		this.setBorder(new LineBorder(Color.BLACK, 2));
		
		// Textfeld anzeigen
		tpInfos = new JTextPane();
		tpInfos.setText(text);
		tpInfos.setSize(490, 670);
		tpInfos.setLocation(0, 0);
		tpInfos.setBackground(Color.BLACK);
		tpInfos.setForeground(Color.WHITE);
		this.add(tpInfos);
		
		
		this.setVisible(true);
	}
}
