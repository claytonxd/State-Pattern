package statepattern.data;

import statepattern.data.state.ITierState;

import java.util.Objects;

public class Tier {

    /**
     * Der Name des Tiers (kann nur bei Initialisierung festgelegt werden).
     */
    private final String name;

    /**
     * Der Zustand des Tiers
     */
    private ITierState tierState;


    /*=-----------------------------------------=*/

    /***
     * @param name Der Name des Tiers
     * @param defaultState Der Standardzustand des Tiers
     */
    public Tier(final String name, final ITierState defaultState) {
        this.name = name;
        this.tierState = Objects.requireNonNull(defaultState);
        System.out.println("[DEBUG] Tier " + name + " wurde erstellt mit State " + defaultState.getClass().getName());
    }

    /*=-----------------------------------------=*/

    /**
     * Diese Methode ist ausschließlich für die Logik des Zustands gedacht
     * und wurde entwickelt, einfach in den nächsten Zustand umzuschalten,
     * um die Effekte der Änderung zu zeigen
     */
    public void cycleState() {
        this.setTierState(this.tierState.nextState());
    }

    /**
     * Methode zum Fuettern des Tiers (abhängig vom Zustand)
     */
    public void fuettern() {
        this.tierState.fuettern(this);
    }

    /*=-----------------------------------------=*/

    /**
     * Setzt den Zustand des Tiers
     * @param tierState Der neue Zustand
     */
    public void setTierState(ITierState tierState) {
        this.tierState = Objects.requireNonNull(tierState);
        System.out.println("[DEBUG] State switched to " + tierState.getClass().getName());
    }

    /**
     * @return Zustand des Tiers
     */
    public ITierState getTierState() {
        return tierState;
    }

    /**
     * @return Den Namen des Tieres
     */
    public String getName() {
        return name;
    }
}



