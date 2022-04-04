package src.model.card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a seven-card hand formed out of a player's pocket cards and the
 * community cards on the table.
 */
public class Hand {
    /** The player's pocket cards and the community cards. */
    private List<Card> hand;
    /** The player's best five-card hand. */
    private List<Card> bestHand;
    /** The rank of the player's best five-card hand. */
    private Rank rank;

    /** Card values in a Royal Flush. */
    private static final List<Value> ROYAL_FLUSH_VALUES = List.of(
            Value.Ace, Value.King, Value.Queen, Value.Jack, Value.Ten);
    /** Bitwise OR of suit encodings in a Flush. */
    private static final List<Byte> FLUSH_ENCODINGS = List.of(
        // since only 0b0001, 0b0010, 0b0100 and 0b1000 will appear if a flush
        (byte) 1, (byte) 2, (byte) 4, (byte) 8
    );
    /** Bitwise OR of value encodings in a Straight.*/
    private static final List<Short> STRAIGHT_ENCODINGS = List.of(
        // since 31 = 0b11111, which will only appear if hand has a straight
        (short) (31 << 8), (short) (31 << 7), (short) (31 << 6), // A, K, Q high
        (short) (31 << 5), (short) (31 << 4), (short) (31 << 3), // J, T, 9 high
        (short) (31 << 2), (short) (31 << 1), (short) (31 << 0), // 8, 7, 6 high
        (short) (0b10000000001111)                               // A... 5,4,3,2
    );

    /**
     * Creates a new empty hand.
     */
    public Hand() {
        hand = new ArrayList<Card>();
    }

    /**
     * Adds the given card to the hand.
     * 
     * @param card card to add to the hand
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Adds the given cards to the hand.
     * 
     * @param cards cards to add to the hand
     */
    public void addAllCards(List<Card> cards) {
        hand.addAll(cards);
    }

    /**
     * Returns the player's best five-card hand.
     * 
     * @return the player's best five-card hand
     */
    public List<Card> getBestHand() {
        return bestHand;
    }

    /**
     * Returns the rank of the player's best five-card hand.
     * 
     * @return the rank of the player's best five-card hand
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Evalueates the highest five-card hand and its rank.
     */
    public void evaluateHand() {
        List<Card> tempHand;
        List<Card> bestHand = new ArrayList<Card>(hand);
        Rank bestRank = Rank.HighCard;
        for (Card card1 : hand) {
            for (Card card2 : hand) {
                if (card1 != card2) {
                    tempHand = new ArrayList<Card>(hand);
                    tempHand.remove(card1);
                    tempHand.remove(card2);
                    Rank rank = evaluateFiveCardHand(tempHand);
                    if (rank.ordinal() > bestRank.ordinal()) {
                        bestRank = rank;
                        bestHand = tempHand;
                    }
                }
            }
        }

        this.bestHand = bestHand;
        this.rank = bestRank;
    }

    /**
     * Evaluates the rank of the given five-card hand.
     * 
     * @param hand a five-card hand
     */
    private Rank evaluateFiveCardHand(List<Card> hand) {
        // suits represented by the hand
        List<Suit> suits = new ArrayList<Suit>(5);
        // bitwise OR of the encodings of the suits in the hand
        byte suitsEncoding = 0;

        // values represented by the hand
        List<Value> values = new ArrayList<Value>(5);
        // bitwise OR of the encodings of the values in the hand
        short valuesEncoding = 0;

        for (Card card : hand) {
            suits.add(card.getSuit());
            suitsEncoding |= card.getSuitEncoding();

            values.add(card.getValue());
            valuesEncoding |= card.getValueEncoding();
        }

        Boolean royal = values.containsAll(ROYAL_FLUSH_VALUES);
        Boolean flush = FLUSH_ENCODINGS.contains(suitsEncoding);
        Boolean straight = STRAIGHT_ENCODINGS.contains(valuesEncoding);

        Map<Value, Integer> counter = new HashMap<Value, Integer>();
        for (Value value : values) {
            if (counter.keySet().contains(value)) {
                counter.put(value, counter.get(value) + 1);
            } else {
                counter.put(value, 1);
            }
        }

        Collection<Integer> counts = counter.values();
        if (counts.contains(5)) {
            return Rank.FiveOfAKind;
        } else if (royal && flush) {
            return Rank.RoyalFlush;
        } else if (straight && flush) {
            return Rank.StraightFlush;
        } else if (counts.contains(4)) {
            return Rank.FourOfAKind;
        } else if (counts.contains(3) && counts.contains(2)) {
            return Rank.FullHouse;
        } else if (flush) {
            return Rank.Flush;
        } else if (straight) {
            return Rank.Straight;
        } else if (counts.contains(3)) {
            return Rank.ThreeOfAKind;
        } else if (counter.keySet().stream().filter(x -> counter.get(x) == 2).count() == 2) {
            return Rank.TwoPair;
        } else if (counts.contains(2)) {
            return Rank.OnePair;
        } else {
            return Rank.HighCard;
        }
    }
}
