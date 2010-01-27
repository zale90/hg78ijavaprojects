package Game.Minigames;

import Game.Spiel;
import java.awt.*;

public interface Minispiel {

	public void start(Spiel spiel);
	public void setLocation(Point poin);
	public Dimension getSize();

}
