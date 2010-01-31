package Game.GUI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

import Game.Initialisator;

import Game.Optionen;

public class Spielbereich extends JPanel implements MouseListener {

	private static final long serialVersionUID = -634199744790451275L;

	private JLabel lblbackGround;
	private Spieloberfl‰che spieloberfl‰che;
	private int aktivesObjekt;
	private ArrayList<Aktionsobjekt> aktionsObjekte;
	private MausLabel lblMaus;
	private Thread threadlblMaus;

	public Spielbereich(int x, int y, Spieloberfl‰che spielUI) {

		spieloberfl‰che = spielUI;
		aktionsObjekte = new ArrayList<Aktionsobjekt>();
		aktivesObjekt = -1; // -1 heiﬂt das momentan kein Objekt aktiv ist; das
		// heiﬂt man ist nicht mit der Maus auf einem und
		// hat auch nicht auf eines geklickt
		Verzweigung.setSpielbereich(this);

		lblMaus = new MausLabel(this);
		this.add(lblMaus);
		threadlblMaus = new Thread(lblMaus);

		aktionsObjekte = Initialisator.getAktionsobjekte(this, spieloberfl‰che.getSpiel().getFamilienmitglieder());

		// Hintergrund und Spielbereichgrˆﬂe
		lblbackGround = new JLabel(new ImageIcon(Optionen.ICON_PATH_GAME
				+ "bg.png"));
		lblbackGround.setSize(800, 500);
		lblbackGround.setLocation(0, 0);
		lblbackGround.setOpaque(true);
		lblbackGround.addMouseListener(this);
		this.add(lblbackGround);

		this.setSize(800, 500);
		this.setLocation(x, y);
		this.setBackground(null);
		this.setLayout(null);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent mouseClick) {
		// if(aktivesObjekt != -1 && mouseClick.getSource() ==
		// aktionsObjekte.get(aktivesObjekt))
		// {
		// aktionsObjekte.get(aktivesObjekt).getMenu().setVisible(true);
		// }

		if (aktivesObjekt != -1) {
			aktionsObjekte.get(aktivesObjekt).getMenu().setVisible(false);
			if (mouseClick.getSource() == lblbackGround) {
				aktionsObjekte.get(aktivesObjekt).setAktiv(false);
				lblMaus.setVisible(false);
				aktivesObjekt = -1;
			} else {
				if (mouseClick.getSource() == aktionsObjekte.get(aktivesObjekt)) {
					aktionsObjekte.get(aktivesObjekt).getMenu()
							.setVisible(true);
					lblMaus.setVisible(false);
				} else {
					aktionsObjekte.get(aktivesObjekt).setAktiv(false);
					for (int i = 0; i < aktionsObjekte.size(); i++) {
						if (aktionsObjekte.get(i) == mouseClick.getSource()) {
							aktionsObjekte.get(i).setAktiv(true);
							lblMaus.setText(aktionsObjekte.get(i)
									.getHeadertext());

							// showActionComponent(mouseClick.getPoint(),
							// header, aktionsObjekte.get(i));
							try {
								threadlblMaus = new Thread(lblMaus);
								threadlblMaus.start();
							} catch (Exception e) {
							}

							// header.setLocation(aktionsObjekte.get(i).getHeaderpos());
							// header.setVisible(true);

							aktivesObjekt = i;
							return;
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent mouseOver) {
		if (mouseOver.getSource() == lblbackGround) {
			if (aktivesObjekt != -1
					&& !aktionsObjekte.get(aktivesObjekt).getMenu().isVisible()) {
				aktionsObjektAbwaehlen();
			}
		} else if (aktivesObjekt == -1) {
			for (int i = 0; i < aktionsObjekte.size(); i++) {
				if (mouseOver.getSource() == aktionsObjekte.get(i)) {
					lblMaus.setText(aktionsObjekte.get(i).getHeadertext());

					// showActionComponent(mouseOver.getPoint(), header,
					// aktionsObjekte.get(i));
					try {
						threadlblMaus = new Thread(lblMaus);
						threadlblMaus.start();
					} catch (Exception e) {

					}
					// header.setLocation(aktionsObjekte.get(i).getHeaderpos());
					// header.setVisible(true);

					aktionsObjekte.get(i).setAktiv(true);
					aktivesObjekt = i;
					return;
				}
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent mouseEx) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	// Zeigt unterhalb der Maus ein Panel mit mˆglichen Aktionen an.
	/**
	 * @param mEvt
	 *            Ein Punkt f¸r x,y Coords.
	 * @param actionCmp
	 *            Die Komponente, die angezeigt werden soll.
	 * @param lblSuper
	 *            Die Komponente, bei der das Actionpanel erstellt wird.
	 * @param actionCompType
	 *            0=ActionPanel 1=ActionLabel
	 */
	public void showActionComponent(Point mEvt, Component actionCmp,
			JComponent lblSuper) {
		// MouseClick pos bestimmen

		// pointerInfo = MouseInfo.getPointerInfo();
		// Point p = pointerInfo.getLocation();
		// int xpos = (int) p.getX();
		// int ypos = (int) p.getY() + 10;

		int xpos = (int) mEvt.getX();
		int ypos = (int) mEvt.getY();
		int height = 30;

		// Sorgt daf¸r, dass die Komponente nicht auﬂerhalb des Panels erstellt
		// wird.
		if ((xpos + lblSuper.getX() + actionCmp.getWidth() / 2) < 800) {
			actionCmp.setLocation(xpos + lblSuper.getX() - actionCmp.getWidth()
					/ 2, ypos + lblSuper.getY() - height);
		} else {
			actionCmp.setLocation(800 - actionCmp.getWidth(), ypos
					+ lblSuper.getY() - height);
		}
		actionCmp.setVisible(true);

	}

	public void aktionAusfuehren(Aktion akt) {
		aktionsObjektAbwaehlen();
		spieloberfl‰che.aktion(akt);
	}

	public void setzeAktiviert(boolean akt) {
		if (akt) {
			for (int i = 0; i < aktionsObjekte.size(); i++) {
				aktionsObjekte.get(i).addMouseListener(this);
			}
		} else {
			for (int i = 0; i < aktionsObjekte.size(); i++) {
				aktionsObjekte.get(i).removeMouseListener(this);
			}
		}
		if (aktivesObjekt != -1) {
			aktionsObjektAbwaehlen();
		}
	}

	public void aktionsObjektAbwaehlen() {
		if (aktivesObjekt != -1) {
			aktionsObjekte.get(aktivesObjekt).getMenu().setVisible(false);
			aktionsObjekte.get(aktivesObjekt).setAktiv(false);
			lblMaus.setVisible(false);
			aktivesObjekt = -1;
		}
	}

	public int getAktivesObjekt() {
		return aktivesObjekt;
	}

	public Spieloberfl‰che getSpieloberfl‰che() {
		return spieloberfl‰che;
	}
}
