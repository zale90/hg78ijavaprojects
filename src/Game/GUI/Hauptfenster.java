package Game.GUI;

import javax.swing.*;

import Game.Optionen;

public class Hauptfenster extends JFrame {

	private static final long serialVersionUID = -8343782304567423757L;
	
	private JPanel mainPanel;

	public Hauptfenster() {
		super(Optionen.NAME);
		this.setSize(1000, 700);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setNativeLookAndFeel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.setVisible(true);
	}
	
	public void zeigePanel(JPanel panel) {
		if(mainPanel != null) {
			this.remove(mainPanel);
		}
		mainPanel = panel;
		this.add(mainPanel);
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
