package Server.GUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import Game.Optionen;
import Server.Highscores;

public class ServerGUI extends JFrame implements MouseListener {
	
	private static final long serialVersionUID = -1046827739949356186L;
	
	private JLabel lblAdmin;
	private AdminGUI adminGUI;
	private HighscoreListe[] scorePanels;

	public ServerGUI(Highscores[] list) {
		super("Highscores");
		this.setSize(1000, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setNativeLookAndFeel();
        
        this.setIconImage(new ImageIcon("files/Icon-Europa.png").getImage());
		
		JLabel lblHeader = new JLabel("Highscores des Spiels " + Optionen.NAME);
		lblHeader.setSize(900, 50);
		lblHeader.setLocation(20, 20);
		lblHeader.setFont(Optionen.FONT_TITLE);
		this.add(lblHeader);
		
		// Highscores anzeigen
		int xPos = 20;
		int yPos = 70;
		scorePanels = new HighscoreListe[list.length];
		for(int i = 0; i < list.length; i++) {
			scorePanels[i] = new HighscoreListe(list[i]);
			scorePanels[i].setLocation(xPos, yPos);
			this.add(scorePanels[i]);
			xPos += 320;
		}
		
		
		// Admin
		lblAdmin = new JLabel("Admin");
		lblAdmin.setSize(80, 30);
		lblAdmin.setLocation(880, 20);
		lblAdmin.setOpaque(true);
		lblAdmin.setBackground(Color.RED);
		lblAdmin.addMouseListener(this);
		lblAdmin.setFont(Optionen.FONT_BUTTON);
		lblAdmin.setBorder(new LineBorder(Color.BLACK));
		lblAdmin.setHorizontalAlignment(0);
		this.add(lblAdmin);
		
		adminGUI = new AdminGUI();
		
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		if(evt.getSource() == lblAdmin) {
			adminGUI.setVisible(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
	/**
	 * Setzt das Windows Look & Feel!
	 */
    private void setNativeLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName() );
        } catch( Exception e ) {}
    }
}
