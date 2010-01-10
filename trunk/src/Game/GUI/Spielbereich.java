package Game.GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

import Game.Optionen;


public class Spielbereich extends JPanel implements MouseListener
{

	/**
	 * Der Bereich in dem das Spiel visuell abl�uft (Wohnung)
	 */
	private static final long serialVersionUID = -634199744790451275L;
	private JLabel lblbackGround;
	private ImageIcon imgDoorClosed;
	private ImageIcon imgDoorOpened;
	private Spieloberfl�che spieloberfl�che;
	private int aktivesObjekt;
	//zu ArrayLists: Jedes Aktionsobjekt hat einen Header, ein Label f�r den Spielbereich, 
	//2 Bilder, eins f�r inaktiv und eins wenn die Maus drauf ist, und ein Untermenu, das aufploppt, wenn man draufklickt
	//In den ArrayLists gibts folgendes System: ein Index ist ein Aktionsobjekt. Das hei�t wenn auf ein Objekt geklickt
	//wird muss man nur durch eine Schleife herausfinden, an welchem index das in "aktionsObjekte" steht und dieser Index
	//ist dann derselbe, unter dem bspw in aktionsHeader der passende header zu finden ist.
	private ArrayList<JLabel> aktionsObjekte, aktionsHeader;
	private ArrayList<Verzweigung> aktionsMenus;
	private ArrayList<ImageIcon> bilderInaktiv, bilderAktiv; 
	
	
	public Spielbereich(int x,int y, Spieloberfl�che spielUI) {
		
		spieloberfl�che = spielUI;
		
		bilderInaktiv = new ArrayList<ImageIcon>();
		bilderAktiv = new ArrayList<ImageIcon>();
		aktionsObjekte = new ArrayList<JLabel>();
		aktionsHeader = new ArrayList<JLabel>();
		aktionsMenus = new ArrayList<Verzweigung>();
		aktivesObjekt = -1; //-1 hei�t das momentan kein Objekt aktiv ist; das hei�t  man ist nicht mit der Maus auf einem und hat auch nicht auf eines geklickt
		Verzweigung.setSpielbereich(this); //die Verzweigung muss auf das Spielbereich-objekt zugreifen k�nnen
		
		//Folgender Block ist nur zum test da; ich erstelle damit das Menu, was aufgeht, wenn man auch die T�r klickt, mitsamt Untermenu
		// TEST
		ArrayList<Aktion> aktVer1 = new ArrayList<Aktion>();
		aktVer1.add(new Aktion("Kino", "(blue)Gehe ins Kino", "Du bist ins Kino gegangen", null, null));
		ArrayList<Verzweigung> verVer1 = new ArrayList<Verzweigung>();
		ArrayList<Aktion> aktVer11 = new ArrayList<Aktion>();
		aktVer11.add(new Aktion("Park", "(blue)Gehe in den Park \n\n(red)Zeit: -1\n(green)Soziales: +10\nLuxus: +5", "Du bist in den Park gegangen", null,null));
		Verzweigung ver11 = new Verzweigung("Sonstiges", "Sonstige Aktivit�ten ausserhalb deiner Wohnung.", aktVer11, new ArrayList<Verzweigung>());
		this.add(ver11);
		verVer1.add(ver11);
		Verzweigung ver1 = new Verzweigung("Door", "Hier kannst du Aktivit�ten au�erhalb deiner Wohnung ausw�hlen", aktVer1, verVer1);
		this.add(ver1);
		aktionsMenus.add(ver1);
		// TEST
		
		// Schonmal erster Menupunkt f�r K�hlschrank; m�ssen wir sp�ter mal schauen ob wir das alles hier initialisieren oder in Initialisator
		ArrayList<Aktion> gemueseAktionen = new ArrayList<Aktion>();
		gemueseAktionen.add(new Aktion("Hochwertig", "(blue)Kaufe hochwertiges Gem�se", "Du hast Qualit�tsgem�se gekauft", null, null));
		gemueseAktionen.add(new Aktion("Mittelmae�ig", "(blue)Kaufe mittelmae�iges Gem�se", "Du hast mittelmae�iges Gem�se gekauft", null, null));
		gemueseAktionen.add(new Aktion("Billig", "(blue)Kaufe billiges Gem�se", "Du hast billiges Gem�se gekauft", null, null));
		ArrayList<Verzweigung> kuehlschrankVerzweigung = new ArrayList<Verzweigung>();
		kuehlschrankVerzweigung.add(new Verzweigung("Gemuese", "Gem�se erh�ht nicht nur deinen Nahrungsbalken, sondern auch deine Gesundheit. Allerdings kostet es daf�r auch mehr als beispielsweise Fast Food.", gemueseAktionen, new ArrayList<Verzweigung>()));
		this.add(kuehlschrankVerzweigung.get(0));
		Verzweigung ver12 = new Verzweigung("Kuehlschrank", "Hier kannst du Lebensmittel einkaufen.", new ArrayList<Aktion>(), kuehlschrankVerzweigung);
		this.add(ver12);
		aktionsMenus.add(ver12);
		
		bilderInaktiv.add(new ImageIcon("files/gameImages/doorclosed.png"));
		bilderInaktiv.add(new ImageIcon("files/gameImages/fridgeclosed.png"));
		bilderAktiv.add(new ImageIcon("files/gameImages/null.png"));
		bilderAktiv.add(new ImageIcon("files/gameImages/fridgeopen.png"));
		
		JLabel lblDoorHeader = new JLabel("Wohnung verlassen", SwingConstants.CENTER);
		lblDoorHeader.setFont(Optionen.FONT_ACTION_HEADER);
		lblDoorHeader.setSize(150,40);
		lblDoorHeader.setVisible(false);
		this.add(lblDoorHeader);
		aktionsHeader.add(lblDoorHeader);
		
		JLabel lblFridgeHeader = new JLabel("Essen kaufen", SwingConstants.CENTER);
		lblFridgeHeader.setFont(Optionen.FONT_ACTION_HEADER);
		lblFridgeHeader.setSize(150,40);
		lblFridgeHeader.setVisible(false);
		this.add(lblFridgeHeader);
		
		JLabel lblDoor = new JLabel(bilderInaktiv.get(0));
		lblDoor.setSize(81, 340);
		lblDoor.setLocation(687, 90);
		lblDoor.addMouseListener(this);
		lblDoor.setOpaque(false);
		this.add(lblDoor);
		aktionsObjekte.add(lblDoor);
		
		aktionsHeader.add(lblFridgeHeader);
		
		JLabel lblFridge = new JLabel(bilderInaktiv.get(1));
		lblFridge.setSize(99, 237);
		lblFridge.setLocation(566, 86);
		lblFridge.addMouseListener(this);
		lblFridge.setOpaque(false);
		this.add(lblFridge);
		aktionsObjekte.add(lblFridge);
		
		
		lblbackGround = new JLabel(new ImageIcon("files/gameImages/bg.png"));
		lblbackGround.setSize(800, 500);
		lblbackGround.setLocation(0,0);
		lblbackGround.setOpaque(true);
		lblbackGround.addMouseListener(this);
		this.add(lblbackGround);
		
		
		
		this.setSize(800,500);
		this.setLocation(x,y);
		this.setBackground(null);
		this.setLayout(null);
		this.setVisible(true);
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent mouseClick) {
		//blendet Untermenus aus, die evtl noch angezeigt sind
		if (aktivesObjekt != -1)
		{
			aktionsMenus.get(aktivesObjekt).setVisible(false);
			if (aktionsObjekte.get(aktivesObjekt) != mouseClick.getSource())
			{
				aktionsObjekte.get(aktivesObjekt).setIcon(bilderInaktiv.get(aktivesObjekt));
				aktionsHeader.get(aktivesObjekt).setVisible(false);
				for (int i = 0; i < aktionsObjekte.size(); i++)
				{
					if (aktionsObjekte.get(i) == mouseClick.getSource())
					{
						aktionsObjekte.get(i).setIcon(bilderAktiv.get(i));
						showActionComponent(mouseClick.getPoint(), aktionsHeader.get(i), aktionsObjekte.get(i), 1);
						aktivesObjekt = i;
						return;
					}
				}
			}
		}
		
		if (mouseClick.getSource() == lblbackGround)
		{
			if (aktivesObjekt != -1)
			{
				aktionsMenus.get(aktivesObjekt).setVisible(false);
				aktionsObjekte.get(aktivesObjekt).setIcon(bilderInaktiv.get(aktivesObjekt));
				aktionsHeader.get(aktivesObjekt).setVisible(false);
				aktivesObjekt = -1;
			}
		}
		
		//Zeigt ein Door-Actionpanel an. 
		if(aktivesObjekt != -1 && mouseClick.getSource() == aktionsObjekte.get(aktivesObjekt))
		{
			aktionsMenus.get(aktivesObjekt).setVisible(true);
			return;
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent mouseOver) {
		// T�r �ffnet sich bei MouseOver.
		
		if (mouseOver.getSource() == lblbackGround)
		{
			if (aktivesObjekt != -1 && !aktionsMenus.get(aktivesObjekt).isVisible())
			{
				aktionsObjekte.get(aktivesObjekt).setIcon(bilderInaktiv.get(aktivesObjekt));
				aktionsHeader.get(aktivesObjekt).setVisible(false);
				aktivesObjekt = -1;
			}
		}
		else if (aktivesObjekt == -1)
		{
			for (int i = 0; i < aktionsObjekte.size(); i++)
			{
				if(mouseOver.getSource() == aktionsObjekte.get(i))
				{
					showActionComponent(mouseOver.getPoint(), aktionsHeader.get(i), aktionsObjekte.get(i), 1);
					aktionsObjekte.get(i).setIcon(bilderAktiv.get(i));
					aktivesObjekt = i;
					return;
				}
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent mouseEx) {
		//T�r schlie�en u.s.w. in mouse entered gepackt, weil sonst die T�r zu ging wenn man auf das ActionPanel ging
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	//Zeigt unterhalb der Maus ein Panel mit m�glichen Aktionen an.
	/**
	 * @param mEvt Ein Punkt f�r x,y Coords.
	 * @param actionCmp Die Komponente, die angezeigt werden soll.
	 * @param lblSuper Die Komponente, bei der das Actionpanel erstellt wird.
	 * @param actionCompType 0=ActionPanel 1=ActionLabel
	 */
	public void showActionComponent(Point mEvt, Component actionCmp, JComponent lblSuper, int actionCompType) {
		//MouseClick pos bestimmen
		
		//pointerInfo = MouseInfo.getPointerInfo();
		//Point p = pointerInfo.getLocation();
		//int xpos = (int) p.getX();
		//int ypos = (int) p.getY() + 10;	
		
		int xpos = (int)mEvt.getX();
		int ypos = (int)mEvt.getY();
		int height = 0;
		//spielUI.zeigeNachrichtInKonsole("Mausklick Location @ " + xpos + ", " + ypos + " (T�rbereich).");
				
		switch (actionCompType) {
		case 0: height = -5;
				break;
		case 1: height = 40;
				break;
		}
		
		//Sorgt daf�r, dass die Komponente nicht au�erhalb des Panels erstellt wird.
		if ((xpos + lblSuper.getX() + actionCmp.getWidth() / 2) < 800) {
			actionCmp.setLocation(xpos + lblSuper.getX() - actionCmp.getWidth() / 2, ypos + lblSuper.getY() - height);
		} else {
			actionCmp.setLocation(800 - actionCmp.getWidth(), ypos + lblSuper.getY() - height);
		}
		actionCmp.setVisible(true);
				
	}
	public void aktionAusfuehren(Aktion akt)
	{
		spieloberfl�che.aktion(akt);
		aktionsMenus.get(aktivesObjekt).setVisible(false);		
	}
	
}
