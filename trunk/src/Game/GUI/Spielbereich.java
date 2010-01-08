package Game.GUI;

import javax.swing.*;
import java.awt.event.*;


public class Spielbereich extends JPanel implements MouseListener
{

	/**
	 * Der Bereich in dem das eigentliche Spiel abläuft (Wohnung)
	 */
	private static final long serialVersionUID = -634199744790451275L;
	private JLabel lblbackGround;
	private JLabel lblDoor;
	//private Spieloberfläche spielUI;
	private ImageIcon imgDoorClosed;
	private ImageIcon imgDoorOpened;
	
	
	public Spielbereich(int x,int y, Spieloberfläche spielUI) {
		
		//this.spielUI = spielUI;
		
		imgDoorClosed = new ImageIcon("files/gameImages/doorclosed.png");
		imgDoorOpened = new ImageIcon("files/gameImages/null.png");
		
		
		lblDoor = new JLabel(imgDoorClosed);
		lblDoor.setSize(81, 340);
		lblDoor.setLocation(687,90);
		lblDoor.addMouseListener(this);
		lblDoor.setOpaque(false);
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
		// Tür öffnet sich bei MouseOver.
		if (mevtE.getSource() == lblDoor) {
			//spielUI.zeigeNachrichtInKonsole("Maus ist im in Türbereich.");
			lblDoor.setIcon(imgDoorOpened);
		}

		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//Alle Objekte werden zurück in den Standardzustand versetzt.
		//Tür wird geschlossen.
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
