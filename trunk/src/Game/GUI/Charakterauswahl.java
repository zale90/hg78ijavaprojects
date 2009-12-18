package Game.GUI;

import java.awt.event.*;


import javax.swing.*;
import Game.*;

public class Charakterauswahl extends JPanel implements MouseListener
{

	private static final long serialVersionUID = -5037394024976426750L;
	private Object lblStart;

	public Charakterauswahl(Avatar av1, Avatar av2, Avatar av3) {
		this.setSize(1000, 700);
		this.setLocation(0,0);
		this.setLayout(null);
		this.setBackground(null);
		
		JLabel lbl‹berschrift = new JLabel("W‰hlen Sie einen Charakter");
		lbl‹berschrift.setLocation(300, 10);
		lbl‹berschrift.setSize(400, 50);
		lbl‹berschrift.setFont(Optionen.FONT_TITLE);
		this.add(lbl‹berschrift);
		
		JLabel lblStart = new JLabel("Spiel starten");
		lblStart.setLocation(850, 620);
		lblStart.setSize(150,40);
		lblStart.setFont(Optionen.FONT_BUTTON);
		lblStart.addMouseListener(this);
		this.add(lblStart);
		
		JPanel pnlCharacter1 = new CharakterGUI(av1, 10, 100);
		JPanel pnlCharacter2 = new CharakterGUI(av2, 340, 100);
		JPanel pnlCharacter3 = new CharakterGUI(av3, 670, 100);
		
		
		
		
		this.setVisible(true);
	}
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == this.lblStart){
			//JOA NE?!
		} 
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
