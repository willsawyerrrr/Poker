import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

public class Deck {
    private List<Card> deck;
    private Random random;

    private Card aceClubs = new Card(Suit.Clubs, Value.Ace);
    private Card twoClubs = new Card(Suit.Clubs, Value.Two);
    private Card threeClubs = new Card(Suit.Clubs, Value.Three);
    private Card fourClubs = new Card(Suit.Clubs, Value.Four);
    private Card fiveClubs = new Card(Suit.Clubs, Value.Five);
    private Card sixClubs = new Card(Suit.Clubs, Value.Six);
    private Card sevenClubs = new Card(Suit.Clubs, Value.Seven);
    private Card eightClubs = new Card(Suit.Clubs, Value.Eight);
    private Card nineClubs = new Card(Suit.Clubs, Value.Nine);
    private Card tenClubs = new Card(Suit.Clubs, Value.Ten);
    private Card jackClubs = new Card(Suit.Clubs, Value.Jack);
    private Card queenClubs = new Card(Suit.Clubs, Value.Queen);
    private Card kingClubs = new Card(Suit.Clubs, Value.King);
    private Card aceDiamonds = new Card(Suit.Diamonds, Value.Ace);
    private Card twoDiamonds = new Card(Suit.Diamonds, Value.Two);
    private Card threeDiamonds = new Card(Suit.Diamonds, Value.Three);
    private Card fourDiamonds = new Card(Suit.Diamonds, Value.Four);
    private Card fiveDiamonds = new Card(Suit.Diamonds, Value.Five);
    private Card sixDiamonds = new Card(Suit.Diamonds, Value.Six);
    private Card sevenDiamonds = new Card(Suit.Diamonds, Value.Seven);
    private Card eightDiamonds = new Card(Suit.Diamonds, Value.Eight);
    private Card nineDiamonds = new Card(Suit.Diamonds, Value.Nine);
    private Card tenDiamonds = new Card(Suit.Diamonds, Value.Ten);
    private Card jackDiamonds = new Card(Suit.Diamonds, Value.Jack);
    private Card queenDiamonds = new Card(Suit.Diamonds, Value.Queen);
    private Card kingDiamonds = new Card(Suit.Diamonds, Value.King);
    private Card aceHearts = new Card(Suit.Hearts, Value.Ace);
    private Card twoHearts = new Card(Suit.Hearts, Value.Two);
    private Card threeHearts = new Card(Suit.Hearts, Value.Three);
    private Card fourHearts = new Card(Suit.Hearts, Value.Four);
    private Card fiveHearts = new Card(Suit.Hearts, Value.Five);
    private Card sixHearts = new Card(Suit.Hearts, Value.Six);
    private Card sevenHearts = new Card(Suit.Hearts, Value.Seven);
    private Card eightHearts = new Card(Suit.Hearts, Value.Eight);
    private Card nineHearts = new Card(Suit.Hearts, Value.Nine);
    private Card tenHearts = new Card(Suit.Hearts, Value.Ten);
    private Card jackHearts = new Card(Suit.Hearts, Value.Jack);
    private Card queenHearts = new Card(Suit.Hearts, Value.Queen);
    private Card kingHearts = new Card(Suit.Hearts, Value.King);
    private Card aceSpades = new Card(Suit.Spades, Value.Ace);
    private Card twoSpades = new Card(Suit.Spades, Value.Two);
    private Card threeSpades = new Card(Suit.Spades, Value.Three);
    private Card fourSpades = new Card(Suit.Spades, Value.Four);
    private Card fiveSpades = new Card(Suit.Spades, Value.Five);
    private Card sixSpades = new Card(Suit.Spades, Value.Six);
    private Card sevenSpades = new Card(Suit.Spades, Value.Seven);
    private Card eightSpades = new Card(Suit.Spades, Value.Eight);
    private Card nineSpades = new Card(Suit.Spades, Value.Nine);
    private Card tenSpades = new Card(Suit.Spades, Value.Ten);
    private Card jackSpades = new Card(Suit.Spades, Value.Jack);
    private Card queenSpades = new Card(Suit.Spades, Value.Queen);
    private Card kingSpades = new Card(Suit.Spades, Value.King);

    public Deck() {
        deck = new ArrayList<Card>(52);
        random = new Random();

        deck.add(aceClubs);
        deck.add(twoClubs);
        deck.add(threeClubs);
        deck.add(fourClubs);
        deck.add(fiveClubs);
        deck.add(sixClubs);
        deck.add(sevenClubs);
        deck.add(eightClubs);
        deck.add(nineClubs);
        deck.add(tenClubs);
        deck.add(jackClubs);
        deck.add(queenClubs);
        deck.add(kingClubs);
        deck.add(aceDiamonds);
        deck.add(twoDiamonds);
        deck.add(threeDiamonds);
        deck.add(fourDiamonds);
        deck.add(fiveDiamonds);
        deck.add(sixDiamonds);
        deck.add(sevenDiamonds);
        deck.add(eightDiamonds);
        deck.add(nineDiamonds);
        deck.add(tenDiamonds);
        deck.add(jackDiamonds);
        deck.add(queenDiamonds);
        deck.add(kingDiamonds);
        deck.add(aceHearts);
        deck.add(twoHearts);
        deck.add(threeHearts);
        deck.add(fourHearts);
        deck.add(fiveHearts);
        deck.add(sixHearts);
        deck.add(sevenHearts);
        deck.add(eightHearts);
        deck.add(nineHearts);
        deck.add(tenHearts);
        deck.add(jackHearts);
        deck.add(queenHearts);
        deck.add(kingHearts);
        deck.add(aceSpades);
        deck.add(twoSpades);
        deck.add(threeSpades);
        deck.add(fourSpades);
        deck.add(fiveSpades);
        deck.add(sixSpades);
        deck.add(sevenSpades);
        deck.add(eightSpades);
        deck.add(nineSpades);
        deck.add(tenSpades);
        deck.add(jackSpades);
        deck.add(queenSpades);
        deck.add(kingSpades);
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