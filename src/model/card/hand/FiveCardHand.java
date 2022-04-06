package src.model.card.hand;

import src.model.card.Card;
import src.model.card.Value;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public abstract class FiveCardHand implements Comparable<FiveCardHand> {
    private List<Card> hand;
    private Rank rank;

    /** Bitwise OR of value encoings in a Royal Flush. */
    private static final short ROYAL_FLUSH_ENCODING = (short) 0b1111100000000;
    /** Bitwise OR of suit encodings in a Flush. */
    private static final List<Byte> FLUSH_ENCODINGS = List.of(
        (byte) 1, (byte) 2, (byte) 4, (byte) 8
    );
    /** Bitwise OR of value encodings in a Straight.*/
    private static final List<Short> STRAIGHT_ENCODINGS = List.of(
        // since 31 = 0b11111, which is bitwise OR of five consecutive cards
        (short) (31 << 8), (short) (31 << 7), (short) (31 << 6), // A, K, Q high
        (short) (31 << 5), (short) (31 << 4), (short) (31 << 3), // J, T, 9 high
        (short) (31 << 2), (short) (31 << 1), (short) (31 << 0), // 8, 7, 6 high
        (short) (0b10000000001111)                               // A... 5,4,3,2
    );

    public FiveCardHand(List<Card> hand, Rank rank) {
        this.hand = hand;
        this.rank = rank;
    }

    public List<Card> getHand() {
        return hand;
    }

    public Rank getRank() {
        return rank;
    }

    public int compareTo(FiveCardHand o) {
        return this.rank.compareTo(o.getRank());
    }

    /**
     * Evaluates the rank of the given five-card hand.
     * 
     * @param hand a five-card hand
     */
    static Rank evaluateFiveCardHand(List<Card> hand) {
        // bitwise OR of the encodings of the suits in the hand
        byte suitsEncoding = 0;
        // values represented by the hand
        List<Value> values = new ArrayList<Value>(5);
        // bitwise OR of the encodings of the values in the hand
        short valuesEncoding = 0;

        for (Card card : hand) {
            suitsEncoding |= card.getSuitEncoding();
            values.add(card.getValue());
            valuesEncoding |= card.getValueEncoding();
        }

        Boolean royal = (valuesEncoding == ROYAL_FLUSH_ENCODING);
        Boolean flush = FLUSH_ENCODINGS.contains(suitsEncoding);
        Boolean straight = STRAIGHT_ENCODINGS.contains(valuesEncoding);

        Map<Value, Integer> counter = new EnumMap<Value, Integer>(Value.class);
        for (Value value : values) {
            if (counter.containsKey(value)) {
                counter.put(value, counter.get(value) + 1);
            } else {
                counter.put(value, 1);
            }
        }

        if (counter.containsValue(5)) {
            return Rank.FiveOfAKind;
        } else if (royal && flush) {
            return Rank.RoyalFlush;
        } else if (straight && flush) {
            return Rank.StraightFlush;
        } else if (counter.containsValue(4)) {
            return Rank.FourOfAKind;
        } else if (counter.containsValue(3) && counter.containsValue(2)) {
            return Rank.FullHouse;
        } else if (flush) {
            return Rank.Flush;
        } else if (straight) {
            return Rank.Straight;
        } else if (counter.containsValue(3)) {
            return Rank.ThreeOfAKind;
        } else if (counter.keySet().stream().filter(
                x -> counter.get(x) == 2).count() == 2) {
            return Rank.TwoPair;
        } else if (counter.containsValue(2)) {
            return Rank.OnePair;
        } else {
            return Rank.HighCard;
        }
    }
}