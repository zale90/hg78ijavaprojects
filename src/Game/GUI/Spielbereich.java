package Game.GUI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

import Game.Minigames.TicTacToe.*;
import Game.Information;

import Game.Optionen;


public class Spielbereich extends JPanel implements MouseListener
{

	/**
	 * Der Bereich in dem das Spiel visuell abläuft (Wohnung)
	 */
	private static final long serialVersionUID = -634199744790451275L;
	private JLabel lblbackGround;
	private Spieloberfläche spieloberfläche;
	private int aktivesObjekt;
	private ArrayList<Aktionsobjekt> aktionsObjekte;
	private MausLabel lblMaus;
	private Thread threadlblMaus;
	
	
	public Spielbereich(int x,int y, Spieloberfläche spielUI) {
		
		spieloberfläche = spielUI;
		aktionsObjekte = new ArrayList<Aktionsobjekt>();
		aktivesObjekt = -1; //-1 heißt das momentan kein Objekt aktiv ist; das heißt  man ist nicht mit der Maus auf einem und hat auch nicht auf eines geklickt
		Verzweigung.setSpielbereich(this); //die Verzweigung muss auf das Spielbereich-objekt zugreifen können
		
		// es gibt nur noch einen header, der einfach rumbewegt und mit anderen Strings ausgestattet wird
		lblMaus = new MausLabel(this);		
		this.add(lblMaus);
		threadlblMaus = new Thread(lblMaus);
		
		// erstellen der Aktionsobjekte mit Menus
		ArrayList<Aktion> türAktionen = new ArrayList<Aktion>();
		türAktionen.add(new Aktion("Kino besuchen", "(blue)Gehe ins Kino", "Du bist ins Kino gegangen", null, null));
        türAktionen.add(new Aktion("Freunde besuchen", "(blue)Besuche deine Freunde", "Du hast deine Freunde besucht.", null, null));
        türAktionen.get(1).setMinispiel(new TicTacToe());
        türAktionen.add(new Aktion("Theater besuchen", "(blue)Besuche ein Theater", "Du bist ins Theater gegangen.", null, null));
		ArrayList<Verzweigung> türVerzweigungen = new ArrayList<Verzweigung>();
		ArrayList<Aktion> sonstigesAktionen = new ArrayList<Aktion>();
		sonstigesAktionen.add(new Aktion("Park", "(blue)Gehe in den Park ", "Du bist in den Park gegangen", null, null));
		Verzweigung sonstiges = new Verzweigung("Sonstiges", "Sonstige Aktivitäten ausserhalb deiner Wohnung.", sonstigesAktionen, new ArrayList<Verzweigung>());
		türVerzweigungen.add(sonstiges);
		Verzweigung tuerMenu = new Verzweigung("Tür", "Hier kannst du Aktivitäten außerhalb deiner Wohnung auswählen", türAktionen, türVerzweigungen);
		
		Aktionsobjekt tuer = new Aktionsobjekt("Wohnung verlassen", new Point(687, 90), new Dimension(81, 340), "dooropen.png","doorclosed.png", tuerMenu);
		tuer.addMouseListener(this);
		this.add(tuer);
		aktionsObjekte.add(tuer);
		
		//Verzweigung von der Zeitung wird erstellt mit allen Aktionen.
		ArrayList<Aktion> zeitungsAktionen = new ArrayList<Aktion>();
		zeitungsAktionen.add(new Aktion("Lesen", "(blue) Du liest...", "Du hast die Zeitung durch gelesen.", null, null));	
		ArrayList<Verzweigung> zeitungsVerzweigungen = new ArrayList<Verzweigung>();
		Verzweigung zeitungsMenu = new Verzweigung("Zeitung", "Hier kannst du die Zeitung lesen, Kreuzworträtsel lösen und vieles mehr.", zeitungsAktionen, zeitungsVerzweigungen);
		
		Aktionsobjekt zeitung = new Aktionsobjekt("Zeitung lesen", new Point(100, 340), new Dimension(125, 64), "newspaperopen.png", "newspaperclosed.png", zeitungsMenu);
		zeitung.addMouseListener(this);
		this.add(zeitung);
		aktionsObjekte.add(zeitung);
		
		ArrayList<Aktion> gemueseAktionen = new ArrayList<Aktion>();
		Information gemueseInfos[] = new Information[3];
		Information gemueseInfo = new Information(Information.AENDERN_GELD, Information.ART_UM_WERT, -20);
		gemueseInfos[0] = gemueseInfo;
		gemueseInfo = new Information(Information.AENDERN_NAHRUNG, Information.ART_UM_WERT, 20);
		gemueseInfos[1] = gemueseInfo;
		gemueseInfo = new Information(Information.AENDERN_GESUNDHEIT, Information.ART_UM_WERT, 10);
		gemueseInfos[2] = gemueseInfo;
		gemueseAktionen.add(new Aktion("Hochwertig", "(blue)Kaufe hochwertiges Gemüse", "Du hast Qualitätsgemüse gekauft", gemueseInfos.clone(),null));
		gemueseInfo = new Information(Information.AENDERN_GELD, Information.ART_UM_WERT, -15);
		gemueseInfos[0] = gemueseInfo;
		gemueseInfo = new Information(Information.AENDERN_NAHRUNG, Information.ART_UM_WERT, 15);
		gemueseInfos[1] = gemueseInfo;
		gemueseInfo = new Information(Information.AENDERN_GESUNDHEIT, Information.ART_UM_WERT, 5);
		gemueseInfos[2] = gemueseInfo;
		gemueseAktionen.add(new Aktion("Mittelmäßig", "(blue)Kaufe mittelmäßiges Gemüse", "Du hast mittelmäßiges Gemüse gekauft", gemueseInfos.clone(),null));
		gemueseInfo = new Information(Information.AENDERN_GELD, Information.ART_UM_WERT, -10);
		gemueseInfos[0] = gemueseInfo;
		gemueseInfo = new Information(Information.AENDERN_NAHRUNG, Information.ART_UM_WERT, 5);
		gemueseInfos[1] = gemueseInfo;
		gemueseInfo = new Information(Information.AENDERN_GESUNDHEIT, Information.ART_UM_WERT, 2);
		gemueseInfos[2] = gemueseInfo;
		gemueseAktionen.add(new Aktion("Billig", "(blue)Kaufe billiges Gemüse", "Du hast billiges Gemüse gekauft", gemueseInfos.clone(),null));
		ArrayList<Verzweigung> kuehlschrankVerzweigung = new ArrayList<Verzweigung>();
		kuehlschrankVerzweigung.add(new Verzweigung("Gemüse", "Gemüse erhöht nicht nur deinen Nahrungsbalken, sondern auch deine Gesundheit. Allerdings kostet es dafür auch mehr als beispielsweise Fast Food.", gemueseAktionen, new ArrayList<Verzweigung>()));
		Verzweigung kuehlschrankMenu = new Verzweigung("Kühlschrank", "Hier kannst du Lebensmittel einkaufen.", new ArrayList<Aktion>(), kuehlschrankVerzweigung);
		
		Aktionsobjekt kuehlschrank = new Aktionsobjekt("Essen kaufen",new Point(522, 85), new Dimension(144, 241),"fridgeopen.png", "fridgeclosed.png", kuehlschrankMenu);
		kuehlschrank.addMouseListener(this);
		this.add(kuehlschrank);
		aktionsObjekte.add(kuehlschrank);
		
		// Hintergrund und Spielbereichgröße
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
				lblMaus.setVisible(false);
				aktivesObjekt = -1;
			}
			else
			{
				if(mouseClick.getSource() == aktionsObjekte.get(aktivesObjekt))
				{
					aktionsObjekte.get(aktivesObjekt).getMenu().setVisible(true);
					lblMaus.setVisible(false);
				}
				else	
				{
					aktionsObjekte.get(aktivesObjekt).setAktiv(false);
					for (int i = 0; i < aktionsObjekte.size(); i++)
					{
						if (aktionsObjekte.get(i) == mouseClick.getSource())
						{
							aktionsObjekte.get(i).setAktiv(true);
							lblMaus.setText(aktionsObjekte.get(i).getHeadertext());
							
							
							//showActionComponent(mouseClick.getPoint(), header, aktionsObjekte.get(i));
							try {
								    threadlblMaus = new Thread(lblMaus);
								    threadlblMaus.start();
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
					lblMaus.setText(aktionsObjekte.get(i).getHeadertext());
					
					
					//showActionComponent(mouseOver.getPoint(), header, aktionsObjekte.get(i));
					try {
					     	threadlblMaus = new Thread(lblMaus);
					     	threadlblMaus.start();
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
	
	//Zeigt unterhalb der Maus ein Panel mit möglichen Aktionen an.
	/**
	 * @param mEvt Ein Punkt für x,y Coords.
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
		int height = 30;
		
				
		
		//Sorgt dafür, dass die Komponente nicht außerhalb des Panels erstellt wird.
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
		spieloberfläche.aktion(akt);
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
			lblMaus.setVisible(false);
			aktivesObjekt = -1;
		}
	}
	
	public int getAktivesObjekt() {
		return aktivesObjekt;
	}
}
