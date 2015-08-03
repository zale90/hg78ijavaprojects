# Einleitung #

Hier findet ihr alle Informationen um eure Minigames so anzupassen, dass das Hauptspiel sie benutzen kann.


# Speichern von Dateien #

Alle Dateien, die das Spiel benötigt, sollten im Ordner files/minigames/Game-Name gespeichert werden.


# Konstruktor #

Im Konstruktor eures Minispiels solltet ihr euer GUI erstellen und alles für den Start des Spiels vorbereiten, aber das GUI noch unsichtbar und das Spiel nicht beginnen lassen.

Achtung: Euer Fenster solltet ihr so einstellen, das es immer vor allen anderen Fenstern ist. Benutzt dazu die Methode setAlwaysOnTop(true) von Window.


# Interface Minispiel #

Alle Minigames müssen das Interface Minispiel implementieren. Dieses stellt sicher, dass in jedem Minispiel die folgende Methode vorhanden ist:
public void start(Spiel spiel).

Das Minispiel wird vom Hauptspiel über diese Methode angesprochen und bekommt das aktuelle Spiel-Objekt übergeben. Aus diesem Objekt bekommt es benötigte Daten und kann zum Schluss Änderungen daran vornehmen (siehe Minispiel Ende).
Bitte nicht direkt Daten von Spiel verändern, sondern nur über Information-Arrays und die minispielEnde-Methoden.

Wenn die start-Methode aufgerufen wird macht ihr euer GUI sichtbar und lasst das Minispiel beginnen. Außerdem solltet ihr das Spiel-Objekt was euch übergeben wurde speichern, da ihr es später noch braucht.


# Minispiel Ende #

Wenn euer Minispiel beendet ist müsst ihr am Spiel-Objekt was ihr gespeichert habt eine der folgenden Methoden aufrufen, denen ihr, um Veränderungen an Bedürfnissen, Geld oder sonstwas vorzunehmen, Informationsobjekte übergeben müsst:

void minispielEnde(Information[.md](.md) infos)

void minispielEnde(Information[.md](.md) infos, String ausgabe)

(Es muss ein Information-Array übergeben werden, nicht nur eine Information, das Wiki zeigt die eckigen Klammern nicht an.)

Nach dem Aufruf einer der minispielEnde-Methoden **muss** das Minispiel beendet werden.
Beenden heißt, dass das Minispiel in den Zustand versetzt wird, in dem es war, bevor die start-Methode aufgerufen wurde, damit wir es so oft wir wollen wieder starten können.
In den meisten Fällen müsste also das GUI invisible gemacht werden und alle Buttons, Einstellungen u.s.w. wieder so gesetzt werden, wie sie im Konstruktor eingestellt wurden.
(Niemals System.exit verwenden, sonst wird das Hauptspiel mit beendet!)


# Informationsobjekte #

Einem Informationsobjekt müsst ihr im Konstruktor 3 int-Werte übergeben; 1. Wert: was soll geändert werden? 2. Wert: wie soll es geändert werden? 3. Wert: um/auf welchen Wert soll es geändert werden?
Welche Werte was bewirken seht ihr in der Information-Klasse.


# Informationspaket #

Alle benötigten Dateien befinden sich im Transfer-Ordner!