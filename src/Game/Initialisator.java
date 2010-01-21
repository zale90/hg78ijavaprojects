package Game;

import java.awt.Dimension;
import java.awt.Point;
import java.util.*;

import Game.GUI.*;
import Game.Minigames.TicTacToe.TicTacToe;

public class Initialisator 
{
	/**
	 * Liefert die vordefinierten Avatare zur�ck.
	 * 
	 * @return ArrayList mit Avataren.
	 */
	public static ArrayList<Avatar> gibAvatare()
	{
		ArrayList<Avatar> avList = new ArrayList<Avatar>();
		Bed�rfnis[] bedList;
		
		// Horst Terano
		bedList = new Bed�rfnis[4];		
		bedList[0] = new Bed�rfnis(0, 50, 0, 100, 25);  // Nahrung
		bedList[1] = new Bed�rfnis(1, 50, 0, 100, 20);  // Gesundheit
		bedList[2] = new Bed�rfnis(2, 50, 0, 100, 30);  // Soziales
		bedList[3] = new Bed�rfnis(3, 50, 0, 100, 35);  // Luxus
		avList.add(new Avatar(1, "Horst Terarno", "Schwierigkeitsgrad: leicht\n\nHorst ist ein 35j�hriger, kinder- und arbeitsloser Akademiker. Er hat Immobilienmanagement studiert und danach bei Traumhaus.de gearbeitet. Aufgrund der Wirtschaftskrise und gescheiterter Aktienspekulationen verlor er seinen Arbeitsplatz und seine R�cklagen schrumpften auf 1500,-�. Trotz Hartz IV versucht er seinen Lebensstandard zu halten.", bedList, 1500, 400, 5));
		
		// Hasma Hamada
		bedList = new Bed�rfnis[5];		
		bedList[0] = new Bed�rfnis(0, 50, 0, 100, 50);  // Nahrung
		bedList[1] = new Bed�rfnis(1, 50, 0, 100, 25);  // Gesundheit
		bedList[2] = new Bed�rfnis(2, 50, 0, 100, 20);  // Soziales
		bedList[3] = new Bed�rfnis(3, 50, 0, 100, 25);  // Luxus
		bedList[4] = new Bed�rfnis(4, 50, 0, 100, 30);  // Kinder
		avList.add(new Avatar(2, "Hasma Hamada", "Schwierigkeitsgrad: mittel\n\nHasma ist 40 Jahre alt, verheiratet und ist Vater von 3 Kindern (9, 10 und 12 Jahre). Er ist gelernter Dachdecker, raucht und ist f�r jeden Snack zu haben. Er pflegt seine Freundschaften und liebt seine Kinder. Aufgrund von Rationalisierung und bescheidenen Qualifikationen wurde ihm bei Bedachungen Wenner gek�ndigt. Seine Frau tr�gt mit einem 400�-Job zur Haushaltskasse bei, trotzdem kann die Familie nur R�cklagen in H�he von 130,-� verbuchen.", bedList, 130, 1500, 2));
		
		// Chantal-Jacqueline Chaves
		bedList = new Bed�rfnis[5];		
		bedList[0] = new Bed�rfnis(0, 50, 0, 100, 35);  // Nahrung
		bedList[1] = new Bed�rfnis(1, 50, 0, 100, 30);  // Gesundheit
		bedList[2] = new Bed�rfnis(2, 50, 0, 100, 25);  // Soziales
		bedList[3] = new Bed�rfnis(3, 50, 0, 100, 20);  // Luxus
		bedList[4] = new Bed�rfnis(4, 50, 0, 100, 30);  // Kinder
		avList.add(new Avatar(3, "Jacqueline Chaves", "Schwierigkeitsgrad: schwer\n\nChantal ist eine 20j�hrige Mutter eines 3j�hrigen M�dchens und erneut im f�nften Monat schwanger. Den Kontakt zu ihren Eltern, sowie zu den V�tern ihrer Kinder hat sie abgebrochen. Ihre Fris�r-Ausbildung hat sie aufgrund der Schwangerschaft abgebrochen und hat deswegen keine R�cklage und knabbert an einer monatlichen Rate von 30,-� f�r diverse Haushaltsger�te.", bedList, 0, 600, 3));
		
		return avList;
	}
	
	/**
	 * Gibt die Liste mit allen Ereignissen die keine Kinder ben�tigen zur�ck. Diese sind dabei immer in der gleichen Reihenfolge.
	 * 
	 * @return ArrayList mit allen Ereignissen.
	 */
	public static ArrayList<Ereignis> gibEreignisse() {
		ArrayList<Ereignis> erList = new ArrayList<Ereignis>();
		
		Ereignis e;
		Information[] ja, nein;
		Random zufall = new Random();
		
		// Ereignisdaten
		ja = new Information[1];
		ja[0] = new Information(13, 2, 50);
		e = new Ereignis(1, "Lottogewinn", "Du hast 4 Richtige und daher im Lotto 50� gewonnen. Gehe sparsam damit um!", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, -10);
		nein = new Information[1];
		nein[0] = new Information(3, 4, -50);
		e = new Ereignis(2, "Krankheit", "Du hast Atemschwierigkeiten und starkes Fieber und kannst Ihren Arzt besuchen. Dabei fallen 10� Praxisgeb�hren an. Gehst du nicht zum Arzt leiden Ihre Bed�rfnisse darunter. M�chtest du den Arzt besuchen?", true, ja, nein);
		erList.add(e);
		
		ja = new Information[2];
		ja[0] = new Information(5, 2, 25);
		ja[1] = new Information(7, 2, 35);
		e = new Ereignis(3, "Kino", "Natasha l�dt dich ins Kino ein. Dadurch wird dein Bed�rfnis an Luxus und Sozialem gesichert.", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, -40);
		e = new Ereignis(4, "Diebstahl", "In dein Haus wurde eingebrochen. Dabei wurden dir 40� entwendet.", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(1, 1, 0);
		e = new Ereignis(5, "K�hlschrank defekt", "W�hrend du in der Stadt warst hat dein K�hlschrank den Geist aufgegeben. Du kannst ihn zwar reparieren, jedoch sind alle Lebensmittel verdorben.", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, 50);
		e = new Ereignis(6, "Steuerr�ckzahlung", "Du hast im letzten Jahr zu viele Steuern gezahlt und erh�lst daher jetzt eine R�ckzahlung in H�he von 50�.", false, ja, null);
		erList.add(e);		
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, 100);
		e = new Ereignis(8, "Fund", "Du findest in der Stadt 100� auf dem Boden. Du bist mega happy.", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, -10);
		e = new Ereignis(9, "Ohne Licht gefahren", "W�hrend du mit deinem Fahrrad ohne Licht unterwegs warst, wurdest du erwischt und mu�t 10� Strafe zahlen.", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, 10);
		e = new Ereignis(10, "Pfand erhalten", "Du bringst deine gesammelten Pfandflaschen zur�ck und erh�lst 10�.", false, ja, null);
		erList.add(e);
		
		nein = new Information[2];
		nein[0] = new Information(13, 2, 12);
		nein[1] = new Information(5, 4, -60);
		e = new Ereignis(11, "R�ckgeld", "Die Kassiererin bei kik gibt dir 12� zu viel zur�ck. Du kannst das Geld einstecken, doch das w�rde dein soziales Ansehen senken. Gibst du das Geld zur�ck?", true, null, nein);
		erList.add(e);
		
		ja = new Information[2];
		ja[0] = new Information(13, 2, -400);
		ja[1] = new Information(12, 2, 1);
		nein = new Information[1];
		nein[0] = new Information(13, 2, -20);
		e = new Ereignis(12, "Waschmaschine defekt", "Deine Waschmaschine scheint defekt zu sein. Zwar k�nnte ein Mechaniker das alte Ger�t f�r 20� reparieren, doch mit einer neuen, 400 Euro teuren Waschmaschine sparst du viel Zeit und hast somit einen Zug mehr pro Runde. Kaufst du die neue Maschine?", true, ja, nein);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, -8);
		e = new Ereignis(13, "Fahrradreifen defekt", "W�hrend einer Radtour ist dein Fahrradreifen geplatzt. An einer nahegelegenen Tankstelle kannst du Flickzeug besorgen. Es kostet dich 8�.", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		if(zufall.nextBoolean())
			ja[0] = new Information(13, 2, 75);
		else
			ja[0] = new Information(13, 4, -15);
		e = new Ereignis(14, "Gericht", "Du wurdest von einem Reichen angep�belt. M�chtest du Schadensersatz fordern? Obwohl Geld winkt, musst du bei einer Niederlage des Prozesses einen Teil der Gerichtskosten �bernehmen.", true, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, 70);
		e = new Ereignis(15, "Erbfall", "Ein Onkel ist verstorben. Sein Verm�chtnis betr�gt 70�.", false, ja, null);
		erList.add(e);
		
		ja = new Information[2];
		ja[0] = new Information(5, 2, 15);
		ja[1] = new Information(7, 2, 25);
		e = new Ereignis(16, "Preisausschreiben", "Du hast bei einem Preisausschreiben gewonnen! Der Gewinn sind Freikarten f�r Kino und Museum. ", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, -30);
		nein = new Information[1];
		nein[0] = new Information(5, 4, -75);
		e = new Ereignis(17, "Ausflug", "Einer deiner Freunde fragt, ob du an einem Ausflug teilnehmen willst. Du m�sstest 30� bezahlen, andernfalls sinkt dein soziales Bed�rfnis. Nimmst du teil?", true, ja, nein);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, -15);
		e = new Ereignis(18, "Handy", "Eine Handyabrechnung wurde nicht beglichen, zahle 15� Strafe.", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(1, 3, 100);
		e = new Ereignis(19, "Essen bei Mutter", "Deine Mutter hat dich am Wochenende zum Essen eingeladen!", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, -70);
		nein = new Information[1];
		nein[0] = new Information(5, 1, 0);
		e = new Ereignis(20, "Todesfall", "Dein Onkel ist pl�tzlich verstorben. Der Rest deiner Verwandschaft schl�gt vor, dass du dich mit 70� an der Beerdigung beteiligst. Bist du einverstanden? Wenn nicht, sinkt dein Ansehen.", true, ja, nein);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(1, 1, 100);
		e = new Ereignis(21, "Nahrungsaufschub", "Dein Vater bringt dir eine Ladung �pfel aus seinem Garten mit und f�llt damit deinen Nahrungsvorrat auf.", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(7, 2, 20);
		e = new Ereignis(22, "Europafest", "Du besuchst das Europafest zum Thema \"Armut und Ausgrenzung\" und spielst ein tolles Spiel. Das Bed�rfnis nach Luxus sinkt.", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, -10);
		e = new Ereignis(23, "McDonalds ruft an", "McDonalds ruft an: Deine Mutter ist in der Rutsche stecken geblieben. Du musst 10� f�r Fett zahlen um sie wieder heraus zu holen.", false, ja, null);
		erList.add(e);
		
		return erList;
	}
	
	/**
	 * Gibt die Liste mit allen Ereignissen die Kinder ben�tigen zur�ck. Diese sind dabei immer in der gleichen Reihenfolge.
	 * 
	 * @return ArrayList mit allen Ereignissen.
	 */
	public static ArrayList<Ereignis> gibKinderEreignisse() {
		ArrayList<Ereignis> erList = new ArrayList<Ereignis>();
		
		Ereignis e;
		Information[] ja, nein;
		//Random zufall = new Random();
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, -70);
		e = new Ereignis(7, "Schulausflug", "Deine Kinder machen einen Ausflug nach Hamburg. Zahle 70 Euro f�r die Fahrt. ", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(Information.AENDERN_KINDER, 1, 100);
		e = new Ereignis(24, "Clown", "Asi, der lustige Clown kommt vorbei. Deine Kinder sind euphorisch!", false, ja, null);
		erList.add(e);
		
		ja = new Information[2];
		ja[0] = new Information(Information.AENDERN_GELD, 2, -50);
		ja[1] = new Information(Information.AENDERN_KINDER, 4, 50);
		nein = new Information[1];
		nein[0] = new Information(Information.AENDERN_KINDER, 4, -50);
		e = new Ereignis(25, "Spielzeugwunsch", "Deine Kinder wollen unbedingt ein neues Spielzeug f�r 50 Euro. Kaufst du es und machst dein Kind gl�cklich oder wirst du es entt�uschen?", true, ja, nein);
		
		return erList;
	}
	
	public static ArrayList<Aktionsobjekt> getAktionsobjekte(Spielbereich spielbereich) {
		ArrayList<Aktionsobjekt> aktionsobjekte = new ArrayList<Aktionsobjekt>();
		
		Aktionsobjekt cheat = getCheat();
		cheat.addMouseListener(spielbereich);
		spielbereich.add(cheat);
		aktionsobjekte.add(cheat);
		
		Aktionsobjekt schrank = getSchrank();
		schrank.addMouseListener(spielbereich);
		spielbereich.add(schrank);
		aktionsobjekte.add(schrank);
		
		Aktionsobjekt tv = getTV();
		tv.addMouseListener(spielbereich);
		spielbereich.add(tv);
		aktionsobjekte.add(tv);
		
		Aktionsobjekt tuer = getTuer();
		tuer.addMouseListener(spielbereich);
		spielbereich.add(tuer);
		aktionsobjekte.add(tuer);
		
		Aktionsobjekt zeitung = getZeitung();
		zeitung.addMouseListener(spielbereich);
		spielbereich.add(zeitung);
		aktionsobjekte.add(zeitung);
		
		Aktionsobjekt kuehlschrank = getKuehlschrank();
		kuehlschrank.addMouseListener(spielbereich);
		spielbereich.add(kuehlschrank);
		aktionsobjekte.add(kuehlschrank);
				
		return aktionsobjekte;
	}
	
	public static Aktionsobjekt getSchrank() {
		
		Verzweigung schrankMenu = new Verzweigung("Kleiderschrank", "Hier kannst du dich einkleiden.", new ArrayList<Aktion>(), new ArrayList<Verzweigung>());
		Aktionsobjekt schrank = new Aktionsobjekt("Kleiderschrank �ffnen", new Point(92,45), new Dimension(137,230), "", "shrankclosed.png", schrankMenu);
		
		return schrank;
	}
	
	public static Aktionsobjekt getTV() {
		Verzweigung tvMenu = new Verzweigung("Fernseher", "Hier kannst du Britt, DSDS und Hartz 4 TV gucken.", new ArrayList<Aktion>(), new ArrayList<Verzweigung>());
		Aktionsobjekt tv = new Aktionsobjekt("TV anschaltren", new Point(10,257), new Dimension(79, 124), "tvopen.png", "tvclosed.png", tvMenu);
		
		return tv;
	}
	
	public static Aktionsobjekt getKuehlschrank() {
		//Gem�se
		ArrayList<Aktion> gemueseAktionen = new ArrayList<Aktion>();
		Information gemueseInfos[] = new Information[4];
		gemueseInfos[0] = new Information(Information.AENDERN_ZEIT, Information.ART_UM_WERT,-1);
		gemueseInfos[1] = new Information(Information.AENDERN_GELD, Information.ART_UM_WERT, -20);
		gemueseInfos[2] = new Information(Information.AENDERN_NAHRUNG, Information.ART_UM_WERT, 20);
		gemueseInfos[3] = new Information(Information.AENDERN_GESUNDHEIT, Information.ART_UM_WERT, 15);
		gemueseAktionen.add(new Aktion("Hochwertig", "(blue)Kaufe hochwertiges Gem�se", "Du hast Qualit�tsgem�se gekauft.", gemueseInfos.clone(),null));
		gemueseInfos[0] = new Information(Information.AENDERN_ZEIT, Information.ART_UM_WERT,-1);
		gemueseInfos[1] = new Information(Information.AENDERN_GELD, Information.ART_UM_WERT, -15);
		gemueseInfos[2] = new Information(Information.AENDERN_NAHRUNG, Information.ART_UM_WERT, 15);
		gemueseInfos[3] = new Information(Information.AENDERN_GESUNDHEIT, Information.ART_UM_WERT, 10);
		gemueseAktionen.add(new Aktion("Mittelm��ig", "(blue)Kaufe mittelm��iges Gem�se", "Du hast mittelm��iges Gem�se gekauft.", gemueseInfos.clone(),null));
		gemueseInfos[0] = new Information(Information.AENDERN_ZEIT, Information.ART_UM_WERT,-1);
		gemueseInfos[1] = new Information(Information.AENDERN_GELD, Information.ART_UM_WERT, -10);
		gemueseInfos[2] = new Information(Information.AENDERN_NAHRUNG, Information.ART_UM_WERT, 10);
		gemueseInfos[3] = new Information(Information.AENDERN_GESUNDHEIT, Information.ART_UM_WERT, 5);
		gemueseAktionen.add(new Aktion("Billig", "(blue)Kaufe billiges Gem�se", "Du hast billiges Gem�se gekauft.", gemueseInfos.clone(),null));
		ArrayList<Verzweigung> kuehlschrankVerzweigung = new ArrayList<Verzweigung>();
		kuehlschrankVerzweigung.add(new Verzweigung("Gem�se", "Gem�se erh�ht nicht nur deinen Nahrungsbalken, sondern auch deine Gesundheit. Allerdings kostet es daf�r auch relativ viel.", gemueseAktionen, new ArrayList<Verzweigung>()));
		
		//Fleisch
		ArrayList<Aktion> fleischAktionen = new ArrayList<Aktion>();
		Information fleischInfos[] = new Information[4];
		fleischInfos[0] = new Information(Information.AENDERN_ZEIT, Information.ART_UM_WERT, -1);
		fleischInfos[1] = new Information(Information.AENDERN_GELD, Information.ART_UM_WERT, -20);
		fleischInfos[2] = new Information(Information.AENDERN_NAHRUNG, Information.ART_UM_WERT, 30);
		fleischInfos[3] = new Information(Information.AENDERN_GESUNDHEIT, Information.ART_UM_WERT, -15);
		fleischAktionen.add(new Aktion("Hochwertig", "(blue)Kaufe hochwertiges Fleisch", "Du hast Qualit�tsfleisch gekauft.", fleischInfos.clone(), null));
		fleischInfos[0] = new Information(Information.AENDERN_ZEIT, Information.ART_UM_WERT, -1);
		fleischInfos[1] = new Information(Information.AENDERN_GELD, Information.ART_UM_WERT, -15);
		fleischInfos[2] = new Information(Information.AENDERN_NAHRUNG, Information.ART_UM_WERT, 25);
		fleischInfos[3] = new Information(Information.AENDERN_GESUNDHEIT, Information.ART_UM_WERT, -10);
		fleischAktionen.add(new Aktion("Mittelm��ig", "(blue)Kaufe mittelm��iges Fleisch", "Du hast mittelm��iges Fleisch gekauft.", fleischInfos.clone(), null));
		fleischInfos[0] = new Information(Information.AENDERN_ZEIT, Information.ART_UM_WERT, -1);
		fleischInfos[1] = new Information(Information.AENDERN_GELD, Information.ART_UM_WERT, -10);
		fleischInfos[2] = new Information(Information.AENDERN_NAHRUNG, Information.ART_UM_WERT, 20);
		fleischInfos[3] = new Information(Information.AENDERN_GESUNDHEIT, Information.ART_UM_WERT, -5);
		fleischAktionen.add(new Aktion("Billig", "(blue)Kaufe billiges Fleisch", "Du hast billiges Fleisch gekauft.", fleischInfos.clone(), null));
		kuehlschrankVerzweigung.add(new Verzweigung("Fleisch", "Fleisch macht extrem satt, ist aber auch recht teuer. Leider macht es auf Dauer fett und schadet somit der Gesundheit.", fleischAktionen, new ArrayList<Verzweigung>()));
		
		//Brot
		ArrayList<Aktion> brotAktionen = new ArrayList<Aktion>();
		Information brotInfos[] = new Information[3];
		brotInfos[0] = new Information(Information.AENDERN_ZEIT, Information.ART_UM_WERT, -1);
		brotInfos[1] = new Information(Information.AENDERN_GELD, Information.ART_UM_WERT, -15);
		brotInfos[2] = new Information(Information.AENDERN_NAHRUNG, Information.ART_UM_WERT, 20);
		brotAktionen.add(new Aktion("Hochwertig", "(blue)Kaufe hochwertiges Brot", "Du hast Qualit�tsbrot gekauft.", brotInfos.clone(), null));
		brotInfos[0] = new Information(Information.AENDERN_ZEIT, Information.ART_UM_WERT, -1);
		brotInfos[1] = new Information(Information.AENDERN_GELD, Information.ART_UM_WERT, -10);
		brotInfos[2] = new Information(Information.AENDERN_NAHRUNG, Information.ART_UM_WERT, 15);
		brotAktionen.add(new Aktion("Mittelm��ig", "(blue)Kaufe mittelm��iges Brot", "Du hast mittelm��iges Brot gekauft.", brotInfos.clone(), null));
		brotInfos[0] = new Information(Information.AENDERN_ZEIT, Information.ART_UM_WERT, -1);
		brotInfos[1] = new Information(Information.AENDERN_GELD, Information.ART_UM_WERT, -5);
		brotInfos[2] = new Information(Information.AENDERN_NAHRUNG, Information.ART_UM_WERT, 10);
		brotAktionen.add(new Aktion("Billig", "(blue)Kaufe billiges Brot", "Du hast billiges Brot gekauft.", brotInfos.clone(), null));
		kuehlschrankVerzweigung.add(new Verzweigung("Brot", "Brot macht satt, hat aber sonst keinerlei Auswirkungen. Daf�r ist es g�nstiger als andere Nahrungsmittel.", brotAktionen, new ArrayList<Verzweigung>()));
		
		//Fast Food
		ArrayList<Aktion> ffoodAktionen = new ArrayList<Aktion>();
		Information ffoodInfos[] = new Information[4];
		ffoodInfos[0] = new Information(Information.AENDERN_ZEIT, Information.ART_UM_WERT, -1);
		ffoodInfos[1] = new Information(Information.AENDERN_GELD, Information.ART_UM_WERT, -9);
		ffoodInfos[2] = new Information(Information.AENDERN_NAHRUNG, Information.ART_UM_WERT, 30);
		ffoodInfos[3] = new Information(Information.AENDERN_GESUNDHEIT, Information.ART_UM_WERT, -30);
		ffoodAktionen.add(new Aktion("Maximen�", "(blue)Der Koloss unter den Burgern", "Du hast ein Maximen� gekauft.", ffoodInfos.clone(), null));
		ffoodInfos[0] = new Information(Information.AENDERN_ZEIT, Information.ART_UM_WERT, -1);
		ffoodInfos[1] = new Information(Information.AENDERN_GELD, Information.ART_UM_WERT, -6);
		ffoodInfos[2] = new Information(Information.AENDERN_NAHRUNG, Information.ART_UM_WERT, 20);
		ffoodInfos[3] = new Information(Information.AENDERN_GESUNDHEIT, Information.ART_UM_WERT, -20);
		ffoodAktionen.add(new Aktion("Sparmen�", "(blue)Ein ansehnlicher Haufen Fleisch", "Du hast mittelm��iges Fleisch gekauft.", ffoodInfos.clone(), null));
		ffoodInfos[0] = new Information(Information.AENDERN_ZEIT, Information.ART_UM_WERT, -1);
		ffoodInfos[1] = new Information(Information.AENDERN_GELD, Information.ART_UM_WERT, -3);
		ffoodInfos[2] = new Information(Information.AENDERN_NAHRUNG, Information.ART_UM_WERT, 10);
		ffoodInfos[3] = new Information(Information.AENDERN_GESUNDHEIT, Information.ART_UM_WERT, -10);
		ffoodAktionen.add(new Aktion("ein paar Burger", "(blue)3 pappige Burger", "Du hast billiges Fleisch gekauft.", ffoodInfos.clone(), null));
		kuehlschrankVerzweigung.add(new Verzweigung("Fast Food", "Bet�rend, billig, b�se: Fast Food macht f�r wenig Geld erstaunlich satt. Doch die von Geschmacksverst�rkern durchzogenen Nahrungsmittel schaden der Gesundheit extrem.", ffoodAktionen, new ArrayList<Verzweigung>()));
		
		Verzweigung kuehlschrankMenu = new Verzweigung("K�hlschrank", "Hier kannst du Lebensmittel einkaufen.", new ArrayList<Aktion>(), kuehlschrankVerzweigung);
		Aktionsobjekt kuehlschrank = new Aktionsobjekt("Essen kaufen",new Point(522, 85), new Dimension(144, 241),"fridgeopen.png", "fridgeclosed.png", kuehlschrankMenu);
		return kuehlschrank;
	}
	
	public static Aktionsobjekt getTuer() {
		ArrayList<Aktion> t�rAktionen = new ArrayList<Aktion>();
		
		Information[] kinoBesuchen = {
				new Information(Information.AENDERN_ZEIT, Information.ART_UM_WERT, -1),
				new Information(Information.AENDERN_GELD, Information.ART_UM_WERT, -15),
				new Information(Information.AENDERN_SOZIALES, Information.ART_UM_WERT, 5),
				new Information(Information.AENDERN_LUXUS, Information.ART_UM_WERT, 15),
				new Information(Information.AENDERN_KINDER, Information.ART_UM_WERT, 15)
		};
		t�rAktionen.add(new Aktion("Kino besuchen", "(blue)Geh ins Kino und verbringe eine sch�ne Zeit mit deinen Freunden.", "Du bist ins Kino gegangen", kinoBesuchen, null));
		
        t�rAktionen.add(new Aktion("Freunde besuchen", "(blue)==Minispiel starten==", "Du hast deine Freunde besucht.", null, null));
        t�rAktionen.get(1).setMinispiel(new TicTacToe());
        
        Information[] theaterBesuchen = {
        		new Information(Information.AENDERN_ZEIT, Information.ART_UM_WERT, -1),
        		new Information(Information.AENDERN_GELD, Information.ART_UM_WERT, -30),
        		new Information(Information.AENDERN_SOZIALES, Information.ART_UM_WERT, 10),
        		new Information(Information.AENDERN_LUXUS, Information.ART_UM_WERT, 30),
        		new Information(Information.AENDERN_KINDER, Information.ART_UM_WERT, 10)
        };
        t�rAktionen.add(new Aktion("Theater besuchen", "(blue)Besuch ein Theater und genie� die Kultur.", "Du bist ins Theater gegangen.", theaterBesuchen, null));
        
		ArrayList<Verzweigung> t�rVerzweigungen = new ArrayList<Verzweigung>();
		ArrayList<Aktion> sonstigesAktionen = new ArrayList<Aktion>();
		
		Information[] parkBesuchen = {
				new Information(Information.AENDERN_ZEIT, Information.ART_UM_WERT, -1),
				new Information(Information.AENDERN_SOZIALES, Information.ART_UM_WERT, 25),
		};
		sonstigesAktionen.add(new Aktion("Park", "(blue)Geh in den Park und triff ein paar nette Leute.", "Du bist in den Park gegangen", parkBesuchen, null));
		
		Verzweigung sonstiges = new Verzweigung("Sonstiges", "Sonstige Aktivit�ten ausserhalb deiner Wohnung.", sonstigesAktionen, new ArrayList<Verzweigung>());
		t�rVerzweigungen.add(sonstiges);
		
		Verzweigung tuerMenu = new Verzweigung("T�r", "Hier kannst du Aktivit�ten au�erhalb deiner Wohnung ausw�hlen", t�rAktionen, t�rVerzweigungen);
		Aktionsobjekt tuer = new Aktionsobjekt("Wohnung verlassen", new Point(687, 90), new Dimension(81, 340), "dooropen.png","doorclosed.png", tuerMenu);
		return tuer;
	}
	
	public static Aktionsobjekt getZeitung() {
		ArrayList<Aktion> zeitungsAktionen = new ArrayList<Aktion>();
		Information[] zeitungLesen = {
				new Information(Information.AENDERN_ZEIT, Information.ART_UM_WERT, -1),
				new Information(Information.AENDERN_BEWERBUNGSFAKTOR, Information.ART_UM_WERT, 5)
		};
		zeitungsAktionen.add(new Aktion("Lesen", "(blue)Lesen bildet! Verbessere deine Allgemeinbildung und somit deine Chance, in einem Bewerbungsgespr�ch angenommen zu werden.", "Du hast die Zeitung durchgelesen.", zeitungLesen, null));	
		ArrayList<Verzweigung> zeitungsVerzweigungen = new ArrayList<Verzweigung>();
		Verzweigung zeitungsMenu = new Verzweigung("Zeitung", "Hier kannst du die Zeitung lesen, Kreuzwortr�tsel l�sen und vieles mehr.", zeitungsAktionen, zeitungsVerzweigungen);
		
		Aktionsobjekt zeitung = new Aktionsobjekt("Zeitung lesen", new Point(100, 340), new Dimension(125, 64), "newspaperopen.png", "newspaperclosed.png", zeitungsMenu);
		return zeitung;
	}
	
	public static Aktionsobjekt getCheat() {
		// Aktionen
		ArrayList<Aktion> cheatAktionen = new ArrayList<Aktion>();
		
		// Bed�rfnisse f�llen
		Information[] nutzBed = {
				new Information(Information.AENDERN_NAHRUNG, Information.ART_AUF_WERT, 100),
				new Information(Information.AENDERN_GESUNDHEIT, Information.ART_AUF_WERT, 100),
				new Information(Information.AENDERN_SOZIALES, Information.ART_AUF_WERT, 100),
				new Information(Information.AENDERN_LUXUS, Information.ART_AUF_WERT, 100),
				new Information(Information.AENDERN_KINDER, Information.ART_AUF_WERT, 100)
		};
		cheatAktionen.add(new Aktion("Bed�rfnisse f�llen", "(blue)F�lle alle Bed�rfnisse auf das Maximum auf!", "Du hast alle Bed�rfnisse auf das Maximum aufgef�llt!", null, nutzBed));
		
		// Geld um 1.000 Euro erh�hen
		Information[] nutzGeld = {
				new Information(Information.AENDERN_GELD, Information.ART_UM_WERT, 1000)
		};
		cheatAktionen.add(new Aktion("Geld verdienen", "(blue)Verdiene 1.000�! Ganz ohne Arbeit!", "Du hast ganz ohne Arbeit 1.000� verdient!", null, nutzGeld));
		
		// Zeit um 5 erh�hen
		Information[] nutzZeit = {
				new Information(Information.AENDERN_ZEIT, Information.ART_UM_WERT, 5)
		};
		cheatAktionen.add(new Aktion("Zeit sparen", "(blue)Spare Zeit, indem du hier ganz einfach Cheatest!", "Du hast soviel Zeit gespart, dass du jetzt eine Aktion mehr hast!", null, nutzZeit));
		
		// Verzweigungen
		ArrayList<Verzweigung> cheatVerzweigungen = new ArrayList<Verzweigung>();
		
		// Men�
		Verzweigung cheatMenu = new Verzweigung("Cheaten", "Auch als HartzIV-Empf�nger kannst du manchmal Chuck Norris spielen!", cheatAktionen, cheatVerzweigungen);
		
		Aktionsobjekt cheat = new Aktionsobjekt("", new Point(0, 498), new Dimension(10, 10), "", "", cheatMenu);	
		return cheat;
	}
}
