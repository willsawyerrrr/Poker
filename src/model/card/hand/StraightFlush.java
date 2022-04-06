package src.model.card.hand;

import src.model.card.Card;
import src.model.card.Suit;
import src.model.card.Value;

import java.util.List;

public class StraightFlush extends FiveCardHand {
    private Suit suit;
    private Value highestValue;

    public StraightFlush(List<Card> hand) {
        super(hand, Rank.Straight);
        this.suit = hand.get(0).getSuit();
        this.highestValue = hand.get(4).getValue();
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getHighestValue() {
        return highestValue;
    }

    @Override
    public int compareTo(FiveCardHand o) {
        if (super.compareTo(o) != 0) {
            return super.compareTo(o);
        }

        StraightFlush ot = (StraightFlush) o;
        return this.highestValue.compareTo(ot.getHighestValue());
    }
}
