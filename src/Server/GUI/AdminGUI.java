package Server.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;

import Server.ScoreServer;

public class AdminGUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -6464529758980285571L;
	
	private static JTextPane console;
	private JButton btnReset;
	
	public AdminGUI() {
		super("Administration");
		this.setSize(500, 280);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLayout(null);
        
        this.setIconImage(new ImageIcon("files/Icon-Europa.png").getImage());
		
        btnReset = new JButton("Reset Highscores");
        btnReset.setSize(150, 30);
        btnReset.setLocation(20, 20);
        btnReset.addActionListener(this);
        this.add(btnReset);
        
		// Konsole
		AdminGUI.console = new JTextPane();
		AdminGUI.console.setSize(498, 150);
		AdminGUI.console.setLocation(0, 100);
		AdminGUI.console.setEditable(true);
		this.add(console);
		
		/* Scrollpane funktioniert natürlich mal wieder nicht :-/
		JScrollPane spConsole = new JScrollPane(console);
		console.setSize(498, 150);
		console.setLocation(0, 100);
        spConsole.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(spConsole);
		*/
	}
	
	public static void consoleText(String text) {
		AdminGUI.console.setText(text + "\n" + console.getText());
		AdminGUI.console.setCaretPosition(0);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == btnReset) {
			ScoreServer.resetHighscores();
		}
	}

}
