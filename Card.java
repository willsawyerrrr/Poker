public class Card {
    /** The card's suit. */
    private Suit suit;
    /** The card's value. */
    private Value value;

    /**
     * Creates a new card with the given suit and value.
     * 
     * @param suit  the card's suit
     * @param value the card's value
     */
    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    /**
     * Returns the card's suit.
     * 
     * @return the card's suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns the card's value.
     * 
     * @return the card's value
     */
    public Value getValue() {
        return value;
    }

    /**
     * Returns a human-readable string representing the card.
     * This string is formatted as:
     * <code><em>value</em> of <em>suit</em></code>.
     * 
     * @return the human-readable string representation of the card
     */
    @Override
    public String toString() {
        return String.format("%s of %s", value, suit);
    }
}
