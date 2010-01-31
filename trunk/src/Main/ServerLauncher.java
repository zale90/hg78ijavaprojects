package Main;

import Server.ScoreServer;

public class ServerLauncher {

	/**
	 * Startet den Highscore-Server!
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length > 0) {
			if(args[0].equals("fullscreen=1")) {
				new ScoreServer(true);
			}
		} else {
			new ScoreServer(false);
		}
	}

}
