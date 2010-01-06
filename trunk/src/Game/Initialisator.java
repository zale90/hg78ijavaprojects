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
	public static ArrayList<Avatar> gibAvatare()
	{
		ArrayList<Avatar> avList = new ArrayList<Avatar>();
		Bed�rfnis[] bedList = new Bed�rfnis[4];
		
		bedList[0] = new Bed�rfnis(0, 50, 0, 100, 25);
		bedList[1] = new Bed�rfnis(1, 50, 0, 100, 25);
		bedList[2] = new Bed�rfnis(2, 50, 0, 100, 25);
		bedList[3] = new Bed�rfnis(3, 50, 0, 100, 25);
//		bedList[4] = new Bed�rfnis(4, 50, 0, 100, 25);
		
		avList.add(new Avatar(1, "Horst Terarno", "Akademiker (leicht)", bedList, 1500, 500, 5));
		avList.add(new Avatar(2, "Arn Terhorsto", "Akademiker (leicht)", bedList, 1000, 200, 2));
		avList.add(new Avatar(3, "Ter Horstarno", "Akademiker (leicht)", bedList, 750, 300, 3));
		
		return avList;
	}
}
