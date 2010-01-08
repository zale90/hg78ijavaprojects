package Game.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Spielbereich extends JPanel implements MouseListener
{

	/**
	 * Der Bereich in Dem das eigentlich Spiel abläuft (Wohnung)
	 */
	private static final long serialVersionUID = -634199744790451275L;
	private JLabel lblbackGround;
	private JLabel lblDoor;
	private Spieloberfläche spielUI;
	private ImageIcon imgDoorClosed;
	private ImageIcon imgDoorOpened;
	
	
	public Spielbereich(int x,int y, Spieloberfläche spielUI) {
		
		this.spielUI = spielUI;
		
		imgDoorClosed = new ImageIcon("files/avatarImages/Horst Terarno.jpg");
		imgDoorOpened = new ImageIcon("files/avatarImages/Hasma Hasmada.jpg");
		
		
		lblDoor = new JLabel(imgDoorClosed);
		lblDoor.setSize(50,100);
		lblDoor.setLocation(200,50);
		lblDoor.addMouseListener(this);
		this.add(lblDoor);
		
		
		
		lblbackGround = new JLabel(new ImageIcon("files/gameImages/bg.jpg"));
		lblbackGround.setSize(800, 500);
		lblbackGround.setLocation(0,0);
		this.add(lblbackGround);
		
		
		
		this.setSize(800,500);
		this.setLocation(x,y);
		this.setBackground(null);
		this.setLayout(null);
		this.setVisible(true);
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent mevtE) {
		// TODO Mouse Enter fertig machen für alle Objekte.
		if (mevtE.getSource() == lblDoor) {
			spielUI.zeigeNachrichtInKonsole("Maus ist im in Türbereich.");
			lblDoor.setIcon(imgDoorOpened);
		}
		if (mevtE.getSource() == lblbackGround) {
			lblDoor.setIcon(imgDoorClosed);
		}
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
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
	

	
	
}
