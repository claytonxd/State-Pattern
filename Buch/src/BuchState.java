public abstract class BuchState {
    protected Buch buch;

    public BuchState(final Buch buch) {
        this.buch = buch;
    }

    public abstract void ausleihen();
}
