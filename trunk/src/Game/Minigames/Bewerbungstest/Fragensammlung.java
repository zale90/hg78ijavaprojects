package Game.Minigames.Bewerbungstest;

import java.util.*;
public class Fragensammlung
{

  // Anfang Attribute
       private ArrayList<Frage> bewerbung;
       private Random r;
  // Ende Attribute

       public Fragensammlung(){
          bewerbung = new ArrayList<Frage>();
          r = new Random();
       bewerbung.add(new Frage("Aus welchem Grund überschulden sich private Haushalte am häufigsten?","1.) Arbeitslosigkeit","2.)Trennung, Scheidung, Tod","3.) gescheiterte Selbstständigkeit","4.) gescheiterte Immobilienfinanzierung",1));
       bewerbung.add(new Frage("Welches Wort passt nicht zu den anderen ?","1.) Nadel ","2.)Messer","3.) Schere","4.)Säge",1));
       bewerbung.add(new Frage("Welches Wort passt nicht zu den anderen ?","1.) Weg ","2.) Treppe","3.) Straße","4.)Ausgang",4));
       bewerbung.add(new Frage("Welches Wort passt nicht zu den anderen ?","1.) hämmern","2.) stricken","3.) backen","4.) unterrichten",4));
       bewerbung.add(new Frage("Welches Wort passt nicht zu den anderen ?","1.) Ebene","2.) Berg","3.) Schlucht","4.) Garten",4));
       bewerbung.add(new Frage("Welches Wort passt nicht zu den anderen ?","1.) Fälschung ","2.) Unfall","3.) Versehen","4.) Irrtum",1));
       bewerbung.add(new Frage("Welches Wort passt nicht zu den anderen ?","1.) Blaumann ","2.) Uniform","3.) Kostüm","4.) Kampfanzug",3));
       bewerbung.add(new Frage("Welches Wort passt nicht zu den anderen ?","1.) Goldfisch ","2.) Wal","3.) Hai","4.) Karpfen",2));
       bewerbung.add(new Frage("Welches Wort passt nicht zu den anderen ?","1.) Batterie ","2.) Tisch","3.) Festplatte","4.) Wasserboiler",2));
       bewerbung.add(new Frage("Welcher Begriff passt zu dem Wort \"reduzieren\" am besten ?","1.) zuweisen ","2.) haushalten","3.) verringern","4.) dosieren",3));
       bewerbung.add(new Frage("Welcher Begriff passt zu dem Wort \"vereiteln\" am besten ?","1.) verhindern ","2.) verleumden","3.) verweigern","4.) verneinen",1));
       bewerbung.add(new Frage("Welcher Begriff passt zu dem Wort \"unkonzentriert\" am besten ?","1.) verwirrt ","2.) hektisch","3.) abgelenkt","4.) nervös",3));
       bewerbung.add(new Frage("Welcher Begriff passt zu dem Wort \"Realität\" am besten ?","1.) Klarheit ","2.) Wahrheit","3.) Nüchternheit","4.) Scharfsinn",2));
       bewerbung.add(new Frage("Welcher Begriff passt zu dem Wort \"primitiv\" am besten ?","1.) eingeschränkt ","2.) roh","3.) unsensibel","4.) einfach",4));
       bewerbung.add(new Frage("Welcher Begriff passt zu dem Wort \"provisorisch\" am besten ?","1.) beeinträchtigt ","2.) minderwertig","3.) behelfsmäßig","4.) simpel",3));
       bewerbung.add(new Frage("Ergänzen Sie das Sprichwort:  Hochmut kommt... ","1.) niemals gut ","2.) vor dem Fall","3.) vor dem Knall","4.) von zu viel Mut",2));
       bewerbung.add(new Frage("Ergänzen Sie das Sprichwort:  Hunde, die bellen, ... ","1.) zerreißen dich. ","2.) sollten von einem Tierarzt untersucht werden.  ","3.) sind nicht gefährlich.","4.) beißen nicht.",4));
       bewerbung.add(new Frage("Ergänzen Sie das Sprichwort:  Wer den Pfennig nicht ehrt ... ","1.) ist des Talers nicht wert.","2.) ist des Schillings nicht wert.","3.) ist des Euros nicht wert.","4.) ist des Dollars nicht wert.",1));
       bewerbung.add(new Frage("Ergänzen Sie das Sprichwort:  Je später der Abend ... ","1.) desto dunkler die Nacht. ","2.) desto früher ist's morgen.","3.) desto besser der Wein.","4.) desto schöner die Gäste.",4));
       bewerbung.add(new Frage("Ergänzen Sie das Sprichwort:  Wer anderen eine Grube gräbt,  ... ","1.) ist ein gemeines Schwein.","2.) macht sich strafbar.","3.) fällt selbst hinein.","4.) ist sehr hilfsbereit.",3));
       bewerbung.add(new Frage("Ergänzen Sie das Sprichwort:  Viele Hände  ... ","1.) sind der Anfang vom ende.","2.) verderben den Brei.","3.) aber keine Spende.","4.) machen bald ein Ende.",4));
       bewerbung.add(new Frage("Welcher Feldherr wurde 1815 wo geschlagen ","1.) Bismarck verlor gegen die Österreicher bei Sedan","2.) Napoleon verlor die Schlacht von Waterloo","3.) Metternich verlor bei Königsgrätz","4.) Barbarossa verlor die Schlacht um Jerusalem",2));
       bewerbung.add(new Frage("Welche Revolution begann im November 1917?","1.) Die Russische Revolution","2.) Die Französische Revolution","3.) Die Industrielle Revolution","4.) Bauern Revolution",1));
       bewerbung.add(new Frage("Was geschah am 4. Juli 1776? ","1.) Erklärung der amerikanischen Unabhängikeit","2.) Revolte gegen die britische Herrschaft in Ostindien","3.) Niederlage gegen Frankreich in der Schlacht bei Trafalgar","4.) Ausbruch des Burgenkrieges in Südafrika",1));
       bewerbung.add(new Frage("Was geschah im November 1989? ","1.) Der Fall der Berliner Mauer.","2.) Der Kalte Krieg war zu Ende.","3.) Die DDR trat der Bundesrepublik Deutschland bei.","4.) Amerika führt Krieg gegen den Irak.",1));
       bewerbung.add(new Frage("Welche Parteien bilden die aktuelle Bundesregierung? ","1.) SPD - Grüne","2.) CDU - FDP","3.) Linke - FDP","4.) Linke – SPD",2));
       bewerbung.add(new Frage("Wer war(en) der/die Spitzenkandidat(en) der Grünen? ","1.) Ethen und Heptin","2.) Amadeus und Schneider","3.) Künast und Trittin","4.) Fischer",3));
       bewerbung.add(new Frage("Wann wurde die CDU geründet? ","1.) 12.Dezember 1933","2.) 26. Juni 1945","3.) 24. Mai 1956","4.) 07. August 1974",3));
       bewerbung.add(new Frage("Welcher ist der höchste Berg der Welt? ","1.) Pamir","2.) K2","3.) Mount Everest","4.) Mont Blanc",3));
       bewerbung.add(new Frage("Welcher ist der längste Fluss der Welt? ","1.) Nil","2.) Rhein","3.) Mississippi River","4.) Amazonas",1));
       bewerbung.add(new Frage("Wie viele aktive Vulkane gibt es auf der Erde? ","1.) ca. 500","2.) ca. 750","3.) ca. 1300","4.) ca. 1900",4));
       bewerbung.add(new Frage("Was ist das größte Land der Welt? ","1.) USA","2.) China","3.) Russland","4.) Kanada",3));
       bewerbung.add(new Frage("Was ist die Hauptstadt der Türkei? ","1.) Kopenhagen","2.) Istanbul ","3.) Ankara","4.) Antalya",3));
       bewerbung.add(new Frage("Was ist die Hauptstadt von Japan? ","1.) Peking","2.)  Hiroshima","3.) Tokio","4.) Yokohama",3));
       bewerbung.add(new Frage("Wie heißt der Leadsänger der Toten Hosen?", "1.) Hustinetti", "2.) Nimm Zwei","3.) Campino", "4.) Wick Blau",3));
       bewerbung.add(new Frage("Werden Spielkarten auf der Rückseite gekennzeichnet, dann sind sie...?","1.) verbleit","2.) abgekupfert","3.) gezinkt","4.) vergoldet",3));
       bewerbung.add(new Frage("Was ist das größte Land der Welt? ","1.) USA","2.) China","3.) Russland","4.) Kanada",3));
       bewerbung.add(new Frage("Was ist die Hauptstadt von Australien? ","1.) Sydney","2.)  Melbourne","3.) Perth","4.) Canberra",4));
       bewerbung.add(new Frage("Wie groß ist Deutschland? ","1.) 357.104,07km²","2.) 314.156,26km² ","3.) 424.456,57km²","4.) 605.154,78km²",1));
       bewerbung.add(new Frage("Was ist keine Temperaturskale? ","1.) Celsius","2.) Newton ","3.) Fahrenheit","4.) Kelvin",2));
       bewerbung.add(new Frage("Ein PS ist wie viel KW? ","1.) 0,56","2.) 0,74 ","3.) 0,96","4.) 1,34",2));
       bewerbung.add(new Frage("In welcher Einheit wird die Stärke des Stroms gemessen? ","1.) A - Ampere","2.) V - Volt","3.) N - Newton","4.) R - Radius",1));
       bewerbung.add(new Frage("Unter welchem Bundeskanzler wurde das Arbeitslosengeld II. eingeführt? ","1.) Atze Schröder","2.) Gerhard Schröder","3.) Helmut Kohl","4.) Blumen Kohl",2));
       bewerbung.add(new Frage("Wie viele Einwohner hat die EU? ","1.) ca. 500 Millionen","2.) ca. 1 Milliarde ","3.) 314.159.265","4.) ca. 750 Millionen",1));
       bewerbung.add(new Frage("Was ist die Hauptstadt von Rumänien? ","1.) Bukarest","2.) Budapest","3.) Bratislava","4.) Belgrad",1));
       bewerbung.add(new Frage("Wer war kein Bundespräsident? ","1.) Richard von Weizsäcker","2.) Theodor Heuss","3.) Kurt Georg Kiesinger","4.) Walter Scheel",3));
       bewerbung.add(new Frage("In welchem Jahr endete der 1. Weltkrieg? ","1.) 1912","2.) 1918 ","3.) 1933","4.) 1945",2));
       bewerbung.add(new Frage("Was ist keine Inselgruppe? ","1.) McDonald-Inseln","2.) Burgerking-Inseln","3.) Spitzbergen","4.) Franz-Joseph-Land",2));
       bewerbung.add(new Frage("Was ist keine Programmiersprache? ","1.) Plankalkül","2.) C++","3.) C#","4.) C - -",4));
       bewerbung.add(new Frage("Welche Spannung kommt aus den deutschen Haushaltssteckdose? ","1.) 220 Watt","2.) 220 Volt","3.) 230 Watt","4.) 230 Volt",4));
       bewerbung.add(new Frage("Was gibt die Funktion f ‘(x) an der Stelle x für die Funktion f(x) an?","1.) Schnittpunkt","2.) Steigung","3.) gar nichts","4.) Abstand zur x-Achse",2));
       bewerbung.add(new Frage("Wie heißt unser Verteidigungsminister? ","1.) Wolfgang Schäuble","2.) Guido Westerwelle","3.) Dr. Karl Theodor Freiherr zu Gutenberg","4.) Franz Josef Jung",3));
       bewerbung.add(new Frage("Wie nennen die Finnen ihr eigenes Land ","1.) Sumami","2.) Supapi","3.) Suomi","4.) Suopi",3));
       bewerbung.add(new Frage("Welches dieser Tiere ist kein Insekt ","1.) Stubenfliege","2.)Heuschrecke","3.) Hirschkäfer","4.)Kreuzspinne",4));
       bewerbung.add(new Frage("Ergänzen Sie die fehlende Zahl: 1; 3; 5; 7; 9; ? ","1.) 10","2.) 11","3.) 13","4.) 17",2));
       bewerbung.add(new Frage("Ergänzen Sie die fehlende Zahl: 4; 7; 13; 25; 49; ? ","1.) 67","2.) 123","3.) 97","4.) 65",3));
       bewerbung.add(new Frage("Ergänzen Sie die fehlende Zahl: 8; 11; 4; 7; 0; ? ","1.) 2","2.) 12","3.) -2","4.) 3",4));
       bewerbung.add(new Frage("Ergänzen Sie die fehlende Zahl: 2; 3; 5; 7; 11; ? ","1.) 13","2.) 14","3.) 12","4.) 17",1));
       bewerbung.add(new Frage("Ergänzen Sie die fehlende Zahl: 4; 11; 15; 26; 41; ? ","1.) 59","2.) 88","3.) 72","4.) 67",4));
       bewerbung.add(new Frage("Ergänzen Sie die fehlende Zahl: -9; -8; -4; 3; 13; ? ","1.) 17","2.) 21","3.) 26","4.) 28",3));
       }

  // Anfang Methoden

       public Frage gibFragenobjekt(){
          int i = r.nextInt(bewerbung.size());
          Frage temp = bewerbung.get(i);
          bewerbung.remove(i);
          return temp;
       }
  // Ende Methoden
}