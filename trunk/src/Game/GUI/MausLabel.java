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
	private boolean running = false;

	public MausLabel(String text, Component comp) {
		this.comp = comp;
		System.out.println(text);
		this.setText(text);
		this.setSize(text.length() * 9, 30);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setOpaque(true);
		this.setFont(Optionen.FONT_ACTION_HEADER);
		this.setForeground(Color.WHITE);
		this.setBackground(new java.awt.Color(0,0,0,150));
		this.setBorder(new LineBorder(Color.BLACK, 2));
		this.setVisible(false);
	}
	
	@Override
	public void run() {
		running = true;
		System.out.println("Thread gestartet!");
		this.setVisible(true);
		while (running) {
			if (this.isVisible()) {
				
				try {
					Thread.sleep(100);
				} catch (Exception e) {}
				
				Point p = MouseInfo.getPointerInfo().getLocation();
				SwingUtilities.convertPointFromScreen(p, comp);
				if ((p.getX() + this.getWidth()) < comp.getWidth()) {
					this.setLocation((int) (p.getX()), (int) p.getY() + 20);
				} else {
					this.setLocation(comp.getWidth() - this.getWidth(),(int) p.getY() + 20);
				}
			}
		}
	}
	
	public void stop() {
		running = false;
		System.out.println("Gestoppt!");
	}

}
