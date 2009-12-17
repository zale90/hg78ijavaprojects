/**
 * Stellt das eigentliche Spiel da, in dem alle
 * wesentlichen Abl�ufe geschehen und verarbeitet
 * werden.
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

public class Spiel {
	
	private Bed�rfnis[] bed�rfnisse;
	private int kontostand;
	private int avatarNr;
	
	/**
	 * Ein neues Spielt wird anhand der Informationen
	 * eines Avatars erstellt und die Spieloberfl�che
	 * wird angezeigt.
	 * 
	 * @param avatar
	 */
	public Spiel(Avatar avatar) {
		bed�rfnisse = avatar.getBed�rfnisse();
		kontostand = avatar.getKontostand();
		avatarNr = avatar.getAvatarNummer();
		
	}

}
