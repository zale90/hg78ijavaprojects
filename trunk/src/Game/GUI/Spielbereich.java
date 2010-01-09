package Game.GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

import Game.Optionen;


public class Spielbereich extends JPanel implements MouseListener, ActionListener
{

	/**
	 * Der Bereich in dem das Spiel visuell abläuft (Wohnung)
	 */
	private static final long serialVersionUID = -634199744790451275L;
	private JLabel lblbackGround;
	private JLabel lblDoor;
	private JLabel lblDoorHeader;
	//private Spieloberfläche spielUI;
	private ImageIcon imgDoorClosed;
	private ImageIcon imgDoorOpened;
	private JPanel pnlDoorActions;
	private JButton btnKino;
	//private PointerInfo pointerInfo;
	
	
	public Spielbereich(int x,int y, Spieloberfläche spielUI) {
		
		//this.spielUI = spielUI;
		imgDoorClosed = new ImageIcon("files/gameImages/doorclosed.png");
		imgDoorOpened = new ImageIcon("files/gameImages/null.png");
		
		lblDoorHeader = new JLabel("Wohnung verlassen", SwingConstants.CENTER);
		lblDoorHeader.setFont(Optionen.FONT_ACTION_HEADER);
		lblDoorHeader.setSize(150,40);
		lblDoorHeader.setVisible(false);
		this.add(lblDoorHeader);
		
		pnlDoorActions = new JPanel();
		pnlDoorActions.setSize(200, 40);
		pnlDoorActions.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		pnlDoorActions.setVisible(false);
		pnlDoorActions.setOpaque(true);
		this.add(pnlDoorActions);
		
		btnKino = new JButton("Kino");
		btnKino.setSize(80,25);
		btnKino.setLocation(5,5);
		pnlDoorActions.add(btnKino);
		btnKino.addActionListener(this);
		
		
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
			
			showActionComponent(mouseClick, pnlDoorActions, lblDoor, 0);			
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent mouseOver) {
		// Tür öffnet sich bei MouseOver.
		if (mouseOver.getSource() == lblDoor) {
			lblDoor.setIcon(imgDoorOpened);
			showActionComponent(mouseOver, lblDoorHeader, lblDoor, 1);
		}
		
	}

	@Override
	public void mouseExited(MouseEvent mouseEx) {
		//Alle Objekte werden zurück in den Standardzustand versetzt.
		//Tür wird geschlossen.
		if(mouseEx.getSource() == lblDoor) {
			lblDoor.setIcon(imgDoorClosed);
			lblDoorHeader.setVisible(false);		
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
	 * @param actionCmp Die Komponente, die angezeigt werden soll.
	 * @param lblSuper Das JLabel, bei dem das Actionpanel erstellt wird.
	 * @param actionCompType 0=ActionPanel 1=ActionLabel
	 */
	public void showActionComponent(MouseEvent mEvt, Component actionCmp, JLabel lblSuper, int actionCompType) {
		//MouseClick pos bestimmen
		
		//pointerInfo = MouseInfo.getPointerInfo();
		//Point p = pointerInfo.getLocation();
		//int xpos = (int) p.getX();
		//int ypos = (int) p.getY() + 10;	
		
		int xpos = mEvt.getX();
		int ypos = mEvt.getY();
		int height = 0;
		//spielUI.zeigeNachrichtInKonsole("Mausklick Location @ " + xpos + ", " + ypos + " (Türbereich).");
				
		switch (actionCompType) {
		case 0: height = -5;
				break;
		case 1: height = 40;
				break;
		}
		
		//Sorgt dafür, dass die Komponente nicht außerhalb des Panels erstellt wird.
		if ((xpos + lblSuper.getX() + actionCmp.getWidth() / 2) < 800) {
			actionCmp.setLocation(xpos + lblSuper.getX() - actionCmp.getWidth() / 2, ypos + lblSuper.getY() - height);
		} else {
			actionCmp.setLocation(800 - actionCmp.getWidth(), ypos + lblSuper.getY() - height);
		}
		actionCmp.setVisible(true);
				
	}



	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnKino) {
			pnlDoorActions.setVisible(false);
		}
		
	}
	
}
