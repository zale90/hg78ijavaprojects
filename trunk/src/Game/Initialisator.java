package Game;

import java.util.*;

public class Initialisator 
{
	
	public Initialisator()
	{

	}
	
	public ArrayList<Avatar> gibAvatare()
	{
		ArrayList<Avatar> liste = new ArrayList<Avatar>();
		ArrayList<Bed�rfnis> bliste = new ArrayList<Bed�rnis>();
		
		bliste.add(new Bed�rfnis(1, 50, 0, 100, 25));
		bliste.add(new Bed�rfnis(2, 50, 0, 100, 25));
		bliste.add(new Bed�rfnis(3, 50, 0, 100, 25));
		bliste.add(new Bed�rfnis(4, 50, 0, 100, 25));
		
		liste.add(new Avatar(1, "Horst Terano", "Akademiker (leicht)", bliste, 1500, "BILD"));
		liste.add(new Avatar(2, "Horst Terano", "Akademiker (leicht)", bliste, 1500, "BILD"));
		liste.add(new Avatar(3, "Horst Terano", "Akademiker (leicht)", bliste, 1500, "BILD"));
		
		return liste;
	}
}
