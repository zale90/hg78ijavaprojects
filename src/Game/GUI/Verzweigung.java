package Game.GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import Game.*;

public class Verzweigung extends JPanel implements MouseListener, ActionListener { //wahrscheinlich müssen wir hier später MouseListener nehmen, aber egal
	
	private static final long serialVersionUID = 1L;
	private String name, beschreibung;
	private static Spielbereich spielber;
	private ArrayList<Verzweigung> verzweigungen;
	private ArrayList<Aktion> aktionen;
	private ArrayList<JButton> verzweigungsButtons;
	private JTextPane beschreibungsfeld;
	
	public Verzweigung(String name, String beschreibung, ArrayList<Aktion> akt, ArrayList<Verzweigung> verz)
	{
		this.name = name;
		this.beschreibung = beschreibung;
		aktionen = akt;
		verzweigungen = verz;
		verzweigungsButtons = new ArrayList<JButton>();
		
		// bei 170 höhe hat man für genau 5 Buttons platz
		this.setSize(300, 170);
		this.setLocation(170, 100);
	      this.setBackground(null);
	      this.setLayout(null);
		
	      
	     //wir sollten Buttons für Verzweigungen und für Aktionen wahrscheinlich noch unterschiedliche Farben geben
		for (int i = 0; i < aktionen.size(); i++)
		{
			aktionen.get(i).setLocation(10, (i*30) + 10);
			aktionen.get(i).addMouseListener(this);
			this.add(aktionen.get(i));
		}
		for (int i = 0; i < verzweigungen.size(); i++)
		{
			verzweigungsButtons.add(new JButton(verzweigungen.get(i).getName()));
			verzweigungsButtons.get(i).addActionListener(this);
			verzweigungsButtons.get(i).setSize(120, 30);
			if (aktionen.size() == 0)
				verzweigungsButtons.get(i).setLocation(10, ((aktionen.size()+i)*30) + 10);
			else
				verzweigungsButtons.get(i).setLocation(10, ((aktionen.size()+i)*30) + 10);
			this.add(verzweigungsButtons.get(i));
		}
		beschreibungsfeld = new JTextPane();
		beschreibungsfeld.setSize(150,150);
		beschreibungsfeld.setLocation(140, 10);
	    this.add(beschreibungsfeld);
	    beschreibungsfeld.setText(beschreibung);
	    
	    JScrollPane beschreibungsscroller = new JScrollPane(beschreibungsfeld);
	    beschreibungsscroller.setSize(beschreibungsfeld.getSize());
	    beschreibungsscroller.setLocation(beschreibungsfeld.getLocation());
	    this.add(beschreibungsscroller);
		
		this.setVisible(false);
	}
	public static void setSpielbereich(Spielbereich sp)
	{
		spielber = sp;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		for (int i = 0; i < verzweigungen.size(); i++)
		{
			if (verzweigungsButtons.get(i).equals(arg0.getSource()))
			{
				super.setVisible(false);
				verzweigungen.get(i).setVisible(true);
				return;
			}
		}	
		
	}
	public String getName()
	{
		return name;
	}
	
	//setVisible überschreiben, damit alle Untermenus dieser Verzweigung auch ausgeblendet werden
	public void setVisible(boolean aFlag)
	{
		super.setVisible(aFlag);
		if (aFlag)
			return;
		for (int i = 0; i < verzweigungen.size(); i++)
		{
			verzweigungen.get(i).setVisible(false);
		}
	}
	// isVisible überschreiben, damit Spielbereich erfahren kann, ob noch Untermenus dieses menus visible sind
	public boolean isVisible()
	{
		for (int i = 0; i < verzweigungen.size(); i++)
		{
			if (verzweigungen.get(i).isVisible())
			{
				return true;
			}
		}
		return super.isVisible();
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		for (int i = 0; i < aktionen.size(); i++)
		{
			if (aktionen.get(i).equals(arg0.getSource()))
			{
				spielber.aktionAusfuehren(aktionen.get(i));
				return;
			}
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		for (int i = 0; i < aktionen.size(); i++)
		{
			if (aktionen.get(i).equals(arg0.getSource()))
			{
				beschreibungsfeld.setText(aktionen.get(i).getBeschreibung());
				return;
			}
		}
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		beschreibungsfeld.setText(beschreibung);
		
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
