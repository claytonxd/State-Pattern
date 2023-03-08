package statepattern.data.state;

import statepattern.data.Tier;

public class NichtHungrigTierState implements ITierState {

    @Override
    public void fuettern(final Tier tier) {
        System.out.println("Das Tier " + tier.getName() + " hat keinen Hunger und hat nichts gegessen.");
    }

    @Override
    public ITierState nextState() {
        return new HungrigTierState();
    }
}


