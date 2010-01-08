package Game;

import java.util.*;

public class Initialisator 
{
	
	/*
	 * gibt die Avatare zur�ck, die irgendwo gespeichert werden m�ssen (evtl. launcher?)
	 * Werte m�ssen nat�rlich noch angepasst werden!
	 */
	public static ArrayList<Avatar> gibAvatare()
	{
		ArrayList<Avatar> avList = new ArrayList<Avatar>();
		Bed�rfnis[] bedList;
		
		// Horst Terano
		bedList = new Bed�rfnis[4];		
		bedList[0] = new Bed�rfnis(0, 50, 0, 100, 25);  // Nahrung
		bedList[1] = new Bed�rfnis(1, 50, 0, 100, 25);  // Gesundheit
		bedList[2] = new Bed�rfnis(2, 50, 0, 100, 25);  // Soziales
		bedList[3] = new Bed�rfnis(3, 50, 0, 100, 25);  // Luxus
		avList.add(new Avatar(1, "Horst Terarno", "Horst ist ein 35-j�hriger, kinder- und arbeitsloser Akademiker. Er hat Immobilienmanagement studiert und danach bei Traumhaus.de gearbeitet. Aufgrund der Wirtschaftskrise und gescheiterter Aktienspekulationen verlor er seinen Arbeitsplatz und seine R�cklagen schrumpften auf 1500,-�. Trotz Hartz IV versucht er seinen Lebensstandard zu halten.", bedList, 1500, 500, 5));
		
		// Hasma Hamada
		bedList = new Bed�rfnis[5];		
		bedList[0] = new Bed�rfnis(0, 50, 0, 100, 25);  // Nahrung
		bedList[1] = new Bed�rfnis(1, 50, 0, 100, 25);  // Gesundheit
		bedList[2] = new Bed�rfnis(2, 50, 0, 100, 25);  // Soziales
		bedList[3] = new Bed�rfnis(3, 50, 0, 100, 25);  // Luxus
		//bedList[4] = new Bed�rfnis(4, 50, 0, 100, 25);  // Kinder
		avList.add(new Avatar(2, "Hasma Hamada", "Hasma ist 40 Jahre alt, verheiratet und ist Vater von 3 Kindern(9, 10 und 12 Jahre). Er ist gelernter Schlosser, raucht und ist f�r jeden Snack zu haben. Er pflegt seine Freundschaften und liebt seine Kinder. Aufgrund von Rationalisierung und bescheidenen  Qualifikationen wurde ihm bei Wenner Metallbau gek�ndigt. Seine Frau tr�gt mit einem 400� Job zur Haushaltskasse bei, trotzdem k�nnen sie nur R�cklagen in H�he von 130,-� verbuchen.", bedList, 130, 200, 2));
		
		// Chantal-Jacqueline Chaves
		bedList = new Bed�rfnis[4];		
		bedList[0] = new Bed�rfnis(0, 50, 0, 100, 25);  // Nahrung
		bedList[1] = new Bed�rfnis(1, 50, 0, 100, 25);  // Gesundheit
		bedList[2] = new Bed�rfnis(2, 50, 0, 100, 25);  // Soziales
		bedList[3] = new Bed�rfnis(3, 50, 0, 100, 25);  // Luxus
		avList.add(new Avatar(3, "Chantal-Jacqueline Chaves", "Chantal ist eine 20- j�hrige Mutter eines 3- j�hrigen M�dchens und erneut im f�nften Monat schwanger. Den Kontakt zu ihren Eltern, sowie zu den V�tern ihrer Kinder hat sie abgebrochen. Ihre Fris�r - Ausbildung hat sie aufgrund der Schwangerschaft abgebrochen und hat deswegen keine R�cklage und knabbert an einer monatlichen Rate von 30,-� f�r diverse Haushaltsger�te.", bedList, 0, 300, 3));
		
		return avList;
	}
	
	public static ArrayList<Ereignis> gibEreignisse() {
		ArrayList<Ereignis> erList = new ArrayList<Ereignis>();
		
		Ereignis e;
		Information[] ja, nein;
		
		// Ereignisdaten
		ja = new Information[1];
		ja[0] = new Information(13, 2, 50);
		e = new Ereignis(1, "Lottogewinn", "Sie haben 4 Richtige und daher im Lotto 50 � gewonnen. Gehen Sie sparsam damit um!", false, null, null);
		erList.add(e);
		
		e = new Ereignis(2, "Krankheit", "Sie haben Atemschwierigkeiten und starkes Fieber und k�nnen Ihren Arzt besuchen. Dabei fallen 10� Praxisgeb�hren an. Gehen Sie nicht zum Arzt leiden Ihre Bed�rfnisse darunter. M�chten Sie zum Arzt gehen?", true, null, null);
		erList.add(e);
		
		e = new Ereignis(3, "Kino", "Natasha l�dt dich ins Kino ein. Dadurch wird dein Bed�rfnis an Kultur und Sozialem gesichert.", false, null, null);
		erList.add(e);
		
		e = new Ereignis(4, "Diebstahl", "In dein Haus wurde eingebrochen. Dabei wurden dir 40 � entwendet.", false, null, null);
		erList.add(e);
		
		e = new Ereignis(5, "K�hlschrank im Arsch", "W�hrend du in der Stadt warst hat dein K�hlschrank den Geist aufgegeben. Du kannst ihn zwar reparieren, jedoch sind alle Lebensmittel verdorben. M�chtest du den K�hlschrank jetzt reparieren?", true, null, null);
		erList.add(e);
		
		e = new Ereignis(6, "Steuerr�ckzahlung", "Sie haben im letzten Jahr zu viele Steuern gezahlt und erhalten daher jetzt eine R�ckzahlung in H�he von 50�. ", false, null, null);
		erList.add(e);
		
		e = new Ereignis(7, "Schulausflug", "Dein(e) Kind(er) machen einen Ausflug nach Hamburg. Zahle 70� f�r die Fahrt. ", false, null, null);
		erList.add(e);
		
		e = new Ereignis(8, "Fund", "Du findest in der Stadt 100� auf dem Boden. Du bist mega happy. ", false, null, null);
		erList.add(e);
		
		e = new Ereignis(9, "Ohne Licht gefahren", "W�hrend du mit deinem Fahrrad ohne Licht unterwegs warst, wurdest du erwischt und mu�t 10 � Strafe zahlen. ", false, null, null);
		erList.add(e);
		
		e = new Ereignis(10, "Pfand erhalten", "Du bringst deine gesammelten Pfandflaschen zur�ck und erh�lst 10 �. ", false, null, null);
		erList.add(e);
		
		e = new Ereignis(11, "R�ckgeld", "Die Kassiererin bei kik gibt Ihnen 10 � zu viel zur�ck. Geben Sie das Geld zur�ck oder stecken Sie es unauff�llig ein? ", false, null, null);
		erList.add(e);
		
		e = new Ereignis(12, "Waschmaschine defekt", "Ihre Waschmaschiene scheint defekt zu sein. M�chten Sie einen Mechaniker anrufen um diese reparieren zu lassen oder m�chten Sie sich direkt ein neuesstromsparenderes Modell kaufen?  ", false, null, null);
		erList.add(e);
		
		e = new Ereignis(13, "Fahrradreifen defekt", "W�hrend einer Radtour ist ihr Fahrradreifen geplatzt. An einer nahegelegenen Tankstelle k�nnen sie Flickzeug besorgen. Es kostet sie 8 �. ", false, null, null);
		erList.add(e);
		
		e = new Ereignis(14, "Gericht", "Sie wurden von einem Reichen angep�belt. Wollen sie Schadensersatz fordern? Falls ja, m�ssen sie bei einer Niederlage des Prozesses die Gerichtskosten �bernehmen. ", false, null, null);
		erList.add(e);
		
		e = new Ereignis(15, "Erbfall", "Ein Onkel ist verstorben. Sein Verm�chtnis betr�gt 70 Euro. ", false, null, null);
		erList.add(e);
		
		e = new Ereignis(16, "Kreuzwortr�tsel", "Sie haben bei einem Kreuzwortr�tsel gewonnen! Sie erhalten Freikarten f�r das Kino und ein Museum. ", false, null, null);
		erList.add(e);
		
		e = new Ereignis(17, "Ausflug", "Einer deiner Freunde fragt, ob du an einem Ausflug teilnehmen willst. Du m�sstest 30 Euro bezahlen, andernfalls sinkt dein Soziales. ", false, null, null);
		erList.add(e);
		
		e = new Ereignis(18, "Handy", "Eine Handyabrechnung wurde nicht beglichen, zahlen sie 15 Euro Strafe. ", false, null, null);
		erList.add(e);
		
		e = new Ereignis(19, "Essen bei Mutter", "Deine Mutter hat dich am Wochenende zum Essen eingeladen! ", false, null, null);
		erList.add(e);
		
		e = new Ereignis(20, "Todesfall", "Dein Onkel ist pl�tzlich verstorben. Der Rest deiner Verwandschaft schl�gt vor, dass du dich mit 70� an der Beerdigung beteiligst. Bist du einverstanden? ", false, null, null);
		erList.add(e);
		
		e = new Ereignis(21, "Nahrungsaufschub", "Dein Vater bringt dir eine Ladung �pfel aus seinem Garten mit und f�llt damit deinen Nahrungsvorrat auf. ", false, null, null);
		erList.add(e);
		
		e = new Ereignis(22, "Europafest", "Du besuchst das Europafest zum Thema \"Armut und Ausgrenzung\" und spielst ein tolles Spiel. Das Bed�rfnis nach Kultur sinkt. ", false, null, null);
		erList.add(e);
		
		e = new Ereignis(23, "McDonalds ruft an", "McDonalds ruft an: Deine Mutter ist in der Rutsche stecken geblieben. Du musst 10� f�r Fett zahlen um sie wieder heraus zu holen. ", false, null, null);
		erList.add(e);
		
		return erList;
	}
}