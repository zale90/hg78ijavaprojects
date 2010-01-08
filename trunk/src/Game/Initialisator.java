package Game;

import java.util.*;

public class Initialisator 
{
	
	/*
	 * gibt die Avatare zurück, die irgendwo gespeichert werden müssen (evtl. launcher?)
	 * Werte müssen natürlich noch angepasst werden!
	 */
	public static ArrayList<Avatar> gibAvatare()
	{
		ArrayList<Avatar> avList = new ArrayList<Avatar>();
		Bedürfnis[] bedList;
		
		// Horst Terano
		bedList = new Bedürfnis[4];		
		bedList[0] = new Bedürfnis(0, 50, 0, 100, 25);  // Nahrung
		bedList[1] = new Bedürfnis(1, 50, 0, 100, 25);  // Gesundheit
		bedList[2] = new Bedürfnis(2, 50, 0, 100, 25);  // Soziales
		bedList[3] = new Bedürfnis(3, 50, 0, 100, 25);  // Luxus
		avList.add(new Avatar(1, "Horst Terarno", "Horst ist ein 35-jähriger, kinder- und arbeitsloser Akademiker. Er hat Immobilienmanagement studiert und danach bei Traumhaus.de gearbeitet. Aufgrund der Wirtschaftskrise und gescheiterter Aktienspekulationen verlor er seinen Arbeitsplatz und seine Rücklagen schrumpften auf 1500,-€. Trotz Hartz IV versucht er seinen Lebensstandard zu halten.", bedList, 1500, 500, 5));
		
		// Hasma Hamada
		bedList = new Bedürfnis[5];		
		bedList[0] = new Bedürfnis(0, 50, 0, 100, 25);  // Nahrung
		bedList[1] = new Bedürfnis(1, 50, 0, 100, 25);  // Gesundheit
		bedList[2] = new Bedürfnis(2, 50, 0, 100, 25);  // Soziales
		bedList[3] = new Bedürfnis(3, 50, 0, 100, 25);  // Luxus
		//bedList[4] = new Bedürfnis(4, 50, 0, 100, 25);  // Kinder
		avList.add(new Avatar(2, "Hasma Hamada", "Hasma ist 40 Jahre alt, verheiratet und ist Vater von 3 Kindern(9, 10 und 12 Jahre). Er ist gelernter Schlosser, raucht und ist für jeden Snack zu haben. Er pflegt seine Freundschaften und liebt seine Kinder. Aufgrund von Rationalisierung und bescheidenen  Qualifikationen wurde ihm bei Wenner Metallbau gekündigt. Seine Frau trägt mit einem 400€ Job zur Haushaltskasse bei, trotzdem können sie nur Rücklagen in Höhe von 130,-€ verbuchen.", bedList, 130, 200, 2));
		
		// Chantal-Jacqueline Chaves
		bedList = new Bedürfnis[4];		
		bedList[0] = new Bedürfnis(0, 50, 0, 100, 25);  // Nahrung
		bedList[1] = new Bedürfnis(1, 50, 0, 100, 25);  // Gesundheit
		bedList[2] = new Bedürfnis(2, 50, 0, 100, 25);  // Soziales
		bedList[3] = new Bedürfnis(3, 50, 0, 100, 25);  // Luxus
		avList.add(new Avatar(3, "Chantal-Jacqueline Chaves", "Chantal ist eine 20- jährige Mutter eines 3- jährigen Mädchens und erneut im fünften Monat schwanger. Den Kontakt zu ihren Eltern, sowie zu den Vätern ihrer Kinder hat sie abgebrochen. Ihre Frisör - Ausbildung hat sie aufgrund der Schwangerschaft abgebrochen und hat deswegen keine Rücklage und knabbert an einer monatlichen Rate von 30,-€ für diverse Haushaltsgeräte.", bedList, 0, 300, 3));
		
		return avList;
	}
	
	public static ArrayList<Ereignis> gibEreignisse() {
		ArrayList<Ereignis> erList = new ArrayList<Ereignis>();
		
		Ereignis e;
		Information[] ja, nein;
		Random zufall = new Random();
		
		// Ereignisdaten
		ja = new Information[1];
		ja[0] = new Information(13, 2, 50);
		e = new Ereignis(1, "Lottogewinn", "Sie haben 4 Richtige und daher im Lotto 50 € gewonnen. Gehen Sie sparsam damit um!", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, -10);
		nein = new Information[1];
		nein[0] = new Information(3, 4, -50);
		e = new Ereignis(2, "Krankheit", "Sie haben Atemschwierigkeiten und starkes Fieber und können Ihren Arzt besuchen. Dabei fallen 10€ Praxisgebühren an. Gehen Sie nicht zum Arzt leiden Ihre Bedürfnisse darunter. Möchten Sie den Arzt besuchen?", true, ja, nein);
		erList.add(e);
		
		ja = new Information[2];
		ja[0] = new Information(5, 2, 25);
		ja[1] = new Information(7, 2, 35);
		e = new Ereignis(3, "Kino", "Natasha lädt dich ins Kino ein. Dadurch wird dein Bedürfnis an Luxus und Sozialem gesichert. ", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, -40);
		e = new Ereignis(4, "Diebstahl", "In dein Haus wurde eingebrochen. Dabei wurden dir 40 € entwendet. ", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(1, 1, 0);
		e = new Ereignis(5, "Kühlschrank im Arsch", "Während du in der Stadt warst hat dein Kühlschrank den Geist aufgegeben. Du kannst ihn zwar reparieren, jedoch sind alle Lebensmittel verdorben. ", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, 50);
		e = new Ereignis(6, "Steuerrückzahlung", "Sie haben im letzten Jahr zu viele Steuern gezahlt und erhalten daher jetzt eine Rückzahlung in Höhe von 50€. ", false, ja, null);
		erList.add(e);
		
		//===========================
		//Was machen wir mit den Kinderereignissen? Bislang haben wir kein Kriterium,
		//um diese von anderen Ereignisen abgrenzen zu können...
		//Ich bin dafür, Kinderereignisse komplett zu entfernen.
		//===========================
		ja = new Information[1];
		ja[0] = new Information(13, 2, -70);
		e = new Ereignis(7, "Schulausflug", "Dein(e) Kind(er) machen einen Ausflug nach Hamburg. Zahle 70€ für die Fahrt. ", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, 100);
		e = new Ereignis(8, "Fund", "Du findest in der Stadt 100€ auf dem Boden. Du bist mega happy. ", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, -10);
		e = new Ereignis(9, "Ohne Licht gefahren", "Während du mit deinem Fahrrad ohne Licht unterwegs warst, wurdest du erwischt und mußt 10 € Strafe zahlen. ", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, 10);
		e = new Ereignis(10, "Pfand erhalten", "Du bringst deine gesammelten Pfandflaschen zurück und erhälst 10 €. ", false, ja, null);
		erList.add(e);
		
		nein = new Information[2];
		nein[0] = new Information(13, 2, 12);
		nein[1] = new Information(5, 4, -60);
		e = new Ereignis(11, "Rückgeld", "Die Kassiererin bei kik gibt Ihnen 12 € zu viel zurück. Sie könnten das Geld einstecken, doch das würde ihr soziales Ansehen senken. Geben Sie das Geld zurück?", true, null, nein);
		erList.add(e);
		
		ja = new Information[2];
		ja[0] = new Information(13, 2, -400);
		ja[1] = new Information(12, 2, 1);
		nein = new Information[1];
		nein[0] = new Information(13, 2, -20);
		e = new Ereignis(12, "Waschmaschine defekt", "Ihre Waschmaschiene scheint defekt zu sein. Zwar könnte ein Mechaniker das alte Gerät für 20 € reparieren, doch mit einer neuen, 400 Euro teuren Waschmaschine sparen Sie viel Zeit und haben somit einen Zug mehr pro Runde. Kaufen Sie die neue Maschine?", true, ja, nein);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, -8);
		e = new Ereignis(13, "Fahrradreifen defekt", "Während einer Radtour ist ihr Fahrradreifen geplatzt. An einer nahegelegenen Tankstelle können sie Flickzeug besorgen. Es kostet sie 8 €. ", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		if(zufall.nextInt(2) == 0)
			ja[0] = new Information(13, 2, 75);
		else
			ja[0] = new Information(13, 4, -15);
		e = new Ereignis(14, "Gericht", "Sie wurden von einem Reichen angepöbelt. Wollen sie Schadensersatz fordern? Obwohl Geld winkt, müssen sie bei einer Niederlage des Prozesses einen Teil der Gerichtskosten übernehmen. ", true, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, 70);
		e = new Ereignis(15, "Erbfall", "Ein Onkel ist verstorben. Sein Vermächtnis beträgt 70 Euro. ", false, ja, null);
		erList.add(e);
		
		ja = new Information[2];
		ja[0] = new Information(5, 4, 15);
		ja[1] = new Information(7, 2, 25);
		e = new Ereignis(16, "Preisausschreiben", "Sie haben bei einem Preisausschreiben gewonnen! Sie erhalten Freikarten für das Kino und ein Museum. ", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, 30);
		nein = new Information[1];
		nein[0] = new Information(5, 4, -75);
		e = new Ereignis(17, "Ausflug", "Einer deiner Freunde fragt, ob du an einem Ausflug teilnehmen willst. Du müsstest 30 Euro bezahlen, andernfalls sinkt dein soziales Bedürfnis. Nimmst du teil?", true, ja, nein);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, -15);
		e = new Ereignis(18, "Handy", "Eine Handyabrechnung wurde nicht beglichen, zahlen sie 15 Euro Strafe. ", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(1, 3, 100);
		e = new Ereignis(19, "Essen bei Mutter", "Deine Mutter hat dich am Wochenende zum Essen eingeladen!", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, 70);
		nein = new Information[1];
		nein[0] = new Information(5, 1, 0);
		e = new Ereignis(20, "Todesfall", "Dein Onkel ist plötzlich verstorben. Der Rest deiner Verwandschaft schlägt vor, dass du dich mit 70€ an der Beerdigung beteiligst. Bist du einverstanden? Wenn nicht, sinkt dein Ansehen.", true, ja, nein);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(1, 1, 100);
		e = new Ereignis(21, "Nahrungsaufschub", "Dein Vater bringt dir eine Ladung Äpfel aus seinem Garten mit und füllt damit deinen Nahrungsvorrat auf. ", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(7, 4, 50);
		e = new Ereignis(22, "Europafest", "Du besuchst das Europafest zum Thema \"Armut und Ausgrenzung\" und spielst ein tolles Spiel. Das Bedürfnis nach Luxus sinkt. ", false, ja, null);
		erList.add(e);
		
		ja = new Information[1];
		ja[0] = new Information(13, 2, -10);
		e = new Ereignis(23, "McDonalds ruft an", "McDonalds ruft an: Deine Mutter ist in der Rutsche stecken geblieben. Du musst 10€ für Fett zahlen um sie wieder heraus zu holen. ", false, ja, null);
		erList.add(e);
		
		return erList;
	}
}
