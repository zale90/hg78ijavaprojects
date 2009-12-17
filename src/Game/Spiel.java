/**
 * Stellt das eigentliche Spiel da, in dem alle
 * wesentlichen Abläufe geschehen und verarbeitet
 * werden.
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

public class Spiel {
	
	private Bedürfnis[] bedürfnisse;
	private int kontostand;
	private int avatarNr;
	
	/**
	 * Ein neues Spielt wird anhand der Informationen
	 * eines Avatars erstellt und die Spieloberfläche
	 * wird angezeigt.
	 * 
	 * @param avatar
	 */
	public Spiel(Avatar avatar) {
		bedürfnisse = avatar.getBedürfnisse();
		kontostand = avatar.getKontostand();
		avatarNr = avatar.getAvatarNummer();
		
	}

}
