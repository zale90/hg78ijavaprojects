package Game.GUI;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Game.Optionen;

public class ActionHeader extends JLabel implements Runnable{

	private Point p;
	private Spielbereich spielbereich;
	
	/**
	 * Der Header, der erscheint, wenn man die Maus über ein Interaktionsobjekt bewegt.
	 */
	private static final long serialVersionUID = 1L;

	public ActionHeader(Spielbereich spielbereich) {
		this.spielbereich = spielbereich;
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(Optionen.FONT_ACTION_HEADER);
		this.setSize(150,40);
		this.setForeground(Color.BLACK);
		this.setBackground(Color.WHITE);
		this.setVisible(false);
	}
	
	
	public void run() {
		while (spielbereich.getAktivesObjekt() != -1){
			if (this.isVisible()) {
				try {
					Thread.sleep(20);
				} catch (Exception e) {}
				p = MouseInfo.getPointerInfo().getLocation();
				SwingUtilities.convertPointFromScreen(p, spielbereich);
				//spielbereich.spieloberfläche.zeigeNachrichtInKonsole(p.getX() + ", " + p.getY());
				this.setLocation(p);
				this.repaint();
				
			}
		}
		
			
	}

}
