package Game.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import Game.Optionen;

public class MausLabel extends JLabel implements Runnable {
	
	private static final long serialVersionUID = 4150232603403351817L;
	
	private Component comp;
	
	/**
	 * Zeigt ein Label neben der Maus an.
	 * 
	 * @param comp Komponente in der sich das Label befinden darf.
	 */
	public MausLabel(Component comp) {
		this.comp = comp;
		
		// Labelaussehen:
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setOpaque(true);
		this.setFont(Optionen.FONT_ACTION_HEADER);
		this.setSize(130,30);
		this.setForeground(Color.WHITE);
		this.setBackground(new java.awt.Color(0,0,0,150));
		this.setBorder(new LineBorder(Color.BLACK, 2));
		this.setVisible(false);
	}
	
	@Override
	/**
	 * Zeigt das Label neben der Maus an, bis es auf unsichtbar gesetzt wird.
	 */
	public void run() {
		this.setSize(this.getText().length() * 9, 30);
		this.setVisible(true);
		while (isVisible()){
			if (this.isVisible()) {
				
				try {
					Thread.sleep(10);
				} catch (Exception e) {}
				
				Point p = MouseInfo.getPointerInfo().getLocation();
				SwingUtilities.convertPointFromScreen(p, comp);
				
				if ((p.getX() + this.getWidth()) < comp.getWidth()-5) {
					this.setLocation((int) (p.getX()), (int) p.getY() + 20);
				} else {
					this.setLocation(comp.getWidth()-5 - this.getWidth(),(int) p.getY() + 20);
				}
				
			}
		}
	}
}
