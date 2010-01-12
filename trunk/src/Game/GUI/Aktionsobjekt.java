package Game.GUI;

import javax.swing.*;
import java.awt.*;

import Game.*;

public class Aktionsobjekt extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String headertext;
	private Point headerpos;
	private ImageIcon aktiv, inaktiv;
	private Verzweigung menu;
	
	public Aktionsobjekt(String headertext, Point headerpos, String aktiv, String inaktiv, Verzweigung menu)
	{
		this.setHeadertext(headertext);
		this.setHeaderpos(headerpos);
		this.setAktiv(new ImageIcon(Optionen.ICON_PATH_GAME + aktiv));
		this.setInaktiv(new ImageIcon(Optionen.ICON_PATH_GAME + inaktiv));
		this.setMenu(menu);
		this.setIcon(this.inaktiv);
		this.setVisible(true);
	}

	public void setHeadertext(String headertext) {
		this.headertext = headertext;
	}

	public String getHeadertext() {
		return headertext;
	}

	public void setHeaderpos(Point headerpos) {
		this.headerpos = headerpos;
	}

	public Point getHeaderpos() {
		return headerpos;
	}

	public void setAktiv(ImageIcon aktiv) {
		this.aktiv = aktiv;
	}

	public ImageIcon getAktiv() {
		return aktiv;
	}

	public void setMenu(Verzweigung menu) {
		this.menu = menu;
	}

	public Verzweigung getMenu() {
		return menu;
	}

	public void setInaktiv(ImageIcon inaktiv) {
		this.inaktiv = inaktiv;
	}

	public ImageIcon getInaktiv() {
		return inaktiv;
	}
	public void setAktiv(boolean act)
	{
		if (act)
			setIcon(aktiv);
		else
			setIcon(inaktiv);
	}

}
