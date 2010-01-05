package Game;

import java.util.*;

public class Initialisator 
{
	
	public Initialisator()
	{

	}
	
	/*
	 * gibt die Avatare zur�ck, die irgendwo gespeichert werden m�ssen (evtl. launcher?)
	 * Werte m�ssen nat�rlich noch angepasst werden!
	 */
	public ArrayList<Avatar> gibAvatare()
	{
		ArrayList<Avatar> avList = new ArrayList<Avatar>();
		Bed�rfnis[] bedList = new Bed�rfnis[4];
		
		bedList[0] = new Bed�rfnis(0, 50, 0, 100, 25);
		bedList[1] = new Bed�rfnis(1, 50, 0, 100, 25);
		bedList[2] = new Bed�rfnis(2, 50, 0, 100, 25);
		bedList[3] = new Bed�rfnis(3, 50, 0, 100, 25);
		
		avList.add(new Avatar(1, "Horst Terano", "Akademiker (leicht)", bedList, 1500, "testAvatar.jpg"));
		avList.add(new Avatar(2, "Horst Terano", "Akademiker (leicht)", bedList, 1500, "BILD"));
		avList.add(new Avatar(3, "Horst Terano", "Akademiker (leicht)", bedList, 1500, "BILD"));
		
		return avList;
	}
}
