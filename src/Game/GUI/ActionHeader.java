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
	 * Der Header, der erscheint, wenn man die Maus �ber ein Interaktionsobjekt bewegt.
	 */
	private static final long serialVersionUID = 1L;

	public ActionHeader(Spielbereich spielbereich) {
		this.spielbereich = spielbereich;
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setOpaque(true);
		this.setFont(Optionen.FONT_ACTION_HEADER);
		this.setSize(150,40);
		this.setForeground(Color.WHITE);
		this.setBackground(new java.awt.Color(0,0,0,20));	
		this.setVisible(false);
	}
	
	
	public void run() {
		while (spielbereich.getAktivesObjekt() != -1){
			if (this.isVisible()) {
				try {
					Thread.sleep(10);
				} catch (Exception e) {}
				p = MouseInfo.getPointerInfo().getLocation();
				SwingUtilities.convertPointFromScreen(p, spielbereich);
				//spielbereich.spieloberfl�che.zeigeNachrichtInKonsole(p.getX() + ", " + p.getY());
				
				//this.setLocation(p);
				if ((p.getX() + this.getWidth() / 2) < 800) {
					this.setLocation((int) (p.getX() - this.getWidth() / 2), (int) p.getY() - 30);
				} else {
					this.setLocation(800 - this.getWidth(),(int) p.getY() - 30);
				}
				this.repaint();
				
			}
		}
		
			
	}

}