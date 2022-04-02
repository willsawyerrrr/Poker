package src.poker.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

/**
 * Represents a deck of cards used to play a game of poker. This deck does not
 * necessarily consist of only one standard deck, but it will consist of an
 * integer number of standard decks.
 */
public class Deck {
    /** The deck of cards. */
    private List<Card> deck;
    /** Random object used to generate pseudo-random cards. */
    private Random random;

    /**
     * Creates a new deck of cards with the given number of standard decks.
     * 
     * @param numDecks the number of standard decks to include in this deck
     */
    public Deck(Integer numDecks) {
        deck = new ArrayList<Card>(52 * numDecks);
        random = new Random();

        for (int i = 1; i <= numDecks; i++) {
            for (Suit suit : Suit.values()) {
                for (Value value : Value.values()) {
                    deck.add(new Card(suit, value));
                }
            }
        }
    }

    /**
     * Returns the deck.
     * 
     * @return the deck
     */
    public List<Card> getDeck() {
        return deck;
    }

    /**
     * Returns a pseudo-random card from the deck.
     * 
     * @return a pseudo-random card from the deck
     */
    public Card generateCard() {
        Integer index = random.nextInt(deck.size());
        Card result = deck.get(index);
        deck.remove(result);
        return result;
    }

    /**
     * Returns a human-readable string representing the deck.
     * This string is formatted as:
     * <code>
     *      <em>Card1</em>
     *      <em>Card2</em>
     *      <em>Card3</em>
     *      ...
     *      <em>CardN</em>
     * </code>
     * where <em>CardM</em> is the string representation of <em>CardM</em> and
     * <em>N</em> is the number of cards currently in the deck.
     * 
     * @see Card#toString
     * 
     * @return the human-readable string representation of the deck
     */
    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for (Card card : deck) {
            joiner.add(card.toString());
        }
        return joiner.toString();
    }
}