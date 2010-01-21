/**
 * Speichert wichtige Informationen.
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Optionen {
	
	public static final String NAME = "Ich bin arm - Holt mich hier raus!";
	public static final String VERSION = "0.2009.12.17";
    public static final String ICON_PATH_GAME = "files/gameImages/";
    public static final String ICON_PATH_AVATAR = "files/avatarImages/";
    public static final Font FONT_TITLE = new Font("Arial", Font.BOLD, 28);
    public static final Font FONT_BIGGER = new Font("Arial", Font.BOLD, 14);
    public static final Font FONT_BUTTON = new Font("Arial", Font.PLAIN, 20);
    public static final Font FONT_ACTION_HEADER = new Font("Arial", Font.BOLD, 14);
    public static final int NETWORK_PORT = 1337;
    public static final String NETWORK_ADDRESS = getNetworkAddress();
    
    private static final String NETWORK_CONFIG = "files/data/network.config";
    private static String getNetworkAddress() {
    	String address = "";
		try {
	    	BufferedReader test;
			test = new BufferedReader(new FileReader(NETWORK_CONFIG));
	    	address = test.readLine().trim();
		} catch (FileNotFoundException e) {
			System.out.println("Netzwerkkonfiguration konnte nicht gefunden!");
		} catch (IOException e) {
			System.out.println("Fehler:\n" + e.getMessage());
		}
		return address;
    }
    
}
