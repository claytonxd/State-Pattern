package statepattern.data.state;

import statepattern.data.Tier;

public class HungrigTierState implements ITierState {

    @Override
    public void fuettern(final Tier tier) {
        System.out.println("Das Tier " + tier.getName() + " hat etwas gegessen");
    }

    @Override
    public ITierState nextState() {
        return new NichtHungrigTierState();
    }
}



