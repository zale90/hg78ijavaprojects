package Game.GUI;

import java.awt.event.*;
import java.util.*;


import javax.swing.*;
import Game.*;

public class Charakterauswahl extends JPanel implements MouseListener
{

	private static final long serialVersionUID = -5037394024976426750L;
	private Object lblStart;

	public Charakterauswahl(ArrayList<Avatar> avaArray) {
		this.setSize(1000, 700);
		this.setLocation(0,0);
		this.setLayout(null);
		this.setBackground(null);
		
		JLabel lblÜberschrift = new JLabel("Wählen Sie einen Charakter");
		lblÜberschrift.setLocation(300, 10);
		lblÜberschrift.setSize(400, 50);
		lblÜberschrift.setFont(Optionen.FONT_TITLE);
		this.add(lblÜberschrift);

		
		JPanel pnlCharacter1 = new CharakterGUI(avaArray.get(1), 10, 100);
		JPanel pnlCharacter2 = new CharakterGUI(avaArray.get(2), 340, 100);
		JPanel pnlCharacter3 = new CharakterGUI(avaArray.get(3), 670, 100);
		
		
		
		
		this.setVisible(true);
	}
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
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
