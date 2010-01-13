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
		this.setOpaque(true);
		this.setFont(Optionen.FONT_ACTION_HEADER);
		this.setSize(130,30);
		this.setForeground(Color.WHITE);
		this.setBackground(new java.awt.Color(0,0,0,150));
		this.setVisible(false);
	}
	
	
	public void run() {
		this.setSize(this.getText().length() * 9, 30);
		this.setVisible(true);
		while (spielbereich.getAktivesObjekt() != -1){
			if (this.isVisible()) {
				try {
					Thread.sleep(10);
				} catch (Exception e) {}
				p = MouseInfo.getPointerInfo().getLocation();
				SwingUtilities.convertPointFromScreen(p, spielbereich);
				//spielbereich.spieloberfläche.zeigeNachrichtInKonsole(p.getX() + ", " + p.getY());
				
				//this.setLocation(p);
				if ((p.getX() + this.getWidth()) < 800) {
					this.setLocation((int) (p.getX()), (int) p.getY() + 20);
				} else {
					this.setLocation(800 - this.getWidth(),(int) p.getY() + 20);
				}
				//this.repaint();
				
			}
		}
		
			
	}

}
