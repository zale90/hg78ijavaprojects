package Game.GUI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


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
	private JPanel pnlDoorActions;
	//private PointerInfo pointerInfo;
	private TextComponent txtDoorHeader;
	
	
	public Spielbereich(int x,int y, Spieloberfläche spielUI) {
		
		//this.spielUI = spielUI;
		imgDoorClosed = new ImageIcon("files/gameImages/doorclosed.png");
		imgDoorOpened = new ImageIcon("files/gameImages/null.png");
		
		//txtDoorHeader = new TextComponent("Wohnung verlassen");
		
		pnlDoorActions = new JPanel();
		pnlDoorActions.setSize(200, 40);
		pnlDoorActions.setBackground(Color.BLACK);
		pnlDoorActions.setVisible(false);
		this.add(pnlDoorActions);
		
		
		lblDoor = new JLabel(imgDoorClosed);
		lblDoor.setSize(81, 340);
		lblDoor.setLocation(687, 90);
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
	public void mouseClicked(MouseEvent mouseClick) {
		//Zeigt ein Door-Actionpanel an. 
		if (mouseClick.getSource() == lblDoor) {
			
			showActionPanel(mouseClick, pnlDoorActions, lblDoor);			
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent mouseOver) {
		// Tür öffnet sich bei MouseOver.
		if (mouseOver.getSource() == lblDoor) {
			lblDoor.setIcon(imgDoorOpened);
		}

		
	}

	@Override
	public void mouseExited(MouseEvent mouseEx) {
		//Alle Objekte werden zurück in den Standardzustand versetzt.
		//Tür wird geschlossen.
		if(mouseEx.getSource() == lblDoor) {
			lblDoor.setIcon(imgDoorClosed);
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	//Zeigt unterhalb der Maus ein Panel mit möglichen Aktionen an.
	/**
	 * @param mEvt Ein Mouse Event für x,y Coords der Maus.
	 * @param actionPnl Das Panel, das angezeigt werden soll.
	 * @param lblSuper Das JLabel, bei dem das Actionpanel erstellt wird.
	 */
	public void showActionPanel(MouseEvent mEvt, Component actionPnl, JLabel lblSuper) {
		//MouseClick pos bestimmen
		
		//pointerInfo = MouseInfo.getPointerInfo();
		//Point p = pointerInfo.getLocation();
		//int xpos = (int) p.getX();
		//int ypos = (int) p.getY() + 10;	
		
		int xpos = mEvt.getX();
		int ypos = mEvt.getY();
		//spielUI.zeigeNachrichtInKonsole("Mausklick Location @ " + xpos + ", " + ypos + " (Türbereich).");
				
		//Sorgt dafür, dass das Actionpanel nicht außerhalb des Panels erstellt wird.
		if ((xpos + lblSuper.getX() + actionPnl.getWidth() / 2) < 800) {
			actionPnl.setLocation(xpos + lblSuper.getX() - actionPnl.getWidth() / 2, ypos + lblSuper.getY() + 5);
		} else {
			actionPnl.setLocation(800 - actionPnl.getWidth(), ypos + lblSuper.getY() + 5);
		}
		actionPnl.setVisible(true);
		

		
	}
	
}
