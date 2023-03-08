package statepattern.data.state;

import statepattern.data.Tier;

public interface ITierState {

    void fuettern(final Tier tier);

    ITierState nextState();

}


