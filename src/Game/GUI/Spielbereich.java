package Game.GUI;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;


public class Spielbereich extends JPanel implements MouseListener
{

	/**
	 * Der Bereich in dem das eigentliche Spiel abl�uft (Wohnung)
	 */
	private static final long serialVersionUID = -634199744790451275L;
	private JLabel lblbackGround;
	private JLabel lblDoor;
	private Spieloberfl�che spielUI;
	private ImageIcon imgDoorClosed;
	private ImageIcon imgDoorOpened;
	
	
	public Spielbereich(int x,int y, Spieloberfl�che spielUI) {
		
		this.spielUI = spielUI;
		
		imgDoorClosed = new ImageIcon("files/avatarImages/Horst Terarno.jpg");
		imgDoorOpened = new ImageIcon("files/avatarImages/Hasma Hamada.jpg");
		
		
		lblDoor = new JLabel(imgDoorClosed);
		lblDoor.setSize(50,100);
		lblDoor.setLocation(200,50);
		lblDoor.addMouseListener(this);
		this.add(lblDoor);
		
		
		
		lblbackGround = new JLabel(new ImageIcon("files/gameImages/bg.png"));
		lblbackGround.setSize(800, 500);
		lblbackGround.setLocation(0,0);
		lblbackGround.setOpaque(true);
		this.add(lblbackGround);
		
		
		
		this.setSize(800,500);
		this.setLocation(x,y);
		this.setBackground(null);
		this.setLayout(null);
		this.setVisible(true);
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent mevtE) {
		// T�r �ffnet sich bei MouseOver.
		if (mevtE.getSource() == lblDoor) {
			spielUI.zeigeNachrichtInKonsole("Maus ist im in T�rbereich.");
			lblDoor.setIcon(imgDoorOpened);
		}

		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//Alle Objekte werden zur�ck in den Standardzustand versetzt.
		//T�r wird geschlossen.
		if(arg0.getSource() == lblDoor) {
			lblDoor.setIcon(imgDoorClosed);
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	

	
	
}
