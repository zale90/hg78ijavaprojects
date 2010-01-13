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
	private ActionHeader header;
	private Thread threadHeader;
	
	
	public Spielbereich(int x,int y, Spieloberfl�che spielUI) {
		
		spieloberfl�che = spielUI;
		aktionsObjekte = new ArrayList<Aktionsobjekt>();
		aktivesObjekt = -1; //-1 hei�t das momentan kein Objekt aktiv ist; das hei�t  man ist nicht mit der Maus auf einem und hat auch nicht auf eines geklickt
		Verzweigung.setSpielbereich(this); //die Verzweigung muss auf das Spielbereich-objekt zugreifen k�nnen
		
		// es gibt nur noch einen header, der einfach rumbewegt und mit anderen Strings ausgestattet wird
		header = new ActionHeader(this);		
		this.add(header);
		threadHeader = new Thread(header);
		
		// erstellen der Aktionsobjekte mit Menus
		ArrayList<Aktion> t�rAktionen = new ArrayList<Aktion>();
		t�rAktionen.add(new Aktion("Kino besuchen", "(blue)Gehe ins Kino", "Du bist ins Kino gegangen", null));
        t�rAktionen.add(new Aktion("Freunde besuchen", "(blue)Besuche deine Freunde\n\n(red)Zeit: -1\n(green)Soziales: +10", "Du hast deine Freunde besucht.", null));
        t�rAktionen.add(new Aktion("Theater besuchen", "(blue)Besuche ein Theater\n\n(red)Geld: -50\nZeit: -1\n(green)Soziales: +10\nLuxus: +15", "Du bist ins Theater gegangen.", null));
		ArrayList<Verzweigung> t�rVerzweigungen = new ArrayList<Verzweigung>();
		ArrayList<Aktion> sonstigesAktionen = new ArrayList<Aktion>();
		sonstigesAktionen.add(new Aktion("Park", "(blue)Gehe in den Park \n\n(red)Zeit: -1\n(green)Soziales: +10\nLuxus: +5", "Du bist in den Park gegangen", null));
		Verzweigung sonstiges = new Verzweigung("Sonstiges", "Sonstige Aktivit�ten ausserhalb deiner Wohnung.", sonstigesAktionen, new ArrayList<Verzweigung>());
		t�rVerzweigungen.add(sonstiges);
		Verzweigung tuerMenu = new Verzweigung("T�r", "Hier kannst du Aktivit�ten au�erhalb deiner Wohnung ausw�hlen", t�rAktionen, t�rVerzweigungen);
		
		Aktionsobjekt tuer = new Aktionsobjekt("Wohnung verlassen", new Point(687, 90), new Dimension(81, 340), "dooropen.png","doorclosed.png", tuerMenu);
		tuer.addMouseListener(this);
		this.add(tuer);
		aktionsObjekte.add(tuer);
		
		ArrayList<Aktion> gemueseAktionen = new ArrayList<Aktion>();
		gemueseAktionen.add(new Aktion("Hochwertig", "(blue)Kaufe hochwertiges Gem�se", "Du hast Qualit�tsgem�se gekauft", null));
		gemueseAktionen.add(new Aktion("Mittelm��ig", "(blue)Kaufe mittelm��iges Gem�se", "Du hast mittelm��iges Gem�se gekauft", null));
		gemueseAktionen.add(new Aktion("Billig", "(blue)Kaufe billiges Gem�se", "Du hast billiges Gem�se gekauft", null));
		ArrayList<Verzweigung> kuehlschrankVerzweigung = new ArrayList<Verzweigung>();
		kuehlschrankVerzweigung.add(new Verzweigung("Gem�se", "Gem�se erh�ht nicht nur deinen Nahrungsbalken, sondern auch deine Gesundheit. Allerdings kostet es daf�r auch mehr als beispielsweise Fast Food.", gemueseAktionen, new ArrayList<Verzweigung>()));
		Verzweigung kuehlschrankMenu = new Verzweigung("K�hlschrank", "Hier kannst du Lebensmittel einkaufen.", new ArrayList<Aktion>(), kuehlschrankVerzweigung);
		
		Aktionsobjekt kuehlschrank = new Aktionsobjekt("Essen kaufen",new Point(522, 85), new Dimension(144, 241),"fridgeopen.png", "fridgeclosed.png", kuehlschrankMenu);
		kuehlschrank.addMouseListener(this);
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
//		if(aktivesObjekt != -1 && mouseClick.getSource() == aktionsObjekte.get(aktivesObjekt))
//		{
//			aktionsObjekte.get(aktivesObjekt).getMenu().setVisible(true);
//		}
		
		/*
		 * unterhalb der alte Code, mit dem Untermenus geschlossen wurden, sobald man auf Hintergrund
		 * oder andere aktionsObjekte klickte. Bitte drin lassen, damit wirs u.U. schnell wieder haben.
		 */
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
					header.setVisible(false);
				}
				else	
				{
					aktionsObjekte.get(aktivesObjekt).setAktiv(false);
					for (int i = 0; i < aktionsObjekte.size(); i++)
					{
						if (aktionsObjekte.get(i) == mouseClick.getSource())
						{
							aktionsObjekte.get(i).setAktiv(true);
							header.setText(aktionsObjekte.get(i).getHeadertext());
							
							
							showActionComponent(mouseClick.getPoint(), header, aktionsObjekte.get(i));
							try {
									threadHeader.start();
							        } catch (Exception e) {							        	
							        }
							
//							header.setLocation(aktionsObjekte.get(i).getHeaderpos());
//							header.setVisible(true);
							
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
		if (mouseOver.getSource() == lblbackGround)
		{
			if (aktivesObjekt != -1 && !aktionsObjekte.get(aktivesObjekt).getMenu().isVisible())
			{
				aktionsObjektAbwaehlen();
			}
		}
		else if (aktivesObjekt == -1)
		{
			for (int i = 0; i < aktionsObjekte.size(); i++)
			{
				if(mouseOver.getSource() == aktionsObjekte.get(i))
				{
					header.setText(aktionsObjekte.get(i).getHeadertext());
					
					
					showActionComponent(mouseOver.getPoint(), header, aktionsObjekte.get(i));
					try {
							threadHeader.start();
					        } catch (Exception e) {
					        	
					        }
//					header.setLocation(aktionsObjekte.get(i).getHeaderpos());
//					header.setVisible(true);
					
					aktionsObjekte.get(i).setAktiv(true);
					aktivesObjekt = i;
					return;
				}
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent mouseEx) {
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
	public void showActionComponent(Point mEvt, Component actionCmp, JComponent lblSuper) {
		//MouseClick pos bestimmen
		
		//pointerInfo = MouseInfo.getPointerInfo();
		//Point p = pointerInfo.getLocation();
		//int xpos = (int) p.getX();
		//int ypos = (int) p.getY() + 10;	
		
		int xpos = (int)mEvt.getX();
		int ypos = (int)mEvt.getY();
		int height = 0;
		
				
		
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
		aktionsObjektAbwaehlen();	
		spieloberfl�che.aktion(akt);
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
			aktionsObjektAbwaehlen();
		}
	}
	public void aktionsObjektAbwaehlen()
	{
		if (aktivesObjekt != -1)
		{
			aktionsObjekte.get(aktivesObjekt).getMenu().setVisible(false);
			aktionsObjekte.get(aktivesObjekt).setAktiv(false);
			header.setVisible(false);
			aktivesObjekt = -1;
		}
	}
	
	public int getAktivesObjekt() {
		return aktivesObjekt;
	}
}
