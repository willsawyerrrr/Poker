import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

public class Deck {
    private List<Card> deck;
    private Random random;

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

    public List<Card> getDeck() {
        return deck;
    }

    public Card generateCard() {
        Integer index = random.nextInt(deck.size());
        Card result = deck.get(index);
        deck.remove(result);
        return result;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for (Card card : deck) {
            joiner.add(card.toString());
        }
        return joiner.toString();
    }
}