package Game.GUI;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Verzweigung extends JPanel implements MouseListener { //wahrscheinlich müssen wir hier später MouseListener nehmen, aber egal
	
	private static final long serialVersionUID = 1L;
	private String name, beschreibung;
	private static Spielbereich spielber;
	private ArrayList<Verzweigung> verzweigungen;
	private ArrayList<Aktion> aktionen;
	private ArrayList<JLabel> verzweigungsButtons;
	private JTextPane beschreibungsfeld;
	
	public Verzweigung(String name, String beschreibung, ArrayList<Aktion> akt, ArrayList<Verzweigung> verz)
	{
		this.name = name;
		this.beschreibung = beschreibung;
		aktionen = akt;
		verzweigungen = verz;
		verzweigungsButtons = new ArrayList<JLabel>();
		
		// bei 170 höhe hat man für genau 5 Buttons platz
		this.setSize(300, 170);
		this.setLocation(280, 150);
	      this.setBackground(new Color(153,134,124));
	      this.setLayout(null);
	      this.setBorder(BorderFactory.createEtchedBorder());
	      
	     //wir sollten Buttons für Verzweigungen und für Aktionen wahrscheinlich noch unterschiedliche Farben geben
		for (int i = 0; i < aktionen.size(); i++)
		{
			aktionen.get(i).setLocation(10, (i*30) + 10);
			aktionen.get(i).addMouseListener(this);
			this.add(aktionen.get(i));
		}
		for (int i = 0; i < verzweigungen.size(); i++)
		{
			verzweigungsButtons.add(new JLabel(verzweigungen.get(i).getName() + " >>"));
			verzweigungsButtons.get(i).addMouseListener(this);
			verzweigungsButtons.get(i).setSize(120, 30);
			verzweigungsButtons.get(i).setHorizontalAlignment(0);
			verzweigungsButtons.get(i).setOpaque(true);
			if (aktionen.size() == 0)
				verzweigungsButtons.get(i).setLocation(10, ((aktionen.size()+i)*30) + 10);
			else
				verzweigungsButtons.get(i).setLocation(10, ((aktionen.size()+i)*30) + 10);
			this.add(verzweigungsButtons.get(i));
		}
		beschreibungsfeld = new JTextPane();
		beschreibungsfeld.setSize(150,150);
		beschreibungsfeld.setLocation(140, 10);
		beschreibungsfeld.setEditable(false);
		beschreibungsfeld.setBackground(new Color(220, 220, 220));
		beschreibungsfeld.setOpaque(true);
	    this.add(beschreibungsfeld);
	    Style style = beschreibungsfeld.addStyle("red", null);
        StyleConstants.setForeground(style, Color.red);
        StyleConstants.setBold(style, true);
        style = beschreibungsfeld.addStyle("green", null);
        StyleConstants.setForeground(style, new Color(0,150,0));
        StyleConstants.setBold(style, true);
        style = beschreibungsfeld.addStyle("black", null);
        StyleConstants.setForeground(style, Color.black);
        StyleConstants.setBold(style, true);
        style = beschreibungsfeld.addStyle("blue", null);
        StyleConstants.setForeground(style, Color.blue);
        StyleConstants.setBold(style, true);
	    beschreibungFuellen("(black)" + beschreibung);
	    
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
		if (arg0.getSource().getClass().getName().split("\\.")[2].equals("Aktion"))
		{
			for (int i = 0; i < aktionen.size(); i++)
			{
				if (aktionen.get(i).equals(arg0.getSource()))
				{
					spielber.aktionAusfuehren(aktionen.get(i));
					return;
				}
			}
		}
		else
		{
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
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		if (arg0.getSource().getClass().getName().split("\\.")[2].equals("Aktion"))
		{
			for (int i = 0; i < aktionen.size(); i++)
			{
				if (aktionen.get(i).equals(arg0.getSource()))
				{
					beschreibungFuellen(aktionen.get(i).getBeschreibung());
					return;
				}
			}
		}
		else
		{
			for (int i = 0; i < verzweigungen.size(); i++)
			{
				if (verzweigungsButtons.get(i).equals(arg0.getSource()))
				{
					beschreibungFuellen("(blue)" + verzweigungen.get(i).getBeschreibung());
					return;
				}
			}
		}
	}
	public String getBeschreibung()
	{
		return beschreibung;
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		beschreibungFuellen("(black)" + beschreibung);
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void beschreibungFuellen(String str)
	{
		beschreibungsfeld.setText("");
		String[] beschr = str.split("\\(");
		StyledDocument sd = beschreibungsfeld.getStyledDocument();
		for (int i = 0;i < beschr.length; i++)
		{
			String[] besch = beschr[i].split("\\)");
			try
	        {
	            sd.insertString(sd.getLength(), besch[1], beschreibungsfeld.getStyle(besch[0]));
	        }
	        catch (Exception e)
	        {
	        }
		}
		beschreibungsfeld.setCaretPosition(0);
	}
}
