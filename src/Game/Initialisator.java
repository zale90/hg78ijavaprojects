package Game;

import java.util.*;

public class Initialisator 
{
	
	public Initialisator()
	{

	}
	
	/*
	 * gibt die Avatare zurück, die irgendwo gespeichert werden müssen (evtl. launcher?)
	 * Werte müssen natürlich noch angepasst werden!
	 */
	public ArrayList<Avatar> gibAvatare()
	{
		ArrayList<Avatar> avList = new ArrayList<Avatar>();
		Bedürfnis[] bedList = new Bedürfnis[4];
		
		bedList[0] = new Bedürfnis(0, 50, 0, 100, 25);
		bedList[1] = new Bedürfnis(1, 50, 0, 100, 25);
		bedList[2] = new Bedürfnis(2, 50, 0, 100, 25);
		bedList[3] = new Bedürfnis(3, 50, 0, 100, 25);
		
		avList.add(new Avatar(1, "Horst Terano", "Akademiker (leicht)", bedList, 1500, "testAvatar.jpg"));
		avList.add(new Avatar(2, "Horst Terano", "Akademiker (leicht)", bedList, 1500, "BILD"));
		avList.add(new Avatar(3, "Horst Terano", "Akademiker (leicht)", bedList, 1500, "BILD"));
		
		return avList;
	}
}
