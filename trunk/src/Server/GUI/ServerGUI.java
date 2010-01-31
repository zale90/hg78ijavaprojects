package Server.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import Game.Optionen;
import Server.Highscores;

public class ServerGUI extends JFrame implements MouseListener, ComponentListener {

	private static final long serialVersionUID = -1046827739949356186L;

	private JLabel lblAdmin, lblX;
	private AdminGUI adminGUI;
	private HighscoreListe[] scorePanels;

	public ServerGUI(Highscores[] list, boolean fullscreen) {
		super("Highscores: " + Optionen.NAME);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		if(fullscreen) {
			this.setSize(size);
		} else {
			this.setSize(size.width-100, size.height-100);
		}
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.WHITE);
		this.setNativeLookAndFeel();
		this.setUndecorated(fullscreen);
		this.addComponentListener(this);

		this.setIconImage(new ImageIcon("files/Icon-Europa.png").getImage());
		
		// Logo anzeigen
		JLabel lblLogo = new JLabel(new ImageIcon("files/LogoText.png"));
		lblLogo.setSize(907, 300);
		lblLogo.setLocation(170, 10);
		this.add(lblLogo);

		// Highscores anzeigen
		int xPos = 70;
		int yPos = 350;
		scorePanels = new HighscoreListe[list.length];
		for (int i = 0; i < list.length; i++) {
			scorePanels[i] = new HighscoreListe(list[i]);
			scorePanels[i].setLocation(xPos, yPos);
			this.add(scorePanels[i]);
			xPos += 400;
		}

		// Admin
		lblAdmin = new JLabel("Admin");
		lblAdmin.setSize(80, 30);
		lblAdmin.setOpaque(true);
		lblAdmin.setBackground(Color.RED);
		lblAdmin.addMouseListener(this);
		lblAdmin.setFont(Optionen.FONT_BUTTON);
		lblAdmin.setBorder(new LineBorder(Color.BLACK));
		lblAdmin.setHorizontalAlignment(0);
		this.add(lblAdmin);
		
		// X anzeigen
		if(fullscreen) {
			lblX = new JLabel("X");
			lblX.setSize(30, 30);
			lblX.setOpaque(true);
			lblX.setBackground(Color.RED);
			lblX.addMouseListener(this);
			lblX.setFont(Optionen.FONT_BUTTON);
			lblX.setBorder(new LineBorder(Color.BLACK));
			lblX.setHorizontalAlignment(0);
			this.add(lblX);			
		}
		

		adminGUI = new AdminGUI();

		this.setVisible(true);
	}

	public void aktualisiereTabellen() {
		for (HighscoreListe list : scorePanels) {
			list.aktualisiereTabelle();
		}
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		if (evt.getSource() == lblAdmin) {
			// Prüfen ob Kennwort richtig, dann Admin-Konsole anzeigen!
	        JPasswordField passwordField = new JPasswordField(30);
	        passwordField.setToolTipText("Bitte Kennwort eingeben");
	        JOptionPane.showMessageDialog(this, passwordField, "Bitte Kennwort für Admin-Zugriff eingeben:", JOptionPane.OK_OPTION);
	        String input = String.valueOf(passwordField.getPassword());
	        
			if(input.equals("PlankalküL")) {
				adminGUI.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Das eingegebene Kennwort ist leider falsch! Sie haben keinen Zugriff auf die Einstellungen!", "Kennwort falsch", JOptionPane.ERROR_MESSAGE);
			}
		} else if(evt.getSource() == lblX) {
			System.exit(0);
		}
	}
	
	public void componentResized(ComponentEvent e) {
		lblAdmin.setLocation(this.getSize().width-(lblAdmin.getSize().width + 50), 20);
		if(lblX != null) {
			lblX.setLocation(lblAdmin.getLocation().x-(lblX.getSize().width + 20), 20);
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

	@Override
	public void componentHidden(ComponentEvent arg0) {
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
	}

	@Override
	public void componentShown(ComponentEvent arg0) {		
	}

	/**
	 * Setzt das Windows Look & Feel!
	 */
	private void setNativeLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
	}
}
