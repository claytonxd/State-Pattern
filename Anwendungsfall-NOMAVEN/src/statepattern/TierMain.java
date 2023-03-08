package statepattern;

import statepattern.data.Tier;
import statepattern.data.state.HungrigTierState;
import statepattern.data.state.NichtHungrigTierState;

public class TierMain {

    public static void main(String[] args) {
        // Beide Tiere initialisieren
        final Tier tier1 = new Tier("Ronaldo", new HungrigTierState());
        final Tier tier2 = new Tier("Uwuwewewe onyetenwewe ugweuhem osas", new NichtHungrigTierState());

        // Beide Tiere 1x fuettern
        tier1.fuettern(); // Das hier sollte gefüttert werden, da es hungrig ist
        tier2.fuettern(); // Das hier sollte nicht gefütter werden, da es nicht hungrig ist

        // Den Status einmal wechseln
        tier1.cycleState();
        tier2.cycleState();

        // Beide Tiere 1x fuettern
        tier1.fuettern(); // Das hier sollte nicht gefütter werden, da es nicht hungrig ist
        tier2.fuettern(); // Das hier sollte gefüttert werden, da es hungrig ist
    }

}





