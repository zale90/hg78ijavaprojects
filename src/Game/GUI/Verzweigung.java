package Game.GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import Game.*;

public class Verzweigung extends JPanel implements ActionListener { //wahrscheinlich müssen wir hier später MouseListener nehmen, aber egal
	
	private static final long serialVersionUID = 1L;
	private String name;
	private static Spielbereich spielber;
	private ArrayList<Verzweigung> verzweigungen;
	private ArrayList<Aktion> aktionen;
	private ArrayList<JButton> verzweigungsButtons;
	
	public Verzweigung(String name, ArrayList<Aktion> akt, ArrayList<Verzweigung> verz)
	{
		this.name = name;
		aktionen = akt;
		verzweigungen = verz;
		verzweigungsButtons = new ArrayList<JButton>();
		
		this.setSize(80, (aktionen.size() * 20) + (verzweigungen.size()) * 20);
	      this.setBackground(null);
	      this.setLayout(null);
		
	      
	     //wir sollten Buttons für Verzweigungen und für Aktionen wahrscheinlich noch unterschiedliche Farben geben
		for (int i = 0; i < aktionen.size(); i++)
		{
			aktionen.get(i).setLocation(0, i*20);
			aktionen.get(i).addActionListener(this);
			this.add(aktionen.get(i));
		}
		for (int i = 0; i < verzweigungen.size(); i++)
		{
			verzweigungsButtons.add(new JButton(verzweigungen.get(i).getName()));
			verzweigungsButtons.get(i).addActionListener(this);
			verzweigungsButtons.get(i).setSize(80, 20);
			verzweigungsButtons.get(i).setLocation(0, (aktionen.size()+i)*20);
			this.add(verzweigungsButtons.get(i));
		}
		this.setVisible(false);
	}
	public static void setSpielbereich(Spielbereich sp)
	{
		spielber = sp;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
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
					setVisible(false);
					spielber.showActionComponent(verzweigungsButtons.get(i).getLocation(), verzweigungen.get(i), this, 0);
					return;
				}
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
}
