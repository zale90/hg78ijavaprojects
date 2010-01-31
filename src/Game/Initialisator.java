package Game;

import java.awt.*;
import java.util.*;

import Game.GUI.*;
import Game.Minigames.Senso.*;
import Game.Minigames.Sudoku.*;
import Game.Minigames.TicTacToe.*;
import Game.Minigames.Automat.Kontrolle;
import Game.Minigames.Bewerbungstest.*;
import Game.Minigames.Kniffel.Kniffel;
import Game.Minigames.Kreuzwortr�tsel.*;
import Game.Minigames.*;

public class Initialisator {

	/**
	 * Liefert die vordefinierten Avatare zur�ck.
	 * 
	 * @return ArrayList mit Avataren.
	 */
	public static ArrayList<Avatar> gibAvatare() {
		ArrayList<Avatar> avList = new ArrayList<Avatar>();
		Bed�rfnis[] bedList;

		// Horst Terano
		bedList = new Bed�rfnis[4];
		bedList[0] = new Bed�rfnis(0, 50, 0, 100, 25); // Nahrung
		bedList[1] = new Bed�rfnis(1, 50, 0, 100, 20); // Gesundheit
		bedList[2] = new Bed�rfnis(2, 50, 0, 100, 30); // Soziales
		bedList[3] = new Bed�rfnis(3, 50, 0, 100, 35); // Luxus
		avList
				.add(new Avatar(
						1,
						"Horst Terarno",
						"Schwierigkeitsgrad: leicht\n\nHorst ist ein 35j�hriger, kinder- und arbeitsloser Akademiker. Er hat Immobilienmanagement studiert und danach bei Traumhaus.de gearbeitet. Aufgrund der Wirtschaftskrise und gescheiterter Aktienspekulationen verlor er seinen Arbeitsplatz und seine R�cklagen schrumpften auf 1500,-�. Trotz Hartz IV versucht er seinen Lebensstandard zu halten.",
						bedList, 500, 400, 20, 1));

		// Hasma Hamada
		bedList = new Bed�rfnis[5];
		bedList[0] = new Bed�rfnis(0, 50, 0, 100, 50); // Nahrung
		bedList[1] = new Bed�rfnis(1, 50, 0, 100, 25); // Gesundheit
		bedList[2] = new Bed�rfnis(2, 50, 0, 100, 20); // Soziales
		bedList[3] = new Bed�rfnis(3, 50, 0, 100, 25); // Luxus
		bedList[4] = new Bed�rfnis(4, 50, 0, 100, 30); // Kinder
		avList
				.add(new Avatar(
						2,
						"Hasma Hamada",
						"Schwierigkeitsgrad: mittel\n\nHasma ist 40 Jahre alt, verheiratet und ist Vater von 3 Kindern (9, 10 und 12 Jahre). Er ist gelernter Dachdecker, raucht und ist f�r jeden Snack zu haben. Er pflegt seine Freundschaften und liebt seine Kinder. Aufgrund von Rationalisierung und bescheidenen Qualifikationen wurde ihm bei Bedachungen Wenner gek�ndigt. Seine Frau tr�gt mit einem 400�-Job zur Haushaltskasse bei, trotzdem kann die Familie nur R�cklagen in H�he von 130,-� verbuchen.",
						bedList, 200, 1500, 15, 5));

		// Chantal-Jacqueline Chaves
		bedList = new Bed�rfnis[5];
		bedList[0] = new Bed�rfnis(0, 50, 0, 100, 35); // Nahrung
		bedList[1] = new Bed�rfnis(1, 50, 0, 100, 30); // Gesundheit
		bedList[2] = new Bed�rfnis(2, 50, 0, 100, 25); // Soziales
		bedList[3] = new Bed�rfnis(3, 50, 0, 100, 20); // Luxus
		bedList[4] = new Bed�rfnis(4, 50, 0, 100, 30); // Kinder
		avList
				.add(new Avatar(
						3,
						"Jacqueline Chaves",
						"Schwierigkeitsgrad: schwer\n\nChantal ist eine 20j�hrige Mutter eines 3j�hrigen M�dchens und erneut im f�nften Monat schwanger. Den Kontakt zu ihren Eltern, sowie zu den V�tern ihrer Kinder hat sie abgebrochen. Ihre Fris�r-Ausbildung hat sie aufgrund der Schwangerschaft abgebrochen und hat deswegen keine R�cklage und knabbert an einer monatlichen Rate von 30,-� f�r diverse Haushaltsger�te.",
						bedList, 100, 600, 10, 3));

		return avList;
	}

	/**
	 * Gibt die Liste mit allen Ereignissen die keine Kinder ben�tigen zur�ck.
	 * Diese sind dabei immer in der gleichen Reihenfolge.
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
		e = new Ereignis(
				1,
				"Lottogewinn",
				"Du hast 4 Richtige und daher im Lotto 50� gewonnen. Gehe sparsam damit um!",
				false, ja, null);
		erList.add(e);

		ja = new Information[1];
		ja[0] = new Information(13, 2, -10);
		nein = new Information[1];
		nein[0] = new Information(3, 4, -50);
		e = new Ereignis(
				2,
				"Krankheit",
				"Du hast Atemschwierigkeiten und starkes Fieber und kannst deinen Arzt besuchen. Dabei fallen 10� Praxisgeb�hren an. Gehst du nicht zum Arzt leiden deine Bed�rfnisse darunter. M�chtest du den Arzt besuchen?",
				true, ja, nein);
		e.setJaAusgabe("Dein Arzt behandelt dich und verschreibt dir wirksame Medikamente.");
		e.setNeinAusgabe("Da du nicht zum Arzt gegangen bist sinkt deine Gesundheit stark.");
		erList.add(e);

		ja = new Information[2];
		ja[0] = new Information(5, 2, 25);
		ja[1] = new Information(7, 2, 35);
		e = new Ereignis(
				3,
				"Kino",
				"Natasha l�dt dich ins Kino ein. Dadurch wird dein Bed�rfnis an Luxus und Sozialem gesichert.",
				false, ja, null);
		erList.add(e);

		ja = new Information[1];
		ja[0] = new Information(13, 2, -40);
		e = new Ereignis(
				4,
				"Diebstahl",
				"In dein Haus wurde eingebrochen. Dabei wurden dir 40� entwendet.",
				false, ja, null);
		erList.add(e);

		ja = new Information[1];
		ja[0] = new Information(1, 1, 0);
		e = new Ereignis(
				5,
				"K�hlschrank defekt",
				"W�hrend du in der Stadt warst hat dein K�hlschrank den Geist aufgegeben. Du kannst ihn zwar reparieren, jedoch sind alle Lebensmittel verdorben.",
				false, ja, null);
		erList.add(e);

		ja = new Information[1];
		ja[0] = new Information(13, 2, 50);
		e = new Ereignis(
				6,
				"Steuerr�ckzahlung",
				"Du hast im letzten Jahr zu viele Steuern gezahlt und erh�lst daher jetzt eine R�ckzahlung in H�he von 50�.",
				false, ja, null);
		erList.add(e);

		ja = new Information[1];
		ja[0] = new Information(13, 2, 100);
		e = new Ereignis(
				8,
				"Fund",
				"Du findest in der Stadt 100� auf dem Boden. Du bist mega happy.",
				false, ja, null);
		erList.add(e);

		ja = new Information[1];
		ja[0] = new Information(13, 2, -10);
		e = new Ereignis(
				9,
				"Ohne Licht gefahren",
				"W�hrend du mit deinem Fahrrad ohne Licht unterwegs warst, wurdest du erwischt und musst 10� Strafe zahlen.",
				false, ja, null);
		erList.add(e);

		ja = new Information[1];
		ja[0] = new Information(13, 2, 10);
		e = new Ereignis(
				10,
				"Pfand erhalten",
				"Du bringst deine gesammelten Pfandflaschen zur�ck und erh�lst 10�.",
				false, ja, null);
		erList.add(e);

		nein = new Information[2];
		nein[0] = new Information(13, 2, 12);
		nein[1] = new Information(5, 4, -10);
		ja = new Information[1];
		ja[0] = new Information(5, 4, 5);
		e = new Ereignis(
				11,
				"R�ckgeld",
				"Die Kassiererin bei KiK gibt dir 12� zu viel zur�ck. Du kannst das Geld einstecken, doch das w�rde dein soziales Ansehen senken. Gibst du das Geld zur�ck?",
				true, ja, nein);
		e.setJaAusgabe("Du hast das Geld zur�ckgegeben und f�hlst dich deshalb sehr gut.");
		e.setNeinAusgabe("Du hast das Geld eingesteckt und bekommst deshalb leichte Gewissensbisse");
		erList.add(e);

		ja = new Information[2];
		ja[0] = new Information(13, 2, -400);
		ja[1] = new Information(12, 2, 1);
		nein = new Information[1];
		nein[0] = new Information(13, 2, -20);
		e = new Ereignis(
				12,
				"Waschmaschine defekt",
				"Deine Waschmaschine scheint defekt zu sein. Zwar k�nnte ein Mechaniker das alte Ger�t f�r 20� reparieren, doch mit einer neuen, 400 Euro teuren Waschmaschine sparst du viel Zeit und hast somit einen Zug mehr pro Runde. Kaufst du die neue Maschine?",
				true, ja, nein);
		e.setJaAusgabe("Du hast dir f�r 400 Euro eine neue Waschmaschine gekauft. Dadurch musst du nun weniger Zeit pro Woche in deine schmutzige W�sche investieren.");
		e.setNeinAusgabe("Du hast dich entschieden, deine alte Waschmaschine reparieren zu lassen. Dadurch sparst du zwar Geld, musst aber weiterhin mit der alten langsamen Maschine waschen.");
		erList.add(e);

		ja = new Information[1];
		ja[0] = new Information(13, 2, -8);
		e = new Ereignis(
				13,
				"Fahrradreifen defekt",
				"W�hrend einer Radtour ist dein Fahrradreifen geplatzt. An einer nahegelegenen Tankstelle kannst du Flickzeug besorgen. Es kostet dich 8�.",
				false, ja, null);
		erList.add(e);

		ja = new Information[1];
		String ausgabe = "";
		if (zufall.nextBoolean())
		{
			ja[0] = new Information(13, 2, 75);
			ausgabe = "Du hast die Verhandlung gewonnen! Dein Schadensersatz betr�gt 75 Euro.";
		}
		else
		{
			ja[0] = new Information(13, 4, -15);
			ausgabe = "Der Richter hat gegen dich entschieden. Du musst die Prozesskosten von 15 Euro (Es war ein sehr kleiner Prozess!) �bernehmen.";
		}
		e = new Ereignis(
				14,
				"Gericht",
				"Du wurdest von einem Reichen angep�belt. M�chtest du Schadensersatz fordern? Obwohl Geld winkt, musst du bei einer Niederlage des Prozesses einen Teil der Gerichtskosten �bernehmen.",
				true, ja, null);
		e.setJaAusgabe(ausgabe);
		e.setNeinAusgabe("Du entscheidest dich, den Reichen nicht zu verklagen.");
		erList.add(e);

		ja = new Information[1];
		ja[0] = new Information(13, 2, 70);
		e = new Ereignis(15, "Erbfall",
				"Ein Onkel ist verstorben. Sein Verm�chtnis betr�gt 70�.",
				false, ja, null);
		erList.add(e);

		ja = new Information[2];
		ja[0] = new Information(5, 2, 15);
		ja[1] = new Information(7, 2, 25);
		e = new Ereignis(
				16,
				"Preisausschreiben",
				"Du hast bei einem Preisausschreiben gewonnen! Der Gewinn sind Freikarten f�r Kino und Museum. ",
				false, ja, null);
		erList.add(e);

		ja = new Information[1];
		ja[0] = new Information(13, 2, -30);
		nein = new Information[1];
		nein[0] = new Information(5, 4, -50);
		e = new Ereignis(
				17,
				"Ausflug",
				"Einer deiner Freunde fragt, ob du an einem Ausflug teilnehmen willst. Du m�sstest 30� bezahlen, andernfalls sinkt dein soziales Bed�rfnis. Nimmst du teil?",
				true, ja, nein);
		e.setJaAusgabe("Du nimmst an dem Ausflug mit deinen Freunden Teil und hast viel Spa�.");
		e.setNeinAusgabe("Du lehnst den Ausflug ab und deine Freunde sind entt�uscht.");
		erList.add(e);

		ja = new Information[1];
		ja[0] = new Information(13, 2, -15);
		e = new Ereignis(
				18,
				"Handy",
				"Eine Handyabrechnung wurde nicht beglichen, zahle 15� Strafe.",
				false, ja, null);
		erList.add(e);

		ja = new Information[1];
		ja[0] = new Information(1, 4, 100);
		e = new Ereignis(19, "Essen bei Mutter",
				"Deine Mutter hat dich am Wochenende zum Essen eingeladen!",
				false, ja, null);
		erList.add(e);

		ja = new Information[1];
		ja[0] = new Information(13, 2, -70);
		nein = new Information[1];
		nein[0] = new Information(5, Information.ART_UM_WERT, -50);
		e = new Ereignis(
				20,
				"Todesfall",
				"Dein Onkel ist pl�tzlich verstorben. Der Rest deiner Verwandtschaft schl�gt vor, dass du dich mit 70� an der Beerdigung beteiligst. Bist du einverstanden? Wenn nicht, sinkt dein Ansehen.",
				true, ja, nein);
		e.setJaAusgabe("Du beteiligst dich an der Beerdigung und deine Verwandschaft ist zufrieden mit dir.");
		e.setNeinAusgabe("Du lehnst eine Beteiligung an der Beerdigung ab, worauf deine Verwandschaft sich lautstark beschwert.");
		erList.add(e);

		ja = new Information[1];
		ja[0] = new Information(1, 1, 100);
		e = new Ereignis(
				21,
				"Nahrungsaufschub",
				"Dein Vater bringt dir eine Ladung �pfel aus seinem Garten mit und f�llt damit deinen Nahrungsvorrat auf.",
				false, ja, null);
		erList.add(e);

		ja = new Information[1];
		ja[0] = new Information(7, 2, 20);
		e = new Ereignis(
				22,
				"Europafest",
				"Du besuchst das Europafest zum Thema \"Armut und Ausgrenzung\" und spielst ein tolles Spiel. Dein Verlangen nach Luxus wurde teilweise gestillt.",
				false, ja, null);
		erList.add(e);

		ja = new Information[1];
		ja[0] = new Information(13, 2, -10);
		e = new Ereignis(
				23,
				"McDonalds ruft an",
				"McDonalds ruft an: Deine Mutter ist in der Rutsche stecken geblieben. Du musst 10� f�r Fett zahlen, um sie wieder heraus zu holen.",
				false, ja, null);
		erList.add(e);

		return erList;
	}

	/**
	 * Gibt die Liste mit allen Ereignissen die Kinder ben�tigen zur�ck. Diese
	 * sind dabei immer in der gleichen Reihenfolge.
	 * 
	 * @return ArrayList mit allen Ereignissen.
	 */
	public static ArrayList<Ereignis> gibKinderEreignisse() {
		ArrayList<Ereignis> erList = new ArrayList<Ereignis>();

		Ereignis e;
		Information[] ja, nein;
		// Random zufall = new Random();

		ja = new Information[2];
		ja[0] = new Information(13, 2, -70);
		ja[1] = new Information(Information.AENDERN_KINDER, 2, 40);
		e = new Ereignis(
				7,
				"Schulausflug",
				"Deine Kinder machen einen Ausflug nach Hamburg. Zahle 70 Euro f�r die Fahrt. ",
				false, ja, null);
		erList.add(e);

		ja = new Information[1];
		ja[0] = new Information(Information.AENDERN_KINDER, 1, 100);
		e = new Ereignis(
				24,
				"Clown",
				"August, der lustige Clown kommt vorbei. Deine Kinder sind euphorisch!",
				false, ja, null);
		erList.add(e);

		ja = new Information[2];
		ja[0] = new Information(Information.AENDERN_GELD, 2, -50);
		ja[1] = new Information(Information.AENDERN_KINDER, 4, 50);
		nein = new Information[1];
		nein[0] = new Information(Information.AENDERN_KINDER, 4, -50);
		e = new Ereignis(
				25,
				"Spielzeugwunsch",
				"Deine Kinder wollen unbedingt ein neues Spielzeug f�r 50 Euro. Kaufst du es und machst dein Kind gl�cklich oder wirst du es entt�uschen?",
				true, ja, nein);
		e.setJaAusgabe("Du kaufst deinen Kindern ein Spielzeug und sie sind gl�cklich.");
		e.setNeinAusgabe("Du erkl�rst deinen Kindern, dass du dir momentan keine neuen Spielzeuge leisten kannst, wodurch sie sehr traurig werden.");
		return erList;
	}

	public static ArrayList<GeldBetrag> getHorstEinnahmen() {
		ArrayList<GeldBetrag> einnahmen = new ArrayList<GeldBetrag>();

		einnahmen.add(new GeldBetrag("Hartz IV", 359));
		einnahmen.add(new GeldBetrag("Mieterstattung", 477));

		return einnahmen;
	}

	public static ArrayList<GeldBetrag> getHasmaEinnahmen() {
		ArrayList<GeldBetrag> einnahmen = new ArrayList<GeldBetrag>();

		einnahmen.add(new GeldBetrag("Hartz IV Mann", 323));
		einnahmen.add(new GeldBetrag("Hartz IV Frau", 323));
		einnahmen.add(new GeldBetrag("Kind 6-13 Jahre", 251));
		einnahmen.add(new GeldBetrag("Kind 6-13 Jahre", 251));
		einnahmen.add(new GeldBetrag("Kind 6-13 Jahre", 251));
		einnahmen.add(new GeldBetrag("Zuverdienst Ehefrau", 160));
		einnahmen.add(new GeldBetrag("Mieterstattung", 478));

		return einnahmen;
	}

	public static ArrayList<GeldBetrag> getJaquelineEinnahmen() {
		ArrayList<GeldBetrag> einnahmen = new ArrayList<GeldBetrag>();

		einnahmen.add(new GeldBetrag("Hartz IV", 359));
		einnahmen.add(new GeldBetrag("Kind 0-6 Jahre", 215));
		einnahmen.add(new GeldBetrag("Schwangerschaft", 61));
		einnahmen.add(new GeldBetrag("Alleinerziehend",129));
		einnahmen.add(new GeldBetrag("Mieterstattung", 407));

		return einnahmen;
	}

	public static ArrayList<GeldBetrag> getHorstAusgaben() {
		ArrayList<GeldBetrag> ausgaben = new ArrayList<GeldBetrag>();

		ausgaben.add(new GeldBetrag("Miete", 477));

		return ausgaben;
	}

	public static ArrayList<GeldBetrag> getHasmaAusgaben() {
		ArrayList<GeldBetrag> ausgaben = new ArrayList<GeldBetrag>();

		ausgaben.add(new GeldBetrag("Miete", 478));

		return ausgaben;
	}

	public static ArrayList<GeldBetrag> getJaquelineAusgaben() {
		ArrayList<GeldBetrag> ausgaben = new ArrayList<GeldBetrag>();

		ausgaben.add(new GeldBetrag("Rate", 100));
		ausgaben.add(new GeldBetrag("Miete", 407));

		return ausgaben;
	}

	public static ArrayList<Aktionsobjekt> getAktionsobjekte(
			Spielbereich spielbereich, int familienmitglieder) {
		ArrayList<Aktionsobjekt> aktionsobjekte = new ArrayList<Aktionsobjekt>();

		Aktionsobjekt cheat = getCheat();
		cheat.addMouseListener(spielbereich);
		spielbereich.add(cheat);
		aktionsobjekte.add(cheat);

		Aktionsobjekt schrank = getSchrank(familienmitglieder);
		schrank.addMouseListener(spielbereich);
		spielbereich.add(schrank);
		aktionsobjekte.add(schrank);

		Aktionsobjekt casino = getCasino(spielbereich, familienmitglieder);
		casino.addMouseListener(spielbereich);
		spielbereich.add(casino);
		aktionsobjekte.add(casino);

		Aktionsobjekt tv = getTV(familienmitglieder);
		tv.addMouseListener(spielbereich);
		spielbereich.add(tv);
		aktionsobjekte.add(tv);

		Aktionsobjekt tuer = getTuer(spielbereich, familienmitglieder);
		tuer.addMouseListener(spielbereich);
		spielbereich.add(tuer);
		aktionsobjekte.add(tuer);

		Aktionsobjekt zeitung = getZeitung(spielbereich, familienmitglieder);
		zeitung.addMouseListener(spielbereich);
		spielbereich.add(zeitung);
		aktionsobjekte.add(zeitung);

		Aktionsobjekt kuehlschrank = getKuehlschrank(familienmitglieder);
		kuehlschrank.addMouseListener(spielbereich);
		spielbereich.add(kuehlschrank);
		aktionsobjekte.add(kuehlschrank);

		return aktionsobjekte;
	}

	public static Aktionsobjekt getCasino(Spielbereich spielbereich, int familienmitglieder) {
		ArrayList<Aktion> casinoAktionen = new ArrayList<Aktion>();
		
		Information[] slotInfos = {
				new Information(Information.AENDERN_ZEIT,
						Information.ART_UM_WERT, -3)
		};
		casinoAktionen
				.add(new Aktion(
						"Slot Machine",
						"(blue)Versuche dein gro�es Gl�ck an diesem Automaten. Aber Achtung: Verspiele nicht alles!",
						"Du warst im Kasino und hast dein Gl�ck an der Slot Machine versucht!",
						slotInfos, null));
		casinoAktionen.get(0).setMinispiel(
				positionMinigame(new Kontrolle(), spielbereich));

		Information[] sensoInfos = {
				new Information(Information.AENDERN_ZEIT,
						Information.ART_UM_WERT, -3)
		};
		casinoAktionen
				.add(new Aktion(
						"Senso",
						"(blue)Zeige deine Geschicklichkeit im Spiel \"Senso\"!",
						"Du warst im Kasino und hast dich am Spiel \"Senso\" versucht!",
						sensoInfos, null));
		casinoAktionen.get(1).setMinispiel(
				positionMinigame(new Senso(), spielbereich));

		Information[] kniffelInfos = {
				new Information(Information.AENDERN_ZEIT,
						Information.ART_UM_WERT, -3)
		};
		casinoAktionen
				.add(new Aktion(
						"W�rfelspiel",
						"(blue)Wirf 5 W�rfel und versuche W�rfelkombinationen zu bekommen. Hier kannst du sogar etwas gewinnen.",
						"Du hast ein W�rfelspiel gespielt.", kniffelInfos, null));
		casinoAktionen.get(2).setMinispiel(
				positionMinigame(new Kniffel(), spielbereich));

		Verzweigung casinoMenu = new Verzweigung("Casino",
				"Gehe ins Casino und spiele an diversen Automaten.",
				casinoAktionen, new ArrayList<Verzweigung>());

		Aktionsobjekt casino = new Aktionsobjekt("Casino aufsuchen", new Point(
				371, 43), new Dimension(84, 56), "casinoOn.png",
				"casinoOff.png", casinoMenu);
		return casino;
	}

	// kik, s.oliver, tommy hilfiger, gucci

	public static Aktionsobjekt getSchrank(int familienmitglieder) {
		ArrayList<Aktion> schrankAktionen = new ArrayList<Aktion>();

		Information[] kikInfos = {
				new Information(Information.AENDERN_ZEIT,
						Information.ART_UM_WERT, -2),
				new Information(Information.AENDERN_GELD,
						Information.ART_UM_WERT, -20),
				new Information(Information.AENDERN_SOZIALES,
						Information.ART_UM_WERT, +5),
				new Information(Information.AENDERN_LUXUS,
						Information.ART_UM_WERT, +15),
				new Information(Information.AENDERN_KINDER,
						Information.ART_UM_WERT, +5) };
		schrankAktionen.add(new Aktion("KiK",
				"(blue)Ein Kauf beim Textildiscounter sorgt f�r Kleidung.",
				"Du hast g�nstige Kleidung gekauft.", kikInfos, null));
		Information[] sOliverInfos = {
				new Information(Information.AENDERN_ZEIT,
						Information.ART_UM_WERT, -2),
				new Information(Information.AENDERN_GELD,
						Information.ART_UM_WERT, -40),
				new Information(Information.AENDERN_SOZIALES,
						Information.ART_UM_WERT, +15),
				new Information(Information.AENDERN_LUXUS,
						Information.ART_UM_WERT, +30),
				new Information(Information.AENDERN_KINDER,
						Information.ART_UM_WERT, +10) };
		schrankAktionen
				.add(new Aktion(
						"s.Oliver",
						"(blue)Mittelpreisige Kleidung ist nicht nur schick, sondern auch angesagt.",
						"Du hast mittelpreisige Kleidung gekauft.",
						sOliverInfos, null));
		Information[] tommyHilfigerInfos = {
				new Information(Information.AENDERN_ZEIT,
						Information.ART_UM_WERT, -2),
				new Information(Information.AENDERN_GELD,
						Information.ART_UM_WERT, -60),
				new Information(Information.AENDERN_SOZIALES,
						Information.ART_UM_WERT, +25),
				new Information(Information.AENDERN_LUXUS,
						Information.ART_UM_WERT, +45),
				new Information(Information.AENDERN_KINDER,
						Information.ART_UM_WERT, +15) };
		schrankAktionen
				.add(new Aktion(
						"Tommy Hilfiger",
						"(blue)Diese nicht ganz preiswerte Kleidung macht dich bekannt und beliebt.",
						"Du hast teure Kleidung gekauft.", tommyHilfigerInfos,
						null));
		Information[] gucciInfos = {
				new Information(Information.AENDERN_ZEIT,
						Information.ART_UM_WERT, -2),
				new Information(Information.AENDERN_GELD,
						Information.ART_UM_WERT, -120),
				new Information(Information.AENDERN_SOZIALES,
						Information.ART_UM_WERT, +55),
				new Information(Information.AENDERN_LUXUS,
						Information.ART_AUF_WERT, 100),
				new Information(Information.AENDERN_KINDER,
						Information.ART_UM_WERT, +30) };
		schrankAktionen
				.add(new Aktion(
						"Gucci",
						"(blue)Beste Kleidung aus den feinsten Stoffen zu horenden Preisen.",
						"Du hast Luxus-Kleidung gekauft. Du Verschwender!",
						gucciInfos, null));

		Verzweigung schrankMenu = new Verzweigung("Kleiderschrank",
				"Kaufe neue Kleidung und erf�ll dein Bed�rfnis nach Luxus!",
				schrankAktionen, new ArrayList<Verzweigung>());
		Aktionsobjekt schrank = new Aktionsobjekt("Kleidung kaufen", new Point(
				92, 45), new Dimension(137, 230), "shrankopen.png",
				"shrankclosed.png", schrankMenu);

		return schrank;
	}

	public static Aktionsobjekt getTV(int familienmitglieder) {
		ArrayList<Aktion> tvAktionen = new ArrayList<Aktion>();
		Information[] schauenInfos = {
				new Information(Information.AENDERN_ZEIT,
						Information.ART_UM_WERT, -2),
				new Information(Information.AENDERN_LUXUS,
						Information.ART_UM_WERT, 15),
				new Information(Information.AENDERN_KINDER,
						Information.ART_UM_WERT, 10),
				new Information(Information.AENDERN_BEWERBUNGSFAKTOR,
						Information.ART_UM_WERT, -1)
		};
		tvAktionen.add(new Aktion("Fernsehen",
				"(blue)Schaue ein wenig fern und f�hl dich gut! Bedenke: Fernsehen macht doof!",
				"Du hast ferngesehen.", schauenInfos, null));

		Information[] dvdInfos = {
				new Information(Information.AENDERN_ZEIT,
						Information.ART_UM_WERT, -3),
				new Information(Information.AENDERN_GELD,
						Information.ART_UM_WERT, -20),
				new Information(Information.AENDERN_NAHRUNG,
						Information.ART_UM_WERT, 5),
				new Information(Information.AENDERN_SOZIALES,
						Information.ART_UM_WERT, 10),
				new Information(Information.AENDERN_LUXUS,
						Information.ART_UM_WERT, 10) };
		tvAktionen.add(new Aktion("DVD-Abend",
				"(blue)Veranstalte einen DVD-Abend mit deinen Freunden!",
				"Du hast einen DVD-Abend veranstaltet.", dvdInfos, null));
		
		Information[] gymInfos = {
				new Information(Information.AENDERN_ZEIT,
						Information.ART_UM_WERT, -2),
				new Information(Information.AENDERN_GESUNDHEIT,
						Information.ART_UM_WERT, 15)
		};
		tvAktionen.add(new Aktion("Tele-GYM schauen",
				"(blue)Lass vor deinem TV die H�ften kreisen und tu nebenbei etwas f�r deine Figur.",
				"Du hast gymnastische �bungen gemacht.", gymInfos, null));

		Verzweigung tvMenu = new Verzweigung("Fernseher",
				"Hier kannst du Britt, DSDS und Hartz 4 TV gucken.",
				tvAktionen, new ArrayList<Verzweigung>());
		Aktionsobjekt tv = new Aktionsobjekt("TV anschalten",
				new Point(10, 257), new Dimension(79, 124), "tvopen.png",
				"tvclosed.png", tvMenu);

		return tv;
	}

	public static Aktionsobjekt getKuehlschrank(int familienmitglieder) {
		// Gem�se
		ArrayList<Aktion> gemueseAktionen = new ArrayList<Aktion>();
		Information gemueseInfos[] = new Information[4];
		gemueseInfos[0] = new Information(Information.AENDERN_ZEIT,
				Information.ART_UM_WERT, -1);
		gemueseInfos[1] = new Information(Information.AENDERN_GELD,
				Information.ART_UM_WERT, -30);
		gemueseInfos[2] = new Information(Information.AENDERN_NAHRUNG,
				Information.ART_UM_WERT, 20);
		gemueseInfos[3] = new Information(Information.AENDERN_GESUNDHEIT,
				Information.ART_UM_WERT, 15);
		gemueseAktionen
				.add(new Aktion("Hochwertig",
						"(blue)Kaufe hochwertiges Gem�se",
						"Du hast Qualit�tsgem�se gekauft.", gemueseInfos
								.clone(), null));
		gemueseInfos[0] = new Information(Information.AENDERN_ZEIT,
				Information.ART_UM_WERT, -1);
		gemueseInfos[1] = new Information(Information.AENDERN_GELD,
				Information.ART_UM_WERT, -20);
		gemueseInfos[2] = new Information(Information.AENDERN_NAHRUNG,
				Information.ART_UM_WERT, 15);
		gemueseInfos[3] = new Information(Information.AENDERN_GESUNDHEIT,
				Information.ART_UM_WERT, 10);
		gemueseAktionen.add(new Aktion("Mittelm��ig",
				"(blue)Kaufe mittelm��iges Gem�se",
				"Du hast mittelm��iges Gem�se gekauft.", gemueseInfos.clone(),
				null));
		gemueseInfos[0] = new Information(Information.AENDERN_ZEIT,
				Information.ART_UM_WERT, -1);
		gemueseInfos[1] = new Information(Information.AENDERN_GELD,
				Information.ART_UM_WERT, -15);
		gemueseInfos[2] = new Information(Information.AENDERN_NAHRUNG,
				Information.ART_UM_WERT, 10);
		gemueseInfos[3] = new Information(Information.AENDERN_GESUNDHEIT,
				Information.ART_UM_WERT, 5);
		gemueseAktionen
				.add(new Aktion("Billig", "(blue)Kaufe billiges Gem�se",
						"Du hast billiges Gem�se gekauft.", gemueseInfos
								.clone(), null));
		ArrayList<Verzweigung> kuehlschrankVerzweigung = new ArrayList<Verzweigung>();
		kuehlschrankVerzweigung
				.add(new Verzweigung(
						"Gem�se",
						"Gem�se erh�ht nicht nur deinen Nahrungsbalken, sondern auch deine Gesundheit. Allerdings kostet es daf�r auch relativ viel.",
						gemueseAktionen, new ArrayList<Verzweigung>()));

		// Fleisch
		ArrayList<Aktion> fleischAktionen = new ArrayList<Aktion>();
		Information fleischInfos[] = new Information[3];
		fleischInfos[0] = new Information(Information.AENDERN_ZEIT,
				Information.ART_UM_WERT, -1);
		fleischInfos[1] = new Information(Information.AENDERN_GELD,
				Information.ART_UM_WERT, -20);
		fleischInfos[2] = new Information(Information.AENDERN_NAHRUNG,
				Information.ART_UM_WERT, 30);
//		fleischInfos[3] = new Information(Information.AENDERN_GESUNDHEIT,
//				Information.ART_UM_WERT, 0);
		fleischAktionen
				.add(new Aktion("Hochwertig",
						"(blue)Kaufe hochwertiges Fleisch",
						"Du hast Qualit�tsfleisch gekauft.", fleischInfos
								.clone(), null));
		fleischInfos = new Information[4];
		fleischInfos[0] = new Information(Information.AENDERN_ZEIT,
				Information.ART_UM_WERT, -1);
		fleischInfos[1] = new Information(Information.AENDERN_GELD,
				Information.ART_UM_WERT, -15);
		fleischInfos[2] = new Information(Information.AENDERN_NAHRUNG,
				Information.ART_UM_WERT, 25);
		fleischInfos[3] = new Information(Information.AENDERN_GESUNDHEIT,
				Information.ART_UM_WERT, -5);
		fleischAktionen.add(new Aktion("Mittelm��ig",
				"(blue)Kaufe mittelm��iges Fleisch",
				"Du hast mittelm��iges Fleisch gekauft.", fleischInfos.clone(),
				null));
		fleischInfos[0] = new Information(Information.AENDERN_ZEIT,
				Information.ART_UM_WERT, -1);
		fleischInfos[1] = new Information(Information.AENDERN_GELD,
				Information.ART_UM_WERT, -10);
		fleischInfos[2] = new Information(Information.AENDERN_NAHRUNG,
				Information.ART_UM_WERT, 20);
		fleischInfos[3] = new Information(Information.AENDERN_GESUNDHEIT,
				Information.ART_UM_WERT, -10);
		fleischAktionen
				.add(new Aktion("Billig", "(blue)Kaufe billiges Fleisch",
						"Du hast billiges Fleisch gekauft.", fleischInfos
								.clone(), null));
		kuehlschrankVerzweigung
				.add(new Verzweigung(
						"Fleisch",
						"Fleisch macht extrem satt, ist aber auch recht teuer. Leider macht besonders billiges Fleisch auf Dauer fett und schadet somit der Gesundheit.",
						fleischAktionen, new ArrayList<Verzweigung>()));

		// Brot
		ArrayList<Aktion> brotAktionen = new ArrayList<Aktion>();
		Information brotInfos[] = new Information[3];
		brotInfos[0] = new Information(Information.AENDERN_ZEIT,
				Information.ART_UM_WERT, -1);
		brotInfos[1] = new Information(Information.AENDERN_GELD,
				Information.ART_UM_WERT, -15);
		brotInfos[2] = new Information(Information.AENDERN_NAHRUNG,
				Information.ART_UM_WERT, 20);
		brotAktionen.add(new Aktion("Hochwertig",
				"(blue)Kaufe hochwertiges Brot",
				"Du hast Qualit�tsbrot gekauft.", brotInfos.clone(), null));
		brotInfos[0] = new Information(Information.AENDERN_ZEIT,
				Information.ART_UM_WERT, -1);
		brotInfos[1] = new Information(Information.AENDERN_GELD,
				Information.ART_UM_WERT, -10);
		brotInfos[2] = new Information(Information.AENDERN_NAHRUNG,
				Information.ART_UM_WERT, 15);
		brotAktionen
				.add(new Aktion("Mittelm��ig",
						"(blue)Kaufe mittelm��iges Brot",
						"Du hast mittelm��iges Brot gekauft.", brotInfos
								.clone(), null));
		brotInfos[0] = new Information(Information.AENDERN_ZEIT,
				Information.ART_UM_WERT, -1);
		brotInfos[1] = new Information(Information.AENDERN_GELD,
				Information.ART_UM_WERT, -5);
		brotInfos[2] = new Information(Information.AENDERN_NAHRUNG,
				Information.ART_UM_WERT, 10);
		brotAktionen.add(new Aktion("Billig", "(blue)Kaufe billiges Brot",
				"Du hast billiges Brot gekauft.", brotInfos.clone(), null));
		kuehlschrankVerzweigung
				.add(new Verzweigung(
						"Brot",
						"Brot macht satt, hat aber sonst keinerlei Auswirkungen. Daf�r ist es g�nstiger als andere Nahrungsmittel.",
						brotAktionen, new ArrayList<Verzweigung>()));

		// Fast Food
		ArrayList<Aktion> ffoodAktionen = new ArrayList<Aktion>();
		Information ffoodInfos[] = new Information[4];
		ffoodInfos[0] = new Information(Information.AENDERN_ZEIT,
				Information.ART_UM_WERT, -1);
		ffoodInfos[1] = new Information(Information.AENDERN_GELD,
				Information.ART_UM_WERT, -9);
		ffoodInfos[2] = new Information(Information.AENDERN_NAHRUNG,
				Information.ART_UM_WERT, 30);
		ffoodInfos[3] = new Information(Information.AENDERN_GESUNDHEIT,
				Information.ART_UM_WERT, -30);
		ffoodAktionen.add(new Aktion("Maximen�",
				"(blue)Der Koloss unter den Burgern",
				"Du hast ein Maximen� gekauft.", ffoodInfos.clone(), null));
		ffoodInfos[0] = new Information(Information.AENDERN_ZEIT,
				Information.ART_UM_WERT, -1);
		ffoodInfos[1] = new Information(Information.AENDERN_GELD,
				Information.ART_UM_WERT, -6);
		ffoodInfos[2] = new Information(Information.AENDERN_NAHRUNG,
				Information.ART_UM_WERT, 20);
		ffoodInfos[3] = new Information(Information.AENDERN_GESUNDHEIT,
				Information.ART_UM_WERT, -20);
		ffoodAktionen.add(new Aktion("Sparmen�",
				"(blue)Ein ansehnlicher Haufen Fleisch",
				"Du hast mittelm��iges Fleisch gekauft.", ffoodInfos.clone(),
				null));
		ffoodInfos[0] = new Information(Information.AENDERN_ZEIT,
				Information.ART_UM_WERT, -1);
		ffoodInfos[1] = new Information(Information.AENDERN_GELD,
				Information.ART_UM_WERT, -3);
		ffoodInfos[2] = new Information(Information.AENDERN_NAHRUNG,
				Information.ART_UM_WERT, 10);
		ffoodInfos[3] = new Information(Information.AENDERN_GESUNDHEIT,
				Information.ART_UM_WERT, -10);
		ffoodAktionen.add(new Aktion("ein paar Burger",
				"(blue)3 pappige Burger", "Du hast billiges Fleisch gekauft.",
				ffoodInfos.clone(), null));
		kuehlschrankVerzweigung
				.add(new Verzweigung(
						"Fast Food",
						"Bet�rend, billig, b�se: Fast Food macht f�r wenig Geld erstaunlich satt. Doch die, von Geschmacksverst�rkern durchzogenen Nahrungsmittel, schaden der Gesundheit extrem.",
						ffoodAktionen, new ArrayList<Verzweigung>()));

		Verzweigung kuehlschrankMenu = new Verzweigung("K�hlschrank",
				"Hier kannst du Lebensmittel einkaufen.",
				new ArrayList<Aktion>(), kuehlschrankVerzweigung);
		Aktionsobjekt kuehlschrank = new Aktionsobjekt("Essen kaufen",
				new Point(522, 85), new Dimension(144, 241), "fridgeopen.png",
				"fridgeclosed.png", kuehlschrankMenu);
		return kuehlschrank;
	}

	public static Aktionsobjekt getTuer(Spielbereich spielbereich, int familienmitglieder) {
		ArrayList<Aktion> t�rAktionen = new ArrayList<Aktion>();
		ArrayList<Aktion> kulturAktionen = new ArrayList<Aktion>();
		ArrayList<Verzweigung> t�rVerzweigungen = new ArrayList<Verzweigung>();
		
		Information[] kinoBesuchen = {
				new Information(Information.AENDERN_ZEIT,
						Information.ART_UM_WERT, -2),
				new Information(Information.AENDERN_GELD,
						Information.ART_UM_WERT, -15),
				new Information(Information.AENDERN_SOZIALES,
						Information.ART_UM_WERT, 5),
				new Information(Information.AENDERN_LUXUS,
						Information.ART_UM_WERT, 15),
				new Information(Information.AENDERN_KINDER,
						Information.ART_UM_WERT, 15) };
		kulturAktionen
				.add(new Aktion(
						"Kino",
						"(blue)Geh ins Kino und verbringe eine sch�ne Zeit mit deinen Freunden.",
						"Du bist ins Kino gegangen", kinoBesuchen, null));
		
		Information[] konzertBesuchen = {
				new Information(Information.AENDERN_ZEIT,
						Information.ART_UM_WERT, -3),
				new Information(Information.AENDERN_GELD,
						Information.ART_UM_WERT, -30),
				new Information(Information.AENDERN_SOZIALES,
						Information.ART_UM_WERT, 7),
				new Information(Information.AENDERN_LUXUS,
						Information.ART_UM_WERT, 22),
				new Information(Information.AENDERN_KINDER,
						Information.ART_UM_WERT, 12)
		};
		kulturAktionen.add(new Aktion("Konzert",
				"(blue)Schau deinem Lieblingsmusiker beim Musizieren zu.",
				"Du hast ein Konzert besucht.", konzertBesuchen, null));

		Information[] theaterBesuchen = {
				new Information(Information.AENDERN_ZEIT,
						Information.ART_UM_WERT, -3),
				new Information(Information.AENDERN_GELD,
						Information.ART_UM_WERT, -55),
				new Information(Information.AENDERN_SOZIALES,
						Information.ART_UM_WERT, 10),
				new Information(Information.AENDERN_LUXUS,
						Information.ART_UM_WERT, 30),
				new Information(Information.AENDERN_KINDER,
						Information.ART_UM_WERT, 10) };
		kulturAktionen.add(new Aktion("Theater",
				"(blue)Besuche ein Theater und genie�e die Kultur.",
				"Du bist ins Theater gegangen.", theaterBesuchen, null));
		
		Verzweigung kultur = new Verzweigung("Kultur",
				"Genie�e ein St�ck Kultur und vergiss den Alltag.",
				kulturAktionen, new ArrayList<Verzweigung>());
		t�rVerzweigungen.add(kultur);

		Information[] freundeBesuchen = {
				new Information(Information.AENDERN_ZEIT,
						Information.ART_UM_WERT, -3)
		};
		t�rAktionen
				.add(new Aktion(
						"Freunde besuchen",
						"(blue)Besuche deine Freunde und spiele mit ihene Tic Tac Toe.\nSoziales steigt um 5 pro Runde!",
						"Du hast deine Freunde besucht.", freundeBesuchen, null));
		t�rAktionen.get(0).setMinispiel(
				positionMinigame(new TicTacToe(), spielbereich));
		
		Information[] parkBesuchen = {
				new Information(Information.AENDERN_ZEIT,
						Information.ART_UM_WERT, -3),
				new Information(Information.AENDERN_GESUNDHEIT,
						Information.ART_UM_WERT, 10),
				new Information(Information.AENDERN_SOZIALES,
						Information.ART_UM_WERT, 20),
				new Information(Information.AENDERN_KINDER,
						Information.ART_UM_WERT, 20)};
		t�rAktionen.add(new Aktion("Park",
				"(blue)Geh in den Park und triff ein paar nette Leute.",
				"Du bist in den Park gegangen.", parkBesuchen, null));
		
		Information[] arztBesuchen = {
				new Information(Information.AENDERN_ZEIT,
						Information.ART_UM_WERT, -4),
				new Information(Information.AENDERN_GELD,
						Information.ART_UM_WERT, -10),
				new Information(Information.AENDERN_GESUNDHEIT,
						Information.ART_UM_WERT, 50)
		};
		t�rAktionen.add(new Aktion("Arzt",
				"(blue)Besuch den Arzt, zahle Praxisgeb�hren und setz dich in's Wartezimmer.",
				"Du bist zum Arzt gegangen.", arztBesuchen, null));

		Verzweigung tuerMenu = new Verzweigung(
				"T�r",
				"Hier kannst du Aktivit�ten au�erhalb deiner Wohnung ausw�hlen",
				t�rAktionen, t�rVerzweigungen);
		Aktionsobjekt tuer = new Aktionsobjekt("Wohnung verlassen", new Point(
				687, 90), new Dimension(81, 340), "dooropen.png",
				"doorclosed.png", tuerMenu);
		return tuer;
	}

	public static Aktionsobjekt getZeitung(Spielbereich spielbereich, int familienmitglieder) {
		ArrayList<Aktion> zeitungsAktionen = new ArrayList<Aktion>();
		Information[] zeitungLesen = {
				new Information(Information.AENDERN_ZEIT,
						Information.ART_UM_WERT, -2),
				new Information(Information.AENDERN_BEWERBUNGSFAKTOR,
						Information.ART_UM_WERT, 5) };
		zeitungsAktionen
				.add(new Aktion(
						"Lesen",
						"(blue)Lesen bildet! Verbessere deine Allgemeinbildung und somit deine Chance, in einem Bewerbungsgespr�ch angenommen zu werden.",
						"Du hast die Zeitung durchgelesen.", zeitungLesen, null));

		Information[] bewerbenInfos = { new Information(
				Information.AENDERN_ZEIT, Information.ART_UM_WERT, -5), };
		zeitungsAktionen
				.add(new Aktion(
						"Bewerben",
						"(blue)Bewirb dich auf einen 400�-Job und versuch dich am Einstellungsverfahren.",
						"Du hast dich auf einen Job beworben.", bewerbenInfos,
						null));
		zeitungsAktionen.get(1).setMinispiel(
				positionMinigame(new Bewerbungsflaeche(), spielbereich));

		Information[] kreuzwortInfos = { new Information(
				Information.AENDERN_ZEIT, Information.ART_UM_WERT, -3) };
		zeitungsAktionen
				.add(new Aktion(
						"Kreuzwortr�tsel",
						"(blue)L�se ein Kreuzwortr�tsel und gewinne eine Tagesfahrt nach Bad M�nster Eifel.",
						"Du hast versucht, ein Kreuzwortr�tsel zu l�sen.",
						kreuzwortInfos, null));
		zeitungsAktionen.get(2).setMinispiel(
				positionMinigame(new KreuzGUI(), spielbereich));
		
		Information[] sudokuInfos = {
				new Information(Information.AENDERN_ZEIT, Information.ART_UM_WERT, -3)
		};
		zeitungsAktionen.add(new Aktion("Sudoku l�sen",
				"(blue)Versuche ein Sudoku zu l�sen und dabei noch etwas zu gewinnen.",
				"Du hast versucht, ein Sudoku zu l�sen.",
				sudokuInfos, null));
		zeitungsAktionen.get(3).setMinispiel(positionMinigame(new SudokuFenster1(), spielbereich));

		ArrayList<Verzweigung> zeitungsVerzweigungen = new ArrayList<Verzweigung>();
		Verzweigung zeitungsMenu = new Verzweigung(
				"Zeitung",
				"Hier kannst du die Zeitung lesen, Kreuzwortr�tsel l�sen und vieles mehr.",
				zeitungsAktionen, zeitungsVerzweigungen);

		Aktionsobjekt zeitung = new Aktionsobjekt("Zeitung lesen", new Point(
				100, 340), new Dimension(125, 64), "newspaperopen.png",
				"newspaperclosed.png", zeitungsMenu);
		return zeitung;
	}

	public static Aktionsobjekt getCheat() {
		// Aktionen
		ArrayList<Aktion> cheatAktionen = new ArrayList<Aktion>();

		// Bed�rfnisse f�llen
		Information[] nutzBed = {
				new Information(Information.AENDERN_NAHRUNG,
						Information.ART_AUF_WERT, 100),
				new Information(Information.AENDERN_GESUNDHEIT,
						Information.ART_AUF_WERT, 100),
				new Information(Information.AENDERN_SOZIALES,
						Information.ART_AUF_WERT, 100),
				new Information(Information.AENDERN_LUXUS,
						Information.ART_AUF_WERT, 100),
				new Information(Information.AENDERN_KINDER,
						Information.ART_AUF_WERT, 100) };
		cheatAktionen.add(new Aktion("Bed�rfnisse f�llen",
				"(blue)F�lle alle Bed�rfnisse auf das Maximum auf!",
				"Du hast alle Bed�rfnisse auf das Maximum aufgef�llt!", null,
				nutzBed));

		// Geld um 1.000 Euro erh�hen
		Information[] nutzGeld = { new Information(Information.AENDERN_GELD,
				Information.ART_UM_WERT, 1000), };
		cheatAktionen.add(new Aktion("Geld verdienen",
				"(blue)Verdiene 1.000�! Ganz ohne Arbeit!",
				"Du hast ganz ohne Arbeit 1.000� verdient!", null, nutzGeld));

		// Zeit um 5 erh�hen
		Information[] nutzZeit = { new Information(Information.AENDERN_ZEIT,
				Information.ART_UM_WERT, 5) };
		cheatAktionen
				.add(new Aktion(
						"Zeit sparen",
						"(blue)Spare Zeit, indem du hier ganz einfach Cheatest!",
						"Du hast soviel Zeit gespart, dass du jetzt eine Aktion mehr hast!",
						null, nutzZeit));

		// Verzweigungen
		ArrayList<Verzweigung> cheatVerzweigungen = new ArrayList<Verzweigung>();

		// Men�
		Verzweigung cheatMenu = new Verzweigung(
				"Cheaten",
				"Auch als HartzIV-Empf�nger kannst du manchmal Chuck Norris spielen!",
				cheatAktionen, cheatVerzweigungen);

		Aktionsobjekt cheat = new Aktionsobjekt("", new Point(0, 498),
				new Dimension(10, 10), "", "", cheatMenu);
		return cheat;
	}

	private static Minispiel positionMinigame(Minispiel minispiel,
			Spielbereich spielber) {
		Point newLocation = SpielAnwendung.getHauptfenster().getLocation();
		newLocation.setLocation(newLocation.getX()
				+ spielber.getLocation().getX(), newLocation.getY()
				+ spielber.getLocation().getY());
		newLocation
				.setLocation(
						(int) (newLocation.getLocation().getX() + (20 + (800 - minispiel
								.getSize().getWidth()) / 2)),
						(int) (newLocation.getLocation().getY() + (60 + (500 - minispiel
								.getSize().getHeight() + 50) / 2)));
		minispiel.setLocation(newLocation);
		return minispiel;
	}
}
