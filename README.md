# State Pattern
Handout zum Referat "State Pattern" von Kevin Clayton, Nico Hilgart und Alexander Domanja
<br>
## Gliederung
- Wie funktioniert das State Pattern
- Einsatzbereiche des Patterns
- Klassendiagramm für das Beispiel
- Praxis: Anwendungsfall "Tier"
- Praxis: Code
- Praxis: Klassendiagramm

## Nummer 1) Wie funktioniert das State Pattern
Das sogenannte "State Pattern" (auf deutsch Zustandsmuster) beschreibt die Kapselung von zustandsabhängigen Verhaltensweisen eines Objekts. Das Verhalten eines Objektes ist in den meisten Fällen abhängig von seinem Zustand (State). In den meisten Fällen wird hierfür eine große `switch` Anweisung oder auch ein großer `if-else` Block geschrieben. Das State Pattern ist die Lösung für solche Verhaltensprobleme. Eine klassische Implementation des Patterns schlägt ein `interface` (`IState`) oder eine `abstract class` (`State`) als Basisklasse vor mit virtuellen Methoden wie `execute()` oder `runXY()`. Von dieser Basisklasse oder dem Interface erben dann alle verschiedenen Zustände welche benötigt werden.

    public interface IState { ... };

    public class StoppedState implements IState { ... };

    public class RunningState implements IState { ... };

Die Klasse, für die diese Zustände entworfen wurden sollte dann am besten einen `private` Attribut in ihrer Klasse haben vom Typ `IState|State`. Dies ist dann der Zustand welcher über zum Beispiel einen Setter

    public void setState(IState state) { ... };

wieder gesetzt werden kann. Sobald das Objekt dann eine Verhaltensspezifische Funktion aufrufen muss kann einfach über `IState#execute(void)` die Funktion aufgerufen werden.

    public class TestClass {
        private IState state;

        public void executeExampleState() {
            // state kann über this#setState(IState) verändert werden und verändert den Zustand
            state.execute();
        }
    }

Hier ein Beispiel-Klassendiagramm, wie es typisch aufgebaut ist

![yikes](https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/State_Design_Pattern_UML_Class_Diagram.svg/400px-State_Design_Pattern_UML_Class_Diagram.svg.png)
(Quelle: Wikipedia)

## Nummer 2) Einsatzbereiche des Patterns
Es gibt viele verschiedene Einsatzbereiche in dem das Pattern eingesetzt werden kann, zum Beispiel bei einer Bücherei.
In der Bücherei gibt es verschiedene Bücher welche jeweils im Code als Klasse dargestellt werden.

    public class Buch { ... }

Dieses Buch kann ausgeliehen werden und dann wieder zurückgebracht werden. Dementsprechend haben wir 2 Zustände: Ausgeliehen und Vorhanden.
Wir wollen natürlich nicht, dass wenn das Buch ausgeliehen wurde es noch einmal ausgeliehen werden kann. Dementsprechend ist die Methode, welche vom derzeitigen Zustand abhängt die `ausleihen()` Methode.

    public class Buch { 
        public void ausleihen() {
            // Zustandsmethode
        }
    }

Als nächstes erzeugen wir die Basisklasse, in dem Fall brauchen wir eine `abstract class` da wir zum Zustand auch noch Informationen speichern müssen.

    public abstract class BuchState {
        protected Buch buch;

        public BuchState(Buch buch) { ... }

        public abstract void ausleihen();
    }

Nach dem wir die Basisklasse haben müssen die einzelnen Zustände realisiert werden.

    public class BuchStateAusgeliehen extends BuchState {
        public BuchStateAusgeliehen(Buch buch) {
            super(buch);
        }

        @Override
        public void ausleihen() {
            // Logik...
        }
    }

    public class BuchStateVorhanden extends BuchState { ... }

Sobald wir alle Zustände erzeugt haben kann in der Buch Klasse endlich der Zustand gespeichert werden. Hierfür muss einfach wie oben beschrieben der Zustand (in dem Fall `BuchState`) in ein `private` Attribut der Klasse `Buch` gespeichert werden.

    public class Buch {
        private BuchState state;

        ...
    }

Danach einen Setter dazu bauen, damit der Status auch verändert werden kann. Zum Beispiel von einem `BuchService`.

    public class Buch {
        ...

        public void setState(BuchState state) { ... }
    }


Damit können wir schon mal einen Zustand speichern, jetzt muss nur noch ein Konstruktor erstellt werden welcher einen Standardzustand annimmt für das Objekt (da ja die Bücher i.d.R in einer Datenbank liegen). 

    public class Buch {
        public Buch(BuchState state) { ... }

        ...
    }

Und damit muss nur noch der Inhalt der Zustandsmethode auf `BuchState#ausleihen()` gelegt werden. Damit sieht die vollständige Klasse so aus

    public class Buch {
        private BuchState state;

        public Buch(BuchState state) { ... }

        public void ausleihen() {
            state.ausleihen();
        }
        public void setState(BuchState state) { ... }
    }

Und so kann das Pattern ganz leicht eingesetzt werden, es gibt noch ganz viele verschiedene Einsatzbereiche.

Bsp: "Zustand eines Videospiels", "Zustände über Spieler" und im allgemeine alles was sich in Zustände unterteilen lässt.

## Klassendiagramm für das Beispiel

![Klassendiagramm](https://i.ibb.co/CK0BjBK/Buch.png)

## Praxis: Anwendungsfall Tier
Für den Praxis-Teil stellen wir das Projekt "Tier" vor.

Das Problem: Es soll durch das State-Pattern ermittelt werden ob ein Tier hungrig ist oder nicht. Dementsprechend muss auch die Methode "fuettern" im Tier Objekt anders reagieren.

## Praxis: Code

Der Code ist hier zu finden: https://github.com/claytonxd/State-Pattern/tree/master/Anwendungsfall-NOMAVEN/src/statepattern


ITierState.java:<br>
![ITierState.java](https://i.ibb.co/SxHWLHH/ITier-State.png)

HungrigTierState.java:<br>
![HungrigTierState.java](https://i.ibb.co/8BkcGd3/Hungrig-Tier-State.png)

NichtHungrigTierState.java:<br>
![NichtHungrigTierState.java](https://i.ibb.co/YkMXVdd/Nicht-Hungrig-Tier-State.png)

Tier.java:<br>
![Tier.java](https://i.ibb.co/6Dq1kYB/Tier.png)

TierMain.java:<br>
![TierMain.java](https://i.ibb.co/f4jGzt1/TierMain.png)

## Praxis: Klassendiagram

![Klassendiagramm](https://i.ibb.co/rwZp8KR/Klassendiagramm-Tier.png)

## Quellen
https://www.philipphauer.de/study/se/design-pattern/state.php<br>
https://sourcemaking.com/design_patterns/state<br>
https://de.wikipedia.org/wiki/Zustand_(Entwurfsmuster)
