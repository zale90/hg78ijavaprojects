package Game.GUI;

import javax.swing.*;
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
	private Spieloberfl�che spieloberfl�che;
	private int aktivesObjekt;
	private ArrayList<Aktionsobjekt> aktionsObjekte;
	private JLabel header;
	
	
	public Spielbereich(int x,int y, Spieloberfl�che spielUI) {
		
		spieloberfl�che = spielUI;
		aktionsObjekte = new ArrayList<Aktionsobjekt>();
		aktivesObjekt = -1; //-1 hei�t das momentan kein Objekt aktiv ist; das hei�t  man ist nicht mit der Maus auf einem und hat auch nicht auf eines geklickt
		Verzweigung.setSpielbereich(this); //die Verzweigung muss auf das Spielbereich-objekt zugreifen k�nnen
		
		// es gibt nur noch einen header, der einfach rumbewegt und mit anderen Strings ausgestattet
		header = new JLabel();
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setFont(Optionen.FONT_ACTION_HEADER);
		header.setSize(150,40);
		header.setVisible(false);
		this.add(header);
		
		// erstellen der Aktionsobjekte mit Menus
		ArrayList<Aktion> t�rAktionen = new ArrayList<Aktion>();
		t�rAktionen.add(new Aktion("Kino", "(blue)Gehe ins Kino", "Du bist ins Kino gegangen", null));
		ArrayList<Verzweigung> t�rVerzweigungen = new ArrayList<Verzweigung>();
		ArrayList<Aktion> sonstigesAktionen = new ArrayList<Aktion>();
		sonstigesAktionen.add(new Aktion("Park", "(blue)Gehe in den Park \n\n(red)Zeit: -1\n(green)Soziales: +10\nLuxus: +5", "Du bist in den Park gegangen", null));
		Verzweigung sonstiges = new Verzweigung("Sonstiges", "Sonstige Aktivit�ten ausserhalb deiner Wohnung.", sonstigesAktionen, new ArrayList<Verzweigung>());
		t�rVerzweigungen.add(sonstiges);
		Verzweigung tuerMenu = new Verzweigung("T�r", "Hier kannst du Aktivit�ten au�erhalb deiner Wohnung ausw�hlen", t�rAktionen, t�rVerzweigungen);
		
		Aktionsobjekt tuer = new Aktionsobjekt("Wohnung verlassen", "dooropen.png","doorclosed.png", tuerMenu);
		tuer.setSize(81, 340);
		tuer.setLocation(687, 90);
		tuer.addMouseListener(this);
		tuer.setOpaque(false);
		this.add(tuer);
		aktionsObjekte.add(tuer);
		
		ArrayList<Aktion> gemueseAktionen = new ArrayList<Aktion>();
		gemueseAktionen.add(new Aktion("Hochwertig", "(blue)Kaufe hochwertiges Gem�se", "Du hast Qualit�tsgem�se gekauft", null));
		gemueseAktionen.add(new Aktion("Mittelm��ig", "(blue)Kaufe mittelm��iges Gem�se", "Du hast mittelm��iges Gem�se gekauft", null));
		gemueseAktionen.add(new Aktion("Billig", "(blue)Kaufe billiges Gem�se", "Du hast billiges Gem�se gekauft", null));
		ArrayList<Verzweigung> kuehlschrankVerzweigung = new ArrayList<Verzweigung>();
		kuehlschrankVerzweigung.add(new Verzweigung("Gem�se", "Gem�se erh�ht nicht nur deinen Nahrungsbalken, sondern auch deine Gesundheit. Allerdings kostet es daf�r auch mehr als beispielsweise Fast Food.", gemueseAktionen, new ArrayList<Verzweigung>()));
		Verzweigung kuehlschrankMenu = new Verzweigung("Kuehlschrank", "Hier kannst du Lebensmittel einkaufen.", new ArrayList<Aktion>(), kuehlschrankVerzweigung);
		
		Aktionsobjekt kuehlschrank = new Aktionsobjekt("Essen kaufen","fridgeopen.png", "fridgeclosed.png", kuehlschrankMenu);
		kuehlschrank.setSize(144, 241);
		kuehlschrank.setLocation(522, 85);
		kuehlschrank.addMouseListener(this);
		kuehlschrank.setOpaque(false);
		this.add(kuehlschrank);
		aktionsObjekte.add(kuehlschrank);		
		
		// Hintergrund und Spielbereichgr��e
		lblbackGround = new JLabel(new ImageIcon(Optionen.ICON_PATH_GAME + "bg.png"));
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
		if (aktivesObjekt != -1)
		{
			aktionsObjekte.get(aktivesObjekt).getMenu().setVisible(false);
			if (mouseClick.getSource() == lblbackGround)
			{
				aktionsObjekte.get(aktivesObjekt).setAktiv(false);
				header.setVisible(false);
				aktivesObjekt = -1;
			}
			else
			{
				if(mouseClick.getSource() == aktionsObjekte.get(aktivesObjekt))
				{
					aktionsObjekte.get(aktivesObjekt).getMenu().setVisible(true);
				}
				else	
				{
					aktionsObjekte.get(aktivesObjekt).setAktiv(false);
					for (int i = 0; i < aktionsObjekte.size(); i++)
					{
						if (aktionsObjekte.get(i) == mouseClick.getSource())
						{
							aktionsObjekte.get(i).setAktiv(true);
							header.setText(aktionsObjekte.get(i).getHeader());
							showActionComponent(mouseClick.getPoint(), header, aktionsObjekte.get(i), 1);
							aktivesObjekt = i;
							return;
						}
					}
				}
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent mouseOver) {
		// T�r �ffnet sich bei MouseOver.
		
		if (mouseOver.getSource() == lblbackGround)
		{
			if (aktivesObjekt != -1 && !aktionsObjekte.get(aktivesObjekt).getMenu().isVisible())
			{
				aktionsObjekte.get(aktivesObjekt).setAktiv(false);
				header.setVisible(false);
				aktivesObjekt = -1;
			}
		}
		else if (aktivesObjekt == -1)
		{
			for (int i = 0; i < aktionsObjekte.size(); i++)
			{
				if(mouseOver.getSource() == aktionsObjekte.get(i))
				{
					header.setText(aktionsObjekte.get(i).getHeader());
					showActionComponent(mouseOver.getPoint(), header, aktionsObjekte.get(i), 1);
					aktionsObjekte.get(i).setAktiv(true);
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
		aktionsObjekte.get(aktivesObjekt).getMenu().setVisible(false);		
	}
	public void setzeAktiviert(boolean akt)
	{
		if (akt)
		{
			for (int i = 0; i < aktionsObjekte.size(); i++)
			{
				aktionsObjekte.get(i).addMouseListener(this);
			}
		}
		else
		{
			for (int i = 0; i < aktionsObjekte.size(); i++)
			{
				aktionsObjekte.get(i).removeMouseListener(this);
			}
		}
		if (aktivesObjekt != -1)
		{
			aktionsObjekte.get(aktivesObjekt).getMenu().setVisible(false);
			aktionsObjekte.get(aktivesObjekt).setAktiv(false);
			header.setVisible(false);
			aktivesObjekt = -1;
		}
	}
	
}
