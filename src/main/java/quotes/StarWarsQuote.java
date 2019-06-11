package quotes;

public class StarWarsQuote {
    private String starWarsQuote;

    public StarWarsQuote(){}

    public StarWarsQuote(String text) {
        this.starWarsQuote = text;
    }

    public String getStarWarsQuote() {
        return starWarsQuote;
    }

    public void setStarWarsQuote(String starWarsQuote) {
        this.starWarsQuote = starWarsQuote;
    }

    @Override
    public String toString() {
        return String.format("Quote: %s", starWarsQuote);
    }
}
