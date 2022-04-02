package src.poker.card;

/**
 * Represents a playing card with a suit (Clubs, Diamonds, Hearts or Spades)
 * and value (Two, Three, ..., Ten, Jack, Queen, King, Ace).
 */
public class Card {
    /** The card's suit. */
    private Suit suit;
    /** Encoding of the card's suit. */
    private byte suitEncoding;
    /** The card's value. */
    private Value value;
    /** Encoding of the card's value. */
    private short valueEncoding;

    /**
     * Creates a new card with the given suit and value.
     * 
     * @param suit  the card's suit
     * @param value the card's value
     */
    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.suitEncoding = (byte) (1 << suit.ordinal());
        this.value = value;
        this.valueEncoding = (short) (1 << value.ordinal());
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
     * Returns the encoding of the card's suit.
     * 
     * @return the encoding of the card's suit
     */
    public byte getSuitEncoding() {
        return suitEncoding;
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
     * Returns the encoding of the card's value.
     * 
     * @return the encoding of the card's value
     */
    public short getValueEncoding() {
        return valueEncoding;
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
