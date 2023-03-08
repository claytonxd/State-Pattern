public class Buch {

    private BuchState state;

    public Buch(final BuchState state) {
        this.state = state;
    }

    public void ausleihen() {
        this.state.ausleihen();
    }

    public void setState(BuchState state) {
        this.state = state;
    }
}
